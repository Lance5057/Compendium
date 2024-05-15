package com.lance5057.compendium.index.material.base;

import java.util.List;

import com.lance5057.compendium.index.material.extentions._MaterialExtention;

public abstract class _MaterialBase {
	public String type;
	public String name;
	public List<_MaterialExtention> extentions;

	public _MaterialBase(String name) {
		this.name = name;
	}
}
