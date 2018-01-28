import bc.*;
import java.util.*;
public class Worker extends Robot{
	Unit unit;
	GameController gc;
	int target;
	public Worker(Unit u, GameController gameController){
		unit = u;
		gc = gameController;
		target = -1;
	}

}