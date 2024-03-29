package lance5057.compendium;

import lance5057.compendium.core.workstations.craftinganvil.CraftingAnvilContainer;
import lance5057.compendium.core.workstations.craftinganvil.CraftingAnvilScreen;
import lance5057.compendium.core.workstations.workstation.WorkstationContainer;
import lance5057.compendium.core.workstations.workstation.WorkstationScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CompendiumContainers {
	public enum ContainerID {
		CRAFTING_ANVIL, WORKSTATION
	};

	public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES,
			Reference.MOD_ID);

	public static final RegistryObject<MenuType<CraftingAnvilContainer>> CRAFTING_ANVIL_CONTAINER = CONTAINERS
			.register("crafting_anvil_container", () -> IForgeMenuType.create((windowId, inv,
					data) -> new CraftingAnvilContainer(windowId, data.readBlockPos(), inv, inv.player)));

	public static final RegistryObject<MenuType<WorkstationContainer>> WORKSTATION_CONTAINER = CONTAINERS
			.register("workstation_container", () -> IForgeMenuType.create(
					(windowId, inv, data) -> new WorkstationContainer(windowId, data.readBlockPos(), inv, inv.player)));

	// public static ContainerType<CraftingAnvilContainer>
	// CRAFTING_ANVIL_CONTAINER_TYPE;

	public static void register(IEventBus modBus) {
		CONTAINERS.register(modBus);
	}

	public static void registerClient(FMLClientSetupEvent event) {
		MenuScreens.register(CRAFTING_ANVIL_CONTAINER.get(), CraftingAnvilScreen::new);
		MenuScreens.register(WORKSTATION_CONTAINER.get(), WorkstationScreen::new);
	}
}
