package Trafficss;

public class Timer extends Thread{
	
	
	
	public Timer() {
			
	}
	
	public void run() {
		int seconds = 0;
		int tsecs = 0;
		int mins = 0;
		int tmins = 0;
		
		do {
			try {
				if (seconds<10) {
					System.out.println("Time: " + tmins+mins+":"+tsecs+seconds);
					seconds++;
					if (seconds==10) {
						seconds = 0;
						tsecs++;
						if (tsecs==6) {
							tsecs=0;
							seconds=0;
							mins++;
							if (mins==10) {
								mins=0;
								tsecs=0;
								seconds=0;
								tmins++;
							}
						}
					}

					sleep(1000);
					
				}
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());;
			}
		
		} while (Vehicle.getTotalCounter()<Vehicle.getCounter());
	}
	
	
}
