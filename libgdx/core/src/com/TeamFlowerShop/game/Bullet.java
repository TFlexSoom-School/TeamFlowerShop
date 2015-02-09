package com.TeamFlowerShop.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.*;

public class Bullet {
	
	SpriteBatch batch;
	Texture bulletTex;
	TextureRegion bulletTexReg;
	
	float posX, posY;
	int U_X, U_Y;
	double velocityX, velocityY;
	double rotation;
	
	Rectangle bulletRect;
	
	public Bullet (double VelocityX, double VelocityY, float PosX, float PosY, double Rotation, int UniversalX, int UniversalY){
		batch = new SpriteBatch();
		bulletTex = new Texture("Bullet.png");
		bulletTexReg = new TextureRegion(bulletTex, 0, 
				0, bulletTex.getWidth(), bulletTex.getHeight());
		
		velocityX = VelocityX;
		velocityY = VelocityY;
		posX = PosX;
		posY = PosY;
		
		rotation = Rotation;
		
		U_X = 0 - UniversalX;
		U_Y = 0 - UniversalY;
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