package lance5057.compendium.appendixes.metallurgy.data.builders;

import lance5057.compendium.Compendium;
import lance5057.compendium.appendixes.metallurgy.AppendixMetallurgy;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalMaterialBasic;
import lance5057.compendium.core.data.builders.TCItemTags;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraftforge.common.Tags;

public class MetalItemTags {

    public static void registerTags(TCItemTags itp) {
	Compendium.logger.info("\t - Item Tags");

	for (MetallurgyMaterialHelper mh : AppendixMetallurgy.metals) {

	    if (mh.hasBase()) {
		MetalMaterialBasic b = mh.getBase();

		itp.getOrCreateBuilder(Tags.Items.INGOTS).add(b.INGOT.get());
		INamedTag<Item> INGOT_MATERIAL = TCItemTags.ItemTag("ingots/" + mh.name);
		itp.getOrCreateBuilder(INGOT_MATERIAL).add(b.INGOT.get());
	    }
	}
    }

}
