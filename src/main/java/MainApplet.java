package main.java;

import processing.core.PApplet;

/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")

public class MainApplet extends PApplet{
	private String path = "main/resources/";
	private String file = "starwars-episode-1-interactions.json";
	
	private final static int width = 1200, height = 650;
	Minim minim;
	AudioPlayer song;
	
	public void setup() {

		size(width, height);
		smooth();
		loadData();
		minim = new Minim(this);
		song = minim.loadFile(this.getClass().getResource("/res/star_wars.mp3").getPath());
		song.play();
	}

	public void draw() {

	}

	private void loadData(){

	}

}
