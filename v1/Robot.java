import bc.*;
public class Robot{
    public Unit unit;
    public int target;
    public MapLocation targetLoc;

    private GameController gc;

    public Robot(Unit u, GameController game){
        target = -1;
        unit = u;
        gc = game;
    }

    public int hashCode(){
        return unit.id();
    }

    public boolean canReplicate(){
        return false; // Do Nothing for now
    }

    public void replicate(){
        // Do Nothing for now
    }
    public void buildFactory(){
        // Do Nothing for now
    }
    public MapLocation buildRocket(){
        return null;// Do Nothing for now
    }
    public void explore(){
        // Do Nothing for now
    }
    public void performTasks(){
        // Do Nothing for now
    }

    public void bug(Unit unit, GameController gc, MapLocation m){
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
            System.out.println("I'm stuck :(");
        }
    }
}
