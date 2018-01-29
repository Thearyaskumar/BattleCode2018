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
						moveTarget=unit.location().mapLocation();
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
}
