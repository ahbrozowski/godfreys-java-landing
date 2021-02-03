package org.godfreyslanding;

public class Time {
	long start;
	long total;
	int timeDay;
	static int DAYLENGTH = 1440000;
	public Time() {
		start = System.currentTimeMillis();
		total = 0;
		timeDay = 0;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public int getTimeDay() {
		return timeDay;
	}
	public void setTimeDay(int timeDay) {
		this.timeDay = timeDay;
	} 
	
	public void update() {
		total = System.currentTimeMillis() - start;
		timeDay = (int) (total%DAYLENGTH);
		
	}
	
	public static int getDayLength() {
		return DAYLENGTH;
	}
	public double getHour() {;
	
		double c = DAYLENGTH/24.0;
		double h = timeDay/c;
		return h;
	}
	

	
}
