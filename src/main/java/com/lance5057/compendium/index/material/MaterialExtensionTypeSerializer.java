package com.lance5057.compendium.index.material;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;

public abstract class MaterialExtensionTypeSerializer<T extends _MaterialExtension>
		implements JsonSerializer<T>, JsonDeserializer<T> {

}
