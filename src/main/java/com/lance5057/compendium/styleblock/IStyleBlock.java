package com.lance5057.compendium.styleblock;

import java.util.List;

public interface IStyleBlock {
	public int getNumOfMaterials();

	public List<String> getMaterialTypes();

	public List<MultiStyle> getStyles();
}
