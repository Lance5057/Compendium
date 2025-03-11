package com.lance5057.compendium.client.models;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.block.model.BakedQuad;

//Basic implementation, only holds 1 material type
public class BasicIndexQuad implements IIndexQuad {
	public List<BakedQuad> quads = new ArrayList<BakedQuad>();

}
