package main.java;

import java.util.ArrayList;
import java.util.Random;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;
import controlP5.*;

/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")
public class MainApplet extends PApplet {
	private String path = "main/resources/";
	private String file = "starwars-episode-1-interactions.json";
	private String address = path.concat(file);
	
	JSONObject data;
	JSONArray nodes, links;
	private ArrayList<Character> characters;
	private ControlP5 cp5; 
	private int epsNum = 1;
	private int num_in_great_circle = 0;
	private float circle_x, circle_y, radius;
	private final static int width = 1200, height = 650;
	
	public void setup() {

		size(width, height);
		circle_x = width / 2;
		circle_y = height / 2;
		radius = (width-50)/2;
		background(255);
		//text("Star Wars 1", width/3, 5);
		cp5 = new ControlP5(this);
		
		cp5.addButton("addAll")
			.setLabel("ADD ALL")
			.setColorBackground(139)
			.setPosition(width*3/4, height/20)
			.setSize(100, 30);
		cp5.addButton("clear")
			.setLabel("CLEAR")
			.setColorBackground(139)
			.setPosition(width*3/4, 80+height/20)
			.setSize(100, 30);
		
		characters = new ArrayList<Character>();
		smooth();
		loadData();
		
	}

	public void draw() {
		background(255);
		stroke(48, 139, 206);
		//strokeWeight(5); 
		text("Star Wars %d", width/3, 5, epsNum);
		ellipse(circle_x, circle_y, radius, radius);
		
		for(Character character:characters) {
			fill(0, 102, 153);
			character.display();
		}
		
		float src_x, src_y, dest_x, dest_y;
		for(int i=0; i<characters.size(); i++) {
			src_x = characters.get(i).x;
			src_y = characters.get(i).y;
			
			for(int j=0; j<characters.get(i).getTargets().size(); j++) {
				dest_x = characters.get(i).getTargets().get(j).x;
				dest_y = characters.get(i).getTargets().get(j).y;
				fill(255);
				line(src_x, src_y, dest_x, dest_y);
			}
		}
	}
	
	public void keyPressed() {
		background(255);
		
		Random random = new Random();
		epsNum = random.nextInt(8);
		while(epsNum==0){
			epsNum = random.nextInt(8);
		}
		String num = "";
		num = num.valueOf(epsNum);
		file = "starwars-episode-";
		file = file.concat(num);
		file = file.concat("-interactions.json");
		
	}
	
	public void addInCircle() {
		
	}
	
	public void mousePressed() {
		for(Character character:characters) {
			if (dist(character.x, character.y, mouseX, mouseY) <= character.radius) {
				character.x = mouseX;
				character.y = mouseY;
			}
		}
	}
	
	public void mouseDragger() {

	}
	
	private void loadData(){
		int x=0, y=0;
		data = loadJSONObject(address);
		nodes = data.getJSONArray("nodes");
		for(int i=0; i<nodes.size(); i++){
			JSONObject node = nodes.getJSONObject(i);
			
			String name = node.getString("name");
			int value = node.getInt("value");
			
			Character input = new Character(this, name, 10+(x+5)*60, 10+(y+5)*60);
			x = (x+1)%3;
			y = (y+1)%9;
			this.characters.add(input);
		}
		
		links = data.getJSONArray("links");
		for(int j=0; j<links.size(); j++){
			JSONObject link = links.getJSONObject(j);
			
			int source = link.getInt("source");
			int target = link.getInt("target");
			int value = link.getInt("value");
			
			characters.get(source).addTarget( characters.get(target) );
		}
	}

}