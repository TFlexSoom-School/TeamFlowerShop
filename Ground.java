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
	
	int groundSquareDims = 128;
	public void Update(int UniversalXPos, int UniversalYPos)
	{
		int Xnum = 0, Ynum = 0;
		for (int i = UniversalXPos - groundSquareDims; i < UniversalXPos; i++)
		{
			if (i % groundSquareDims == 0)
				Xnum = i;
		}
		for (int i = UniversalYPos - groundSquareDims; i < UniversalYPos; i++)
		{
			if (i % groundSquareDims == 0)
				Ynum = i;
		}
		for (int i = Xnum; i < (Xnum + Gdx.graphics.getWidth() 
				+ groundSquareDims); i += groundSquareDims)
		{
			for (int j = Ynum; j < (Ynum + Gdx.graphics.getHeight() 
					+ groundSquareDims); j += groundSquareDims)
			{
				if (!Contains(new GroundPiece(1, i, j), pieces))
				{
					pieces.add(new GroundPiece(3, i, j));
					System.out.println(pieces.size());
					System.out.println(Contains(new GroundPiece(3, i, j), pieces));
				}
			}
		}
		//pieces.add(new GroundPiece(1, 0, 0));
		
		for (GroundPiece gp : pieces)
		{
			gp.Update(UniversalXPos, UniversalYPos);
		}
	}
	
	public void Draw()
	{
		for (GroundPiece gp : pieces)
		{
			batch.begin();
			batch.draw(gp.GroundTex, gp.posX + gp.universalX, gp.posY + gp.universalY);
			batch.end();
		}
	}
	
	public boolean Contains(GroundPiece gp, ArrayList<GroundPiece> comparer)
	{
		boolean result = false;
		for (int i = 0; i < comparer.size() - 1; i ++)
		{
			gp.universalX = comparer.get(i).universalX;
			gp.universalY = comparer.get(i).universalY;
			gp.GroundTex = comparer.get(i).GroundTex;
			
			if (gp == comparer.get(i))
			{
				result = true;
			}
		}
		return result;
	}
}
