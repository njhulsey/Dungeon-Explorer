package com.nickhulsey.input;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import com.nickhulsey.entities.Room;
import com.nickhulsey.start.Game;
import com.nickhulsey.start.Main;

public class MapHandler {

	public int[][] data;
	private Game game;
	private int roomSize;
	private int amountRoomsWidth;
	private int amountRoomsHeight;
	private Random r;
	
	private BufferedImage miniMap;
	private int mapScale = 30;
	private int alpha = 200;
	private final Color currentRoom = new Color(242,197,0,alpha);
	
	//0 = floor
	//1 = wall
	//[y][x]
	
	//TO FIND YOUR POSITION ON THE MAP DATA, JUST DIVIDE YOUR X BY THE TILE SIZE 
	
	public MapHandler(Game game){
		this.game = game;
		roomSize = Main.ROOM_SIZE;
		amountRoomsWidth = Main.MAP_WIDTH;
		amountRoomsHeight = Main.MAP_HEIGHT;
		data = new int[roomSize * amountRoomsHeight][roomSize * amountRoomsWidth];
		miniMap = new BufferedImage(Main.MAP_WIDTH, Main.MAP_HEIGHT, BufferedImage.TYPE_INT_ARGB );
		generateMap();
		
	}
	
	public void generateMap(){
		r = new Random();
		//set game data to all 0's
		for(int y = 0; y < data.length; y++){
			for(int x = 0; x < data[y].length; x++){
				data[y][x] = 0;
			}
		}
		for(int y = 0; y < amountRoomsHeight; y++){
			for(int x = 0; x < amountRoomsWidth; x++){
				generateRoom(x * roomSize, y * roomSize,0);
				//room location
				//System.out.println("room at: " + " X: " + x * roomSize + " Y: " + y * roomSize);
			}
		}
	}
	
	public String getHallwayDirection(int xI, int yI){
		int maxX = roomSize * amountRoomsWidth - 1;
		int maxY = roomSize * amountRoomsHeight - 1;
		
		ArrayList<String> directions = new ArrayList<String>();
		directions.add("top");
		directions.add("left");
		directions.add("bottom");
		directions.add("right");
		
		if(xI == 0)directions.remove("left");
		if(xI >= maxX)directions.remove("right");
		if(yI == 0)directions.remove("top");
		if(yI >= maxY)directions.remove("bottom");
		return directions.get(r.nextInt(directions.size()));
		
	}
	
	public void generateRoom(int xI, int yI, int amountOfHallways){
		//where to place the hallway
		String hallway = getHallwayDirection(xI, yI);
		int hallways_perRoom = 2;
		//is the set amount of hallways given?
		if(amountOfHallways > 0)hallways_perRoom = amountOfHallways;
		
		// - 1 because arrays 
		//generate top
		for(int x = 0; x < roomSize - 1; x++){
			//if the space is a floor then place a wall there
			if(data[yI][x+xI] == 0)data[yI][x + xI] = 1;
			
			//generate hallway
			if(yI - 1 >= 0 && x + xI > xI && r.nextInt(4) == 0 && hallway.equals("top")){
				data[yI][x+xI] = 2;
				data[yI - 1][x+xI] = 2;
				//can we create annother hallway connected to this room?
				if(hallways_perRoom > 0){
					hallway = getHallwayDirection(xI, yI);
					hallways_perRoom --;
				}else hallway = "";
			}
			
		}
		
		//generate left
		for(int y = 0; y < roomSize - 1; y++){
			if(data[y + yI][xI] == 0)data[y + yI][xI] = 1;
			
			if(xI - 1 >= 0 && y + yI > yI && r.nextInt(4) == 0 && hallway.equals("left")){
				data[y + yI][xI] = 2;
				data[y + yI][xI - 1] = 2;
				if(hallways_perRoom > 0){
					hallway = getHallwayDirection(xI, yI);
					hallways_perRoom --;
				}else hallway = "";
			}
			
		}
		
		//generate bottom
		for(int x = 0; x < roomSize ; x++){
			if(data[yI + roomSize - 1][x + xI] == 0)data[yI + roomSize - 1][x + xI] = 1;
			
			if(yI + roomSize + 1 <= (roomSize * amountRoomsHeight - 1) && xI + x > xI && x + xI < xI + roomSize - 1 && r.nextInt(4) == 0 && hallway.equals("bottom")){
				data[yI + roomSize - 1][x + xI] = 2;
				data[yI + roomSize][x + xI] = 2;
				if(hallways_perRoom > 0){
					hallway = getHallwayDirection(xI, yI);
					hallways_perRoom --;
				}else hallway = "";
			}
		}
		
		//generate right
		for(int y = 0; y < roomSize - 1; y++){
			if(data[yI + y][xI + roomSize - 1] == 0) data[yI + y][xI + roomSize - 1] = 1;
			
			if(xI + roomSize + 1<= (roomSize * amountRoomsWidth - 1) && yI + y > yI && hallway.equals("right") && r.nextInt(4) == 0){
				data[yI + y][xI + roomSize - 1] = 2;
				data[yI + y][xI + roomSize] = 2;
				if(hallways_perRoom > 0){
					hallway = getHallwayDirection(xI, yI);
					hallways_perRoom --;
				}else hallway = "";
			}
		}
		if(hallways_perRoom > 0 )generateRoom(xI,yI, hallways_perRoom);
		
			//i = amount of columns
			for(int i = r.nextInt(7) + 3; i > 0; i--){
				int x = xI + 2 + r.nextInt(roomSize - 4);
				int y = yI + 2 + r.nextInt(roomSize - 4);
				data[y][x] = 1;
			}
	}
	
	public void printData(){
		for(int y = 0; y < data.length; y++){
			for(int x = 0; x < data[y].length; x++){
				System.out.print(data[y][x]);
			}
			System.out.println();
		}
	}
	
	public void updateMap(){
		Graphics2D mapG = (Graphics2D) miniMap.getGraphics();
		//clear all pixels by using alphaComposite.clear
		mapG.setColor(Color.black);
		mapG.setComposite(AlphaComposite.Clear);
		mapG.fillRect(0, 0, miniMap.getWidth(), miniMap.getHeight());
		//set it back to normal
		mapG.setComposite(AlphaComposite.Src);
		for(Room room: game.rooms){
			//if we have visited the room and are not in it...
			if(room.visited && game.player.room != room){
				//get the rooms color then draw the square
				mapG.setColor(new Color(room.roomType.color.getRed(),room.roomType.color.getGreen(), room.roomType.color.getBlue(), alpha));
				mapG.fillRect(room.x / Main.ROOM_SIZE / Main.TILE_SIZE, room.y / Main.ROOM_SIZE / Main.TILE_SIZE, 1, 1);
				//if we are in the room then draw the square with the current room color
			}else if(game.player.room == room){
				mapG.setColor(currentRoom);
				mapG.fillRect(room.x / Main.ROOM_SIZE / Main.TILE_SIZE, room.y / Main.ROOM_SIZE / Main.TILE_SIZE, 1, 1);
			}
		}
		
	}
	
	public void drawMap(Graphics2D g){
		//draw it to the game
		g.drawImage(miniMap, game.camera.x, game.camera.y, miniMap.getWidth() * mapScale, miniMap.getHeight() * mapScale, null);
	}
}