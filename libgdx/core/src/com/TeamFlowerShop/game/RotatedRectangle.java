package com.TeamFlowerShop.game;

import java.util.*;

import com.badlogic.gdx.math.*;

public class RotatedRectangle {
	public class LinearEquation
	{
		float slope = 0;
		float yint = 0;
		
		public LinearEquation (Point p1, Point p2)
		{
			if (p1.x - p2.x != 0)
				slope = (p1.y - p2.y) / (p1.x - p2.x);
			else
				slope = 100000;
			
			yint = p1.y - (slope * p1.x);
		}
		
		public float f (float x)
		{
			return (slope * x) + yint;
		}
	}
	
	Point P_A, P_B, P_C, P_D;
	ArrayList<Point> rectPoints;
	float X, Y;
	float Rotation;
	float Width, Height;
	float xHeight, yHeight;

	LinearEquation f1, f2, f3, f4;
	
	public RotatedRectangle(float x, float y, float width, float height, float rotation)//origin at center of rectangle automatically
	//because im too lazy
	{
		xHeight = (float)Math.cos(rotation + Math.toRadians(90)) * height;
		yHeight = (float)Math.sin(rotation + Math.toRadians(90)) * height;
		
		P_A = new Point((int)((Math.cos(rotation) + x) * width) + xHeight, (int)((Math.sin(rotation) + y) * width) + yHeight);
		P_B = new Point(-(int)((Math.cos(rotation) + x) * width) + xHeight, -(int)((Math.sin(rotation) + y) * width) + yHeight);
		P_C = new Point(-(int)((Math.cos(rotation) + x) * width) + xHeight, -(int)((Math.sin(rotation) + y) * width) + yHeight);
		P_D = new Point((int)((Math.cos(rotation) + x) * width) + xHeight, (int)((Math.sin(rotation) + y) * width) + yHeight);
		
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
		P_A = new Point((int)((Math.cos(Rotation) + X) * Width) + xHeight, (int)((Math.sin(Rotation) + Y) * Width) + yHeight);
		P_B = new Point(-(int)((Math.cos(Rotation) + X) * Width) + xHeight, -(int)((Math.sin(Rotation) + Y) * Width) + yHeight);
		P_C = new Point(-(int)((Math.cos(Rotation) + X) * Width) - xHeight, -(int)((Math.sin(Rotation) + Y) * Width) - yHeight);
		P_D = new Point((int)((Math.cos(Rotation) + X) * Width) - xHeight, (int)((Math.sin(Rotation) + Y) * Width) - yHeight);
		
		P_A = new Point(-5, 5);
		P_B = new Point(5, 5);
		P_C = new Point(5, -5);
		P_D = new Point(-5, -5);
		
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
			System.out.println("true");
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