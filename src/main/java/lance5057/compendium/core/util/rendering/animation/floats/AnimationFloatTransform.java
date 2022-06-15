package lance5057.compendium.core.util.rendering.animation.floats;

public class AnimationFloatTransform {
	AnimatedFloatVector3 loc, scale, rot;
	
	public AnimationFloatTransform(AnimatedFloatVector3 l, AnimatedFloatVector3 s, AnimatedFloatVector3 r)
	{
		loc = l;
		scale = s;
		rot = r;
	}
	
	public void animate() {
		loc.animate();
		scale.animate();
		rot.animate();
	}
	
	public AnimatedFloatVector3 getLocation()
	{
		return loc;
	}
	
	public AnimatedFloatVector3 getScale()
	{
		return scale;
	}
	
	public AnimatedFloatVector3 getRotation()
	{
		return rot;
	}
}
