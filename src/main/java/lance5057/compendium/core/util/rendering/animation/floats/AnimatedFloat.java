package lance5057.compendium.core.util.rendering.animation.floats;

public class AnimatedFloat {
	float i;
	float iMax;
	float iMin;
	float speed;
	boolean add;

	public AnimatedFloat(float iMax) {
		this(-iMax, iMax, 1);
	}
	
	public AnimatedFloat(float iMax, float speed) {
		this(-iMax, iMax, speed);
	}

	public AnimatedFloat(float iMin, float iMax, float speed) {
		this.iMin = iMin;
		this.iMax = iMax;
		this.speed = speed;
		add = true;
	}

	public void animate() {
		if (add) {
			i+=speed;
			if (i > iMax)
				add = false;
		} else {
			i-=speed;
			if (i < iMin)
				add = true;
		}
	}

	public float getFloat() {
		return i;
	}
	
	public void setMax(float m)
	{
		this.iMax = m;
	}
	
	public void setMin(float m)
	{
		this.iMin = m;
	}
	
	public void setSpeed(float s)
	{
		this.speed = s;
	}
}