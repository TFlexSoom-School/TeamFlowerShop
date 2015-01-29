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
		for (float i = UniversalXPos; i < (Gdx.graphics.getWidth() + UniversalXPos) / groundSquareDims; i++)
		{
			for (float j = UniversalYPos; j < (Gdx.graphics.getHeight() + UniversalYPos) / groundSquareDims; j++)
			{
				
				/*if (!pieces.contains(new GroundPiece(1, (i * groundSquareDims), (j * groundSquareDims))) && 
						!pieces.contains(new GroundPiece(2, (i * groundSquareDims), (j * groundSquareDims))) 
						&& !pieces.contains(new GroundPiece(3, (i * groundSquareDims), (j * groundSquareDims))) 
						&& !pieces.contains(new GroundPiece(4, (i * groundSquareDims), (j * groundSquareDims))))
				{
					pieces.add(new GroundPiece((int)Math.floor(4 * Math.random()), (i * groundSquareDims) + 
							UniversalXPos, (j * groundSquareDims) + UniversalYPos));
					System.out.println(pieces.size());
				}*/
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
}
