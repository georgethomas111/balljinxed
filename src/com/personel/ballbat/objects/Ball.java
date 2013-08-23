package com.personel.ballbat.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.personel.ballbat.common.Common;
import com.personel.ballbat.model.Position;
import java.lang.Math;
/**
 * 
 * @author george
 * Ball that gets dynamically updated during the coarse of the game
 * Contains all the properties of the ball
 */

public class Ball {

	Position position;
	public int radius;
	public Rectangle rectangle;
	double speed;
	int angle;
	public Ball(Position position, int radius, double d ,int angle) {
		super();
		this.position = position;
		this.radius = radius;
		this.rectangle = 
			new Rectangle((int)position.getX() - radius , 
					(int)position.getY() - radius ,	2 * radius ,2 * radius);
		this.speed = d;
		this.angle = angle;
	}
	
	/**
	 * - used for for shiftY because origin is on the top left corner of the frame
	 * @param angle - the angle the ball is currently travelling in
	 * @param speed - the speed of the ball
	 * @return - true if still in frame after update
	 */
	public boolean update() {	
		position.shiftX(Math.cos(radian(angle)) * +speed); //Think about floating point pixels
		position.shiftY(Math.sin(radian(angle)) * -speed);
		rectangle.setLocation((int)position.getX() , (int)position.getY());
		return position.isInFrame();
	}
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	/**
	 * Returns the angle in radiants for an angle in degree
	 * @param angle
	 * @return
	 */
	private double radian(int angle) {
		return angle * Math.PI / 180;		
	}

	/**
	 * Draws the ball
	 * @param g
	 */
	public void draw(Graphics g)
	{
		g.setColor(Color.YELLOW);
		g.fillArc((int)position.getX(), (int)position.getY(), radius, radius, 0, 360);
	}
	
	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	
	public void correctRectanglePosition() {
		rectangle.setLocation((int)position.getX() - radius , (int)position.getY() - radius);
	}
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public boolean down() {
		if(position.getY() > Common.MAX_FRAMEHEIGHT)
			return true;
		else
			return false;
	}

	public void reflect() {
		angle = 180 - angle ;
	}

	public void topReflect() {
		angle = 360 - angle ;
	}

	public boolean top() {
		if(position.getY() < Common.MIN_FRAMEHEIGHT)
			return true;
		else
			return false;
	}
	
	@Override
	public String toString() {
		return "Ball [position=" + position + ", radius=" + radius
				+ ", rectangle=" + rectangle + ", speed=" + speed + ", angle="
				+ angle + "]";
	}

}
