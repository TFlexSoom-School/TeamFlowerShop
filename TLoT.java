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
	TextureRegion img_1;
	
	ArrayList<Bullet> bullets;
	Ground gRenderer;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Player.png");
		img_1 = new TextureRegion(img, 0, 0, img.getWidth(), img.getHeight());
		
		bullets = new ArrayList<Bullet>();
		gRenderer = new Ground();
	}

	
	int universalXPos = 0, universalYPos = 0;
	float velocityX, velocityY;
	float speed = 10;
	int MousePosX, MousePosY;
	double rotation;
	int timer = 0;
	
	public void Update() {
		gRenderer.Update(universalXPos, universalYPos);
		MousePosX = Gdx.input.getX();
		MousePosY = Gdx.graphics.getHeight() - Gdx.input.getY();
		
		rotation = Math.atan2(((Gdx.graphics.getHeight() / 2) - MousePosY), 
					((Gdx.graphics.getWidth() / 2) - MousePosX));
		
		velocityX = (float)Math.cos(rotation) * 20;
		velocityY = (float)Math.sin(rotation) * 20;
		
		if (timer % 5 == 0 && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
			bullets.add(new Bullet(velocityX, velocityY, 
					(Gdx.graphics.getWidth() / 2), 
						(Gdx.graphics.getHeight() / 2), rotation, universalXPos, universalYPos));
		
		for (Bullet b : bullets){
			b.Update(universalXPos, universalYPos);
		}
		
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
			double distance = Math.sqrt(Math.pow((bullets.get(i).posX), 2) + 
					Math.pow((bullets.get(i).posY), 2));
			
			if (distance > 2000)
				bullets.remove(i);
		}
	}
	
	@Override
	public void render () {
		Update();
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//HERE STARTS ALL THE DRAW CODE. ENGINE ONLY HAS ONE LOOP METHOD, AND IT IS RENDER.
		gRenderer.Draw();
		
		for (Bullet b : bullets){
			b.Draw();
		}
		
		batch.begin();
		batch.draw(img_1, (Gdx.graphics.getWidth() / 2) - (img.getWidth() / 2), 
				(Gdx.graphics.getHeight() / 2) - (img.getHeight() / 2), img.getWidth() / 2, 
					img.getHeight() / 2, img.getWidth(), img.getHeight(), 1, 1, 
						(int)Math.toDegrees(rotation) + 90);
		batch.end();
		
		timer += 1;
	}
	
	public void Move (int key)
	{
		if (key == Keys.W)
		{
			universalYPos -= speed;
			MoveAll(false, -speed);
		}
		if (key == Keys.A)
		{
			universalXPos += speed;
			MoveAll(true, speed);
		}
		if (key == Keys.S)
		{
			universalYPos += speed;
			MoveAll(false, speed);
		}
		if (key == Keys.D)
		{
			universalXPos -= speed;
			MoveAll(true, -speed);
		}
	}
	
	public void MoveAll (Boolean XY, float distance)
	{
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
