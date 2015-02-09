package com.TeamFlowerShop.game;

import com.badlogic.gdx.graphics.Texture;


public class GroundPiece {
	int GroundTex;
	float posX, posY;
	float universalX = 0, universalY = 0;
	
	public GroundPiece (int groundType, float PosX, float PosY)
	{
		posX = PosX;
		posY = PosY;
		
		if (groundType == 0)
		{
			groundType = 1;
		}
		
		//here is just a 'piece' of ground used by Ground class to keep track of pieces
		GroundTex = groundType;
	}
	
	public void Update(int U_X, int U_Y)
	{
		universalX = U_X;
		universalY = U_Y;
	}
}
