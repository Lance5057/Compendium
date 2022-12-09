//package lance5057.compendium.core.library.materialutilities.addons.base;
//
//import java.util.function.Consumer;
//
//import lance5057.compendium.core.data.builders.TCBlockModels;
//import lance5057.compendium.core.data.builders.TCBlockTags;
//import lance5057.compendium.core.data.builders.TCEnglishLoc;
//import lance5057.compendium.core.data.builders.TCItemModels;
//import lance5057.compendium.core.data.builders.TCItemTags;
//import lance5057.compendium.core.data.builders.TCRecipes;
//import lance5057.compendium.core.library.materialutilities.MaterialHelper;
//import net.minecraft.data.loot.BlockLoot;
//import net.minecraft.data.recipes.FinishedRecipe;
//import net.minecraftforge.event.world.BiomeLoadingEvent;
//
//public abstract class MaterialBase {
//
//	public abstract void setup(MaterialHelper helper);
//	public abstract void setupClient(MaterialHelper helper);
//
//	public abstract void registerBlockModels(TCBlockModels model, String name);
//
//	public abstract void registerItemModels(TCItemModels model, String name);
//
//	public abstract void addTranslations(TCEnglishLoc loc, String capName);
//
//	public abstract void registerItemTags(TCItemTags itp, String name);
//	public abstract void registerBlockTags(TCBlockTags tags, String name);
//
//	public abstract void buildLootTable(BlockLoot table, String name);
//
//	public abstract void buildRecipes(TCRecipes recipes, Consumer<FinishedRecipe> consumer, String name);
//	
//	public void biomeEvent(BiomeLoadingEvent event, String name) {}
//}
