package com.personel.ballbat.common;

import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MP3 extends Thread {
    private String filename;
    private Player player; 

    // constructor that takes the name of an MP3 file
    public MP3(String filename) {
        this.filename = filename;
        try {
        	InputStream in =  this.getClass().getClassLoader().getResourceAsStream(filename);
        	player = new Player(in);
        }
        catch (Exception e) {
            System.out.println("Problem playing file " + filename);
            System.out.println(e);
        }
    }

    public void close() { 
    	if (player != null) 
    		player.close(); 
    }
    // play the MP3 file to the sound card

    public void play() {
            }
    
    public void run() {
    	try {
			player.play();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}