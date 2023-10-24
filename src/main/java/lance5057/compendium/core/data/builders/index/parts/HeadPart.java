package lance5057.compendium.core.data.builders.index.parts;

import com.google.gson.JsonObject;

public abstract class HeadPart implements IPartProvider {

	private final int durability;
	private final int enchantability;
	
	public HeadPart( int durability, int enchantability)
	{
		this.durability = durability;
		this.enchantability = enchantability;
		
	}
	
	@Override
	public void serialize(JsonObject json) {
		json.addProperty("durability", this.durability);
		json.addProperty("enchantability", this.enchantability);
	}

}
