import bc.*;
public class EarthPlayer{
    public static int[] strategy(GameController gc){
        int[] strat = new int[]{0,0,0};

        PlanetMap map = gc.startingMap(Planet.values()[0]);
        VecUnit units = map.getInitial_units();

        for(int i = 0; i < units.size(); i++){
            MapLocation l = units.get(i).location().mapLocation();
            strat[0] += l.getX();
            strat[1] += l.getY();
            strat[2]++;
        }

        // Time to average them!
        strat[0] = strat[0]/strat[2];
        strat[1] = strat[0]/strat[2];
        strat[2] = 0; // Just go there :)

        return strat;
    }
}