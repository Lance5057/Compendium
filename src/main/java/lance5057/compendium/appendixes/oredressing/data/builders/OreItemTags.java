package lance5057.compendium.appendixes.oredressing.data.builders;

import lance5057.compendium.Compendium;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;

public class OreItemTags extends ItemTagsProvider {

    public OreItemTags(DataGenerator generatorIn, BlockTagsProvider blockTagProvider) {
	super(generatorIn, blockTagProvider);
	Compendium.logger.info("\t - Item Tags");
    }

    @Override
    protected void registerTags() {
	
    }

}
