// import the API.
// See xxx for the javadocs.
import bc.*;

public class Player {
    public static void main(String[] args) {
        // Connect to the manager, starting the game
        GameController gc = new GameController();

        // This should be called repeatedly, for now we'll do it once
        int[] strategy = developStrategy(gc);
        System.out.println("(" + strategy[0] + ", " + strategy[1] + ")");

        while (true) {
            // Develop strategy:
            VecUnit units = gc.myUnits();

            // VecUnit is a class that you can think of as similar to ArrayList<Unit>, but immutable.
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

    private static int[] developStrategy(GameController gc){
        return EarthPlayer.strategy(gc); // There is no strategy right now
    }
}
