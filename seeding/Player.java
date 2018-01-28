import bc.*;
import java.util.*;
public class Player{
	public static void main(String[] args){
        GameController gc = new GameController();
		//set up variables we'll use later
      	boolean goToMars = false;
      	int[] prod = new int[5];
      	orbits = gc.orbitPattern();

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

        public int calcHab(){
        	return -1
        }
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
          	// 1: full garrison - idling for good launch time
          	// 2: Launching
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

            //Big mess from here all the way until.......
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
            //......here. :(
            // DO THE REST OF THE STUFF HERE
            // Run rocket code
            for(Rocket r : myRockets){
              	if (!r.fullHealth())
              		r.setTarget(-1); //in case it got damaged. Maybe have this as -2? -Ruben
              	switch(r.getTarget()){ //-1-unbuilt, 0-unfull,1-full waiting, 2-launching
              		case -1: if(r.fullHealth())
              					r.setTarget(0); //if it's now built, time to work.
              				 break;
                  	case 0:  //sense all units nearby this rocket
                  		     //set their bug target to this rocket
                  			 if (r.fullGar())
                  				r.setTarget(1); //if it's now full, time to idle
                  			 //In Robot code, if type is what r wants, bug toward r's location and load.
                  			 break;
                  	case 1:  for (int i = 0; i < 5; i++){ //don't do this more than 10 times per turn to avoid timeOut
                  				if (gc.canLaunchRocket(r.id(),r.getLandingLoc()))
                  					break;
                  				else
                  					r.findLandingLoc();
                  			 if (gc.canLaunchRocket(r.id(),r.getLandingLoc()) && orbit.duration()<orbit.getCenter() || gc.round()>600)
                  			 	r.setTarget(2)
                  			 break;
                  	}
                  	case 2:  gc.launchRocket(r.id(), r.getLandingLoc()); break; //no reason to test if I can... done in case 1
                }
			}

            for(Factory f : myFactories){
              if(f.target == 1)
                f.buildOrPlace();
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
