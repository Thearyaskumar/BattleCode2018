import bc.*;
public class Rocket{
    public static void oneMove(GameController gc, Unit unit, int strategy){
        if(unit.structureGarrison().size() == unit.structureMaxCapacity())
          gc.launchRocket(unit.id(), new MapLocation(Planet.Mars, (int)(Math.random()*gc.startingMap(Planet.Mars).getWidth()), (int)(Math.random()*gc.startingMap(Planet.Mars).getHeight()))

        for(Direction d: Direction.values()){
          try{
            Unit u = senseUnitAtLocation(unit.location().mapLocation().add(d))
            gc.load(unit.id(), u.id());
          }
          catch(Exception e){}
        }
    }
}
