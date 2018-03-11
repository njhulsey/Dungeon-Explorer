package com.nickhulsey.entities;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import com.nickhulsey.enemies.Enemy;
import com.nickhulsey.start.Game;
import com.nickhulsey.start.Main;
import com.nickhulsey.values.EnemyType;
import com.nickhulsey.values.RoomType;

public class Room{
	
	public int x, y,size;
	public Game game;
	public RoomType roomType;
	public ArrayList<Entity> tiles;
	public boolean visited = false;

	private int roomSize = Main.ROOM_SIZE;
	private int tileSize = Main.TILE_SIZE;

	public Room(int x, int y, Game game){
		this.x = x;
		this.y = y;
		this.size = roomSize * tileSize;
		this.game = game;
		this.roomType = generateDifficulty(); 
		tiles = new ArrayList<Entity>();
		generateRoom();
	}
	
	public void generateRoom(){
		Random r = new Random();
		int[][] data = game.map.data;
		//where the room is on the data array
		//System.out.println("X: " + this.x / roomSize / tileSize + " Y: " + this.y / roomSize / tileSize); 
		
		for(int y = 0; y < roomSize; y ++){
			for(int x = 0; x < roomSize; x++){
				
				//outprint the current data
				//System.out.print(data[y + (this.y / tileSize)][x + (this.x / tileSize)]);
				
				//is the current tile a background?
				int dataI = data[y + (this.y / tileSize)][x + (this.x / tileSize)];
				if(dataI == 0)
					tiles.add(new Floor(this.x + (x * tileSize), this.y  + (y * tileSize), tileSize, tileSize, this, game));
				
				if(dataI == 1)
					tiles.add(new Wall(this.x + (x * tileSize), this.y + (y * tileSize), tileSize, tileSize, this, game));
				
				if(dataI == 2)
					tiles.add(new Floor(this.x + (x * tileSize), this.y   + (y * tileSize), tileSize, tileSize, this, game));
				
			}
		}
		int lanternAmount = r.nextInt(3) + 1;
		for(int i = 0; i < lanternAmount; i++){
			tiles.add(new Lantern(x  + r.nextInt(size - tileSize * 3) + tileSize, y + r.nextInt(size - tileSize * 3) + tileSize, tileSize, tileSize,this,game));
		}
		int amountOfCreatures = r.nextInt(3) + 3;
		Enemy addTo;
		for(int i = 0; i < amountOfCreatures; i++){
			addTo = new Enemy(0,0,Main.TILE_SIZE,Main.TILE_SIZE,game.player ,EnemyType.SKELETON.randomType(roomType.difficulty), this, game);
			addTo.randomPosition();
			game.enemies.add(addTo);
		}
	
	}
	
	public RoomType generateDifficulty(){
		//needs work
		Random r = new Random();
		if(r.nextInt(3) != 0){
			if(r.nextInt(4) == 0)
				return RoomType.YARD;
			else if(r.nextInt(3) == 0)
				return RoomType.STONE;
			else if(r.nextInt(3) == 0)
				return RoomType.BLUE;
			else if(r.nextInt(3) != 0)
				return RoomType.PURPLE;
			else 
				return RoomType.RED;
		}
		return RoomType.WOOD;
		
	}
	
	public void tick(){
		//if the player is in the room the set visited equal to true and the players current room to this
		if(game.player.x + game.player.w > x * Main.SCALE && game.player.y > y * Main.SCALE && game.player.x < x * Main.SCALE+ size * Main.SCALE && game.player.y + game.player.h < y * Main.SCALE + size * Main.SCALE){
			game.player.room = this;
			visited = true;
		}
		
		for(Entity t: tiles)t.tick();
	}
	
	public boolean inRoom(Entity e){
		if(e.x + e.w > x * Main.SCALE && e.y > y * Main.SCALE &&e.x < x * Main.SCALE+ size * Main.SCALE && e.y + e.h < y * Main.SCALE + size * Main.SCALE)
			return true;
		return false;
	}
	
	public void render(Graphics2D g){
		if(visited)
			for(Entity t: tiles)t.render(g);

	}
}
