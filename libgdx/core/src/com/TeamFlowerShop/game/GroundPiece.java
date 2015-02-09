package com.TeamFlowerShop.game;

import com.badlogic.gdx.graphics.Texture;


public class GroundPiece {
	Texture GroundTex;
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
		if (groundType == 1)
			GroundTex = new Texture("gsquare1.png");
		else if (groundType == 2)
			GroundTex = new Texture("gsquare2.png");
		else if (groundType == 3)
			GroundTex = new Texture("gsquare3.png");
		else if (groundType == 4)
			GroundTex = new Texture("gsquare4.png");
		
	}
	
	public void Update(int U_X, int U_Y)
	{
		universalX = U_X;
		universalY = U_Y;
	}
}
