package com.lance5057.compendium.blocks.chair;

import java.util.List;

import com.lance5057.compendium.client.models.multistylematerial.MultiStyleMaterialItemRenderer;
import com.lance5057.compendium.style.StyleData;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;

public class ChairItemRenderer extends MultiStyleMaterialItemRenderer {

	public ChairItemRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher, EntityModelSet entityModelSet) {
		super(blockEntityRenderDispatcher, entityModelSet);
	}

	@Override
	public List<String> getStyles(List<Integer> curStyles) {
		return List.of(StyleData.CHAIR_BACK.getTypes().get(curStyles.get(0)),
				StyleData.CHAIR_SEAT.getTypes().get(curStyles.get(1)),
				StyleData.CHAIR_LEGS.getTypes().get(curStyles.get(2)));
	}

	public static MultiStyleMaterialItemRenderer getInstance() {
		if (instance == null) {
			instance = new ChairItemRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
					Minecraft.getInstance().getEntityModels());
		}
		return instance;
	}

}
