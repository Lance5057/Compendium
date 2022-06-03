//package lance5057.compendium;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
//
//import lance5057.compendium.appendixes.oredressing.AppendixOreDressing;
//import lance5057.compendium.appendixes.oredressing.materialhelper.OreDressingMaterialHelper;
//import lance5057.compendium.configs.CompendiumConfig;
//import lance5057.compendium.core.util.OreRetrogenFeature;
//import lance5057.compendium.core.world.features.CompendiumConfiguredFeatures;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.resources.ResourceKey;
//import net.minecraft.server.level.ServerLevel;
//import net.minecraft.world.level.ChunkPos;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.LevelAccessor;
//import net.minecraft.world.level.biome.Biome.BiomeCategory;
//import net.minecraft.world.level.chunk.ChunkStatus;
//import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
//import net.minecraft.world.level.levelgen.feature.Feature;
//import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
//import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
//import net.minecraftforge.event.TickEvent;
//import net.minecraftforge.event.world.BiomeLoadingEvent;
//import net.minecraftforge.event.world.ChunkDataEvent;
//import net.minecraftforge.event.world.WorldEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.LogicalSide;
//
//public class CompendiumWorldGen {
//
//	public static final Feature<OreConfiguration> ORE_RETROGEN = new OreRetrogenFeature(OreConfiguration.CODEC);
//
//	@SubscribeEvent
//	public void biomeModification(final BiomeLoadingEvent event) {
//		// Ore
//		BiomeGenerationSettingsBuilder generation = event.getGeneration();
//
//		for (OreDressingMaterialHelper mh : AppendixOreDressing.ores) {
//			// MaterialOre ore = mh.getOre();
//			if (mh.hasSparseOre())
//				mh.getSparseOre().generate(mh, event, generation);
//			if (mh.hasOre())
//				mh.getOre().generate(mh, event, generation);
//			if (mh.hasDenseOre())
//				mh.getDenseOre().generate(mh, event, generation);
//
//		}
//
//		// Structures
////		event.getGeneration().getStructures().add(() -> CompendiumConfiguredStructures.CONFIGURED_DUNGEON.get());
////		int i = 0;
//
//		// Features
//		if (event.getCategory() == BiomeCategory.DESERT)
//			event.getGeneration().addFeature(Decoration.LAKES, CompendiumConfiguredFeatures.CONFIGURED_DRY_LAKE.get());
//	}
//
//	private void generateOres(Random random, int chunkX, int chunkZ, ServerLevel world) {
//
//		for (OreDressingMaterialHelper mh : AppendixOreDressing.ores) {
//			if (mh.hasSparseOre())
//				mh.getSparseOre().retrogen(mh, random, chunkX, chunkZ, world);
//			if (mh.hasOre())
//				mh.getOre().retrogen(mh, random, chunkX, chunkZ, world);
//			if (mh.hasDenseOre())
//				mh.getDenseOre().retrogen(mh, random, chunkX, chunkZ, world);
//		}
//	}
//
//	public static final Map<RegistryKey<Level>, List<ChunkPos>> retrogenChunks = new HashMap<>();
//
//	int indexToRemove = 0;
//
//	@SubscribeEvent
//	public void serverWorldTick(TickEvent.WorldTickEvent event) {
//		if (event.side == LogicalSide.CLIENT || event.phase == TickEvent.Phase.START)
//			return;
//		ResourceKey<net.minecraft.world.level.Level> dimension = event.world.dimension();
//		int counter = 0;
//		int remaining;
//		synchronized (retrogenChunks) {
//			final List<ChunkPos> chunks = retrogenChunks.get(dimension);
//
//			if (chunks != null && chunks.size() > 0) {
//				if (indexToRemove >= chunks.size())
//					indexToRemove = 0;
//				for (int i = 0; i < 2 && indexToRemove < chunks.size(); ++i) {
//					if (chunks.size() <= 0)
//						break;
//					ChunkPos loc = chunks.get(indexToRemove);
//					if (event.world.hasChunk(loc.x, loc.z)) {
//						long worldSeed = ((ISeedReader) event.world).getSeed();
//						Random fmlRandom = new Random(worldSeed);
//						long xSeed = (fmlRandom.nextLong() >> 3);
//						long zSeed = (fmlRandom.nextLong() >> 3);
//						fmlRandom.setSeed(xSeed * loc.x + zSeed * loc.z ^ worldSeed);
//						this.generateOres(fmlRandom, loc.x, loc.z, (ServerLevel) event.world);
//						counter++;
//						chunks.remove(indexToRemove);
//					} else
//						++indexToRemove;
//				}
//			}
//			remaining = chunks == null ? 0 : chunks.size();
//		}
//	}
//
//	@SubscribeEvent
//	public void chunkDataLoad(ChunkDataEvent.Load event) {
//		LevelAccessor Level = event.getLevel();
//		if (event.getChunk().getStatus() == ChunkStatus.FULL && Level instanceof Level) {
//			boolean b = event.getData().getCompound("Level").getCompound("Compendium")
//					.contains(CompendiumConfig.getInstance().worldgen.retroGenName.get());
//			if (!b && CompendiumConfig.getInstance().worldgen.enableRetroGen.get()) {
//				Compendium.logger.log(Level.INFO, "Chunk " + event.getChunk().getPos().toString()
//						+ " has been flagged for ore retrogen by Compendium.");
//
//				RegistryKey<Level> dimension = ((Level) world).dimension();
//				synchronized (retrogenChunks) {
//					retrogenChunks.computeIfAbsent(dimension, d -> new ArrayList<>()).add(event.getChunk().getPos());
//				}
//			}
//		}
//	}
//
//	@SubscribeEvent
//	public void chunkDataSave(ChunkDataEvent.Save event) {
//		CompoundTag levelTag = event.getData().getCompound("Level");
//		CompoundTag nbt = new CompoundTag();
//		levelTag.put("Compendium", nbt);
//		nbt.putBoolean(CompendiumConfig.getInstance().worldgen.retroGenName.get(), true);
//
//	}
//
//	@SubscribeEvent
//	public void addDimensionalSpacing(final WorldEvent.Load event) {
////		if (event.getLevel() instanceof ServerLevel) {
////			ServerLevel serverWorld = (ServerLevel) event.getLevel();
////
////			Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkProvider().generator.func_235957_b_().func_236195_a_());
////			tempMap.put(CompendiumStructures.DUNGEON.get(), DimensionStructuresSettings.field_236191_b_.get(CompendiumStructures.DUNGEON.get()));
////			serverWorld.getChunkProvider().generator.func_235957_b_().field_236193_d_ = tempMap;
////		}
//	}
//}
