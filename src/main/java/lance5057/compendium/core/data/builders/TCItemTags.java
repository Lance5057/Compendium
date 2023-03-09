package lance5057.compendium.core.data.builders;

import lance5057.compendium.Compendium;
import lance5057.compendium.CompendiumTags;
import lance5057.compendium.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TCItemTags extends ItemTagsProvider {

	public TCItemTags(DataGenerator generatorIn, BlockTagsProvider blockTagProvider,
			ExistingFileHelper existingFileHelper) {
		super(generatorIn, blockTagProvider, Reference.MOD_ID, existingFileHelper);
		Compendium.logger.info("\t - Item Tags");
	}

	@Override
	protected void addTags() {
		tag(CompendiumTags.AXES).add(Items.NETHERITE_AXE, Items.DIAMOND_AXE, Items.IRON_AXE, Items.GOLDEN_AXE,
				Items.STONE_AXE, Items.WOODEN_AXE);
		tag(CompendiumTags.PICKAXES).add(Items.NETHERITE_PICKAXE, Items.DIAMOND_PICKAXE, Items.IRON_PICKAXE, Items.GOLDEN_PICKAXE,
				Items.STONE_PICKAXE, Items.WOODEN_PICKAXE);
	}
}
