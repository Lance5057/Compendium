package lance5057.compendium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.Level;

import lance5057.compendium.appendixes.oredressing.AppendixOreDressing;
import lance5057.compendium.appendixes.oredressing.materialhelper.OreDressingMaterialHelper;
import lance5057.compendium.configs.CompendiumConfig;
import lance5057.compendium.core.util.OreRetrogenFeature;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CompendiumWorldGen {

    public static final Feature<OreFeatureConfig> ORE_RETROGEN = new OreRetrogenFeature(OreFeatureConfig.CODEC);

    @SubscribeEvent
    public void biomeModification(final BiomeLoadingEvent event) {
	// Ore
	BiomeGenerationSettingsBuilder generation = event.getGeneration();

	for (OreDressingMaterialHelper mh : AppendixOreDressing.ores) {
	    // MaterialOre ore = mh.getOre();
	    if (mh.hasSparseOre())
		mh.getSparseOre().generate(mh, event, generation);
	    if (mh.hasOre())
		mh.getOre().generate(mh, event, generation);
	    if (mh.hasDenseOre())
		mh.getDenseOre().generate(mh, event, generation);
	    
	}

	// Structures
//		event.getGeneration().getStructures().add(() -> CompendiumConfiguredStructures.CONFIGURED_DUNGEON.get());
//		int i = 0;
    }

    private void generateOres(Random random, int chunkX, int chunkZ, ServerWorld world) {
	
	for (OreDressingMaterialHelper mh : AppendixOreDressing.ores) {
	    if (mh.hasSparseOre())
		mh.getSparseOre().retrogen(mh, random, chunkX, chunkZ, world);
	    if (mh.hasOre())
		mh.getOre().retrogen(mh, random, chunkX, chunkZ, world);
	    if (mh.hasDenseOre())
		mh.getDenseOre().retrogen(mh, random, chunkX, chunkZ, world);
	}
    }

    public static final Map<RegistryKey<World>, List<ChunkPos>> retrogenChunks = new HashMap<>();

    int indexToRemove = 0;

    @SubscribeEvent
    public void serverWorldTick(TickEvent.WorldTickEvent event) {
	if (event.side == LogicalSide.CLIENT || event.phase == TickEvent.Phase.START
		|| !(event.world instanceof ServerWorld))
	    return;
	RegistryKey<World> dimension = event.world.getDimensionKey();
	int counter = 0;
	int remaining;
	synchronized (retrogenChunks) {
	    final List<ChunkPos> chunks = retrogenChunks.get(dimension);

	    if (chunks != null && chunks.size() > 0) {
		if (indexToRemove >= chunks.size())
		    indexToRemove = 0;
		for (int i = 0; i < 2 && indexToRemove < chunks.size(); ++i) {
		    if (chunks.size() <= 0)
			break;
		    ChunkPos loc = chunks.get(indexToRemove);
		    if (event.world.chunkExists(loc.x, loc.z)) {
			long worldSeed = ((ISeedReader) event.world).getSeed();
			Random fmlRandom = new Random(worldSeed);
			long xSeed = (fmlRandom.nextLong() >> 3);
			long zSeed = (fmlRandom.nextLong() >> 3);
			fmlRandom.setSeed(xSeed * loc.x + zSeed * loc.z ^ worldSeed);
			this.generateOres(fmlRandom, loc.x, loc.z, (ServerWorld) event.world);
			counter++;
			chunks.remove(indexToRemove);
		    } else
			++indexToRemove;
		}
	    }
	    remaining = chunks == null ? 0 : chunks.size();
	}
    }

    @SubscribeEvent
    public void chunkDataLoad(ChunkDataEvent.Load event) {
	IWorld world = event.getWorld();
	if (event.getChunk().getStatus() == ChunkStatus.FULL && world instanceof World) {
	    boolean b = event.getData().getCompound("Level").getCompound("Compendium")
		    .contains(CompendiumConfig.getInstance().worldgen.retroGenName.get());
	    if (!b && CompendiumConfig.getInstance().worldgen.enableRetroGen.get()) {
		Compendium.logger.log(Level.INFO, "Chunk " + event.getChunk().getPos().toString()
			+ " has been flagged for ore retrogen by Compendium.");

		RegistryKey<World> dimension = ((World) world).getDimensionKey();
		synchronized (retrogenChunks) {
		    retrogenChunks.computeIfAbsent(dimension, d -> new ArrayList<>()).add(event.getChunk().getPos());
		}
	    }
	}
    }

    @SubscribeEvent
    public void chunkDataSave(ChunkDataEvent.Save event) {
	CompoundNBT levelTag = event.getData().getCompound("Level");
	CompoundNBT nbt = new CompoundNBT();
	levelTag.put("Compendium", nbt);
	nbt.putBoolean(CompendiumConfig.getInstance().worldgen.retroGenName.get(), true);

    }

    @SubscribeEvent
    public void addDimensionalSpacing(final WorldEvent.Load event) {
//		if (event.getWorld() instanceof ServerWorld) {
//			ServerWorld serverWorld = (ServerWorld) event.getWorld();
//
//			Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkProvider().generator.func_235957_b_().func_236195_a_());
//			tempMap.put(CompendiumStructures.DUNGEON.get(), DimensionStructuresSettings.field_236191_b_.get(CompendiumStructures.DUNGEON.get()));
//			serverWorld.getChunkProvider().generator.func_235957_b_().field_236193_d_ = tempMap;
//		}
    }
}
