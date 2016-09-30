/***************************
 * Purpose: GameDebugVars class, centralized location to view
 * status variables set during execution for debugging purposes
 *
 * Original Author: Zachary Johnson
 ***************************/

public class GameDebugVars
{
	//General tracking variables
	public static double frameRate = 0; //current frame rate of the game
	public static double paintsPerSecond = 0;
	
	// private static GameState gameState = GameState.MAIN_MENU; //Game state the game is currently in
	// public static GameState getGameState() { return gameState; }
	
	// //Used to control game state transitions
	// public static void setGameState(GameState newState)
	// {
		// switch (newState)
		// {
			// case MAIN_MENU:
				// view.init_MainMenu();
			// case GAME:
				// break;
			// default:
				// break;
		// }
	// }
}