package com.TeamFlowerShop.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;

public class Wall {

	SpriteBatch batch;
	
	Texture wallTex;
	
	int posX, posY;
	
	// Initialization for the wall
	public Wall (int x, int y)
	{
		wallTex = new Texture("Wall.png");
		
		batch = new SpriteBatch();
		
		posX = x;
		posY = y;
	}
	
	// Update function for the wall
	public void Update ()
	{
		
	}
	
	// Draw function for the wall
	public void Draw (int universalXPos, int universalYPos)
	{
		batch.begin();
		
		batch.draw(wallTex, posX - universalXPos, posY - universalYPos);
		
		batch.end();
		
	}
	
}
