//package lance5057.compendium.core.world.dungeon;
//
//import javax.annotation.Nonnull;
//
//import org.apache.logging.log4j.Level;
//
//import com.mojang.serialization.Codec;
//import com.sun.jna.Structure;
//
//import lance5057.compendium.Compendium;
//import net.minecraft.util.math.MutableBoundingBox;
//import net.minecraft.util.registry.DynamicRegistries;
//import net.minecraft.world.gen.GenerationStage;
//import net.minecraft.world.gen.feature.NoFeatureConfig;
//import net.minecraft.world.gen.feature.template.TemplateManager;
//import net.minecraft.world.level.biome.Biome;
//import net.minecraft.world.level.chunk.ChunkGenerator;
//import net.minecraft.world.level.levelgen.structure.StructureStart;
//
//public class CompendiumDungeonStructure extends Structure<NoFeatureConfig> {
//
//	public CompendiumDungeonStructure(Codec<NoFeatureConfig> codec) {
//		super(codec);
//	}
//
//	@Nonnull
//	@Override
//	public GenerationStage.Decoration step() {
//		return GenerationStage.Decoration.SURFACE_STRUCTURES;
//	}
//
//	@Nonnull
//	@Override
//	public IStartFactory<NoFeatureConfig> getStartFactory() {
//		return CompendiumDungeonStructure.DungeonStart::new;
//	}
//
//	public static class DungeonStart extends StructureStart<NoFeatureConfig> {
//
//		public DungeonStart(Structure<NoFeatureConfig> structure, int chunkPosX, int chunkPosZ,
//				MutableBoundingBox bounds, int references, long seed) {
//			super(structure, chunkPosX, chunkPosZ, bounds, references, seed);
//		}
//
//		@Override
//		public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator,
//				TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig config) {
//
//			int x = (chunkX << 4) + 7;
//			int z = (chunkZ << 4) + 7;
//
////			dynamicRegistryManager.registry(Registry.TEMPLATE_POOL_REGISTRY).
////			.getOrDefault(new ResourceLocation(Reference.MOD_ID, "dungeon/start_pool"))
////			
////			BlockPos blockpos = new BlockPos(x, 0, z);
////
////			JigsawManager.addPieces(dynamicRegistryManager,
////					new VillageConfig(
////							() -> ,
////
////							10),
////					AbstractVillagePiece::new, chunkGenerator, templateManagerIn, blockpos, this.pieces, this.random,
////					false, true);
////
////			this.pieces.forEach(piece -> piece.move(0, 1, 0));
////			this.pieces.forEach(piece -> piece.getBoundingBox().y0 -= 1);
//
//			this.calculateBoundingBox();
//
//			Compendium.logger.log(Level.DEBUG,
//					"Dungeon at " + this.pieces.get(0).getBoundingBox().x0 + " "
//							+ this.pieces.get(0).getBoundingBox().y0 + " "
//							+ this.pieces.get(0).getBoundingBox().z0);
//		}
//
//	}
//}