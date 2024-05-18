package com.lance5057.compendium.index.material;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.GsonBuilder;
import com.lance5057.compendium.index.material.base.MaterialGem;
import com.lance5057.compendium.index.material.base.MaterialMetal;
import com.lance5057.compendium.index.material.base.MaterialWood;
import com.lance5057.compendium.index.material.base._MaterialBase;

public class MaterialTypeRegistry {
	private static Map<Class<?>, MaterialTypeSerializer<?>> serializers = new HashMap<>();

	public static <T extends _MaterialBase> void register(Class<T> type, MaterialTypeSerializer<T> serializer) {
		serializers.put(type, serializer);
	}

	public static GsonBuilder setupGson() {
		GsonBuilder g = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping();
		for (Map.Entry<Class<?>, MaterialTypeSerializer<?>> entry : serializers.entrySet()) {
			g.registerTypeAdapter(entry.getKey(), entry.getValue());
		}
		return g;
	}

	static {
		serializers.put(MaterialMetal.class, new MaterialMetal.Serializer());
		serializers.put(MaterialWood.class, new MaterialWood.Serializer());
		serializers.put(MaterialGem.class, new MaterialGem.Serializer());
	}
}
