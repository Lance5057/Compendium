package com.lance5057.compendium.client.models;

import net.minecraft.client.renderer.block.model.BakedQuad;

public interface IIndexQuad {
	public BakedQuad getQuad(String materialName);
	
	public void addQuad(String materialName, BakedQuad quad);
}
