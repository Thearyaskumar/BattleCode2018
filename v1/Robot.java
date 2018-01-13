import bc.*;
public class Robot extends Unit {
    public int target;
    public MapLocation targetLoc;
    public Robot(){
        target = -1;
        super();
    }

    public int hashCode(){
        return this.id();
    }

    public void bug(Unit unit, GameController gc, MapLocation m){
        // This will be a very rudimentary bathfinding algorithm to see if I can
        // get it functional at all. This will obv. have to be waaay improved
        // Find the correct direction:
        Direction d = unit.location().mapLocation().directionTo(m);
        if (gc.isMoveReady(unit.id())){
            for(int i = 0; i < 8; i++)
            	if (gc.canMove(unit.id(),d)){
                    gc.moveRobot(unit.id(), d);
                    return;
                }else{
                    switch(d){
                        case Direction.North: d = Direction.Northeast;
                            break;
                        case Direction.Northeast: d = Direction.East;
                            break;
                        case Direction.East: d = Direction.Southeast;
                            break;
                        case Direction.Southeast: d = Direction.South;
                            break;
                        case Direction.South: d = Direction.Southwest;
                            break;
                        case Direction.Southwest: d = Direction.West;
                            break;
                        case Direction.West: d = Direction.Northwest;
                            break;
                        case Direction.Northwest: d = Direction.North;
                            break;
                    }
                }
            System.out.println("I'm stuck :(");
        }
    }
}
