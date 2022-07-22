package lance5057.compendium.core.library.materialutilities.addons.base;

import java.util.function.Consumer;

import lance5057.compendium.CompendiumBlocks;
import lance5057.compendium.CompendiumItems;
import lance5057.compendium.CompendiumTileEntities;
import lance5057.compendium.core.blocks.ItemDisplayBlock;
import lance5057.compendium.core.data.builders.TCBlockModels;
import lance5057.compendium.core.data.builders.TCBlockTags;
import lance5057.compendium.core.data.builders.TCEnglishLoc;
import lance5057.compendium.core.data.builders.TCItemModels;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.data.builders.TCRecipes;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class MaterialStatues extends MaterialBase {

	public RegistryObject<ItemDisplayBlock> STATUE_BLOCK;
	public RegistryObject<ItemDisplayBlock> HAND_STATUE_BLOCK;
	
	public RegistryObject<BlockItem> STATUE_ITEMBLOCK;
	public RegistryObject<BlockItem> HAND_STATUE_ITEMBLOCK;
	
	@Override
	public void setup(MaterialHelper helper) {
		STATUE_BLOCK = CompendiumBlocks.BLOCKS.register(helper.name + "_statueblock",
				() -> new ItemDisplayBlock());
		HAND_STATUE_BLOCK = CompendiumBlocks.BLOCKS.register(helper.name + "_handstatueblock",
				() -> new ItemDisplayBlock());
		
		STATUE_ITEMBLOCK = CompendiumItems.ITEMS.register(helper.name + "_statueitemblock",
				() -> new BlockItem(STATUE_BLOCK.get(), new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));
		HAND_STATUE_ITEMBLOCK = CompendiumItems.ITEMS.register(helper.name + "_handstatueitemblock",
				() -> new BlockItem(HAND_STATUE_BLOCK.get(), new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));
		
		CompendiumTileEntities.displayBlocks.add(STATUE_BLOCK);
		CompendiumTileEntities.displayBlocks.add(HAND_STATUE_BLOCK);
	}

	@Override
	public void setupClient(MaterialHelper helper) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerBlockModels(TCBlockModels model, String name) {
		model.horizontalBlock(STATUE_BLOCK.get(),
				model.models().withExistingParent(name + "statue", model.modLoc("block/bases/statue"))
					.texture("0", model.modLoc("block/material/" + name + "/" + name + "gemblock")));
		
		model.horizontalBlock(HAND_STATUE_BLOCK.get(),
				model.models().withExistingParent(name + "handstatue", model.modLoc("block/bases/handstatue"))
					.texture("0", model.modLoc("block/material/" + name + "/" + name + "gemblock")));
	}

	@Override
	public void registerItemModels(TCItemModels model, String name) {
		model.forBlockItem(STATUE_ITEMBLOCK, name);
		model.forBlockItem(HAND_STATUE_ITEMBLOCK, name);
	}

	@Override
	public void addTranslations(TCEnglishLoc loc, String capName) {
		loc.add(STATUE_ITEMBLOCK.get(), capName + " Statue");
		loc.add(HAND_STATUE_ITEMBLOCK.get(), capName + " Hand");
	}

	@Override
	public void registerItemTags(TCItemTags itp, String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerBlockTags(TCBlockTags tags, String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildLootTable(BlockLoot table, String name) {
		table.dropSelf(STATUE_BLOCK.get());
		table.dropSelf(HAND_STATUE_BLOCK.get());
	}

	@Override
	public void buildRecipes(TCRecipes recipes, Consumer<FinishedRecipe> consumer, String name) {
		// TODO Auto-generated method stub

	}

}
