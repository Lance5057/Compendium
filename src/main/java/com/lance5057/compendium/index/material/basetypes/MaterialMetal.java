package com.lance5057.compendium.index.material.basetypes;

public class MaterialMetal {
	public boolean loadIngot;
	public boolean loadStorageBlock;
	public boolean loadNugget;
	
	String stringTest;
	
	public MaterialMetal()
	{
		loadIngot = true;
		loadStorageBlock = true;
		loadNugget = true;
		
		stringTest = "test";
	}
	
	public MaterialMetal(boolean ingot, boolean block, boolean nugget)
	{
		loadIngot = ingot;
		loadStorageBlock = block;
		loadNugget = nugget;
	}
}
