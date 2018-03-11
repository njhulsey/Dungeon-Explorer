package com.nickhulsey.values;

import com.nickhulsey.render.Sprite;
import com.nickhulsey.render.SpriteHandler;

public enum BulletType {
				//speed, life == distance
	ARROW("arrow_", 12, 45), DAGGER("dagger_", 9, 15), BOLT("bolt_",5, 30);
	
	String type;
	Sprite sprite;
	public int speed;
	public int life;
	BulletType(String type, int speed, int life){
		this.type = type;
		this.speed = speed;
		this.life = life;
		sprite = SpriteHandler.getSpriteData(type + "bullet");
	}
	
	public Sprite getSprite(){
		return new Sprite(sprite.data, sprite.name);
	}
	
}