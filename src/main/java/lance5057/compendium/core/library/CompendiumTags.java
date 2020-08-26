package lance5057.compendium.core.library;

import lance5057.compendium.Reference;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class CompendiumTags {
	public static final Tag<Item> DUST = ItemTags.getCollection().getOrCreate(new ResourceLocation(Reference.MOD_ID, "dust"));
	public static final Tag<Item> PLATE = ItemTags.getCollection().getOrCreate(new ResourceLocation(Reference.MOD_ID, "plate"));
	public static final Tag<Item> COIN = ItemTags.getCollection().getOrCreate(new ResourceLocation(Reference.MOD_ID, "coin"));
	public static final Tag<Item> GEAR = ItemTags.getCollection().getOrCreate(new ResourceLocation(Reference.MOD_ID, "gear"));
	public static final Tag<Item> ROD = ItemTags.getCollection().getOrCreate(new ResourceLocation(Reference.MOD_ID, "rod"));
	public static final Tag<Item> COIL = ItemTags.getCollection().getOrCreate(new ResourceLocation(Reference.MOD_ID, "coil"));
	public static final Tag<Item> SPRING = ItemTags.getCollection().getOrCreate(new ResourceLocation(Reference.MOD_ID, "spring"));
	public static final Tag<Item> CASING = ItemTags.getCollection().getOrCreate(new ResourceLocation(Reference.MOD_ID, "casing"));
	public static final Tag<Item> WIRE = ItemTags.getCollection().getOrCreate(new ResourceLocation(Reference.MOD_ID, "wire"));
}