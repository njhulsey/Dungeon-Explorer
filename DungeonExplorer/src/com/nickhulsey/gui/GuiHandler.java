package com.nickhulsey.gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.nickhulsey.start.Game;
import com.nickhulsey.start.Main;

public class GuiHandler {
	
	private static BufferedImage image;
	private Game game;
	public static ArrayList<Gui> guiList;
	public static ArrayList<Gui> guiShadowList;
	
	public GuiHandler(Game game){
		image = new BufferedImage(Main.WIDTH, Main.HEIGHT,BufferedImage.TYPE_INT_ARGB);
		this.game = game;
		guiList = new ArrayList<Gui>();
		guiShadowList = new ArrayList<Gui>();
	}
	
	public void addGui(){
		Graphics2D g = (Graphics2D)image.getGraphics();
		g.setComposite(AlphaComposite.Src);
		for(Gui gui: guiList)gui.render(g);
	}
	
	public void addShadowGui(){
		Graphics2D g = (Graphics2D)image.getGraphics();
		g.setComposite(AlphaComposite.Src);
		for(Gui gui: guiShadowList)gui.render(g);
	}
	
	public void clearGui(){
		Graphics2D g = (Graphics2D) image.getGraphics();
		//clear all pixels by using alphaComposite.clear
		g.setColor(Color.black);
		g.setComposite(AlphaComposite.Clear);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());
		//set it back to normal
		g.setComposite(AlphaComposite.Src);
	}

	public void render(Graphics2D g){
		g.drawImage(image, game.camera.x, game.camera.y, null);
		clearGui();
	}
	
	
}