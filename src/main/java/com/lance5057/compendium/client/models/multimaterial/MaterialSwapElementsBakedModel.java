package com.lance5057.compendium.client.models.multimaterial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.lance5057.compendium.client.models.multimaterial.model.BasicIndexModel;
import com.lance5057.compendium.client.models.style.StyleModelData;
import com.lance5057.compendium.multimaterial.MultiMaterialType;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.ChunkRenderTypeSet;
import net.neoforged.neoforge.client.model.IDynamicBakedModel;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelProperty;

public class MaterialSwapElementsBakedModel implements IDynamicBakedModel {
	private static final ModelProperty<MultiMaterialModelData> DATA = new ModelProperty<>();
	private final BakedModel base;
	Map<String, BasicIndexModel> quads = new HashMap<String, BasicIndexModel>();

	@SuppressWarnings("deprecation")
	public MaterialSwapElementsBakedModel(BakedModel base, Map<String, BasicIndexModel> quads) {
		this.base = base;
		this.quads = quads;

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

		return this.base.getRenderTypes(state, rand, data);
	}

	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource rand,
			ModelData extraData, @Nullable RenderType renderType) {
		List<BakedQuad> l = new ArrayList<BakedQuad>();

		@Nullable
		List<MultiMaterialType> mats = extraData.get(MultiMaterialModelData.STATE);

		l.addAll(this.base.getQuads(state, side, rand, extraData, renderType));

		if (mats != null && mats.size() > 0) {
			for (int i = 0; i < mats.size(); i++) {
				BasicIndexModel q = quads.get(mats.get(0).getCurrentMaterial());
				if (q != null && q.model != null) {
					List<BakedQuad> r = q.model.getQuads(state, side, rand, extraData, renderType);
					if (r != null)
						if (renderType == null || base.getRenderTypes(state, rand, extraData).contains(renderType))
							l.addAll(r);
				}
			}
		} else {
			BasicIndexModel q = quads.get("invalid");

			if (q != null)
				if (q.model != null) {
					List<BakedQuad> r = q.model.getQuads(state, side, rand, extraData, renderType);
					if (r != null)
						if (renderType == null || base.getRenderTypes(state, rand, extraData).contains(renderType))
							l.addAll(r);
				}
		}

		return l;

	}

	@Override
	public ModelData getModelData(BlockAndTintGetter level, BlockPos pos, BlockState state, ModelData modelData) {
		MultiMaterialModelData data = new MultiMaterialModelData();

		return modelData.derive().with(DATA, data).build();
	}

}
