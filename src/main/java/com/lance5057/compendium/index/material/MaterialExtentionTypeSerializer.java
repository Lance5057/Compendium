package com.lance5057.compendium.index.material;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.lance5057.compendium.index.material.extentions._MaterialExtension;

public abstract class MaterialExtentionTypeSerializer<T extends _MaterialExtension>
		implements JsonSerializer<T>, JsonDeserializer<T> {

}
