package com.nickhulsey.start;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.nickhulsey.input.InputHandler;

public class Main extends JPanel implements Runnable{
	private boolean isRunning = false;
	private Thread thread;
	private JFrame frame;
	
	public static final int TILE_SIZE = 8; // how big every sprite is
	public static final int ROOM_SIZE = 16; // how big every room is
	public static final int SCALE = 6; // scale for the all the objects aka how big they are.
	public static final int WINDOW_SCALE = 8; // the scale for the window...the bigger this is the more zoomed out
	
	//public static int SEED = Integer.parseInt(JOptionPane.showInputDialog("Please input value for seed"));
	private static Random r = new Random();//(SEED)
	public static int MAP_WIDTH = r.nextInt(5) + 3;  // amount of rooms width
	public static int MAP_HEIGHT = r.nextInt(5) + 3; // amount of rooms height
	
	public static final int GAME_WIDTH = MAP_WIDTH * TILE_SIZE * ROOM_SIZE * SCALE; // how big the map is
	public static final int GAME_HEIGHT = MAP_HEIGHT * TILE_SIZE * ROOM_SIZE * SCALE;
	
	public static int WIDTH = TILE_SIZE * ROOM_SIZE * WINDOW_SCALE; // 
	public static int HEIGHT = TILE_SIZE * ROOM_SIZE * WINDOW_SCALE;
	public static double wScale,hScale;
	
	public InputHandler input;
	public Game game;
	
	
	public Main(){
		thread = new Thread(this);
		input = new InputHandler();
		game = new Game(input, this);
		setDoubleBuffered(true);
	}
	
	public void start(){
		if(!isRunning){
			isRunning = true;
			thread.start();
		}else return;
	}

	public void stop(){
		if(isRunning){
			try {
				thread.join();
				isRunning = false;
			} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	
	
	public void run(){
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(isRunning){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				repaint();
				updates++;
				delta--;
				frames++;
			}
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				frame.setTitle("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.scale(wScale, hScale);
		g.setColor(new Color(25,25,25,255));
		g.fillRect(0, 0, getWidth() * SCALE, getHeight() * SCALE);
		game.draw(g2d);
	}
	
	
	public void tick(){
		game.tick();
		//find the ratios
		wScale = this.getWidth() / (double)WIDTH; 
		hScale = this.getHeight() / (double)HEIGHT;
	}
	
	public static void main(String [] args){
		JFrame frame = new JFrame("DUNGEON");
		Main panel = new Main();
		panel.game.init();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(panel.WIDTH, panel.HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.addKeyListener(panel.input);
		frame.addMouseListener(panel.input);
		frame.addMouseMotionListener(panel.input);
		frame.addFocusListener(panel.input);
		frame.add(panel);
		frame.setVisible(true);
		panel.frame = frame;
		panel.start();
	}
	
}