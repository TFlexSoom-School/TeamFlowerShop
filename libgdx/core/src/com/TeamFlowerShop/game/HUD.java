package com.TeamFlowerShop.game;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;

public class HUD {
	
	SpriteBatch batch;
	Texture HealthBar;
	float rotation = 0;
	
	float BarX, BarY;
	double universalXPos, universalYPos;
	
	Rectangle BarRect;
	
	boolean X, Y;
	public HUD(int Health, int Gold, ArrayList<Enemy>, Arraylist<ItemNumbers>)
	{
		batch = new SpriteBatch();
			
		HealthBar = new Texture("Health Bar.png");
	}
	
	public void Update(int Health, int Gold, ArrayList<Enemy> Enemies)
	{
		
	}
	
	public void Draw(int ux, int uy)
	{
		BarRect = new Rectangle(BarX - ux, BarY - uy, 
				HealthBar.getWidth(), HealthBar.getHeight());
		
		batch.begin();
		batch.draw(HealthBar, (float)BarX - ux, (float)BarY - uy, HealthBar.getWidth() / 2, 
				HealthBar.getHeight() / 2, HealthBar.getWidth(), HealthBar.getHeight());
		batch.end();
	}
}