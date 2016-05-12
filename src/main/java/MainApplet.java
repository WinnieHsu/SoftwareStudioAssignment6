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
public class MainApplet extends PApplet{
	private String path = "main/resources/";
	private String file = "starwars-episode-1-interactions.json";
	
	JSONObject data;
	JSONArray nodes, links;
	private ArrayList<Character> characters;
	private ControlP5 cp5; 
	
	
	private final static int width = 1200, height = 650;
	
	public void setup() {

		size(width, height);
		
		
		characters = new ArrayList<Character>();
		smooth();
		loadData();
		
	}

	public void draw() {
		background(255);
		for(Character character:characters){
			fill(0);
			fill(0, 102, 153);
			character.display();
		}
		
		float src_x, src_y, dest_x, dest_y;
		for(int i=0; i<characters.size(); i++){
			src_x = characters.get(i).x;
			src_y = characters.get(i).y;
			
			for(int j=0; j<characters.get(i).getTargets().size(); j++){
				dest_x = characters.get(i).getTargets().get(j).x;
				dest_y = characters.get(i).getTargets().get(j).y;
				fill(255);
				line(src_x, src_y, dest_x, dest_y);
			}
		}
	}
	
	public void keyPressed(){
		background(255);
		
		for(int i=0; i<characters.size(); i++){
			Random x = new Random();
			Random y = new Random();
			
			characters.get(i).x = x.nextFloat()*800;
			characters.get(i).y = y.nextFloat()*800;
		}
	}

	private void loadData(){
		/*int j=0;
		String num = "";
		while(j<7){
		j++;
		num = num.valueOf(j);
		file = "starwars-episode-";
		file = file.concat(num);
		file = file.concat("-interactions.json");*/
		data = loadJSONObject(file);
		nodes = data.getJSONArray("nodes");
		for(int i=0; i<nodes.size(); i++){
			JSONObject node = nodes.getJSONObject(i);
			
			String name = node.getString("name");
			int value = node.getInt("value");
			//String colour = node.getString("colour");
			
			//System.out.println(i+", "+name+", "+value+", "+colour);
			
			Random x = new Random();
			Random y = new Random();
			
			Character input = new Character(this, name, x.nextFloat()*800, y.nextFloat()*800);
			this.characters.add(input);
		}
		
		links = data.getJSONArray("links");
		for(int j=0; j<links.size(); j++){
			JSONObject link = links.getJSONObject(j);
			
			int source = link.getInt("source");
			int target = link.getInt("target");
			int value = link.getInt("value");
			
			//System.out.println(j+", "+source+", "+target+", "+value);
			//System.out.println("target is "+characters.get(source));
			
			characters.get(source).addTarget( characters.get(target) );
		}
		//}
	}

}
