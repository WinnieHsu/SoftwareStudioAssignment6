package main.java;

import java.util.ArrayList;
import processing.core.PApplet;


/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	public float x, y, radius=15;
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
		parent.ellipse(x, y, 2*radius, 2*radius);
		if (mouse_in == true) {
			radius = (float) (1.2 * 15);
			parent.fill(124);
			parent.rect(x, y-12, 70, 15);
			parent.fill(29);
			parent.text(name, x, y);
			mouse_in = false;
		} else {
			radius = 15;
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
	
	public float getInitialX() {
		return initial_x;
	}
	
	public float getInitialY() {
		return initial_y;
	}
	
}