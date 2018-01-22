import bc.*;
import java.util.*;
public class Player{
	public static void main(String[] args){
        GameController gc = new GameController();
		//set up variables we'll use later
      	boolean goToMars = false;
      	int[] prod = new int[5];

        // Setup hashsets for our units
        HashSet<Worker> myWorkers = new HashSet<Worker>();
        HashSet<Knight> myKnights = new HashSet<Knight>();
        HashSet<Mage> myMages = new HashSet<Mage>();
        HashSet<Ranger> myRangers = new HashSet<Ranger>();
        HashSet<Healer> myHealers = new HashSet<Healer>();
        HashSet<Factory> myFactories = new HashSet<Factory>();
        HashSet<Rocket> myRockets = new HashSet<Rocket>();

        UnitType[] attackPriority = [UnitType.Factory,UnitType.Rocket,UnitType.Mage,UnitType.Worker,UnitType.Ranger,UnitType.Knight,UnitType.Healer];

        // Hashsets for the robots we've already seen
        HashSet<Integer> seen = new HashSet<Integer>();

      public int calcHab(
      public int evalMobility(PlanetMap pm){
        	return -1;
        }
        //Get info about Earth and Mars
        EarthMap = gc.startingMap(Planet.Earth);
        MarsMap = gc.startingMap(Planet.Mars);
        EarthSize = EarthMap.getHeight()*EarthMap.getWidth();
        MarsSize = MarsMap.getHeight()*MarsMap.getWidth();
        EarthMobility = evalMobility(EarthMap);

        boolean mapLarge = EarthSize > 750 ;
        boolean haveSuicideKnighted = false;

        while(1){
            // First we will add all new units:

          	// INTEGER: robot.target if
          	// -1: unassigned
            // 0: Random Exploration
          	// 1: Targeted Moving
          	// 2: Attacking
          	// 3: Retreating
            // <...> Specialized

          	// FACTORY: robot.target if
          	// -1: unbuilt
          	// 0: idle
          	// 1: Building - see factory queue

          	// Factory.getQueue():
          	// ArrayList of UnitTypes

          	// ROCKETS: robot.target if
          	// -1: unbuilt
          	// 0: unfull garrison
          	// 1: full garrison - not yet launched
          	// 2: idling for good launch time
          	// 3: Launching
            VecUnit units = gc.myUnits();
            for(int i = 0; i < units.size(); i++){
                Unit u = units.get(i); // Get the unit
                if (!seen.contains(u.id())){ // If we don't recognize it's id
                    switch(u.unitType()){ // Add it to the respective collection
                        case Factory: myFactories.add(new Factory(u, gc)); break;
                        case Rocket:  myRockets.add(new Rocket(u, gc));    break;
                        case Worker:  myWorkers.add(new Worker(u,gc));     break;
                        case Mage:    myMages.add(new Mage(u,gc));         break;
                        case Ranger:  myRangers.add(new Ranger(u,gc));     break;
                        case Knight:  myKnights.add(new Knight(u,gc));     break;
                        case Healer:  myHealers.add(new Healer(u,gc));     break;
                    }
                    seen.add(u.id()); // We've seen them now
                }
            }

            gc.writeTeamArray(0, gc.round()); // updateTeamArray (to indicate alive)

            if(mapLarge){
                if(!haveSuicideKnighted){
                  	int pos=0;
                  	UnitType[] unitsToBuild = [UnitType.Worker,UnitType.Mage,UnitType.Ranger,UnitType.Knight];
                    for(Factory f:myFactories) {
                    	if(f.target==0)
                          	pos = pos%len(unitsToBuild);
                     		f.queue(UnitType.Ranger);
                    }
                }
                rocketIfPossible();
                hab = calcHab();
              	if (!hab){ //or if hab is less than some number
                    goToMars = true;
              	} else if (winning)

                production = [UnitType.Worker, UnitType.Mage, UnitType.Ranger, UnitType.Knight, UnitType.Healer];
                gc.queueReseach(UnitType.Rocket);
            } else {
                // AGRO
                mob = evalMobility();
                if (mob){
              		gc.queueResearch(UnitType.Knight);
                }else{
                  	gc.queueResearch(UnitType.Mage);

                int mobility = evalMobility();
                  if (mobility==1) {
                    for(Factory f:myFactories) {
                    	if(f.target==0)
                     		f.queue(UnitType.Knight);
                    }
                  } else if(mobility==0) {
                    boolean needRockets = 5;
                    if(myMages.size()==0)
                      	needRockets = 0;
                    else if(myRockets.size()>0)
                    	needRockets = myMages.size()/myRockets.size();
                    for(Factory f : myFactories) {
                    	if(f.target==0)
                          	if(needRockets>0)
                     			f.queue(UnitType.Rocket);
                          	else
                          		f.queue(UnitType.Mage);
                    }
                  }
            }

            // DO THE REST OF THE STUFF HERE
            // Run rocket code
            for(Rocket r : myRockets){
              	switch(r.target){
                  	case 0: r.tryLoadGarrison(); break; // Unfull garrison
                  	case 1: findRocketLandLoc(r); break; // Full garrison
                  	case 2: r.tryLaunch(); break; // Idling for launch
                }
			}

            for(Factory f : myFactories){
              if(f.target == 1)
                g.buildOrPlace();
            }

            for(Worker w : myWorkers){
              if(w.target == -1)
                w.target = 0;
              w.oneRound();
            }

            for(Healer h : myHealers){
              h.oneRound();
            }
            for(Knight k : myKnights){
              k.oneRound();
            }
            for(Mage m : myMages){
              m.oneRound();
            }
            for(Ranger r : myRangers){
              r.oneRound();
            }
        }
    }

  	void rocketIfPossible();
    int calcHab(){

    }
    int evalMobility() { //return 1 for high, 0 for low
      // Get the starting locations:
      MapLocation startLoc = gc.startingMap(Planet.Earth).getInitial_units().get(0).location().mapLocation();
      // Floodfull with a depth of 6:
      Queue<MapLocation> q = new LinkedList<MapLocation>();

    }
    void buildFactories();
    void agro();
    void determineResearch();
}
