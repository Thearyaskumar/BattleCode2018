import bc.*;
public class Factory{
    public Unit unit;
    public GameController gc;
	public Factory(Unit u, GameController game){
        unit = u;
        gc = game;
    }

    public void build(UnitType ut){
        /* Begin building units if you are able to */
        if (!gc.canProduceRobot(unit.id(), ut))
            return; // Can't produce robot
        gc.produceRobot(unit.id(), ut);
    }

    public int building(){
        return unit.isFactoryProducing() ==0 ? 0 : 1;
    }

    public UnitType unloadGarrison(){
        /* Try to unload your garrison in any available spaces */
        VecUnitID garrison = unit.structureGarrison();
        if (garrison.size() > 0)
            for(Direction d : Direction.values())
                if(gc.canUnload(unit.id(), d)){
                    gc.unload(unit.id(), d);
                    return gc.unit(garrison.get(0)).unitType();
                }
        return null;
    }

    public boolean canUnloadGarrison(){
        return unit.structureGarrison().size() > 0;
    }
}