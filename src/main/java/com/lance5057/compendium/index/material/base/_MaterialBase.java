package com.lance5057.compendium.index.material.base;

import java.util.List;

import com.lance5057.compendium.index.IIndexEntry;
import com.lance5057.compendium.index.material.extentions._MaterialExtention;

public abstract class _MaterialBase implements IIndexEntry {
	public String type;
	public String name;
	
	public List<_MaterialExtention> extentions;

	public _MaterialBase(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof _MaterialBase m) {
			if (m.name.compareTo(this.name) == 0)
				return true;
		}
		return false;
	}
}
