package com.personel.ballbat.model;

import com.personel.ballbat.common.Common;

/**
 * 
 * @author george
 * Represents the a point
 */
public class Position {
	
	double x;
	double y;
	
	public Position(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	public boolean isInFrame() {
		if((this.x <= Common.MAX_FRAMEWIDTH) && (this.y <= Common.MAX_FRAMEHEIGHT)
				&&(this.x>=Common.MIN_FRAMEWIDTH) &&(this.y >= Common.MIN_FRAMEHEIGHT))
			
			return true;
		else
			return false;
	}
	
	public boolean shiftY(double d) {
		y += d;
		return isInFrame();	
	}
	
	public boolean shiftX(double d) {
		x += d;
		return isInFrame();	
	}
	
	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}

}
