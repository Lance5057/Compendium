package lance5057.compendium.core.data.builders.index.parts.weapons;

import com.google.gson.JsonObject;

import lance5057.compendium.core.data.builders.index.parts.HeadPart;

public class WeaponHeadPart extends HeadPart {

	private final float weaponDamage;
	private final float weaponSpeed;

	public WeaponHeadPart(int durability, int enchantability, float weaponDamage, float weaponSpeed) {
		super(durability, enchantability);
		this.weaponDamage = weaponDamage;
		this.weaponSpeed = weaponSpeed;
	}

	@Override
	public void serialize(JsonObject json) {
		super.serialize(json);
		json.addProperty("weapon_damage", this.weaponDamage);
		json.addProperty("weapon_speed", this.weaponSpeed);
	}

	@Override
	public String getPartName() {
		return "weapon_head";
	}
}
