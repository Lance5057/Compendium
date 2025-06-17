package com.lance5057.compendium.client.models.multistylematerial.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.jetbrains.annotations.Nullable;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.client.models.style.model.StyleModel;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext;

public class MultiStyleMaterialModel {
	public static class Unbaked {
		Map<String, ResourceLocation> multistyles = new HashMap<String, ResourceLocation>();
		Map<String, StyleModel> models = new HashMap<String, StyleModel>();

		public Unbaked(String name, String type, String model, List<String> styles) {
			styles.forEach(s -> multistyles.put(s, ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID,
					"block/material/" + type + "/" + name + "/" + model)));
		}

		public void resolveParents(Function<ResourceLocation, UnbakedModel> modelGetter,
				IGeometryBakingContext context) {

		}

		public Baked bake(IGeometryBakingContext context, ModelBaker baker,
				Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides) {
			Map<String, BakedModel> bakedModels = new HashMap<String, BakedModel>();
			models.forEach((k, v) -> {
				BakedModel baked = v.model.bake(baker, spriteGetter, modelState);

				bakedModels.put(k, baked);
			});
			return new Baked(bakedModels);
		}

		public static Unbaked read(JsonObject jsonObject, JsonDeserializationContext deserializationContext)
				throws JsonParseException {
			List<StyleModel> models = new ArrayList<StyleModel>();

			jsonObject.entrySet().forEach(i -> {
				if (i.getValue().isJsonObject()) {
					JsonObject jo = i.getValue().getAsJsonObject();
					if (jo.has("model")) {
						models.add(new StyleModel(i.getKey(), ResourceLocation.parse(jo.get("model").getAsString())));
					}
				}
			});

			return new Unbaked(null, null, null, null);
		}
	}

	public static class Baked {
		public final Map<String, BakedModel> models;

		public Baked(Map<String, BakedModel> models) {
			this.models = models;
		}

		public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource rand,
				ModelData extraData, @Nullable RenderType renderType, String styleName) {
			List<BakedQuad> l = new ArrayList<BakedQuad>();

			for (int i = 0; i < models.size(); i++)
				l.addAll(models.getOrDefault(styleName, null).getQuads(state, side, rand, extraData, renderType));

			return l;
		}
	}
}
