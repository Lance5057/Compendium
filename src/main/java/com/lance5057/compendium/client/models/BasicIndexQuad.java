package com.lance5057.compendium.client.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.core.Direction;

//Basic implementation, only holds 1 material type
public class BasicIndexQuad {
	public final String material;
	public Map<Direction, List<BakedQuad>> quads = new HashMap<Direction, List<BakedQuad>>();

	public BasicIndexQuad(String m) {
		this.material = m;

	}

}
