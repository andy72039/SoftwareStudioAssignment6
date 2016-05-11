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
	private ArrayList<Link> targets;
	private int value;
	private int color;
	public boolean settled;
	
	public int radius = 20;

	public Character(PApplet parent, String name, float x, float y, int value, int color) {
		this.targets = new ArrayList<Link>();
		//anchor �ΨӬ����I���쥻��m
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
	//Link class �s��Character �Mvalue��targets�ϥ�
	class Link {
		Character character;
		int value;
		Link(Character character, int value) {
			this.character = character;
			this.value = value;
		}
		Character getCharacter() {
			return this.character;
		}
		int getValue() {
			return this.value;
		}
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

	//�N����[�Jtargets��	
	public void addTarget(Character character, int value) {
		Link link = new Link(character, value);
		targets.add(link);
	}
	
	//��ܻP�ۤv�������⪺�s��
	public void displayLinks() {
		if(settled){
			Character ch_t;
			int value;
			for(int i=0; i<targets.size(); i++) {
				ch_t = targets.get(i).getCharacter();
				value = targets.get(i).getValue();
				if(ch_t.settled){
					parent.strokeWeight(value*2); //�H�s�u���ʲӨӪ�ܨ�̤������j�z���Y
					parent.line(cur_x, cur_y, ch_t.cur_x, ch_t.cur_y);
					parent.strokeWeight(0);
				}
				//parent.cur
			}
		}
	}
}
