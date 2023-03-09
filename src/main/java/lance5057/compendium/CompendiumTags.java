package lance5057.compendium;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class CompendiumTags {
	public static final TagKey<Item> AXES = ItemTags.create(new ResourceLocation("forge", "axes"));
	public static final TagKey<Item> PICKAXES = ItemTags.create(new ResourceLocation("forge", "pickaxes"));
}
