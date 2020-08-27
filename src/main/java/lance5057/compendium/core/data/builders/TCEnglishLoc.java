package lance5057.compendium.core.data.builders;

import lance5057.compendium.Reference;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import lance5057.compendium.core.materials.CompendiumMaterials;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class TCEnglishLoc extends LanguageProvider {

	public TCEnglishLoc(DataGenerator gen) {
		super(gen, Reference.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
//		for(MaterialHelper m : CompendiumMaterials.materials)
//		{
//			for(MaterialBase mb: m.addons)
//			{
//				mb.setupEnglishLocalization(this);
//			}
//		}
	}

}
