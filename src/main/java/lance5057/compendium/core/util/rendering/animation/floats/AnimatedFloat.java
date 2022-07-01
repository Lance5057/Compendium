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
	boolean loop = false;
	boolean pingpong = false;

	public static AnimatedFloat zero = new AnimatedFloat(0, 0, 0);

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

	public AnimatedFloat(float iMin, float iMax, float speed, boolean loop, boolean pingpong) {
		this.iMin = iMin;
		this.iMax = iMax;
		this.speed = speed;
		this.loop = loop;
		this.pingpong = pingpong;
	}

	public void animate() {
		if (add) {
			i += speed;
			if (i >= iMax) {
				if (loop)
					i = iMin;
				else if (pingpong)
					add = false;
				else
					i = iMax;
			}
		} else {
			i -= speed;
			if (i <= iMin) {
				if (pingpong)
					add = true;
				else
					i = iMax;
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
		boolean pingpong = j.get("pingpong").getAsBoolean();

		return new AnimatedFloat(min, max, speed, loop, pingpong);
	}

	public static AnimatedFloat read(FriendlyByteBuf buffer) {
		float min = buffer.readFloat();
		float max = buffer.readFloat();
		float speed = buffer.readFloat();
		boolean loop = buffer.readBoolean();
		boolean pingpong = buffer.readBoolean();

		return new AnimatedFloat(min, max, speed, loop, pingpong);
	}

	public static void write(AnimatedFloat af, FriendlyByteBuf buffer) {
		buffer.writeFloat(af.iMin);
		buffer.writeFloat(af.iMin);
		buffer.writeFloat(af.iMin);
		buffer.writeBoolean(af.loop);
	}

	public static JsonObject addProperty(AnimatedFloat af) {
		JsonObject jo = new JsonObject();

		jo.addProperty("min", af.iMin);
		jo.addProperty("max", af.iMax);
		jo.addProperty("speed", af.speed);
		jo.addProperty("loop", af.loop);

		return jo;
	}
}