package com.TeamFlowerShop.game;

import java.util.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TLoT extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	TextureRegion img_1;//draw it as a texture region because it draws a certain region of a sprite
						//and who knows when we might add animation? :D
	
	ArrayList<Bullet> bullets;
	ArrayList<Enemy> enemies;
	ArrayList<Blocks> blocks;
	ArrayList<Wall> walls;
	Ground gRenderer;
	HUD hud;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Player.png");
		img_1 = new TextureRegion(img, 0, 0, img.getWidth(), img.getHeight());
		
		bullets = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
		blocks = new ArrayList<Blocks>();
		walls = new ArrayList<Wall>();
		gRenderer = new Ground();
		hud = new HUD(0, enemies);
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
	
	// Initialization function to be run once at the beginning of game
	// I will be using it to spawn walls
	public void Initialize() {
		
		// Draw walls
		for (int a = 0; a < 320; a += 64)
		{
			walls.add(new Wall(a, 0));			
		}
			
	}
		
	boolean init = true;

	
	public void Update() {
		
		// Code to make the initialization run once
			if(init)
			{
				Initialize();
				init = !init;
			}
		
		if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT))
			thisFrameRMD = true;
		else
			thisFrameRMD = false;
		gRenderer.Update(universalXPos, universalYPos);
		
		MousePosX = Gdx.input.getX();
		MousePosY = Gdx.graphics.getHeight() - Gdx.input.getY();//have to do this because mouse Y is calculated with Y incrementing as it goes down but
																//with everything else incrementing from down to up 
		
		rotation = Math.atan2(((Gdx.graphics.getHeight() / 2) - MousePosY), 
					((Gdx.graphics.getWidth() / 2) - MousePosX));
		
		velocityX = (float)Math.cos(rotation) * 20;//simple trig to find how fast up it should go and how fast to the side
		velocityY = (float)Math.sin(rotation) * 20;//a search on the trig unit circle should come up with something if you want to know more
		
		if (timer % 5 == 0 && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
			bullets.add(new Bullet(velocityX, velocityY, 
					(Gdx.graphics.getWidth() / 2), 
						(Gdx.graphics.getHeight() / 2), rotation, universalXPos, universalYPos));
		
		if (timer % 200 == 0)//spawns an enemy every 200 updates NEED TO IMPROVE: random spawning, enemy hardness level, rectangles that are rotated? :3 pls?
			enemies.add(new Enemy(universalXPos, universalYPos));
		
		for (Enemy e : enemies)
		{//updates each enemy in the list enemies
			e.Update(universalXPos + (Gdx.graphics.getWidth() / 2), universalYPos + (Gdx.graphics.getHeight() / 2), blocks);
		}
		
		for (Bullet b : bullets)
		{//updates each bullet in the list of bullets
			b.Update(universalXPos + (Gdx.graphics.getWidth() / 2), 
					universalYPos + (Gdx.graphics.getHeight() / 2));
			
		}
		
		for (Wall w : walls)
		{
			w.Update();
		}
		
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
							i--;
							j--;
							break; // This just fixes this.
						}
					}
				}
			}
		}
		
		//redirects to move method which deals with movement
		if(Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP))
			Move(MoveDirection.UP);
		if(Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN))
			Move(MoveDirection.DOWN);
		if(Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT))
			Move(MoveDirection.LEFT);
		if(Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT))
			Move(MoveDirection.RIGHT);

		
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
	}
	
	@Override
	public void render () {
		Update();
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//HERE STARTS ALL THE DRAW CODE. ENGINE ONLY HAS ONE LOOP METHOD, AND IT IS RENDER.
		gRenderer.Draw(universalXPos, universalYPos);
		
		for (Bullet b : bullets){//draws each bullet in the list of bullets
			b.Draw();
		}
		
		
		batch.begin();//draw the player
		batch.draw(img_1, (Gdx.graphics.getWidth() / 2) - (img.getWidth() / 2), 
				(Gdx.graphics.getHeight() / 2) - (img.getHeight() / 2), img.getWidth() / 2, 
					img.getHeight() / 2, img.getWidth(), img.getHeight(), 1, 1, 
						(int)Math.toDegrees(rotation) + 90);
		batch.end();
		
		for (Enemy e : enemies)
		{//draws each enemy in the list of enemies
			e.Draw(universalXPos, universalYPos);
		}
		
		for (Blocks b : blocks)
			b.Draw();
		
		hud.Draw(0, 0);
		// draws HUD class
		
		for (Wall w : walls)
		{
			w.Draw(universalXPos, universalYPos);
		}
		
		timer += 1;
	}
	
	public void Move (MoveDirection d)
	{//Move() deals with movement
		if (d == MoveDirection.UP)
		{
			universalYPos += speed;
			MoveAll(false, -speed);
		}
		if (d == MoveDirection.LEFT)
		{
			universalXPos -= speed;
			MoveAll(true, speed);
		}
		if (d == MoveDirection.DOWN)
		{
			universalYPos -= speed;
			MoveAll(false, speed);
		}
		if (d == MoveDirection.RIGHT)
		{
			universalXPos += speed;
			MoveAll(true, -speed);
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
//>>>>>>> branch 'master' of https://github.com/TFlexSoom/TeamFlowerShop.git
}
