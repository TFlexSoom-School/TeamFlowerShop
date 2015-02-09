package com.TeamFlowerShop.game;

import java.awt.Point;
import java.util.*;
import com.badlogic.gdx.math.*;

public class RotatedRectangle {
	
	Point P_A, P_B, P_C, P_D;
	ArrayList<Point> rectPoints;
	ArrayList<Integer> rectPointsX;
	ArrayList<Integer> rectPointsY;
	
	public RotatedRectangle(float x, float y, float width, float height, float rotation)//origin at center of rectangle automatically
	//because im too lazy
	{
		P_A = new Point();
		P_A.x = (int)(x - (Math.cos(rotation) * width));
		P_A.y = (int)(y - (Math.sin(rotation) * height));

		P_B = new Point();
		P_B.x = (int)(x + (Math.cos(rotation) * width));
		P_B.y = (int)(y + (Math.sin(rotation) * height));

		P_C = new Point();
		P_C.x = (int)(x - (Math.cos(rotation) * width)) + 10;
		P_C.y = (int)(y - (Math.sin(rotation) * height)) + 10;

		P_D = new Point();
		P_D.x = (int)(x + (Math.cos(rotation) * width)) + 10;
		P_D.y = (int)(y + (Math.sin(rotation) * height)) + 10;
		//dont ask whats going on here i barely know even though i wrote the code
		
		rectPoints = new ArrayList<Point>();
		rectPoints.add(P_A);
		rectPoints.add(P_B);
		rectPoints.add(P_C);
		rectPoints.add(P_D);
		
		for (Point p : rectPoints)
		{
			rectPointsX.add(p.x);
			rectPointsY.add(p.y);
		}
	}

	public boolean Contains(Point p)
	{
		boolean result = false;
		
		
		
		return result;
	}
	
	public boolean Overlaps(Rectangle r)
	{
		boolean result = false;
		for (Point p : rectPoints)
		{
			if (r.contains(p.x, p.y))
			{
				result = true;
			}
		}
		return result;
	}

	public boolean Overlaps(RotatedRectangle rr)
	{
		boolean result = false;
		
		return result;
	}
}
