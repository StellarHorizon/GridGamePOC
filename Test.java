/***************************
 * Purpose: Test class to check Java implementation of ideas
 * Original Author: Zachary Johnson
 ***************************/
import java.lang.Integer;
import java.lang.Class;
import java.lang.*;
import java.awt.Graphics;
import java.awt.Rectangle;

class Test
{
	public static void main(String[] args)
	{
		//Graphics g = new Graphics();
		//int a = 5 / 2.0;
		double b = 5 / 2.0;
		double c = (int)5.0 / (2 + 1.0);
		
		//p(a);
		p("" + b);
		p("" + c);
	}
	
	// public static void testDraw(Graphics g)
	// {
		// g.drawRect(new Rectangle(500, 500));
	// }
	
	
	public static void p(String s)
	{
		System.out.println(s);
	}
}