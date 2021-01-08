package lance5057.compendium;

import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.materials.CompendiumMaterials;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CompendiumClient {
	public static void setRenderLayers() {
		for(MaterialHelper mh : CompendiumMaterials.materials)
		{
			mh.client();
		}
		
		RenderType cutout = RenderType.getCutout();
		RenderTypeLookup.setRenderLayer(CompendiumBlocks.HAMMERING_STATION.get(), cutout);
		//RenderTypeLookup.setRenderLayer(CompendiumBlocks.CRAFTING_ANVIL.get(), RenderType.getTranslucent());
	}
}
