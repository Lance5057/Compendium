package lance5057.compendium.appendixes.oredressing.data.builders;

import lance5057.compendium.appendixes.oredressing.AppendixOreDressing;
import lance5057.compendium.appendixes.oredressing.materialhelper.OreDressingMaterialHelper;
import net.minecraft.data.BlockTagsProvider;
import net.minecraftforge.common.Tags;

public class OreBlockTags {

    public static void registerTags(BlockTagsProvider btp)
    {
	for (OreDressingMaterialHelper mh : AppendixOreDressing.ores) {
	    if (mh.hasOre())
		btp.getOrCreateBuilder(Tags.Blocks.ORES).add(mh.getOre().ORE.get());
	    if (mh.hasDenseOre())
		btp.getOrCreateBuilder(Tags.Blocks.ORES).add(mh.getDenseOre().ORE.get());
	    if (mh.hasSparseOre())
		btp.getOrCreateBuilder(Tags.Blocks.ORES).add(mh.getSparseOre().ORE.get());
	}
	
    }

}
