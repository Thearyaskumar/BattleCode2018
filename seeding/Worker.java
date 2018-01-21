import bc.*;
import java.util.*;
public class Worker extends Robot{
	private Unit unit;
	private GameController gc;
	public Worker(Unit u, GameController gameController){
		unit = u;
		gc = gameController;
	}
}