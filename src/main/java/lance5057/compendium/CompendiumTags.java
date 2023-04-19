package lance5057.compendium;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class CompendiumTags {
	public static final TagKey<Item> AXES = ItemTags.create(new ResourceLocation("forge", "axes"));
	public static final TagKey<Item> PICKAXES = ItemTags.create(new ResourceLocation("forge", "pickaxes"));
	
	public static final TagKey<Item> WORKSTATION_UPGRADE_LIGHT = ItemTags.create(new ResourceLocation("compendium", "workstation_light"));
	public static final TagKey<Item> WORKSTATION_UPGRADE_INV4X = ItemTags.create(new ResourceLocation("compendium", "workstation_inv4x"));
	public static final TagKey<Item> WORKSTATION_UPGRADE_INV5X = ItemTags.create(new ResourceLocation("compendium", "workstation_inv5x"));
	public static final TagKey<Item> WORKSTATION_UPGRADE_POWER = ItemTags.create(new ResourceLocation("compendium", "workstation_power"));
	public static final TagKey<Item> WORKSTATION_UPGRADE_BATTERY = ItemTags.create(new ResourceLocation("compendium", "workstation_battery"));
}
