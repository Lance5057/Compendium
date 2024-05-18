package com.lance5057.compendium.index.material;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.lance5057.compendium.index.material.base._MaterialBase;

public abstract class MaterialTypeSerializer<T extends _MaterialBase>
		implements JsonSerializer<T>, JsonDeserializer<T> {
}
