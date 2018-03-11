package com.nickhulsey.start;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import com.nickhulsey.enemies.Enemy;
import com.nickhulsey.entities.Entity;
import com.nickhulsey.entities.Player;
import com.nickhulsey.entities.Room;
import com.nickhulsey.gui.GuiHandler;
import com.nickhulsey.input.Camera;
import com.nickhulsey.input.InputHandler;
import com.nickhulsey.input.MapHandler;
import com.nickhulsey.render.LightHandler;
import com.nickhulsey.render.SpriteHandler;
import com.nickhulsey.values.PlayerType;
import com.nickhulsey.values.RoomType;

public class Game {
	public InputHandler input;
	public Main main;
	public SpriteHandler spriteHandler;
	public LightHandler lightHandler;
	public GuiHandler guiHandler;
	public MapHandler map;
	
	public Camera camera;
	public Player player;
	
	public ArrayList<Room> rooms;
	public ArrayList<Enemy> enemies;
	public ArrayList<Entity> entities;
	
	//TODO: MOUSE SCALING
	//FIX RENDERING SHADOWS
	
	public Game(InputHandler input, Main main){
		this.input = input;
		this.main = main;
		spriteHandler = new SpriteHandler();
	}
	
	public void init(){
		map = new MapHandler(this);
		lightHandler = new LightHandler(this);
		enemies = new ArrayList<Enemy>();
		entities = new ArrayList<Entity>();
		
		camera = new Camera();
		guiHandler = new GuiHandler(this);
		rooms = new ArrayList<Room>();
		
		player = new Player(0, 0, Main.TILE_SIZE, Main.TILE_SIZE,PlayerType.WARRIOR, null, this);
		
		//place rooms accordingly 
		for(int x = 0; x < Main.MAP_WIDTH; x ++){
			for(int y = 0; y < Main.MAP_HEIGHT; y++){
				rooms.add(new Room(x * Main.ROOM_SIZE * Main.TILE_SIZE, y * Main.ROOM_SIZE * Main.TILE_SIZE, this));
			}
		}
		
		
		for(Room r: rooms){
			if(r.roomType == RoomType.WOOD){
				player.room = r;
				player.randomPosition();
				break;
			}
		}
		
		map.printData();
		
	}

	public void tick(){
		player.tick();
		for(Room r: rooms)r.tick();
	
		for(int i = 0; i < enemies.size(); i++)
			enemies.get(i).tick();
		for(int i = 0; i < entities.size(); i++)
			entities.get(i).tick();
	
		//update the lights and minimap
		map.updateMap();

	}
	
	public void draw(Graphics2D g){
		camera.tick();
		g.translate(-camera.x, -camera.y);
		for(Room r: rooms)r.render(g);
		for(Entity e: entities)e.render(g);
		for(Enemy e: enemies)e.render(g);
		player.render(g);
		//GUI
		guiHandler.addShadowGui();
		guiHandler.render(g);
		lightHandler.render(g);
		map.drawMap(g);
		guiHandler.addGui();
		guiHandler.render(g);
	}
	
}