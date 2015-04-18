package com.TeamFlowerShop.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;

public class GameOverScreen {
SpriteBatch batch;
	
	Texture overScreen;

	float MousePosY;
	float posX, posY;
	
	// Initialization for the wall
	public GameOverScreen (float UniversalXPos, float UniversalYPos)
	{
		posX = UniversalXPos;
		posY = UniversalYPos;
		overScreen = new Texture("GameOverScreen.png");
		batch = new SpriteBatch();	
	}
	public boolean Update(int healthRemaining)
	{
		if(healthRemaining == 0 || healthRemaining < 0)
		{
			return true;
		}
		return false;
	}
	

	public void Draw (float ux, float uy)
	{
		batch.begin();
		
		batch.draw(overScreen, ux , uy);
		
		batch.end();
		
	}
}
