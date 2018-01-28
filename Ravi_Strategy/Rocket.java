import bc.*;
public class Rocket{
    public static void oneMove(GameController gc, Unit unit, int strategy){
        if(unit.location().isOnPlanet(Planet.Mars)) {
            if(unit.structureGarrison().size()>0)
                for(Direction d : Direction.values()) {
                    if(gc.canUnload(unit.id(),d))
                        gc.unload(unit.id(),d);
                }
        } else if(unit.structureGarrison().size() == unit.structureMaxCapacity()) {
            MapLocation m;
            int i = 0;
            do {
                i++;
                m = new MapLocation(Planet.Mars, (int)(Math.random()*gc.startingMap(Planet.Mars).getWidth()), (int)(Math.random()*gc.startingMap(Planet.Mars).getHeight()));
            } while(i<10 && !gc.canLaunchRocket(unit.id(),m));
            System.out.println(i);
            if(i<10) {
                gc.launchRocket(unit.id(), m);
                System.out.println("launched rocket");
            }
        } else {
            for(Direction d: Direction.values()){
                try{
                    if(gc.hasUnitAtLocation(unit.location().mapLocation().add(d))) {
                        Unit u = gc.senseUnitAtLocation(unit.location().mapLocation().add(d));
                        //System.out.println("loaded unit onto rocket");
                        gc.load(unit.id(), u.id());
                    }
                }
                catch(Exception e){
                    //e.printStackTrace();
                }
            }
        }
        
    }
}
