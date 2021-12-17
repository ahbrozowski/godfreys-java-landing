package org.godfreyslanding;

import java.awt.Color;

public class Sky extends Body {

	public Sky(double x, double y) {
		super(x, y, 2, 2, new Vector(0,0), Color.CYAN, true, 0, 8, true, 0);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(boolean gravity,Time t) {
		super.update(gravity, t);
		int timeDay = t.getTimeDay();
		int dayLength = t.getDayLength();
		double c = dayLength/24.0;
		double h = timeDay/c;
		int lowest = 8;
		for(int i = 1; i <= 8; i++) {
			if(h < i/2.0 || h > (14 - i/2.0)) {
				if(i < lowest) {
					lowest = i;
				}
			}
		}
		System.out.println(lowest + " " + h);
		light = lowest;
		
	}

}
