/***************************
 * Purpose: View component of Model-View-Controller paradigm,
 * starting point for drawing to the screen
 *
 * Original Author: Zachary Johnson
 ***************************/

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import javax.swing.ImageIcon;
import java.util.Iterator;
import java.awt.Color;
import java.awt.image.*;


class View extends JPanel
{
	Model model;
	ViewWindow window;
	BufferedImage currFrame, nextFrame;
	
	//Tracking variable to allow only one instance of painting next frame at a time
	private boolean drawingNextFrame = false;
	
	private static FullScreenMenu mainMenu;

	/***************************
	 * Constructor
	 ***************************/
	View(Model m, ViewWindow w) throws IOException
	{
		this.model = m;
		
		//this.background = ImageIO.read(new File ("background.png"));
		
		this.window = w;
		
		this.currFrame = new BufferedImage(Game.WIDTH - 1, Game.HEIGHT - 1, BufferedImage.TYPE_INT_ARGB);
	}
	
	/***************************
	 * Constructor
	 ***************************/
	public void mouseClick(Point2D position)
	{
		//Loop through screen elements to determine what was clicked
		//Currently only the ViewWindow
		
	}
	
	/***************************
	 * Primary painting function
	 ***************************/
	public void paintComponent(Graphics g)
	{
//		try
//		{
//			BufferedImage testImage = GameFunction.loadBufferedImage("TestTriangle.png");
//			g.drawImage(testImage, 50, 50, null);
//		}
//		catch (Exception e) { }
		
		//Draw the current frame if it is ready (may not be ready when game first starts)
		if (this.currFrame != null)
		{
			//System.out.println("Drawing current frame");
			g.drawImage(this.currFrame, 0, 0, null);
		}
		
		//Draw next frame if not already doing so
		if (this.drawingNextFrame == false)
		{
			//System.out.println("Drawing next frame");
			drawNextFrame();
			this.drawingNextFrame = false;
			g.drawImage(this.currFrame, 0, 0, null);
		}
	}
	public void drawNextFrame()
	{
		this.drawingNextFrame = true;
		
		this.nextFrame = new BufferedImage(Game.WIDTH - 1, Game.HEIGHT - 1, BufferedImage.TYPE_INT_ARGB);
		Graphics nfg = nextFrame.getGraphics();
		
		switch (model.mv.getGameState())
		{
			case MAIN_MENU:
				if (model.mv.mainMenu != null)
				{
					if (model.mv.mainMenu.needsRedraw)
						nfg.drawImage(model.mv.mainMenu.drawRecurrsive(), 0, 0, null);
					else
						nfg.drawImage(model.mv.mainMenu.image, 0, 0, null);
					//this.currFrame.getGraphics().drawImage(model.mv.mainMenu.drawRecurrsive(), 0, 0, null);
				}
				else
				{
					nfg.setColor(Color.GRAY);
					nfg.fillRect(0, 0, Game.WIDTH - 1, Game.HEIGHT - 1);
				}
				break;
				
			case GAME:
				nfg.setColor(Color.BLACK);
				nfg.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
				
				this.window.draw(nfg);
				
				nfg.setColor(Color.WHITE);
				nfg.drawString("Magnification: " + this.window.magnification / 10 + "." + this.window.magnification % 10 + "x", 15, 15);
				nfg.drawString("sourceMin: (" + GameConstant.sx1 + ", " + GameConstant.sy1 + ")", 15, 35);
				nfg.drawString("sourceMax: (" + GameConstant.sx2 + ", " + GameConstant.sy2 + ")", 15, 55);
				nfg.drawString("FPS: " + GameDebugVars.frameRate, 15, 75);
				nfg.drawString("PPS: " + GameDebugVars.paintsPerSecond, 15, 95);
				break;
			default:
				break;
		}
		
		this.currFrame = this.nextFrame;
	}
}






























