package com.lance5057.compendium.index.material.extentions.extrametalblocks.client;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.lance5057.compendium.Compendium;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.CustomLoaderBuilder;
import net.neoforged.neoforge.client.model.generators.ModelBuilder;
import net.neoforged.neoforge.client.model.geometry.IGeometryLoader;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class MetalTileGeometryLoader implements IGeometryLoader<MetalTileUnbakedGeometry> {
    public static ResourceLocation ID = Compendium.modLoc("metal_tile");
    @Override
    public MetalTileUnbakedGeometry read(JsonObject jsonObject, JsonDeserializationContext deserializationContext) throws JsonParseException {
        return new MetalTileUnbakedGeometry();
    }

    public static <T extends ModelBuilder<T>> CustomLoaderBuilder<T> builder(T parent, ExistingFileHelper existingFileHelper) {
        return new CustomLoaderBuilder<T>(ID, parent, existingFileHelper, true) {
        };
    }
} 