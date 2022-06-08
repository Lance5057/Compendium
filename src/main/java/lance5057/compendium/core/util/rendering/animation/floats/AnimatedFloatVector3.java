package lance5057.compendium.core.util.rendering.animation.floats;

public class AnimatedFloatVector3 {
	AnimatedFloat x, y, z;

	public AnimatedFloatVector3(float xMax, float yMax, float zMax, float speed) {
		x = new AnimatedFloat(xMax, speed);
		y = new AnimatedFloat(yMax, speed);
		z = new AnimatedFloat(zMax, speed);
	}

	public AnimatedFloatVector3(float xMin, float xMax, float yMin, float yMax, float zMin, float zMax, float speed) {
		x = new AnimatedFloat(xMin, xMax, speed);
		y = new AnimatedFloat(yMin, yMax, speed);
		z = new AnimatedFloat(zMin, zMax, speed);
	}

	public void animate() {
		x.animate();
		y.animate();
		z.animate();
	}

	public AnimatedFloat getX() {
		return x;
	}

	public AnimatedFloat getY() {
		return y;
	}

	public AnimatedFloat getZ() {
		return z;
	}

	public void setMax(float x, float y, float z) {
		this.getX().setMax(x);
		this.getY().setMax(y);
		this.getZ().setMax(z);
	}

	public void setMin(float x, float y, float z) {
		this.getX().setMin(x);
		this.getY().setMin(y);
		this.getZ().setMin(z);
	}

	public void setSpeed(float speed)
	{
		this.getX().setSpeed(speed);
		this.getY().setSpeed(speed);
		this.getZ().setSpeed(speed);
	}
}
