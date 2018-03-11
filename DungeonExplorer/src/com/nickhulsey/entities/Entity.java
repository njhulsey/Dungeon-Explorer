package com.nickhulsey.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import com.nickhulsey.gui.HealthBar;
import com.nickhulsey.render.Sprite;
import com.nickhulsey.render.SpriteHandler;
import com.nickhulsey.start.Game;
import com.nickhulsey.start.Main;

public abstract class Entity{

	public static enum ObjectID {
		
		WALL(true), FLOOR(false), PLAYER(false), VANITY(false), ENEMY(false), PLAYER_BULLET(false), ENEMY_BULLET(false);
		
		public boolean solid;
		
		ObjectID(boolean solid){
			this.solid = solid;
		}
		
	}
	
	
	public int x, y, w, h;
	protected Game game;
	protected Sprite sprite;
	public ObjectID id;
	public Room room;
	public HealthBar health;
	public int defence;
	
	public Entity(int x, int y, int w, int h, ObjectID id, Room room, Game game){
		this.x = x * Main.SCALE;
		this.y = y * Main.SCALE;
		this.w = w * Main.SCALE;
		this.h = h * Main.SCALE;
		this.id = id;
		this.room = room;
		this.game = game;
		sprite = SpriteHandler.nullSprite;
	}
	
	public abstract void tick();
	public abstract void render(Graphics2D g);

	public void randomPosition(){
		//set position to a random room
		Random r = new Random();
		x = room.x + r.nextInt(room.size - Main.TILE_SIZE * 2) + Main.TILE_SIZE;
		y = room.y + r.nextInt(room.size - Main.TILE_SIZE * 2) + Main.TILE_SIZE;
		x *= Main.SCALE;
		y *= Main.SCALE;
		snapToGrid();
		
		//if we are colliding with any of the walls then recall this method
		for(int i = 0; i < room.tiles.size(); i++)
			if(collisionAll(room.tiles.get(i),1) && room.tiles.get(i).id == ObjectID.WALL || room.tiles.get(i).id == ObjectID.ENEMY)
				randomPosition();
		
	}
	
	
	public boolean collisionLeft(Entity e){
		Rectangle coll = new Rectangle(x - 1, y, w, h);
		Rectangle Ecoll = new Rectangle(e.x,e.y,e.w,e.h);
		if(coll.intersects(Ecoll)){
			coll = null;
			Ecoll = null;
			return true;
		}
		coll = null;
		Ecoll = null;
		return false;
	}
	public boolean collisionRight(Entity e){
		Rectangle coll = new Rectangle(x + w, y, 1, h);
		Rectangle Ecoll = new Rectangle(e.x,e.y,e.w,e.h);
		if(coll.intersects(Ecoll)){
			coll = null;
			Ecoll = null;
			return true;
		}
		coll = null;
		Ecoll = null;
		return false;
	}
	
	public boolean collisionUp(Entity e){
		Rectangle coll = new Rectangle(x, y - 1, w, 1);
		Rectangle Ecoll = new Rectangle(e.x,e.y,e.w,e.h);
		if(coll.intersects(Ecoll)){
			coll = null;
			Ecoll = null;
			return true;
		}
		coll = null;
		Ecoll = null;
		return false;
	}
	public boolean collisionDown(Entity e){
		Rectangle coll = new Rectangle(x, y + h, w, 1);
		Rectangle Ecoll = new Rectangle(e.x,e.y,e.w,e.h);
		if(coll.intersects(Ecoll)){
			coll = null;
			Ecoll = null;
			return true;
		}
		coll = null;
		Ecoll = null;
		return false;
	}
	
	public boolean collisionAll(Entity e, int extraRadius){
		Rectangle coll = new Rectangle(x - extraRadius, y - extraRadius, w + (extraRadius * 2), h + (extraRadius * 2));
		Rectangle Ecoll = new Rectangle(e.x,e.y,e.w,e.h);
		if(coll.intersects(Ecoll)){
			coll = null;
			Ecoll = null;
			return true;
		}
		coll = null;
		Ecoll = null;
		return false;
	}

	public void snapToGrid(){
		while(x % Main.TILE_SIZE * Main.SCALE > 0){
			x++;
		}
		while(y % Main.TILE_SIZE * Main.SCALE > 0){
			y++;
		}
	}
	
	public void getCurrentRoom(){
		for(Room r: game.rooms)
			if(r.inRoom(this)){
				room = r;
				return;
			}
	}
	
}