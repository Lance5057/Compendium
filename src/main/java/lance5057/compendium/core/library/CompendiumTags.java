package lance5057.compendium.core.library;

import lance5057.compendium.Reference;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class CompendiumTags {
	public static final Tag<Item> DUST = ItemTags.getCollection().getOrCreate(new ResourceLocation("forge", "dusts"));
	public static final Tag<Item> PLATE = ItemTags.getCollection().getOrCreate(new ResourceLocation("forge", "plates"));
	public static final Tag<Item> COIN = ItemTags.getCollection().getOrCreate(new ResourceLocation("forge", "coins"));
	public static final Tag<Item> GEAR = ItemTags.getCollection().getOrCreate(new ResourceLocation("forge", "gears"));
	public static final Tag<Item> ROD = ItemTags.getCollection().getOrCreate(new ResourceLocation("forge", "rods"));
	public static final Tag<Item> COIL = ItemTags.getCollection().getOrCreate(new ResourceLocation("forge", "coils"));
	public static final Tag<Item> SPRING = ItemTags.getCollection().getOrCreate(new ResourceLocation("forge", "springs"));
	public static final Tag<Item> CASING = ItemTags.getCollection().getOrCreate(new ResourceLocation("forge", "casings"));
	public static final Tag<Item> WIRE = ItemTags.getCollection().getOrCreate(new ResourceLocation("forge", "wires"));
}