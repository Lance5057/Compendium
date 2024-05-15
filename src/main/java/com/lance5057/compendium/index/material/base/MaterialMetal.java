package com.lance5057.compendium.index.material.base;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class MaterialMetal extends _MaterialBase {
	public boolean loadIngot;
	public boolean loadStorageBlock;
	public boolean loadNugget;

	public MaterialMetal(String name) {
		this(name, true, true, true);
	}

	public MaterialMetal(String name, boolean ingot, boolean block, boolean nugget) {
		super(name);
		loadIngot = ingot;
		loadStorageBlock = block;
		loadNugget = nugget;
		
		this.type = Serializer.CLASS_TYPE;
	}

	public class Serializer implements JsonSerializer<MaterialMetal>, JsonDeserializer<MaterialMetal> {

		public static final String CLASS_TYPE = "METAL";

		@Override
		public MaterialMetal deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject jsonObj = json.getAsJsonObject();
			String className = jsonObj.get(CLASS_TYPE).getAsString();

			try {
				Class<?> clz = Class.forName(className);
				return context.deserialize(json, clz);
			} catch (ClassNotFoundException e) {
				throw new JsonParseException(e);
			}
		}

		@Override
		public JsonElement serialize(MaterialMetal src, Type typeOfSrc, JsonSerializationContext context) {
			Gson gson = new Gson();
			gson.toJson(src, src.getClass());
			JsonElement jsonElement = gson.toJsonTree(src);
			jsonElement.getAsJsonObject().addProperty(CLASS_TYPE, src.getClass().getCanonicalName());
			return jsonElement;
		}

	}
}
