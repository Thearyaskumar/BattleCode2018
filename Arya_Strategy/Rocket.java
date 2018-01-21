import bc.*;
public class Rocket{
    public Unit unit;
    public GameController gc;
    public boolean requested;
    public Rocket(Unit u, GameController game){
        unit = u;
        gc = game;
        requested = false;
    }
    public void launchIfFull(){
        /* We can set a location and launch to mars! */
        if (unit.structureGarrison().size() == unit.structureMaxCapacity())
            gc.launchRocket(unit.id(), new MapLocation(Planet.Earth, 1, 1));
    }
    public MapLocation location(){
        return unit.location().mapLocation();
    }
}