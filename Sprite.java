/***************************
 * Purpose: Sprite class to handle essential
 * attributes of most ingame sprite objects.
 * 
 * Inherits from GameEntity
 *
 * Original Author: Zachary Johnson
 ***************************/

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sprite extends GameEntity
{
	public BufferedImage currentImage;
	public Rotation rotation;
	
	public Sprite(Point2D position)
	{
		this.mapPos = new Point2D(position);
		this.rotation = new Rotation(0);
		try
		{
			//this.currentImage = GameFunction.loadBufferedImage("TestTriangle.png");
			this.currentImage = GameFunction.loadBufferedImage("/resources/ships/debugship_blue.png");
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2d)
	{
		AffineTransform at = new AffineTransform();
		at.translate(this.mapPos.getX(), this.mapPos.getY());
		at.scale(0.5, 0.5);
		
		//DEBUG
		double angle = Math.atan2(this.mapPos.getX() - Controller.mousePos.getX(), Controller.mousePos.getY() - this.mapPos.getY());
		angle += Math.PI;
		at.rotate(angle, currentImage.getWidth() / 2, currentImage.getHeight() / 2);
		
		g2d.drawImage(currentImage, at, null);
	}
}
