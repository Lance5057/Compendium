package lance5057.compendium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import lance5057.compendium.configs.CompendiumConfig;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.MaterialOre;
import lance5057.compendium.core.materials.CompendiumMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class TCEvents {
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {

//		TinkersCompendium.parts.registerItems(event);
//		TinkersCompendium.tools.registerItems(event);
//		TinkersCompendium.workstations.registerItems(event);
//		TinkersCompendium.textiles.registerItems(event);
//
////		if (TinkersCompendium.bloodmagic != null)
////			TinkersCompendium.bloodmagic.registerItems(event);
////		if (TinkersCompendium.botania != null)
////			TinkersCompendium.botania.registerItems(event);

		// TinkersCompendium.items.registerItems(event);
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
//		TinkersCompendium.mats.registerBlocks(event);
//		TinkersCompendium.tools.registerBlocks(event);
//		TinkersCompendium.workstations.registerBlocks(event);
//		TinkersCompendium.textiles.registerBlocks(event);
//
////		if (TinkersCompendium.bloodmagic != null)
////			TinkersCompendium.bloodmagic.registerBlocks(event);
////		if (TinkersCompendium.botania != null)
////			TinkersCompendium.botania.registerBlocks(event);

		// TinkersCompendium.blocks.registerBlocks(event);
	}
}
