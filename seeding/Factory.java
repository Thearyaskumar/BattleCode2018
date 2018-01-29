import bc.*;
import java.util.*;
public class Factory{
    Unit unit;
    GameController gc;
    long size;
    double pworker = 0;
    double pmage = 0;
    double pranger = 0;
    double pknight = 0;
    double phealer = 0;
	public Factory(Unit u, GameController game){
        unit = u;
        gc = game;
        size = gc.startingMap(gc.planet()).getHeight()*gc.startingMap(gc.planet()).getWidth();
    }
    public int id() {
        return unit.id();
    }
    public void build(UnitType ut){
        /* Begin building units if you are able to */
        if (!gc.canProduceRobot(unit.id(), ut))
            return; // Can't produce robot
        gc.produceRobot(unit.id(), ut);
    }
    public void unloadGarrison(){
        /* Try to unload your garrison in any available spaces */
        //VecUnitID garrison = unit.structureGarrison();
        for(Direction d : Direction.values()){
            if(gc.canUnload(unit.id(), d))
                gc.unload(unit.id(), d);
        }
    }
    public boolean canUnloadGarrison(){
        return unit.structureGarrison().size() > 0;
    }
    public void oneMove(){
        if (gc.round()>600 && gc.myUnits().size()>0)
            return; //save Karbonite
        if (unit.isFactoryProducing()==0){
            if (canUnloadGarrison())
                unloadGarrison();
            else{
                boolean largeMap = size>750;
                if (largeMap){
                    pworker = 0.4*((600.0-1.0*gc.round())/600.0);
                    phealer = 0.4*(1.0*gc.round()/600.0);
                    pmage = 0.3;
                    pranger = 0.2;
                    pknight = 0.1;
                }else{
                    pworker = 0.2*((600.0-1.0*gc.round())/600.0);
                    phealer = 0.2*(1.0*gc.round()/600.0);
                    pmage = 0.4;
                    pranger = 0.2;
                    pknight = 0.2;
                double rand = Math.random();
                if (rand<pworker)
                    build(UnitType.Worker); 
                else if (rand<pworker+phealer)
                    build(UnitType.Healer);
                else if (rand<pworker+phealer+pmage)
                    build(UnitType.Mage);
                else if (rand<pworker+phealer+pmage+pranger)
                    build(UnitType.Ranger);
                else
                    build(UnitType.Knight);
                }
            }
        }
    }
}