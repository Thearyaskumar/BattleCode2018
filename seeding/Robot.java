import bc.*;
import java.util.*;
public class Robot{
	/**
	*bug pathfinding that applies to all robots
	*/
	public Unit unit;
	public GameController gc;
	public void bug(int targetX, int targetY){
		//given target loc
		//next step towards that direction
		boolean above; //if target is above or below orthogonal search line
		MapLocation myLoc = unit.MapLocation();
		int x = targetX - myLoc.getX();
		int y = targetY - myLoc.getY();
		/*
		int orthoLine = -xDisplacement/yDisplacement; //creates orthogonal search line based off of slope of displacement line
		*/

	}
	public void performTasks(){

	}
}
