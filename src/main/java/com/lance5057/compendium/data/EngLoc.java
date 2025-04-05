package com.lance5057.compendium.data;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumItems;
import com.lance5057.compendium.index.CompendiumIndex;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class EngLoc extends LanguageProvider {

	public EngLoc(PackOutput gen) {
		super(gen, Compendium.MOD_ID, "en_us");

	}

	@Override
	protected void addTranslations() {
		this.add("itemGroup.compendium.tab", "Compendium");
		this.add(CompendiumItems.ADJUSTINATOR.get(), "Adjustinator");
		this.add(CompendiumItems.HAMMERING_STATION.get(), "Hammering Station");
		this.add(CompendiumItems.SAW_BUCK.get(), "Sawbuck");
		this.add(CompendiumItems.SCRAPPING_TABLE.get(), "Scrapping Table");
		this.add(CompendiumItems.WORKBENCH.get(), "Workbench");
		this.add(CompendiumItems.TOOLRACK.get(), "Tool Rack");
		this.add(CompendiumItems.COMPONENT_DRAWER.get(), "Component Drawer");
		this.add(CompendiumItems.COSMETIC_TOOLBOX.get(), "Cosmetic Toolbox");
		this.add(Compendium.MOD_ID + ".tooltip.toolbox", "Right click for style menu. Shift + Right click to place");

		CompendiumIndex.index.forEach(i -> {
			i.engLoc(this);
		});
	}

}
