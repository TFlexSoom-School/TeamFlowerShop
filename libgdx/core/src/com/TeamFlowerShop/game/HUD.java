package com.TeamFlowerShop.game;

import com.badlogic.gdx.graphics.Color;
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
	int health;
    private BitmapFont font;

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

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
		setHealth(intialHealth);	
        font = new BitmapFont();
        font.setColor(Color.RED);
        font.setScale(2);		
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
		batch.begin();
		font.draw(batch, "00", 200, 200);
		batch.end();
		
	}
}