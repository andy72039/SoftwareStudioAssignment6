package main.java;

import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	public float anchor_x, anchor_y;
	public float cur_x, cur_y;
	public String name;
	private PApplet parent;
	private ArrayList<Character> targets;
	private int value;
	private int color;
	public boolean settled;
	
	public int radius = 20;

	public Character(PApplet parent, String name, float x, float y, int value, int color) {
		this.targets = new ArrayList<Character>();
		this.anchor_x = x;
		this.anchor_y = y;
		this.name = name;
		this.parent = parent;
		this.value = value;
		this.color = color;
		this.settled = false;
		cur_x = anchor_x;
		cur_y = anchor_y;
	}

	public Character(MainApplet parent){

		this.parent = parent;

	}

	public void display(int mouseX, int mouseY){
		//moveball(mouseX, mouseY);
		parent.fill(color);
		parent.ellipse(cur_x,cur_y,radius*2,radius*2);
		parent.fill(255);
	}
	
	/*private void moveball(int new_x , int new_y){
		if(selected){
			cur_x = new_x;
			cur_y = new_y;
		}else{
			if(cur_x > anchor_x)
				cur_x--;
			if(cur_x < anchor_x)
				cur_x++;
			if(cur_y > anchor_y)
				cur_y--;
			if(cur_y < anchor_y)
				cur_y++;
		}
	}*/
	
	public void addTarget(Character character) {
		targets.add(character);
	}
	
}
