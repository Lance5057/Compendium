package lance5057.compendium.core.library.materialutilities.addons.base;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class MaterialShingles extends MaterialBase {

	RegistryObject<Block> SHINGLE_BLOCK;
	public List<RegistryObject<BlockShingles>> SHINGLES;
	public List<RegistryObject<BlockShingles>> SHINGLES_ALT;
	public List<RegistryObject<BlockShinglesCap>> SHINGLES_CAPS;
//    public List<RegistryObject<BlockShinglesCap>> SHINGLES_CAPS_ALT;

	RegistryObject<BlockNamedItem> SHINGLES_ITEMBLOCK;
	public List<RegistryObject<BlockNamedItem>> SHINGLESITEM;
	public List<RegistryObject<BlockNamedItem>> SHINGLESITEM_ALT;
	public List<RegistryObject<BlockNamedItem>> SHINGLESITEM_CAPS;
//    public List<RegistryObject<BlockNamedItem>> SHINGLESITEM_CAPS_ALT;

	public MaterialShingles() {
		SHINGLES = new ArrayList<RegistryObject<BlockShingles>>();
		SHINGLES_ALT = new ArrayList<RegistryObject<BlockShingles>>();
		SHINGLES_CAPS = new ArrayList<RegistryObject<BlockShinglesCap>>();
//	SHINGLES_CAPS_ALT = new ArrayList<RegistryObject<BlockShinglesCap>>();

		SHINGLESITEM = new ArrayList<RegistryObject<BlockNamedItem>>();
		SHINGLESITEM_ALT = new ArrayList<RegistryObject<BlockNamedItem>>();
		SHINGLESITEM_CAPS = new ArrayList<RegistryObject<BlockNamedItem>>();
//	SHINGLESITEM_CAPS_ALT = new ArrayList<RegistryObject<BlockNamedItem>>();
	}

	@Override
	public void setup(MaterialHelper helper) {
		// TODO Auto-generated method stub

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
