package main.java;
import java.awt.Color;
import java.lang.reflect.Array;
import java.util.ArrayList;

import ddf.minim.*;
import de.looksgood.ani.Ani;
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
	private JSONObject data;
	private JSONArray nodes, links;
	private ArrayList<ArrayList<Character>> characters;
	private Network net;
	private int curepi;

	private Minim minim;
	private AudioPlayer song;
	
	private boolean selected = false;
	private Character grabbed = null;
		
	public void setup() {
		size(width, height);
		characters = new ArrayList<ArrayList<Character>>();
		for(int i=1; i<=7; i++) {
			characters.add(new ArrayList<Character>());
		}
		smooth();
		loadData();
		net = new Network(this);
		minim = new Minim(this);
		//System.out.println(this.getClass().getResource("/star_wars.mp3").getPath());
		/*song = minim.loadFile(this.getClass().getResource("/star_wars.mp3").getPath());
		song.play();*/
		
		Ani.init(this);
		
		curepi = 1;
	}

	public void draw() {
		strokeWeight(0);
		stroke(255);
		fill(255);
		rect(0, 0, 1200, 670);
		fill(Color.MAGENTA.getRGB());
		textSize(80);
		text("Star Wars " + curepi,350,80);
		fill(255);
		net.display();
		for(int i = 0 ; i < characters.get(curepi - 1).size() ; i++){
			characters.get(curepi - 1).get(i).display(mouseX, mouseY);
		}
		
		for(int i = 0 ; i < characters.get(curepi - 1).size() ; i++){
			Character ch  = characters.get(curepi - 1).get(i);
			if(mouseX > ch.cur_x - ch.radius && mouseX < ch.cur_x + ch.radius){
				if(mouseY > ch.cur_y - ch.radius && mouseY < ch.cur_y + ch.radius){
					fill(Color.green.getRGB());
					rect(mouseX + 10, mouseY - 10, 50 + ch.name.length()*10, 40);
					fill(0);
					textSize(20);
					text(ch.name, mouseX + 20, mouseY + 20);
					fill(255);
				}
			}
		}
	}

	private void loadData(){
		for(int i=1; i<=7; i++) {
			data = loadJSONObject(path + "starwars-episode-" + i + "-interactions.json");
			nodes = data.getJSONArray("nodes");
			links = data.getJSONArray("links");
			
			int x = 50;
			int y = 50;

			for(int j = 0; j <nodes.size(); j++) {
				characters.get(i-1).add(new Character(this, nodes.getJSONObject(j).getString("name"),
						x, y, nodes.getJSONObject(j).getInt("value"), unhex(nodes.getJSONObject(j).getString("colour").substring(1))));
				y += 60;
				if(y >= 600){
					y = 50;
					x += 60;
				}
			}

			for(int j=0; j< links.size(); j++) {
				characters.get(i-1).get(links.getJSONObject(j).getInt("source")).addTarget(characters.get(i-1).get(links.getJSONObject(j).getInt("target")));
			}
		}
	}

	public void keyPressed() {
		if(key >= '1' && key <= '7' ) {
			curepi = (key-'1');
		}
	}
	
	public void mousePressed(){
		if(!selected){
			for(int i = 0 ; i < characters.get(curepi - 1).size() ; i++){
				Character ch  = characters.get(curepi - 1).get(i);
				if(mouseX > ch.cur_x - ch.radius && mouseX < ch.cur_x + ch.radius){
					if(mouseY > ch.cur_y - ch.radius && mouseY < ch.cur_y + ch.radius){
						selected = true;
						grabbed = ch;
					}
				}
			}
		}
	}
	
	public void mouseReleased(){
		//curepi++;
		if(selected){
			if(net.onCircle(mouseX, mouseY)){
				net.addCharacter(grabbed);
			}else{
				Ani.to(grabbed, (float)0.8, "cur_x", grabbed.anchor_x, Ani.LINEAR);
				Ani.to(grabbed, (float)0.8, "cur_y", grabbed.anchor_y, Ani.CUBIC_IN_OUT);
			}
			grabbed = null;
			selected = false;
		}
	}
	
	public void mouseDragged(){
		if(selected){
			grabbed.cur_x = mouseX;
			grabbed.cur_y = mouseY;
		}
	}

}