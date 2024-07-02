package com.lance5057.compendium;

import java.util.function.Supplier;

import com.lance5057.compendium.workstations.workstation.WorkbenchMenu;
import com.lance5057.compendium.workstations.workstation.WorkbenchScreen;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CompendiumMenus {
	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU,
			Compendium.MOD_ID);

	public static final Supplier<MenuType<WorkbenchMenu>> WORKBENCH_MENU = MENU_TYPES.register("workbench",
			() -> IMenuTypeExtension.create(WorkbenchMenu::new));
	
	public static void registerClient(FMLClientSetupEvent event) {
		MenuScreens.register(WORKBENCH_MENU.get(), WorkbenchScreen::new);
	}
}
