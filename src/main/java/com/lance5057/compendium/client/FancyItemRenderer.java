package com.lance5057.compendium.client;

import java.util.Map;
import java.util.Map.Entry;

import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;

import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.client.models.multimaterial.MultiMaterialModelData;
import com.lance5057.compendium.client.models.style.StyleModelData;
import com.lance5057.compendium.components.block.MultiMaterialBlockComponent;
import com.lance5057.compendium.components.block.StyleBlockComponent;
import com.lance5057.compendium.styleblock.IStyleBlock;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelData.Builder;
import net.neoforged.neoforge.client.model.renderable.BakedModelRenderable;
import net.neoforged.neoforge.client.model.renderable.IRenderable;

public class FancyItemRenderer extends BlockEntityWithoutLevelRenderer {
	protected static FancyItemRenderer instance;

	public FancyItemRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher, EntityModelSet entityModelSet) {
		super(blockEntityRenderDispatcher, entityModelSet);
	}

	public static FancyItemRenderer getInstance() {
		if (instance == null) {
			instance = new FancyItemRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
					Minecraft.getInstance().getEntityModels());
		}
		return instance;
	}

	@Override
	public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack ps, MultiBufferSource mbs,
			int packedLight, int overlay) {
		ps.pushPose();
		if (stack.getItem() instanceof BlockItem bi) {
			if (bi.getBlock() instanceof IStyleBlock st) {

				@Nullable
				MultiMaterialBlockComponent mmt = stack.get(CompendiumComponents.MULTI_MATERIAL);
				@Nullable
				StyleBlockComponent s = stack.get(CompendiumComponents.STYLE);

				Builder md = ModelData.builder();

				if (mmt != null)
					md.with(MultiMaterialModelData.STATE, mmt.getTypes());
				if (s != null)
					md.with(StyleModelData.STYLES, st.getStyles(s.styles()));
				
				Map<Property<?>, Comparable<?>> p = bi.getBlock().defaultBlockState().getValues();
				String variant = "";

				for (Entry<Property<?>, Comparable<?>> x : p.entrySet()) {
					variant += x.getKey().getName() + "=" + getName(x.getKey(), x.getValue()) + ",";
				}
				if (variant.length() > 0 && variant.charAt(variant.length()-1) == ',')
					variant = variant.substring(0, variant.length() - 1);

				BakedModel bm = Minecraft.getInstance().getModelManager()
						.getModel(new ModelResourceLocation(st.getItemModelLocation(), variant));

				BakedModelRenderable bmr = BakedModelRenderable.of(bm);
				IRenderable<ModelData> ir = bmr.withModelDataContext();

				if (displayContext == ItemDisplayContext.GUI) {
					ps.translate(1, 0, 0);
					ps.mulPose(new Quaternionf().fromAxisAngleDeg(0, 1, 0, -90));
				}

				if (ir != null) {
					ir.render(ps, mbs, texture -> RenderType.entityCutout(texture), packedLight,
							overlay, 0, md.build());

				}

			}
		}

		ps.popPose();

	}
	
	private static <T extends Comparable<T>> String getName(Property<T> property, Comparable<?> value) {
		return property.getName((T) value);
	}
}
