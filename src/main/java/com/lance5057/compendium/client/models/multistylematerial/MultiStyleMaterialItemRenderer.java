package com.lance5057.compendium.client.models.multistylematerial;

import org.jetbrains.annotations.Nullable;

import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.blocks.IStyleable;
import com.lance5057.compendium.blocks.chair.ChairBlock;
import com.lance5057.compendium.client.models.multimaterial.MultiMaterialModelData;
import com.lance5057.compendium.client.models.style.StyleModelData;
import com.lance5057.compendium.components.block.MultiMaterialBlockComponent;
import com.lance5057.compendium.components.block.StyleBlockComponent;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.ClientHooks;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelData.Builder;
import net.neoforged.neoforge.client.model.renderable.BakedModelRenderable;

public class MultiStyleMaterialItemRenderer extends BlockEntityWithoutLevelRenderer {
	protected static MultiStyleMaterialItemRenderer instance;

	public MultiStyleMaterialItemRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher,
			EntityModelSet entityModelSet) {
		super(blockEntityRenderDispatcher, entityModelSet);
	}

	public static MultiStyleMaterialItemRenderer getInstance() {
		if (instance == null) {
			instance = new MultiStyleMaterialItemRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
					Minecraft.getInstance().getEntityModels());
		}
		return instance;
	}

	@Override
	public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack ps, MultiBufferSource mbs,
			int packedLight, int overlay) {
		ps.pushPose();
		if (stack.getItem() instanceof BlockItem bi) {
			if (bi.getBlock() instanceof IStyleable st) {
				BakedModel bm;
				if (displayContext != ItemDisplayContext.GUI)
					bm = Minecraft.getInstance().getBlockRenderer().getBlockModel(
							bi.getBlock().defaultBlockState().setValue(ChairBlock.FACING, Direction.EAST));
				else {
					bm = Minecraft.getInstance().getBlockRenderer().getBlockModel(
							bi.getBlock().defaultBlockState().setValue(ChairBlock.FACING, Direction.WEST));
					ps.scale(0.75f, 0.75f, 0.75f);
					ps.translate(0.4, -0.1, 0);
				}

				@Nullable
				MultiMaterialBlockComponent mmt = stack.get(CompendiumComponents.MULTI_MATERIAL);
				@Nullable
				StyleBlockComponent s = stack.get(CompendiumComponents.STYLE);

				Builder md = ModelData.builder();

				if (mmt != null)
					md.with(MultiMaterialModelData.STATE, mmt.types());
				if (s != null)
					md.with(StyleModelData.STYLES, st.getCurrentAllString());

				bm = ClientHooks.handleCameraTransforms(ps, bm, displayContext, true);

				BakedModelRenderable bmr = BakedModelRenderable.of(bm);
				if (bmr != null) {
					bmr.withContext(md.build()).render(ps, mbs, texture -> RenderType.entityCutout(texture),
							packedLight, overlay, overlay, null);

				}
			}
		}

		ps.popPose();

	}
}
