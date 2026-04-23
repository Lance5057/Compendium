package com.lance5057.compendium.index.material.base.gem;

import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.util.CompendiumBlockHandler;
import com.lance5057.compendium.index.util.CompendiumItemHandler;
import com.lance5057.compendium.util.TagUtil;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.neoforged.neoforge.client.event.ModelEvent.ModifyBakingResult;
import net.neoforged.neoforge.client.model.RegistryAwareItemModelShaper;

public class ClientGem {

	public static void doItems(RegistryAwareItemModelShaper shaper, MaterialGem mm) {
		for (CompendiumItemHandler i : mm.ITEMS) {
			if (i.shouldGenerate())
				shaper.register(i.ITEM.asItem(), ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
		}

		for (CompendiumBlockHandler i : mm.BLOCKS) {
			if (i.shouldGenerate())
				shaper.register(i.BLOCK_ITEM.asItem(), ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
		}

		for (_MaterialExtension me : mm.extensions) {
			for (CompendiumItemHandler i : me.ITEMS) {
				if (i.shouldGenerate())
					shaper.register(i.ITEM.asItem(), ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
			}

			for (CompendiumBlockHandler i : me.BLOCKS) {
				if (i.shouldGenerate())
					shaper.register(i.BLOCK_ITEM.asItem(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
			}
		}
	}

	public static void doGem(ModifyBakingResult event, MaterialGem mw) {
		// TODO Auto-generated method stub

	}

}
