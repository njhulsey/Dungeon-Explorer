package com.nickhulsey.values;

import java.util.Random;

import com.nickhulsey.render.Sprite;
import com.nickhulsey.render.SpriteHandler;
import com.nickhulsey.start.Main;

public enum EnemyType {
	//		type: damage: speed: vision: defence: collision
	SKELETON("skeleton_", 15, 2, 4,4,true), 
	SPIDER("spider_",15,2,4,4,true),
	
	DOG("dog_", 20,3,5,7,true),
	THIEVE("thieve_",20,2,4,7,true),
	
	DWARF("dwarf_", 23,2,4,10,true),
	
	GHOST("ghost_", 30,2,4,15,false),
	BLACKMAGE("blackmage_",30,2,4,15,true),
	
	NECROMANCER("necromancer_", 35,2,4,19,true),
	ELF("elf_",35,2,4,19,true),
	
	DEMON("demon_",40,2,4,25,true),
	SKULL("skull_",40,2,Main.ROOM_SIZE,25,false);
	
	public String type;
	public boolean walls;
	public int damage,vision,speed,defence;
	public boolean collision;
	
	public Sprite spriteLeft;
	public Sprite spriteRight;
	
	EnemyType(String type, int damage, int speed, int vision, int defence, boolean collision){
		this.type = type;
		this.damage = damage;
		this.speed = speed;
		this.defence = defence;
		//convert to amount of blocks
		this.vision = vision * Main.TILE_SIZE * Main.SCALE;
		this.collision = collision;
		
		spriteLeft = SpriteHandler.getSpriteData(type + "left");
		spriteRight = SpriteHandler.getSpriteData(type + "right");
		
	}
	public EnemyType randomType(int difficulty){
		Random r = new Random();
		if(difficulty == 0){
			switch(r.nextInt(2)){
			case 0: return EnemyType.SKELETON;
			case 1: return EnemyType.SPIDER;
			}
		}
		if(difficulty == 1){
			switch(r.nextInt(2)){
			case 0: return EnemyType.DOG;
			case 1: return EnemyType.THIEVE;
			}
		}
		if(difficulty == 2){
			switch(r.nextInt(2)){
			case 0: return EnemyType.DWARF;
			case 1: return EnemyType.SKELETON;
			}
		}
		if(difficulty == 3){
			switch(r.nextInt(2)){
			case 0: return EnemyType.GHOST;
			case 1: return EnemyType.BLACKMAGE;
			}
		}
		if(difficulty == 4){
			switch(r.nextInt(2)){
			case 0: return EnemyType.NECROMANCER;
			case 1: return EnemyType.ELF;
			}
		}
		if(difficulty == 5){
			switch(r.nextInt(2)){
			case 0: return EnemyType.DEMON;
			case 1: return EnemyType.SKULL;
			}
		}
		return EnemyType.SKELETON;
	}
}