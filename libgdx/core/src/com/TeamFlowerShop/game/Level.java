package com.TeamFlowerShop.game;

import java.util.ArrayList;
import java.util.Arrays;

public class Level {

	
	public static String level1 = "O O O O O O O O O O O O O O O;"
				  		 		+ "O E X O O X X X X X X X O O O;"
				  		 		+ "O E X X O X X O O O O O O E O;"
				  		 		+ "O X X X O X X X X X X O X X O;"
				  		 		+ "X O X X O X X O O X X O X X O;"
				  		 		+ "X O X X K O X X O X X X X X O;"
				  		 		+ "X O X X O O O X O X X X X E O;"
				  		 		+ "X O X X X X X X O X X O O O O;"
				  		 		+ "X O E X X X O H O X X O E E O;"
				  		 		+ "X O O O O O O X O X X O X X O;"
				  		 		+ "X X X X X X O X O O X X X X O;"
				  		 		+ "O O O O O O X X X O O O O O O;"
				  		 		+ "O E X X X X X X X O X X X E O;"
				  		 		+ "O E X X X X X X X X X X X E O;"
				  		 		+ "O O O O O O O O O O O O O O O;";
	
	public enum Pieces{
		Wall,
		EmptyGround,
		EnemySpawnPoint,
		HeroSpawnPoint,
		GoalPoint,
		Unknown;
	}
	
	private ArrayList<ArrayList<String>> Rows = new ArrayList<ArrayList<String>>();
	private int originXOffset = 5;
	private int originYOffset = 5;
	
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
		//Cycle through rows in reverse so that maps can be edited in readible format
		//while level class takes care putten the last row as the first because
		//the bottom left corner is the origin.
		for(int i=rows.length-1; i > -1; i-- )
		{
			ArrayList<String> blocks = new ArrayList<String>(Arrays.asList(rows[i].split(" ")));
			Rows.add(blocks);
		}		
	}
	
	public Pieces GetPieceName(int X, int Y)
	{
		int row = (Y / 128) + originYOffset;
		int collumn = (X / 128) + originXOffset;
		String pieceName = null;
		
		try
		{
			pieceName = Rows.get(row).get(collumn);
		}
		catch(IndexOutOfBoundsException e)
		{
			pieceName = "OutOfMapBounds";
		}
		
		if(pieceName.equalsIgnoreCase("O"))
		{
			return Pieces.Wall;
		}
		if(pieceName.equalsIgnoreCase("X"))
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
