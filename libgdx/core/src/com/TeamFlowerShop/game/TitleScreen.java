package com.TeamFlowerShop.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;
import java.util.ArrayList;

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
		startButtonRect = new Rectangle((posX/64)*20, (posY/48)*20, startButton.getWidth(), startButton.getHeight());
		
		batch = new SpriteBatch();	
	}
	

	public boolean Update()
	{
		//startButton = new Texture();
		MousePosY = Gdx.graphics.getHeight() - Gdx.input.getY();
		/*if (startButtonRect.contains(Gdx.input.getX(), MousePosY))
		{
			startButton = new Texture("StartB.png");
		}
		else
		{
			start button = new Texture("StartA.png");
		}*/
		if(startButtonRect.contains((Gdx.input.getX()/64)*20, (MousePosY/48)*20) && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
		{
			return false;
		}
		return true;  //Start has not been clicked
		
	}
	

	public void Draw (float ux, float uy)
	{
		batch.begin();
		
		batch.draw(titleBackdrop, ux , uy);
		batch.draw(startButton, ((Gdx.graphics.getWidth()/64)*20)-ux,  ((Gdx.graphics.getHeight()/48)*20)-uy);
		
		batch.end();
		
	}
}
	