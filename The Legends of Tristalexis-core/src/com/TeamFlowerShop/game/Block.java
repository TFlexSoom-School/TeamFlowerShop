package com.TeamFlowerShop.game;

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

public class Block {
	SpriteBatch batch;
	
	Texture blockTex;
	
	Rectangle blockRect;
	
	public Block(Rectangle BlockRect)
	{
		batch = new SpriteBatch();
		
		blockRect = BlockRect;
		
		blockTex = new Texture("BLOCK.png");
	}
	
	public void Draw()
	{
		batch.begin();
		batch.draw(blockTex, blockRect.x, blockRect.y);
		batch.end();
	}
}
