
public class ProjectEvolvingArgs implements Cloneable
{
	double x,y,z;
	double sigmaX,sigmaY,sigmaZ;
	int success,fail;
	
	
	public ProjectEvolvingArgs(double x, double y, double z, double sigmaX, double sigmaY, double sigmaZ)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.sigmaX = sigmaX;
		this.sigmaY = sigmaY;
		this.sigmaZ = sigmaZ;
		success = 0;
		fail = 0;
	}

	public ProjectEvolvingArgs(double x, double y, double z, double sigmaX, double sigmaY, double sigmaZ, int success, int fail)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.sigmaX = sigmaX;
		this.sigmaY = sigmaY;
		this.sigmaZ = sigmaZ;
		this.success = success;
		this.fail = fail;
	}
	
	public ProjectEvolvingArgs(double x, double y, double z, double sigma)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.sigmaX = sigma;
		this.sigmaY = sigma;
		this.sigmaZ = sigma;
		this.success = 0;
		this.fail = 0;
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
	public int getSuccess() { return success;}
	public int getFail() {return fail;}
}