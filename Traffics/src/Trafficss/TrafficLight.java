package Trafficss;

import java.util.ArrayList;
//import java.util.concurrent.Semaphore;
import java.util.Random;

public abstract class TrafficLight extends Thread{

	private Random rand = new Random();
	private int delay;
	protected ArrayList<Road> roadsin;
	Road chosenRoad;
	Road greenlight;
	
	public TrafficLight (ArrayList<Road> roadsin) {
		this.roadsin = roadsin;
	
		delay = 2+rand.nextInt(3);

		this.greenlight = SelectRoad(this.roadsin);
		System.out.println(toString());
		roadsin.get(0).getEnd().setTlight(this);
	}
	
	public abstract Road SelectRoad(ArrayList<Road> roadsin);
	
	public int getDelay() {
		return this.delay;
	}

	public void run() {  
		 while ( Vehicle.getTotalCounter() < Vehicle.getCounter()) {			
			try {
				sleep(delay * 1000);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			chosenRoad = SelectRoad(this.roadsin);
			greenlight = chosenRoad;
			System.out.println(toString());
			synchronized (this) {
				this.notifyAll();
			}
		}
	}
	
	public Road getCurrenGreen() {
		return greenlight;
	}
	
	public Junction getJunction() {
		return chosenRoad.getEnd();
	}

	@Override
	public String toString() {
		return "TrafficLight " + this.greenlight.getEnd().toString() + ", delay=" + delay + ": green light on " + greenlight.toString() ;
	}


}