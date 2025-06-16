package com.lance5057.compendium.client.models.multistylematerial;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import com.lance5057.compendium.client.models.multimaterial.MultiMaterialModelData;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.multimaterial.MultiMaterialType;

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

	public MultiStyleMaterialBakedModel(BakedModel bake,
			List<MultiStyleMaterialBakedModel.BakedLayer> bakedLayers) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean useAmbientOcclusion() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isGui3d() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean usesBlockLight() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCustomRenderer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TextureAtlasSprite getParticleIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemOverrides getOverrides() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource rand,
			ModelData extraData, @Nullable RenderType renderType) {
		// TODO Auto-generated method stub
		return null;
	}

	public static class BakedLayer {
		public final List<MATERIAL_TYPES> validTypes;
		public final Map<String, BakedModel> models;

		public BakedLayer(List<MATERIAL_TYPES> validTypes, Map<String, BakedModel> models) {
			this.validTypes = validTypes;
			this.models = models;

		}

		public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource rand,
				ModelData extraData, @Nullable RenderType renderType, BakedModel base, int index) {
			@Nullable
			List<MultiMaterialType> mats = extraData.get(MultiMaterialModelData.STATE);
			List<BakedQuad> l = new ArrayList<BakedQuad>();
			if (mats != null && mats.size() >= index + 1) {
				BakedModel q = models.get(mats.get(index).getCurrentMaterial());
				if (q != null) {
					List<BakedQuad> r = q.getQuads(state, side, rand, extraData, renderType);
					if (r != null) {
						if (renderType == null || q.getRenderTypes(state, rand, extraData).contains(renderType))
							l.addAll(r);
					} else
						l.addAll(getInvalid(state, side, rand, extraData, renderType, base));
				} else
					l.addAll(getInvalid(state, side, rand, extraData, renderType, base));
			} else {
				l.addAll(getInvalid(state, side, rand, extraData, renderType, base));
			}

			return l;
		}

		public List<BakedQuad> getInvalid(@Nullable BlockState state, @Nullable Direction side, RandomSource rand,
				ModelData extraData, @Nullable RenderType renderType, BakedModel base) {
			List<BakedQuad> l = new ArrayList<BakedQuad>();

			BakedModel q = models.get("invalid");

			if (q != null) {
				List<BakedQuad> r = q.getQuads(state, side, rand, extraData, renderType);
				if (r != null)
					if (renderType == null || base.getRenderTypes(state, rand, extraData).contains(renderType))
						l.addAll(r);
			}

			return l;
		}
	}

}
