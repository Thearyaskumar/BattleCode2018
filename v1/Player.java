//package bot;
// import the API.
// See xxx for the javadocs.
import bc.*;

public class Player {
    public static void main(String[] args) {
        // Connect to the manager, starting the game
        GameController gc = new GameController();

        onGameStart();

        while (true) {
            // VecUnit is a class that you can think of as similar to ArrayList<Unit>, but immutable.
            VecUnit units = gc.myUnits();
            for (int i = 0; i < units.size(); i++) {
                Unit unit = units.get(i);

                switch (unit.unitType()){
                    case Factory: System.out.println("Unit is a Factory");
                                  break;
                    case Rocket: System.out.println("Unit is a Rocket");
                                  break;
                    case Worker: System.out.println("Unit is a Worker");
                                  break;
                    case Ranger: System.out.println("Unit is a Ranger");
                                  break;
                    case Mage: System.out.println("Unit is a Mage");
                                  break;
                    case Knight: System.out.println("Unit is a Knight");
                                  break;
                    case Healer: System.out.println("Unit is a Healer");
                                  break;
                }

                while(true){
                    // Just stop here temporarily
                }
            }
            // Submit the actions we've done, and wait for our next turn.
            gc.nextTurn();
        }
    }

    private static void onGameStart(){
        // As of right now, there is nothing particular we are doing at the start of the game :)
    }
}
