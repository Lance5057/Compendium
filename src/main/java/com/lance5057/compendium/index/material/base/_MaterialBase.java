package com.lance5057.compendium.index.material.base;

import java.util.List;

import com.lance5057.compendium.index.IIndexEntry;
import com.lance5057.compendium.index.material.extentions._MaterialExtension;

public abstract class _MaterialBase implements IIndexEntry {
	public final String TYPE;
	public String name;

	public List<_MaterialExtension> extensions;

	public _MaterialBase(String name, String type) {
		this.TYPE = type;
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

	public _MaterialBase addExtension(_MaterialExtension x) {
		extensions.add(x);
		return this;
	}
}
