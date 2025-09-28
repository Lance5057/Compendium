package com.lance5057.compendium.index.material.extensions.wood;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.blocks.PipeStyleBlock;
import com.lance5057.compendium.blocks.RotatedPillarStyleBlock;
import com.lance5057.compendium.blocks.SimpleStyleBlock;
import com.lance5057.compendium.blocks.SlabStyleBlock;
import com.lance5057.compendium.blocks.StairStyleBlock;
import com.lance5057.compendium.data.IndexBlockModelProvider;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.extensions.MaterialExtensionSerializer;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.util.CompendiumBlockHandler;
import com.lance5057.compendium.index.util.DataUtil;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ExtensionExtraPlanks extends _MaterialExtension {
	public final CompendiumBlockHandler PLANK;
	public final CompendiumBlockHandler PLANK_BLOCK;
	public final CompendiumBlockHandler PLANK_SLAB;
	public final CompendiumBlockHandler PLANK_CORNER;
	public final CompendiumBlockHandler PLANK_STAIRS;

	private TagKey<Item> plankTag;

	public ExtensionExtraPlanks(boolean plank, boolean plankBlock, boolean plankSlab, boolean plankCorner,
			boolean plankStairs) {
		PLANK = new CompendiumBlockHandler("plank");
		PLANK_BLOCK = new CompendiumBlockHandler("plank_block");
		PLANK_SLAB = new CompendiumBlockHandler("plank_slab");
		PLANK_CORNER = new CompendiumBlockHandler("plank_corner");
		PLANK_STAIRS = new CompendiumBlockHandler("plank_stairs");

		PLANK.setEnabled(plank);
		PLANK_BLOCK.setEnabled(plankBlock);
		PLANK_SLAB.setEnabled(plankSlab);
		PLANK_CORNER.setEnabled(plankCorner);
		PLANK_STAIRS.setEnabled(plankStairs);
	}

	@Override
	public void setup(_MaterialBase base) {
		PLANK.setup(base, () -> new PipeStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
		PLANK_BLOCK.setup(base, () -> new SimpleStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
		PLANK_CORNER.setup(base, () -> new RotatedPillarStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
		PLANK_SLAB.setup(base, () -> new SlabStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_SLAB)));
		PLANK_STAIRS.setup(base, () -> new StairStyleBlock(PLANK_BLOCK.BLOCK.get().defaultBlockState(),
				Block.Properties.ofFullCopy(Blocks.DARK_OAK_STAIRS)));
		CompendiumBlockEntities.validStyleBlocks.add(PLANK.BLOCK);
		CompendiumBlockEntities.validStyleBlocks.add(PLANK_BLOCK.BLOCK);
		CompendiumBlockEntities.validStyleBlocks.add(PLANK_CORNER.BLOCK);
		CompendiumBlockEntities.validStyleBlocks.add(PLANK_SLAB.BLOCK);
		CompendiumBlockEntities.validStyleBlocks.add(PLANK_STAIRS.BLOCK);
	}

	@Override
	public void tab(_MaterialBase base, Output output) {
		PLANK.tab(base, output);
	}

	@Override
	public void blockStateModel(_MaterialBase base, BlockStateProvider bsp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void blockModel(_MaterialBase base, IndexBlockModelProvider ibmp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemModel(_MaterialBase base, ItemModelProvider tmp) {
		DataUtil.basicMaterial3DItem(tmp, PLANK.BLOCK_ITEM.get(), base, Compendium.modLoc("item/plank"), base.getType(),
				tmp.mcLoc("block/" + base.name.toLowerCase() + "_planks"));
	}

	@Override
	public void engLoc(_MaterialBase base, LanguageProvider lp) {
		StringBuilder material_name = new StringBuilder();
		for (String word : base.name.split("_")) {
			word = word.substring(0, 1).toUpperCase() + word.substring(1);
			material_name.append(word).append(" ");
		}
		if (PLANK.enabled()) {
			lp.add(this.PLANK.BLOCK_ITEM.get(), material_name + "Plank");
		}
	}

	@Override
	public void recipes(_MaterialBase base, RecipeOutput consumer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void blockLoot(_MaterialBase base, BlockLootSubProvider blp) {
		blp.dropSelf(PLANK.BLOCK.get());
		blp.dropSelf(PLANK_BLOCK.BLOCK.get());
		blp.dropSelf(PLANK_SLAB.BLOCK.get());
		blp.dropSelf(PLANK_CORNER.BLOCK.get());
		blp.dropSelf(PLANK_STAIRS.BLOCK.get());
	}

	@Override
	public void setupItemTags(_MaterialBase base, ItemTagsProvider itp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupBlockTags(_MaterialBase base, BlockTagsProvider itp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupClient(_MaterialBase base, FMLClientSetupEvent event) {
		// TODO Auto-generated method stub

	}

	public static class Serializer extends MaterialExtensionSerializer<ExtensionExtraPlanks> {

		public Serializer() {
			super("EXTRAPLANKS");
		}

		@Override
		public JsonElement serialize(ExtensionExtraPlanks src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("type", type);
			j.addProperty("loadPlank", src.PLANK.enabled());
			j.addProperty("loadPlankBlock", src.PLANK_BLOCK.enabled());
			j.addProperty("loadPlankSlab", src.PLANK_SLAB.enabled());
			j.addProperty("loadPlankCorner", src.PLANK_CORNER.enabled());
			j.addProperty("loadPlankStairs", src.PLANK_STAIRS.enabled());

			return j;
		}

		@Override
		public ExtensionExtraPlanks deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			boolean loadPlank = j.get("loadPlank").getAsBoolean();
			boolean loadPlankBlock = j.get("loadPlankBlock").getAsBoolean();
			boolean loadPlankSlab = j.get("loadPlankSlab").getAsBoolean();
			boolean loadPlankCorner = j.get("loadPlankCorner").getAsBoolean();
			boolean loadPlankStairs = j.get("loadPlankStairs").getAsBoolean();

			return new ExtensionExtraPlanks(loadPlank, loadPlankBlock, loadPlankSlab, loadPlankCorner, loadPlankStairs);
		}

	}

	@Override
	public void otherLoot(_MaterialBase base, LootTableSubProvider lsp) {
		// TODO Auto-generated method stub
		
	}

}
