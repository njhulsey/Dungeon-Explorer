package com.nickhulsey.render;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.nickhulsey.start.Game;
import com.nickhulsey.start.Main;

public class LightHandler {
	private Game game;
	private BufferedImage lightMap;
	private static ArrayList<Light> lights;
	private Graphics2D g;
	
	//apparently you can draw onto a buffered image using graphics objects.....didnt know
	/**
	 * how to use light objects
	 * 1. create light object within object that you want to emit light
	 * 2. add the light to the lights array
	 * 3. update the light every tick so it can stay center with the object (call the center method)
	 * 4. make sure the light can draw. It is set to false by default 
	 * 
	 * video: https://www.youtube.com/watch?v=KjfbzDGrRfI
	 */
	
	public LightHandler(Game game){
		this.game = game;
		lights = new ArrayList<Light>();
		lightMap = new BufferedImage(Main.WIDTH, Main.HEIGHT, BufferedImage.TYPE_INT_ARGB);
	}

	public void update(){
		g = (Graphics2D) lightMap.getGraphics();
		g.setColor(new Color(0,0,0,255));
		g.fillRect(0, 0, lightMap.getWidth(), lightMap.getHeight());
		g.setComposite(AlphaComposite.DstOut);
		
		for(int i = 0; i < lights.size(); i++){
			lights.get(i).render(g);
		}
		g.dispose();
	}
	
	public void render(Graphics2D g){
		update();
		g.drawImage(lightMap, game.camera.x, game.camera.y,null);
		
	}
	public static void addLight(Light light){
		lights.add(light);
	}
}
