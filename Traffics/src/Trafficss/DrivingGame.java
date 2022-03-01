package Trafficss;

import java.util.ArrayList;

public class DrivingGame {
	int turns;
	int junctions;
	int vehicles;
	ArrayList<Vehicle> cars = new ArrayList<Vehicle>();
	Map gamemap;
	
	public DrivingGame(int junctions,int vehicles) {
		this.junctions=junctions;
		this.vehicles = vehicles;
		gamemap = new Map(junctions);
		createVehicles(this.vehicles);
	}
		
	
	public ArrayList<Vehicle> createVehicles(int vehicles) {
		for (int i = 0; i < vehicles; i++) {
			Vehicle car1 = new Vehicle(this.gamemap);
			cars.add(car1);
		}
		return cars;
	}
	
	public ArrayList<Vehicle> getCars(){
		return this.cars;
	}
	
	public void play() {
		Timer timer = new Timer();
		timer.setPriority(10);
		timer.start();		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
			for (int t=0; t < getCars().size() ; t++){ 
				getCars().get(t).start();	
			}
			
			for (int y=0; y < gamemap.getTrafficLights().size(); y++) {
				gamemap.getTrafficLights().get(y).setPriority(7);
				gamemap.getTrafficLights().get(y).start();
			}
			
			while(Thread.activeCount()>1) {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
				}
			}
			System.out.println("All vehicles have arrived to their destination.");		
		}
}


