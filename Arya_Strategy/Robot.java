import bc.*;
/*
LIST OF TARGETS:
-1 : Unassigned
 0 : Explore
 1 : Factory
 2 : Rocket
 3 : Clone
 4 : Karbonite
 5 : Repair (An already built structure)
*/
public class Robot{
    public Unit unit;
    public int target;
    public MapLocation targetLoc;
    public Direction targetDir;
    public Unit targetStruct;

    private GameController gc;

    public Robot(Unit u, GameController game){
        target = -1;
        unit = u;
        gc = game;
    }

    public void replicate(){
        /* Find a *safe* location to replicate if there is one */
        for(Direction d : Direction.values())
            if(gc.canReplicate(unit.id(), d)){
                targetDir = d;
                target = 3; // Clone
                return;
            }
        target = 0; // Just explore for a clone location
    }

    public void buildFactory(){
        /* Find a good location to build a factory */
        for(Direction d : Direction.values()){
            if(gc.canBlueprint(unit.id(), UnitType.Factory, d)){
                gc.blueprint(unit.id(), UnitType.Factory, d);
                targetStruct = gc.senseUnitAtLocation(dirToLoc(unit.location().mapLocation(), d));
                target = 1; // Factory
                return;
            }
        }
        target = 0; // Just explore for a factory location
    }

    public void buildRocket(){
        /* Find a good location to build a rocket */
        for(Direction d : Direction.values())
            if(gc.canBlueprint(unit.id(), UnitType.Rocket, d)){
                gc.blueprint(unit.id(), UnitType.Rocket, d);
                targetStruct = gc.senseUnitAtLocation(dirToLoc(unit.location().mapLocation(), d));
                target = 2; // Rocket
                return;
            }
        target = 0; // Just explore for a factory location
    }

    public void explore(){
        /* Just generally pick an unexplored / unviewed point and move towards it */
        System.out.println("Trying to explore!");
    }

    public void performTasks(){
        /* Actually work towards your goals! */
        System.out.println("Hi, im working towards: " + target);
        switch(target){
            case 0: break; // Explore

            case 1: // Building factories and rockets are identical:
            case 2: if(gc.canBuild(unit.id(), targetStruct.id())) // Rocket
                        gc.build(unit.id(), targetStruct.id());
                    if(targetStruct.health() == targetStruct.maxHealth())
                        target = -1;
                    break;

            case 3: gc.replicate(unit.id(), targetDir); // Clone
                    target = -1;
                    break;

            case 4: break; // Karbonite

            case 5: break; // Repair

        }
    }

    public void bug(MapLocation m){
        // STATIC, MUST BE UPDATED
        // This will be a very rudimentary pathfinding algorithm to see if I can
        // get it functional at all. This will obv. have to be waaay improved
        // Find the correct direction:
        Direction d = unit.location().mapLocation().directionTo(m);
        if (gc.isMoveReady(unit.id())){
            for(int i = 0; i < 8; i++)
            	if (gc.canMove(unit.id(),d)){
                    gc.moveRobot(unit.id(), d);
                    return;
                } else {
                    switch(d){ // Rotate clockwise
                        case North: d = Direction.Northeast; break;
                        case Northeast: d = Direction.East; break;
                        case East: d = Direction.Southeast; break;
                        case Southeast: d = Direction.South; break;
                        case South: d = Direction.Southwest; break;
                        case Southwest: d = Direction.West; break;
                        case West: d = Direction.Northwest; break;
                        case Northwest: d = Direction.North; break;
                    }
                }
        }
    }

    private MapLocation dirToLoc(MapLocation m, Direction d){
        switch(d){
            case North:     return new MapLocation(m.getPlanet(), m.getX()  , m.getY()+1);
            case Northeast: return new MapLocation(m.getPlanet(), m.getX()+1, m.getY()+1);
            case East:      return new MapLocation(m.getPlanet(), m.getX()+1, m.getY()  );
            case Southeast: return new MapLocation(m.getPlanet(), m.getX()+1, m.getY()-1);
            case South:     return new MapLocation(m.getPlanet(), m.getX()  , m.getY()-1);
            case Southwest: return new MapLocation(m.getPlanet(), m.getX()-1, m.getY()-1);
            case West:      return new MapLocation(m.getPlanet(), m.getX()-1, m.getY()  );
            case Northwest: return new MapLocation(m.getPlanet(), m.getX()-1, m.getY()+1);
            default:        return new MapLocation(m.getPlanet(), m.getX()  , m.getY()  );
        }
    }
}
