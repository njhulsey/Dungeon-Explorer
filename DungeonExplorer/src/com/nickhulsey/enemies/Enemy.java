package com.nickhulsey.enemies;

import java.awt.Color;
import java.awt.Graphics2D;

import com.nickhulsey.entities.Entity;
import com.nickhulsey.entities.Room;
import com.nickhulsey.gui.HealthBar;
import com.nickhulsey.start.Game;
import com.nickhulsey.values.EnemyType;

public class Enemy extends Entity {

	public EnemyType enemyType;
	public Entity follow;
	
	public Enemy(int x, int y, int w, int h, Entity follow, EnemyType enemyType, Room room, Game game) {
		super(x, y, w, h, ObjectID.ENEMY, room, game);
		this.enemyType = enemyType;
		this.follow = follow;
		sprite = enemyType.spriteRight;
		init();
	}
	
	public Enemy(int x, int y, int w, int h, ObjectID id, EnemyType enemyType, Room room, Game game) {
		super(x, y, w, h, id, room, game);
		this.enemyType = enemyType;
		sprite = enemyType.spriteRight;
		follow = null;
		init();
	}
	
	private void init(){
		health = new HealthBar(x,y,250,50,null,new Color(255,0,0,255),null);
		health.addToShadowList();
	}
	
	public void tick() {
		followTarget();
		getCurrentRoom();
		health.updatePosition(x - game.camera.x, y - game.camera.y - 15);
		
		if(health.health == 0)game.enemies.remove(this);
		
		
		for(int i = 0; i < game.entities.size(); i++){
			if(collisionAll(game.entities.get(i), 0) && game.entities.get(i).id == ObjectID.PLAYER_BULLET){
				int damage = game.player.damage - enemyType.defence;
				if(damage < 0) damage = 1;
				health.damage(damage);
				game.entities.remove(i);
			}
		}
		
	}

	public void followTarget() {
		
		if(collisionLeft(follow))
			x += enemyType.speed * 15;
		if(collisionRight(follow))
			x -= enemyType.speed * 15;
		if(collisionUp(follow))
			y += enemyType.speed * 15;
		if (collisionDown(follow))
			y -= enemyType.speed * 15;
		
		if (collisionAll(follow,enemyType.vision) && !collisionAll(follow, 1) && room.visited) {
			// move right
			if (follow.x - x > 0) {
				boolean moveRight = true;
				if(enemyType.collision)
					for (Entity e : room.tiles)
						if (collisionRight(e) && e.id.solid) {
							moveRight = false;
							break;
						}
				if (moveRight){
					sprite = enemyType.spriteRight;
					x += enemyType.speed;
				}
				// else move left
			} else if(follow.x - x < 0){
				boolean moveLeft = true;
				if(enemyType.collision)
					for (Entity e : room.tiles)
						if (collisionLeft(e)&& e.id.solid) {
							moveLeft = false;
							break;
						}
				if (moveLeft){
					sprite = enemyType.spriteLeft;
					x -= enemyType.speed;
				}
			}
			
			// if move down
			if (follow.y - y > 0) {
				boolean moveDown = true;
				if(enemyType.collision)
					for (Entity e : room.tiles)
						if (collisionDown(e)&& e.id.solid) {
							moveDown = false;
							break;
						}
				if (moveDown)
					y += enemyType.speed;
				
				// else move up
			} else if(follow.y - y < 0){
				boolean moveUp = true;
				if(enemyType.collision)
					for (Entity e : room.tiles)
						if (collisionUp(e)&& e.id.solid) {
							moveUp = false;
							break;
						}
				if (moveUp)
					y -= enemyType.speed;
				
			}
			
		}
	}

	public void render(Graphics2D g) {
		if(room.visited)sprite.draw(x, y, g);
	}

}