package com.personel.ballbat.common;

import com.personel.ballbat.model.Position;

/**
 * 
 * @author George
 * Contains the Common elements used  
 *
 */
public class Common {
	
	// Frame Attributes
	public static final int MAX_FRAMEWIDTH = 400;
	public static final int MAX_FRAMEHEIGHT = 400;
	public static final int MIN_FRAMEWIDTH = 0;
	public static final int MIN_FRAMEHEIGHT = 0;
	
	// 100 is considered to be the gap for showing score and info from bottom and top respectively
	
	public static final Position SCORE_POSITION = 
		new Position(MAX_FRAMEWIDTH - 100 , 100);
	public static final Position INFO_POSITION = 
		new Position(MAX_FRAMEWIDTH / 2 - 100 , MAX_FRAMEHEIGHT - 100);
	
	//Bat Attributes
	public static final int BAT_HEIGHT = 10;
	public static final int BAT_WIDTH = 50;
	public static final Position BAT_POSITION = 
		new Position(MAX_FRAMEWIDTH/2, MAX_FRAMEHEIGHT - BAT_HEIGHT/2-20);
	
	//Ball Attributes
	public static final int BALL_INITIALRADIUS = 10;
	public static final Position BALL_POSITION = 
		new Position(MAX_FRAMEWIDTH/2, MAX_FRAMEHEIGHT - BAT_HEIGHT/2-20 - 2 - BALL_INITIALRADIUS); 
	
	//Level Attributes
	public static final int LEVEL1_SPEED = 1;
	public static final int LEVEL1_BRICKPATTERN = 1;
	public static final int SAFESPACE = 30;
	public static final String BATBALLSOUND = "metal.mp3";
}

