import bc.*;
public class Worker{

    public static MapLocation target;

	public static void oneMove(GameController gc, Unit unit, int strategy){
        VecMapLocation m;
        Direction d;
        int pos; 
        if(!unit.location().isInGarrison()) {
        switch (strategy) {
            case Player.HARVEST:
                m = gc.allLocationsWithin(unit.location().mapLocation(),1);
                pos = 0;
                while(pos<m.size() && gc.karboniteAt(m.get(pos))<=0) {
                    pos++;
                }
                if(pos<m.size() && gc.canHarvest(unit.id(),unit.location().mapLocation().directionTo(m.get(pos)))) {
                    gc.harvest(unit.id(),unit.location().mapLocation().directionTo(m.get(pos)));
                } else if(gc.isMoveReady(unit.id())) { //no karbonite...move to new location
                    m = gc.allLocationsWithin(unit.location().mapLocation(),50);
                    pos = 0;
                    for(int i=0; i<m.size(); i++) {
                        if(gc.karboniteAt(m.get(i))>0 && gc.startingMap(gc.planet()).onMap(m.get(pos)))
                            pos=i;
                    }
                    if(pos==0 && gc.karboniteAt(m.get(0))==0) {
                        if(target==null) 
                            target = new MapLocation(gc.planet(),(int)(Math.random()*gc.startingMap(gc.planet()).getWidth()),(int)(Math.random()*gc.startingMap(gc.planet()).getHeight()));
                        d = unit.location().mapLocation().directionTo(target);
                    } else {
                        d = unit.location().mapLocation().directionTo(m.get(pos));
                    }
                    if(target!=null && (unit.location().mapLocation().equals(target) || (unit.location().mapLocation().isWithinRange(unit.visionRange(),target) && gc.startingMap(gc.planet()).isPassableTerrainAt(target)==0))) {
                        target = null;
                    }
                    pos=0;
                    while(pos<8 && !gc.canMove(unit.id(),d)) {
                        d = Math.random()<0.5 ? bc.bcDirectionRotateLeft(d) : bc.bcDirectionRotateRight(d);
                        pos++;
                    }
                    if(pos<8) {
                        gc.moveRobot(unit.id(),d);
                    }
                }
                break;
            
            case Player.BUILD_FACTORY:
                m = gc.allLocationsWithin(unit.location().mapLocation(),1);
                boolean moveDone = false;
                for(pos = 0; pos < m.size(); pos++) {
                    if(gc.hasUnitAtLocation(m.get(pos)) && gc.senseUnitAtLocation(m.get(pos)).unitType()==UnitType.Factory && gc.senseUnitAtLocation(m.get(pos)).structureIsBuilt()==0 && gc.canBuild(unit.id(),gc.senseUnitAtLocation(m.get(pos)).id())) {
                        gc.build(unit.id(),gc.senseUnitAtLocation(m.get(pos)).id());
                        moveDone = true;
                        break;
                    }
                }
                if(!moveDone)
                    for(pos = 0; pos < m.size(); pos++) {
                        if(unit.location().mapLocation().directionTo(m.get(pos)) != Direction.North && gc.canBlueprint(unit.id(),UnitType.Factory,unit.location().mapLocation().directionTo(m.get(pos)))) {  
                            gc.blueprint(unit.id(),UnitType.Factory,unit.location().mapLocation().directionTo(m.get(pos)));
                            moveDone = true;
                            break;
                        }
                    }
                if(!moveDone && gc.isMoveReady(unit.id())) { //no spaces left...move to new location
                    if(target==null) 
                        m = gc.allLocationsWithin(unit.location().mapLocation(),50);
                        target = m.get((int)(Math.random()*m.size()));
                    d = unit.location().mapLocation().directionTo(target);
                    if(target!=null && (unit.location().mapLocation().equals(target) || (unit.location().mapLocation().isWithinRange(unit.visionRange(),target) && gc.startingMap(gc.planet()).isPassableTerrainAt(target)==0))) {
                        target = null;
                    }
                    pos=0;
                    while(pos<8 && !gc.canMove(unit.id(),d)) {
                        d = Math.random()<0.5 ? bc.bcDirectionRotateLeft(d) : bc.bcDirectionRotateRight(d);
                        pos++;
                    }
                    if(pos<8) {
                        gc.moveRobot(unit.id(),d);
                    }
                }
                break;

                case Player.BUILD_ROCKET:
                m = gc.allLocationsWithin(unit.location().mapLocation(),1);
                boolean moveDone = false;
                for(pos = 0; pos < m.size(); pos++) {
                    if(gc.hasUnitAtLocation(m.get(pos)) && gc.senseUnitAtLocation(m.get(pos)).unitType()==UnitType.Factory && gc.senseUnitAtLocation(m.get(pos)).structureIsBuilt()==0 && gc.canBuild(unit.id(),gc.senseUnitAtLocation(m.get(pos)).id())) {
                        gc.build(unit.id(),gc.senseUnitAtLocation(m.get(pos)).id());
                        moveDone = true;
                        break;
                    }
                }
                if(!moveDone)
                    for(pos = 0; pos < m.size(); pos++) {
                        if(unit.location().mapLocation().directionTo(m.get(pos)) != Direction.North && gc.canBlueprint(unit.id(),UnitType.Rocket,unit.location().mapLocation().directionTo(m.get(pos)))) {  
                            gc.blueprint(unit.id(),UnitType.Rocket,unit.location().mapLocation().directionTo(m.get(pos)));
                            moveDone = true;
                            break;
                        }
                    }
                if(!moveDone && gc.isMoveReady(unit.id())) { //no spaces left...move to new location
                    if(target==null) 
                        m = gc.allLocationsWithin(unit.location().mapLocation(),50);
                        target = m.get((int)(Math.random()*m.size()));
                    d = unit.location().mapLocation().directionTo(target);
                    if(target!=null && (unit.location().mapLocation().equals(target) || (unit.location().mapLocation().isWithinRange(unit.visionRange(),target) && gc.startingMap(gc.planet()).isPassableTerrainAt(target)==0))) {
                        target = null;
                    }
                    pos=0;
                    while(pos<8 && !gc.canMove(unit.id(),d)) {
                        d = Math.random()<0.5 ? bc.bcDirectionRotateLeft(d) : bc.bcDirectionRotateRight(d);
                        pos++;
                    }
                    if(pos<8) {
                        gc.moveRobot(unit.id(),d);
                    }
                }
                break;
        }
    }



    }
}