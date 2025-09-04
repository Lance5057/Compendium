package com.lance5057.compendium;

import java.util.List;

import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.style.StyleRemover;
import com.mojang.serialization.Codec;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.datamaps.AdvancedDataMapType;
import net.neoforged.neoforge.registries.datamaps.DataMapValueMerger;

public class CompendiumStyles {
	public static final AdvancedDataMapType<Block, List<StyleData>, StyleRemover> STYLE_DATA = AdvancedDataMapType
			.builder(Compendium.modLoc("style_data"), Registries.BLOCK, Codec.list(StyleData.CODEC))
			.synced(Codec.list(StyleData.CODEC), true).merger(DataMapValueMerger.listMerger())
			.remover(StyleRemover.CODEC).build();
}
