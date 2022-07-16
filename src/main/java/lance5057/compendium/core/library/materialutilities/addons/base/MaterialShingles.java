package lance5057.compendium.core.library.materialutilities.addons.base;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import lance5057.compendium.Compendium;
import lance5057.compendium.CompendiumBlocks;
import lance5057.compendium.CompendiumItems;
import lance5057.compendium.core.blocks.BlockShingles;
import lance5057.compendium.core.blocks.BlockShinglesCap;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

public class MaterialShingles extends MaterialBase {
	
	public static List<MaterialHelper> WOODS = new ArrayList<MaterialHelper>();

	RegistryObject<Block> SHINGLE_BLOCK;
	public List<RegistryObject<BlockShingles>> SHINGLES;
	public List<RegistryObject<BlockShingles>> SHINGLES_ALT;
	public List<RegistryObject<BlockShinglesCap>> SHINGLES_CAPS;
//    public List<RegistryObject<BlockShinglesCap>> SHINGLES_CAPS_ALT;

	RegistryObject<BlockItem> SHINGLES_ITEMBLOCK;
	public List<RegistryObject<BlockItem>> SHINGLESITEM;
	public List<RegistryObject<BlockItem>> SHINGLESITEM_ALT;
	public List<RegistryObject<BlockItem>> SHINGLESITEM_CAPS;
//    public List<RegistryObject<BlockNamedItem>> SHINGLESITEM_CAPS_ALT;

	public MaterialShingles() {
		SHINGLES = new ArrayList<RegistryObject<BlockShingles>>();
		SHINGLES_ALT = new ArrayList<RegistryObject<BlockShingles>>();
		SHINGLES_CAPS = new ArrayList<RegistryObject<BlockShinglesCap>>();
//	SHINGLES_CAPS_ALT = new ArrayList<RegistryObject<BlockShinglesCap>>();

		SHINGLESITEM = new ArrayList<RegistryObject<BlockItem>>();
		SHINGLESITEM_ALT = new ArrayList<RegistryObject<BlockItem>>();
		SHINGLESITEM_CAPS = new ArrayList<RegistryObject<BlockItem>>();
//	SHINGLESITEM_CAPS_ALT = new ArrayList<RegistryObject<BlockNamedItem>>();
	}

	@Override
	public void setup(MaterialHelper helper) {
		SHINGLE_BLOCK = CompendiumBlocks.BLOCKS.register(helper.name + "_shinglesblock",
				() -> new Block(Block.Properties.of(Material.METAL).strength(5F, 10F).sound(SoundType.METAL)));

		SHINGLES_ITEMBLOCK = CompendiumItems.ITEMS.register(helper.name + "_shingles_itemblock",
				() -> new BlockItem(SHINGLE_BLOCK.get(), new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));
		
		for(MaterialHelper mh : WOODS)
		{
			if(mh.getDefiningItem() != null)
			{
				RegistryObject<BlockShingles> SHINGLE = cmh.BLOCKS.register(cmh.name + "_" + m.name + "_shingles",
					() -> new BlockShingles(() -> SHINGLE_BLOCK.get().getDefaultState(),
						Block.Properties.create(Material.WOOD).hardnessAndResistance(5F, 10F)
							.sound(SoundType.METAL).notSolid()));

				RegistryObject<BlockShingles> SHINGLE_ALT = cmh.BLOCKS.register(
					cmh.name + "_" + m.name + "_shinglesalt",
					() -> new BlockShingles(() -> SHINGLE_BLOCK.get().getDefaultState(),
						Block.Properties.create(Material.WOOD).hardnessAndResistance(5F, 10F)
							.sound(SoundType.METAL).notSolid()));

				RegistryObject<BlockNamedItem> SHINGLEITEM = cmh.ITEMS
					.register(cmh.name + "_" + m.name + "_shinglesitem", () -> new BlockNamedItem(SHINGLE.get(),
						new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));

				RegistryObject<BlockNamedItem> SHINGLEITEM_ALT = cmh.ITEMS.register(
					cmh.name + "_" + m.name + "_shinglesitem_alt", () -> new BlockNamedItem(SHINGLE_ALT.get(),
						new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));

				RegistryObject<BlockShinglesCap> SHINGLE_CAP = cmh.BLOCKS.register(
					cmh.name + "_" + m.name + "_shingles_cap",
					() -> new BlockShinglesCap(Block.Properties.create(Material.WOOD).hardnessAndResistance(5F, 10F)
						.sound(SoundType.METAL).notSolid()));

				RegistryObject<BlockNamedItem> SHINGLEITEM_CAP = cmh.ITEMS.register(
					cmh.name + "_" + m.name + "_shinglesitem_cap", () -> new BlockNamedItem(SHINGLE_CAP.get(),
						new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));

//			    RegistryObject<BlockShinglesCap> SHINGLE_CAP_ALT = cmh.BLOCKS
//				    .register(cmh.name + "_" + m.name + "_shingles_cap_alt", () -> new BlockShinglesCap(Block.Properties
//					    .create(Material.WOOD).hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));
		//
//			    RegistryObject<BlockNamedItem> SHINGLEITEM_CAP_ALT = cmh.ITEMS.register(
//				    cmh.name + "_" + m.name + "_shinglesitem_cap_alt", () -> new BlockNamedItem(SHINGLE_CAP_ALT.get(),
//					    new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));

				SHINGLES.add(SHINGLE);
				SHINGLES_ALT.add(SHINGLE_ALT);
				SHINGLES_CAPS.add(SHINGLE_CAP);
//			    SHINGLES_CAPS_ALT.add(SHINGLE_CAP_ALT);

				SHINGLESITEM_CAPS.add(SHINGLEITEM_CAP);
//			    SHINGLESITEM_CAPS_ALT.add(SHINGLEITEM_CAP_ALT);
				SHINGLESITEM.add(SHINGLEITEM);
				SHINGLESITEM_ALT.add(SHINGLEITEM_ALT);
			}
			else
				Compendium.logger.warn(mh.name + " doesn't have a defining item, ignoring!");
		}
	}

	@Override
	public void setupClient(MaterialHelper helper) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerBlockModels(TCBlockModels model, String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerItemModels(TCItemModels model, String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addTranslations(TCEnglishLoc loc, String capName) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void buildRecipes(TCRecipes recipes, Consumer<FinishedRecipe> consumer, String name) {
		// TODO Auto-generated method stub

	}

}
