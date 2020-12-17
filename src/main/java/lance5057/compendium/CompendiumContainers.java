package lance5057.compendium;

import lance5057.compendium.core.workstations.containers.CraftingAnvilContainer;
import lance5057.compendium.core.workstations.screen.CraftingAnvilScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CompendiumContainers {
	public enum ContainerID {
		CRAFTING_ANVIL
	};

	public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Reference.MOD_ID);

	public static final RegistryObject<ContainerType<CraftingAnvilContainer>> CRAFTING_ANVIL_CONTAINER = CONTAINERS.register("crafting_anvil_container", () -> IForgeContainerType.create(CraftingAnvilContainer::createContainerClientSide));

	// public static ContainerType<CraftingAnvilContainer>
	// CRAFTING_ANVIL_CONTAINER_TYPE;

	public static void register(IEventBus modBus) {
		CONTAINERS.register(modBus);
	}

	public static void registerClient(FMLClientSetupEvent event) {
		ScreenManager.registerFactory(CRAFTING_ANVIL_CONTAINER.get(), CraftingAnvilScreen::new);
	}
}
