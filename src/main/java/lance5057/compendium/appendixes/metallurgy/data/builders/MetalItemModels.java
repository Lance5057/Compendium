package lance5057.compendium.appendixes.metallurgy.data.builders;

import lance5057.compendium.appendixes.metallurgy.AppendixMetallurgy;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalAdvancedTools;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalMaterialBasic;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalMaterialComponents;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalVanillaTools;
import lance5057.compendium.core.data.builders.TCItemModels;

public class MetalItemModels {
    public static void registerModels(TCItemModels model) {
	for (MetallurgyMaterialHelper mh : AppendixMetallurgy.metals) {

	    if (mh.hasBase()) {
		MetalMaterialBasic b = mh.getBase();

		model.forItem(b.INGOT, mh.name);
		model.forItem(b.NUGGET, mh.name);
		model.forBlockItem(b.STORAGE_ITEMBLOCK, mh.name);
	    }
	    if (mh.hasVanillaTools()) {
		MetalVanillaTools b = mh.getVanillaTools();

		model.withExistingParent(b.AXE.getId().getPath(), model.mcLoc("item/handheld"))
			.texture("layer0", model.modLoc("item/material/" + mh.name + "/" + b.AXE.getId().getPath()))
			.texture("layer1", model.modLoc("item/axebase"));

		model.withExistingParent(b.HOE.getId().getPath(), model.mcLoc("item/handheld"))
			.texture("layer0", model.modLoc("item/material/" + mh.name + "/" + b.HOE.getId().getPath()))
			.texture("layer1", model.modLoc("item/hoebase"));

		model.withExistingParent(b.PICKAXE.getId().getPath(), model.mcLoc("item/handheld"))
			.texture("layer0", model.modLoc("item/material/" + mh.name + "/" + b.PICKAXE.getId().getPath()))
			.texture("layer1", model.modLoc("item/pickaxebase"));

		model.withExistingParent(b.SHOVEL.getId().getPath(), model.mcLoc("item/handheld"))
			.texture("layer0", model.modLoc("item/material/" + mh.name + "/" + b.SHOVEL.getId().getPath()))
			.texture("layer1", model.modLoc("item/shovelbase"));

		model.withExistingParent(b.SWORD.getId().getPath(), model.mcLoc("item/handheld"))
			.texture("layer0", model.modLoc("item/material/" + mh.name + "/" + b.SWORD.getId().getPath()))
			.texture("layer1", model.modLoc("item/swordbase"));
	    }
	    if (mh.hasComponents()) {
		MetalMaterialComponents b = mh.getComponents();

		model.forItem(b.SETTING, mh.name);
		model.forItem(b.JUMPRINGS, mh.name);
		model.forItem(b.FILIGREE, mh.name);
		model.forItem(b.FOIL, mh.name);
		model.forItem(b.COIL, mh.name);
		model.forItem(b.SPRING, mh.name);
		model.forItem(b.CASING, mh.name);
		model.forItem(b.WIRE, mh.name);
		model.forItem(b.CLASP, mh.name);
		model.forItem(b.RINGSHANK, mh.name);
		model.forItem(b.RIVETS, mh.name);
		model.forItem(b.PLATE, mh.name);
		model.forItem(b.COIN, mh.name);
		model.forItem(b.GEAR, mh.name);
		model.forItem(b.ROD, mh.name);
		model.forItem(b.DUST, mh.name);
		model.forItem(b.TINYDUST, mh.name);
		model.forItem(b.KEY, mh.name);
	    }
	    if (mh.hasAdvancedTools()) {
		MetalAdvancedTools b = mh.getAdvancedTools();

		model.withExistingParent(b.HAMMER.getId().getPath(), model.mcLoc("item/handheld"))
			.texture("layer0", model.modLoc("item/material/" + mh.name + "/" + b.HAMMER.getId().getPath()))
			.texture("layer1", model.modLoc("item/hammerbase"));
		model.withExistingParent(b.SAW.getId().getPath(), model.mcLoc("item/handheld"))
			.texture("layer0", model.modLoc("item/material/" + mh.name + "/" + b.SAW.getId().getPath()))
			.texture("layer1", model.modLoc("item/sawbase"));

		model.withExistingParent(b.BOW.getId().getPath(), model.mcLoc("item/handheld"))
			.texture("layer1", model.modLoc("item/material/" + mh.name + "/" + b.BOW.getId().getPath()))
			.texture("layer0", model.mcLoc("item/bow")).override().predicate(model.mcLoc("pulling"), 1)

			.model(model.withExistingParent(b.BOW.getId().getPath() + "_0", model.mcLoc("item/handheld"))
				.texture("layer1",
					model.modLoc("item/material/" + mh.name + "/" + b.BOW.getId().getPath() + "_pulling_0"))
				.texture("layer0", model.mcLoc("item/bow_pulling_0")))
			.end()

			.override().predicate(model.mcLoc("pulling"), 1).predicate(model.mcLoc("pull"), 0.65f)
			.model(model.withExistingParent(b.BOW.getId().getPath() + "_1", model.mcLoc("item/handheld"))
				.texture("layer1",
					model.modLoc("item/material/" + mh.name + "/" + b.BOW.getId().getPath() + "_pulling_1"))
				.texture("layer0", model.mcLoc("item/bow_pulling_1")))
			.end()

			.override().predicate(model.mcLoc("pulling"), 1).predicate(model.mcLoc("pull"), 0.9f)
			.model(model.withExistingParent(b.BOW.getId().getPath() + "_2", model.mcLoc("item/handheld"))
				.texture("layer1",
					model.modLoc("item/material/" + mh.name + "/" + b.BOW.getId().getPath() + "_pulling_2"))
				.texture("layer0", model.mcLoc("item/bow_pulling_2")));
	    }
	}
    }
}
