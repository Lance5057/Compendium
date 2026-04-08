package com.lance5057.compendium.client.models.style;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.lance5057.compendium.client.ClientUtil;
import com.lance5057.compendium.client.models.IndexEntryModelData;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.ChunkRenderTypeSet;
import net.neoforged.neoforge.client.model.IDynamicBakedModel;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelProperty;

public class StyleBakedModel implements IDynamicBakedModel {
	private static final ModelProperty<StyleModelData> DATA = new ModelProperty<>();
	private static final ModelProperty<IndexEntryModelData> INDEX_DATA = new ModelProperty<>();
	private BakedModel base;
	public final String baseName;
//	String current = "";
//	Map<String, BakedModel> models = new HashMap<String, BakedModel>();

	@SuppressWarnings("deprecation")
	public StyleBakedModel(BakedModel base, String baseName) {
		this.base = base;
		this.baseName = baseName;

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
		List<String> styles = extraData.get(StyleModelData.STYLES);
		String index = extraData.get(IndexEntryModelData.NAME);

		if (index != null) {
			if (styles != null && styles.size() > 0) {
				ResourceLocation rc = ClientUtil.createStyleBlockLocation(index + "_" + baseName, styles.get(0));

				BakedModel q = Minecraft.getInstance().getModelManager().getModel(new ModelResourceLocation(rc, ""));

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

	@Override
	public ModelData getModelData(BlockAndTintGetter level, BlockPos pos, BlockState state, ModelData modelData) {
		StyleModelData data = new StyleModelData();
		IndexEntryModelData index = new IndexEntryModelData();

		return modelData.derive().with(DATA, data).with(INDEX_DATA, index).build();
	}

}
