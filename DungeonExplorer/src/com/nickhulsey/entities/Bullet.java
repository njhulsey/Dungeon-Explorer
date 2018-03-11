package com.nickhulsey.entities;

import java.awt.Graphics2D;

import com.nickhulsey.start.Game;
import com.nickhulsey.values.BulletType;

public class Bullet extends Entity{

	private int count,life;
	float stepX, stepY;
	public BulletType bulletType;
	
	public Bullet(int x, int y, int w, int h, int targetX, int targetY, BulletType bulletType, Room room, Game game) {
		super(x, y, w, h, ObjectID.PLAYER_BULLET, room, game);
		this.bulletType = bulletType;
		this.life = bulletType.life;

		
		float distance = (float)(Math.sqrt(Math.pow(targetX - this.x, 2) + Math.pow(targetY - this.y, 2)));
		stepX = (targetX - this.x) / distance * bulletType.speed;
		stepY = (targetY - this.y) / distance * bulletType.speed;
		
		sprite = bulletType.getSprite();
		sprite.pointTowards(this, targetX, targetY);
	}

	public void tick() {
		this.getCurrentRoom();
		//if we are not colliding with a solid entity
		for(Entity e: room.tiles){
			if(collisionAll(e,0) && e.id.solid)
				game.entities.remove(this);
		}
		
		//are we still alive?
		count ++;
		if(count >= life)game.entities.remove(this);
		x += stepX;
		y += stepY;
	}

	public void render(Graphics2D g) {
		sprite.draw(x, y, g);
		
	}

	
}