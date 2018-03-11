package com.nickhulsey.entities;

import java.awt.Graphics2D;

import com.nickhulsey.start.Game;

public class Wall extends Entity{

	public Wall(int x, int y, int w, int h, Room room, Game game) {
		super(x, y, w, h, ObjectID.WALL, room, game);
		sprite = room.roomType.getWallData();
	}

	public void tick() {
		
	}

	public void render(Graphics2D g) {
		sprite.draw(x, y, g);
	}
	
}
