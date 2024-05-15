package com.lance5057.compendium.index.json;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.lance5057.compendium.index.material.base.MaterialMetal;
import com.lance5057.compendium.index.material.base._MaterialBase;

public class MaterialTypeAdapter extends TypeAdapter<_MaterialBase> {
	private TypeAdapter<JsonElement> jsonElementAdapter;
	private TypeAdapter<_MaterialBase> baseAdapter;
	private TypeAdapter<MaterialMetal> metalAdapter;

	public MaterialTypeAdapter(MaterialTypeAdapterFactory oldFactory, Gson gson) {
		this.jsonElementAdapter = gson.getAdapter(JsonElement.class);
		this.baseAdapter = gson.getDelegateAdapter(oldFactory, TypeToken.get(_MaterialBase.class));
		this.metalAdapter = gson.getDelegateAdapter(oldFactory, TypeToken.get(MaterialMetal.class));
	}

	@Override
	public void write(JsonWriter out, _MaterialBase value) throws IOException {
		if (value != null) {
			if (value instanceof MaterialMetal) {
				metalAdapter.write(out, (MaterialMetal) value);
			}
		}
	}

	@Override
	public _MaterialBase read(JsonReader in) throws IOException {
		JsonObject objectJson = jsonElementAdapter.read(in).getAsJsonObject();
		JsonElement jsonElement = objectJson.get("type");
		if (jsonElement == null) {
			return null;
		}

		String type = jsonElement.toString().replace("\"", "");
		if (type.equals(MaterialMetal.Serializer.CLASS_TYPE)) {
			return metalAdapter.fromJsonTree(objectJson);

		} else {
			System.err.println("Type not found");
		}

		return null;
	}

}
