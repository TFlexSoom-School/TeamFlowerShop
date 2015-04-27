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
	float buttonX;
	float buttonY;
	
	// Initialization for the wall
	public TitleScreen (float UniversalXPos, float UniversalYPos)
	{
		posX = UniversalXPos;
		posY = UniversalYPos;
		titleBackdrop = new Texture("Title.png");
		startButton = new Texture("StartA.png");
		buttonX = (Gdx.graphics.getWidth() / 2) - (startButton.getWidth() / 2);
		buttonY = (Gdx.graphics.getHeight() / 2) - (startButton.getHeight() / 2);

		startButtonRect = new Rectangle(buttonX, buttonY, startButton.getWidth(), startButton.getHeight());
		
		batch = new SpriteBatch();	
	}
	

	public boolean Update()
	{
		MousePosY = Gdx.graphics.getHeight() - Gdx.input.getY();
		
		if (startButtonRect.contains(Gdx.input.getX(), (MousePosY)))
		{
			startButton = new Texture("StartB.png");
		}
		else
		{
			startButton = new Texture("StartA.png");
		}
		
		if(startButtonRect.contains(Gdx.input.getX(), MousePosY) && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
		{
			return false;
		}
		return true;  //Start has not been clicked
		
	}
	

	public void Draw (float ux, float uy)
	{
		batch.begin();
		
		batch.draw(titleBackdrop, ux , uy, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(startButton, buttonX,  buttonY);
		
		batch.end();
		
	}
}
	