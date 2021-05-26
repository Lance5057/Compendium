package lance5057.compendium.core.data.builders;

import lance5057.compendium.appendixes.carpentry.data.CarpentryBlockTags;
import lance5057.compendium.appendixes.construction.data.ConstructionBlockTags;
import lance5057.compendium.appendixes.oredressing.data.builders.OreBlockTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;

public class TCBlockTags extends BlockTagsProvider {
//	public static List<Pair<Tag<Block>, Block>> BlockTags = new ArrayList<Pair<Tag<Block>, Block>>();
//	public static List<Pair<Tag<Block>, Tag<Block>>> NestedTags = new ArrayList<Pair<Tag<Block>, Tag<Block>>>();

	public TCBlockTags(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void registerTags() {
	    OreBlockTags.registerTags(this);
	    CarpentryBlockTags.registerTags(this);
	    ConstructionBlockTags.registerTags(this);
	}

}
