package lance5057.compendium.core.world.dungeon;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.mojang.serialization.Codec;

import lance5057.compendium.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
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
        return DungeonStart::new;
    }

    public static class DungeonStart extends StructureStart<NoFeatureConfig> {

        public DungeonStart(Structure<NoFeatureConfig> structure, int chunkPosX, int chunkPosZ, MutableBoundingBox bounds, int references, long seed) {
            super(structure, chunkPosX, chunkPosZ, bounds, references, seed);
        }

        @Override
        @ParametersAreNonnullByDefault
        public void func_230364_a_(DynamicRegistries registries, ChunkGenerator generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig config) {
            int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;
            int heightModifier = rand.nextInt(32) + 64;
            BlockPos blockpos = new BlockPos(x, generator.getHeight(x, z, Heightmap.Type.WORLD_SURFACE) + heightModifier, z);

            JigsawManager.func_242837_a(registries, new VillageConfig(() -> registries.getRegistry(Registry.JIGSAW_POOL_KEY).getOrDefault(new ResourceLocation(Reference.MOD_ID, "dungeon/start_pool")), 50), AbstractVillagePiece::new, generator, templateManagerIn, blockpos, this.components, this.rand, true, false);

            this.recalculateStructureSize();
        }

    }
}