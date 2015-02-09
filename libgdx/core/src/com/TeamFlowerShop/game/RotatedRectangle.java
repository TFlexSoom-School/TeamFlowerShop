package com.TeamFlowerShop.game;

import java.awt.Point;
import java.util.*;
import com.badlogic.gdx.math.*;

public class RotatedRectangle {
	
	public class LinearEquation
	{
		float slope = 0;
		float yint = 0;
		
		public LinearEquation (Point p1, Point p2)
		{
			slope = (p1.y - p2.y) / (p1.x - p2.x);
			
			yint = p1.y - (slope * p1.x);
		}
		
		public float f (float x)
		{
			return (slope * x) + yint;
		}
	}
	
	Point P_A, P_B, P_C, P_D;
	ArrayList<Point> rectPoints;
	ArrayList<Integer> rectPointsX;
	ArrayList<Integer> rectPointsY;
	float X, Y;
	float Rotation;
	float Width, Height;

	LinearEquation f1, f2, f3, f4;
	
	public RotatedRectangle(float x, float y, float width, float height, float rotation)//origin at center of rectangle automatically
	//because im too lazy
	{
		P_A = new Point((int)((Math.cos(rotation) + x) * width) + 10, (int)((Math.sin(rotation) + y) * height) + 10);
		P_B = new Point(-(int)((Math.cos(rotation) + x) * width) + 10, -(int)((Math.sin(rotation) + y) * height) + 10);
		P_C = new Point(-(int)((Math.cos(rotation) + x) * width) - 10, -(int)((Math.sin(rotation) + y) * height) - 10);
		P_D = new Point((int)((Math.cos(rotation) + x) * width) - 10, (int)((Math.sin(rotation) + y) * height) - 10);
		
		X = x;
		Y = y;
		Rotation = rotation;
		Width = width;
		Height = height;
		
		f1 = new LinearEquation(P_A, P_B);
		f2 = new LinearEquation(P_B, P_C);
		f3 = new LinearEquation(P_C, P_D);
		f4 = new LinearEquation(P_D, P_A);
	}
	
	public void Update()
	{
		P_A = new Point((int)((Math.cos(Rotation) + X) * Width) + 10, (int)((Math.sin(Rotation) + Y) * Height) + 10);
		P_B = new Point(-(int)((Math.cos(Rotation) + X) * Width) + 10, -(int)((Math.sin(Rotation) + Y) * Height) + 10);
		P_C = new Point(-(int)((Math.cos(Rotation) + X) * Width) - 10, -(int)((Math.sin(Rotation) + Y) * Height) - 10);
		P_D = new Point((int)((Math.cos(Rotation) + X) * Width) - 10, (int)((Math.sin(Rotation) + Y) * Height) - 10);
		
		f1 = new LinearEquation(P_A, P_B);
		f2 = new LinearEquation(P_B, P_C);
		f3 = new LinearEquation(P_C, P_D);
		f4 = new LinearEquation(P_D, P_A);
	}

	public boolean Contains(Point p)
	{
		boolean result = false;
		
		if (((f1.f(p.x) > p.y && f3.f(p.x) < p.y) ||
			(f1.f(p.x) < p.y && f3.f(p.x) > p.y)) &&
			((f2.f(p.x) > p.y && f4.f(p.x) < p.y) ||
			(f2.f(p.x) < p.y && f4.f(p.x) > p.y)))
		{
			result = true;
		}
		
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
		
		if (rr.Contains(P_A) || rr.Contains(P_B) || rr.Contains(P_C) || rr.Contains(P_D))
			result = true;
		
		return result;
	}
}