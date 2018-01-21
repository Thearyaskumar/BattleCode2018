import bc.*;
public class Factory{
	public static void oneMove(GameController gc, Unit unit, int strategy){
        if(unit.isFactoryProducing()==0) {
            if(unit.structureGarrison().size()>0)
                for(Direction d : Direction.values()) {
                    if(gc.canUnload(unit.id(),d))
                        gc.unload(unit.id(),d);
                }
            else
                switch (strategy) {
                    case Player.BUILD_RANGER:
                        if(gc.canProduceRobot(unit.id(),UnitType.Ranger))
                            gc.produceRobot(unit.id(),UnitType.Ranger);
                        break;
                    case Player.BUILD_MAGE:
                        if(gc.canProduceRobot(unit.id(),UnitType.Mage))
                            gc.produceRobot(unit.id(),UnitType.Mage);
                        break;
                    case Player.BUILD_HEALER:
                        if(gc.canProduceRobot(unit.id(),UnitType.Healer))
                            gc.produceRobot(unit.id(),UnitType.Healer);
                        break;
                    case Player.BUILD_KNIGHT:
                        if(gc.canProduceRobot(unit.id(),UnitType.Knight))
                            gc.produceRobot(unit.id(),UnitType.Knight);
                        break;
                        case Player.BUILD_WORKER:
                        if(gc.canProduceRobot(unit.id(),UnitType.Worker))
                            gc.produceRobot(unit.id(),UnitType.Worker);
                        break;
                }
        }
    }
}