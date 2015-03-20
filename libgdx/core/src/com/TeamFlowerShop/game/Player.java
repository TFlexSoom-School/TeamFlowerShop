package com.TeamFlowerShop.game;

import com.TeamFlowerShop.game.TLoT.MoveDirection;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;

public class Player {

	SpriteBatch batch;
	
	Texture playerTex;
	
	int posX, posY;
	int playerVelX, playerVelY;
	int speed;
	int width;
	int height;
	
	public Player () 
	{
		
	}
	
	public void Update () 
	{
		HandlePlayerMovement();
		
		playerVelX = 0; 
		playerVelY = 0;
		
		if(Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP))
			playerVelY += speed;
		if(Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN))
			playerVelY -= speed;
		if(Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT))
			playerVelX -= speed;
		if(Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT))
			playerVelX += speed;
	}
	
	public void Draw ()
	{
		
	}
	
	public void HandlePlayerMovement ()
	{
		
	}
}
