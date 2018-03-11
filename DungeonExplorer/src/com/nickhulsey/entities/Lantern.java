package com.nickhulsey.entities;

import java.awt.Graphics2D;
import java.util.Random;

import com.nickhulsey.render.Light;
import com.nickhulsey.render.LightHandler;
import com.nickhulsey.render.SpriteHandler;
import com.nickhulsey.start.Game;

public class Lantern extends Entity{

	private Light light;
	public Lantern(int x, int y, int w, int h, Room room, Game game) {
		super(x, y, w, h, ObjectID.VANITY, room, game);
		sprite = SpriteHandler.getSpriteData("lantern");
		
		Random r = new Random();
		light = new Light(0,0 ,(w) * (r.nextInt(13) + 12), 70);
		LightHandler.addLight(light);
	}

	public void tick() {
		light.center(this, game);
		if(game.player.room == room)light.draw = true;
		else light.draw = false;
	}

	public void render(Graphics2D g) {
		sprite.draw(x, y, g);
	}

}
