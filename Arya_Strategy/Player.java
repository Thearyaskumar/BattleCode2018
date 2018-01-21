// import the API.
// See xxx for the javadocs.
import bc.*;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Player {
	public static void main(String[] args) {
		// Connect to the manager, starting the game
		GameController gc = new GameController();

		// Setup hashsets for our units
		ArrayList<Robot> myWorkers = new ArrayList<Robot>();
		ArrayList<Robot> myWarriors = new ArrayList<Robot>();
		ArrayList<Factory> myFactories = new ArrayList<Factory>();
		ArrayList<Rocket> myRockets = new ArrayList<Rocket>();

		// Hashsets for the robots we've already seen
		HashSet<Integer> seen = new HashSet<Integer>();

		//Setup rocket request counts (Rocket Request Worker, etc):
		Queue<MapLocation> rrWorkers = new LinkedList<MapLocation>();
		Queue<MapLocation> rrKnights = new LinkedList<MapLocation>();
		Queue<MapLocation> rrMages = new LinkedList<MapLocation>();
		Queue<MapLocation> rrRangers = new LinkedList<MapLocation>();
		Queue<MapLocation> rrHealers = new LinkedList<MapLocation>();

		// Setup worker task counts (Worker Task Factory, etc):
		int wtExplore, wtFactory, wtRocket, wtClone, wtKarbonite, wtRepair;
		HashSet<Robot> unassignedWorkers = new HashSet<Robot>();
		int rocketsBuilt = 0; // To build the first rocket after the tech is researched

		// Factory building counts (Factory Building Knight, etc):
		int fbHealer, fbKnight, fbMage, fbRanger, fbWorker;
		fbHealer = fbKnight = fbMage = fbRanger = fbWorker = 0;

		// The brilliantly lazy research solution:
		// Hardcode 1000 turn's worth of research :)
		gc.queueResearch(UnitType.Rocket); //100
		gc.queueResearch(UnitType.Worker); //25
		gc.queueResearch(UnitType.Knight); //25
		gc.queueResearch(UnitType.Knight); //75
		gc.queueResearch(UnitType.Ranger); //25
		gc.queueResearch(UnitType.Rocket); //100
		gc.queueResearch(UnitType.Rocket); //100
		gc.queueResearch(UnitType.Ranger); //100
		gc.queueResearch(UnitType.Ranger); //200
		gc.queueResearch(UnitType.Knight); //150
		gc.queueResearch(UnitType.Mage); //25
		gc.queueResearch(UnitType.Mage); //75

		while (true) { // Every turn:

			// First we will add all new units:
			VecUnit units = gc.myUnits();
			for(int i = 0; i < units.size(); i++){
				Unit u = units.get(i); // Get the unit
				if (!seen.contains(u.id())){ // If we don't recognize it's id
					switch(u.unitType()){ // Add it to the respective collection
						case Factory: myFactories.add(new Factory(u, gc)); break;
						case Rocket:  myRockets.add(new Rocket(u, gc));    break;
						case Worker:  myWorkers.add(new Robot(u,gc));      break;
						default:      myWarriors.add(new Robot(u,gc));     break;
					}
					seen.add(u.id());
				}
			}

			// Ship rockets and request if necessary:
			for(Rocket r : myRockets){
				if(!r.requested){ // Don't request from factories - they calc on their own
					for(int i = 0; i < 4; i++)
						rrKnights.add(r.location()); // Add knight request
					for(int i = 0; i < 2; i++)
						rrRangers.add(r.location()); // Add ranger request
					rrMages.add(r.location()); // Add mage request
					rrWorkers.add(r.location()); // Add worker request
					r.requested = true;
				}
				r.launchIfFull(); // GOOOOOO!
			}

			// Let's figure out what each worker is doing!
			wtExplore = wtFactory =  wtRocket =  wtClone =  wtKarbonite =  wtRepair = 0; // Reset counts
			unassignedWorkers.clear(); // Reset unassigned workers
			for(Robot r : myWorkers){
				switch(r.target){
					case -1: unassignedWorkers.add(r); break;
					case 0: wtExplore++; break;
					case 1: wtFactory++; break;
					case 2: wtRocket++; break;
					case 3: wtClone++; break;
					case 4: wtKarbonite++; break;
					case 5: wtRepair++; break;
				}
			}

			// Now we gotta assign the new ones:
			for(Robot r : unassignedWorkers){
				if(myWorkers.size() + wtClone < 2){ // Keep 2 workers
					r.replicate();
					switch(r.target){
						case 0: wtExplore++; break;
						case 3: wtClone++; break;
					}

				} else if(gc.karbonite() > 100){ // Next, if round < 70 build factories
					r.buildFactory(); // Try to build a factory
					switch(r.target){ // We might not be able to build
						case 0: wtExplore++; break; // If this happens, we can't build a factory rn
						case 1: wtFactory++; break; // We can build a factory within sight range
					}

				/*} else if (rrWorkers.size() > 0){ // Send to rockets if any of them need it
					r.targetLoc = rrWorkers.remove();
					r.target = 2;
					wtRocket++;

				} else if (gc.karbonite() > 75 && rocketsBuilt == 0 && gc.researchInfo().getLevel(UnitType.Rocket) > 0) {
					// After we've researched rockets, our next priority is to build at least one.
					r.buildRocket(); // Select a location for our rocket
					if(r.target == 2)
						wtRocket++;
					else
						wtExplore++;*/
				} // Nothing worth doing: Just keep doing nothing
			}

			// Okay we're ready - lets let the robots work towards their goals!
			for(Robot r : myWorkers){
				r.performTasks();
			}

			//Let's manage the factories!:
			for(Factory f : myFactories){
				//Determine what should be made (Based on rocket requests)
				if(rrKnights.size() > fbKnight){
					f.build(UnitType.Knight);
					fbKnight += f.building();
				} else if (rrMages.size() > fbMage){
					f.build(UnitType.Mage);
					fbMage += f.building();
				} else if (rrHealers.size() > fbHealer){
					f.build(UnitType.Healer);
					fbHealer += f.building();
				} else if (rrRangers.size() > fbRanger){
					f.build(UnitType.Ranger);
					fbRanger += f.building();
				} else { //Just build some knights
					f.build(UnitType.Knight);
				}

				// Empty Garrison if possible
				if (f.canUnloadGarrison()) {
					UnitType built = f.unloadGarrison();
					switch(built){ // Reduce the factory building number (It's been built)
						case Worker: fbWorker--; break;
						case Knight: fbKnight--; break;
						case Ranger: fbRanger--; break;
						case Mage: fbMage--; break;
						case Healer: fbHealer--; break;
					}
				}

				System.out.println("Factory health: " + f.unit.health());
			}

			// TODO: Determine attacks for the remainder of the troops

			// TODO: Intelligent Queue :)

			// TODO: Send troops towards the rockets

			// TODO: Rockets load troops

			// Submit the actions we've done, and wait for our next turn.
			gc.nextTurn();
		}
	}
}
