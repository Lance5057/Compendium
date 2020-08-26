package lance5057.tDefense;

import lance5057.tDefense.core.library.materialutilities.MaterialHelper;
import lance5057.tDefense.core.materials.CompendiumMaterials;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TCClient {
	public static void setRenderLayers() {
		for(MaterialHelper mh : CompendiumMaterials.materials)
		{
			mh.client();
		}
	}
}
