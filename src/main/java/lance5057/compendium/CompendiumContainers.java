package lance5057.compendium;

import lance5057.compendium.core.workstations.containers.CraftingAnvilContainer;
import lance5057.compendium.core.workstations.screen.CraftingAnvilScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CompendiumContainers {
	public enum ContainerID {
		CRAFTING_ANVIL
	};

	public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS,
			Reference.MOD_ID);

	public static final RegistryObject<MenuType<CraftingAnvilContainer>> CRAFTING_ANVIL_CONTAINER = CONTAINERS
			.register("crafting_anvil_container", () -> IForgeMenuType.create(
					(windowId, inv, data) -> new CraftingAnvilContainer(windowId, data.readBlockPos(), inv, inv.player)));

	// public static ContainerType<CraftingAnvilContainer>
	// CRAFTING_ANVIL_CONTAINER_TYPE;

	public static void register(IEventBus modBus) {
		CONTAINERS.register(modBus);
	}

	public static void registerClient(FMLClientSetupEvent event) {
		MenuScreens.register(CRAFTING_ANVIL_CONTAINER.get(), CraftingAnvilScreen::new);
	}
}
