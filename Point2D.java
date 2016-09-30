/***************************
 * Purpose: Simple class used to organize two numbers that
 * typically work in pairs (ex. coordinate points)
 *
 * Support for long and double type numbers,
 * but get functions always return double
 *
 * Original Author: Zachary Johnson
 ***************************/
/***************************
 * TODO:
 * -Overload operators to make Point2D easier to manipulate
 *		(+, -, *, /,)
 ***************************/
import java.lang.Number;

class Point2D
{
	private double dx, dy;
	private long lx, ly;
	
	/***************************
	* Constructors
	***************************/
	public Point2D(double valX, double valY)
	{
		this.dx = valX;
		this.dy = valY;
		this.lx = 0;
		this.ly = 0;
	}
	public Point2D(long valX, long valY)
	{
		this.lx = valX;
		this.ly = valY;
		this.dx = 0.0;
		this.dy = 0.0;
	}
	
	//Copy constructor
	public Point2D(Point2D other)
	{
		this.setX(other.getX());
		this.setY(other.getY());
// 		this.dx = other.dx;
// 		this.dy = other.dy;
// 		this.lx = other.lx;
// 		this.ly = other.ly;
	}
	
	/***************************
	* Get functions
	***************************/
	public double getX()
	{
		if (this.dx != 0.0)
			return this.dx;
		else
			return (double) this.lx;
	}
	
	public double getY()
	{
		if (this.dy != 0.0)
			return this.dy;
		else
			return (double) this.ly;
	}
	
	/***************************
	* Set functions
	***************************/
	//int
	public void setX(int num)
	{
		this.lx = num;
		this.dx = 0.0;
	}
	public void setY(int num)
	{
		this.ly = num;
		this.dy = 0.0;
	}
	//double
	public void setX(double num)
	{
		this.dx = num;
		this.lx = 0;
	}
	public void setY(double num)
	{
		this.dy = num;
		this.ly = 0;
	}
	
	/***************************
	* Distance function
	***************************/
	double distanceTo(Point2D otherPoint)
	{
		double deltaX = Math.abs((double) this.getX() - (double) otherPoint.getX());
		double deltaY = Math.abs((double) this.getY() - (double) otherPoint.getY());
		double length = Math.sqrt(Math.pow((double) deltaX, 2) + Math.pow((double) deltaY, 2));
		
		return length;
	}
}