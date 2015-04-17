package com.TeamFlowerShop.game;

import java.util.ArrayList;
import java.util.Arrays;

public class Level {

	
	public String level1 = "O O O O O O O O O O O O O O O;"
				  		 + "O E X K O X X X X X X X O O O;"
				  		 + "O E X X O X X O O O O O O E O;"
				  		 + "O X X X O X X X X X X O X X O;"
				  		 + "X O X X O X X O O X X O X X O;"
				  		 + "X O X X O X X X O X X X X X O;"
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
	public ArrayList<ArrayList<String>> Rows = new ArrayList<ArrayList<String>>();
	
	public void populateLevel()
	{
		String [] rows = level1.split(";");
		for(String r : rows)
		{
			ArrayList<String> blocks = new ArrayList<String>(Arrays.asList(r.split(" ")));
			Rows.add(blocks);
		}
	}
}
