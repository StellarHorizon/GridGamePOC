/***************************
 * Purpose: GameMap class representing the map for the game, with
 * a given rectangular height, width, containing unique cells
 *
 * Original Author: Zachary Johnson
 ***************************/

import java.io.*;
import java.util.Scanner;
import java.lang.Exception;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

import java.awt.Image;

public class GameMap
{
	String filename;
	
	public static String debugImagePath = "TestTriangle.png"; //To be default image if description doesn't match a cell type
	public static String resourcePath[] = {"resources/terrain/dirt.png", "resources/terrain/grass.jpg", "resources/terrain/looserocks.png", "resources/terrain/water.jpg"}; //TEMPORARY HARDCODED
	public static Image pictures[];
	public static Image debugImage;
	
	int mapCellsWidth;
	int mapCellsHeight;
	int mapStartX = 0;
	int mapStartY = 0;
	MapCell map[][];
	
	/***************************
	 * Constructor
	 ***************************/
	GameMap(String mapFile)
	{
		this.filename = mapFile;
		
		loadMap();
	}
	
	/***************************
	 * Validate map file, then filepath to map file to build the game map
	 ***************************/
	public void loadMap()
	{
		if (validateMapFile())
		{
			try
			{
				//Create empty map file
				map = new MapCell[mapCellsWidth][mapCellsHeight];
				
				//Load map with file data
				Scanner fileReader = new Scanner(GameFunction.loadFile(filename));
				fileReader.useDelimiter("\\s|\\p{Punct}\\s");	//If commas are used at end of cell descriptions they will not be read
				
				for (int currHeight = 0; currHeight < mapCellsHeight; currHeight++)
				{
					//Get the line of the map file
					String currLine = fileReader.nextLine();
					
					//Save the contents of the line into the array
					Scanner lineReader = new Scanner(currLine);
					lineReader.useDelimiter("\\s|\\p{Punct}\\s");
					for (int currWidth = 0; currWidth < mapCellsWidth; currWidth++)
					{
						String toInsert = lineReader.next();
						while (toInsert.equals(""))
							toInsert = lineReader.next();
						
						map[currWidth][currHeight] = new MapCell(currWidth, currHeight, toInsert, this);
					}
					lineReader.close();
				}
				
				fileReader.close();
				
				System.out.println("Map file loaded successfully!");
			}
			catch (Exception e)
			{
				System.out.println("Odd error while loading map data after file already validated!");
				e.printStackTrace();
			}
		}
		else
			System.out.println("Map file not valid!");
	}
	
	/***************************
	 * Function validates file to ensure it can be
	 * used for building a map
	 ***************************/
	private boolean validateMapFile()
	{
		int numLines = 0;
		
		System.out.print("Attempting to validate map \"" + filename + "\" from file...   ");
		
		try (Scanner reader = new Scanner(GameFunction.loadFile(filename)))
		{
			String testLine = null;
			
			//Get first line of file
			if (reader.hasNextLine())
			{
				testLine = reader.nextLine();
				if (testLine.length() == 0)
				{
					System.out.println("ERROR!");
					System.out.println("Map file invalid: cannot begin with a blank line!");
					return false;
				}
				
				numLines++;
			}
			
			//Get number of cells on first line
			int width = 0;
			for (int i = 0; i < testLine.length(); i++)
			{
				if (charStartsWord(i, testLine))
					width++;
			}
			
			//Read file
			while (reader.hasNextLine())
			{
				//Get next line
				testLine = reader.nextLine();
				numLines++;
				
				//Check to ensure line has same number of cells as first line
				int tempWidth = 0;
				for (int i = 0; i < testLine.length(); i++)
				{
					if (charStartsWord(i, testLine))
						tempWidth++;
				}
				
				
				//Map file not valid if number of cells on line is different
				if (tempWidth != width)
				{
					System.out.println("ERROR!");
					System.out.println("Map file not valid starting at line: " + numLines);
					return false;
				}
			}
			
			this.mapCellsWidth = width;
			this.mapCellsHeight = numLines;
			
			//If hasn't returned false, map is valid
			System.out.println("SUCCESS!");
			System.out.println("Map of size: " + this.mapCellsWidth + " x " + this.mapCellsHeight + " successfully loaded.");
			
			return true;
			
		}
		catch (Exception e)
		{
			System.out.println("Exception occurred while validating map file!");
			e.printStackTrace();
		}
		return false;
	}
	
	/***************************
	 * Debug function, prints content of map stored
	 * in memory to the console
	 ***************************/
	public void printMap()
	{
		System.out.println("Map File Contents\n");
		System.out.println("Map Size: " + mapCellsWidth + " x " + mapCellsHeight + "\n");
		
		//Print reference numbers
		for (int i = 0; i < mapCellsWidth; i++)
		{
			System.out.print("\t" + i);
		}
		
		System.out.println();
		
		//Print contents of the map cells
		for (int i = 0; i < mapCellsHeight; i++)
		{
			System.out.print(i);
			for (int j = 0; j < mapCellsWidth; j++)
			{
				System.out.print("\t" + map[j][i].print());
			}
			System.out.println();
		}
	}
	
	/***************************
	 * Helper function
	 * Returns true if char passed as input is a delimiter in file
	 ***************************/
	private boolean isDelimiter(char c)
	{
		switch (c)
		{
			case '\t':	return true;
			case ' ':	return true;
			case ',':	return true;
			
			default:	return false;
		}
	}
	
	/***************************
	 * Helper function
	 * Returns true if character at index is start of new "word",
	 * or first nondelimiter char in a sequence preceded by a delimiter or nothing
	 ***************************/
	private boolean charStartsWord(int index, String line)
	{
		return (index > 0  && !isDelimiter(line.charAt(index)) && isDelimiter(line.charAt(index - 1))) || (index == 0 && !isDelimiter(line.charAt(index)));
	}
	
	/***************************
	 * Drawing function to draw the game map within the ViewWindow passed as parameters
	 ***************************/
	public void drawView(Graphics g, Rectangle screenDrawArea, Point2D windowMapPos, int magnification)
	{
		Point2D windowScreenSize = new Point2D(screenDrawArea.getWidth(), screenDrawArea.getHeight());
		Point2D screenPos = new Point2D(screenDrawArea.getX(), screenDrawArea.getY());
		
		//Determine cell length
		int cellLength = (int) (MapCell.cellLength * magnification / 10.0);
		
		//Determine minimum cell to draw, never below 0
		int minCellX = (int)(windowMapPos.getX() / cellLength);
		int minCellY = (int)(windowMapPos.getY() / cellLength);
		
		//Error checking
		if (minCellX < 0)
			minCellX = 0;
		if (minCellY < 0)
			minCellY = 0;
		
		//Determine maximum cell to draw (subtract 1 since minimum cells already counted), never above maximum cells on map
		int maxCellX = minCellX + (int)Math.ceil(windowScreenSize.getX() / (cellLength * magnification / 10.0)) - 1;
		int maxCellY = minCellY + (int)Math.ceil(windowScreenSize.getY() / (cellLength * magnification / 10.0)) - 1;
		
		//Error checking
		if (maxCellX > mapCellsWidth - 1)
			maxCellX = mapCellsWidth - 1;
		if (maxCellY > mapCellsHeight - 1)
			maxCellY = mapCellsHeight - 1;
		
		//Draw the map cells
		for (int currX = minCellX; currX <= maxCellX; currX++)
		{
			for (int currY = minCellY; currY <= maxCellY; currY++)
			{
				//Determine translated min and max bounds for the cell
				int cellStartX = (currX * cellLength) - (int)windowMapPos.getX() + (int)screenPos.getX();
				int cellStartY = (currY * cellLength) - (int)windowMapPos.getY() + (int)screenPos.getY();
				
				int cellEndX = cellStartX + cellLength;//-1
				int cellEndY = cellStartY + cellLength;//-1
				
				//Determine actual start and end for drawing cell
				int drawStartX = (int)Math.max(cellStartX, screenPos.getX());
				int drawStartY = (int)Math.max(cellStartY, screenPos.getY());
				int drawEndX = (int)Math.min(cellEndX, screenPos.getX() + windowScreenSize.getX());
				int drawEndY = (int)Math.min(cellEndY, screenPos.getY() + windowScreenSize.getY());
				
				//Determine amount of source image to use
				int sourceMinX = (int)Math.max(0, (drawStartX - cellStartX) / (magnification / 10.0));
				int sourceMinY = (int)Math.max(0, (drawStartY - cellStartY) / (magnification / 10.0));
				int sourceMaxX = (int)Math.min(MapCell.cellLength, ((screenPos.getX() + windowScreenSize.getX() - drawStartX)) / (magnification / 10.0));//-1
				int sourceMaxY = (int)Math.min(MapCell.cellLength, ((screenPos.getY() + windowScreenSize.getY() - drawStartY)) / (magnification / 10.0));//-1
				
				//Temporary for debugging purposes
				if (currX == minCellX && currY == minCellY)
				{
					GameConstant.sx1 = sourceMinX;
					GameConstant.sy1 = sourceMinY;
					GameConstant.sx2 = sourceMaxX;
					GameConstant.sy2 = sourceMaxY;
				}
				
				//Draw the cell
				g.drawImage(map[currX][currY].getImage(), drawStartX, drawStartY, drawEndX, drawEndY, sourceMinX, sourceMinY, sourceMaxX, sourceMaxY, null);
				
				//Draw cell border
// 				for (int i = 0; i < map[currX][currY].getBorderSize(); i++)
// 					g.drawRect(cellStartX + i, cellStartY + i, cellLength - i * 2, cellLength - i * 2);
					
				//Draw cell coordinates and description
// 				g.setColor(Color.YELLOW);
//  				g.drawString("[" + currX + ", " + currY + "]", cellStartX + 10, cellStartY + /*(int)screenPos.getY()*/ + 10);
//  				g.drawString("Type: " + map[currX][currY].getDescription(), cellStartX + 10, cellStartY + /*(int)screenPos.getY()*/ + 30);
 				
 				//Last minute check to see if another cell needs to be drawn (Sloppy, FIX MATH)
 				if (currX == maxCellX && (currX + 1) != mapCellsWidth && drawEndX < (screenPos.getX() + windowScreenSize.getX()))
 					maxCellX++;
 				if (currY == maxCellY && (currY + 1) != mapCellsHeight && drawEndY < (screenPos.getY() + windowScreenSize.getY()))
 					maxCellY++;
 				
			}
		}
	}
	
}