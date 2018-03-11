package com.nickhulsey.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.nickhulsey.enemies.Enemy;
import com.nickhulsey.gui.Gui.GuiType;
import com.nickhulsey.gui.HealthBar;
import com.nickhulsey.gui.Icon;
import com.nickhulsey.render.Light;
import com.nickhulsey.render.LightHandler;
import com.nickhulsey.start.Game;
import com.nickhulsey.start.Main;
import com.nickhulsey.values.PlayerType;

public class Player extends Entity{
	private Light light;
	public PlayerType playerType;
	
	public int damage; 
	private int counter, fireSpeed = 30, speed = 4;
	
	private Icon defenceIcon, damageIcon;
	
	public Player(int x, int y, int w, int h, PlayerType playerType, Room room, Game game) {
		super(x, y, w, h, ObjectID.PLAYER, room, game);
		this.playerType = playerType;
		damage = playerType.damage;
		defence = playerType.defence;
		
		//GUI
		sprite = playerType.frontSprite;
		health = new HealthBar(15,Main.HEIGHT * Main.SCALE - 316, (Main.WIDTH * Main.SCALE) / 3,256,null,new Color(255,0,0,175), new Color(255,255,255,150));
		health.addToGuiList();
		
		defenceIcon = new Icon(health.x + health.w + 50, Main.HEIGHT - 65, Main.TILE_SIZE * Main.SCALE, Main.TILE_SIZE * Main.SCALE, playerType.defence, GuiType.DEFENCE);
		defenceIcon.addToGuiList();

		damageIcon = new Icon(defenceIcon.x + 200, Main.HEIGHT - 65, Main.TILE_SIZE * Main.SCALE, Main.TILE_SIZE * Main.SCALE, playerType.damage, GuiType.DAMAGE);
		damageIcon.addToGuiList();
		
		//create a light object and set it to follow our player
		light = new Light(0, 0, (w * h) * 4, 75);
		light.draw = true;
		LightHandler.addLight(light);
		
		game.camera.setFollow(this);
	}
	
	public void tick() {
		light.center(this, game);
		if(game.input.key[KeyEvent.VK_D])moveRight();
		if(game.input.key[KeyEvent.VK_A])moveLeft();
		if(game.input.key[KeyEvent.VK_W])moveUp();
		if(game.input.key[KeyEvent.VK_S])moveDown();
		counter++;
		
		for(Enemy enemy: game.enemies)
			if(collisionAll(enemy,2)){
				int damage = enemy.enemyType.damage - defence;
				if(damage <= 0) damage = 1;
				health.damage(damage);
			}
		
		//are we firing?
		if(game.input.pressed && counter >= fireSpeed){
			game.entities.add(new Bullet(x / Main.SCALE + (w - 48) / Main.SCALE / 2, y / Main.SCALE + (h - 48) / Main.SCALE / 2 , Main.TILE_SIZE, Main.TILE_SIZE, game.input.MouseX + game.camera.x, game.input.MouseY + game.camera.y,playerType.bulletType,room,game));
			counter = 0;
		}
		//are we dead?
		if(health.health <= 0)System.exit(0);
		
	}
	
	public void moveRight(){
		for(Entity e: room.tiles){
			if(collisionRight(e) && e.id.solid)
				return;
		}
		sprite = playerType.rightSprite;
		x+=speed;
	}
	
	public void moveLeft(){
		for(Entity e: room.tiles){
			if(collisionLeft(e) && e.id.solid)
				return;
		}
		sprite = playerType.leftSprite;
		x-=speed;
	}
	
	public void moveUp(){
		for(Entity e: room.tiles){
			if(collisionUp(e) && e.id.solid)
				return;
		}
		sprite = playerType.backSprite;
		y-=speed; 
	}
	
	public void moveDown(){
		for(Entity e: room.tiles){
			if(collisionDown(e) && e.id.solid)
				return;
		}
		sprite = playerType.frontSprite;
		y+=speed;
	}

	public void render(Graphics2D g) {	
		sprite.draw(x, y, g);
	}

}
