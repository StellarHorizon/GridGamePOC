import java.util.*;
import java.awt.image.*;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;

public class FullScreenMenu extends DrawnFeature
{
	LinkedList<ActionButton> menuButtons;
	LinkedList<MenuText> menuTexts;
	
	public FullScreenMenu()
	{
		super(new Rectangle(Game.WIDTH - 1, Game.HEIGHT - 1));
		menuButtons = new LinkedList<ActionButton>();
		menuTexts = new LinkedList<MenuText>();
		
		//setMainMenu();
		
		setTestMenu();
	}
	
	public void setMainMenu()
	{
		this.menuButtons.add(new ActionButton("Start the Game"));
	}
	
	public void setTestMenu()
	{	
		this.menuTexts.add(new MenuText("GRIDGAME", Game.CENTERX, 100, Color.GREEN, Color.BLACK));
		
		ActionButton buttonA = new ActionButton("ButtonA", new Point2D(200, 200));
		ActionButton buttonB = new ActionButton("ButtonB", new Point2D(200, 320));
		
		this.menuButtons.add(buttonA);
		this.menuButtons.add(buttonB);
	}
	
	public BufferedImage draw(Graphics g)
	{
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, Game.WIDTH - 1, Game.HEIGHT - 1);
		
		for (ActionButton curr : menuButtons)
		{
			curr.draw(g);
		}
		for (MenuText curr : menuTexts)
		{
			curr.draw(g);
		}
		return this.nextImage;
	}
	
	public void mouseDown(MouseEvent e)
	{
		this.needsRedraw = true;
		
		Point2D mousePoint = new Point2D(e.getX(), e.getY());
		
		for (ActionButton curr : menuButtons)
			if (curr.isWithin(mousePoint) && !curr.isDisabled())
			{
				curr.setState(GUIButtonStates.PRESSED);
				break;
			}
	}
	
	public void mouseUp(MouseEvent e)
	{
		this.needsRedraw = true;
		
		Point2D mousePoint = new Point2D(e.getX(), e.getY());
		
		for (ActionButton curr : menuButtons)
		{
			if (curr.isWithin(mousePoint) && curr.getState() == GUIButtonStates.PRESSED && curr.isToggledOn() == true && !curr.isDisabled())
			{
				curr.setState(GUIButtonStates.ACTIVE);
				break;
			}
			else if (curr.isWithin(mousePoint) && curr.getState() == GUIButtonStates.PRESSED && curr.isToggledOn() == false && !curr.isDisabled())
			{
				curr.setState(GUIButtonStates.HOVER);
				break;
			}
			else if (curr.getState() == GUIButtonStates.PRESSED && !curr.isDisabled())
			{
				if (curr.isToggledOn())
				{
					curr.setToggledOn(false);
					curr.setState(GUIButtonStates.NORMAL);
				}
				else
				{
					curr.setToggledOn(true);
					curr.setState(GUIButtonStates.ACTIVE);
				}
				break;
			}
		}
	}
	
	public void mouseScroll(MouseWheelEvent e)
	{
		this.needsRedraw = true;
	}
	
	public void mouseDrag(MouseEvent e)
	{
		this.needsRedraw = true;
	}
	
	public void mouseMove(MouseEvent e)
	{
		this.needsRedraw = true;
		
		Point2D mousePoint = new Point2D(e.getX(), e.getY());
		
		for (ActionButton curr : menuButtons)
		{
			if (curr.isWithin(mousePoint))
			{
				if (!curr.isDisabled() && curr.getState() != GUIButtonStates.ACTIVE)
				{
					curr.setState(GUIButtonStates.HOVER);
					break;
				}
			}
			else if (curr.getState() == GUIButtonStates.HOVER)
			{
				curr.setState(GUIButtonStates.NORMAL);
			}
		}
	}
	
	public void keyPress(KeyEvent e)
	{
		
	}
	
}































