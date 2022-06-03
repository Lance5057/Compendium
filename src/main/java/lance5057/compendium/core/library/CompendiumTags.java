package lance5057.compendium.core.library;

import lance5057.compendium.Reference;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class CompendiumTags {
	public static final TagKey<Item> DUST = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "dusts"));
	public static final TagKey<Item> PLATE = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "plates"));
	public static final TagKey<Item> COIN = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "coins"));
	public static final TagKey<Item> GEAR = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "gears"));
	public static final TagKey<Item> ROD = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "rods"));
	public static final TagKey<Item> COIL = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "coils"));
	public static final TagKey<Item> SPRING = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "springs"));
	public static final TagKey<Item> CASING = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "casings"));
	public static final TagKey<Item> WIRE = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "wires"));
	public static final TagKey<Item> SETTING = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "settings"));
	public static final TagKey<Item> JUMPRINGS = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "jumprings"));
	public static final TagKey<Item> FILIGREE = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "filigree"));
	public static final TagKey<Item> FOIL = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "foils"));
	public static final TagKey<Item> CLASP = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "clasps"));
	public static final TagKey<Item> RINGSHANK = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "ringshanks"));
	public static final TagKey<Item> RIVETS = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "rivets"));
	public static final TagKey<Item> TINYDUST = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "tinydusts"));
	public static final TagKey<Item> KEY = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "keys"));

	public static final TagKey<Item> HAMMER = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "tools/hammers"));
	public static final TagKey<Item> SAW = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "tools/saw"));

	public static final TagKey<Item> PLANK = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "planks"));
	public static final TagKey<Item> LOG = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "log"));

	// Blocks
	public static final TagKey<Block> SHINGLESCAP = BlockTags
			.create(new ResourceLocation(Reference.MOD_ID, "shinglescap"));
	public static final TagKey<Block> SHINGLES = BlockTags
			.create(new ResourceLocation(Reference.MOD_ID, "shinglescap"));

	public static final TagKey<Block> MINEABLE_WITH_SAW = create("mineable/saw");

	private static TagKey<Block> create(String p_203847_) {
		return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(p_203847_));
	}

	public static TagKey<Block> create(ResourceLocation name) {
		return TagKey.create(Registry.BLOCK_REGISTRY, name);
	}
}