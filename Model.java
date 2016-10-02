/***************************
 * Purpose: Model class of Model-View-Controller paradigm,
 * Core game model starts in this class
 *
 * Original Author: Zachary Johnson
 ***************************/

import java.io.IOException;
import javax.swing.ImageIcon;
import java.util.*;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;

class Model
{	
	Random rand;
	int seed = GameConstant.gameSeed; //Old value is 10
	int tick = 0;
	GameState gameState;
	public ModelVars mv;
	
	//String mapFilePath = "genMap.txt";
	//GameMap gameMap;
	
	/***************************
	 * Constructor
	 ***************************/
	Model() throws IOException
	{
		//Set up class containing model variables
		this.mv = new ModelVars(this);
		
		//Set up basic resources
		this.rand = new Random(seed);
		
		this.mv.setGameState(GameState.MAIN_MENU);
		
		//this.gameMap = new GameMap(mapFilePath);
	}

	/***************************
	 * Called when game state changes to main menu
	 ***************************/
	public void init_MainMenu()
	{
		mv.mainMenu = new FullScreenMenu();
	}
	 
	 /***************************
	 * Called when game state changes to the game
	 ***************************/
	public void init_Game()
	{
		
		return;
	}
	
	/***************************
	 * Called when game is changing states
	 * Allows model to gain control before the resources are disposed of
	 * Returns false to cancel the state change, true otherwise
	 ***************************/
	public boolean leaveCurrentState()
	{
		//No special behavior needed yet
		return true;
	}
	
	//Called by ModelVar class after variables initialized
	public void initGameState()
	{
		switch (mv.getGameState())
		{
			case MAIN_MENU:
				init_MainMenu();
			case GAME:
				init_Game();
		}
	}
	
	//Update function called on each tick
	public void update()
	{
		switch (mv.getGameState())
		{
			case MAIN_MENU:
				break;
			case GAME:
				break;
			default:
				break;
		}
	}

	//Function called when left mouse button is clicked
	public void onLeftClick(Point2D point){}
	
	//Function called when right mouse button is clicked
	public void onRightClick(Point2D point){}
	
	//Function called when left mouse button is released
	public void onLeftClickRelease(Point2D point){}
	
	//Function called when right mouse button is released
	public void onRightClickRelease(Point2D point){}
	
	
}
