package com.TeamFlowerShop.game;

//import com.TeamFlowerShop.game.TLoT.MoveDirection;
import java.util.ArrayList;

import com.TeamFlowerShop.game.Level.Pieces;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class Player {

	SpriteBatch batch;
	
	Texture playerTex;
	
	int posX, posY;
	int velX, velY;
	int speed;
	int width;
	int height;
	Ground ground;
	boolean diagMode = false;
	int  buttonsP = 0;
	boolean diagnol = false;
	
	// Draw utility variables
	float playerX;
	float playerY;
	float originX;
	float originY;	
	
	// Variables to easily fix the bullet velocity "bug"
	int trueVelX, trueVelY;
	
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
		
		if(diagnol)
		{
			speed = 5;
		}
		else
		{
			speed = 10;
		}
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

	private ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	public void Draw ()
	{
		playerX = (Gdx.graphics.getWidth() / 2) - (img.getWidth() / 2);
		playerY = (Gdx.graphics.getHeight() / 2) - (img.getHeight() / 2);
		originX = img.getWidth() / 2;
		originY = img.getHeight() / 2;
		
		batch.begin();//draw the player
		batch.draw(img_1, playerX, playerY, originX, originY, img.getWidth(), img.getHeight(), 1, 1, (int)Math.toDegrees(rotation) + 90);
		batch.end();
		if(diagMode)
		{
			shapeRenderer.begin(ShapeType.Line);
	        shapeRenderer.setColor(Color.RED);
	        shapeRenderer.rect(potentialRect.x, potentialRect.y, potentialRect.width, potentialRect.height);
	        shapeRenderer.end();
		}
	}
	
	private Rectangle potentialRect;
	
	public void HandlePlayerMovement ()
	{
		velX = 0; 
		velY = 0;
		trueVelX = 0;
		trueVelY = 0;
		
		// this big block handles movement
		if(Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP))
		{
			velY += speed;
			buttonsP += buttonsP + 1;
		}
		if(Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN))	
		{
			velY -= speed;
			buttonsP += buttonsP + 1;
		}
		if(Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT))
		{
			velX -= speed;
			buttonsP += buttonsP + 1;
		}
		if(Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT))
		{
			velX += speed;
			buttonsP += buttonsP + 1;
		}
		if(buttonsP >= 2)
		{
			diagnol = true;
			buttonsP = 0;
		}
		else
		{
			diagnol = false;
			buttonsP = 0;
		}
		
		int potentialX = (int)playerX + velX;

		int potentialY = (int)playerY + velY;

		potentialRect = new Rectangle(potentialX+posX, potentialY+posY, img.getHeight(), img.getWidth());

		if(!currentLevel.OverlapsPieceType(potentialRect, Pieces.Wall))
		{
			posX += velX;
			posY += velY;
			
			trueVelX = velX;
			trueVelY = velY;
		}	
		else if(!currentLevel.OverlapsPieceType(new Rectangle(potentialX+posX, playerY+posY, img.getHeight(), img.getWidth()), Pieces.Wall))
		{
			posX += velX;
			trueVelX = velX;
		}
		else if(!currentLevel.OverlapsPieceType(new Rectangle(playerX+posX, potentialY+posY, img.getHeight(), img.getWidth()), Pieces.Wall))
		{
			posY += velY;
			trueVelY = velY;
		}
		//Exists for diagnostic drawing only, probably should be wrapped in an if statement
		potentialRect = new Rectangle(potentialX, potentialY, img.getHeight(), img.getWidth());
	}
}
