package lance5057.compendium.core.data.builders;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.MaterialExtraComponents;
import lance5057.compendium.core.materials.CompendiumMaterials;
import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;

public class TCBlockTags extends BlockTagsProvider {
//	public static List<Pair<Tag<Block>, Block>> BlockTags = new ArrayList<Pair<Tag<Block>, Block>>();
//	public static List<Pair<Tag<Block>, Tag<Block>>> NestedTags = new ArrayList<Pair<Tag<Block>, Tag<Block>>>();

	public TCBlockTags(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void registerTags() {
		for (MaterialHelper mh : CompendiumMaterials.materials) {
			if(mh.getExtraComponents() != null)
			{
				MaterialExtraComponents ec = mh.getExtraComponents();
				
				this.getOrCreateBuilder(BlockTags.WALLS).add(ec.WALL.get());
			}
		}
	}

}
