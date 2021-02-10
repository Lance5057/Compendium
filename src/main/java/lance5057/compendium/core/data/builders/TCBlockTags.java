package lance5057.compendium.core.data.builders;

import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.MaterialAdvancedExtraComponents;
import lance5057.compendium.core.library.materialutilities.addons.MaterialOre;
import lance5057.compendium.core.materials.CompendiumMaterials;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ForgeBlockTagsProvider;
import static net.minecraftforge.common.Tags.Blocks.*;

public class TCBlockTags extends BlockTagsProvider {
//	public static List<Pair<Tag<Block>, Block>> BlockTags = new ArrayList<Pair<Tag<Block>, Block>>();
//	public static List<Pair<Tag<Block>, Tag<Block>>> NestedTags = new ArrayList<Pair<Tag<Block>, Tag<Block>>>();

	public TCBlockTags(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void registerTags() {
		for (MaterialHelper mh : CompendiumMaterials.materials) {
			if(mh.getAdvancedComponents() != null)
			{
				MaterialAdvancedExtraComponents ec = mh.getAdvancedComponents();
				
				this.getOrCreateBuilder(BlockTags.WALLS).add(ec.WALL.get());
			}
			if(mh.getOre() != null)
			{
			    MaterialOre ore = mh.getOre();
			    
			    this.getOrCreateBuilder(ORES).add(ore.ORE.get());
			}
		}
	}

}
