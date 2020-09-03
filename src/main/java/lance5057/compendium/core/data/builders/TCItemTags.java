package lance5057.compendium.core.data.builders;

import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.CraftableMaterial;
import lance5057.compendium.core.library.materialutilities.addons.MeltableMaterial;
import lance5057.compendium.core.materials.CompendiumMaterials;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.Tags;

public class TCItemTags extends ItemTagsProvider {

	public TCItemTags(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void registerTags() {
		for (MaterialHelper mh : CompendiumMaterials.materials) {

			// Meltable Materials
			if (mh.getIngot() != null) {
				MeltableMaterial mm = mh.getIngot();
				getBuilder(Tags.Items.INGOTS).add(mm.INGOT.get());
				getBuilder(Tags.Items.NUGGETS).add(mm.NUGGET.get());
				getBuilder(Tags.Items.STORAGE_BLOCKS).add(mm.STORAGE_ITEMBLOCK.get());
			}

			// Craftable Materials
			if (mh.getGem() != null) {
				CraftableMaterial cm = mh.getGem();
				getBuilder(Tags.Items.GEMS).add(cm.GEM.get());
				getBuilder(Tags.Items.NUGGETS).add(cm.NUGGET.get());
				getBuilder(Tags.Items.STORAGE_BLOCKS).add(cm.STORAGE_ITEMBLOCK.get());
			}
		}
	}

}
