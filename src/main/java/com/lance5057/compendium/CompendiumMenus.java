package com.lance5057.compendium;

import java.util.function.Supplier;

import com.lance5057.compendium.gui.AdjustinatorMenu;
import com.lance5057.compendium.workstations.cosmetictoolbox.CosmeticToolboxMenu;
import com.lance5057.compendium.workstations.cosmetictoolbox.placed.CosmeticToolboxPlacedMenu;
import com.lance5057.compendium.workstations.workbench.WorkbenchMenu;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CompendiumMenus {
	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU,
			Compendium.MOD_ID);

	public static final Supplier<MenuType<WorkbenchMenu>> WORKBENCH_MENU = MENU_TYPES.register("workbench",
			() -> IMenuTypeExtension.create(WorkbenchMenu::new));

	public static final Supplier<MenuType<CosmeticToolboxMenu>> STYLE_MENU = MENU_TYPES.register("style",
			() -> IMenuTypeExtension.create(CosmeticToolboxMenu::new));

	public static final Supplier<MenuType<CosmeticToolboxPlacedMenu>> PLACED_STYLE_MENU = MENU_TYPES
			.register("style_placed", () -> IMenuTypeExtension.create(CosmeticToolboxPlacedMenu::new));

	public static final Supplier<MenuType<AdjustinatorMenu>> ADJUSTINATOR_MENU = MENU_TYPES.register("adjustinator",
			() -> IMenuTypeExtension.create(AdjustinatorMenu::new));

	public static void register(IEventBus modBus) {
		MENU_TYPES.register(modBus);
	}
}
