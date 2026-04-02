package com.lance5057.compendium.index.material.base.wood;

import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.extensions.wood.ExtensionExtraLogs;
import com.lance5057.compendium.index.material.extensions.wood.ExtensionExtraPlanks;
import com.lance5057.compendium.util.TagUtil;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.neoforged.neoforge.client.model.RegistryAwareItemModelShaper;

public class ClientWood {
	public static void doItems(RegistryAwareItemModelShaper shaper, _MaterialBase mb, MaterialWood mw) {

		if (mw.LOG.shouldGenerate())
			shaper.register(mw.LOG.BLOCK_ITEM.asItem(), ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));

		mw.extensions.forEach(i -> {
			if (i instanceof ExtensionExtraPlanks eep) {
				if (eep.PLANK.shouldGenerate())
					shaper.register(eep.PLANK.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.PLANK_BLOCK.shouldGenerate())
					shaper.register(eep.PLANK_BLOCK.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.PLANK_SLAB.shouldGenerate())
					shaper.register(eep.PLANK_SLAB.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.PLANK_STAIRS.shouldGenerate())
					shaper.register(eep.PLANK_STAIRS.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
			}
			if (i instanceof ExtensionExtraLogs eep) {
				if (eep.LOG.shouldGenerate())
					shaper.register(eep.LOG.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.LOG_SLAB.shouldGenerate())
					shaper.register(eep.LOG_SLAB.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.LOG_STAIRS.shouldGenerate())
					shaper.register(eep.LOG_STAIRS.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.SMALL_LOG.shouldGenerate())
					shaper.register(eep.SMALL_LOG.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.STRIPPED_LOG.shouldGenerate())
					shaper.register(eep.STRIPPED_LOG.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.STRIPPED_LOG_SLAB.shouldGenerate())
					shaper.register(eep.STRIPPED_LOG_SLAB.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.STRIPPED_LOG_STAIRS.shouldGenerate())
					shaper.register(eep.STRIPPED_LOG_STAIRS.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.STRIPPED_SMALL_LOG.shouldGenerate())
					shaper.register(eep.STRIPPED_SMALL_LOG.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
			}
		});
	}
}
