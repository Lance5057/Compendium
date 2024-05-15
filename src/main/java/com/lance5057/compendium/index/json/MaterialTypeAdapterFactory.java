package com.lance5057.compendium.index.json;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.lance5057.compendium.index.material.base._MaterialBase;

public class MaterialTypeAdapterFactory implements TypeAdapterFactory {

	@Override
	@SuppressWarnings("unchecked")
	public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
		if (!_MaterialBase.class.isAssignableFrom(type.getRawType())) {
			return null;
		}
		return (TypeAdapter<T>) new MaterialTypeAdapter(this, gson).nullSafe();
	}
}