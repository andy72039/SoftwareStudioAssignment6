package main.java;

import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;

/**
* This class is used for the visualization of the network.
* Depending on your implementation, you might not need to use this class or create a class on your own.
* I used the class to draw the circle and re-arrange nodes and links.
* You will need to declare other variables.
*/
public class Network {
	
	private PApplet parent;
	private int circle_x = 600, circle_y = 350, circle_r = 250;
	private ArrayList<Character> characters;

	public Network(PApplet parent){

		this.parent = parent;
		characters = new ArrayList<Character>();
	}

	public void display(){
		//畫出中央的圓圈
		if(onCircle(parent.mouseX, parent.mouseY))
			parent.strokeWeight(20);
		else
			parent.strokeWeight(10);
		parent.stroke(Color.orange.getRGB());
		parent.ellipse(circle_x, circle_y, circle_r*2, circle_r*2);
		parent.strokeWeight(0);
	}

	//判斷座標是否位於圓的邊上
	public boolean onCircle(float x, float y){
		float x2 = (x - circle_x)*(x - circle_x);
		float y2 = (y - circle_y)*(y - circle_y);
		if(x2 + y2 < Math.pow(circle_r + 10, 2) && x2 + y2 > Math.pow(circle_r - 10, 2))
			return true;
		else
			return false;
	}
	
	//將在圓上的點加入characters
	public void addToCircle(Character ch){
		characters.add(ch);
		ch.settled = true;
		settleClient();
	}
	
	//移除圓上的點
	public void removeFromCircle(Character ch){
		characters.remove(ch);
		ch.settled = false;
		settleClient();
	}
	
	//將點自動放到圓的邊上
	public void settleClient(){
		int n = characters.size();
		Character ch;
		for(int i = 0 ; i < n ; i++){
			ch = characters.get(i);
			ch.cur_x = (float)(circle_r*Math.cos(i * 2*Math.PI/n) + circle_x);
			ch.cur_y = (float)(circle_r*Math.sin(i * 2*Math.PI/n) + circle_y);
		}
	}
}
