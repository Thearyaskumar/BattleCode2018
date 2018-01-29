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
		int pos = 0;
		if(unit.location().isOnMap()) {
			if(unit.location().isOnPlanet(Planet.Earth)) { //stuff specific to Earth
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
						break;
					}
				}
			} else if(gc.round()>600) {
				VecMapLocation m = gc.allLocationsWithin(unit.location().mapLocation(),1);
				for(pos = 0; pos < m.size(); pos++) {
					if(gc.canBlueprint(unit.id(),UnitType.Rocket,unit.location().mapLocation().directionTo(m.get(pos)))) {  
						gc.blueprint(unit.id(),UnitType.Rocket,unit.location().mapLocation().directionTo(m.get(pos)));
						break;
					}
				}
			}
			


			//mine all surrounding karbonite
			VecMapLocation m = gc.allLocationsWithin(unit.location().mapLocation(),1);
			pos = 0;
			while(pos<m.size() && gc.karboniteAt(m.get(pos))<=0) {
				pos++;
			}
			if(pos<m.size() && gc.canHarvest(unit.id(),unit.location().mapLocation().directionTo(m.get(pos)))) {
				gc.harvest(unit.id(),unit.location().mapLocation().directionTo(m.get(pos)));
			} else if(pos==m.size()) {
				//choose moveTarget if moveTarget is null
				if(moveTarget==null) {
					m = gc.allLocationsWithin(unit.location().mapLocation(),unit.visionRange());
					for(int i = 0; i < 5; i++) {
						int p = (int)(Math.random()*m.size());
						if(gc.karboniteAt(m.get(p))>0)
							moveTarget = m.get(p);
					}
				}

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
}