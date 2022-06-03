package lance5057.compendium.core.library.materialutilities;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import lance5057.compendium.core.data.builders.TCBlockModels;
import lance5057.compendium.core.data.builders.TCBlockTags;
import lance5057.compendium.core.data.builders.TCEnglishLoc;
import lance5057.compendium.core.data.builders.TCItemModels;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.data.builders.TCRecipes;
import lance5057.compendium.core.data.builders.loottables.TCBlockLoot;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialMetal;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.ForgeTier;

public class MaterialHelper {

	public String name;
	public String type;

	public ForgeTier tier;

	ItemLike definingItem;

	public List<MaterialBase> addons;

	public MaterialHelper(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public MaterialHelper(String name) {
		this.name = name;
		addons = new ArrayList<MaterialBase>();
	}

	protected void setDefiningItem(ItemLike item) {
		definingItem = item;
	}

	public ItemLike getDefiningItem() {
		return definingItem;
	}

	public MaterialHelper addMetalBase() {
		addons.add(new MaterialMetal());
		return this;
	}
//	public void setBase() {
//		switch (type) {
//		case "wood":
//
//		case "gem":
//		case "stone":
//		case "metal":
//			base = new MaterialMetal();
//			break;
//		default:
//			break;
//		}
//	}

	public void setupClient() {
	}

	public void setup() {
		for (MaterialBase mb : addons)
			mb.setup(this);
	}

	public void registerItemModels(TCItemModels model) {
		for (MaterialBase mb : addons)
			mb.registerItemModels(model, name);
	}

	public void registerBlockModels(TCBlockModels model) {
		for (MaterialBase mb : addons)
			mb.registerBlockModels(model, name);
	}

	public void addTranslations(TCEnglishLoc tcEnglishLoc) {
		for (MaterialBase mb : addons)
			mb.addTranslations(tcEnglishLoc, name.substring(0,1).toUpperCase() + name.substring(1));
	}

	public void addItemTags(TCItemTags tags) {
		for (MaterialBase mb : addons)
			mb.registerItemTags(tags, name);
	}

	public void addBlockTags(TCBlockTags tags) {
		for (MaterialBase mb : addons)
			mb.registerBlockTags(tags, name);
	}

	public void addBlockLoot(TCBlockLoot loot) {
		for (MaterialBase mb : addons)
			mb.buildLootTable(loot, name);
	}

	public void addRecipes(TCRecipes recipes, Consumer<FinishedRecipe> consumer) {
		for (MaterialBase mb : addons)
			mb.buildRecipes(recipes, consumer, name);
	}
}
