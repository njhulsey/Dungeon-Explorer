package com.nickhulsey.input;
import com.nickhulsey.entities.Entity;
import com.nickhulsey.start.Main;

public class Camera {
	private Entity follow;
	public int x, y = 0;
	
	private int xMax;
	private int xMin = 0;
	
	private int yMax; 
	private int yMin = 0;
	
	public Camera(){
	}	
	
	public void tick(){
		xMax = Main.GAME_WIDTH * Main.SCALE - Main.HEIGHT;
		yMax = Main.GAME_HEIGHT * Main.SCALE - Main.WIDTH;
		x = follow.x - Main.WIDTH / 2;
		y = follow.y - Main.HEIGHT / 2;
		
		if(x > xMax)x = xMax;
		if(x < xMin)x = xMin;
		if(y > yMax)y = yMax;
		if(y < yMin)y = yMin;
		
	}
	
	public void setFollow(Entity follow){
		this.follow = follow;
	}
		
}
