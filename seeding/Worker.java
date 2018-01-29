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

	void oneRound(int task) {
		if(unit.location().isOnMap()) {
			if(unit.location.isOnPlanet(Planet.Earth)) { //stuff specific to Earth

				VecMapLocation m = gc.allLocationsWithin(unit.location().mapLocation(),1);
                for(pos = 0; pos < m.size(); pos++) {
                    if(gc.hasUnitAtLocation(m.get(pos)) && gc.senseUnitAtLocation(m.get(pos)).unitType()==UnitType.Factory && gc.senseUnitAtLocation(m.get(pos)).structureIsBuilt()==0 && gc.canBuild(unit.id(),gc.senseUnitAtLocation(m.get(pos)).id())) {
                        gc.build(unit.id(),gc.senseUnitAtLocation(m.get(pos)).id());
                        break;
                    }
                }
                for(pos = 0; pos < m.size(); pos++) {
                    if(gc.hasUnitAtLocation(m.get(pos)) && gc.senseUnitAtLocation(m.get(pos)).unitType()==UnitType.Rocket && gc.senseUnitAtLocation(m.get(pos)).structureIsBuilt()==0 && gc.canBuild(unit.id(),gc.senseUnitAtLocation(m.get(pos)).id())) {
                        gc.build(unit.id(),gc.senseUnitAtLocation(m.get(pos)).id());
                        break;
                    }
                }

				VecUnit nearbyRockets = gc.senseNearbyUnitsByType(unit.location().mapLocation(), 5, UnitType.Rocket); //go towards nearby rockets
				for(int i = 0; i < nearbyRockets.size(); i++) {
					if(nearbyRockets.get(i).structureGarrison().size()<nearbyRockets.get(i).structureMaxCapacity()) {
						moveTarget=unit.location().mapLocation();
						break;
					}
				}
			}

			if(task==1) {
				VecMapLocation m = gc.allLocationsWithin(unit.location().mapLocation(),1);
				for(pos = 0; pos < m.size(); pos++) {
					if(gc.canBlueprint(unit.id(),UnitType.Factory,unit.location().mapLocation().directionTo(m.get(pos)))) {  
						gc.blueprint(unit.id(),UnitType.Factory,unit.location().mapLocation().directionTo(m.get(pos)));
						moveDone = true;
						break;
					}
				}
			} else if(gc.round()>600) {
				VecMapLocation m = gc.allLocationsWithin(unit.location().mapLocation(),1);
				for(pos = 0; pos < m.size(); pos++) {
					if(gc.canBlueprint(unit.id(),UnitType.Rocket,unit.location().mapLocation().directionTo(m.get(pos)))) {  
						gc.blueprint(unit.id(),UnitType.Rocket,unit.location().mapLocation().directionTo(m.get(pos)));
						moveDone = true;
						break;
					}
				}
			}
			


			//mine all surrounding karbonite
			VecMapLocation m = gc.allLocationsWithin(unit.location().mapLocation(),1);
			int pos = 0;
			while(pos<m.size() && gc.karboniteAt(m.get(pos))<=0) {
				pos++;
			}
			if(pos<m.size() && gc.canHarvest(unit.id(),unit.location().mapLocation().directionTo(m.get(pos)))) {
				gc.harvest(unit.id(),unit.location().mapLocation().directionTo(m.get(pos)));
			} else if(pos==m.size()) {
				//choose target if target is null
				if(target==null) {
					m = gc.allLocationsWithin(unit.location().mapLocation(),unit.visionRange());
					for(int i = 0; i < 5; i++) {
						if(gc.karboniteAt(m.get((int)(Math.random()*m.size())))>0)
							moveTarget = m;
					}
				}

				if(gc.isMoveReady(unit.id())) {
					if(target!=null) {
						Direction d = bug2(target);
						if(gc.canMove(unit.id(),d)) {
							gc.moveRobot(unit.id(), d);
						}
					}
				}
			}
		}
	}
}