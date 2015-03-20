package com.TeamFlowerShop.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.*;

public class Bullet {
	
	SpriteBatch batch;
	Texture bulletTex;
	TextureRegion bulletTexReg;
	
	float posX, posY;
	//float universalXPos, universalYPos;
	int speed = 20; // The speed of each bullet.
	double velocityX, velocityY;
	double rotation;
	
	Rectangle bulletRect;
	
	public Bullet (float mousePosX, float mousePosY){
		batch = new SpriteBatch();
		bulletTex = new Texture("Bullet.png");
		bulletTexReg = new TextureRegion(bulletTex, 0, 0, bulletTex.getWidth(), bulletTex.getHeight()); // I don't know why this is here
		
		// Set the rotation of the bullet
		rotation = Math.atan2(((Gdx.graphics.getHeight() / 2) - mousePosY), 
				              ((Gdx.graphics.getWidth()  / 2) - mousePosX));
		
		// Set the velocities of the bullet based on its rotation
		velocityX = Math.cos(rotation) * speed;
		velocityY = Math.sin(rotation) * speed;
		
		// Set the bullet's position at the middle of the screen
		posX = Gdx.graphics.getWidth()  / 2;
		posY = Gdx.graphics.getHeight() / 2;
		
		// The player's position will be held in the player class and the player class only
		//universalXPos = universalXPos_;
		//universalYPos = universalYPos_;
		//U_Y = 0 - UniversalY;
		//universal X and universal Y are VERY IMPORTANT. refers to the x, y val on the map of the BOTTOM LEFT HAND CORNER
	}
	
	public void Update(int UniversalX, int UniversalY){
		bulletRect= new Rectangle(posX, posY, bulletTex.getWidth(), bulletTex.getHeight());
		
		posX -= velocityX;
		posY -= velocityY;
	}
	
	public void DisposeBullet()
	{
		bulletTex.dispose();
	}
	
	public void Draw(){
		batch.begin();
		batch.draw(bulletTexReg, posX, posY, bulletTex.getWidth() / 2, bulletTex.getHeight() / 2, bulletTex.getWidth(), 
				bulletTex.getHeight(), 1, 1, (int)Math.toDegrees(rotation));
		batch.end();
	}
}