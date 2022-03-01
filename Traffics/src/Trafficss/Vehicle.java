package Trafficss;

import java.util.ArrayList;
import java.util.Random;

public class Vehicle extends Thread {
	Map map;
	Random randSpeed;
	int Speed;
	double distLeft;
	ArrayList<Road> Route;

	private static int counter = 0;

	int countname;
	int count;
	static int totalcounter = 0;
	boolean arrived = false;

	public Vehicle(Map map) {
		counter++;
		countname = counter;
		this.map = map;
		Speed = (int) ((Math.random() * 120) + 30);
		Route = map.MakeRoute();
		if ( new Random().nextBoolean()) {
			Route = this.map.calcShortestPath(Route.get(0).getStart(), Route.get(Route.size()-1).getEnd());
		}
		count = 0;
		if (count < Route.size()) {
			distLeft = Route.get(count).getLength();
		}
		creating();
	}

	public void run() {
		while (arrived ==false) {
			this.move();
		}
	}

	public void move() {
		if (count < Route.size()) {
			TrafficLight tl=Route.get(count).getEnd().getTlight();
			distLeft -= Speed;
			if (distLeft <= 0) {
				if (count < Route.size() - 1) {
			
					
					if (tl!=null&&tl.getCurrenGreen() != Route.get(count)) {
						try {
							System.out.println(this.toString() + " is waiting for green light at "
									+ Route.get(count).getEnd());
							synchronized (tl) {
								tl.wait();
							}
						} catch (InterruptedException e) {
							System.out.println(e.getMessage());
						}
					}  	
					count++;
					distLeft = Route.get(count).getLength();

				}
				else {
						System.out.println(
								toString() + " arrived to it's destination: " + Route.get(count).getEnd().toString());
						arrived = true;
						totalcounter++;
						return;
				}
			}
			System.out.println(toString() + " is moving on the " + Route.get(count).toString());
						
		}
		

		try {
			sleep(1000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static int getCounter() {
		return counter;
	}
	public Junction getCurrentJunction() {
		return this.getCurrentRoad().getEnd();
	}

	public Road getCurrentRoad() {
		return Route.get(counter);
	}

	public int getSpeed() {
		return this.Speed;
	}

	public double getDistanceLeft() {
		return this.distLeft;
	}

	private void creating() {
		System.out.println("Creating " + toString() + ", speed: " + Speed + ", path: " + this.Route.toString());
	}

	public static int getTotalCounter() {
		return totalcounter;
	}
	
	public ArrayList<Road> getRoute() {
		return Route;
	}
	
	public void setRoute(ArrayList<Road> route) {
		Route = route;
	}

	@Override
	public String toString() {
		return "Vehicle " + countname;
	}

}
