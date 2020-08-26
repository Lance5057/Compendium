package lance5057.tDefense.core.data.builders;

import lance5057.tDefense.Reference;
import lance5057.tDefense.core.library.materialutilities.MaterialBase;
import lance5057.tDefense.core.library.materialutilities.MaterialHelper;
import lance5057.tDefense.core.materials.CompendiumMaterials;
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
