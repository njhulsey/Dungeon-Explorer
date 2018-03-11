package com.nickhulsey.gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.nickhulsey.render.Sprite;
import com.nickhulsey.render.SpriteHandler;

public abstract class Gui {


	public static enum GuiType {
	
		DEFENCE("defence_"),DAMAGE("damage_"),HEALTH("health_");
		
		public Sprite sprite;
		
		GuiType(String type){
			sprite = SpriteHandler.getSpriteData(type + "gui");
			
		}
		
	}
	
	public BufferedImage image;
	public int x,y,w,h;
	public GuiType guiType;
	
	public Gui(int x, int y, int w, int h,GuiType guiType){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.guiType = guiType;
	}
	
	public void addToShadowList(){
		GuiHandler.guiShadowList.add(this);
	}
	
	public void addToGuiList(){
		GuiHandler.guiList.add(this);
	}
	
	
	public abstract void render(Graphics2D g);
	
	public void updatePosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
}