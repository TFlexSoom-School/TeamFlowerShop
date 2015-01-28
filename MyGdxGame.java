package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.input.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Player.png");
	}

	
	double rotation;
	float posX = 0, posY = 0;
	
	@Override
	public void render () {
		if (Gdx.input.isKeyPressed(Keys.W))
			posY += 3;
		if (Gdx.input.isKeyPressed(Keys.A))
			posX -= 3;
		if (Gdx.input.isKeyPressed(Keys.S))
			posY -= 3;
		if (Gdx.input.isKeyPressed(Keys.D))
			posX += 3;
		
		rotation = Math.atan2((posY + img.getHeight() / 2) - (Gdx.graphics.getHeight() - 
				Gdx.input.getY()), (posX+ img.getWidth() / 2) - Gdx.input.getX());
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, posX, posY, (float)img.getWidth()/2, (float)img.getHeight()/2, 
				(float)img.getWidth(), (float)img.getHeight(), (float)1, (float)1, 
				(float)Math.toDegrees(rotation) + 90, 0, 0, img.getWidth(), img.getHeight(), false, false);
		batch.end();
	}
}
