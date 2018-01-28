import bc.*;
import java.util.*;
public class Robot{
	/**
	*bug pathfinding that applies to all robots
	*/
	public Unit unit;
	public GameController gc;
	public void bug2(MapLocation targetMapLoc){

		//given target loc
		//next step towards that direction
		boolean above; //if target is above or below orthogonal search line
		Location myLoc = unit.location();
		if (!myLoc.isOnMap()){
			return; //basically, if it's not on a map, don't do anything
		}
		MapLocation myMapLoc = myLoc.mapLocation();
		int targetX = targetMapLoc.getX();
		int targetY = targetMapLoc.getY();
		int x = targetX - myMapLoc.getX();
		int y = targetY - myMapLoc.getY();
		/*
		int orthoLine = -xDisplacement/yDisplacement; //creates orthogonal search line based off of slope of displacement line
		wait, shouldn't it be 1.0*this? -Ruben
		*/

	}
	public void performTasks(){

	}
}
