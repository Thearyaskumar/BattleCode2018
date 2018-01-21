import bc.*;
import java.util.*;
public class Player{
    public static void main(String[] args){
        GameController gc = new GameController();

        // Setup hashsets for our units
        ArrayList<Worker> myWorkers = new ArrayList<Worker>();
        ArrayList<Knight> myKnights = new ArrayList<Knight>();
        ArrayList<Mage> myMages = new ArrayList<Mage>();
        ArrayList<Ranger> myRangers = new ArrayList<Ranger>();
        ArrayList<Healer> myHealers = new ArrayList<Healer>();
        ArrayList<Factory> myFactories = new ArrayList<Factory>();
        ArrayList<Rocket> myRockets = new ArrayList<Rocket>();

        // Hashsets for the robots we've already seen
        HashSet<Integer> seen = new HashSet<Integer>();

        while(1){
            // First we will add all new units:
            VecUnit units = gc.myUnits();
            for(int i = 0; i < units.size(); i++){
                Unit u = units.get(i); // Get the unit
                if (!seen.contains(u.id())){ // If we don't recognize it's id
                    switch(u.unitType()){ // Add it to the respective collection
                        case Factory: myFactories.add(new Factory(u, gc)); break;
                        case Rocket:  myRockets.add(new Rocket(u, gc));    break;
                        case Worker:  myWorkers.add(new Worker(u,gc));     break;
                        case Mage:    myMages.add(new Mage(u,gc));         break;
                        case Ranger:  myRangers.add(new Ranger(u,gc));     break;
                        case Knight:  myKnights.add(new Knight(u,gc));     break;
                        case Healer:  myHealers.add(new Healer(u,gc));     break;
                        
                    }
                    seen.add(u.id()); // We've seen them now
                }
            }

            // DO THE REST OF THE STUFF HERE
        }
    }
}