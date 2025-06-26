package com.lance5057.compendium.client.models.multistylematerial;

import java.util.List;

import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;

import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.client.models.multimaterial.MultiMaterialModelData;
import com.lance5057.compendium.client.models.style.StyleModelData;
import com.lance5057.compendium.components.block.MultiMaterialBlockComponent;
import com.lance5057.compendium.components.block.StyleBlockComponent;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelData.Builder;
import net.neoforged.neoforge.client.model.renderable.BakedModelRenderable;

public abstract class MultiStyleMaterialItemRenderer extends BlockEntityWithoutLevelRenderer {
	protected static MultiStyleMaterialItemRenderer instance;

	public MultiStyleMaterialItemRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher,
			EntityModelSet entityModelSet) {
		super(blockEntityRenderDispatcher, entityModelSet);
	}

	@Override
	public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack ps, MultiBufferSource mbs,
			int packedLight, int overlay) {
		ps.pushPose();

		if (displayContext == ItemDisplayContext.GUI) {

			ps.mulPose(new Quaternionf().rotateXYZ((float) Math.toRadians(30), (float) Math.toRadians(225), 0));
			ps.translate(-0.05f, 1.0f, -1.9f);
			ps.scale(1.25f, 1.25f, 1.25f);
		} else if (displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND
				|| displayContext == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND) {
			ps.translate(0, 0.35, -0.17f);
		} else {
			ps.translate(0, 0.25f, 0f);

			if (stack.getItem() instanceof BlockItem bi) {
				BakedModel bm = Minecraft.getInstance().getBlockRenderer()
						.getBlockModel(bi.getBlock().defaultBlockState());

				@Nullable
				MultiMaterialBlockComponent mmt = stack.get(CompendiumComponents.MULTI_MATERIAL);
				@Nullable
				StyleBlockComponent s = stack.get(CompendiumComponents.STYLE);

				Builder md = ModelData.builder();

				if (mmt != null)
					md.with(MultiMaterialModelData.STATE, mmt.types());
				if (s != null)
					md.with(StyleModelData.STYLES, getStyles(s.styles()));

				BakedModelRenderable.of(bm).withContext(md.build());
			}
		}

		ps.popPose();

	}

	public abstract List<String> getStyles(List<Integer> curStyles);
}
