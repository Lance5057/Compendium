package lance5057.compendium.core.data.builders;

import lance5057.compendium.Reference;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import lance5057.compendium.core.materials.CompendiumMaterials;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelProvider;

public class TCItemModels extends ModelProvider<ItemModelBuilder> {
	private final ExistingFileHelper fh;
	
	public TCItemModels(DataGenerator generator, ExistingFileHelper fh) {
		super(generator, Reference.MOD_ID, ITEM_FOLDER, ItemModelBuilder::new, fh);
		this.fh = fh;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void registerModels() {
		for(MaterialHelper m : CompendiumMaterials.materials)
		{
			for(MaterialBase mb: m.addons)
			{
				mb.setupItemModels(this,fh);
			}
		}
	}
	
}
