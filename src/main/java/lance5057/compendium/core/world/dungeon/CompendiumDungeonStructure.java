package lance5057.compendium.core.world.dungeon;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.Level;

import com.mojang.serialization.Codec;

import lance5057.compendium.Compendium;
import lance5057.compendium.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class CompendiumDungeonStructure extends Structure<NoFeatureConfig> {

	public CompendiumDungeonStructure(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	@Nonnull
	@Override
	public GenerationStage.Decoration getDecorationStage() {
		return GenerationStage.Decoration.SURFACE_STRUCTURES;
	}

	@Nonnull
	@Override
	public IStartFactory<NoFeatureConfig> getStartFactory() {
		return CompendiumDungeonStructure.DungeonStart::new;
	}

	public static class DungeonStart extends StructureStart<NoFeatureConfig> {

		public DungeonStart(Structure<NoFeatureConfig> structure, int chunkPosX, int chunkPosZ, MutableBoundingBox bounds, int references, long seed) {
			super(structure, chunkPosX, chunkPosZ, bounds, references, seed);
		}

		@Override
		public void func_230364_a_(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig config) {

			int x = (chunkX << 4) + 7;
			int z = (chunkZ << 4) + 7;

			BlockPos blockpos = new BlockPos(x, 0, z);

			JigsawManager.func_242837_a(dynamicRegistryManager, new VillageConfig(() -> dynamicRegistryManager.getRegistry(Registry.JIGSAW_POOL_KEY).getOrDefault(new ResourceLocation(Reference.MOD_ID, "dungeon/start_pool")),

					10), AbstractVillagePiece::new, chunkGenerator, templateManagerIn, blockpos, this.components, this.rand, false, true);

			this.components.forEach(piece -> piece.offset(0, 1, 0));
			this.components.forEach(piece -> piece.getBoundingBox().minY -= 1);

			this.recalculateStructureSize();

			Compendium.logger.log(Level.DEBUG, "Dungeon at " + this.components.get(0).getBoundingBox().minX + " " + this.components.get(0).getBoundingBox().minY + " " + this.components.get(0).getBoundingBox().minZ);
		}

	}
}