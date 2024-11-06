package com.lance5057.compendium;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class CompendiumTags {
	public static TagKey<Block> PRYABLE = BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", "pryable"));
	public static TagKey<Block> SAWABLE = BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", "sawable"));
}
