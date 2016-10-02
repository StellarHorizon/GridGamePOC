import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sprite extends GameEntity
{
	public BufferedImage currentImage;
	
	public Sprite(Point2D position)
	{
		this.pos = new Point2D(position);
		try
		{
			this.currentImage = GameFunction.loadBufferedImage("TestTriangle.png");
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g)
	{
		g.drawImage(currentImage, (int) this.pos.getX(), (int) this.pos.getY(), null);
	}
}
