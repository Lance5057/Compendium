package com.lance5057.compendium.index.material;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.GsonBuilder;
import com.lance5057.compendium.index.material.base.MaterialGlass;
import com.lance5057.compendium.index.material.base.MaterialTypeSerializer;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.base.metal.MaterialMetal;
import com.lance5057.compendium.index.material.base.textile.ExistsLocationsTextile;
import com.lance5057.compendium.index.material.base.textile.MaterialTextile;
import com.lance5057.compendium.index.material.base.textile.SpecialLocationsTextile;
import com.lance5057.compendium.index.material.base.textile.SpecialTextureLocationsTextile;
import com.lance5057.compendium.index.material.base.wood.MaterialWood;
import com.lance5057.compendium.index.material.base.wood.locations.ExistsLocationsWood;
import com.lance5057.compendium.index.material.base.wood.locations.SpecialLocationsWood;
import com.lance5057.compendium.index.material.base.wood.locations.SpecialTextureLocationsWood;
import com.lance5057.compendium.index.material.extensions.ExtensionAdvancedTools;
import com.lance5057.compendium.index.material.extensions.ExtensionVanillaTools;
import com.lance5057.compendium.index.material.extensions.MaterialExtensionSerializer;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.material.extensions.wood.ExtensionExtraLogs;
import com.lance5057.compendium.index.material.extensions.wood.ExtensionExtraPlanks;

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

		g.registerTypeAdapter(SpecialLocationsWood.class, new SpecialLocationsWood.Serializer());
		g.registerTypeAdapter(ExistsLocationsWood.class, new ExistsLocationsWood.Serializer());
		g.registerTypeAdapter(SpecialTextureLocationsWood.class, new SpecialTextureLocationsWood.Serializer());

		g.registerTypeAdapter(SpecialLocationsTextile.class, new SpecialLocationsTextile.Serializer());
		g.registerTypeAdapter(ExistsLocationsTextile.class, new ExistsLocationsTextile.Serializer());
		g.registerTypeAdapter(SpecialTextureLocationsTextile.class, new SpecialTextureLocationsTextile.Serializer());
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
//		serializers.put(MaterialGem.class, new MaterialGem.Serializer());
		serializers.put(MaterialGlass.class, new MaterialGlass.Serializer());
//		serializers.put(MaterialStone.class, new MaterialStone.Serializer());
		serializers.put(MaterialTextile.class, new MaterialTextile.Serializer());

		extensions.put(_MaterialExtension.class, new _MaterialExtension.Serializer());
		extensions.put(ExtensionVanillaTools.class, new ExtensionVanillaTools.Serializer());
		extensions.put(ExtensionAdvancedTools.class, new ExtensionAdvancedTools.Serializer());
//		extensions.put(ExtensionArmor.class, new ExtensionArmor.Serializer());
//		extensions.put(ExtensionMetalStyleBlocks.class, new ExtensionMetalStyleBlocks.Serializer());
//		extensions.put(ExtensionStoneStyleBlocks.class, new ExtensionStoneStyleBlocks.Serializer());
		extensions.put(ExtensionExtraLogs.class, new ExtensionExtraLogs.Serializer());
		extensions.put(ExtensionExtraPlanks.class, new ExtensionExtraPlanks.Serializer());
	}
}
