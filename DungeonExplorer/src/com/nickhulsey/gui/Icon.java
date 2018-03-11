package com.nickhulsey.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Icon extends Gui{

	private Font font;
	public int value;
	private Color color;
	public Icon(int x, int y, int w, int h, int value,GuiType guiType) {
		super(x, y, w, h,guiType);
		font = new Font("Verdana", Font.BOLD, 45);
		this.value = value;
		color = new Color(150,150,150);
	}
	
	
	
	public void render(Graphics2D g) {
		g.drawImage(guiType.sprite.data, x, y, w, h, null);
		g.setFont(font);
		g.setColor(color);
		g.drawString(value+"",x + w + 10, y + (int)(h / 1.2) );
	}
	
}