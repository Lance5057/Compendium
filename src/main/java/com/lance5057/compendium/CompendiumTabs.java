package com.lance5057.compendium;

import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.material.base._MaterialBase;

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
						CompendiumItems.ITEMS.getEntries().forEach(i -> output.accept(i.get()));
						CompendiumIndex.index.stream().sorted((i, o) -> {
							if (i instanceof _MaterialBase mb1) {
								if (o instanceof _MaterialBase mb2) {
									if (mb1.getType() == mb2.getType()) {
										return mb1.name.compareTo(mb2.name);
									} else if (mb1.getType().ordinal() > mb2.getType().ordinal())
										return 1;
									else
										return -1;
								} else
									return 1;
							} else if (o instanceof _MaterialBase)
								return -1;
							return 0;

						}).forEach(i -> i.tab(output));
//						CompendiumIndex.index.forEach(i -> i.tab(output));
					}).build());
}
