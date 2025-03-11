package com.lance5057.compendium.client.models;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.index.IIndexEntry;
import com.lance5057.compendium.index.material.base._MaterialBase;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.IDynamicBakedModel;
import net.neoforged.neoforge.client.model.data.ModelData;

public class MaterialSwapElementsBakedModel implements IDynamicBakedModel {

	private final BakedModel base;
	@Nullable
	private List<IIndexQuad> quads;

	@SuppressWarnings("deprecation")
	public MaterialSwapElementsBakedModel(BakedModel base, MATERIAL_TYPES materialType) {
		this.base = base;

		// Lets get stupid!
		List<BakedQuad> q = base.getQuads(null, null, RandomSource.create());

		for (IIndexEntry i : CompendiumIndex.index) {
			if (i instanceof _MaterialBase mb) {
				if (mb.getType() == materialType) {
					BasicIndexQuad biq = new BasicIndexQuad();
					for (BakedQuad quad : q) {

						// grab the sprite and change it!
						ResourceLocation atlas = quad.getSprite().atlasLocation();
						ResourceLocation sprite = quad.getSprite().contents().name();

						TextureAtlasSprite tas = Minecraft.getInstance().getTextureAtlas(atlas).apply(sprite);

						BakedQuad bq = new BakedQuad(quad.getVertices(), quad.getTintIndex(), quad.getDirection(), tas,
								quad.isShade());

					}
				}
			}
		}
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

	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource rand,
			ModelData extraData, @Nullable RenderType renderType) {
		List<BakedQuad> q = new ArrayList<BakedQuad>();
//		for (int i = 0; i < quads.size(); i++)
//			q.add(quads.get(i).getQuad(null));

		return q;
	}

}
