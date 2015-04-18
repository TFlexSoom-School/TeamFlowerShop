package com.TeamFlowerShop.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;

public class HUD {
	
	SpriteBatch batch;
	Texture healthBar;
	float rotation = 0;
	float barX, barY;
	double universalXPos, universalYPos;
	int healthLeached = 0;
	BitmapFont font =  new BitmapFont();
	int health;
	

	Rectangle BarRect;
		
	String printedKills = "Hello World";
	
	boolean X, Y;
	public HUD(int gold, int kills, int intialHealth)
	// Will potentially hold all of the Heads up display information including Player health, Kills,
	// and any other necessities.
	{
		batch = new SpriteBatch();
		font = new BitmapFont();
			
		healthBar = new Texture("Health Bar.png");
		health = intialHealth;
		
	}
	
	public void Update(int gold, int kills, boolean attacked)
	{
		if (attacked)
		{
			health -= 32;
		}

		printedKills = "" + kills;
	}
	
	public void Draw(float ux, float uy)
	{	
		batch.begin();
		batch.draw(healthBar, (float) ux, (float) uy, health, healthBar.getHeight());
		
		batch.end();
		batch.begin();
		font.draw(batch, printedKills, (float) ux, (float) uy, 10, 10);
		
		batch.end();
	}
}