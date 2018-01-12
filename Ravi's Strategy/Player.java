// import the API.
// See xxx for the javadocs.
import bc.*;

public class Player {
    public static void main(String[] args) {
        // Connect to the manager, starting the game
        GameController gc = new GameController();

        onGameStart();
        int strategy;

        while (true) {
            // Develop strategy:
            strategy = developStrategy(gc); // That should call {earth, mars}Player.developStrategy()

            // VecUnit is a class that you can think of as similar to ArrayList<Unit>, but immutable.
            VecUnit units = gc.myUnits();
            for (int i = 0; i < units.size(); i++) {
                Unit unit = units.get(i);

                switch (unit.unitType()){
                    case Factory: Factory.oneMove(gc, unit, strategy);
                                  break;
                    case Rocket: Rocket.oneMove(gc, unit, strategy);
                                  break;
                    case Worker: Robot.oneMove(gc, unit, strategy);
                                  break;
                    case Ranger: Robot.oneMove(gc, unit, strategy);
                                  break;
                    case Mage: Robot.oneMove(gc, unit, strategy);
                                  break;
                    case Knight: Robot.oneMove(gc, unit, strategy);
                                  break;
                    case Healer: Robot.oneMove(gc, unit, strategy);
                                  break;
                }
            }
            // Submit the actions we've done, and wait for our next turn.
            gc.nextTurn();
        }
    }

    private static void onGameStart(){
        // As of right now, there is nothing particular we are doing at the start of the game :)
    }

    private static int developStrategy(GameController gc){
        return 0; // There is no strategy right now
    }
}
