package com.personel.ballbat.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
/**
 * Represents each brick in the game.
 * @author george
 *
 */
public class Brick {

	int x;
	int y;
	int height;
	int width;
	Rectangle rectangle;
	/**
	 * 
	 * @param x the x position of the brick
	 * @param y the y position of the brick
	 * @param width the width of the brick
	 * @param height the height of the brick
	 */
	
	public Brick(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		rectangle = new Rectangle(x,y,width,height);	
	}

	public void draw(Graphics g)
	{   
		g.setColor(Color.orange);
		g.fillRect(x, y, width, height);
	}
	
	@Override
	public String toString() {
		return "Brick [x=" + x + ", y=" + y + ", height=" + height + ", width="
				+ width + ", rectangle=" + rectangle + "]";
	}
}
