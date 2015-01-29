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
		for (int x = UniversalXPos - groundSquareDims; x < UniversalXPos + Gdx.graphics.getWidth() + groundSquareDims; x++)
		{
			if (x % groundSquareDims == 0)
			{
				for (int y = UniversalYPos - groundSquareDims; y < UniversalYPos + Gdx.graphics.getHeight() + groundSquareDims; y++)
				{
					if (y % groundSquareDims == 0)
					{
						if (!Contains(new GroundPiece(1, x, y), pieces))
						{
							pieces.add(new GroundPiece((int)Math.round(Math.random() * 4), x, y));
							System.out.println(pieces.size());
						}
					}
				}
			}
		}
		
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
			batch.draw(gp.GroundTex, (gp.posX + gp.universalX), (gp.posY + gp.universalY));
			batch.end();
		}
	}
	
	public boolean Contains(GroundPiece gp, ArrayList<GroundPiece> comparer)
	{
		boolean result = false;
		for (int i = 0; i < comparer.size() - 1; i ++)
		{
			if (gp.posX == comparer.get(i).posX &&
				gp.posX == comparer.get(i).posX)
			{
				result = true;
			}
		}
		return result;
	}
}
