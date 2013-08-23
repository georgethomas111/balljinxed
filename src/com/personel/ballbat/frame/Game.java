package com.personel.ballbat.frame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

import com.personel.ballbat.common.Common;
import com.personel.ballbat.common.MP3;
import com.personel.ballbat.model.Position;
import com.personel.ballbat.objects.Ball;
import com.personel.ballbat.objects.Bat;
import com.personel.ballbat.objects.Level;
/**
 * 
 * @author george
 * Frame over which the entire action is going to take place. 
 * Will have the ball,bat and brick in it.
 *  This class controlls all the other classes
 */
public class Game extends JFrame implements KeyListener,MouseListener {
	//TODO 1.Check Brick Design How it could be made more efficient
	//TODO 2.Add Images to make it much more interactive
	
	private static final long serialVersionUID = 1L;

	//Set initial position to be just above the bat
	//1 is the speed of the ball
	Position initialBallPosition = new Position(Common.BALL_POSITION.getX() 
			, Common.BALL_POSITION.getY());
	Ball ball = new Ball(initialBallPosition , Common.BALL_INITIALRADIUS, 0.5 ,Common.SAFESPACE);//Creating the ball using ball class
    int noOfRectangles = 5; //Represents the no of rectangles in a bat. Decides the impact and reflection from bat
    Position initialBatPosition = new Position(Common.BAT_POSITION.getX() 
			, Common.BAT_POSITION.getY());
    Bat bat = new Bat(initialBatPosition , Common.BAT_WIDTH , Common.BAT_HEIGHT, noOfRectangles);//Creating the bat
	Level level;//Bricks object is created here.
	int impactvalue;//This value determines the impact i e the value that specifies the direction it is going to move
	boolean up,start,down,pressRight,pressLeft;//Values that keep track of different statuses
    Image img;
	int score;//total score
    int life;//no of lifes
    Graphics g;//graphics object assosciated with the frame
    boolean ballavailability;//To check if the ball is present in the frame
	private boolean gameover;//To check if the game is over
	MP3 ballHitBatMp3 ;
	boolean levelFinished = false;
	Game() {
		setTitle("Game");
		setVisible(true);
		setSize(400, 400);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(this);
		addMouseListener(this);
		g = getGraphics();
		//TODO Make this proper
		level = new Level(80,30,5,5,15,g);//Creating the bricks and giving the values.The level can be adjusted here.
		up=down=pressRight=pressLeft=false;
		ballavailability = true;
		gameover = false;
		score = 0;
		life = 5;
		ballHitBatMp3 = new MP3(Common.BATBALLSOUND);
    }

	public void paint(Graphics g) {   
		img = createImage(getWidth(),getHeight());
	    paintcomponent(img.getGraphics());
	    g.drawImage(img, 0, 0, this);
	    repaint();
	}
	
	/**
	 * paints the ball bat and bricks in the frame and will be responsible for the movements
	 * @param g graphics object
	 */
	public void paintcomponent(Graphics g) {
		
		// TODO Remove Hard Quoted Entities With the ones in Common
		ball.draw(g);
	    bat.draw(g);
	    level.draw(g);

	    g.setColor(Color.GREEN);
	    if(!start) {
		    g.drawString("Press S or click to Start The Game" ,
		    		(int)Common.INFO_POSITION.getX() , (int)Common.INFO_POSITION.getY());	
	    	}
	   else if(gameover) {
	    	g.drawString("Game Over Better Luck Next time", 
	    			(int)Common.INFO_POSITION.getX() , (int)Common.INFO_POSITION.getY());
	    	g.drawString("Score:" + score + "pts", 
	    			(int)Common.SCORE_POSITION.getX(), (int)Common.SCORE_POSITION.getY());
	    	}
	    else {
		    g.drawString("Score:" + score + "pts", 
		    		(int)Common.SCORE_POSITION.getX(), (int)Common.SCORE_POSITION.getY());
	    	update();	
		    }
	    
	    if(!ballavailability && life>0)
	    {   
	    	start = false;	
	    }
		if(levelFinished) {
		    g.drawString("Congrats!!! You have finished the level" ,
		    		(int)Common.INFO_POSITION.getX() , (int)Common.INFO_POSITION.getY());		
	    }
	}

	/**
	 * Updates the bat,ball and bricks. The updated values are used in paintcomponent
	 */
	
	public void update() {
		if(pressLeft && bat.getPosition().getX() > Common.MIN_FRAMEWIDTH)
			bat.move(-2);
		if(pressRight && bat.getPosition().getX() < Common.MAX_FRAMEWIDTH-bat.getWidth())
			bat.move(+2);
	    if(!ball.update())
	    	if(ball.down()) {
	    		subLife();
	    	} else if(ball.top()) {
	    		ball.topReflect(); //implies it collided with the walls 
	    	} else {
	    		ball.reflect();
	    	}
	    		
		if(bat.collodeWithBall(ball.getPosition().getX(), 
				ball.getPosition().getY()+ ball.getRadius())) {
			ballHitBatMp3.start();
			ballHitBatMp3 = new MP3(Common.BATBALLSOUND);
			ball.setAngle(bat.getAngle()); //get the angle the bat is going to direct the ball
			
		}
		
		//Subtracting Ball radius because we are checking what happens at the top.
		
		if(level.checkbrickcollition(ball.getPosition().getX(), 
				ball.getPosition().getY() - ball.getRadius())) {
			score += 10;
			ball.reflect();
			if(level.getNoOfBricksRemaining() == 0)
				levelFinished = true;
		}
		ball.update();
	}

/**
 *  Called When a life is lost	
 */
private void subLife() {
		
		life--;
		System.out.println("Fell down " + ball + "Position =" + Common.BAT_POSITION);
		if(life>0) {
			initialBallPosition.setX(Common.BALL_POSITION.getX());
			initialBallPosition.setY(Common.BALL_POSITION.getY());
			
			initialBatPosition.setY(Common.BAT_POSITION.getY());
			initialBatPosition.setX(Common.BAT_POSITION.getX());
			ball.setAngle(Common.SAFESPACE);
			bat.setPosition(initialBatPosition);
			ball.setPosition(initialBallPosition);
		    ball.correctRectanglePosition();
			start = false;
			System.out.println("Fell down " + ball + "Position =" + Common.BAT_POSITION);
			impactvalue = 1;//default impact value 
			} else {
			gameover =  true;
			}
}

/**
	 * Key events for different keys pressed
	 * Sets the status variables in the class for the other functions to evaluate
	 */
 	@Override
	public void keyPressed(KeyEvent e) {
	switch(e.getKeyCode())
		{
		case KeyEvent.VK_UP: up = true; break;
		case KeyEvent.VK_DOWN: down = true; break;
		case KeyEvent.VK_RIGHT: pressRight = true; break;
		case KeyEvent.VK_LEFT: pressLeft = true; break;
		case KeyEvent.VK_S:start = true; break;	
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_UP: up = false; break;
		case KeyEvent.VK_DOWN: down = false; break;
		case KeyEvent.VK_RIGHT: pressRight = false; break;
		case KeyEvent.VK_LEFT: pressLeft = false; break;	
		}		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	/**
	 * Makes the start status true
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		start = true;
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	public static void main(String args[])
	{
		new Game();
	}
	
}
