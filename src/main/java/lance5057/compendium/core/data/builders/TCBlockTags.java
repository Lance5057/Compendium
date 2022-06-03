package lance5057.compendium.core.data.builders;

import lance5057.compendium.CompendiumMaterials;
import lance5057.compendium.Reference;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TCBlockTags extends BlockTagsProvider {
//	public static List<Pair<Tag<Block>, Block>> BlockTags = new ArrayList<Pair<Tag<Block>, Block>>();
//	public static List<Pair<Tag<Block>, Tag<Block>>> NestedTags = new ArrayList<Pair<Tag<Block>, Tag<Block>>>();

	public TCBlockTags(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
		super(generatorIn, Reference.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		for (MaterialHelper m : CompendiumMaterials.materials) {
			m.addBlockTags(this);
		}
	}

}
