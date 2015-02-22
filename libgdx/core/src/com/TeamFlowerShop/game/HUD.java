package com.TeamFlowerShop.game;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;

public class HUD {
	
	SpriteBatch batch;
	Texture HealthBar;
	float rotation = 0;
	float barX, barY;
	double universalXPos, universalYPos;
	
	Rectangle BarRect;
	
	boolean X, Y;
	public HUD(int health, int gold, ArrayList<Enemy> enemies)
	{
		batch = new SpriteBatch();
			
		HealthBar = new Texture("Health Bar.png");
	}
	
	public void Update(int Health, int Gold, ArrayList<Enemy> Enemies)
	{
		
	}
	
	public void Draw(int ux, int uy)
	{
		BarRect = new Rectangle(ux, uy, HealthBar.getWidth(), HealthBar.getHeight());
		
		batch.begin();
		batch.draw(HealthBar, (float) ux, (float) uy);
		batch.end();
	}
}