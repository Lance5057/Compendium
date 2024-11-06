package com.lance5057.compendium.index.material.base;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

public abstract class MaterialTypeSerializer<T extends _MaterialBase>
		implements JsonSerializer<T>, JsonDeserializer<T> {
	public final String type;

	public MaterialTypeSerializer(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
