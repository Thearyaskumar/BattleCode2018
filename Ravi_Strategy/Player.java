// import the API.
// See xxx for the javadocs.

import bc.*;
import java.util.Arrays;
import java.util.HashMap;

public class Player {

    //variables for strategy
    public static final int ATTACK = 10;
    public static final int DEFEND = 9;
    public static final int BUILD_WORKER = 8;
    public static final int BUILD_HEALER = 7;
    public static final int BUILD_KNIGHT = 6;
    public static final int BUILD_MAGE = 5;
    public static final int BUILD_RANGER = 4;
    public static final int BUILD_ROCKET = 3;
    public static final int BUILD_FACTORY = 2;
    public static final int HARVEST = 1;
    public static final int DEFAULT = 0;

    public static   boolean SLOW_PRODUCTION = false;

    public static boolean smallMap = false;
    public static int[] robotTasks = new int[11]; //Stores the number of robots that have been assigned each strategy
                                                 //value of strategy corresponds to index in array -> change len of array to # of constants
    public static HashMap<Integer,Integer> currentTasks;

    public static void main(String[] args) {
        // Connect to the manager, starting the game
        GameController gc = new GameController();

        onGameStart(gc);
        int strategy;
        currentTasks = new HashMap<Integer,Integer>();

        while (true) {
            try {
                Arrays.fill(robotTasks,0);

                // VecUnit is a class that you can think of as similar to ArrayList<Unit>, but immutable.
                VecUnit units = gc.myUnits();
                for (int i = 0; i < units.size(); i++) {
                    Unit unit = units.get(i);
                    if(unit.unitType()==UnitType.Rocket)
                        Rocket.oneMove(gc,unit,DEFAULT);
                }
                for (int i = 0; i < units.size(); i++) {
                    if(gc.getTimeLeftMs()<5) {
                        gc.nextTurn();
                        break;
                    }
                    Unit unit = units.get(i);

                    strategy = developStrategy(gc, unit); // That should call {earth, mars}Player.developStrategy()
                    robotTasks[strategy] += 1;
                    currentTasks.put(unit.id(),strategy);
                    
                    try {
                        switch (unit.unitType()){
                            case Factory: Factory.oneMove(gc, unit, strategy);
                                        break;
                            case Worker: Worker.oneMove(gc, unit, strategy);
                                        break;
                            case Ranger: Robot.oneMove(gc, unit, strategy);
                                        break;
                            case Mage: Robot.oneMove(gc, unit, strategy);
                                        break;
                            case Knight: Robot.oneMove(gc, unit, strategy);
                                        break;
                            case Healer: Robot.oneMove(gc, unit, strategy);
                                        break;
                        }
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
            // Submit the actions we've done, and wait for our next turn.
            gc.nextTurn();
        }
    }

    private static void onGameStart(GameController gc){
        
        // The brilliantly lazy research solution:
        // Hardcode 1000 turn's worth of research :)
        gc.queueResearch(UnitType.Mage); //25
        gc.queueResearch(UnitType.Mage); //75
        gc.queueResearch(UnitType.Rocket); //100
        gc.queueResearch(UnitType.Mage); //100
        gc.queueResearch(UnitType.Ranger); //25
        gc.queueResearch(UnitType.Rocket); //100
        gc.queueResearch(UnitType.Knight); //25
        gc.queueResearch(UnitType.Knight); //75
        gc.queueResearch(UnitType.Knight); //150
        gc.queueResearch(UnitType.Worker); //75
        gc.queueResearch(UnitType.Worker); //75
        gc.queueResearch(UnitType.Ranger); //100
        gc.queueResearch(UnitType.Worker); //75
    }

    private static int developStrategy(GameController gc, Unit unit){
        if(gc.round()>550 && gc.round()<1000)
            SLOW_PRODUCTION=true;
        else if(gc.round()>200 && gc.round()<250)
            SLOW_PRODUCTION=true;
        else
            SLOW_PRODUCTION=false;
        //System.out.println(gc.getTeamArray(Planet.Earth));
            switch (unit.unitType()){
                case Worker:
                    if(!currentTasks.containsKey(unit.id()) || currentTasks.get(unit.id())==DEFAULT || gc.round()%10==0) {
                        if(robotTasks[BUILD_FACTORY]<3) {
                            return BUILD_FACTORY;
                        } else {
                            //System.out.println(gc.researchInfo().getLevel(UnitType.Rocket));
                            if(gc.researchInfo().getLevel(UnitType.Rocket)>0) {
                                return Math.random()<0.25 ? HARVEST : BUILD_ROCKET;
                            } else
                                return HARVEST;
                        }
                    } else
                        return currentTasks.get(unit.id());
                
                case Factory:
                    VecUnit units = gc.myUnits();

                    if(robotTasks[BUILD_WORKER]<3) {
                        int workerCount = 0;
                        for(int i = 0; i < units.size(); i++) {
                            if(units.get(i).unitType() == UnitType.Worker)
                                workerCount++;
                        }
                        if(workerCount<=5)
                            return BUILD_WORKER;
                    }
                    switch((robotTasks[BUILD_RANGER]+robotTasks[BUILD_MAGE]+robotTasks[BUILD_KNIGHT]+robotTasks[BUILD_WORKER])%7) {
                        case 0:
                            return BUILD_RANGER;
                        case 1:
                            return BUILD_MAGE;
                        case 5:
                            return BUILD_MAGE;
                        case 2:
                            return BUILD_KNIGHT;
                        case 3:
                            return BUILD_WORKER;
                        case 4:
                            return BUILD_WORKER;
                        case 6:
                            return BUILD_RANGER;
                    }

                case Ranger:
                    if(!currentTasks.containsKey(unit.id()) || currentTasks.get(unit.id())==DEFAULT) {
                        // if(robotTasks[DEFEND]%2==1) {
                        //     return ATTACK;
                        // } else {
                        //     return DEFEND;
                        // }
                        return ATTACK;
                    } else
                        return currentTasks.get(unit.id());

                case Mage:
                    if(!currentTasks.containsKey(unit.id()) || currentTasks.get(unit.id())==DEFAULT) {
                        if(robotTasks[DEFEND]%2==1) {
                            return ATTACK;
                        } else {
                            return DEFEND;
                        }
                    } else
                        return currentTasks.get(unit.id());

                case Knight:
                    if(!currentTasks.containsKey(unit.id()) || currentTasks.get(unit.id())==DEFAULT) {
                        if(robotTasks[DEFEND]%2==1) {
                            return ATTACK;
                        } else {
                            return DEFEND;
                        }
                    } else
                        return currentTasks.get(unit.id());

                case Rocket:
                    return DEFAULT;
                    
                default:
                    return DEFAULT;
            }
    }   
}
