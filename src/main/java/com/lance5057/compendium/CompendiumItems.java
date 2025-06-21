package com.lance5057.compendium;

import java.util.stream.Stream;

import javax.annotation.Nullable;

import com.lance5057.compendium.blocks.chair.StyleChairBlockEntity;
import com.lance5057.compendium.components.block.MultiMaterialBlockComponent;
import com.lance5057.compendium.components.block.StyleBlockComponent;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.items.Adjustinator;
import com.lance5057.compendium.items.MegalithStoneItem;
import com.lance5057.compendium.multimaterial.MultiMaterialType;
import com.lance5057.compendium.styleblock.StyleType;
import com.lance5057.compendium.workstations.cosmetictoolbox.CosmeticToolboxItem;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CompendiumItems {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Compendium.MOD_ID);

	public static final DeferredItem<Item> SAWDUST = ITEMS.register("sawdust", () -> new Item(new Item.Properties()) {
		@Override
		public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
			return 300;
		}
	});

	public static final DeferredItem<Item> ADJUSTINATOR = ITEMS.register("adjustinator",
			() -> new Adjustinator(new Item.Properties()));

	public static final DeferredItem<Item> MEGALITH_STONE = ITEMS.register("megalith_stone",
			() -> new MegalithStoneItem(new Item.Properties()));

	public static final DeferredItem<BlockItem> HAMMERING_STATION = ITEMS.register("hammering_station",
			() -> new BlockItem(CompendiumBlocks.HAMMERING_STATION.get(), new Item.Properties()));

	public static final DeferredItem<BlockItem> SAW_BUCK = ITEMS.register("saw_buck",
			() -> new BlockItem(CompendiumBlocks.SAW_BUCK.get(), new Item.Properties()));

	public static final DeferredItem<BlockItem> SCRAPPING_TABLE = ITEMS.register("scrapping_table",
			() -> new BlockItem(CompendiumBlocks.SCRAPPING_TABLE.get(), new Item.Properties()));

	public static final DeferredItem<BlockItem> WORKBENCH = ITEMS.register("workbench",
			() -> new BlockItem(CompendiumBlocks.WORKBENCH.get(), new Item.Properties()));

	public static final DeferredItem<BlockItem> TOOLRACK = ITEMS.register("toolrack",
			() -> new BlockItem(CompendiumBlocks.TOOLRACK.get(), new Item.Properties()));

	public static final DeferredItem<BlockItem> COMPONENT_DRAWER = ITEMS.register("component_drawer",
			() -> new BlockItem(CompendiumBlocks.COMPONENT_DRAWER.get(), new Item.Properties()));

	public static final DeferredItem<BlockItem> COSMETIC_TOOLBOX = ITEMS.register("cosmetic_toolbox",
			() -> new CosmeticToolboxItem(CompendiumBlocks.COSMETIC_TOOLBOX.get(), new Item.Properties()));

	public static final DeferredItem<Item> CHAIR = ITEMS.register("chair",
			() -> new BlockItem(CompendiumBlocks.CHAIR.get(), new Item.Properties()
					.component(CompendiumComponents.MULTI_MATERIAL.get(),
							new MultiMaterialBlockComponent(
									Stream.of(new MultiMaterialType(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD),
											new MultiMaterialType(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD),
											new MultiMaterialType(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD)).toList()))
					.component(CompendiumComponents.STYLE, new StyleBlockComponent(Stream.of(0, 0, 0).toList()))));

	public static final DeferredItem<Item> WINDOW = ITEMS.register("window",
			() -> new BlockItem(CompendiumBlocks.WINDOW.get(),
					new Item.Properties().component(CompendiumComponents.MULTI_MATERIAL.get(),
							new MultiMaterialBlockComponent(Stream
									.of(new MultiMaterialType(MATERIAL_TYPES.GLASS),
											new MultiMaterialType(MATERIAL_TYPES.METAL, MATERIAL_TYPES.WOOD))
									.toList()))));
}
