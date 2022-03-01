package Trafficss;

import java.util.ArrayList;

public class ConsecutiveTrafficLight extends TrafficLight{
	int counter;
	
	public ConsecutiveTrafficLight(ArrayList<Road> roadsin) {
		super(roadsin);
		counter =-1;
	}


	@Override
	public Road SelectRoad(ArrayList<Road> roadsin) {
		counter++;
		if (counter >= roadsin.size()) {
			this.counter = 0;
		} 
		return this.roadsin.get(counter);
	}


	@Override
	public String toString() {
		return "Sequential " + super.toString();
	}
	
}
