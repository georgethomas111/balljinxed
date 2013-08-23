package com.personel.ballbat.objects;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.personel.ballbat.common.Common;

/**
 * 
 * @author george
 *
 * Represents the bricks that are present as the obstruction. 
 * The structure of the bricks is also shown here(The levels).
 */

public class Level {
	
	List<Brick> bricks; //Represents a list of brick objects
	int noofbricks;
	int widthOfBricks;
	int heightOfBricks;
    int minHorizontalGap;
    int minVerticalGap;
    Graphics g;
	int height;
	int startx = 10;
    int starty = 10;

   

    public void draw(Graphics g) {
    for (Brick brick : bricks) {
    	brick.draw(g);
    	}
    }
    /**
     * 
     * @param noofbricks - the no of bricks
     * @param totalwidth - the total width of the bricks
     * @param totalheight - the total height of the bricks
     *
     */
    public Level(int noofbricks, int widthOfBricks,
			int heightOfBricks, int minHorizontalGap, int minVerticalGap,
			Graphics g1) {
		super();
		this.widthOfBricks = widthOfBricks;
		this.heightOfBricks = heightOfBricks;
		this.minHorizontalGap = minHorizontalGap;
		this.minVerticalGap = minVerticalGap;
		this.g = g1;
		int  count = 0; 
		bricks = new ArrayList<Brick>();
		int i,j;
		for(j = starty; j < Common.MAX_FRAMEHEIGHT/2 ; j += heightOfBricks + minVerticalGap)
		    for(i = startx;i < Common.MAX_FRAMEWIDTH ; i += widthOfBricks + minHorizontalGap) {
		    	if(count == noofbricks) {
		    		break;
		    		}
		    	count++;
		    	Brick brick = new Brick(i , j , widthOfBricks , heightOfBricks);
		    	bricks.add(brick);
			    }
		this.noofbricks = count;
		System.out.println("sss" + bricks.size());
	}

	/**
     * Checks if the ball has collided with the bricks
     * @param d - the balls x position
     * @param e - the balls y position
     * @return - true if it hits
     */
    
    public Boolean checkbrickcollition(double d, double e) {    
    	for (Brick brick : bricks) 
			if(brick.rectangle.contains(d, e)) {  
			   bricks.remove(brick);
			   return true;
			}
    	return false;
    }
    
    public int getNoOfBricksRemaining() {
    	return bricks.size();
    }
    
	public int getNoofbricks() {
		return noofbricks;
	}
}
