package com.lance5057.compendium.client.models.multistylematerial;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.lance5057.compendium.client.ClientUtil;
import com.lance5057.compendium.client.models.multimaterial.MultiMaterialModelData;
import com.lance5057.compendium.client.models.style.StyleModelData;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.multimaterial.MultiMaterialType;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.ChunkRenderTypeSet;
import net.neoforged.neoforge.client.model.IDynamicBakedModel;
import net.neoforged.neoforge.client.model.data.ModelData;

public class MultiStyleMaterialBakedModel implements IDynamicBakedModel {
	BakedModel base;
	List<MultiStyleMaterialBakedModel.BakedLayer> bakedLayers;

	public MultiStyleMaterialBakedModel(BakedModel bake, List<MultiStyleMaterialBakedModel.BakedLayer> bakedLayers) {
		this.base = bake;
		this.bakedLayers = bakedLayers;
	}

	@Override
	public boolean useAmbientOcclusion() {
		return base.useAmbientOcclusion();
	}

	@Override
	public boolean isGui3d() {
		return base.isGui3d();
	}

	@Override
	public boolean usesBlockLight() {
		return base.usesBlockLight();
	}

	@Override
	public boolean isCustomRenderer() {
		return base.isCustomRenderer();
	}

	@Override
	public TextureAtlasSprite getParticleIcon() {
		return base.getParticleIcon();
	}

	@Override
	public ItemOverrides getOverrides() {
		return new MultiStyleMaterialOverrides();
	}

	@Override
	public ChunkRenderTypeSet getRenderTypes(@NotNull BlockState state, @NotNull RandomSource rand,
			@NotNull ModelData data) {

		return ChunkRenderTypeSet.of(RenderType.cutout(), RenderType.translucent(), RenderType.solid());
	}

	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource rand,
			ModelData extraData, @Nullable RenderType renderType) {
		List<BakedQuad> l = new ArrayList<BakedQuad>();

		for (int i = 0; i < bakedLayers.size(); i++)
			l.addAll(bakedLayers.get(i).getQuads(state, side, rand, extraData, renderType, base, i));

		return l;
	}

//	@Override
//	public ModelData getModelData(BlockAndTintGetter level, BlockPos pos, BlockState state, ModelData modelData) {
//		StyleModelData data = new StyleModelData();
//		MultiMaterialModelData data2 = new MultiMaterialModelData();
//
//		return modelData.derive().with(STYLE_DATA, data).with(MATERIAL_DATA, data2).build();
//	}

	public static class BakedLayer {
		public final List<MATERIAL_TYPES> validTypes;
//		public final Map<String, Map<String, BakedModel>> models;
		public final int materialLayer;
		public final int styleLayer;

		public BakedLayer(List<MATERIAL_TYPES> validTypes, Map<String, Map<String, BakedModel>> bakedModels,
				int materialLayer, int styleLayer) {
			this.validTypes = validTypes;
//			this.models = bakedModels;
			this.materialLayer = materialLayer;
			this.styleLayer = styleLayer;

		}

		public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource rand,
				ModelData extraData, @Nullable RenderType renderType, BakedModel base, int index) {

			List<MultiMaterialType> mats = extraData.get(MultiMaterialModelData.STATE);
			List<String> s = extraData.get(StyleModelData.STYLES);
			List<BakedQuad> l = new ArrayList<BakedQuad>();
			if (s != null && s.size() > 0)
				if (mats != null && mats.size() != 0 && mats.size() > materialLayer
						&& mats.get(materialLayer) != null) {

					String m = mats.get(materialLayer).getCurrentMaterial();
					String st = s.get(styleLayer);

					ResourceLocation rc = ClientUtil
							.createMaterialStyleLocation(mats.get(materialLayer).getType().getFirst(), m, st);
					BakedModel t = Minecraft.getInstance().getModelManager()
							.getModel(new ModelResourceLocation(rc, ""));

					t.getQuads(state, side, rand, extraData, renderType);
					workin here
//					Map<String, BakedModel> m = models.get(mats.get(materialLayer).getCurrentMaterial());
//					if (m != null && !m.isEmpty() && s.size() > styleLayer) {
//						BakedModel q = m.getOrDefault(s.get(styleLayer), null);
//						if (q != null) {
//							List<BakedQuad> r = q.getQuads(state, side, rand, extraData, renderType);
//							
//							if (r != null && !r.isEmpty()) {
//								if (renderType == null || q.getRenderTypes(state, rand, extraData).contains(renderType))
//									l.addAll(r);
//							}
//						}
//					}
				}

			return l;

		}

	}

}
