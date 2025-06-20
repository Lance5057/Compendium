package com.lance5057.compendium.client.models.multistylematerial;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import com.lance5057.compendium.client.models.multimaterial.MultiMaterialModelData;
import com.lance5057.compendium.client.models.style.StyleModelData;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.multimaterial.MultiMaterialType;
import com.lance5057.compendium.styleblock.StyleType;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
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
		return base.getOverrides();
	}

	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource rand,
			ModelData extraData, @Nullable RenderType renderType) {
		List<BakedQuad> l = new ArrayList<BakedQuad>();

		for (int i = 0; i < bakedLayers.size(); i++)
			l.addAll(bakedLayers.get(i).getQuads(state, side, rand, extraData, renderType, base, i));

		return l;
	}

	public static class BakedLayer {
		public final List<MATERIAL_TYPES> validTypes;
		public final Map<String, Map<String, BakedModel>> models;

		public BakedLayer(List<MATERIAL_TYPES> validTypes, Map<String, Map<String, BakedModel>> bakedModels) {
			this.validTypes = validTypes;
			this.models = bakedModels;

		}

		public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource rand,
				ModelData extraData, @Nullable RenderType renderType, BakedModel base, int index) {
			@Nullable
			List<MultiMaterialType> mats = extraData.get(MultiMaterialModelData.STATE);
			List<StyleType> s = extraData.get(StyleModelData.STYLES);
			List<BakedQuad> l = new ArrayList<BakedQuad>();
			if (s != null)
				if (mats != null && mats.size() >= index + 1) {
					Map<String, BakedModel> m = models.get(mats.get(index).getCurrentMaterial());
					if (m != null) {
						BakedModel q = m.getOrDefault(s.getCurrentStyle(), null);
						if (q != null) {
							List<BakedQuad> r = q.getQuads(state, side, rand, extraData, renderType);
							if (r != null) {
								if (renderType == null || q.getRenderTypes(state, rand, extraData).contains(renderType))
									l.addAll(r);
							}
						}
					}
				}

			return l;
		}

//		public List<BakedQuad> getInvalid(@Nullable BlockState state, @Nullable Direction side, RandomSource rand,
//				ModelData extraData, @Nullable RenderType renderType, BakedModel base) {
//			List<BakedQuad> l = new ArrayList<BakedQuad>();
//
//			BakedModel q = models.get("invalid");
//
//			if (q != null) {
//				List<BakedQuad> r = q.getQuads(state, side, rand, extraData, renderType);
//				if (r != null)
//					if (renderType == null || base.getRenderTypes(state, rand, extraData).contains(renderType))
//						l.addAll(r);
//			}
//
//			return l;
//		}
	}

}
