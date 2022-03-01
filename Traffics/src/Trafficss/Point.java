package Trafficss;

import java.util.Random;

public class Point {
	private double x=0;
	private double y=0;
	Random pointrand = new Random();
	
	public Point() {
		setRandomPoint();
		create();
	}
	
	public Point (double x, double y) {
		this.x = x;
		this.y = y;

		if ( x < 0 || x > 800) {

			double x1 = 800*pointrand.nextDouble();
			System.out.println(((float)(Math.round(x*100)))/100 + " is illegal value for x and has been replaced with  " + x1);
		
			
			this.x = x1;
		} if ( y < 0 || y > 600) {
			
			double y1 = 600*pointrand.nextDouble();
			System.out.println(((float)(Math.round(y*100)))/100 +" is illegal value for y and has been replaced with " + y1);
			
			this.y = y1;
		}
		create();
	}

	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double calcDistance(Point other) {
		return Math.sqrt(Math.pow(this.getX()-other.getX(),2) + Math.pow(this.getY()-other.getY(),2 ));
	}
	
	public void setRandomPoint() {
		setX(800*pointrand.nextDouble()) ;
		setY(600*pointrand.nextDouble()) ;
	}
	
	public void create() {
		System.out.println("Creating " + toString());
	}

	@Override
	public String toString() {
		return "Point (" + (((float)(Math.round(x*100)))/100) +" , " + (((float)(Math.round(y*100)))/100) + ")";
	}
	
}
