package lance5057.compendium.core.library.materialutilities.addons.base;

import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public abstract interface MaterialBase {

	public abstract void setupClient(MaterialHelper mat);

	public abstract void setupModels(MaterialHelper mat);
	
	public abstract void setupItems(MaterialHelper mat);
	
	public abstract void setupBlocks(MaterialHelper mat);

	public abstract void setup(final FMLCommonSetupEvent event);

	//public abstract void setupWiki(MaterialHelper mat, PrintWriter out);

	//public abstract void setupItemTags();

	//public abstract void setupBlockTags();

	//public abstract void setupRecipes();

	//public abstract void setupItemModels(ModelProvider<ItemModelBuilder> mp, ExistingFileHelper fh);

	//public abstract void setupBlockModels(BlockStateProvider bsp, ExistingFileHelper fh);
	
	//public abstract void setupEnglishLocalization(LanguageProvider lang);
	
	//public abstract void setupLoot();
}
