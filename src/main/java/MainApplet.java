package main.java;
import java.lang.reflect.Array;
import java.util.ArrayList;

import ddf.minim.*;



import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")

public class MainApplet extends PApplet{
	private String path = "main/resources/";
	private String file = "starwars-episode-1-interactions.json";
	
	private final static int width = 1200, height = 650;
	JSONObject data;
	JSONArray nodes, links;
	private ArrayList<ArrayList<Character>> characters;

	Minim minim;
	AudioPlayer song;
	
	public void setup() {
		size(width, height);
		characters = new ArrayList<ArrayList<Character>>();
		for(int i=1; i<=7; i++) {
			characters.add(new ArrayList<Character>());
		}
		smooth();
		loadData();
		minim = new Minim(this);
		/*song = minim.loadFile(this.getClass().getResource("/res/star_wars.mp3").getPath());
		song.play();*/
	}

	public void draw() {

	}

	private void loadData(){
		for(int i=1; i<=7; i++) {
			data = loadJSONObject(path + "starwars-episode-" + i + "-interactions.json");
			nodes = data.getJSONArray("nodes");
			links = data.getJSONArray("links");

			for(int j = 0; j <nodes.size(); j++) {
				characters.get(i-1).add(new Character(this, nodes.getJSONObject(j).getString("name"),
						10, 10, nodes.getJSONObject(j).getInt("value"), nodes.getJSONObject(j).getString("colour")));
			}

			for(int j=0; j< links.size(); j++) {
				characters.get(i-1).get(links.getJSONObject(j).getInt("source")).addTarget(characters.get(i-1).get(links.getJSONObject(j).getInt("target")));
			}
		}
	}

}