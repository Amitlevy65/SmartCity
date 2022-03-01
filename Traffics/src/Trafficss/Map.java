package Trafficss;

import java.util.ArrayList;
import java.util.Random;


public class Map {
	

	private int mapsize;
	private ArrayList<Junction> junctions = new ArrayList<Junction>();
	private ArrayList<Road> roads = new ArrayList<Road>();
	private Random pointrand = new Random();
	private ArrayList<TrafficLight> trafficlights = new ArrayList<TrafficLight>();
	
	public Map(ArrayList<Junction>junctions,ArrayList<Road>roads) {
		this.junctions = junctions;
		this.roads = roads;
		for (Junction j : this.junctions) {
			makeTLight(j);
		}
	}

	public Map(int mapsize) { 
		this.mapsize = mapsize;            
		MakeJunctions(this.mapsize);
		MakeRoads(this.getJunctions().size());
		for (Junction J : this.junctions) {
			makeTLight(J);
		}
		create();
}
	
	public ArrayList<Junction> getJunctions() { //A function that returns the junctions in the map (in the arraylist).
		return this.junctions;
	}
	
	public ArrayList<Road> getRoads(){
		return this.roads;
	}
	
	public ArrayList<Junction> MakeJunctions(int mapsize){
		for (int i = 0; i < mapsize; i++) {
			double newX = 800*pointrand.nextDouble();
			double newY = 600*pointrand.nextDouble();
			Junction newJunc = new Junction(newX,newY);
			junctions.add(newJunc);
		}
		return junctions;
	}
	
	public ArrayList<Road> MakeRoads(int juncArraySize){
		for (int j = 0; j < junctions.size() ; j++) {    
			for (int i = 0; i < junctions.size(); i++) {
				if (i != j && (1 == (int) (Math.random()*2) )) {			
					roads.add(new Road(this.junctions.get(j),this.junctions.get(i)));
				}
			}
		}
		return roads;
	}
	
	public void makeTLight(Junction J) {
		Random Trand = new Random();
		if (Trand.nextBoolean() && Trand.nextBoolean()) {
			if (Trand.nextBoolean()) {
				RandomTrafficLight Tlight = new RandomTrafficLight(J.getEnteringRoads());
				trafficlights.add(Tlight);
			} else {
				ConsecutiveTrafficLight Tlight = new ConsecutiveTrafficLight(J.getEnteringRoads());
				trafficlights.add(Tlight);
			}
		}
	}
	
	public ArrayList<TrafficLight> getTrafficLights(){
		return this.trafficlights;
	}
	
	public ArrayList<Road> MakeRoute(){
		Junction RandomJ;
		RandomJ = this.junctions.get((int) (Math.random()*this.junctions.size()));
		ArrayList<Road> newroads = new ArrayList<Road>();
		
		while (newroads.size()<4 && RandomJ.getExitingRoads().size() > 0 ) {
			newroads.add(RandomJ.getExitingRoads().get((int) (Math.random()*RandomJ.getExitingRoads().size())));
			RandomJ = newroads.get(newroads.size()-1).getEnd();
		}
		return newroads;
	}

	public void create() {
		for (int i = 0; i < this.roads.size(); i++) {
		}
	}
	
	public ArrayList<Road> calcShortestPath(Junction j1,Junction j2){ 
		ArrayList<Double> lensum = new ArrayList<Double>();
		ArrayList<Road> prevroads = new ArrayList<Road>();
		ArrayList<Road> newroute = new ArrayList<Road>();
		ArrayList<Road> exitingroads;
		ArrayList<Boolean> visitedjuncs = new ArrayList<Boolean>(); 
		Junction currentj;
		Road currentr;
		int start;
		int end;
		int index = 0;
		
		for (int i = 0; i < junctions.size(); i++) {
			lensum.add(i,Double.MAX_VALUE);
			visitedjuncs.add(false);
			prevroads.add(null);
		}
		
		lensum.set(junctions.indexOf(j1), 0.0);
		end = junctions.indexOf(j1);
		while (visitedjuncs.contains(false)) {
			double minlen = Double.MAX_VALUE;

			for (int i = 0; i < junctions.size(); i++) {
				if (lensum.get(i) <= minlen && visitedjuncs.get(i)==false) { 
					index=i;
					minlen = lensum.get(i);
				}
			} 
			currentj = junctions.get(index);
			exitingroads = currentj.getExitingRoads();
			
			for (int j = 0; j < exitingroads.size(); j++) { 
				currentr = exitingroads.get(j);
				start = junctions.indexOf(currentj);
				end = junctions.indexOf(currentr.getEnd());
				if (lensum.get(end) > currentr.getLength() + lensum.get(start)) {
					lensum.set(end, currentr.getLength() + lensum.get(start));
					prevroads.set(end, currentr);

				}
			}			
			visitedjuncs.set(junctions.indexOf(currentj), true);
		}
		currentr = prevroads.get(junctions.indexOf(j2));
		
		newroute.add(0,currentr);

		while (prevroads.get(junctions.indexOf(currentr.getStart())) != null) {
			currentr = prevroads.get(junctions.indexOf(currentr.getStart()));
			newroute.add(0,currentr);
		}

		return newroute;		
	}
	
	
	@Override
	public String toString() {
		return "[" + roads + "]";
	}
	
}
	


