package com.TeamFlowerShop.game;

import java.io.*;
import java.util.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.math.*;

public class Ground {
	SpriteBatch batch;
	ArrayList<GroundPiece> pieces;
	
	public Ground()
	{
		batch = new SpriteBatch();
		pieces = new ArrayList<GroundPiece>();
		
	}
	
	int groundSquareDims = new Texture("gsquare1.png").getHeight();
	int numX, numY;
	int timer = 0;
	
	public void UpdateRender(int UniversalXPos, int UniversalYPos)
	{
		/*if (timer % 40 == 0)
		{
			for (int i = UniversalXPos - groundSquareDims; i < UniversalXPos; i++)
				if (i % groundSquareDims == 0)
				{
					numX = i;
					break;
				}
			
			for (int i = UniversalYPos - groundSquareDims; i < UniversalYPos; i++)
				if (i % groundSquareDims == 0)
				{
					numY = i;
					break;
				}
			
			do
			{
				do 
				{
					if (!Contains(new GroundPiece(1, numX, numY), pieces))
					{
						pieces.add(new GroundPiece((int)Math.round(Math.random() * 4), numX, numY));
					}
					
					numY += groundSquareDims;
				}
				while (numY < UniversalYPos + Gdx.graphics.getHeight() * 3);
				
				numX += groundSquareDims;
			}
			while (numX < UniversalXPos + Gdx.graphics.getWidth() * 3);
			
			for (int i = UniversalXPos - groundSquareDims; i < UniversalXPos; i++)
				if (i % groundSquareDims == 0)
				{
					numX = i;
					break;
				}
			
			for (int i = UniversalYPos - groundSquareDims; i < UniversalYPos; i++)
				if (i % groundSquareDims == 0)
				{
					numY = i;
					break;
				}
			
			do
			{
				do 
				{
					if (!Contains(new GroundPiece(1, numX, numY), pieces))
					{
						pieces.add(new GroundPiece((int)Math.round(Math.random() * 4), numX, numY));
					}
					
					numX += groundSquareDims;
				}
				while (numX < UniversalXPos + Gdx.graphics.getWidth() * 3);
				
				numY += groundSquareDims;
			}
			while (numY < UniversalYPos + Gdx.graphics.getHeight() * 3);
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			for (int i = UniversalXPos + Gdx.graphics.getWidth() - groundSquareDims; i < UniversalXPos + Gdx.graphics.getWidth() + groundSquareDims; i++)
				if (i % groundSquareDims == 0)
				{
					numX = i;
					break;
				}
			
			for (int i = UniversalYPos - groundSquareDims; i < UniversalYPos; i++)
				if (i % groundSquareDims == 0)
				{
					numY = i;
					break;
				}
			
			do
			{
				do 
				{
					if (!Contains(new GroundPiece(1, numX, numY), pieces))
					{
						pieces.add(new GroundPiece((int)Math.round(Math.random() * 4), numX, numY));
					}
					
					numY += groundSquareDims;
				}
				while (numY < UniversalYPos + Gdx.graphics.getHeight() * 3);
				
				numX -= groundSquareDims;
			}
			while (numX > UniversalXPos);
			
			for (int i = UniversalXPos - groundSquareDims; i < UniversalXPos; i++)
				if (i % groundSquareDims == 0)
				{
					numX = i;
					break;
				}
			
			for (int i = UniversalYPos + Gdx.graphics.getHeight() - groundSquareDims; i < UniversalYPos + Gdx.graphics.getHeight() + groundSquareDims; i++)
				if (i % groundSquareDims == 0)
				{
					numY = i;
					break;
				}
			
			do
			{
				do 
				{
					if (!Contains(new GroundPiece(1, numX, numY), pieces))
					{
						pieces.add(new GroundPiece((int)Math.round(Math.random() * 4), numX, numY));
					}
					
					numX += groundSquareDims;
				}
				while (numX < UniversalXPos + Gdx.graphics.getWidth() * 3);
				
				numY -= groundSquareDims;
			}
			while (numY > UniversalYPos);
		}*/
	}
	
	public void Update(int UniversalXPos, int UniversalYPos)
	{
		UpdateRender(UniversalXPos, UniversalYPos);
		
		for (int i = UniversalXPos - groundSquareDims; i < UniversalXPos; i++)
			if (i % groundSquareDims == 0)
			{
				numX = i;
				break;
			}
		for (int i = UniversalYPos - groundSquareDims; i < UniversalYPos; i++)
			if (i % groundSquareDims == 0)
			{
				numY = i;
				break;
			}
		
		while (numX < UniversalXPos + Gdx.graphics.getWidth() + groundSquareDims * 4)
		{
			while (numY < UniversalYPos + Gdx.graphics.getHeight())
			{
				if (!Contains(new GroundPiece(1, numX, numY), pieces))
				{
					pieces.add(new GroundPiece(1, numX, numY));
				}
				numY += groundSquareDims;
			}
			numX += groundSquareDims;
		}
		
		for (GroundPiece gp : pieces)
		{
			gp.Update(UniversalXPos, UniversalYPos);
		}
	}
	
	public void Draw(int ux, int uy)
	{
		for (GroundPiece gp : pieces)
		{
			batch.begin();
			batch.draw(gp.GroundTex, (gp.posX - gp.universalX), (gp.posY - gp.universalY));
			batch.end();
		}
	}
	
	public boolean Contains(GroundPiece gp, ArrayList<GroundPiece> comparer)
	{
		boolean result = false;
		for (int i = 0; i < comparer.size() - 1; i ++)
		{
			if (gp.posX == comparer.get(i).posX)
				if(gp.posY == comparer.get(i).posY)
				{
					result = true;
				}
		}
		return result;
	}
}
