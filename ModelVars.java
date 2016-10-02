import java.util.LinkedList;

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
	 * MAIN MENU Public Variables
	 ***************************/
	public Model m;
	public FullScreenMenu mainMenu;
	
	
	/***************************
	 * MAIN MENU Private Variables
	 ***************************/
	private GameState gameState;
	
	public GameState getGameState() { return gameState; }
	public void setGameState(GameState newState)
	{
		//Request state change from model
		//Model can cancel state change by returning false
		if (!m.leaveCurrentState())
			return;
		
		//Dispose of resources for current state
		switch (gameState)
		{
			case MAIN_MENU:
				this.mainMenu = null;
				break;
			case GAME:
				this.gameSprites = null;
				break;
			default:
				break;
		}
		
		//Initialize resources for new state
		switch (newState)
		{
			case MAIN_MENU:
				this.mainMenu = new FullScreenMenu();
				break;
			case GAME:
				this.gameSprites = new LinkedList<Sprite>();
				Sprite testSprite = new Sprite(new Point2D(300, 300));
				gameSprites.add(testSprite);
				break;
			default:
				break;
		}
		
		//Change the GameState
		this.gameState = newState;
		
		//Inform model to prepare any resources
		m.initGameState();
	}
	
	
	/***************************
	 * GAME Public Variables
	 ***************************/
	LinkedList<Sprite> gameSprites;
	
	/***************************
	 * GAME Private Variables
	 ***************************/
}




















