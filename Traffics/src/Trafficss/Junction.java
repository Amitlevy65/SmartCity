package Trafficss;

import java.util.ArrayList;

public class Junction extends Point{
	
	private ArrayList<Road> roadsin = new ArrayList<Road>();
	private ArrayList<Road> roadsout = new ArrayList<Road>();
	static ArrayList<Junction> all_junction = new ArrayList<Junction>();
	int num;
	private TrafficLight tlight=null;
	

	public Junction() {
		super();
	}
	
	public Junction (double x , double y) {
		super(x,y);
	}
	
	public ArrayList<Road> getEnteringRoads(){ 
		return this.roadsin;
	}
	
	public ArrayList<Road> getExitingRoads(){ 
		return this.roadsout;
	}
	
	public Junction getJunction() {
		return this;
	}
	
	public void create() {
		all_junction.add(this);
		num = all_junction.size();
		System.out.println("Creating " + toString() + " at " + super.toString());
	}


	public TrafficLight getTlight() {
		return tlight;
	}

	public void setTlight(TrafficLight tlight) {
		this.tlight = tlight;
	}

	@Override
	public String toString() {
		return "Junction "+num;
	}
	


}
