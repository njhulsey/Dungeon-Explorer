package com.nickhulsey.values;

import com.nickhulsey.render.Sprite;
import com.nickhulsey.render.SpriteHandler;

public enum PlayerType {
	
									//damage, defense
	ARCHER("archer_", BulletType.ARROW, 30, 10), 
	MAGE("mage_", BulletType.BOLT, 35, 15), 
	WARRIOR("warrior_", BulletType.DAGGER, 40, 20);
	
	public Sprite leftSprite;
	public Sprite rightSprite;
	public Sprite frontSprite;
	public Sprite backSprite;
	
	String playerType;
	public BulletType bulletType;
	public int damage;
	public int defence;
	
	PlayerType(String playerType,BulletType bulletType, int damage, int defence){
		this.playerType = playerType;
		this.bulletType = bulletType;
		this.damage = damage;
		this.defence = defence;
		
		leftSprite = SpriteHandler.getSpriteData(playerType + "left");
		rightSprite = SpriteHandler.getSpriteData(playerType + "right");
		frontSprite = SpriteHandler.getSpriteData(playerType + "front");
		backSprite = SpriteHandler.getSpriteData(playerType + "back");
		
	}
	
}