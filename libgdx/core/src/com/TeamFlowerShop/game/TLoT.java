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
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.math.*;

public class TLoT extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	TextureRegion img_1;//draw it as a texture region because it draws a certain region of a sprite
						//and who knows when we might add animation? :D
	
	ArrayList<Bullet> bullets;
	ArrayList<Enemy> enemies;
	ArrayList<Blocks> blocks;
	Ground gRenderer;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Player.png");
		img_1 = new TextureRegion(img, 0, 0, img.getWidth(), img.getHeight());
		
		bullets = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
		blocks = new ArrayList<Blocks>();
		gRenderer = new Ground();
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
	
	public void Update() {
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
			e.Update(universalXPos + (Gdx.graphics.getWidth() / 2), universalYPos + (Gdx.graphics.getHeight() / 2));
		}
		
		for (Bullet b : bullets)
		{//updates each bullet in the list of bullets
			b.Update(universalXPos + (Gdx.graphics.getWidth() / 2), 
					universalYPos + (Gdx.graphics.getHeight() / 2));
			
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
							/*bullets.remove(i);
							enemies.remove(j);
							i--;
							j--;*/
						}
					}
				}
			}
		}
		
		//redirects to move method which deals with movement
		if(Gdx.input.isKeyPressed(Keys.W))
			Move(Keys.W);
		if(Gdx.input.isKeyPressed(Keys.S))
			Move(Keys.S);
		if(Gdx.input.isKeyPressed(Keys.A))
			Move(Keys.A);
		if(Gdx.input.isKeyPressed(Keys.D))
			Move(Keys.D);
		
		for (int i = 0; i < bullets.size(); i++)
		{
			//calculate distance
			double distance = Math.sqrt(Math.pow((bullets.get(i).posX), 2) + 
					Math.pow((bullets.get(i).posY), 2));
			//delete if too far to stop unnecessary mem usage. can change value for different ranges of weapons.
			if (distance > 2000)
				bullets.remove(i);
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
		
		timer += 1;
	}
	
	public void Move (int key)
	{//Move() deals with movement
		if (key == Keys.W)
		{
			universalYPos += speed;
			MoveAll(false, -speed);
		}
		if (key == Keys.A)
		{
			universalXPos -= speed;
			MoveAll(true, speed);
		}
		if (key == Keys.S)
		{
			universalYPos -= speed;
			MoveAll(false, speed);
		}
		if (key == Keys.D)
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
}