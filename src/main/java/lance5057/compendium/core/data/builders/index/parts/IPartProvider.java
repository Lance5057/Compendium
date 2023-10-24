package lance5057.compendium.core.data.builders.index.parts;

import com.google.gson.JsonObject;

public interface IPartProvider {
	public String getPartName();
	public void serialize(JsonObject json);
}
