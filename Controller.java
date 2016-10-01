/***************************
 * Purpose: Controller class, takes input from the user
 * and calls functions to interface with the program
 *
 * Original Author: Zachary Johnson
 ***************************/

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

class Controller implements MouseListener, MouseWheelListener, MouseMotionListener, KeyListener
{
	Model model;
	ViewWindow window;
	Point2D mousePos = new Point2D(0,0);
	boolean draggingMap = false, draggingWindow = false;

	Controller(Model m, ViewWindow w)
	{
		this.model = m;
		this.window = w;
	}

	public void mousePressed(MouseEvent e)
	{
		switch (model.mv.getGameState())
		{
			case MAIN_MENU:
				model.mv.mainMenu.mouseDown(e);
				break;
			case GAME:
				Point2D position = new Point2D(e.getX(), e.getY());
		
				if (e.getButton() == MouseEvent.BUTTON1)
					this.model.onLeftClick(position);
				if (e.getButton() == MouseEvent.BUTTON3)
					this.model.onRightClick(position);
					
				if (position.getX() > window.screenDrawArea.getX() && position.getX() < window.screenDrawArea.getX() + window.screenDrawArea.getWidth())
				{
					if (position.getY() > window.screenDrawArea.getY() && position.getY() < window.screenDrawArea.getY() + window.screenDrawArea.getHeight())
					{
						System.out.println("Click started on map");
						draggingMap = true;
					}
				}
				else
				{
					System.out.println("Click started outside map");
					draggingWindow = true;
				}
				break;
			default:
				break;
		}
	}

	public void mouseReleased(MouseEvent e)
	{
		switch (model.mv.getGameState())
		{
			case MAIN_MENU:
				model.mv.mainMenu.mouseUp(e);
				break;
			case GAME:
				if (e.getButton() == MouseEvent.BUTTON1)
					this.model.onLeftClickRelease(new Point2D(e.getX(), e.getY()));
				if (e.getButton() == MouseEvent.BUTTON3)
					this.model.onRightClickRelease(new Point2D(e.getX(), e.getY()));
				
				System.out.println("Click released");
				draggingMap = false;
				draggingWindow = false;
				break;
			default:
				break;
		}
	}
	
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		switch (model.mv.getGameState())
		{
			case MAIN_MENU:
				model.mv.mainMenu.mouseScroll(e);
				break;
			case GAME:
				if (e.getWheelRotation() < 0 && this.window.magnification < 50)
					this.window.magnification += 2;
				else if (e.getWheelRotation() > 0 && this.window.magnification > 4)
					this.window.magnification -= 2;
				break;
			default:
				break;
		}
	}
	
	public void mouseDragged(MouseEvent e)
	{
		switch (model.mv.getGameState())
		{
			case MAIN_MENU:
				model.mv.mainMenu.mouseDrag(e);
				break;
			case GAME:
				//Mouse drag amount
				Point2D dragAmount = new Point2D((e.getX() - mousePos.getX()), (e.getY() - mousePos.getY()));
				
				//If panning window, factor in the current magnification to drag amount
				if (draggingMap)
				{
					dragAmount = new Point2D((e.getX() - mousePos.getX()) / (this.window.magnification / 10.0), (e.getY() - mousePos.getY()) / (this.window.magnification / 10.0));
					this.window.panWindow(dragAmount);
				}
				//If moving window around, magnification is not factored in
				else
				{
					Point2D newWindowPos = new Point2D(window.screenDrawArea.getX() + dragAmount.getX(), window.screenDrawArea.getY() + dragAmount.getY());
					this.window.setPos(newWindowPos);
				}
				
				this.mousePos = new Point2D(e.getX(), e.getY());
				break;
			default:
				break;
		}
	}
	
	public void mouseMoved(MouseEvent e)
	{
		switch (model.mv.getGameState())
		{
			case MAIN_MENU:
				model.mv.mainMenu.mouseMove(e);
				break;
			case GAME:
				this.mousePos = new Point2D(e.getX(), e.getY());
				break;
			default:
				break;
		}
	}
	
	public void mouseClicked(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void keyPressed(KeyEvent e)
	{
		switch (model.mv.getGameState())
		{
			case MAIN_MENU:
				model.mv.mainMenu.keyPress(e);
				break;
			case GAME:
				int keyCode = e.getKeyCode();
				double increment = 20.0;
				
				if (keyCode == KeyEvent.VK_UP)
				{
					window.virtualPos = new Point2D((int)(window.virtualPos.getX() - increment / 2), (int)(window.virtualPos.getY() - increment / 2));
					window.setSize(new Point2D((int)(window.getSize().getX() + increment / 2), (int)(window.getSize().getY() + increment / 2)));
				}
				else if (keyCode == KeyEvent.VK_DOWN)
				{
					window.virtualPos = new Point2D((int)(window.virtualPos.getX() + increment / 2), (int)(window.virtualPos.getY() + increment / 2));
					window.setSize(new Point2D((int)(window.getSize().getX() - increment / 2), (int)(window.getSize().getY() - increment / 2)));
				}
				break;
			default:
				break;
		}
	}
	public void keyReleased(KeyEvent k) {    }
	public void keyTyped(KeyEvent k) {    }

}
