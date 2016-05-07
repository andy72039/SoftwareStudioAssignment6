package main.java;

import processing.core.PApplet;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	public float x, y, radius;
	private String name;
	private PApplet parent;
	private ArrayList<Character> targets;
	private int value;
	private String color;


	public Character(PApplet parent, String name, float x, float y, int value, String color) {
		this.targets = new ArrayList<Character>();
	this.x = x;
	this.y = y;
	this.name = name;
	this.parent = parent;
	this.value = value;
	this.color = color;
	}

	public void display(){

	}

	public void addTarget(Character character) {
		// TODO Auto-generated method stub
		
	}
	
}
