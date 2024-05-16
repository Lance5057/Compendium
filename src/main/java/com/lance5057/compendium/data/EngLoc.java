package com.lance5057.compendium.data;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.index.CompendiumIndex;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class EngLoc extends LanguageProvider {

	public EngLoc(PackOutput gen) {
		super(gen, Compendium.MOD_ID, "en_us");

	}

	@Override
	protected void addTranslations() {
		CompendiumIndex.index.forEach(i -> {
			i.engLoc(this);
		});
	}

}
