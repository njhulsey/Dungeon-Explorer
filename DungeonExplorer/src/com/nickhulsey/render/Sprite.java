package com.nickhulsey.render;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.nickhulsey.entities.Entity;
import com.nickhulsey.start.Main;

public class Sprite {
	
	public String name;
	public BufferedImage data;
	public BufferedImage original;
	
	public Sprite(BufferedImage data, String name){
		this.name = name;
		this.data = data;
		original = data;
	}
	
	public void fixSize(){
		//scales the original image to scale
		data = new BufferedImage(data.getWidth() * Main.SCALE, data.getHeight() * Main.SCALE, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D)  data.getGraphics();
		g.drawImage(original, 0, 0, data.getWidth(),data.getHeight(),null);
		original = this.data;
	}
	
	public void rotate(double degrees){
		BufferedImage blank = new BufferedImage(original.getWidth(), original.getHeight(),BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) blank.getGraphics();
		g.rotate(Math.toRadians(degrees), data.getWidth() / 2, data.getHeight() / 2);
		g.drawImage(original, 0, 0, null);
		data = blank;
	}
	
	public void pointTowards(Entity e, int x, int y){
		rotate(Math.toDegrees(Math.atan2((y) - (e.y + e.h / 2), (x)- (e.x +  e.w / 2))));
	}
	
	public void draw(int x, int y, Graphics2D g){
		g.drawImage(data,x,y,null);
	}

}