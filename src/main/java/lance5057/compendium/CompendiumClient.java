package lance5057.compendium;

import lance5057.compendium.core.client.VaultRenderer;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.materials.CompendiumMaterials;
import lance5057.compendium.core.workstations.client.CraftingAnvilRenderer;
import lance5057.compendium.core.workstations.client.HammeringStationRenderer;
import lance5057.compendium.core.workstations.client.SawhorseStationRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;

@OnlyIn(Dist.CLIENT)
public class CompendiumClient {
    public static void setRenderLayers() {
	for (MaterialHelper mh : CompendiumMaterials.materials) {
	    mh.client();
	}

	RenderType cutout = RenderType.getCutout();
	RenderTypeLookup.setRenderLayer(CompendiumBlocks.HAMMERING_STATION.get(), cutout);
	// RenderTypeLookup.setRenderLayer(CompendiumBlocks.CRAFTING_ANVIL.get(),
	// RenderType.getTranslucent());
    }

    public static void setTERenderers() {
	ClientRegistry.bindTileEntityRenderer(CompendiumTileEntities.HAMMERING_STATION_TE.get(),
		HammeringStationRenderer::new);
	ClientRegistry.bindTileEntityRenderer(CompendiumTileEntities.CRAFTING_ANVIL_TE.get(),
		CraftingAnvilRenderer::new);
	ClientRegistry.bindTileEntityRenderer(CompendiumTileEntities.SAWHORSE_STATION_TE.get(),
		SawhorseStationRenderer::new);

	ClientRegistry.bindTileEntityRenderer(CompendiumTileEntities.VAULT_TE.get(), VaultRenderer::new);
    }
}
