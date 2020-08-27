package lance5057.compendium.core.data.builders;

import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import lance5057.compendium.core.materials.CompendiumMaterials;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

public class TCBlockModels extends BlockStateProvider {
	private final ExistingFileHelper fh;

	public TCBlockModels(DataGenerator gen, String modid, ExistingFileHelper fh) {
		super(gen, modid, fh);
		this.fh = fh;
	}

	@Override
	protected void registerStatesAndModels() {
//		for(MaterialHelper m : CompendiumMaterials.materials)
//		{
//			for(MaterialBase mb: m.addons)
//			{
//				mb.setupBlockModels(this,fh);
//			}
//		}
	}
}