package com.personel.ballbat.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

import com.personel.ballbat.common.Common;
import com.personel.ballbat.model.Position;

/**
 * 
 * @author george
 * Represents the bat that is going to be found at the bottom of the screen
 * The bat has three rectangles which represents the areas in the bat.
 */
public class Bat {    
	
	Position position;
	int width;
	int height;
	int noOfRectangles;
	int rectangleHit;//represents the current rectangle that got hit by the ball
	Rectangle outerrectangle;
	List<Rectangle> rectangleList; //represents rectangles that deflects balls in different directions
	private int rectSide;
	
	public Bat(Position position, int width, int height, int noOfRectangles) {
		super();
		this.position = position;
		this.width = width;
		this.height = height;
		this.noOfRectangles = noOfRectangles;
		rectSide = width/noOfRectangles;
		outerrectangle = new Rectangle((int)position.getX(),(int) position.getY(), width , height);
		rectangleList = new LinkedList<Rectangle>();
		for(int i = 0 ; i < noOfRectangles; i++) {
			Rectangle rectangle = 
				new Rectangle((int)position.getX() + i*rectSide, (int)position.getY(), rectSide,height);
			rectangleList.add(rectangle);
		}
	}
	
	/**
	 * Moves the bat by the pixel amount. For right side its +ve values
	 * For Left side movements its negative value
	 */
	public boolean move(double px) {
		if(position.shiftX(px)) {
			outerrectangle.setLocation((int)position.getX() , (int)position.getY());
			Iterator<Rectangle> iterator = rectangleList.iterator();
			int i = 0;
			while(iterator.hasNext()) {
				iterator.next().setLocation((int)position.getX() + i*rectSide , (int)position.getY());
				i++;
			}
			return true;
		} else 
			return false;		
	}

	/**
	 * Checks if the ball has collided with the bat and sets the rectangle where it hit. 
	 * @param d the current ball x position
	 * @param e the balls y position
	 * @return true if it hits the bat
	 */
	public boolean collodeWithBall(double d, double e) {
		if(outerrectangle.contains(d,e)) {
			Iterator<Rectangle> iterator = rectangleList.iterator();
			System.out.println("Outer -->" + outerrectangle + " d =" + d + "e = " + e);
			int i = 0;
			while(iterator.hasNext()) {
				Rectangle rectangle = iterator.next();
				System.out.println(rectangle); i++;
				if(rectangle.contains((int)d,(int) e))
					rectangleHit = i;
			}
			return true;
		}
		else		
			return false;
	}

	public void draw(Graphics g)
	{
		g.setColor(Color.red);	
		g.fillRect((int)position.getX(), (int)position.getY(), width, height);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getRectangleHit() {
		return rectangleHit;
	}

	public void setRectangleHit(int rectanglenumber) {
		this.rectangleHit = rectanglenumber;
	}
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public int getAngle() {
		int availableArea = 180 - Common.SAFESPACE * 2;
		return Common.SAFESPACE + ((noOfRectangles - rectangleHit) * availableArea/noOfRectangles); 
	}
}
