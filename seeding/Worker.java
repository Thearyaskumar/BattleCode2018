import bc.*;
import java.util.*;
public class Worker extends Robot{
	private Unit unit;
	private GameController gc;
	private MapLocation moveTarget;
	private Random random;

	public Worker(Unit u, GameController gameController){
		unit = u;
		gc = gameController;
		random = new Random();
	}
	public int id(){
		return unit.id();
	}
	public Location getLoc(){
		return unit.location();
	}
	void oneRound(int task) {
		int pos = 0;
		if(unit.location().isOnMap()) {
			if(unit.location().isOnPlanet(Planet.Earth)) { //stuff specific to Earth
				VecMapLocation m = gc.allLocationsWithin(unit.location().mapLocation(),1);
                // for(pos = 0; pos < m.size(); pos++) {
                //     if(gc.hasUnitAtLocation(m.get(pos)) && gc.senseUnitAtLocation(m.get(pos)).unitType()==UnitType.Factory && gc.senseUnitAtLocation(m.get(pos)).structureIsBuilt()==0 && gc.canBuild(unit.id(),gc.senseUnitAtLocation(m.get(pos)).id())) {
                //         gc.build(unit.id(),gc.senseUnitAtLocation(m.get(pos)).id());
                //         break;
                //     }
				// }
				for(Direction d:Direction.values()) {
					if(gc.hasUnitAtLocation(unit.location().mapLocation().add(d)) && gc.canBuild(unit.id(),gc.senseUnitAtLocation(unit.location().mapLocation().add(d)).id()))
						gc.build(unit.id(),gc.senseUnitAtLocation(unit.location().mapLocation().add(d)).id());
				}
                // for(pos = 0; pos < m.size(); pos++) {
                //     if(gc.hasUnitAtLocation(m.get(pos)) && gc.senseUnitAtLocation(m.get(pos)).unitType()==UnitType.Rocket && gc.senseUnitAtLocation(m.get(pos)).structureIsBuilt()==0 && gc.canBuild(unit.id(),gc.senseUnitAtLocation(m.get(pos)).id())) {
                //         gc.build(unit.id(),gc.senseUnitAtLocation(m.get(pos)).id());
                //         break;
                //     }
                // }

				VecUnit nearbyRockets = gc.senseNearbyUnitsByType(unit.location().mapLocation(), 5, UnitType.Rocket); //go towards nearby rockets
				for(int i = 0; i < nearbyRockets.size(); i++) {
					if(nearbyRockets.get(i).structureGarrison().size()<nearbyRockets.get(i).structureMaxCapacity()) {
						moveTarget=nearbyRockets.get(i).location().mapLocation();
						break;
					}
				}
			

				m = gc.allLocationsWithin(unit.location().mapLocation(),1);
				if(task==1) {
					for(pos = 0; pos < m.size(); pos++) {
						if(gc.canBlueprint(unit.id(),UnitType.Factory,unit.location().mapLocation().directionTo(m.get(pos)))) {  
							gc.blueprint(unit.id(),UnitType.Factory,unit.location().mapLocation().directionTo(m.get(pos)));
							break;
						}
					}
				} else if(gc.round()>600) {
					for(pos = 0; pos < m.size(); pos++) {
						if(gc.canBlueprint(unit.id(),UnitType.Rocket,unit.location().mapLocation().directionTo(m.get(pos)))) {  
							gc.blueprint(unit.id(),UnitType.Rocket,unit.location().mapLocation().directionTo(m.get(pos)));
							break;
						}
					}
				}
			}
			


			//mine all surrounding karbonite
			VecMapLocation m = gc.allLocationsWithin(unit.location().mapLocation(),1);
			pos = 0;
			while(pos<m.size() && gc.canHarvest(unit.id(),unit.location().mapLocation().directionTo(m.get(pos)))) {
				pos++;
			}
			if(pos<m.size() && gc.canHarvest(unit.id(),unit.location().mapLocation().directionTo(m.get(pos)))) {
				gc.harvest(unit.id(),unit.location().mapLocation().directionTo(m.get(pos)));
			} else if(unit.workerHasActed()==0) {
				//choose moveTarget if moveTarget is null
				m = gc.allLocationsWithin(unit.location().mapLocation(),unit.visionRange());
				for(int i = 0; i < 5; i++) {
					int p = (int)(Math.random()*m.size());
					if(gc.canHarvest(unit.id(),unit.location().mapLocation().directionTo(m.get(pos))))
						moveTarget = m.get(p);
				}
				if(moveTarget==null)
					moveTarget=new MapLocation(Planet.Earth, (int)(Math.random()*gc.startingMap(Planet.Earth).getWidth()), (int)(Math.random()*gc.startingMap(Planet.Earth).getHeight()));

				if(gc.isMoveReady(unit.id())) {
					if(moveTarget!=null) {
						Direction d = bug2(moveTarget);
						if(gc.canMove(unit.id(),d)) {
							gc.moveRobot(unit.id(), d);
						}
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