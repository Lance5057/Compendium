package lance5057.compendium.core.util.rendering.animation.floats;

import com.google.gson.JsonObject;

import lance5057.compendium.core.util.rendering.animation.Transform;
import net.minecraft.network.FriendlyByteBuf;

public class AnimatedFloat {
	float i;
	float iMax;
	float iMin;
	float speed;
	boolean add = true;
	boolean loop;

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
	}

	public AnimatedFloat(float iMin, float iMax, float speed, boolean loop) {
		this.iMin = iMin;
		this.iMax = iMax;
		this.speed = speed;
		this.loop = loop;
	}

	public void animate() {
		if (add) {
			i += speed;
			if (i > iMax) {
				if (loop)
					add = false;
				else
					speed = iMax;
			}
		} else {
			i -= speed;
			if (i < iMin) {
				if (loop)
					add = true;
				else
					speed = iMin;
			}
		}
	}

	public float getFloat() {
		return i;
	}

	public void setMax(float m) {
		this.iMax = m;
	}

	public void setMin(float m) {
		this.iMin = m;
	}

	public void setSpeed(float s) {
		this.speed = s;
	}

	public static AnimatedFloat read(JsonObject j) {
		float min = j.get("min").getAsFloat();
		float max = j.get("max").getAsFloat();
		float speed = j.get("speed").getAsFloat();
		boolean loop = j.get("loop").getAsBoolean();
		
		return new AnimatedFloat(min, max, speed, loop);
	}

	public static AnimatedFloat read(FriendlyByteBuf buffer) {
		float min = buffer.readFloat();
		float max = buffer.readFloat();
		float speed = buffer.readFloat();;
		boolean loop = buffer.readBoolean();
		
		return new AnimatedFloat(min, max, speed, loop);
	}

	public static void write(AnimatedFloat am, FriendlyByteBuf buffer) {
		buffer.writeFloat(am.iMin);
		buffer.writeFloat(am.iMin);
		buffer.writeFloat(am.iMin);
		buffer.writeBoolean(am.loop);
	}

	public static JsonObject addProperty(Transform transform) {

		JsonObject t = new JsonObject();

		t.addProperty("x", transform.translate.x());
		t.addProperty("y", transform.translate.y());
		t.addProperty("z", transform.translate.z());

		JsonObject r = new JsonObject();

		t.addProperty("x", transform.rotation.x());
		t.addProperty("y", transform.rotation.y());
		t.addProperty("z", transform.rotation.z());

		JsonObject s = new JsonObject();

		t.addProperty("x", transform.scale.x());
		t.addProperty("y", transform.scale.y());
		t.addProperty("z", transform.scale.z());

		JsonObject jt = new JsonObject();

		jt.add("translate", t);
		jt.add("rotation", r);
		jt.add("scale", s);

		return jt;
	}
}