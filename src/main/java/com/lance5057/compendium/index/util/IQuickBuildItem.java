package com.lance5057.compendium.index.util;

import com.lance5057.compendium.index.material.base._MaterialBase;

import net.minecraft.world.item.CreativeModeTab.Output;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;

public interface IQuickBuildItem {
	public boolean enabled();
	public void setEnabled(boolean b);
	
	public void setup(_MaterialBase base);

	public void tab(_MaterialBase base, Output output);

	public void blockModel(_MaterialBase base, BlockStateProvider bsp);

	public void itemModel(_MaterialBase base, ItemModelProvider tmp);

	public void engLoc(_MaterialBase base, LanguageProvider lp);
}
