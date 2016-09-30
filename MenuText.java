import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

public class MenuText
{
	private String text;
	private Point2D pos;
	private Color textColor, shadowColor;
	private Font font;
	
	/*******************
	 * Constructors
	 ******************/
	 
	public MenuText()
	{
		this.text = "SAMPLE TEXT";
		this.pos = new Point2D(200, 200);
	}
	
	//Universal constructor
	private void constrMenuText(String s, Point2D position, Color colorText, Color colorShadow)
	{
		if (s != null)
			this.text = s;
		else
			this.text = "SAMPLE TEXT";
		
		if (position != null)
			this.pos = new Point2D((int) position.getX(), (int) position.getY());
		else
			this.pos = new Point2D(200, 200);
		
		if (colorText != null)
			this.textColor = colorText;
		else
			this.textColor = Color.WHITE;
		
		
		if (shadowColor != null)
			this.shadowColor = colorShadow;
		else
			this.shadowColor = Color.BLACK;
		
		this.font = new Font("MONOSPACE", Font.BOLD, 20);
	}
	
	public MenuText(String s) { constrMenuText(s, null, null, null); }
	
	public MenuText(Point2D position) { constrMenuText(null, new Point2D((int) position.getX(), (int) position.getY()), null, null); }
	
	public MenuText(String s, Point2D position) { constrMenuText(s, new Point2D((int) position.getX(), (int) position.getY()), null, null); }
	public MenuText(String s, Point2D position, Color colorText) { constrMenuText(s, new Point2D((int) position.getX(), (int) position.getY()), colorText, null); }
	public MenuText(String s, Point2D position, Color colorText, Color colorShadow) { constrMenuText(s, new Point2D((int) position.getX(), (int) position.getY()), colorText, colorShadow); }
	
	public MenuText(String s, int posX, int posY) { constrMenuText(s, new Point2D(posX, posY), null, null); }
	public MenuText(String s, int posX, int posY, Color colorText) { constrMenuText(s, new Point2D(posX, posY), textColor, null); }
	public MenuText(String s, int posX, int posY, Color colorText, Color colorShadow) { constrMenuText(s, new Point2D(posX, posY), textColor, colorShadow); }
	
	/*******************
	 * Getters/Setters
	 ******************/
	public String getText() { return this.text; }
	public void setText(String s) { this.text = s; }
	
	public Point2D getPos() { return this.pos; }
	public void setPos(Point2D point) {	 this.pos = new Point2D((int) point.getX(), (int) point.getY()); }
	public void setPos(int posX, int posY) { this.pos = new Point2D(posX, posY); }
	
	public Color getTextColor() { return this.textColor; }
	public void setTextColor(Color color) { this.textColor = color; }
	
	public Color getShadowColor() { return this.shadowColor; }
	public void setShadowColor(Color color) { this.shadowColor = color; }
	
	public Font getFont() { return this.font; }
	public void setFont(Font f) { this.font = f; }
	
	
	/*******************
	 * Drawing
	 ******************/
	
	public void draw(Graphics g)
	{
		Color oldColor = g.getColor();
		Font oldFont = g.getFont();
		
		g.setFont(this.font);
		
		g.setColor(this.shadowColor);
		g.drawString(this.text, (int) this.pos.getX() + 3, (int) this.pos.getY() + 3);
		
		g.setColor(this.textColor);
		g.drawString(this.text, (int) this.pos.getX(), (int) this.pos.getY());
		
		g.setFont(oldFont);
		g.setColor(oldColor);
	}
}






































