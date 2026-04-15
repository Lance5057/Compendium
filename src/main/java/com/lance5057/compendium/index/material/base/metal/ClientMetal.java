package com.lance5057.compendium.index.material.base.metal;

import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.util.TagUtil;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.neoforged.neoforge.client.model.RegistryAwareItemModelShaper;

public class ClientMetal {
	public static void doItems(RegistryAwareItemModelShaper shaper, _MaterialBase mb, MaterialMetal mm) {
		if (mm.INGOT.shouldGenerate())
			shaper.register(mm.INGOT.ITEM.asItem(), ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));

	}
}
