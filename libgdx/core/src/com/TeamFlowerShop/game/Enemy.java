package com.TeamFlowerShop.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;

public class Enemy {
	
	SpriteBatch batch;
	
	Texture enemyTex;
	float rotation = 0;
	
	float enemyX, enemyY;
	double universalXPos, universalYPos;
	
	Rectangle enemyRect;
	
	boolean X, Y;
	
	public Enemy(int UniversalXPos, int UniversalYPos)
	{
		batch = new SpriteBatch();
		
		enemyTex = new Texture("enemy.png");
		
		if (Math.random() > .5)
			X = true;
		else
			X = false;
		if (Math.random() > .5)
			Y = true;
		else
			Y = false;
		
		if (X)
			enemyX = UniversalXPos -  128;
		else
			enemyX = UniversalXPos + Gdx.graphics.getWidth() + 128;
		
		if (Y)
			enemyY = UniversalYPos -  128;
		else
			enemyY = UniversalYPos + Gdx.graphics.getHeight() + 128;
		
		enemyRect = new Rectangle(enemyX, enemyY, enemyTex.getWidth(), enemyTex.getHeight());
	}
	
	public void Update(int playerX, int playerY)
	{
		rotation = (float)Math.atan2(playerY - enemyY, 
				playerX - enemyX);//find angle made between player and enemy and faces enemy to player
		
		enemyX += Math.cos(rotation) * 10;
		enemyY += Math.sin(rotation) * 10;
	}
	
	public void Draw(int ux, int uy)
	{
		enemyRect = new Rectangle(enemyX - ux, enemyY - uy, 
				enemyTex.getWidth(), enemyTex.getHeight());
		
		batch.begin();
		batch.draw(enemyTex, (float)enemyX - ux, (float)enemyY - uy, enemyTex.getWidth() / 2, 
				enemyTex.getHeight() / 2, enemyTex.getWidth(), enemyTex.getHeight(), 
					1, 1, (float)Math.toDegrees(rotation), 0, 0, enemyTex.getWidth(), enemyTex.getHeight(), false, false);
		batch.end();
	}
}