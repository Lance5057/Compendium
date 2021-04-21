package lance5057.compendium.appendixes.metallurgy.data.builders;

import lance5057.compendium.Compendium;
import lance5057.compendium.appendixes.metallurgy.AppendixMetallurgy;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalMaterialBasic;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalMaterialComponents;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.library.CompendiumTags;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraftforge.common.Tags;

public class MetalItemTags {

    public static void registerTags(TCItemTags itp) {
	Compendium.logger.info("\t - Item Tags");

	for (MetallurgyMaterialHelper mh : AppendixMetallurgy.metals) {

	    if (mh.hasBase()) {
		MetalMaterialBasic b = mh.getBase();

		// Ingot
		itp.getOrCreateBuilder(Tags.Items.INGOTS).add(b.INGOT.get());
		INamedTag<Item> INGOT_MATERIAL = TCItemTags.ItemTag("ingots/" + mh.name);
		itp.getOrCreateBuilder(INGOT_MATERIAL).add(b.INGOT.get());

		// Nugget
		itp.getOrCreateBuilder(Tags.Items.NUGGETS).add(b.NUGGET.get());
		INamedTag<Item> NUGGET_MATERIAL = TCItemTags.ItemTag("nuggets/" + mh.name);
		itp.getOrCreateBuilder(NUGGET_MATERIAL).add(b.NUGGET.get());

		// Storage Block
		itp.getOrCreateBuilder(Tags.Items.STORAGE_BLOCKS).add(b.STORAGE_ITEMBLOCK.get());
		INamedTag<Item> BLOCK_MATERIAL = TCItemTags.ItemTag("storage_blocks/" + mh.name);
		itp.getOrCreateBuilder(BLOCK_MATERIAL).add(b.STORAGE_ITEMBLOCK.get());
	    }

	    if (mh.hasComponents()) {
		MetalMaterialComponents b = mh.getComponents();

		// Casing
		itp.getOrCreateBuilder(CompendiumTags.CASING).add(b.CASING.get());
		INamedTag<Item> CASING_MATERIAL = TCItemTags.ItemTag("casings/" + mh.name);
		itp.getOrCreateBuilder(CASING_MATERIAL).add(b.CASING.get());

		// Clasps
		itp.getOrCreateBuilder(CompendiumTags.CLASP).add(b.CLASP.get());
		INamedTag<Item> CLASP_MATERIAL = TCItemTags.ItemTag("clasps/" + mh.name);
		itp.getOrCreateBuilder(CLASP_MATERIAL).add(b.CLASP.get());

		// Coil
		itp.getOrCreateBuilder(CompendiumTags.COIL).add(b.COIL.get());
		INamedTag<Item> COIL_MATERIAL = TCItemTags.ItemTag("coils/" + mh.name);
		itp.getOrCreateBuilder(COIL_MATERIAL).add(b.COIL.get());

		// Coin
		itp.getOrCreateBuilder(CompendiumTags.COIN).add(b.COIN.get());
		INamedTag<Item> COIN_MATERIAL = TCItemTags.ItemTag("coins/" + mh.name);
		itp.getOrCreateBuilder(COIN_MATERIAL).add(b.COIN.get());

		// Dust
		itp.getOrCreateBuilder(CompendiumTags.DUST).add(b.DUST.get());
		INamedTag<Item> DUST_MATERIAL = TCItemTags.ItemTag("dusts/" + mh.name);
		itp.getOrCreateBuilder(DUST_MATERIAL).add(b.DUST.get());

		// Filigree
		itp.getOrCreateBuilder(CompendiumTags.FILIGREE).add(b.FILIGREE.get());
		INamedTag<Item> FILIGREE_MATERIAL = TCItemTags.ItemTag("filigrees/" + mh.name);
		itp.getOrCreateBuilder(FILIGREE_MATERIAL).add(b.FILIGREE.get());

		// Foil
		itp.getOrCreateBuilder(CompendiumTags.FOIL).add(b.FOIL.get());
		INamedTag<Item> FOIL_MATERIAL = TCItemTags.ItemTag("foils/" + mh.name);
		itp.getOrCreateBuilder(FOIL_MATERIAL).add(b.FOIL.get());

		// Gear
		itp.getOrCreateBuilder(CompendiumTags.GEAR).add(b.GEAR.get());
		INamedTag<Item> GEAR_MATERIAL = TCItemTags.ItemTag("gears/" + mh.name);
		itp.getOrCreateBuilder(GEAR_MATERIAL).add(b.GEAR.get());

		// Jumprings
		itp.getOrCreateBuilder(CompendiumTags.JUMPRINGS).add(b.JUMPRINGS.get());
		INamedTag<Item> JUMPRINGS_MATERIAL = TCItemTags.ItemTag("jumprings/" + mh.name);
		itp.getOrCreateBuilder(JUMPRINGS_MATERIAL).add(b.JUMPRINGS.get());

		// Key
		itp.getOrCreateBuilder(CompendiumTags.KEY).add(b.KEY.get());
		INamedTag<Item> KEY_MATERIAL = TCItemTags.ItemTag("keys/" + mh.name);
		itp.getOrCreateBuilder(KEY_MATERIAL).add(b.KEY.get());

		// Plate
		itp.getOrCreateBuilder(CompendiumTags.PLATE).add(b.PLATE.get());
		INamedTag<Item> PLATE_MATERIAL = TCItemTags.ItemTag("plates/" + mh.name);
		itp.getOrCreateBuilder(PLATE_MATERIAL).add(b.PLATE.get());

		// Ringshank
		itp.getOrCreateBuilder(CompendiumTags.RINGSHANK).add(b.RINGSHANK.get());
		INamedTag<Item> RINGSHANK_MATERIAL = TCItemTags.ItemTag("ringshanks/" + mh.name);
		itp.getOrCreateBuilder(RINGSHANK_MATERIAL).add(b.RINGSHANK.get());

		// Rivets
		itp.getOrCreateBuilder(CompendiumTags.RIVETS).add(b.RIVETS.get());
		INamedTag<Item> RIVETS_MATERIAL = TCItemTags.ItemTag("rivets/" + mh.name);
		itp.getOrCreateBuilder(RIVETS_MATERIAL).add(b.RIVETS.get());

		// Rod
		itp.getOrCreateBuilder(CompendiumTags.ROD).add(b.ROD.get());
		INamedTag<Item> ROD_MATERIAL = TCItemTags.ItemTag("rods/" + mh.name);
		itp.getOrCreateBuilder(ROD_MATERIAL).add(b.ROD.get());

		// Setting
		itp.getOrCreateBuilder(CompendiumTags.SETTING).add(b.SETTING.get());
		INamedTag<Item> SETTING_MATERIAL = TCItemTags.ItemTag("settings/" + mh.name);
		itp.getOrCreateBuilder(SETTING_MATERIAL).add(b.SETTING.get());

		// Springs
		itp.getOrCreateBuilder(CompendiumTags.SPRING).add(b.SPRING.get());
		INamedTag<Item> SPRING_MATERIAL = TCItemTags.ItemTag("springs/" + mh.name);
		itp.getOrCreateBuilder(SPRING_MATERIAL).add(b.SPRING.get());

		// Tinydust
		itp.getOrCreateBuilder(CompendiumTags.TINYDUST).add(b.TINYDUST.get());
		INamedTag<Item> TINYDUST_MATERIAL = TCItemTags.ItemTag("tinydusts/" + mh.name);
		itp.getOrCreateBuilder(TINYDUST_MATERIAL).add(b.TINYDUST.get());

		// Wires
		itp.getOrCreateBuilder(CompendiumTags.WIRE).add(b.WIRE.get());
		INamedTag<Item> WIRE_MATERIAL = TCItemTags.ItemTag("wires/" + mh.name);
		itp.getOrCreateBuilder(WIRE_MATERIAL).add(b.WIRE.get());

	    }
	}
    }

}
