import bc.*;
import java.util.*;
public class Ranger extends Robot{
	private Unit unit;
	private GameController gc;
	private MapLocation moveTarget;

	public Ranger(Unit u, GameController gameController){
		unit = u;
		gc = gameController;
	}

	void oneRound() {
		if(unit.location().isOnMap()) {
			if(unit.location.isOnPlanet(Planet.Earth)) { //stuff specific to Earth
				VecUnit nearbyRockets = gc.senseNearbyUnitsByType(unit.location().mapLocation(), 5, UnitType.Rocket); //go towards nearby rockets
				for(int i = 0; i < nearbyRockets.size(); i++) {
					if(nearbyRockets.get(i).structureGarrison().size()<nearbyRockets.get(i).structureMaxCapacity()) {
						moveTarget=unit.location().mapLocation();
						break;
				}
			}
			



			//identify enemies and store them to public hashmap
			VecUnit visibleEnemies = gc.senseNearbyUnitsByTeam(unit.location().mapLocation(), unit.visionRange(), gc.team()==Team.Blue ? Team.Red : Team.Blue);
			for(int i = 0; i < visibleEnemies.size(); i++) {
				if(!Player.isInEnemyLocs(visibleEnemies.get(i).location().mapLocation())) {
					Player.addEnemyLoc(visibleEnemies.get(i).location().mapLocation());
				}
				if(gc.isAttackReady(unit.id()) && gc.canAttack(unit.id(), visibleEnemies.get(i).id())) {
					gc.attack(unit.id(), visibleEnemies.get(i).id());
				}
			}

			//choose target if target is null
			if(moveTarget==null)
				target = Player.
		}
	}
}
