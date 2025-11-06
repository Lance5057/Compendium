package com.lance5057.compendium.styleblock;

import java.util.List;

import net.minecraft.resources.ResourceLocation;

public interface IStyleBlock {
	public List<String> getStyles(List<Integer> current);
	public ResourceLocation getBlockModelLocation();

}
