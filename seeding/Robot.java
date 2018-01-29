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

	public int id() {
		return unit.id();
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
		Planet planet = gc.planet();
		if(dx > 0 && dy > 0){
			if(gc.canMove(unit.id(), Direction.Northeast)){
				return Direction.Northeast;
			}
			else if(gc.canMove(unit.id(), Direction.North)){
				return Direction.North;
			}
			else if(gc.canMove(unit.id(), Direction.East)){
				return Direction.East;
			}
			else if(gc.canMove(unit.id(), Direction.Northwest)){
				return Direction.Northwest;
			}
			else if(gc.canMove(unit.id(), Direction.Southeast)){
				return Direction.Southeast;
			}
			else if(gc.canMove(unit.id(), Direction.West)){
				return Direction.West;
			}
			else if(gc.canMove(unit.id(), Direction.South)){
				return Direction.South;
			}
			else if(gc.canMove(unit.id(), Direction.Southwest)){
				return Direction.Southwest;
			}
			else{
				return null;
			}
		}
		else if(dx > 0 && dy< 0){
			if(gc.canMove(unit.id(), Direction.Southeast)){
				return Direction.Southeast;
			}
			else if(gc.canMove(unit.id(), Direction.South)){
				return Direction.South;
			}
			else if(gc.canMove(unit.id(), Direction.East)){
				return Direction.East;
			}
			else if(gc.canMove(unit.id(), Direction.Northeast)){
				return Direction.Northeast;
			}
			else if(gc.canMove(unit.id(), Direction.Southwest)){
				return Direction.Southwest;
			}
			else if(gc.canMove(unit.id(), Direction.North)){
				return Direction.North;
			}
			else if(gc.canMove(unit.id(), Direction.West)){
				return Direction.West;
			}
			else if(gc.canMove(unit.id(), Direction.Northwest)){
				return Direction.Northwest;
			}
			else{
				return null;
			}
		}
		else if(dx < 0 && dy < 0){
			if(gc.canMove(unit.id(), Direction.Southwest)){
				return Direction.Southwest;
			}
			else if(gc.canMove(unit.id(), Direction.South)){
				return Direction.South;
			}
			else if(gc.canMove(unit.id(), Direction.West)){
				return Direction.West;
			}
			else if(gc.canMove(unit.id(), Direction.Southeast)){
				return Direction.Southeast;
			}
			else if(gc.canMove(unit.id(), Direction.Northwest)){
				return Direction.Northwest;
			}
			else if(gc.canMove(unit.id(), Direction.East)){
				return Direction.East;
			}
			else if(gc.canMove(unit.id(), Direction.North)){
				return Direction.North;
			}
			else if(gc.canMove(unit.id(), Direction.Northeast)){
				return Direction.Northeast;
			}
			else{
				return null;
			}
		}
		else if(dx < 0 && dy > 0){
			if(gc.canMove(unit.id(), Direction.Northwest)){
				return Direction.Northwest;
			}
			else if(gc.canMove(unit.id(), Direction.North)){
				return Direction.North;
			}
			else if(gc.canMove(unit.id(), Direction.West)){
				return Direction.West;
			}
			else if(gc.canMove(unit.id(), Direction.Northeast)){
				return Direction.Northeast;
			}
			else if(gc.canMove(unit.id(), Direction.Southwest)){
				return Direction.Southwest;
			}
			else if(gc.canMove(unit.id(), Direction.East)){
				return Direction.East;
			}
			else if(gc.canMove(unit.id(), Direction.South)){
				return Direction.South;
			}
			else if(gc.canMove(unit.id(), Direction.Southeast)){
				return Direction.Southeast;
			}
			else{
				return null;
			}
		}
		else if(dx > 0){
			if(gc.canMove(unit.id(), Direction.East)){
				return Direction.East;
			}
			else if(gc.canMove(unit.id(), Direction.Northeast)){
				return Direction.Northeast;
			}
			else if(gc.canMove(unit.id(), Direction.Southeast)){
				return Direction.Southeast;
			}
			else if(gc.canMove(unit.id(), Direction.North)){
				return Direction.North;
			}
			else if(gc.canMove(unit.id(), Direction.South)){
				return Direction.South;
			}
			else if(gc.canMove(unit.id(), Direction.Northwest)){
				return Direction.Northwest;
			}
			else if(gc.canMove(unit.id(), Direction.Southwest)){
				return Direction.Southwest;
			}
			else if(gc.canMove(unit.id(), Direction.West)){
				return Direction.West;
			}
			else{
				return null;
			}
		}
		else if(dx < 0){
			if(gc.canMove(unit.id(), Direction.West)){
				return Direction.West;
			}
			else if(gc.canMove(unit.id(), Direction.Northwest)){
				return Direction.Northwest;
			}
			else if(gc.canMove(unit.id(), Direction.Southwest)){
				return Direction.Southwest;
			}
			else if(gc.canMove(unit.id(), Direction.North)){
				return Direction.North;
			}
			else if(gc.canMove(unit.id(), Direction.South)){
				return Direction.South;
			}
			else if(gc.canMove(unit.id(), Direction.Northeast)){
				return Direction.Northeast;
			}
			else if(gc.canMove(unit.id(), Direction.Southeast)){
				return Direction.Southeast;
			}
			else if(gc.canMove(unit.id(), Direction.East)){
				return Direction.East;
			}
			else{
				return null;
			}
		}
		else if(dy < 0){
			if(gc.canMove(unit.id(), Direction.South)){
				return Direction.South;
			}
			else if(gc.canMove(unit.id(), Direction.Southeast)){
				return Direction.Southeast;
			}
			else if(gc.canMove(unit.id(), Direction.Southwest)){
				return Direction.Southwest;
			}
			else if(gc.canMove(unit.id(), Direction.East)){
				return Direction.East;
			}
			else if(gc.canMove(unit.id(), Direction.West)){
				return Direction.West;
			}
			else if(gc.canMove(unit.id(), Direction.Northeast)){
				return Direction.Northeast;
			}
			else if(gc.canMove(unit.id(), Direction.Northwest)){
				return Direction.Northwest;
			}
			else if(gc.canMove(unit.id(), Direction.North)){
				return Direction.North;
			}
			else{
				return null;
			}
		}
		else if(dy > 0){
			if(gc.canMove(unit.id(), Direction.North)){
				return Direction.North;
			}
			else if(gc.canMove(unit.id(), Direction.Northeast)){
				return Direction.Northeast;
			}
			else if(gc.canMove(unit.id(), Direction.Northwest)){
				return Direction.Northwest;
			}
			else if(gc.canMove(unit.id(), Direction.East)){
				return Direction.East;
			}
			else if(gc.canMove(unit.id(), Direction.West)){
				return Direction.West;
			}
			else if(gc.canMove(unit.id(), Direction.Southeast)){
				return Direction.Southeast;
			}
			else if(gc.canMove(unit.id(), Direction.Southwest)){
				return Direction.Southwest;
			}
			else if(gc.canMove(unit.id(), Direction.South)){
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

	public MapLocation randomChoice(HashSet<MapLocation> myHashSet) {
		int size = myHashSet.size();
		int item = (int)(Math.random()*size); // In real life, the Random object should be rather more shared than this
		int i = 0;
		for(MapLocation obj : myHashSet)
		{
			if (i == item)
				return obj;
			i++;
		}
	}
}
