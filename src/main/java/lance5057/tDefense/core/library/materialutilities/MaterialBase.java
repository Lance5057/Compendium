package lance5057.tDefense.core.library.materialutilities;

import java.io.PrintWriter;

import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public abstract interface MaterialBase {

	
	
	// public abstract void setupPre(MaterialHelper mat);

	// public abstract void setupInit(MaterialHelper mat);

	// public abstract void setupIntegration(MaterialIntegration mi);

	// public abstract void setupPost(MaterialHelper mat);

	public abstract void setupClient(MaterialHelper mat);

	public abstract void setupModels(MaterialHelper mat);

	public abstract void setup(final FMLCommonSetupEvent event);

	public abstract void setupWiki(MaterialHelper mat, PrintWriter out);

	public abstract void setupItemTags();

	public abstract void setupBlockTags();

	public abstract void setupRecipes();

	public abstract void setupItemModels(ModelProvider<ItemModelBuilder> mp, ExistingFileHelper fh);

	public abstract void setupBlockModels(BlockStateProvider bsp, ExistingFileHelper fh);
	
	public abstract void setupEnglishLocalization(LanguageProvider lang);
	
	public abstract void setupLoot();
}
