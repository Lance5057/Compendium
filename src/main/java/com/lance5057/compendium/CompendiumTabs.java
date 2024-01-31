package com.lance5057.compendium;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CompendiumTabs {
	public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,
			Compendium.MOD_ID);

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = TABS.register("tab",
			() -> CreativeModeTab.builder().title(Component.translatable("itemGroup.compendium.tab"))
					.icon(() -> new ItemStack(CompendiumItems.SAWDUST.get())).displayItems((parameters, output) -> {
						output.accept(CompendiumItems.SAWDUST);
					}).build());
}
