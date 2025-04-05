package com.lance5057.compendium.index.material.extensions;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

public abstract class MaterialExtensionSerializer<T extends _MaterialExtension>
		implements JsonSerializer<T>, JsonDeserializer<T> {
	public final String type;

	public MaterialExtensionSerializer(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
