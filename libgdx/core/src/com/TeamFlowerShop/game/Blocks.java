package com.TeamFlowerShop.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.*;

public class Blocks {
	SpriteBatch batch;
	Rectangle blockRect;
	Texture block;
	TextureRegion blockReg;
	float posX, posY;
	float universalX, universalY;
	RotatedRectangle blockCollRect;
	
	public Blocks (float PosX, float PosY, float Width, float Height, float U_X, float U_Y)
	{
		batch = new SpriteBatch();
		blockRect = new Rectangle(PosX, PosY, Width, Height);
		block = new Texture("blockBase.png");
		blockReg = new TextureRegion(block, blockRect.x, blockRect.y, blockRect.width, blockRect.height);
		posX = PosX;
		posY = PosY;
		universalX = U_X;
		universalY = U_Y;
	}
	
	double rotation = 0;
	
	public void Update(float UniversalX, float UniversalY)
	{
		rotation = Math.atan2(blockRect.width, blockRect.height);
		blockCollRect = new RotatedRectangle(posX + UniversalX, posY + UniversalY, blockRect.width, blockRect.height, (float)rotation);
		universalX = UniversalX;
		universalY = UniversalY;
	}
	
	public void Draw()
	{
		batch.begin();
		batch.draw(blockReg, blockRect.x - universalX, blockRect.y - universalY, 0, 0, 1, 
				1, -(float)Math.sqrt(Math.pow(blockRect.width, 2) + 
						Math.pow(blockRect.height, 2)), 3, (float)Math.toDegrees(rotation) + 90);
		batch.end();
	}
}
