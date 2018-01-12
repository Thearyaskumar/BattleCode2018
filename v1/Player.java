// import the API.
// See xxx for the javadocs.
import bc.*;

public class Player {
	public static void main(String[] args) {
		// Connect to the manager, starting the game
		GameController gc = new GameController();
		// This should be called repeatedly, for now we'll do it once
		int[] strategy = developStrategy(gc);
		while (true) {
			// No more strategy development for now: eventually, the function will have to be improved a ton
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
	private static int[] developStrategy(GameController gc){
		if (gc.planet() == Planet.valueOf("Earth"))
			return EarthPlayer.strategy(gc); //The strategy is just: Head over to the enemy
		return MarsPlayer.strategy(gc); // Haven't even thought about this yet
	}
}
