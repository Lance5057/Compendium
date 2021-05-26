package lance5057.compendium;

import java.util.function.Supplier;

import lance5057.compendium.core.entities.GrenadeEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CompendiumEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES,
	    Reference.MOD_ID);

    public static final RegistryObject<EntityType<GrenadeEntity>> GRENADE_ENTITY = register("grenade",
	    CompendiumEntities::grenade);

    private static <E extends Entity> RegistryObject<EntityType<E>> register(final String name,
	    final Supplier<EntityType.Builder<E>> sup) {
	return ENTITIES.register(name, () -> sup.get().build(name));
    }

    private static EntityType.Builder<GrenadeEntity> grenade() {
	return EntityType.Builder.<GrenadeEntity>create(GrenadeEntity::new, EntityClassification.MISC);
    }

    public static void register(IEventBus modBus) {
	ENTITIES.register(modBus);
    }
}
