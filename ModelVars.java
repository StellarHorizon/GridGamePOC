public class ModelVars
{
	/***************************
	 * Constructor
	 ***************************/
	public ModelVars(Model model)
	{
		this.m = model;
		
		this.gameState = GameState.MAIN_MENU;
	}
	
	/***************************
	 * Public Variables
	 ***************************/
	public Model m;
	public FullScreenMenu mainMenu;
	
	
	/***************************
	 * Private Variables
	 ***************************/
	private GameState gameState;
	
	public GameState getGameState() { return gameState; }
	public void setGameState(GameState newState)
	{
		switch (newState)
		{
			case MAIN_MENU:
				m.init_MainMenu();
				break;
			case GAME:
				m.init_Game();
				break;
			default:
				break;
		}
	}
}