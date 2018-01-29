import bc.*;
import java.util.*;
public class Mage extends Robot{
	private Unit unit;
	private GameController gc;
	private MapLocation moveTarget;

	public Mage(Unit u, GameController gameController){
		unit = u;
		gc = gameController;
	}
	public int id(){
		return unit.id();
	}
	void oneRound() {
		if(unit.location().isOnMap()) {
			if(unit.location().isOnPlanet(Planet.Earth)) { //stuff specific to Earth
				VecUnit nearbyRockets = gc.senseNearbyUnitsByType(unit.location().mapLocation(), 5, UnitType.Rocket); //go towards nearby rockets
				for(int i = 0; i < nearbyRockets.size(); i++) {
					if(nearbyRockets.get(i).structureGarrison().size()<nearbyRockets.get(i).structureMaxCapacity()) {
						moveTarget=nearbyRockets.get(i).location().mapLocation();
						break;
					}
				}
			}
			



			//identify enemies and store them to public hashmap
			VecUnit visibleEnemies = gc.senseNearbyUnitsByTeam(unit.location().mapLocation(), unit.attackRange(), gc.team()==Team.Blue ? Team.Red : Team.Blue);
			for(int i = 0; i < visibleEnemies.size(); i++) {
				if(gc.isAttackReady(unit.id()) && gc.canAttack(unit.id(), visibleEnemies.get(i).id())) {
					gc.attack(unit.id(), visibleEnemies.get(i).id());
				}
			}

			//choose moveTarget if moveTarget is null
			if(moveTarget==null || visibleEnemies.size()==0 && !Player.getEnemyLocs().isEmpty())
				moveTarget = randomChoice(Player.getEnemyLocs());
			else if(moveTarget==null)
				moveTarget = new MapLocation(gc.planet(), (int)(Math.random()*gc.startingMap(gc.planet()).getWidth()), (int)(Math.random()*gc.startingMap(gc.planet()).getHeight()));

			if(!gc.isAttackReady(unit.id()) && gc.isMoveReady(unit.id())) {
				if(moveTarget!=null) {
					Direction d = bug2(moveTarget);
					if(gc.canMove(unit.id(),d)) {
						gc.moveRobot(unit.id(), d);
					}
				}
			}
		}
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
}
