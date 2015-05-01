package com.TeamFlowerShop.game;

import java.awt.Point;
import java.util.*;
import java.util.ArrayList;

import com.TeamFlowerShop.game.Level.Pieces;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Ground {
	SpriteBatch batch;
	ArrayList<GroundPiece> pieces;
	Texture[] ground;
	Level currentLevel;
	
	public Ground(Level level)
	{
		batch = new SpriteBatch();
		pieces = new ArrayList<GroundPiece>();
		currentLevel = level;
		ground = new Texture[] {new Texture("gsquare1w.png"), new Texture("gsquare2.png"), 
			new Texture("gsquare3e.png"), new Texture("gsquare4h.png"), new Texture("gsquare1g.png"), new Texture("gsquare1.png")};
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
		//returns the closest point whose XY coordinates are both divisible by
		//128 off the lower left hand side of the screen
		return new Point(numX, numY);
	}
	
	public GroundPiece getGroundPiece(int X, int Y)
	{	
		Pieces pieceName = currentLevel.GetPieceName(X,Y);

		switch(pieceName) {
			case Wall:
				return new GroundPiece(1, X, Y);
			case EmptyGround:
				return new GroundPiece(2, X, Y);
			case EnemySpawnPoint:
				return new GroundPiece(3, X, Y);				
			case HeroSpawnPoint:
				return new GroundPiece(4, X, Y);
			case GoalPoint:
				return new GroundPiece(5, X, Y);
			case Unknown: 
				return new GroundPiece(6, X, Y);		
		}
		return new GroundPiece(6, X, Y);
	}
	
	public void UpdateRender(int UniversalXPos, int UniversalYPos)
	{
		numX = findClosestXY(UniversalXPos, UniversalYPos).x;
		numY = findClosestXY(UniversalXPos, UniversalYPos).y;
		
		while (numY < UniversalYPos + Gdx.graphics.getHeight())
		{
			int tempX = numX; // Adding temporary variable otherwise only the outskirts get filled in
			while (tempX < UniversalXPos + Gdx.graphics.getWidth())
			{
				if (!Contains(new GroundPiece(1, tempX, numY), pieces))
				{
					pieces.add(getGroundPiece(tempX, numY));
				}
				tempX += groundSquareDims;
			}
			numY += groundSquareDims;
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
			batch.draw(ground[gp.GroundTex - 1], (gp.posX - gp.universalX), (gp.posY - gp.universalY));
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