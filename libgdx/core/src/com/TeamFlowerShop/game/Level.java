package com.TeamFlowerShop.game;

import java.util.ArrayList;
import java.util.Arrays;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.*;

public class Level {

	
	public static String level1 = "X X X X X X X X X X X X X X X X X X X X X X X X X X X X X;"
				  		 		+ "X E O O X O O O O O O O O O O O O O O O O X O O O O E E X;"
				  		 		+ "X O O O X O O O O O O O O O O O O O O O O X O O O O O O X;"
				  		 		+ "X O O O X O O X X X X X X X X X X O O O O O O O X O O O X;"
				  		 		+ "X O O X X O O X O O O O O O O O X E E O O O O O X K O O X;"
				  		 		+ "X O O O X O O X O O O O O O O O X X X X X X X X X X O O X;"
				  		 		+ "X O O O O O O X O O O O O O O O X E O O O X O E E X O O X;"
				  		 		+ "X O O O O O O X O O O E O O O O X O O O O X O O O X O O X;"
				  		 		+ "X O O O X O O X O O O O O O O O X O O O O O O O O X O O X;"
				  		 		+ "X E O O X O O X O O O O O O O O X O O O O O O O O X O O X;"
				  		 		+ "X X X X X O O X X X X O O X X X X O O O O X O O O X O O X;"
				  		 		+ "X O O O O O O O E X O O O O X O O O O O X X O O O X O O X;"
				  		 		+ "X O O O O O O O O O O H O O O O O O O E X X O O O X O O X;"
				  		 		+ "X O O O O O O O O O O O O O O O O X X X X X O O O X O O X;"
				  		 		+ "X O O O O O O O O X O O O O X O O X O E E X O O O X O O X;"
				  		 		+ "X O O X X X X O O X X O O X X O O X O O O X O O O X O O X;"
				  		 		+ "X O O X O O O O O O X O O O X O O O O O O X O O O X O O X;"
				  		 		+ "X E E X O O O O O O X O O O X O O O O O O X O O O X O O X;"
				  		 		+ "X X X X O O O O O O O O O O X X X X X X X X X O O X O O X;"
				  		 		+ "X X X X O O O O O O O O O O O O O O X E O O O O O O O O X;"
				  		 		+ "X X X X E E O O O O O O O O O O E E X E O O O O O O O O X;"
				  		 		+ "X X X X X X X X X X X X X X X X X X X X X X X X X X X X X;";
	
	public enum Pieces{
		Wall,
		EmptyGround,
		EnemySpawnPoint,
		HeroSpawnPoint,
		GoalPoint,
		Unknown;
	}
	
	private ArrayList<ArrayList<String>> Rows = new ArrayList<ArrayList<String>>();
	
	private int originXOffset = 12-((Gdx.graphics.getWidth()/128)/2);
	private int originYOffset = 9-((Gdx.graphics.getHeight()/128)/2);
	
	public int getOriginXOffset() {
		return originXOffset;
	}

	public void setOriginXOffset(int originXOffset) {
		this.originXOffset = originXOffset;
	}

	public int getOriginYOffset() {
		return originYOffset;
	}

	public void setOriginYOffset(int originYOffset) {
		this.originYOffset = originYOffset;
	}

	private int pieceSize = 128;
	
	public static Level GetLevel(int levelNumber)
	{
		return new Level(levelNumber);
	}
	
	private Level(int levelNumber)
	{
		String levelString = null;
	
		if(levelNumber == 1)
		{
			levelString = level1;
		}
		String [] rows = levelString.split(";");
		//Cycle through rows in reverse so that maps can be edited in readable format
		//while level class takes care putting the last row as the first because
		//the bottom left corner is the origin.
		for(int i=rows.length-1; i > -1; i-- )
		{
			ArrayList<String> blocks = new ArrayList<String>(Arrays.asList(rows[i].split(" ")));
			Rows.add(blocks);
		}		
	}
	
	public boolean OverlapsPieceType(Rectangle r, Pieces type)
	{
		//bottom left
		if(GetPieceName(r.x, r.y) == type)
		{
			return true;
		}
		
		//bottom right
		if(GetPieceName((r.x + r.width), r.y) == type)
		{
			return true;
		}
		
		//top left
		if(GetPieceName(r.x, (r.y + r.height)) == type)
		{
			return true;
		}
		
		//top right
		if(GetPieceName((r.x + r.width), (r.y + r.height)) == type)
		{
			return true;
		}
		
		return false;
	}
	
	public Pieces GetPieceName(float X, float Y)
	{
		return GetPieceName((int)X, (int)Y);
	}
	
	public Pieces GetPieceName(int X, int Y)
	{
		int row, collumn;
		
		if((Y % pieceSize) != 0 && Y < 0)
		{
			row = ((Y - (pieceSize + (Y % pieceSize))) / pieceSize) + originYOffset;
		}
		else
		{
			row = (Y / pieceSize) + originYOffset;
		}

		if((X % pieceSize) != 0 && X < 0)
		{
			collumn = ((X - (pieceSize + (X % pieceSize))) / pieceSize) + originXOffset;
		}
		else
		{
			collumn = (X / pieceSize) + originXOffset;
		}
		
		String pieceName = null;
		
		try
		{
			pieceName = Rows.get(row).get(collumn);
		}
		catch(IndexOutOfBoundsException e)
		{
			pieceName = "OutOfMapBounds";
		}
		
		if(pieceName.equalsIgnoreCase("X"))
		{
			return Pieces.Wall;
		}
		if(pieceName.equalsIgnoreCase("O"))
		{
			return Pieces.EmptyGround;
		}
		if(pieceName.equalsIgnoreCase("E"))
		{
			return Pieces.EnemySpawnPoint;
		}
		if(pieceName.equalsIgnoreCase("H"))
		{
			return Pieces.HeroSpawnPoint;
		}
		if(pieceName.equalsIgnoreCase("K"))
		{
			return Pieces.GoalPoint;
		}
		else 
		{
			return Pieces.Unknown;
		}
	}
}
