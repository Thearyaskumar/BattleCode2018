import bc.*;
public class Robot{

	public static void oneMove(GameController gc, Unit unit, int strategy){
        VecUnit nearby;
        Direction[] da = Direction.values();
        Unit target;
        Team otherTeam = gc.team()==Team.Blue ? Team.Red : Team.Blue;
        if(!unit.location().isInGarrison()) {
            switch (strategy) {
                case Player.ATTACK:
                    nearby = gc.senseNearbyUnitsByTeam(unit.location().mapLocation(),unit.attackRange(),otherTeam);
                    if(nearby.size()>0 && gc.isAttackReady(unit.id())) {
                        target = nearby.get(0);
                        for(int i = 0; i < nearby.size(); i++) {
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
                    if(gc.isMoveReady(unit.id())) {
                        shuffleArray(da);
                        for(Direction d:da) {
                            if(gc.canMove(unit.id(),d)) {
                                gc.moveRobot(unit.id(),d);
                                break;
                            }
                        }
                    }
                    break;
                case Player.DEFEND:
                    if(gc.isAttackReady(unit.id())) {
                        nearby = gc.senseNearbyUnitsByTeam(unit.location().mapLocation(),unit.attackRange(),otherTeam);
                        if(nearby.size()>0) {
                            target = nearby.get(0);
                            for(int i = 0; i < nearby.size(); i++) {
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
                        for(Direction d:da) {
                            if(gc.canMove(unit.id(),d)) {
                                gc.moveRobot(unit.id(),d);
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