package com.lance5057.compendium.index.json;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.lance5057.compendium.index.material.base.MaterialGem;
import com.lance5057.compendium.index.material.base.MaterialMetal;
import com.lance5057.compendium.index.material.base.MaterialWood;
import com.lance5057.compendium.index.material.base._MaterialBase;

public class MaterialTypeAdapter extends TypeAdapter<_MaterialBase> {
	private TypeAdapter<JsonElement> jsonElementAdapter;
	private TypeAdapter<_MaterialBase> baseAdapter;
	private TypeAdapter<MaterialMetal> metalAdapter;
	private TypeAdapter<MaterialWood> woodAdapter;
	private TypeAdapter<MaterialGem> gemAdapter;

	public MaterialTypeAdapter(MaterialTypeAdapterFactory oldFactory, Gson gson) {
		this.jsonElementAdapter = gson.getAdapter(JsonElement.class);
		this.baseAdapter = gson.getDelegateAdapter(oldFactory, TypeToken.get(_MaterialBase.class));
		this.metalAdapter = gson.getDelegateAdapter(oldFactory, TypeToken.get(MaterialMetal.class));
		this.woodAdapter = gson.getDelegateAdapter(oldFactory, TypeToken.get(MaterialWood.class));
		this.gemAdapter = gson.getDelegateAdapter(oldFactory, TypeToken.get(MaterialGem.class));
	}

	@Override
	public void write(JsonWriter out, _MaterialBase value) throws IOException {
		if (value != null) {
			if (value instanceof MaterialMetal) {
				metalAdapter.write(out, (MaterialMetal) value);
			} else if (value instanceof MaterialWood) {
				woodAdapter.write(out, (MaterialWood) value);
			} else if (value instanceof MaterialGem) {
				gemAdapter.write(out, (MaterialGem) value);
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

		} else if (type.equals(MaterialWood.Serializer.CLASS_TYPE)) {
			return woodAdapter.fromJsonTree(objectJson);

		} else if (type.equals(MaterialGem.Serializer.CLASS_TYPE)) {
			return gemAdapter.fromJsonTree(objectJson);

		} else {
			System.err.println("Type not found");
		}

		return null;
	}

}
