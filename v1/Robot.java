import bc.*;
public class Robot{
	public static void oneMove(GameController gc, Unit unit, int[] strategy){
        MapLocation m = unit.location().mapLocation()
        System.out.println("My Location: (" + m.getX() + ", " + m.getY() + ")");
    }
}