package com.lance5057.compendium.client.models;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.ChunkRenderTypeSet;
import net.neoforged.neoforge.client.RenderTypeGroup;
import net.neoforged.neoforge.client.model.IDynamicBakedModel;
import net.neoforged.neoforge.client.model.data.ModelData;

public class MaterialSwapElementsBakedModel implements IDynamicBakedModel {

	@Nullable
	private final List<IIndexQuad> quads;
	private final TextureAtlasSprite particle;
	private final ItemOverrides overrides;
	private final ItemTransforms transforms;
	@Nullable
	private final ChunkRenderTypeSet blockRenderTypes;
	@Nullable
	private final List<RenderType> itemRenderTypes;
	@Nullable
	private final List<RenderType> fabulousItemRenderTypes;

	public MaterialSwapElementsBakedModel(EnumSet<Direction> enabledFaces, boolean renderOnDisabledFaces,
			List<Block> connectableBlocks, @Nullable List<IIndexQuad> quads, TextureAtlasSprite particle,
			ItemOverrides overrides, ItemTransforms transforms, RenderTypeGroup group) {
		this.quads = quads;
		this.particle = particle;
		this.overrides = overrides;
		this.transforms = transforms;
		this.blockRenderTypes = !group.isEmpty() ? ChunkRenderTypeSet.of(group.block()) : null;
		this.itemRenderTypes = !group.isEmpty() ? List.of(group.entity()) : null;
		this.fabulousItemRenderTypes = !group.isEmpty() ? List.of(group.entityFabulous()) : null;
	}

	@Override
	public boolean useAmbientOcclusion() {
		return true;
	}

	@Override
	public boolean isGui3d() {
		return true;
	}

	@Override
	public boolean usesBlockLight() {
		return true;
	}

	@Override
	public boolean isCustomRenderer() {
		return false;
	}

	@Override
	public TextureAtlasSprite getParticleIcon() {
		return particle;
	}

	@Override
	public ItemOverrides getOverrides() {
		return overrides;
	}

	@Override
	public ItemTransforms getTransforms() {
		return this.transforms;
	}

	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource rand,
			ModelData extraData, @Nullable RenderType renderType) {
		List<BakedQuad> q = new ArrayList<BakedQuad>();
		for (int i = 0; i < quads.size(); i++)
			q.add(quads.get(i).getQuad(null));

		return q;
	}

}
