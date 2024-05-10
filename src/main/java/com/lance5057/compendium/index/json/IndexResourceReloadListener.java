package com.lance5057.compendium.index.json;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

public class IndexResourceReloadListener extends SimpleJsonResourceReloadListener {

	public IndexResourceReloadListener(Gson pGson, String pDirectory) {
		super(pGson, pDirectory);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void apply(Map<ResourceLocation, JsonElement> pObject, ResourceManager pResourceManager,
			ProfilerFiller pProfiler) {
		// TODO Auto-generated method stub
		
	}

}
