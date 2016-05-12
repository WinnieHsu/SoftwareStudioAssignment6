package main.java;

import java.util.ArrayList;
import java.util.Random;
import processing.core.PApplet;
import processing.event.MouseEvent;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	private MainApplet parent;
	public float x, y, radius;
	private String name;
	//private PApplet parent;
	private ArrayList<Character> targets;

	public Character(MainApplet parent, String name, float x, float y){
		
		this.parent = parent;
		this.name = name;
		this.x = x;
		this.y = y;
		targets = new ArrayList<Character>();
	}

	public void display(){
		
		parent.ellipse(this.x, this.y, 50, 20);
		parent.text(this.name, this.x-15, this.y-15);
	}
	
	public void addTarget(Character target){
		this.targets.add(target);
	}
	
	public ArrayList<Character> getTargets(){
		return this.targets;
	}
}
