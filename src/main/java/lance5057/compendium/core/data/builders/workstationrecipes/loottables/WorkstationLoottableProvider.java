//package lance5057.compendium.core.data.builders.workstationrecipes.loottables;
//
//import java.util.List;
//import java.util.Map;
//import java.util.function.BiConsumer;
//import java.util.function.Consumer;
//import java.util.function.Supplier;
//
//import javax.annotation.Nonnull;
//
//import com.google.common.collect.ImmutableList;
//import com.mojang.datafixers.util.Pair;
//
//import net.minecraft.data.DataGenerator;
//import net.minecraft.data.loot.LootTableProvider;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.level.storage.loot.LootTable;
//import net.minecraft.world.level.storage.loot.LootTables;
//import net.minecraft.world.level.storage.loot.ValidationContext;
//import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
//import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
//
//public class WorkstationLoottableProvider extends LootTableProvider {
//
//	public WorkstationLoottableProvider(DataGenerator dataGeneratorIn) {
//		super(dataGeneratorIn);
//	}
//
//	@Override
//	@Nonnull
//	protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
//		return ImmutableList.of(Pair.of(SawhorseRecipeLoottables::new, LootContextParamSets.ALL_PARAMS),
//				Pair.of(ScrappingTableLoottables::new, LootContextParamSets.ALL_PARAMS));
//	}
//
//	@Override
//	protected void validate(Map<ResourceLocation, LootTable> map, @Nonnull ValidationContext validationtracker) {
//		map.forEach((name, table) -> LootTables.validate(validationtracker, name, table));
//	}
//}
