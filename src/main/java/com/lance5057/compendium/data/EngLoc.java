package com.lance5057.compendium.data;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumItems;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.style.StyleLoc;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class EngLoc extends LanguageProvider {

	public EngLoc(PackOutput gen) {
		super(gen, Compendium.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		this.add("itemGroup.compendium.tab", "Compendium");
		this.add("compendium.jei.hammering_station", "Hammering");
		this.add("compendium.jei.sawbuck", "Sawing");
		this.add("compendium.jei.workbench", "Advanced Crafting");
		this.add(CompendiumItems.SAWDUST.get(), "Sawdust");
		this.add(CompendiumItems.ADJUSTINATOR.get(), "Adjustinator");
		this.add(CompendiumItems.MEGALITH_STONE.get(), "Megalith Stone");
		this.add(CompendiumItems.HAMMERING_STATION.get(), "Hammering Station");
		this.add(CompendiumItems.SAW_BUCK.get(), "Sawbuck");
		this.add(CompendiumItems.SCRAPPING_TABLE.get(), "Scrapping Table");
		this.add(CompendiumItems.WORKBENCH.get(), "Workbench");
		this.add(CompendiumItems.TOOLRACK.get(), "Tool Rack");
		this.add(CompendiumItems.COMPONENT_DRAWER.get(), "Component Drawer");
		this.add(CompendiumItems.COSMETIC_TOOLBOX.get(), "Cosmetic Toolbox");
		this.add(Compendium.MOD_ID + ".tooltip.toolbox", "Right click for style menu. Shift + Right click to place");
		this.add(CompendiumItems.CHAIR.get(), "Chair");
		this.add(CompendiumItems.TABLE.get(), "Table");
		this.add(CompendiumItems.CLOTHED_TABLE.get(), "Table with Cloth");
		this.add(CompendiumItems.FANCY_BED.get(), "Fancy Bed");
		this.add(CompendiumItems.WINDOW.get(), "Window");
		this.add(CompendiumItems.FANCY_FENCE.get(), "Fancy Fence");
		this.add(CompendiumItems.SHINGLES_SLANTED.get(), "Slanted Shingles");
		this.add(CompendiumItems.SHINGLES_CAP_SLANTED.get(), "Slanted Shingles Cap");

		CompendiumIndex.index.forEach(i -> {
			i.engLoc(this);
		});
		StyleLoc.StyleNames(this);
	}

}
