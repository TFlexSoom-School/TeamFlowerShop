package com.TeamFlowerShop.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;

public class TitleScreen {

	SpriteBatch batch;
	
	Texture titleBackdrop;
	Texture startButton;
	
	int posX, posY;
	
	// Initialization for the wall
	public TitleScreen ()
	{
		titleBackdrop = new Texture("Title.png");
		startButton = new Texture("StartA.png");
		
		batch = new SpriteBatch();
		
	
	}
	
	// Update function for the wall
	public void Update ()
	{
		
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
	