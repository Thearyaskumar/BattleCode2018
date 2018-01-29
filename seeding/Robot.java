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

	public Direction bug2(MapLocation targetMapLoc){
		//given target loc
		//next step towards that direction
		Location myLoc = unit.location();
		if (!myLoc.isOnMap()){
			return null; //basically, if it's not on a map, don't do anything
		}
		MapLocation myMapLoc = myLoc.mapLocation();
		int targetX = targetMapLoc.getX();
		int targetY = targetMapLoc.getY();
		int dx = targetX - myMapLoc.getX();
		int dy = targetY - myMapLoc.getY();
		if(dx > 0 && dy > 0){
			if(gc.startingMap(planet).canMove(unit.id(), Direction.Northeast) > 0){
				return Direction.Northeast;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.North) > 0){
				return Direction.North;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.East) > 0){
				return Direction.East;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Northwest) > 0){
				return Direction.Northwest;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Southeast) > 0){
				return Direction.Southeast;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.West) > 0){
				return Direction.West;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.South) > 0){
				return Direction.South;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Southwest) > 0){
				return Direction.Southwest;
			}
			else{
				return null;
			}
		}
		else if(dx > 0 && dy< 0){
			if(gc.startingMap(planet).canMove(unit.id(), Direction.Southeast) > 0){
				return Direction.Southeast;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.South) > 0){
				return Direction.South;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.East) > 0){
				return Direction.East;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Northeast) > 0){
				return Direction.Northeast;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Southwest) > 0){
				return Direction.Southwest;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.North) > 0){
				return Direction.North;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.West) > 0){
				return Direction.West;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Northwest) > 0){
				return Direction.Northwest;
			}
			else{
				return null;
			}
		}
		else if(dx < 0 && dy < 0){
			if(gc.startingMap(planet).canMove(unit.id(), Direction.Southwest) > 0){
				return Direction.Southwest;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.South) > 0){
				return Direction.South;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.West) > 0){
				return Direction.West;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Southeast) > 0){
				return Direction.Southeast;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Northwest) > 0){
				return Direction.Northwest;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.East) > 0){
				return Direction.East;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.North) > 0){
				return Direction.North;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Northeast) > 0){
				return Direction.Northeast;
			}
			else{
				return null;
			}
		}
		else if(dx < 0 && dy > 0){
			if(gc.startingMap(planet).canMove(unit.id(), Direction.Northwest) > 0){
				return Direction.Northwest;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.North) > 0){
				return Direction.North;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.West) > 0){
				return Direction.West;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Northeast) > 0){
				return Direction.Northeast;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Southwest) > 0){
				return Direction.Southwest;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.East) > 0){
				return Direction.East;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.South) > 0){
				return Direction.South;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Southeast) > 0){
				return Direction.Southeast;
			}
			else{
				return null;
			}
		}
		else if(dx > 0){
			if(gc.startingMap(planet).canMove(unit.id(), Direction.East) > 0){
				return Direction.East;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Northeast) > 0){
				return Direction.Northeast;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Southeast) > 0){
				return Direction.Southeast;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.North) > 0){
				return Direction.North;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.South) > 0){
				return Direction.South;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Northwest) > 0){
				return Direction.Northwest;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Southwest) > 0){
				return Direction.Southwest;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.West) > 0){
				return Direction.West;
			}
			else{
				return null;
			}
		}
		else if(dx < 0){
			if(gc.startingMap(planet).canMove(unit.id(), Direction.West) > 0){
				return Direction.West;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Northwest) > 0){
				return Direction.Northwest;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Southwest) > 0){
				return Direction.Southwest;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.North) > 0){
				return Direction.North;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.South) > 0){
				return Direction.South;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Northeast) > 0){
				return Direction.Northeast;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Southeast) > 0){
				return Direction.Southeast;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.East) > 0){
				return Direction.East;
			}
			else{
				return null;
			}
		}
		else if(dy < 0){
			if(gc.startingMap(planet).canMove(unit.id(), Direction.South) > 0){
				return Direction.South;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Southeast) > 0){
				return Direction.Southeast;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Southwest) > 0){
				return Direction.Southwest;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.East) > 0){
				return Direction.East;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.West) > 0){
				return Direction.West;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Northeast) > 0){
				return Direction.Northeast;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Northwest) > 0){
				return Direction.Northwest;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.North) > 0){
				return Direction.North;
			}
			else{
				return null;
			}
		}
		else if(dy > 0){
			if(gc.startingMap(planet).canMove(unit.id(), Direction.North) > 0){
				return Direction.North;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Northeast) > 0){
				return Direction.Northeast;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Northwest) > 0){
				return Direction.Northwest;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.East) > 0){
				return Direction.East;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.West) > 0){
				return Direction.West;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Southeast) > 0){
				return Direction.Southeast;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.Southwest) > 0){
				return Direction.Southwest;
			}
			else if(gc.startingMap(planet).canMove(unit.id(), Direction.South) > 0){
				return Direction.South;
			}
			else{
				return null;
			}
		}
		else{
			return null;
		}
	}
}
