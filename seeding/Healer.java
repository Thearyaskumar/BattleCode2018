import bc.*;
import java.util.*;
public class Healer extends Robot{
	private Unit unit;
	private GameController gc;
	private MapLocation moveTarget;
	private Random random;

	public Healer(Unit u, GameController gameController){
		unit = u;
		gc = gameController;
		random = new Random();
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
			VecUnit visibleTeamMates = gc.senseNearbyUnitsByTeam(unit.location().mapLocation(), unit.attackRange(), gc.team());
			for(int i = 0; i < visibleTeamMates.size(); i++) {
				if(gc.isHealReady(unit.id()) && gc.canHeal(unit.id(), visibleTeamMates.get(i).id())) {
					gc.heal(unit.id(), visibleTeamMates.get(i).id());
				}
			}

			//choose moveTarget if moveTarget is null
			if(moveTarget==null || visibleTeamMates.size()==0 && !Player.getEnemyLocs().isEmpty())
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
