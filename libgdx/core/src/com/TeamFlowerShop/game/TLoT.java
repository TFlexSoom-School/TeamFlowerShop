package com.TeamFlowerShop.game;

import java.util.*;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class TLoT extends ApplicationAdapter {
	SpriteBatch batch;
	
	Rectangle playerRect;
	ArrayList<Bullet> bullets;
	ArrayList<Enemy> enemies;
	ArrayList<Blocks> blocks;
	ArrayList<Wall> walls;
	Ground gRenderer;
	HUD hud;
	Player player;
	TitleScreen title;
	GameOverScreen lossScreen;
	boolean showTitleScreen;
	boolean showLossScreen;
	Level level;
	Music themeSong;
    int intialHealth=256;	
	
	@Override
	public void create () {
		batch = new SpriteBatch();		
		bullets = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
		blocks = new ArrayList<Blocks>();
		walls = new ArrayList<Wall>();
		hud = new HUD(0, 1, intialHealth);
		player = new Player(); // The player class will hold the player information rather than here
		title = new TitleScreen(universalXPos, universalYPos);
		showTitleScreen = true;
		showLossScreen = false;
		lossScreen = new GameOverScreen(universalXPos, universalYPos);
		level = Level.GetLevel(1);
		gRenderer = new Ground(level);
		themeSong = Gdx.audio.newMusic(Gdx.files.internal("Illuminati.mp3"));
	}
	

	
	@Override
	public void resize(int width, int height)
	{
		title = new TitleScreen(universalXPos, universalYPos);
		player = new Player();
	}
	
	public enum MoveDirection {
		LEFT, RIGHT, UP, DOWN
	}
	
	int universalXPos = 0, universalYPos = 0;
	
	float velocityX, velocityY;
	float speed = 10;
	int MousePosX, MousePosY;
	double rotation;
	
	int timer = 0;
	
	boolean lastFrameRMD = false;
	boolean thisFrameRMD = false;
	
	float boxX = 0, boxY = 0;
	float boxW = 0, boxH = 0;
	
	int health = 256;

	boolean init = true;
	boolean start = false;
	
	// Initialization function to be run once at the beginning of game
	// I will be using it to spawn walls
	public void Initialize() {
		
		// Draw walls
		/*for (int a = 0; a < 320; a += 64)
		{
			walls.add(new Wall(a, 0));			
		}*/
			
	}
	
	// Update function to run every update. This is called in the render function
	public void Update() {
		playerRect = new Rectangle((Gdx.graphics.getWidth()  / 2) - (player.img.getWidth()  / 2), 
								   (Gdx.graphics.getHeight() / 2) - (player.img.getHeight() / 2), 
								    player.img.getWidth(), player.img.getHeight());
		
		// Code to make the initialization run once
		if(init)
		{
			Initialize();
			init = !init;
		}
		
		// Code to deal with the walls made with the right mouse click. I'll leave this here for now.
		if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT))
			thisFrameRMD = true;
		else
			thisFrameRMD = false;
		
		// This may need to be moved to the end with the rest of the updates
		gRenderer.Update(universalXPos, universalYPos);
		
		// CODE FOR FIRING BULLETS //
		
		// Get the mouse position to hand it over to the bullet
		MousePosX = Gdx.input.getX();
		MousePosY = Gdx.graphics.getHeight() - Gdx.input.getY();//have to do this because mouse Y is calculated with Y incrementing as it goes down but
																//with everything else incrementing from down to up 
		
		rotation = Math.atan2(((Gdx.graphics.getHeight() / 2) - MousePosY), 
							  ((Gdx.graphics.getWidth()  / 2) - MousePosX));
		
		//velocityX = (float)Math.cos(rotation) * 20;//simple trig to find how fast up it should go and how fast to the side
		//velocityY = (float)Math.sin(rotation) * 20;//a search on the trig unit circle should come up with something if you want to know more
		
		if (timer % 5 == 0 && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
			bullets.add(new Bullet(MousePosX, MousePosY));
			
			//bullets.add(new Bullet(velocityX, velocityY, 
			//		(Gdx.graphics.getWidth() / 2), 
			//			(Gdx.graphics.getHeight() / 2), rotation, universalXPos, universalYPos));
		
		// END CODE FOR FIRING BULLETS //
		
		// ENEMY SPAWN CODE
		
		if (timer % 200 == 0)//spawns an enemy every 200 updates NEED TO IMPROVE: random spawning, enemy hardness level, rectangles that are rotated? :3 pls?
			enemies.add(new Enemy(universalXPos, universalYPos));
		
		// END ENEMY SPAWN CODE
		
		// UPDATE SECTION //
		
		for (Enemy e : enemies)
		{//updates each enemy in the list enemies
			e.Update(universalXPos + (Gdx.graphics.getWidth()  / 2), 
					 universalYPos + (Gdx.graphics.getHeight() / 2), blocks);
		}
		
		for (Bullet b : bullets)
		{//updates each bullet in the list of bullets
			b.Update(/* universalXPos + (Gdx.graphics.getWidth()  / 2) */ player.velX, 
					 /* universalYPos + (Gdx.graphics.getHeight() / 2) */ player.velY);
		}
		
		for (Wall w : walls)
		{
			w.Update();
		}
		
		player.Update(rotation, walls);
		
		themeSong.setLooping(true);
		themeSong.play();
		// END UPDATE SECTION //
		
		// BULLET - ENEMY COLLISION //
		
		//used this instead of size - 1 because then if there was only one instance of the object
		//the two wouldn't be recognized in the for loop and if it was <= in the for loop then
		//it crashed because it was out of the size. This checks for collision of bullet and enemy
		if (bullets.size() != 0)
		{
			for (int i = 0; i < bullets.size(); i++)
			{
				if (enemies.size() != 0)
				{
					for (int j = 0; j < enemies.size(); j++)
					{
						if (bullets.get(i).bulletRect.overlaps(enemies.get(j).enemyRect))
						{///////////////////////////////////////fix broken collision code
							bullets.remove(i);
							enemies.remove(j);
							break; // This just fixes this.
						}
					}
				}
			}
		}
		
		if(enemies.size() != 0)
		{
			for (int i = 0; i < enemies.size(); i++)
			{
				if(enemies.get(i).enemyRect.overlaps(playerRect))
				{
					hud.Update(0, 1, true);
					enemies.remove(i);
					break;
				}
				else
				{
					hud.Update(0, 1, false);
				}
			}
		}
		
		// END BULLET - ENEMY COLLISION
		
		universalXPos = player.posX;
		universalYPos = player.posY;
		
		// BULLET AUTOMATIC DELETION //
		for (int i = 0; i < bullets.size(); i++)
		{
			//calculate distance
			double distance = Math.sqrt(Math.pow((bullets.get(i).posX), 2) + 
					Math.pow((bullets.get(i).posY), 2));
			//delete if too far to stop unnecessary mem usage. can change value for different ranges of weapons.
			if (distance > 2000)
			{
				bullets.get(i).DisposeBullet();
				bullets.remove(i);
			}
		}
		// END BULLET AUTOMATIC DELETION //
		
		// MOUSE GENERATED WALLS CONTINUED //
		if (!lastFrameRMD && thisFrameRMD)
		{
			boxX = Gdx.input.getX();
			boxY = Gdx.input.getY();
		}
		if (lastFrameRMD && !thisFrameRMD)
		{
			boxW = Gdx.input.getX() - boxX;
			boxH = Gdx.input.getY() - boxY;
		}
		if (boxX != 0 && boxY != 0 && boxW != 0 && boxH != 0)
		{
			/*if (boxW < 0)
				boxX -= boxW;
			if (boxH < 0)
				boxY -= boxH;*/
			blocks.add(new Blocks((boxX) + universalXPos, (Gdx.graphics.getHeight() - boxY) + universalYPos, boxW, boxH, universalXPos, universalYPos));
			boxX = 0;
			boxY = 0;
			boxW = 0;
			boxH = 0;
		}

		lastFrameRMD = thisFrameRMD;
		
		for (Blocks b : blocks)
			b.Update(universalXPos, universalYPos);
		// END MOUSE GENERATED WALLS CONTIUNUED //
	}
	

	
	@Override
	public void render ()
	{
		if(showTitleScreen)
		{
			showTitleScreen = title.Update();
		}
		

		if(!showTitleScreen)
		{
			Update();
		}
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//HERE STARTS ALL THE DRAW CODE. ENGINE ONLY HAS ONE LOOP METHOD, AND IT IS RENDER.
		gRenderer.Draw(universalXPos, universalYPos);
			
		if(showTitleScreen)
		{	
			title.Draw(universalXPos, universalYPos);		
		}
		else if(hud.getHealth() <= 0)
		{
			lossScreen.Draw(universalXPos, universalYPos);
		}
		else
		{
			for (Bullet b : bullets){//draws each bullet in the list of bullets
				b.Draw();
			}
			
			player.Draw();
			
			for (Enemy e : enemies)
			{//draws each enemy in the list of enemies
				e.Draw(universalXPos, universalYPos);
			}
			
			for (Blocks b : blocks)
				b.Draw();
			
			/*for (Wall w : walls)
			{
				w.Draw(universalXPos, universalYPos);
			}*/
			
			hud.Draw(0, 0);
			// draws HUD class
			
			timer += 1;
		}
	}
	
	public void MoveAll (Boolean XY, float distance)
	{//XY == true means the X value is being changed. else, y val.
		// made this because didnt want to put all code for moving bullets in code
		//for moving the player.
		if (XY)
		{
			for (Bullet b : bullets)
			{
				b.posX += distance;
			}
		}
		if (!XY)
		{
			for (Bullet b : bullets)
			{
				b.posY += distance;
			}
		}
	}
}