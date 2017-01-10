
public class ProjectEvolvingArgs implements Cloneable
{
	double x,y,z;
	double sigmaX,sigmaY,sigmaZ;
	
	
	public ProjectEvolvingArgs(double x, double y, double z, double sigmaX, double sigmaY, double sigmaZ)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.sigmaX = sigmaX;
		this.sigmaY = sigmaY;
		this.sigmaZ = sigmaZ;
	}
	
	public ProjectEvolvingArgs(double x, double y, double z, double sigma)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.sigmaX = sigma;
		this.sigmaY = sigma;
		this.sigmaZ = sigma;
	}
	
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public double getZ()
	{
		return z;
	}
	public double getSigmaX()
	{
		return sigmaX;
	}
	public double getSigmaY()
	{
		return sigmaY;
	}
	public double getSigmaZ()
	{
		return sigmaZ;
	}
}