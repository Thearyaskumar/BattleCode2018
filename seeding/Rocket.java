import bc.*;
import java.util.*;
public class Rocket{
	private Unit unit;
	private GameController gc;
	private ArrayList<UnitType> wanted;
	private int target;
	private MapLocation landingLoc;
	public Rocket(Unit u, GameController game){
		unit = u;
		gc = game;
		wanted = new ArrayList<UnitType>();
		findLandingLoc();
		round = gc.round();//decide what robots the rocket wants based on the round number
		if (round<250)
			wanted.addAll(Arrays.asList(UnitType.Mage, UnitType.Worker, UnitType.Knight));
		else if (round<500)
			wanted.addAll(Arrays.asList(UnitType.Mage, UnitType.Mage, UnitType.Worker, UnitType.worker, UnitType.Knight));
		//else anything goes, just get off Earth fast!
		target = -1;
	}
	public Location getLoc(){
		return unit.location();
	}
	public ArrayList<UnitType> wanted(){
		return wanted;
	}
	public int getTarget(){
		return target;
	}
	public void setTarget(int t){
		target = t;
	}
	public MapLocation getLandingLoc(){
		return landingLoc;
	}
	public void findLandingLoc(){
		landingLoc = new MapLocation(Planet.Mars, (int)(Math.random()*gc.startingMap(Planet.Mars).getWidth()), (int)(Math.random()*gc.startingMap(Planet.Mars).getHeight()));
	}
	public boolean fullHealth(){
		return unit.health()!=unit.maximumHealth();
	}
	public long health(){
		return unit.health();
	}
	public boolean fullGar(){
		return unit.structureGarrison().size()==unit.structureMaxCapacity();
	}
	public boolean emptyGar(){
		return unit.structureGarrison().size()==0;
	}
	public int id(){
		return unit.id();
	}
}