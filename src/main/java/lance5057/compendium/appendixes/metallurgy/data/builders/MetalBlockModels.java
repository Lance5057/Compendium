package lance5057.compendium.appendixes.metallurgy.data.builders;

import lance5057.compendium.Reference;
import lance5057.compendium.appendixes.metallurgy.AppendixMetallurgy;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalMaterialBasic;
import lance5057.compendium.core.data.builders.TCBlockModels;
import net.minecraft.util.ResourceLocation;

public class MetalBlockModels {
    public static void registerModels(TCBlockModels model) {
	for (MetallurgyMaterialHelper mh : AppendixMetallurgy.metals) {

	    if (mh.hasBase()) {
		MetalMaterialBasic b = mh.getBase();

		model.simpleBlock(b.STORAGE_BLOCK.get(), model.models().cubeAll(
			b.STORAGE_BLOCK.get().getRegistryName().getPath(),
			new ResourceLocation(Reference.MOD_ID, "block/material/" + mh.name + "/" + mh.name + "block")));
	    }
	}
    }
}
