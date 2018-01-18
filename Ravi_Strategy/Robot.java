import bc.*;
public class Robot{

	public static void oneMove(GameController gc, Unit unit, int strategy){
        VecUnit nearby;
        Direction d;
        Direction[] da = Direction.values();
        Unit target;
        int pos, i;
        MapLocation locID;
        Team otherTeam = gc.team()==Team.Blue ? Team.Red : Team.Blue;
        Veci32 teamArray = gc.getTeamArray(gc.planet());
        if(!unit.location().isInGarrison()) {
            switch (strategy) {
                case Player.ATTACK:
                    nearby = gc.senseNearbyUnitsByTeam(unit.location().mapLocation(),unit.attackRange(),otherTeam);
                    if(nearby.size()>0 && gc.isAttackReady(unit.id())) {
                        target = nearby.get(0);
                        locID = target.location().mapLocation();
                        for(i = 0; i < teamArray.size() && teamArray.get(i)!=0; i++) {
                            if(new MapLocation(gc.planet(),teamArray.get(i)/100,teamArray.get(i)%100).isWithinRange(25,locID)) {
                                locID = null;
                                break;
                            }
                        }
                        if(locID==null && i<teamArray.size())
                            gc.writeTeamArray(i,target.location().mapLocation().getX()*100+target.location().mapLocation().getY());
                        for(i = 0; i < nearby.size(); i++) {
                            if(nearby.get(i).unitType()==UnitType.Mage && gc.canAttack(unit.id(),nearby.get(i).id())) {
                                gc.attack(unit.id(),nearby.get(i).id());
                                break;
                            } else if(nearby.get(i).unitType()==UnitType.Healer && (target.unitType()==UnitType.Ranger || target.unitType()==UnitType.Knight) ) {
                                target = nearby.get(i);
                            } else if(nearby.get(i).unitType()==UnitType.Ranger && target.unitType()==UnitType.Knight) {
                                target = nearby.get(i);
                            }
                        }
                        if(gc.isAttackReady(unit.id()) && gc.canAttack(unit.id(),target.id()))
                            gc.attack(unit.id(),target.id());
                    }
                    locID = unit.location().mapLocation();
                    for(i = 0; i < teamArray.size() && teamArray.get(i)!=0; i++) {
                        if(locID.isAdjacentTo(new MapLocation(gc.planet(),teamArray.get(i)/100,teamArray.get(i)%100)) && gc.senseNearbyUnitsByTeam(locID,unit.visionRange(),otherTeam).size()==0) {
                            gc.writeTeamArray(i,0);
                            break;
                        }
                    }
                    if(gc.isMoveReady(unit.id())) {
                        i=0;
                        while(i<teamArray.size() && teamArray.get(i)==0)
                            i++;
                        if(i<teamArray.size()) {
                            MapLocation m = new MapLocation(gc.planet(),teamArray.get(i)/100,teamArray.get(i)%100);
                            d = unit.location().mapLocation().directionTo(m);
                            pos=0;
                            while(pos<8 && !gc.canMove(unit.id(),d)) {
                                d = Math.random()<0.5 ? bc.bcDirectionRotateLeft(d) : bc.bcDirectionRotateRight(d);
                                pos++;
                            }
                            if(pos<8) {
                                gc.moveRobot(unit.id(),d);
                            }
                        } else {
                            da = Direction.values();
                            shuffleArray(da);
                            for(Direction di:da) {
                                if(gc.canMove(unit.id(),di)) {
                                    gc.moveRobot(unit.id(),di);
                                    break;
                                }
                            }
                        }
                    }
                    break;
                case Player.DEFEND:
                    if(gc.isAttackReady(unit.id())) {
                        nearby = gc.senseNearbyUnitsByTeam(unit.location().mapLocation(),unit.attackRange(),otherTeam);
                        if(nearby.size()>0) {
                            target = nearby.get(0);
                            for(i = 0; i < nearby.size(); i++) {
                                if(nearby.get(i).unitType()==UnitType.Mage && gc.canAttack(unit.id(),nearby.get(i).id())) {
                                    gc.attack(unit.id(),nearby.get(i).id());
                                    break;
                                } else if(nearby.get(i).unitType()==UnitType.Healer && (target.unitType()==UnitType.Ranger || target.unitType()==UnitType.Knight) ) {
                                    target = nearby.get(i);
                                } else if(nearby.get(i).unitType()==UnitType.Ranger && target.unitType()==UnitType.Knight) {
                                    target = nearby.get(i);
                                }
                            }
                            if(gc.isAttackReady(unit.id()) && gc.canAttack(unit.id(),target.id()))
                                gc.attack(unit.id(),target.id());
                        }
                    }
                    if(gc.isMoveReady(unit.id())) {
                        da = Direction.values();
                        shuffleArray(da);
                        for(Direction di:da) {
                            if(gc.canMove(unit.id(),di)) {
                                gc.moveRobot(unit.id(),di);
                                break;
                            }
                        }
                    }
                    break;
            }
        }
    }

    private static void shuffleArray(Direction[] array)
    {
        int index;
        Direction temp;
        for (int i = array.length - 1; i > 0; i--)
        {
            index = (int)(Math.random()*(i+1));
            if (index != i)
            {
                temp = array[index];
                array[index] = array[i];
                array[i] = temp;
            }
        }
    }
}