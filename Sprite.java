/***************************
 * Purpose: Sprite class to handle essential
 * attributes of most ingame sprite objects.
 * 
 * Inherits from GameEntity
 *
 * Original Author: Zachary Johnson
 ***************************/

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
		this.rotation = new Rotation(75);
		try
		{
			this.currentImage = GameFunction.loadBufferedImage("TestTriangle.png");
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
		//at.rotate(Math.toRadians(this.rotation.getRotation()));
		
		//DEBUG
		double angle = Math.atan2(this.mapPos.getX() - Controller.mousePos.getX(), Controller.mousePos.getY() - this.mapPos.getY());
		at.rotate(angle);
		
		g2d.drawImage(currentImage, at, null);
		//g.drawImage(currentImage, (int) this.mapPos.getX(), (int) this.mapPos.getY(), null);
	}
}
