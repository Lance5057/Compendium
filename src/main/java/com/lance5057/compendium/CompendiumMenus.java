package com.lance5057.compendium;

import java.util.function.Supplier;

import com.lance5057.compendium.blocks.RecipeToolSupplier.drawer.ComponentDrawerMenu;
import com.lance5057.compendium.gui.AdjustinatorMultiMaterialMenu;
import com.lance5057.compendium.gui.AdjustinatorWorkstationMenu;
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

	public static final Supplier<MenuType<AdjustinatorWorkstationMenu>> ADJUSTINATOR_WORKSTATION_MENU = MENU_TYPES
			.register("adjustinator_workstation", () -> IMenuTypeExtension.create(AdjustinatorWorkstationMenu::new));

	public static final Supplier<MenuType<AdjustinatorMultiMaterialMenu>> ADJUSTINATOR_MULTIMATERIAL_MENU = MENU_TYPES
			.register("adjustinator_multimaterial",
					() -> IMenuTypeExtension.create(AdjustinatorMultiMaterialMenu::new));

	public static final Supplier<MenuType<ComponentDrawerMenu>> COMPONENT_DRAWER_MENU = MENU_TYPES
			.register("component_drawer", () -> IMenuTypeExtension.create(ComponentDrawerMenu::new));

	public static void register(IEventBus modBus) {
		MENU_TYPES.register(modBus);
	}
}
