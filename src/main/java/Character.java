package main.java;

import java.util.ArrayList;
import processing.core.PApplet;


/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	public float x, y, radius=30;
	private float initial_x, initial_y;
	private MainApplet parent;
	private MainApplet border;
	private String name;
	private ArrayList<Character> targets;
	private boolean mouse_in = false;
	
	public Character(MainApplet parent, String name, float x, float y) {
		this.parent = parent;
		this.name = name; 
		this.initial_x = x;
		this.initial_y = y;
		this.x = x;
		this.y = y;
		targets = new ArrayList<Character>();
	}
	
	public void display() {
		parent.fill(123, 0, 200);
		parent.ellipse(x, y, radius, radius);
		if (mouse_in == true) {
			border.stroke(48, 5, 180);
			border.ellipse(x, y, radius, radius);
			parent.text(name, x, y);
		}
	}
	
	public void addTarget(Character target) {
		this.targets.add(target);
	}
	
	public ArrayList<Character> getTargets() {
		return this.targets;
	}
	
	public String getName() {
		return name;
	}
	
	public void setMouseIn() {
		mouse_in = true;
	}
	
}