package com.lance5057.compendium.client.models;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.renderer.block.model.BakedQuad;

//Basic implementation, only holds 1 material type
public class BasicIndexQuad implements IIndexQuad{
	public Map<String, BakedQuad> quads = new HashMap<String, BakedQuad>();
	
	@Override
	public BakedQuad getQuad(String materialName) {
		return quads.getOrDefault(materialName, null);
	}

	@Override
	public void addQuad(String materialName, BakedQuad quad) {
		quads.put(materialName, quad);
	}

}
