package com.lance5057.compendium.data;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumItems;
import com.lance5057.compendium.CompendiumTags;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.util.TagUtil;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ItemTagGen extends ItemTagsProvider {

	public ItemTagGen(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider,
			CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
		super(pOutput, pLookupProvider, pBlockTags, Compendium.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider pProvider) {
		tag(Tags.Items.TOOLS).addOptionalTag(CompendiumTags.HAMMER);
		tag(Tags.Items.TOOLS).addOptionalTag(CompendiumTags.PRYBAR);
		tag(Tags.Items.TOOLS).addOptionalTag(CompendiumTags.SAW);
		tag(Tags.Items.MINING_TOOL_TOOLS).addOptionalTag(CompendiumTags.HAMMER);

		tag(CompendiumTags.HAMMER).add(CompendiumItems.CRUDE_HAMMER.asItem());
		tag(CompendiumTags.SAW).add(CompendiumItems.CRUDE_SAW.asItem());

		CompendiumIndex.index.forEach(i -> {
			if (i instanceof _MaterialBase mb) {
				mb.BLOCKS.forEach(b -> b.itemTag(this));
				mb.ITEMS.forEach(b -> b.itemTag(this));
				
				mb.extensions.forEach(e -> {
					e.BLOCKS.forEach(b -> b.itemTag(this));
					e.ITEMS.forEach(b -> b.itemTag(this));
				});
			}
		});

		tag(ItemTags.create(TagUtil.neoTag("wooden_slabs/acacia"))).add(Items.ACACIA_SLAB);
		tag(ItemTags.create(TagUtil.neoTag("slabs/wooden/acacia"))).add(Items.ACACIA_SLAB);

		tag(ItemTags.create(TagUtil.neoTag("stairs/wooden/acacia"))).add(Items.ACACIA_STAIRS);

		tag(ItemTags.create(TagUtil.neoTag("wooden_slabs/birch"))).add(Items.BIRCH_SLAB);
		tag(ItemTags.create(TagUtil.neoTag("slabs/wooden/birch"))).add(Items.BIRCH_SLAB);

		tag(ItemTags.create(TagUtil.neoTag("stairs/wooden/birch"))).add(Items.BIRCH_STAIRS);

		tag(ItemTags.create(TagUtil.neoTag("wooden_slabs/cherry"))).add(Items.CHERRY_SLAB);
		tag(ItemTags.create(TagUtil.neoTag("slabs/wooden/cherry"))).add(Items.CHERRY_SLAB);

		tag(ItemTags.create(TagUtil.neoTag("stairs/wooden/cherry"))).add(Items.CHERRY_STAIRS);

		tag(ItemTags.create(TagUtil.neoTag("wooden_slabs/crimson"))).add(Items.CRIMSON_SLAB);
		tag(ItemTags.create(TagUtil.neoTag("slabs/wooden/crimson"))).add(Items.CRIMSON_SLAB);

		tag(ItemTags.create(TagUtil.neoTag("stairs/wooden/crimson"))).add(Items.CRIMSON_STAIRS);

		tag(ItemTags.create(TagUtil.neoTag("wooden_slabs/dark_oak"))).add(Items.DARK_OAK_SLAB);
		tag(ItemTags.create(TagUtil.neoTag("slabs/wooden/dark_oak"))).add(Items.DARK_OAK_SLAB);

		tag(ItemTags.create(TagUtil.neoTag("stairs/wooden/dark_oak"))).add(Items.DARK_OAK_STAIRS);

		tag(ItemTags.create(TagUtil.neoTag("wooden_slabs/jungle"))).add(Items.JUNGLE_SLAB);
		tag(ItemTags.create(TagUtil.neoTag("slabs/wooden/jungle"))).add(Items.JUNGLE_SLAB);

		tag(ItemTags.create(TagUtil.neoTag("stairs/wooden/jungle"))).add(Items.JUNGLE_STAIRS);

		tag(ItemTags.create(TagUtil.neoTag("wooden_slabs/mangrove"))).add(Items.MANGROVE_SLAB);
		tag(ItemTags.create(TagUtil.neoTag("slabs/wooden/mangrove"))).add(Items.MANGROVE_SLAB);

		tag(ItemTags.create(TagUtil.neoTag("stairs/wooden/mangrove"))).add(Items.MANGROVE_STAIRS);

		tag(ItemTags.create(TagUtil.neoTag("wooden_slabs/oak"))).add(Items.OAK_SLAB);
		tag(ItemTags.create(TagUtil.neoTag("slabs/wooden/oak"))).add(Items.OAK_SLAB);

		tag(ItemTags.create(TagUtil.neoTag("stairs/wooden/oak"))).add(Items.OAK_STAIRS);

		tag(ItemTags.create(TagUtil.neoTag("wooden_slabs/spruce"))).add(Items.SPRUCE_SLAB);
		tag(ItemTags.create(TagUtil.neoTag("slabs/wooden/spruce"))).add(Items.SPRUCE_SLAB);

		tag(ItemTags.create(TagUtil.neoTag("stairs/wooden/spruce"))).add(Items.SPRUCE_STAIRS);

		tag(ItemTags.create(TagUtil.neoTag("wooden_slabs/warped"))).add(Items.WARPED_SLAB);
		tag(ItemTags.create(TagUtil.neoTag("slabs/wooden/warped"))).add(Items.WARPED_SLAB);

		tag(ItemTags.create(TagUtil.neoTag("stairs/wooden/warped"))).add(Items.WARPED_STAIRS);

		tag(ItemTags.create(TagUtil.neoTag("slabs/wooden"))).add(Items.ACACIA_SLAB, Items.BIRCH_SLAB, Items.CHERRY_SLAB,
				Items.CRIMSON_SLAB, Items.DARK_OAK_SLAB, Items.JUNGLE_SLAB, Items.MANGROVE_SLAB, Items.OAK_SLAB,
				Items.SPRUCE_SLAB, Items.WARPED_SLAB);

		tag(ItemTags.create(TagUtil.neoTag("textiles/black_wool"))).add(Items.BLACK_WOOL);
		tag(ItemTags.create(TagUtil.neoTag("textiles/blue_wool"))).add(Items.BLUE_WOOL);
		tag(ItemTags.create(TagUtil.neoTag("textiles/brown_wool"))).add(Items.BROWN_WOOL);
		tag(ItemTags.create(TagUtil.neoTag("textiles/cyan_wool"))).add(Items.CYAN_WOOL);
		tag(ItemTags.create(TagUtil.neoTag("textiles/gray_wool"))).add(Items.GRAY_WOOL);
		tag(ItemTags.create(TagUtil.neoTag("textiles/green_wool"))).add(Items.GREEN_WOOL);
		tag(ItemTags.create(TagUtil.neoTag("textiles/light_blue_wool"))).add(Items.LIGHT_BLUE_WOOL);
		tag(ItemTags.create(TagUtil.neoTag("textiles/light_gray_wool"))).add(Items.LIGHT_GRAY_WOOL);
		tag(ItemTags.create(TagUtil.neoTag("textiles/lime_wool"))).add(Items.LIME_WOOL);
		tag(ItemTags.create(TagUtil.neoTag("textiles/magenta_wool"))).add(Items.MAGENTA_WOOL);
		tag(ItemTags.create(TagUtil.neoTag("textiles/orange_wool"))).add(Items.ORANGE_WOOL);
		tag(ItemTags.create(TagUtil.neoTag("textiles/pink_wool"))).add(Items.PINK_WOOL);
		tag(ItemTags.create(TagUtil.neoTag("textiles/purple_wool"))).add(Items.PURPLE_WOOL);
		tag(ItemTags.create(TagUtil.neoTag("textiles/red_wool"))).add(Items.RED_WOOL);
		tag(ItemTags.create(TagUtil.neoTag("textiles/yellow_wool"))).add(Items.YELLOW_WOOL);
		tag(ItemTags.create(TagUtil.neoTag("textiles/white_wool"))).add(Items.WHITE_WOOL);
	}

}
