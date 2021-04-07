package lance5057.compendium.appendixes.oredressing.materialhelper.addons;

import java.util.Random;

import lance5057.compendium.CompendiumItems;
import lance5057.compendium.CompendiumWorldGen;
import lance5057.compendium.Reference;
import lance5057.compendium.appendixes.oredressing.materialhelper.OreDressingMaterialHelper;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public abstract class MaterialOre implements MaterialBase {

    String matName;
    String parentMod;

    public RegistryObject<Block> ORE;
    public RegistryObject<BlockItem> ITEM_ORE;

    String tool;

    public float hardness;
    public int mininglevel;
    public float resistance;

    public int oreYMax;
    public int oreYMin;
    public int oreSize;
    public int oreChance;

    public int[] oreDimWhite;
    public int[] oreDimBlack;

    public Biome[] oreBiomeWhite;
    public Biome[] oreBiomeBlack;

    public Category category;

    public MaterialOre(OreDressingMaterialHelper mh, String prefix, float hardness, int level, ToolType tool,
	    float resistance, int ymax, int ymin, int veinSize, int veinChance, Category biomeCategory) {
	this(mh, prefix, hardness, level, tool, resistance, ymax, ymin, veinSize, veinChance, biomeCategory,
		Reference.MOD_ID);
    }

    public MaterialOre(OreDressingMaterialHelper mh, String prefix, float hardness, int level, ToolType tool,
	    float resistance, int ymax, int ymin, int veinSize, int veinChance, Category biomeCategory,
	    String parentMod) {
	this.oreYMax = ymax;
	this.oreYMin = ymin;
	this.oreSize = veinSize;
	this.oreChance = veinChance;
	this.category = biomeCategory;

	ORE = mh.BLOCKS.register(mh.name + prefix + "ore",
		() -> new Block(Block.Properties.create(Material.IRON).harvestLevel(level).harvestTool(tool)
			.hardnessAndResistance(hardness, resistance).sound(SoundType.STONE)));
	ITEM_ORE = mh.ITEMS.register(mh.name + prefix + "itemore",
		() -> new BlockItem(ORE.get(), new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));

//		ORE_FEATURE = Feature.ORE
//				.withConfiguration(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.STONE),
//						this.ORE.get().getDefaultState(), this.oreSize))
//				.withPlacement(
//						Placement.field_242907_l.configure(new TopSolidRangeConfig(this.oreYMin, 0, this.oreYMax))).func_242728_a().func_242731_b(oreChance);
    }

    public void setDimensionBlackList(int... list) {
	this.oreDimBlack = list;
    }

    public void setDimensionWhiteList(int... list) {
	this.oreDimWhite = list;
    }

    public void setBiomeBlackList(Biome... list) {
	this.oreBiomeBlack = list;
    }

    public void setBiomeWhiteList(Biome... list) {
	this.oreBiomeWhite = list;
    }

    @Override
    public void setup(FMLCommonSetupEvent event) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setupClient(MaterialHelperBase mat) {
	// TODO Auto-generated method stub

    }

    public void generate(OreDressingMaterialHelper mh, BiomeLoadingEvent event,
	    BiomeGenerationSettingsBuilder generation) {
	if (event.getCategory() == category || category == null || category == Category.NONE) {

	    generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
		    Feature.ORE
			    .withConfiguration(new OreFeatureConfig(new BlockMatchRuleTest(replace(mh)),
				    ORE.get().getDefaultState(), oreSize))
			    .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(oreYMin, 0, oreYMax)))
			    .square().func_242731_b(oreChance));
	}
    }

    public void retrogen(OreDressingMaterialHelper mh, Random random, int chunkX, int chunkZ, ServerWorld world) {
	BlockPos blockPos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
	Biome biome = world.getBiome(blockPos);
	if (biome.getCategory() == category || category == null) {
	    ConfiguredFeature<?, ?> retroFeature = CompendiumWorldGen.ORE_RETROGEN
		    .withConfiguration(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.STONE),
			    ORE.get().getDefaultState(), oreSize))
		    .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(oreYMin, 0, oreYMax)));
	    retroFeature.generate(world, world.getChunkProvider().getChunkGenerator(), random,
		    new BlockPos(16 * chunkX, 0, 16 * chunkZ));
	}
    }

    public abstract Block replace(OreDressingMaterialHelper mh);
}
