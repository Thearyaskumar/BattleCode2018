// import the API.
// See xxx for the javadocs.
import bc.*;
import java.util.HashSet;

public class Player {
	public static void main(String[] args) {
		// Connect to the manager, starting the game
		GameController gc = new GameController();

		// Setup hashsets for our units
		HashSet<Robot> myWorkers = new HashSet<Robot>();
		HashSet<Robot> myWarriors = new HashSet<Robot>();
		HashSet<Factory> myFactories = new HashSet<Factory>();
		HashSet<Rocket> myRockets = new HashSet<Rocket>();

		// Setup worker task counts (Worker Task Factory, etc):
		int wtExplore, wtFactory, wtRocket, wtClone, wtKarbonite, wtRepair;
		HashSet<Robot> unassignedWorkers = new HashSet<Robot>();
		MapLocation rocketLocation = null; // To build the first rocket after the tech is researched

		// Factory tasks counts (Factory Request Knight, etc):
		int frHealer, frKnight, frMage, frRanger, frWorker;
		frHealer = frKnight = frMage = frRanger = frWorker = 0;

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
				Unit u = units.get(i); // Get all the units

				if (u.unitType() == UnitType.Factory){ // Factory
					Factory f = new Factory(u, gc);
					if(!myFactories.contains(f))
						myFactories.add(f);

				} else if (u.unitType() == UnitType.Rocket){ // Rockets
					Rocket r = new Rocket(u, gc);
					if(!myRockets.contains(u))
						myRockets.add(r);

				} else if (u.unitType() == UnitType.Worker){ // Workers
					Robot r = new Robot(u, gc);
					if(!myWorkers.contains(u))
						myWorkers.add(r);

				} else { // Warriors
					Robot r = new Robot(u, gc);
					if(!myWarriors.contains(u))
						myWarriors.add(r);
				}
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
			// TODO: Change this from a bunch of if statements
			for(Robot r : unassignedWorkers){
				if(r.canReplicate()){ // First, try to clone
					r.replicate();

				} else if(gc.round() < 70){
					r.buildFactory(); // Try to build a factory
					switch(r.target){ // We might not be able to build
						case 0: wtExplore++; break; // If this happens, we can't build a factory rn
						case 1: wtFactory++; break; // We can build a factory within sight range
					}

				} else if (rocketLocation == null && gc.researchInfo().getLevel(UnitType.Rocket) > 0) {
					// After we've researched rockets, our next priority is to build at least one.
					rocketLocation = r.buildRocket(); // Select a location for our rocket
					wtRocket++;

				} else if (rocketLocation != null && wtRocket <= 2){ //Only one worker on the rocket rn
					r.target = 2;
					r.targetLoc = rocketLocation;
					wtRocket++;

				} else if (wtFactory < wtExplore){
					r.buildFactory();
					switch(r.target){ // We might not be able to build
						case 0: wtExplore++; break; // If this happens, we can't build a factory rn
						case 1: wtFactory++; break; // We can build a factory within sight range
					}

				} else {
					r.explore(); // Nothing better to do - just explore
				}
			}

			// Okay we're ready - lets let the robots work towards their goals!
			for(Robot r : unassignedWorkers){
				r.performTasks();
			}

			//Let's manage the factories!:
			for(Factory f : myFactories){
				//Determine what should be made (If something can be made)
				if(f.canBuild()){
					if (frHealer > 0){
        			    f.build(UnitType.Healer);
        			    frHealer--;
        			} else if (frMage > 0){
        			    f.build(UnitType.Mage);
        			    frMage--;
        			} else if (frRanger > 0){
        			    f.build(UnitType.Ranger);
        			    frRanger--;
        			} else if (frWorker > 0){
        			    f.build(UnitType.Worker);
        			    frWorker--;
        			} else {
        			    f.build(UnitType.Knight);
        			    frKnight = frKnight > 0 ? frKnight - 1 : 0; // It's the default, so only reduce if it was requested
        			}
				}
				// Empty Garrison if possible
				f.unloadGarrison();
			}

			// TODO: Determine attacks for the remainder of the troops

			// TODO: Intelligent Queue :)

			// Submit the actions we've done, and wait for our next turn.
			gc.nextTurn();
		}
	}
}
