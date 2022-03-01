package Trafficss;

import java.util.ArrayList;

public class RandomTrafficLight extends TrafficLight{

	
	public RandomTrafficLight(ArrayList<Road> roadsin) {
		super(roadsin);
	
	}
	
	@Override
	public Road SelectRoad (ArrayList<Road>roadsin) {
		int random = (int)(Math.random() * roadsin.size());
		return roadsin.get(random);
	}

	@Override
	public String toString() {
		return "Random " +super.toString();
	}
	

}
