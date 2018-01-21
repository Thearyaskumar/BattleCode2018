import bc.*;
import java.util.*;
public class Healer extends Robot{
	private Unit unit;
	private GameController gc;
	public Healer(Unit u, GameController gameController){
		unit = u;
		gc = gameController;
	}
}