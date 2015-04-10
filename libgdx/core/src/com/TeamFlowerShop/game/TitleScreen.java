package com.TeamFlowerShop.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;

public class TitleScreen {

	SpriteBatch batch;
	
	Texture titleBackdrop;
	Texture startButton;
	Rectangle startButtonRect;
	float MousePosY;
	float posX, posY;
	
	// Initialization for the wall
	public TitleScreen (float UniversalXPos, float UniversalYPos)
	{
		posX = UniversalXPos;
		posY = UniversalYPos;
		titleBackdrop = new Texture("Title.png");
		startButton = new Texture("StartA.png");
		startButtonRect = new Rectangle(posX, posY, startButton.getWidth(), startButton.getHeight());
		MousePosY = Gdx.graphics.getHeight() - Gdx.input.getY();
		
		batch = new SpriteBatch();	
	}
	
	// Update function for the wall
	public boolean Update()
	{
		if(startButtonRect.contains(Gdx.input.getX(), MousePosY) && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
		{
			return false;
		}
		return true;  //Start has not been clicked
	}
	
	// Draw function for the wall
	public void Draw (float ux, float uy)
	{
		batch.begin();
		
		batch.draw(titleBackdrop, ux , uy);
		batch.draw(startButton, ux,  uy);
		
		batch.end();
		
	}
}
	