package com.lance5057.compendium.client.models.multistylematerial;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.lance5057.compendium.client.ClientUtil;
import com.lance5057.compendium.client.models.multimaterial.MultiMaterialModelData;
import com.lance5057.compendium.client.models.style.StyleModelData;
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
import net.minecraft.world.level.block.state.properties.Property;
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

	public static class BakedLayer {
		public final String layerName;
		public final int materialLayer;
		public final int styleLayer;
		public final String baseName;

		public BakedLayer(String baseName, String layerName, int materialLayer, int styleLayer) {
			this.baseName = baseName;
			this.layerName = layerName;
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

					ResourceLocation rc = ClientUtil.createMaterialStyleLayerLocation(baseName, layerName, m, st);

//					Map<Property<?>, Comparable<?>> p = state.getValues();
					String variant = "";

//					for (Entry<Property<?>, Comparable<?>> x : p.entrySet()) {
//						variant += x.getKey().getName() + "=" + getName(x.getKey(), x.getValue()) + ",";
//					}
//					if (variant.length() > 0 && variant.charAt(variant.length()-1) == ',')
//						variant = variant.substring(0, variant.length() - 1);
					BakedModel t = Minecraft.getInstance().getModelManager()
							.getModel(new ModelResourceLocation(rc, variant));

					if (t != null) {
						List<BakedQuad> r = t.getQuads(state, side, rand, extraData, renderType);

						if (r != null && !r.isEmpty()) {
							if (renderType == null || t.getRenderTypes(state, rand, extraData).contains(renderType))
								l.addAll(r);
						}

					}
				}

			return l;

		}

	}

	private static <T extends Comparable<T>> String getName(Property<T> property, Comparable<?> value) {
		return property.getName((T) value);
	}

}
