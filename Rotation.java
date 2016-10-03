/***************************
 * Purpose: Rotation class to handle the rotation
 * of sprites.
 *
 * Original Author: Zachary Johnson
 ***************************/

public class Rotation
{
	private double rotation;
	
	public Rotation()
	{
		this.rotation = 0.0;
	}
	
	public Rotation(double degrees)
	{
		this.rotation = degrees;
		validateRotation();
	}
	
	public void setRotation(double degrees)
	{
		this.rotation = degrees;
		validateRotation();
	}
	
	public double getRotation()
	{
		return this.rotation;
	}
	
	public void addAmount(double degrees)
	{
		this.rotation += degrees;
		validateRotation();
	}
	
	private void validateRotation()
	{
		while(this.rotation < 0.0)
			rotation += 360;
		
		while(this.rotation > 360.0)
			rotation -= 360;
	}
}
