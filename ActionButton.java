import java.awt.Color;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.IOException;
import java.awt.Font;
import java.awt.FontMetrics;

public class ActionButton
{
	//Member variables
	private String text;
	private Point2D pos;
	private Point2D dimensions;
	
	private BufferedImage buttonImages[] = new BufferedImage[5];
	private BufferedImage currentImage;
	
	private Color shadowColor = Color.GRAY;
	private Color textColor = Color.YELLOW;
	
	private GUIButtonActions buttonAction;
	private GUIButtonStates buttonState, previousState;
	
	private boolean isDisabled;
	private boolean isToggleButton;
	
	private Font font;
	
	
	public ActionButton()
	{
		initializeButton("TEST BUTTON", new Point2D(0, 0));
	}
	
	public ActionButton(String description)
	{
		initializeButton(description, new Point2D(0, 0));
	}
	
	public ActionButton(Point2D position)
	{
		initializeButton("TEST BUTTON", position);
	}
	
	public ActionButton(String description, Point2D position)
	{
		initializeButton(description, position);
	}
	
	private void initializeButton(String description, Point2D position)
	{
		this.previousState = null;
		this.isDisabled = false;
		this.isToggleButton = true;
		this.text = description;
		this.pos = new Point2D(position);
		this.buttonAction = GUIButtonActions.DO_NOTHING;
		this.font = new Font("MONOSPACE", Font.BOLD, 20);
		
		String imagePath = "resources/buttons/testButton/";
		
		try
		{
			buttonImages[0] = GameFunction.loadBufferedImage(imagePath + "active.png");
			buttonImages[1] = GameFunction.loadBufferedImage(imagePath + "disabled.png");
			buttonImages[2] = GameFunction.loadBufferedImage(imagePath + "hover.png");
			buttonImages[3] = GameFunction.loadBufferedImage(imagePath + "normal.png");
			buttonImages[4] = GameFunction.loadBufferedImage(imagePath + "pressed.png");
		}
		catch(IOException e)
		{
			System.out.println("Some IO exception occurred when trying to load images (ActionButton)");
			//TODO: PLEASE ADD CODE HERE
		}
		
		this.setState(GUIButtonStates.NORMAL);
	}
	
	public void disable()
	{
		this.isDisabled = true;
	}
	public void enable()
	{
		this.isDisabled = false;
	}
	public boolean isDisabled()
	{
		return this.isDisabled;
	}
	
	public void setButtonAction(GUIButtonActions action)
	{
		this.buttonAction = action;
	}
	public GUIButtonActions getButtonAction()
	{
		return this.buttonAction;
	}
	
	public void setIsToggleButton(boolean toggleChoice)
	{
		this.isToggleButton = toggleChoice;
	}
	
	//Called on mouse press while hovering over this button
	public void startClick()
	{
		this.previousState = this.getState();
		
		currentImage = buttonImages[4];
	}
	
	//Called if user cancels clicking button (by click and holding button, but releasing outside of button)
	public void cancelClick()
	{
		this.setState(this.previousState);
	}
	
	//Called when mouse released while hovering over this button
	public void finishClick()
	{
		if (this.previousState == GUIButtonStates.ACTIVE)
			this.setState(GUIButtonStates.NORMAL);
		else if (this.previousState == GUIButtonStates.NORMAL || this.previousState == GUIButtonStates.HOVER)
		{
			ButtonController.doAction(this.buttonAction, this);
			
			//Set button back to correct state
			if (this.isToggleButton)
				this.setState(GUIButtonStates.ACTIVE);
			else
				this.setState(GUIButtonStates.NORMAL);
		}
	}
	
	public void setPos(ReferencePositions refPoint, int x, int y)
	{
		setPos(refPoint, new Point2D(x, y));
	}
	public void setPos(ReferencePositions refPoint, Point2D newPos)
	{
		switch (refPoint)
		{
			case TOP_LEFT:
				this.pos = new Point2D(newPos);
				break;
			case TOP_CENTER:
				this.pos = new Point2D((int) (newPos.getX() - this.dimensions.getX() / 2), (int) newPos.getY());
				break;
			case TOP_RIGHT:
				this.pos = new Point2D((int) (newPos.getX() - this.dimensions.getX()), (int) newPos.getY());
				break;
			case CENTER_LEFT:
				this.pos = new Point2D((int) newPos.getX(), (int) (newPos.getY() - this.dimensions.getY() / 2));
				break;
			case CENTER:
				this.pos = new Point2D((int) (newPos.getX() - this.dimensions.getX() / 2), (int) (newPos.getY() - this.dimensions.getY() / 2));
				break;
			case CENTER_RIGHT:
				this.pos = new Point2D((int) (newPos.getX() - this.dimensions.getX()), (int) (newPos.getY() - this.dimensions.getY() / 2));
				break;
			case BOTTOM_LEFT:
				this.pos = new Point2D((int) newPos.getX(), (int) (newPos.getY() - this.dimensions.getY()));
				break;
			case BOTTOM_CENTER:
				this.pos = new Point2D((int) (newPos.getX() - this.dimensions.getX() / 2), (int) (newPos.getY() - this.dimensions.getY()));
				break;
			case BOTTOM_RIGHT:
				this.pos = new Point2D((int) (newPos.getX() - this.dimensions.getX()), (int) (newPos.getY() - this.dimensions.getY()));
				break;
		}
	}
	public Point2D getPos()
	{
		return this.pos;
	}
	public Point2D getPos(ReferencePositions refPoint)
	{
		switch (refPoint)
		{
			case TOP_LEFT:
				return this.pos;
			case TOP_CENTER:
				return new Point2D((int) (this.pos.getX() + this.dimensions.getX() / 2), (int) this.pos.getY());
			case TOP_RIGHT:
				return new Point2D((int) (this.pos.getX() + this.dimensions.getX()), (int) this.pos.getY());
			case CENTER_LEFT:
				return new Point2D((int) this.pos.getX(), (int) (this.pos.getY() + this.dimensions.getY() / 2));
			case CENTER:
				return new Point2D((int) (this.pos.getX() + this.dimensions.getX() / 2), (int) (this.pos.getY() + this.dimensions.getY() / 2));
			case CENTER_RIGHT:
				return new Point2D((int) (this.pos.getX() + this.dimensions.getX()), (int) (this.pos.getY() + this.dimensions.getY() / 2));
			case BOTTOM_LEFT:
				return new Point2D((int) this.pos.getX(), (int) (this.pos.getY() + this.dimensions.getY()));
			case BOTTOM_CENTER:
				return new Point2D((int) (this.pos.getX() + this.dimensions.getX() / 2), (int) (this.pos.getY() + this.dimensions.getY()));
			case BOTTOM_RIGHT:
				return new Point2D((int) (this.pos.getX() + this.dimensions.getX()), (int) (this.pos.getY() + this.dimensions.getY()));
			default:
				return this.pos;
		}
	}
	
	public void setSize(Point2D newSize)
	{
		this.dimensions = newSize;
	}
	public Font getFont()
	{
		return this.font;
	}
	public void setFont(Font f)
	{
		this.font = f;
	}
	
	public void setState(GUIButtonStates newState)
	{
		switch (newState)
		{
			case ACTIVE:
				currentImage = buttonImages[0];
				break;
			case DISABLED:
				currentImage = buttonImages[1];
				break;
			case HOVER:
				currentImage = buttonImages[2];
				break;
			case NORMAL:
				currentImage = buttonImages[3];
				break;
			case PRESSED:
				currentImage = buttonImages[4];
				break;
			default: //default to disabled button
				currentImage = buttonImages[1];
				break;
		}
		
		this.previousState = this.buttonState;
		this.dimensions = new Point2D(currentImage.getWidth(), currentImage.getHeight());
		this.buttonState = newState;
	}
	
	public GUIButtonStates getState()
	{
		return this.buttonState;
	}
	
	boolean update(Random rand)
	{
		return true;
	}
	
	void draw(Graphics g)
	{
		//Save original values
		Color oldColor = g.getColor();
		Font oldFont = g.getFont();
		
		g.setFont(this.font);
		
		//Draw button
		g.drawImage(currentImage, (int) pos.getX(), (int) pos.getY(), null);
		
        //Get dimensions of button text
        FontMetrics metrics = g.getFontMetrics();
        int textWidth = metrics.stringWidth(text);
		int textHeight = metrics.getHeight();
		
		//Get positions for text centered on button
		int textPosX = (int) (this.pos.getX() + this.dimensions.getX() / 2 - textWidth / 2);
		int textPosY = (int) (this.pos.getY() + this.dimensions.getY() / 2 + textHeight / 2);
		
		//Draw shadow of button text slight off center
		g.setColor(this.shadowColor);
		g.drawString(this.text, textPosX + 3, textPosY + 3);
		
		//Draw button text centered
		g.setColor(this.textColor);
		g.drawString(this.text, textPosX, textPosY);
		
		//Restore original values
		g.setFont(oldFont);
		g.setColor(oldColor);
		
	}
	
	public boolean isWithin(Point2D point)
	{
		if (point.getX() >= this.pos.getX() && point.getX() <= this.pos.getX() + this.dimensions.getX()
			&& point.getY() >= this.pos.getY() && point.getY() <= this.pos.getY() + this.dimensions.getY())
			return true;
		else
			return false;
	}
}



































