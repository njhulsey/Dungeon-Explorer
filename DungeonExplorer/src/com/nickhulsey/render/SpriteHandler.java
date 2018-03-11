package com.nickhulsey.render;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.nickhulsey.start.Game;
import com.nickhulsey.start.Main;

public class SpriteHandler {

	private BufferedImage tileSheet;
	private static ArrayList<Sprite> sprites;
	private String directory = "/Sprites/Terrain.png";
	public static Sprite nullSprite;
	
	
	public SpriteHandler(){
		sprites = new ArrayList<Sprite>();
		try {
			tileSheet = ImageIO.read(getClass().getResource(directory));
		} catch (IOException e) {e.printStackTrace(); System.out.println("ERROR LOADING TILESHEET");}
		nullSprite = new Sprite(tileSheet.getSubimage(tileSheet.getWidth() - Main.TILE_SIZE, tileSheet.getHeight() - Main.TILE_SIZE, Main.TILE_SIZE, Main.TILE_SIZE), "null");
		loadSprites();
	}

	public static Sprite getSpriteData(String name){
		for(Sprite s: sprites){
			if(s.name.equalsIgnoreCase(name))
				return s;
		}
		return nullSprite;
	}
	
	public void addSprite(int startX, int startY, int width, int height, String name){
		Sprite s = new Sprite(tileSheet.getSubimage(startX, startY, width, height), name);
		s.fixSize();
		sprites.add(s);
	}
	
	public void loadSprites(){
		//LIGHT
		addSprite(0,56,Main.TILE_SIZE,Main.TILE_SIZE,"lantern");
		
		//WOOD
		addSprite(0,0,Main.TILE_SIZE,Main.TILE_SIZE,"wood_floor");
		addSprite(8,0,Main.TILE_SIZE,Main.TILE_SIZE,"wood_special_floor");
		addSprite(16,0,Main.TILE_SIZE,Main.TILE_SIZE,"wood_wall");
		addSprite(24,0,Main.TILE_SIZE,Main.TILE_SIZE,"wood_special_wall");
		
		//STONE
		addSprite(0,8,Main.TILE_SIZE,Main.TILE_SIZE,"stone_floor");
		addSprite(8,8,Main.TILE_SIZE,Main.TILE_SIZE,"stone_special_floor");
		addSprite(16,8,Main.TILE_SIZE,Main.TILE_SIZE,"stone_wall");
		addSprite(24,8,Main.TILE_SIZE,Main.TILE_SIZE,"stone_special_wall");
		
		//BLUE
		addSprite(0,16,Main.TILE_SIZE,Main.TILE_SIZE,"blue_floor");
		addSprite(8,16,Main.TILE_SIZE,Main.TILE_SIZE,"blue_special_floor");
		addSprite(16,16,Main.TILE_SIZE,Main.TILE_SIZE,"blue_wall");
		addSprite(24,16,Main.TILE_SIZE,Main.TILE_SIZE,"blue_special_wall");
		
		//PURPLE
		addSprite(0,24,Main.TILE_SIZE,Main.TILE_SIZE,"purple_floor");
		addSprite(8,24,Main.TILE_SIZE,Main.TILE_SIZE,"purple_special_floor");
		addSprite(16,24,Main.TILE_SIZE,Main.TILE_SIZE,"purple_wall");
		addSprite(24,24,Main.TILE_SIZE,Main.TILE_SIZE,"purple_special_wall");
		
		//RED
		addSprite(0,32,Main.TILE_SIZE,Main.TILE_SIZE,"red_floor");
		addSprite(8,32,Main.TILE_SIZE,Main.TILE_SIZE,"red_special_floor");
		addSprite(16,32,Main.TILE_SIZE,Main.TILE_SIZE,"red_wall");
		addSprite(24,32,Main.TILE_SIZE,Main.TILE_SIZE,"red_special_wall");
		
		//YARD
		addSprite(0,40,Main.TILE_SIZE,Main.TILE_SIZE,"yard_floor");
		addSprite(8,40,Main.TILE_SIZE,Main.TILE_SIZE,"yard_special_floor");
		addSprite(16,40,Main.TILE_SIZE,Main.TILE_SIZE,"yard_wall");
		addSprite(24,40,Main.TILE_SIZE,Main.TILE_SIZE,"yard_special_wall");
		
		
		//******PLAYERS*****
		//mage sprites
		addSprite(128, 0, Main.TILE_SIZE, Main.TILE_SIZE,"mage_right");
		addSprite(136, 0, Main.TILE_SIZE, Main.TILE_SIZE,"mage_left");
		addSprite(144, 0, Main.TILE_SIZE, Main.TILE_SIZE,"mage_front");
		addSprite(152, 0, Main.TILE_SIZE, Main.TILE_SIZE,"mage_back");
		
		//archer sprites
		addSprite(128, 8, Main.TILE_SIZE, Main.TILE_SIZE,"archer_right");
		addSprite(136, 8, Main.TILE_SIZE, Main.TILE_SIZE,"archer_left");
		addSprite(144, 8, Main.TILE_SIZE, Main.TILE_SIZE,"archer_front");
		addSprite(152, 8, Main.TILE_SIZE, Main.TILE_SIZE,"archer_back");
		
		//warrior sprites
		addSprite(128, 16, Main.TILE_SIZE, Main.TILE_SIZE,"warrior_right");
		addSprite(136, 16, Main.TILE_SIZE, Main.TILE_SIZE,"warrior_left");
		addSprite(144, 16, Main.TILE_SIZE, Main.TILE_SIZE,"warrior_front");
		addSprite(152, 16, Main.TILE_SIZE, Main.TILE_SIZE,"warrior_back");
		
		
		//*****ENEMYS*****
		//level 0:
		addSprite(64, 48, Main.TILE_SIZE, Main.TILE_SIZE,"skeleton_left");
		addSprite(72, 48, Main.TILE_SIZE, Main.TILE_SIZE,"skeleton_right");
		
		addSprite(64, 56, Main.TILE_SIZE, Main.TILE_SIZE,"spider_left");
		addSprite(72, 56, Main.TILE_SIZE, Main.TILE_SIZE,"spider_right");
		// level 1:
		addSprite(80, 48, Main.TILE_SIZE, Main.TILE_SIZE,"dog_left");
		addSprite(88, 48, Main.TILE_SIZE, Main.TILE_SIZE,"dog_right");
		
		addSprite(80, 56, Main.TILE_SIZE, Main.TILE_SIZE,"thieve_left");
		addSprite(88, 56, Main.TILE_SIZE, Main.TILE_SIZE,"thieve_right");
		
		//level 2:
		addSprite(96, 48, Main.TILE_SIZE, Main.TILE_SIZE,"dwarf_left");
		addSprite(104, 48, Main.TILE_SIZE, Main.TILE_SIZE,"dwarf_right");
		
		//level 3:
		addSprite(112, 48, Main.TILE_SIZE, Main.TILE_SIZE,"ghost_left");
		addSprite(120, 48, Main.TILE_SIZE, Main.TILE_SIZE,"ghost_right");
		
		addSprite(112, 56, Main.TILE_SIZE, Main.TILE_SIZE,"blackmage_left");
		addSprite(120, 56, Main.TILE_SIZE, Main.TILE_SIZE,"blackmage_right");
		
		//Level 4:
		addSprite(128, 48, Main.TILE_SIZE, Main.TILE_SIZE,"necromancer_left");
		addSprite(136, 48, Main.TILE_SIZE, Main.TILE_SIZE,"necromancer_right");
		
		addSprite(128, 56, Main.TILE_SIZE, Main.TILE_SIZE,"elf_left");
		addSprite(136, 56, Main.TILE_SIZE, Main.TILE_SIZE,"elf_right");
		
		//level 5:
		addSprite(144, 48, Main.TILE_SIZE, Main.TILE_SIZE,"demon_left");
		addSprite(152, 48, Main.TILE_SIZE, Main.TILE_SIZE,"demon_right");
		
		addSprite(144, 56, Main.TILE_SIZE, Main.TILE_SIZE,"skull_left");
		addSprite(152, 56, Main.TILE_SIZE, Main.TILE_SIZE,"skull_right");
		
		//*****BULLETS******
		addSprite(120,0, Main.TILE_SIZE, Main.TILE_SIZE, "bolt_bullet");
		addSprite(120,8, Main.TILE_SIZE, Main.TILE_SIZE, "arrow_bullet");
		addSprite(120,16, Main.TILE_SIZE, Main.TILE_SIZE, "dagger_bullet");
		
		
		//*****GUI******
		addSprite(152,24, Main.TILE_SIZE, Main.TILE_SIZE, "defence_gui");
		addSprite(144,24, Main.TILE_SIZE, Main.TILE_SIZE, "damage_gui");
		
	}
	
}