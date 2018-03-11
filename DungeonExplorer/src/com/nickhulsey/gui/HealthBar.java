package com.nickhulsey.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import com.nickhulsey.start.Main;

public class HealthBar extends Gui {

	public Color back, front, border;

	public int maxHealth = 100;
	public int health = maxHealth;
	public HealthBar(int x, int y, int w, int h, Color back, Color front, Color border) {
		super(x / Main.SCALE, y / Main.SCALE, w / Main.SCALE, h/ Main.SCALE,GuiType.HEALTH);
		this.health = maxHealth;
		this.front = front;
		this.back = back;
		this.border = border;
		
		//if it equals null then set it to transparent
		if(front == null)this.front = new Color(0,0,0,0);
		if(back == null) this.back = new Color(0,0,0,0);
		if(border == null)this.border = new Color(0,0,0,0);
		
	}

	public void damage(int damage){
		health -= damage;
		if(health < 0)health = 0;
	}
	
	public void heal(int health){
		this.health += health;
		if(health > maxHealth) health = maxHealth;
	}

	public void render(Graphics2D g) {
		g.setColor(back);
		g.fillRect(x, y, (int)((health / (maxHealth * 1.0)) * w), h);
		g.setColor(front);
		g.fillRect(x, y, (int)((health / (maxHealth * 1.0)) * w), h);
		g.setColor(border);
		g.setStroke(new BasicStroke(3));
		g.drawRect(x, y, (int)((health / (maxHealth * 1.0)) * w), h);

	}

}
