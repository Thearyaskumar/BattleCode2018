import bc.*;
import java.util.*;
public class Player{
  	public static HashSet<MapLocation> enemyLocs = new HashSet<MapLocation>();
  
  public static HashSet<MapLocation> getEnemyLocs() {
    return enemyLocs;
  }
  public static boolean isInEnemyLocs(MapLocation m) {
    return enemyLocs.contains(m);
  }
  public static void addEnemyLoc(MapLocation m) {
    enemyLocs.add(m);
  }
	public static void main(String[] args){
        GameController gc = new GameController();
		//set up variables we'll use later
      	//boolean goToMars = false;
      	//int[] prod = new int[5];
      	OrbitPattern orbits = gc.orbitPattern();
		
        // Setup hashsets for our units
        HashSet<Worker> myWorkers = new HashSet<Worker>();
        HashSet<Knight> myKnights = new HashSet<Knight>();
        HashSet<Mage> myMages = new HashSet<Mage>();
        HashSet<Ranger> myRangers = new HashSet<Ranger>();
        HashSet<Healer> myHealers = new HashSet<Healer>();
        HashSet<Factory> myFactories = new HashSet<Factory>();
        HashSet<Rocket> myRockets = new HashSet<Rocket>();

        //UnitType[] attackPriority = [UnitType.Factory,UnitType.Rocket,UnitType.Mage,UnitType.Worker,UnitType.Ranger,UnitType.Knight,UnitType.Healer];

        /*public int calcHab(){
        	return -1
        }
        public int evalMobility(PlanetMap pm){
                return -1;
        }*/
      	public void determineResearch(boolean EarthIsLarge){
          if (EarthIsLarge){
              gc.queueResearch(UnitType.Worker); //25
              gc.queueResearch(UnitType.Mage);//25
              gc.queueResearch(UnitType.Ranger); //25
              gc.queueResearch(UnitType.Rocket); //50
              gc.queueResearch(UnitType.Mage);//75
              gc.queueResearch(UnitType.Worker); //75
              gc.queueResearch(UnitType.Worker); //75
              gc.queueResearch(UnitType.Worker); //75
              gc.queueResearch(UnitType.Rocket); //100
              gc.queueResearch(UnitType.Rocket); //100
              gc.queueResearch(UnitType.Mage); //100
              gc.queueResearch(UnitType.Healer); //25
              gc.queueResearch(UnitType.Ranger); //100
              gc.queueResearch(UnitType.Healer); //100
              gc.queueResearch(UnitType.Knight); //25
              gc.QueueResearch(UnitType.Knight); //75
          }else{
              gc.queueResearch(UnitType.Mage); //25
              gc.queueResearch(UnitType.Mage); //75
              gc.queueResearch(UnitType.Ranger);//25
              gc.queueResearch(UnitType.Worker); //25
              gc.queueResearch(UnitType.Mage); //100
              gc.queueResearch(UnitType.Healer); //25
              gc.queueResearch(UnitType.Healer); //100
              gc.queueResearch(UnitType.Knight); //25
              gc.queueResearch(UnitType.Rocket); //50
              gc.queueResearch(UnitType.Rocket); //100
              gc.queueResearch(UnitType.Ranger); //100
              gc.queueResearch(UnitType.Knight); //75
              gc.queueResearch(UnitType.Knight); //100
              gc.queueResearch(UnitType.Mage); //75
              gc.queueResearch(UnitType.Healer) //100
          }
        }
        //Get info about Earth and Mars
        EarthMap = gc.startingMap(Planet.Earth);
        MarsMap = gc.startingMap(Planet.Mars);
        EarthSize = EarthMap.getHeight()*EarthMap.getWidth();
        MarsSize = MarsMap.getHeight()*MarsMap.getWidth();
        //EarthMobility = evalMobility(EarthMap);

        boolean mapLarge = EarthSize > 750 ;
        //boolean haveSuicideKnighted = false;

        //queue all research (hardcoded)
        gc.determineResearch(mapLarge);
        while(1){
          	enemyLocs.clear();
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
            // 3: launched
          
          	myFactories.clear();
          	myRockets.clear();
            myWorkers.clear();
            myMages.clear();
          	myRangers.clear();
          	myKnights.clear();
          	myHealers.clear();	
            
            VecUnit units = gc.myUnits();
            for(int i = 0; i < units.size(); i++){
                Unit u = units.get(i); // Get the unit
                switch(u.unitType()){ // Add it to the respective collection
                    case Factory: myFactories.add(new Factory(u, gc)); break;
                    case Rocket:  myRockets.add(new Rocket(u, gc));    break;
                    case Worker:  myWorkers.add(new Worker(u,gc));     break;
                    case Mage:    myMages.add(new Mage(u,gc));         break;
                    case Ranger:  myRangers.add(new Ranger(u,gc));     break;
                    case Knight:  myKnights.add(new Knight(u,gc));     break;
                    case Healer:  myHealers.add(new Healer(u,gc));     break;
                }
            }

            gc.writeTeamArray(0, gc.round()); // updateTeamArray (to indicate alive)

            //Big mess from here all the way until.......
            /*if(mapLarge){
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
            }*/
            //......here. :(
            // DO THE REST OF THE STUFF HERE
            // Run rocket code
            for(Rocket r : myRockets){
              	switch(r.getTarget()){ //-1-unbuilt, 0-unfull,1-full waiting, 2-launching
              		case -1: if(r.fullHealth())
              					r.setTarget(0); //if it's now built, time to work.
              				 break;
                  	case 0:  //sense all units nearby this rocket
                  		     //set their bug target to this rocket
                            for(Direction d: Direction.values()){
                                try{
                                    if(gc.hasUnitAtLocation(r.location().mapLocation().add(d))) {
                                        Unit u = gc.senseUnitAtLocation(r.location().mapLocation().add(d));
                                        //System.out.println("loaded unit onto rocket");
                                        gc.load(r.id(), u.id());
                                    }
                                  	if(r.fullGar())
                                      break;
                                }
                                catch(Exception e){
                                    //e.printStackTrace();
                                }
                            }
                  			 
                  			 if (r.fullGar() || r.health()<40)
                  				r.setTarget(1); //if it's now full, time to idle
                  			 //In Robot code, if type is what r wants, bug toward r's location and load.
                  			 break;
                  	case 1:  for (int i = 0; i < 5; i++){ //don't do this more than 10 times per turn to avoid timeOut
                  				if (gc.canLaunchRocket(r.id(),r.getLandingLoc()))
                  					break;
                  				else
                  					r.findLandingLoc();
                  			 if (gc.canLaunchRocket(r.id(),r.getLandingLoc()) && (r.health()<40||orbit.duration()<orbit.getCenter()||gc.round()>600))
                  			 	r.setTarget(2)
                  			 break;
                  	}
                  	case 2:  gc.launchRocket(r.id(), r.getLandingLoc()); 
                  			 r.setTarget(3);
                             break; //no reason to test if I can... done in case 1
                  	case 3:  if (r.emptyGar())
                      			gc.disintegrateUnit(r.id());
                  				myRockets.remove(r);
                  			else{
                              if(r.location().isOnPlanet(Planet.Mars)) {
                                for(Direction d : Direction.values()) {
                                    if(gc.canUnload(r.id(),d))
                                        gc.unload(r.id(),d);
                                }
                              }
                            }
                            break;
                }
			}
			if (gc.getTimeLeftMs()<5)
              gc.nextTurn();
            for(Factory f : myFactories){
              f.oneRound();
            }
			if (gc.getTimeLeftMs()<5)
              gc.nextTurn();
            for(Worker w : myWorkers){
              w.oneRound();
            }
            if (gc.getTimeLeftMs()<5)
              gc.nextTurn();
			for(Ranger r : myRangers){
              r.oneRound();
			}
            if (gc.getTimeLeftMs()<5)
              gc.nextTurn();
            for(Mage m : myMages){
              m.oneRound();
            }
            if (gc.getTimeLeftMs()<5)
              gc.nextTurn();
            for(Healer h : myHealers){
              h.oneRound();
            }
            if (gc.getTimeLeftMs()<5)
              gc.nextTurn();
            for(Knight k : myKnights){
              k.oneRound();
            }
            gc.nextTurn();
        }
    }

  	/*void rocketIfPossible();
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
    void determineResearch();*/
}