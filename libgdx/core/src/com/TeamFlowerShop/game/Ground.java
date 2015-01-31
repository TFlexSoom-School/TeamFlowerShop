package com.TeamFlowerShop.game;

import java.awt.Point;
import java.util.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
	
	public Point findClosestXY (int universalX, int universalY)
	{
		for (int i = universalX - groundSquareDims; i < universalX; i++)
			if (i % groundSquareDims == 0)
			{
				numX = i;
				break;
			}
		for (int i = universalY - groundSquareDims; i < universalY; i++)
			if (i % groundSquareDims == 0)
			{
				numY = i;
				break;
			}
		return new Point(numX, numY);//returns the closest point whose XY coordinates are both divisible by
									 //128 off the lower left hand side of the screen
	}
	
	public void UpdateRender(int UniversalXPos, int UniversalYPos)
	{
		numX = findClosestXY(UniversalXPos, UniversalYPos).x;
		numY = findClosestXY(UniversalXPos, UniversalYPos).y;
		
		while (numX < UniversalXPos + Gdx.graphics.getWidth())
		{
			while (numY < UniversalYPos + Gdx.graphics.getHeight())
			{
				if (!Contains(new GroundPiece(1, numX, numY), pieces))
				{
					pieces.add(new GroundPiece((int)Math.ceil(Math.random() * 4), numX, numY));
				}
				numY += groundSquareDims;
			}
			numX += groundSquareDims;
		}
		//////////////////////
		numX = findClosestXY(UniversalXPos, UniversalYPos).x;
		numY = findClosestXY(UniversalXPos, UniversalYPos).y;
		
		while (numY < UniversalYPos + Gdx.graphics.getHeight())
		{
			while (numX < UniversalXPos + Gdx.graphics.getWidth())
			{
				if (!Contains(new GroundPiece(1, numX, numY), pieces))
				{
					pieces.add(new GroundPiece((int)Math.ceil(Math.random() * 4), numX, numY));
				}
				numX += groundSquareDims;
			}
			numY += groundSquareDims;
		}
		/////////////////////////////////////////////////////////////////////////////////
		for (int i = UniversalXPos + Gdx.graphics.getWidth() + groundSquareDims; i > UniversalXPos + Gdx.graphics.getWidth(); i--)
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
		while (numX > UniversalXPos)
		{
			while (numY < UniversalYPos + Gdx.graphics.getHeight())
			{
				if (!Contains(new GroundPiece(1, numX, numY), pieces))
				{
					pieces.add(new GroundPiece((int)Math.ceil(Math.random() * 4), numX, numY));
				}
				numY += groundSquareDims;
			}
			numX -= groundSquareDims;
		}
		//////////////////////
		for (int i = UniversalXPos - groundSquareDims; i < UniversalXPos; i++)
			if (i % groundSquareDims == 0)
			{
				numX = i;
				break;
			}
		for (int i = UniversalYPos + Gdx.graphics.getHeight() + groundSquareDims; i > UniversalYPos + Gdx.graphics.getHeight(); i++)
			if (i % groundSquareDims == 0)
			{
				numY = i;
				break;
			}
		while (numY > UniversalYPos)
		{
			while (numX < UniversalXPos + Gdx.graphics.getWidth())
			{
				if (!Contains(new GroundPiece(1, numX, numY), pieces))
				{
					pieces.add(new GroundPiece((int)Math.ceil(Math.random() * 4), numX, numY));
				}
				numX += groundSquareDims;
			}
			numY -= groundSquareDims;
		}
	}
	
	public void Update(int UniversalXPos, int UniversalYPos)
	{
		UpdateRender(UniversalXPos, UniversalYPos);//updaterender adds pieces if they are on the screen.
		
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