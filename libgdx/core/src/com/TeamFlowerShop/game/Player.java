package com.TeamFlowerShop.game;

//import com.TeamFlowerShop.game.TLoT.MoveDirection;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.*;

public class Player {

	SpriteBatch batch;
	
	Texture playerTex;
	
	int posX, posY;
	int velX, velY;
	int speed;
	int width;
	int height;
	
	Texture img;
	TextureRegion img_1;//draw it as a texture region because it draws a certain region of a sprite
						//and who knows when we might add animation? :D
	
	// Draw utility variables
	float playerX;
	float playerY;
	float originX;
	float originY;
	double rotation;
	
	boolean[] collisionDir; // an array to hold which directions the player is colliding with a wall to restrict their movement
	// [up?, right?, down?, left?]
	
	public enum CollisionDirection {
		LEFT, RIGHT, UP, DOWN
	}
	
	public Player () 
	{
		batch = new SpriteBatch();
		
		collisionDir = new boolean[4];
		
		speed = 10;
		
		img = new Texture("Player.png");
		img_1 = new TextureRegion(img, 0, 0, img.getWidth(), img.getHeight());
	}
	
	public void Update (double mouseAngle, ArrayList<Wall> walls) 
	{		
		HandlePlayerMovement();
		
		rotation = mouseAngle; // make the player face the cursor
	}
	
	public void Draw ()
	{
		float playerX = (Gdx.graphics.getWidth() / 2) - (img.getWidth() / 2);
		float playerY = (Gdx.graphics.getHeight() / 2) - (img.getHeight() / 2);
		float originX = img.getWidth() / 2;
		float originY = img.getHeight() / 2;
		
		batch.begin();//draw the player
		batch.draw(img_1, playerX, playerY, originX, originY, img.getWidth(), img.getHeight(), 1, 1, (int)Math.toDegrees(rotation) + 90);
		batch.end();
	}
	
	char a = 1;
	
	public CollisionDirection CheckWallCollision (Wall wall)
	{
		// Check if player left bound is touching wall
		if(posX < wall.posX + wall.wallTex.getWidth() && 
		   posY > wall.posY + wall.wallTex.getHeight() &&
		   posY + img.getHeight() < wall.posY)
		{
			return CollisionDirection.LEFT;
		}
		
		// Check if player right bound is touching wall
		if(posX + img.getWidth() < wall.posX && 
		   posY > wall.posY + wall.wallTex.getHeight() &&
		   posY + img.getHeight() < wall.posY)
		{
			return CollisionDirection.RIGHT;
		}
		
		return null;
	}
	
	public void HandlePlayerMovement ()
	{
		velX = 0; 
		velY = 0;
		
		// this big block handles movement
		if(Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP))
			velY += speed;
		if(Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN))
			velY -= speed;
		if(Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT))
			velX -= speed;
		if(Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT))
			velX += speed;
		
		posX += velX;
		posY += velY;
	}
}
