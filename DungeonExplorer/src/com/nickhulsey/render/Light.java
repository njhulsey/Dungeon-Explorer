package com.nickhulsey.render;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.nickhulsey.entities.Entity;
import com.nickhulsey.start.Game;
import com.nickhulsey.start.Main;

public class Light {
	
	private BufferedImage image;
	private int x,y,brightness;
	public int radius;
	public boolean draw = false;
	
	public Light(int x, int y, int radius, int brightness){
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.brightness = brightness;
		image = new BufferedImage(radius * 2, radius * 2, BufferedImage.TYPE_INT_ARGB);
		Graphics g2 = (Graphics2D) image.getGraphics();
		
		
		int step = 15;
		int numSteps = radius / step;
		g2.setColor(new Color(0,0,0,brightness));
		for(int i = 1; i < numSteps; i++){
				g2.fillOval(radius - i * step, radius - i * step, i * step * 2, i * step * 2);
			}	
		}
	
	public void render(Graphics2D g){
		if(draw)g.drawImage(image, x, y, null);
	}
	
	public void position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void center(Entity e, Game game){
		x = e.x - radius + e.w / 2 - game.camera.x;
		y = e.y - radius + e.h /2 - game.camera.y;
	}
	
}
