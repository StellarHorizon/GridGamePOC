/***************************
 * Purpose: Class containing common static functions
 * used throughout the program
 *
 * Original Author: Zachary Johnson
 ***************************/

import java.awt.*;
import java.lang.Exception;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.io.IOException;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
 
public final class GameFunction
{
	private GameFunction(){}
	
	public static void drawRect(Graphics g, Rectangle rect)
	{
		g.drawRect((int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
	}
	
	public static Image loadImage(String filename) throws IOException
	{
		Image img = ImageIO.read(GameFunction.class.getResource(filename));
		
		return img;
	}
	
	public static File loadFile(String filename) throws IOException, URISyntaxException
	{
		URL url = GameFunction.class.getResource(filename);
		
		File file = new File(url.toURI());
		//File file = new File(GameFunction.class.getResourceAsStream(filename));
		
		return file;
	}
	
	public static BufferedImage loadBufferedImage(String filename) throws IOException
	{
		return (BufferedImage) loadImage(filename);
	}
}