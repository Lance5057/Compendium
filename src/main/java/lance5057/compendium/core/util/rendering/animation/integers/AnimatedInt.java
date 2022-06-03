//package lance5057.compendium.core.util.rendering.animation.integers;
//
//public class AnimatedInt {
//	int i;
//	int iMax;
//	int iMin;
//	boolean add;
//
//	public AnimatedInt(int iMax) {
//		this(-iMax, iMax);
//	}
//
//	public AnimatedInt(int iMin, int iMax) {
//		this.iMin = iMin;
//		this.iMax = iMax;
//		add = true;
//	}
//
//	public void animate() {
//		if (add) {
//			i++;
//			if (i > iMax)
//				add = false;
//		} else {
//			i--;
//			if (i < iMin)
//				add = true;
//		}
//	}
//
//	public int getInt() {
//		return i;
//	}
//	
//	public void setMax(int m)
//	{
//		this.iMax = m;
//	}
//	
//	public void setMin(int m)
//	{
//		this.iMin = m;
//	}
//}