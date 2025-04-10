package com.lance5057.compendium.index.material.extensions.stone;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.extensions.MaterialExtensionSerializer;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.util.DataUtil;
import com.lance5057.compendium.styleblock.StyleItem;

import net.minecraft.core.component.DataComponents;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class ExtensionStoneStyleBlocks extends _MaterialExtension {

	boolean loadTile = false;
	public DeferredBlock<StyleStoneTileBlock> TILE;
	public DeferredItem<StyleItem> TILE_ITEM;

	private TagKey<Item> blockItemTag;
	private TagKey<Block> blockTag;

	public ExtensionStoneStyleBlocks(boolean loadTile) {
		this.loadTile = loadTile;
	}

	@Override
	public void setup(_MaterialBase base) {
		TILE = CompendiumIndex.BLOCKS.register(base.name + "_tile",
				() -> new StyleStoneTileBlock(Block.Properties.ofFullCopy(Blocks.STONE)) {
					@Override
					public String getStyleFromBlock(BlockItemStateProperties bisp) {
						if (bisp != null)
							return base.name + ".style." + bisp.get(StyleStoneTileBlock.STYLE);
						return base.name + "style.0";
					}

					@Override
					public String getStyleFromBlock(int i) {
						return base.name + ".style." + i;
					}
				});
		TILE_ITEM = CompendiumIndex.ITEMS.register(base.name + "_tile_item",
				() -> new StyleItem(TILE.get(), new Item.Properties().component(DataComponents.BLOCK_STATE,
						BlockItemStateProperties.EMPTY.with(StyleStoneTileBlock.STYLE, 0))) {
					@Override
					public String getStyleFromBlock(BlockItemStateProperties bisp) {
						if (bisp != null)
							return base.name + ".style." + bisp.get(StyleStoneTileBlock.STYLE);
						return base.name + "style.0";
					}

					@Override
					public String getStyleFromBlock(int i) {
						return base.name + ".style." + i;
					}

				});
	}

	@Override
	public void tab(_MaterialBase base, Output out) {
		if (this.loadTile) {
			out.accept(TILE_ITEM);
		}
	}

	@Override
	public void blockModel(_MaterialBase base, BlockStateProvider bsp) {
		if (this.loadTile) {

//			ModelFile test = 
			bsp.getVariantBuilder(TILE.get()).forAllStates(state -> {
//				return ConfiguredModel.builder().modelFile(test).build();
				int style = state.getValue(StyleStoneTileBlock.STYLE);

				String suffix = (StyleStoneTileBlock.Styles.values()[style] + "").toLowerCase();
//				return ConfiguredModel.builder()
//						.modelFile(bsp.models().cubeAll(base.name + "_tile_" + suffix + "_base",
//								bsp.modLoc("block/material/" + base.name + "/tile/" + base.name + "_"
//										+ suffix.toLowerCase() + "_tile_block")))
//						.modelFile(bsp.models()
//								.withExistingParent(base.name + "_tile_" + suffix,
//										bsp.modLoc(base.name + "_tile_" + suffix + "_base"))
//								.customLoader(MaterialSwapModelBuilder::begin).setType(MATERIAL_TYPES.Stone).end())
//
//						.build();
				return ConfiguredModel.builder()
						.modelFile(bsp.models().cubeAll(
								"block/material/" + base.getType().toString().toLowerCase() + "/" + base.name + "/"
										+ base.name + "_tile_" + suffix + "_block",
								bsp.modLoc("block/material/" + base.getType().toString().toLowerCase() + "/" + base.name
										+ "/" + suffix.toLowerCase())))
						.build();

			});

		}
	}

	@Override
	public void itemModel(_MaterialBase base, ItemModelProvider tmp) {
		if (this.loadTile) {
			DataUtil.basicMaterialBlockItem(tmp, TILE_ITEM, base.name, "tile_full", base.getType());
		}
	}

	@Override
	public void engLoc(_MaterialBase base, LanguageProvider lp) {
		StringBuilder material_name = new StringBuilder();
		for (String word : base.name.split("_")) {
			word = word.substring(0, 1).toUpperCase() + word.substring(1);
			material_name.append(word).append(" ");
		}
		if (this.loadTile) {
			lp.add(this.TILE.asItem(), material_name + "Tile");
			lp.add(Compendium.MOD_ID + ".tooltip." + base.name + ".style.0", material_name + "Full Tile");
			lp.add(Compendium.MOD_ID + ".tooltip." + base.name + ".style.1", material_name + "Half Tile");
		}
	}

	@Override
	public void recipes(_MaterialBase base, RecipeOutput consumer) {
		if (this.loadTile) {

		}
	}

	@Override
	public void blockLoot(_MaterialBase base, BlockLootSubProvider blp) {
		if (this.loadTile) {
			blp.dropSelf(this.TILE.get());
		}
	}

	@Override
	public void setupItemTags(_MaterialBase base, ItemTagsProvider itp) {
		if (this.loadTile) {

		}
	}

	@Override
	public void setupBlockTags(_MaterialBase base, BlockTagsProvider itp) {
		if (this.loadTile) {
			itp.tag(BlockTags.MINEABLE_WITH_PICKAXE);
		}
	}

	@Override
	public void setupClient(_MaterialBase base, FMLClientSetupEvent event) {
		if (this.loadTile) {

		}
	}

	public static class Serializer extends MaterialExtensionSerializer<ExtensionStoneStyleBlocks> {

		public Serializer() {
			super("STONESTYLEBLOCKS");
		}

		@Override
		public JsonElement serialize(ExtensionStoneStyleBlocks src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("type", type);
			j.addProperty("loadTile", src.loadTile);

			return j;
		}

		@Override
		public ExtensionStoneStyleBlocks deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			boolean loadTile = j.get("loadTile").getAsBoolean();

			return new ExtensionStoneStyleBlocks(loadTile);
		}

	}
}
