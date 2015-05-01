package com.TeamFlowerShop.game;

//import com.TeamFlowerShop.game.TLoT.MoveDirection;
import java.util.ArrayList;

import com.TeamFlowerShop.game.Level.Pieces;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player {

	SpriteBatch batch;
	
	Texture playerTex;
	
	int posX, posY;
	int velX, velY;
	int speed;
	int width;
	int height;
	Ground ground;

	// Draw utility variables
	float playerX;
	float playerY;
	float originX;
	float originY;	
	
	public Ground getGround() {
		return ground;
	}

	public void setGround(Ground ground) {
		this.ground = ground;
	}

	Level currentLevel;
	
	public Level getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(Level currentLevel) {
		this.currentLevel = currentLevel;
	}

	Texture img;
	TextureRegion img_1;//draw it as a texture region because it draws a certain region of a sprite
						//and who knows when we might add animation? :D

	double rotation;
	
	boolean[] collisionDir; // an array to hold which directions the player is colliding with a wall to restrict their movement
	// [up?, right?, down?, left?]
	
	public enum CollisionDirection {
		LEFT, RIGHT, UP, DOWN
	}
	
	public Player (Ground ground, Level currentLevel) 
	{
		batch = new SpriteBatch();
		
		collisionDir = new boolean[4];
		setCurrentLevel(currentLevel);
		setGround(ground);
		
		speed = 10;
		
		img = new Texture("Player.png");
		img_1 = new TextureRegion(img, 0, 0, img.getWidth(), img.getHeight());
		playerX = (Gdx.graphics.getWidth() / 2) - (img.getWidth() / 2);
		playerY = (Gdx.graphics.getHeight() / 2) - (img.getHeight() / 2);
	}
	
	public void Update (double mouseAngle, ArrayList<Wall> walls) 
	{		
		HandlePlayerMovement();
		
		rotation = mouseAngle; // make the player face the cursor
	}
	
	public void Draw ()
	{
		playerX = (Gdx.graphics.getWidth() / 2) - (img.getWidth() / 2);
		playerY = (Gdx.graphics.getHeight() / 2) - (img.getHeight() / 2);
		originX = img.getWidth() / 2;
		originY = img.getHeight() / 2;
		
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
		
		int potentialX = (int)playerX + posX + velX;
		if(velX > 0)
			potentialX += (img.getWidth() / 2);
		else
			potentialX -= (img.getWidth() / 2);
		
		int potentialY = (int)playerY + posY + velY;
		if(velY > 0)
			potentialY += (img.getHeight() / 2);
		else
			potentialY -= (img.getHeight() / 2);

		if(currentLevel.GetPieceName(potentialX, potentialY) != Pieces.Wall)
		{
			posX += velX;
			posY += velY;
		}
		/*else if(currentLevel.GetPieceName(potentialX, (int)playerY) != Pieces.Wall)
		{
			posX += velX;
		}
		else if(currentLevel.GetPieceName((int)playerX, potentialY) != Pieces.Wall)
		{
			posY += velY;
		}*/
	}
}
