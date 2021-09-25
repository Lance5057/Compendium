package lance5057.compendium.core.client;

import lance5057.compendium.core.entities.SeatEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class SeatRenderer extends EntityRenderer<SeatEntity> {

    public SeatRenderer(EntityRendererManager manager) {
	super(manager);
    }

    @Override
    public ResourceLocation getEntityTexture(SeatEntity entity) {
	// TODO Auto-generated method stub
	return null;
    }

}
