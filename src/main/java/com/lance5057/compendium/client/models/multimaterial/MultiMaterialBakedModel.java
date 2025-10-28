package com.lance5057.compendium.client.models.multimaterial;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.multimaterial.MultiMaterialType;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.ChunkRenderTypeSet;
import net.neoforged.neoforge.client.model.IDynamicBakedModel;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelProperty;

public class MultiMaterialBakedModel implements IDynamicBakedModel {
	private static final ModelProperty<MultiMaterialModelData> DATA = new ModelProperty<>();
	private final BakedModel base;
	List<MultiMaterialBakedModel.BakedLayer> layers = new ArrayList<MultiMaterialBakedModel.BakedLayer>();

	@SuppressWarnings("deprecation")
	public MultiMaterialBakedModel(BakedModel base, List<MultiMaterialBakedModel.BakedLayer> bakedLayers) {
		this.base = base;
		this.layers = bakedLayers;

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
	public ItemTransforms getTransforms() {
		return base.getTransforms();
	}

	@Override // FORGE: Get render types based on the selectors matched by the given block
				// state
	public ChunkRenderTypeSet getRenderTypes(@NotNull BlockState state, @NotNull RandomSource rand,
			@NotNull ModelData data) {

		return ChunkRenderTypeSet.of(RenderType.cutout(), RenderType.translucent(), RenderType.solid());
//		return this.base.getRenderTypes(state, rand, data);
	}

	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource rand,
			ModelData extraData, @Nullable RenderType renderType) {
		List<BakedQuad> l = new ArrayList<BakedQuad>();
//		l.addAll(this.base.getQuads(state, side, rand, extraData, renderType));

		for (int i = 0; i < layers.size(); i++)
			l.addAll(layers.get(i).getQuads(state, side, rand, extraData, renderType, base));

		return l;

	}

//	@Override
//	public ModelData getModelData(BlockAndTintGetter level, BlockPos pos, BlockState state, ModelData modelData) {
//		MultiMaterialModelData data = new MultiMaterialModelData();
//
//		return modelData.derive().with(DATA, data).build();
//	}

	public static class BakedLayer {
		public final List<MATERIAL_TYPES> validTypes;
		public final Map<String, BakedModel> models;
		public final int materialLayer;

		public BakedLayer(List<MATERIAL_TYPES> validTypes, Map<String, BakedModel> models, int materialLayer) {
			this.validTypes = validTypes;
			this.models = models;
			this.materialLayer = materialLayer;

		}

		public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource rand,
				ModelData extraData, @Nullable RenderType renderType, BakedModel base) {
			@Nullable
			List<MultiMaterialType> mats = extraData.get(MultiMaterialModelData.STATE);
			List<BakedQuad> l = new ArrayList<BakedQuad>();
			if (mats != null && mats.size() >= materialLayer) {
				BakedModel q = models.get(mats.get(materialLayer).getCurrentMaterial());
				if (q != null) {
					List<BakedQuad> r = q.getQuads(state, side, rand, extraData, renderType);
					if (r != null) {
						if (renderType == null || q.getRenderTypes(state, rand, extraData).contains(renderType))
							l.addAll(r);
					}
				}
			}

			return l;
		}
	}
}
