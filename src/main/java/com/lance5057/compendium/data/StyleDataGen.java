package com.lance5057.compendium.data;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.lance5057.compendium.CompendiumBlocks;
import com.lance5057.compendium.CompendiumStyles;
import com.lance5057.compendium.style.StyleLayer;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;

public class StyleDataGen extends DataMapProvider {

	//Defaults
	public static List<StyleLayer> CLOTHED_TABLE = List.of(new StyleLayer("top", List.of("basic", "trimmed", "smooth")),
			new StyleLayer("legs", List.of("basic", "bar", "fancy")),
			new StyleLayer("cloth", List.of("basic", "long", "short", "angled", "angled_short", "angled_long")));

	public static List<StyleLayer> TABLE = List.of(new StyleLayer("top", List.of("basic", "trimmed", "smooth")),
			new StyleLayer("legs", List.of("basic", "bar", "fancy")));

	protected StyleDataGen(PackOutput packOutput, CompletableFuture<Provider> lookupProvider) {
		super(packOutput, lookupProvider);
	}

	@Override
	protected void gather() {

		this.builder(CompendiumStyles.STYLE_DATA).add(CompendiumBlocks.CLOTHED_TABLE, CLOTHED_TABLE, false).build();
		this.builder(CompendiumStyles.STYLE_DATA).add(CompendiumBlocks.TABLE, TABLE, false).build();
	}

}
