package Trafficss;

public class Road {
	private Junction from;
	private Junction to;
	
	public Road(Junction from, Junction to) {
		this.from = from;
		if (from == to) {
			this.to = createJunction();
			System.out.println("Road can not connect a junction to itself, the end junction has been replaced with " + this.to.toString());
		}else  {
			this.to = to;
		}		
		
		this.from.getExitingRoads().add(this); // The road is added to the list of roads out -FROM-.
		this.to.getEnteringRoads().add(this); // The road is added to the list of roads in -TO-.
		create();
	}

	public double getLength() {
		return from.calcDistance(to);
	}
	
	public Junction getStart() {
		return from;
	}
	
	public Junction getEnd() {
		return to;
	}
	
	public Junction createJunction() {
		Junction newjunction = new Junction();
		return newjunction;
	}
	
	public void create() {
		System.out.println("Creating " + toString() + ", length: " + ((float)(Math.round(this.getLength()*100)))/100);
	}
	
	@Override
	public String toString() {
		return "Road from " + from + " to " + to ;
	}

}
