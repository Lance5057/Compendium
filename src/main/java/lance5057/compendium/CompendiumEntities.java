//package lance5057.compendium;
//
//import java.util.function.Supplier;
//
//import lance5057.compendium.core.entities.GrenadeEntity;
//import lance5057.compendium.core.entities.SeatEntity;
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.entity.EntityType;
//import net.minecraft.world.entity.MobCategory;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraftforge.registries.RegistryObject;
//
//public class CompendiumEntities {
//    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES,
//	    Reference.MOD_ID);
//
//    public static final RegistryObject<EntityType<GrenadeEntity>> GRENADE_ENTITY = register("grenade",
//	    CompendiumEntities::grenade);
//    public static final RegistryObject<EntityType<SeatEntity>> SEAT_ENTITY = register("seat",
//	    CompendiumEntities::seat);
//
//    private static <E extends Entity> RegistryObject<EntityType<E>> register(final String name,
//	    final Supplier<EntityType.Builder<E>> sup) {
//	return ENTITIES.register(name, () -> sup.get().build(name));
//    }
//
//    private static EntityType.Builder<GrenadeEntity> grenade() {
//	return EntityType.Builder.<GrenadeEntity>of(GrenadeEntity::new, MobCategory.MISC);
//    }
//    
//    private static EntityType.Builder<SeatEntity> seat() {
//	return EntityType.Builder.<SeatEntity>of(SeatEntity::new, MobCategory.MISC);
//    }
//
//    public static void register(IEventBus modBus) {
//	ENTITIES.register(modBus);
//    }
//}
