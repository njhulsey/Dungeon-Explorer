package com.nickhulsey.entities;

import java.awt.Graphics2D;

import com.nickhulsey.start.Game;

public class Floor extends Entity{

	public Floor(int x, int y, int w, int h, Room room, Game game) {
		super(x, y, w, h, ObjectID.FLOOR, room, game);
		sprite = room.roomType.getFloorData();
	}

	public void tick() {
		
	}

	public void render(Graphics2D g) {
		sprite.draw(x, y, g);
	
	}
	
	

}
