package com.lance5057.compendium.index.material;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.GsonBuilder;
import com.lance5057.compendium.index.material.base.MaterialGem;
import com.lance5057.compendium.index.material.base.MaterialGlass;
import com.lance5057.compendium.index.material.base.MaterialMetal;
import com.lance5057.compendium.index.material.base.MaterialTypeSerializer;
import com.lance5057.compendium.index.material.base.MaterialWood;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.extentions.ExtensionAdvancedTools;
import com.lance5057.compendium.index.material.extentions.ExtensionArmor;
import com.lance5057.compendium.index.material.extentions.ExtensionVanillaTools;
import com.lance5057.compendium.index.material.extentions.MaterialExtensionSerializer;
import com.lance5057.compendium.index.material.extentions._MaterialExtension;
import com.lance5057.compendium.index.material.extentions.metal.ExtensionExtraMetalBlocks;

public class MaterialTypeRegistry {
	private static Map<Class<?>, MaterialTypeSerializer<?>> serializers = new HashMap<>();

	private static Map<Class<?>, MaterialExtensionSerializer<?>> extensions = new HashMap<>();

	public static <T extends _MaterialBase> void registerType(Class<T> type, MaterialTypeSerializer<T> serializer) {
		serializers.put(type, serializer);
	}

	public static <T extends _MaterialExtension> void registerExtension(Class<T> type,
			MaterialExtensionSerializer<T> serializer) {
		extensions.put(type, serializer);
	}

	public static GsonBuilder setupGson() {
		GsonBuilder g = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping();
		for (Map.Entry<Class<?>, MaterialTypeSerializer<?>> entry : serializers.entrySet()) {
			g.registerTypeAdapter(entry.getKey(), entry.getValue());
		}
		for (Map.Entry<Class<?>, MaterialExtensionSerializer<?>> entry : extensions.entrySet()) {
			g.registerTypeAdapter(entry.getKey(), entry.getValue());
		}
		return g;
	}

	public static MaterialTypeSerializer<?> getTypeSerializer(String s) {
		return serializers.entrySet().stream().filter(e -> e.getValue().getType().equals(s)).map(Map.Entry::getValue)
				.findFirst().orElse(null);
	}

	public static MaterialExtensionSerializer<?> getExtensionSerializer(String s) {
		return extensions.entrySet().stream().filter(e -> e.getValue().getType().equals(s)).map(Map.Entry::getValue)
				.findFirst().orElse(null);
	}

	static {
		serializers.put(_MaterialBase.class, new _MaterialBase.Serializer());
		serializers.put(MaterialMetal.class, new MaterialMetal.Serializer());
		serializers.put(MaterialWood.class, new MaterialWood.Serializer());
		serializers.put(MaterialGem.class, new MaterialGem.Serializer());
		serializers.put(MaterialGlass.class, new MaterialGlass.Serializer());

		extensions.put(_MaterialExtension.class, new _MaterialExtension.Serializer());
		extensions.put(ExtensionVanillaTools.class, new ExtensionVanillaTools.Serializer());
		extensions.put(ExtensionAdvancedTools.class, new ExtensionAdvancedTools.Serializer());
		extensions.put(ExtensionArmor.class, new ExtensionArmor.Serializer());
		extensions.put(ExtensionExtraMetalBlocks.class, new ExtensionExtraMetalBlocks.Serializer());
	}
}
