import bc.*;
import java.util.*;
public class Robot{
	/**
	*bug pathfinding that applies to all robots
	*/
	public Unit unit;
	public GameController gc;
	public void randomBug(){
		//Ravi's random bug code
	}

	public void bug2(MapLocation targetMapLoc){
		//given target loc
		//next step towards that direction
		Location myLoc = unit.location();
		if (!myLoc.isOnMap()){
			return; //basically, if it's not on a map, don't do anything
		}
		MapLocation myMapLoc = myLoc.mapLocation();
		int targetX = targetMapLoc.getX();
		int targetY = targetMapLoc.getY();
		int dx = targetX - myMapLoc.getX();
		int dy = targetY - myMapLoc.getY();
		if(dx > 0 && dy > 0){
			if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Northeast)) > 0){
				return Direction.Northeast;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.North)) > 0){
				return Direction.North;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.East)) > 0){
				return Direction.East;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Northwest)) > 0){
				return Direction.Northwest;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Southeast)) > 0){
				return Direction.Southeast;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.West)) > 0){
				return Direction.West;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.South)) > 0){
				return Direction.South;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Southwest)) > 0){
				return Direction.Southwest;
			}
			else{
				return;
			}
		}
		else if(dx > 0 && dy< 0){
			if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Southeast)) > 0){
				return Direction.Southeast;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.South)) > 0){
				return Direction.South;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.East)) > 0){
				return Direction.East;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Northeast)) > 0){
				return Direction.Northeast;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Southwest)) > 0){
				return Direction.Southwest;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.North)) > 0){
				return Direction.North;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.West)) > 0){
				return Direction.West;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Northwest)) > 0){
				return Direction.Northwest;
			}
			else{
				return;
			}
		else if(dx < 0 && dy < 0){
			if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Southwest)) > 0){
				return Direction.Southwest;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.South)) > 0){
				return Direction.South;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.West)) > 0){
				return Direction.West;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Southeast)) > 0){
				return Direction.Southeast;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Northwest)) > 0){
				return Direction.Northwest;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.East)) > 0){
				return Direction.East;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.North)) > 0){
				return Direction.North;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Northeast)) > 0){
				return Direction.Northeast;
			}
			else{
				return;
			}
		}
		else if(dx < 0 && dy > 0){
			if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Northwest)) > 0){
				return Direction.Northwest;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.North)) > 0){
				return Direction.North;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.West)) > 0){
				return Direction.West;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Northeast)) > 0){
				return Direction.Northeast;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Southwest)) > 0){
				return Direction.Southwest;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.East)) > 0){
				return Direction.East;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.South)) > 0){
				return Direction.South;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Southeast)) > 0){
				return Direction.Southeast;
			}
			else{
				return;
			}
		}
		else if(dx > 0){
			if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.East)) > 0){
				return Direction.East;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Northeast)) > 0){
				return Direction.Northeast;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Southeast)) > 0){
				return Direction.Southeast;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.North)) > 0){
				return Direction.North;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.South)) > 0){
				return Direction.South;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Northwest)) > 0){
				return Direction.Northwest;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Southwest)) > 0){
				return Direction.Southwest;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.West)) > 0){
				return Direction.West;
			}
			else{
				return;
			}
		}
		else if(dx < 0){
			if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.West)) > 0){
				return Direction.West;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Northwest)) > 0){
				return Direction.Northwest;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Southwest)) > 0){
				return Direction.Southwest;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.North)) > 0){
				return Direction.North;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.South)) > 0){
				return Direction.South;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Northeast)) > 0){
				return Direction.Northeast;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Southeast)) > 0){
				return Direction.Southeast;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.East)) > 0){
				return Direction.East;
			}
			else{
				return;
			}
		}
		else if(dy < 0){
			if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.South)) > 0){
				return Direction.South;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Southeast)) > 0){
				return Direction.Southeast;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Southwest)) > 0){
				return Direction.Southwest;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.East)) > 0){
				return Direction.East;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.West)) > 0){
				return Direction.West;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Northeast)) > 0){
				return Direction.Northeast;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Northwest)) > 0){
				return Direction.Northwest;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.North)) > 0){
				return Direction.North;
			}
			else{
				return;
			}

		}
		else if(dy > 0){
			if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.North)) > 0){
				return Direction.North;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Northeast)) > 0){
				return Direction.Northeast;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Northwest)) > 0){
				return Direction.Northwest;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.East)) > 0){
				return Direction.East;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.West)) > 0){
				return Direction.West;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Southeast)) > 0){
				return Direction.Southeast;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.Southwest)) > 0){
				return Direction.Southwest;
			}
			else if(gc.startingMap(planet).isPassableTerrainAt(myLoc.add(Direction.South)) > 0){
				return Direction.South;
			}
			else{
				return;
		}
		else{
			return;
		}
	}
		/*
		int orthoLine = -xDisplacement/yDisplacement; //creates orthogonal search line based off of slope of displacement line
		wait, shouldn't it be 1.0*this? -Ruben
		*/
	public void performTasks(){
	}
}
