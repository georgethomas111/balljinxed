package com.personel.ballbat.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.*;

public class Sound implements Runnable {

	String fileName;
	
	public void run()
	{
		AudioStream audioStream = null;
		try {
			
//			new FileInputStream(fileName);
		InputStream in =  this.getClass().getClassLoader().getResourceAsStream(fileName);
		System.out.println(in.toString());
		audioStream = new AudioStream(in);
		
		AudioPlayer.player.start(audioStream);            
		} catch(Exception e) {
			e.printStackTrace();
		}
		// Similarly, to stop the audio.
		AudioPlayer.player.stop(audioStream);

	}
	
	public Sound(String fileName) {
		super();
		this.fileName = fileName;
	}
}
