//package lance5057.compendium.core.util.rendering.animation.integers;
//
//public class AnimatedIntVector3 {
//	AnimatedInt x, y, z;
//
//	public AnimatedIntVector3(int xMax, int yMax, int zMax)
//	{
//		x = new AnimatedInt(xMax);
//		y = new AnimatedInt(yMax);
//		z = new AnimatedInt(zMax);
//	}
//	
//	public AnimatedIntVector3(int xMin, int xMax, int yMin, int yMax, int zMin, int zMax)
//	{
//		x = new AnimatedInt(xMin, xMax);
//		y = new AnimatedInt(yMin, yMax);
//		z = new AnimatedInt(zMin, zMax);
//	}
//	
//	public void animate() {
//		x.animate();
//		y.animate();
//		z.animate();
//	}
//	
//	public AnimatedInt getX()
//	{
//		return x;
//	}
//	
//	public AnimatedInt getY()
//	{
//		return y;
//	}
//	
//	public AnimatedInt getZ()
//	{
//		return z;
//	}
//	
//	public void setMax(int x, int y, int z)
//	{
//		this.getX().setMax(x);
//		this.getY().setMax(y);
//		this.getZ().setMax(z);
//	}
//	
//	public void setMin(int x, int y, int z)
//	{
//		this.getX().setMin(x);
//		this.getY().setMin(y);
//		this.getZ().setMin(z);
//	}
//	
//}
