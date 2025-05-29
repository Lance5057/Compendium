package com.lance5057.compendium.client.models.style;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.lance5057.compendium.styleblock.StyleType;

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

public class StyleBakedModel implements IDynamicBakedModel {
	private static final ModelProperty<StyleModelData> DATA = new ModelProperty<>();
	private final BakedModel missing;
	String current = "";
	Map<String, BakedModel> models = new HashMap<String, BakedModel>();

	@SuppressWarnings("deprecation")
	public StyleBakedModel(BakedModel base, Map<String, BakedModel> quads) {
		this.missing = base;
		this.models = quads;

	}

	@Override
	public boolean useAmbientOcclusion() {
		return missing.useAmbientOcclusion();
	}

	@Override
	public boolean isGui3d() {
		return missing.isGui3d();
	}

	@Override
	public boolean usesBlockLight() {
		return missing.usesBlockLight();
	}

	@Override
	public boolean isCustomRenderer() {
		return missing.isCustomRenderer();
	}

	@Override
	public TextureAtlasSprite getParticleIcon() {
		return missing.getParticleIcon();
	}

	@Override
	public ItemOverrides getOverrides() {
		return missing.getOverrides();
	}

	@Override
	public ItemTransforms getTransforms() {
		return missing.getTransforms();
	}

	@Override // FORGE: Get render types based on the selectors matched by the given block
				// state
	public ChunkRenderTypeSet getRenderTypes(@NotNull BlockState state, @NotNull RandomSource rand,
			@NotNull ModelData data) {

		return this.missing.getRenderTypes(state, rand, data);
	}

	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource rand,
			ModelData extraData, @Nullable RenderType renderType) {
		List<BakedQuad> l = new ArrayList<BakedQuad>();
		@Nullable
		StyleType mats = extraData.get(StyleModelData.STYLES);

		if (mats != null) {
			BakedModel q = models.get(mats.getCurrentStyle());
			if (q != null) {
				List<BakedQuad> r = q.getQuads(state, side, rand, extraData, renderType);
				if (r != null) {
					if (renderType == null || q.getRenderTypes(state, rand, extraData).contains(renderType))
						l.addAll(r);
				} else
					l.addAll(missing.getQuads(state, side, rand, extraData, renderType));
			} else
				l.addAll(missing.getQuads(state, side, rand, extraData, renderType));
		} else
			l.addAll(missing.getQuads(state, side, rand, extraData, renderType));

		return l;

	}

	@Override
	public ModelData getModelData(BlockAndTintGetter level, BlockPos pos, BlockState state, ModelData modelData) {
		StyleModelData data = new StyleModelData();
		
		
		
		return modelData.derive().with(DATA, data).build();
	}

}
