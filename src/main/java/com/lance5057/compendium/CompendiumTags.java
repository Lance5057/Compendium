package com.lance5057.compendium;

import com.lance5057.compendium.util.TagUtil;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class CompendiumTags {
	
	public static TagKey<Block> PRYABLE = BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", "pryable"));
	public static TagKey<Block> SAWABLE = BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", "sawable"));
	
	public static TagKey<Item> HAMMER = ItemTags.create(TagUtil.neoTag("tools/hammer"));
	public static TagKey<Item> PRYBAR = ItemTags.create(TagUtil.neoTag("tools/prybar"));
	public static TagKey<Item> SAW = ItemTags.create(TagUtil.neoTag("tools/saw"));
	
	public static TagKey<Block> TABLE = BlockTags.create(Compendium.modLoc("table"));
	
	public static TagKey<Item> PLANK = ItemTags.create(TagUtil.neoTag("plank"));
	
	public static TagKey<Item> TEXTILES = ItemTags.create(TagUtil.neoTag("textiles"));
}
