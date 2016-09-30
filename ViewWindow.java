/***************************
 * Purpose: ViewWindow objects represent a rectangular region
 * of the screen the map is drawn onto
 *
 * Objects contain necessary variables related to correct
 * operations
 *
 * Original Author: Zachary Johnson
 ***************************/

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class ViewWindow
{
	Model model;
	
	public Point2D virtualPos;
	public Rectangle screenDrawArea;
	//public Point2D screenPos;
	//public Point2D viewScreenDimensions;
	public int magnification;			//Value is true magnification multiplied by 10

	/***************************
	 * Constructor
	 ***************************/
	ViewWindow(Model m)
	{
		this.model = m;
		
		this.magnification = 10;
		
		virtualPos = new Point2D(100,0); //TEMPORARY HARDCODED
		
		//his.screenPos = new Point2D(500, 200);
		
		screenDrawArea = new Rectangle(200, 200, 1500, 1500); //Temporary hardcoded
		
		//viewScreenDimensions = new Point2D(1500, 1500); //TEMPORARY HARDCODED
	}
	
	public Point2D getScreenPos()
	{
		return new Point2D(screenDrawArea.getX(), screenDrawArea.getY());
	}
	
	public Point2D getSize()
	{
		return new Point2D(screenDrawArea.getWidth(), screenDrawArea.getHeight());
	}
	
	//Set size starting from upper left corner
	public void setSize(Point2D newSize)
	{
		this.screenDrawArea = new Rectangle((int)screenDrawArea.getX(), (int)screenDrawArea.getY(), (int)newSize.getX(), (int)newSize.getY());
	}
	
	public void setPos(Point2D newPos)
	{
		this.screenDrawArea = new Rectangle((int)newPos.getX(), (int)newPos.getY(), (int)screenDrawArea.getWidth(), (int)screenDrawArea.getHeight());
	}
	
	/***************************
	 * Drawing function to draw the contents of the window
	 ***************************/
	public void draw(Graphics g)
	{
		//Call gameMap drawing function that supports ViewWindow style drawing
		////this.model.gameMap.drawView(g, this.screenPos, this.virtualPos, this.viewScreenDimensions, magnification);
		this.model.gameMap.drawView(g, this.screenDrawArea, this.virtualPos, magnification);
		
		//Draw debugging rectangle to ensure proper drawing occurred
		g.setColor(Color.RED);
		GameFunction.drawRect(g, screenDrawArea);
		//g.drawRect((int)this.screenPos.getX(), (int)this.screenPos.getY(), (int)this.viewScreenDimensions.getX(), (int)this.viewScreenDimensions.getY());
	}
	
	/***************************
	 * Function for panning view of map within the window, called by a controller
	 ***************************/
	public void panWindow(Point2D amount)	//Overload += operator?
	{
		//Adjust the map location the window is viewing
		this.virtualPos.setX((int) (this.virtualPos.getX() - (amount.getX() * magnification * GameConstant.scrollSpeed / 100.0)));
		this.virtualPos.setY((int) (this.virtualPos.getY() - (amount.getY() * magnification * GameConstant.scrollSpeed / 100.0)));
		
		//Error checking for moving the map around
		if (this.virtualPos.getX() <= 0.0)
			this.virtualPos.setX(0);
		if (this.virtualPos.getY() <= 0.0)
			this.virtualPos.setY(0);
	}
}