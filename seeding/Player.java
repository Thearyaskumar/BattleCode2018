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
      	OrbitPattern orbit = gc.orbitPattern();
		
        // Setup hashsets for our units
        HashSet<Worker> myWorkers = new HashSet<Worker>();
        HashSet<Knight> myKnights = new HashSet<Knight>();
        HashSet<Mage> myMages = new HashSet<Mage>();
        HashSet<Ranger> myRangers = new HashSet<Ranger>();
        HashSet<Healer> myHealers = new HashSet<Healer>();
        HashSet<Factory> myFactories = new HashSet<Factory>();
        HashSet<Rocket> myRockets = new HashSet<Rocket>();
        HashSet<Integer> seen = new HashSet<Integer>();

        //UnitType[] attackPriority = [UnitType.Factory,UnitType.Rocket,UnitType.Mage,UnitType.Worker,UnitType.Ranger,UnitType.Knight,UnitType.Healer];

        /*public int calcHab(){
        	return -1
        }
        public int evalMobility(PlanetMap pm){
                return -1;
        }*/
      
        //Get info about Earth and Mars
        PlanetMap EarthMap = gc.startingMap(Planet.Earth);
        PlanetMap MarsMap = gc.startingMap(Planet.Mars);
        long EarthSize = EarthMap.getHeight()*EarthMap.getWidth();
        long MarsSize = MarsMap.getHeight()*MarsMap.getWidth();
        //EarthMobility = evalMobility(EarthMap);

        boolean mapLarge = EarthSize > 750 ;
        //boolean haveSuicideKnighted = false;

        //queue all research (hardcoded)
        if (mapLarge){
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
          gc.queueResearch(UnitType.Knight); //75
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
          gc.queueResearch(UnitType.Healer); //100
      }
        while(true){
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
          
            //WORKER:
          	// 0: default
          	// 1: build a factory
          
          	// myFactories.clear();
          	// myRockets.clear();
            // myWorkers.clear();
            // myMages.clear();
          	// myRangers.clear();
          	// myKnights.clear();
          	// myHealers.clear();	
            
            VecUnit units = gc.myUnits();
            HashSet<Integer> thisRound = new HashSet<Integer>();
            for(int i = 0; i < units.size(); i++){
                Unit u = units.get(i); // Get the unit
                thisRound.add(u.id());
                if(!seen.contains(u.id())) {
                    seen.add(u.id());
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
            }

            for(Rocket r:myRockets) {
                if(!thisRound.contains(r.id())) {
                    myRockets.remove(r);
                    seen.remove(r.id());
                }
            }
            for(Factory r:myFactories) {
                if(!thisRound.contains(r.id())) {
                    myFactories.remove(r);
                    seen.remove(r.id());
                }
            }
            for(Worker r:myWorkers) {
                if(!thisRound.contains(r.id())) {
                    myWorkers.remove(r);
                    seen.remove(r.id());
                }
            }
            for(Healer r:myHealers) {
                if(!thisRound.contains(r.id())) {
                    myHealers.remove(r);
                    seen.remove(r.id());
                }
            }
            for(Knight r:myKnights) {
                if(!thisRound.contains(r.id())) {
                    myKnights.remove(r);
                    seen.remove(r.id());
                }
            }
            for(Ranger r:myRangers) {
                if(!thisRound.contains(r.id())) {
                    myRangers.remove(r);
                    seen.remove(r.id());
                }
            }
            for(Mage r:myMages) {
                if(!thisRound.contains(r.id())) {
                    myMages.remove(r);
                    seen.remove(r.id());
                }
            }

            //gc.writeTeamArray(0, gc.round()); // updateTeamArray (to indicate alive)

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
                                    if(gc.hasUnitAtLocation(r.getLoc().mapLocation().add(d))) {
                                        Unit u = gc.senseUnitAtLocation(r.getLoc().mapLocation().add(d));
                                        //System.out.println("loaded unit onto rocket");
                                        if((u.unitType()!=UnitType.Worker || gc.round()>700)&&gc.canLoad(r.id(), u.id()))
                                            try{gc.load(r.id(), u.id());}catch(Exception e){}
                                    }
                                  	if(r.fullGar())
                                      break;
                                }
                                catch(Exception e){
                                    //e.printStackTrace();
                                }
                            }
                  			 
                  			 if (r.fullGar() || r.health()<40 || gc.round()>745)
                               try{r.setTarget(1);}catch(Exception e){} //if it's now full, time to idle
                  			 //In Robot code, if type is what r wants, bug toward r's location and load.
                  			 break;
                  	case 1:  for (int i = 0; i < 5; i++){ //don't do this more than 5 times per turn to avoid timeOut
                  				if (gc.canLaunchRocket(r.id(),r.getLandingLoc()))
                  					break;
                  				else
                                  try{r.findLandingLoc();}catch(Exception e){}
                  			 if (gc.canLaunchRocket(r.id(),r.getLandingLoc()) && (r.health()<40||orbit.duration(gc.round())<orbit.getCenter()||gc.round()>600))
                               try{r.setTarget(2);}catch(Exception e){}
                  			 break;
                  	}
                  	case 2:  gc.launchRocket(r.id(), r.getLandingLoc()); 
                  try{r.setTarget(3);}catch(Exception e){}
                             break; //no reason to test if I can... done in case 1
                  	case 3:  if (r.emptyGar()){
                      try{gc.disintegrateUnit(r.id());}catch(Exception e){}
                  	
                  try{myRockets.remove(r);}catch(Exception e){}
              }
                  			else{ 
                              if(r.getLoc().isOnPlanet(Planet.Mars)) {
                                for(Direction d : Direction.values()) {
                                    if(gc.canUnload(r.id(),d))
                                      try{gc.unload(r.id(),d);}catch(Exception e){}
                                }
                              }
                            }
                            break;
                }
			}
			if (gc.getTimeLeftMs()<5)
              try{gc.nextTurn();}catch(Exception e){}
            for(Factory f : myFactories){
              try{f.oneMove();}catch(Exception e){}
            }
			if (gc.getTimeLeftMs()<5)
              try{gc.nextTurn();}catch(Exception e){}
            for(Worker w : myWorkers){
              int task = 0;
              if(gc.round()%10 == 0){
              ////////////TODO: IMPLEMENT TASK (see guide above)
                if(myFactories.size() < 3 && w.getLoc().isOnPlanet(Planet.Earth)){
                  task = 1;
                }
                try{w.oneRound(task);}catch(Exception e){
              }
            }
          }
            if (gc.getTimeLeftMs()<5)
              try{gc.nextTurn();}catch(Exception e){}
			for(Ranger r : myRangers){
              try{r.oneRound();}catch(Exception e){}
			}
            if (gc.getTimeLeftMs()<5)
              try{gc.nextTurn();}catch(Exception e){}
            for(Mage m : myMages){
              try{m.oneRound();}catch(Exception e){}
            }
            if (gc.getTimeLeftMs()<5)
              try{gc.nextTurn();}catch(Exception e){}
            for(Healer h : myHealers){
              try{h.oneRound();}catch(Exception e){}
            }
            if (gc.getTimeLeftMs()<5)
              try{gc.nextTurn();}catch(Exception e){}
            for(Knight k : myKnights){
              try{k.oneRound();}catch(Exception e){}
            }

            gc.nextTurn();

        }

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
 //REACHED END PARSING