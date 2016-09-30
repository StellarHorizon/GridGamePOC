/***************************
 * Purpose: MapCell class representing a single cell on the GameMap,
 * contains relevant information about the cell including type,
 * position on map, and images used to draw
 *
 * Original Author: Zachary Johnson
 ***************************/

import java.awt.Color;
import java.awt.Image;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class MapCell
{
	//The cell's position on the map
// 	public static double cellLength = 256.0; //TEMPORARY HARDCODED
	//public static double cellLength = 50.0; //TEMPORARY HARDCODED
	private GameMap parent;
	public static double cellLength = GameConstant.cellLength;
	private Image currentImage = null;
	// private static String debugImagePath = "TestTriangle.png"; //To be default image if description doesn't match a cell type
	// private static String resourcePath[] = {"dirt.png", "grass.jpg", "looserocks.png", "water.jpg"}; //TEMPORARY HARDCODED
	// private static Image pictures[];
	// private static Image debugImage;
	public int x, y; //Cell position on map
	//public int borderSize = 10; //TEMPORARY HARDCODED
	String description;
	
	/***************************
	 * Constructor
	 ***************************/
	MapCell(int posX, int posY, String stringDescription, GameMap map) throws IOException
	{
		this.x = posX;
		this.y = posY;
		this.parent = map;
		
		//Load image resources if not already done
		if (parent.pictures == null)
		{
			parent.pictures = new Image[parent.resourcePath.length];
			
			for (int i = 0; i < parent.resourcePath.length; i++)
			{
				if (parent.pictures[i] == null)
				{
					parent.pictures[i] = loadImage(parent.resourcePath[i]);
					BufferedImage bimg = GameFunction.loadBufferedImage(GameMap.resourcePath[i]);
					//this.cellLength = bimg.getWidth();
					System.out.println("Loaded " + parent.resourcePath[i]);
				}
			}
		}
		
		if (parent.debugImage == null)
			parent.debugImage = loadImage(parent.debugImagePath);
		
		//Set map cell description
		this.description = stringDescription;
		
		//Parse description to set characteristics of the map cell
		parseDescription();
	}
	
	private boolean parseDescription()
	{
		if (this.description.contains("0"))
		{
			this.currentImage = parent.pictures[0];
		}
		else if (this.description.contains("1"))
		{
			this.currentImage = parent.pictures[1];
		}
		else if (this.description.contains("2"))
		{
			this.currentImage = parent.pictures[2];
		}
		else if (this.description.contains("3"))
		{
			this.currentImage = parent.pictures[3];
		}
		else
		{
			//Use the default image
			this.currentImage = parent.pictures[0];
			this.currentImage = parent.debugImage;
		}
		
		return true;
	}
	
	/***************************
	 * Getter methods
	 ***************************/
	public String getDescription() { return this.description; }
	//public int getBorderSize() { return this.borderSize; }
	
	public Image getImage()
	{
		if (this.currentImage != null)
			return this.currentImage;
		else
			return parent.pictures[0];
	}
	
	/***************************
	 * Setter methods
	 ***************************/
	public void setDescription(String input)
	{
		this.description = input;
		
		parseDescription();
	}
	
	/***************************
	 * Utility methods
	 ***************************/
	public String print() { return this.description; }
	
	
	//CHANGE TO USE UNIVERSAL FUNCTION! (Also be sure to remember to also set default picture)
	private Image loadImage(String filename) throws IOException
	{
		Image img = GameFunction.loadImage(filename);
		
		if (img != null && this.currentImage == null)
			currentImage = img;
		
		return img;
	}
}