package com.TeamFlowerShop.game;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.utils.Array;

public class Level {

	
	public static String level1 = "O O O O O O O O O O O O O O O;"
				  		 		+ "O E X O O X X X X X X X O O O;"
				  		 		+ "O E X X O X X O O O O O O E O;"
				  		 		+ "O X X X O X X X X X X O X X O;"
				  		 		+ "X O X X O X X O O X X O X X O;"
				  		 		+ "X O X X K X X X O X X X X X O;"
				  		 		+ "X O X X O O O X O X X X X E O;"
				  		 		+ "X O X X X X X H O X X O O O O;"
				  		 		+ "X O E X X X O X O X X O E E O;"
				  		 		+ "X O O O O O O X O X X O X X O;"
				  		 		+ "X X X X X X O X O O X X X X O;"
				  		 		+ "O O O O O O X X X O O O O O O;"
				  		 		+ "O E X X X X X X X O X X X E O;"
				  		 		+ "O E X X X X X X X X X X X E O;"
				  		 		+ "O O O O O O O O O O O O O O O;";
	
	public static String WallPiece = "O";
	public static String EmptyGround = "X";
	public static String EnemySpawnPoint = "E";
	public static String HeroSpawnPoint = "H";
	public static String GoalPoint = "K";
	public static ArrayList<ArrayList<String>> Rows = new ArrayList<ArrayList<String>>();
	
	public static ArrayList<ArrayList<String>> GetLevel(int levelNumber)
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
		
		return Rows;
	}
}
