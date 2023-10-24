package lance5057.compendium.core.data.builders.index.parts.tools;

import com.google.gson.JsonObject;

import lance5057.compendium.core.data.builders.index.parts.weapons.WeaponHeadPart;

public class ToolHeadPart extends WeaponHeadPart {

	private final float digSpeed;

	public ToolHeadPart(int durability, int enchantability, float weaponDamage, float weaponSpeed, float digSpeed) {
		super(durability, enchantability, weaponDamage, weaponSpeed);
		this.digSpeed = digSpeed;
	}
	
	@Override
	public void serialize(JsonObject json) {
		super.serialize(json);
		
		json.addProperty("dig_speed", this.digSpeed);
	}

	@Override
	public String getPartName() {
		return "tool_head";
	}

}
