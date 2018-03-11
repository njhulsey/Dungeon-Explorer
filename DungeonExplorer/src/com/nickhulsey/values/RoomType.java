package com.nickhulsey.values;

import java.awt.Color;
import java.util.Random;

import com.nickhulsey.enemies.Enemy;
import com.nickhulsey.entities.Room;
import com.nickhulsey.render.Sprite;
import com.nickhulsey.render.SpriteHandler;
import com.nickhulsey.start.Main;

public enum RoomType {
	//type for sprites
	//difficulty for monsters
	//color for minimap
	
	WOOD("wood_",0,new Color(107,69,36,0)),
	YARD("yard_",1,new Color(67,133,40,0)),
	STONE("stone_",2,new Color(85,85,85,0)),
	BLUE("blue_",3,new Color(72,110,142,0)),
	PURPLE("purple_",4, new Color(122,52,107,0)),
	RED("red_",5,new Color(128,24,24,0));
	
	public String type;
	public int difficulty;
	public Color color;
	RoomType(String type, int difficulty, Color color){
		this.type = type;
		this.color = color;
		this.difficulty = difficulty;
	}
	
	public Sprite getWallData(){
		Random r = new Random();
		if(r.nextInt(10) == 0)return SpriteHandler.getSpriteData(type + "special_wall"); 
		return SpriteHandler.getSpriteData(type + "wall");
	}

	public Sprite getFloorData(){
		Random r = new Random();
		if(r.nextInt(15) == 0)return SpriteHandler.getSpriteData(type + "special_floor"); 
		return SpriteHandler.getSpriteData(type + "floor");
	}
	
}