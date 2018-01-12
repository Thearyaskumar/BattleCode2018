import bc.*;
public class Robot{
    public static void oneMove(GameController gc, Unit unit, int[] strategy){
        MapLocation m = new MapLocation(gc.planet(), strategy[0], strategy[1]);
	   bug(unit, gc, m); // Lazy pathfinding to other side
    }

    public static void bug(Unit unit, GameController gc, MapLocation m){
        // This will be a very rudimentary bathfinding algorithm to see if I can
        // get it functional at all. This will obv. have to be waaay improved
        // Find the correct direction:
        Direction d = unit.location().mapLocation().directionTo(m);
        if (gc.isMoveReady(unit.id())){
            for(int i = 0; i < 8; i++)
            	if (!gc.canMove(unit.id(), d))
                    System.out.println("Can't Move!");
            gc.moveRobot(unit.id(), d);
        }
    }
}
