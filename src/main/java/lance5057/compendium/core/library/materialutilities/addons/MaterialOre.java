package lance5057.compendium.core.library.materialutilities.addons;

import lance5057.compendium.Reference;
import lance5057.compendium.TCItems;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class MaterialOre implements MaterialBase {

	String matName;
	String parentMod;

	public RegistryObject<Block> ORE;
//	public Block gravelBlock;
//	public Block sandBlock;
//	public Block sandstoneBlock;
//	public Block netherBlock;

	public RegistryObject<BlockItem> ITEM_ORE;
//	public Item gravelItemBlock;
//	public Item sandItemBlock;
//	public Item sandstoneItemBlock;
//	public Item netherItemBlock;

	// public RegistryObject<Item> ITEM_CLUMP;
//	public Item oreGravel;
//	public Item oreSand;

	String tool;

	public static Tag<Item> MATERIAL_ORE;

//	public static ConfiguredFeature<?, ?> ORE_FEATURE;

//	public String prefix;
//	public String style;
	// public int oreColor;
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

//	public float biomeElevationMin = -2;
//	public float biomeElevationMax = -2;
//	public float biomeTempMin = -2;
//	public float biomeTempMax = -2;
//	public float biomeHumidityMin = -2;
//	public float biomeHumidityMax = -2;

//	public MaterialOre(String prefix, String style, float hardness, int level, String tool, float resistance) {
//		this(prefix, style, 0, hardness, level, tool, resistance, 64, 0, 8, 10);
//	}
//
//	public MaterialOre(String prefix, String style, int color, float hardness, int level, String tool,
//			float resistance) {
//		this(prefix, style, color, hardness, level, tool, resistance, 64, 0, 8, 10);
//	}
//
//	public MaterialOre(String prefix, String style, int color, float hardness, int level, String tool, float resistance,
//			int ymax, int ymin, int veinSize, int veinChance) {
//		this(prefix, style, color, hardness, level, tool, resistance, 64, 0, 8, 10, -2, -2, -2, -2, -2, -2);
//	}
	public MaterialOre(MaterialHelper mh, float hardness, int level, ToolType tool, float resistance, int ymax,
			int ymin, int veinSize, int veinChance, Category biomeCategory) {
		this(mh, hardness, level, tool, resistance, ymax, ymin, veinSize, veinChance, biomeCategory, Reference.MOD_ID);
	}

	public MaterialOre(MaterialHelper mh, float hardness, int level, ToolType tool, float resistance, int ymax,
			int ymin, int veinSize, int veinChance, Category biomeCategory, String parentMod) {
		this.matName = matName;
		this.parentMod = parentMod;

		this.oreYMax = ymax;
		this.oreYMin = ymin;
		this.oreSize = veinSize;
		this.oreChance = veinChance;
		this.category = biomeCategory;

		ORE = mh.BLOCKS.register(mh.name + "ore",
				() -> new Block(Block.Properties.create(Material.IRON).harvestLevel(level).harvestTool(tool)
						.hardnessAndResistance(hardness, resistance).sound(SoundType.STONE)));
		ITEM_ORE = mh.ITEMS.register(mh.name + "itemore",
				() -> new BlockItem(ORE.get(), new Item.Properties().group(TCItems.TCITEMS)));

//		ORE_FEATURE = Feature.ORE
//				.withConfiguration(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.STONE),
//						this.ORE.get().getDefaultState(), this.oreSize))
//				.withPlacement(
//						Placement.field_242907_l.configure(new TopSolidRangeConfig(this.oreYMin, 0, this.oreYMax))).func_242728_a().func_242731_b(oreChance);
	}

//	public void OreGen() {
//		for (Biome biome : ForgeRegistries.BIOMES) {
//			if (this.category == null || biome.getCategory() == this.category) {
//				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
//						Feature.ORE
//								.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.field_241882_a,
//										ORE.get().getDefaultState(), oreSize))
//								.withPlacement(Placement.
//										.configure(new ChanceConfig(this.oreChance, 0, 0, this.oreYMax))));
//			}
//		}
//	}

//	@Override
//	public void setupPre(MaterialHelper mat) {

//		oreClump = TCItems.registerItem(prefix + "clump_" + mat.mat.identifier, TinkersCompendium.tab);
//		oreGravel = TCItems.registerItem("gravelclump_" + mat.mat.identifier, TinkersCompendium.tab);
//		oreSand = TCItems.registerItem("sandclump_" + mat.mat.identifier, TinkersCompendium.tab);

	// TODO add ore clumps
	// oreBlock = new TDOreBlock(oreClump, hardness, resistance, mininglevel, tool);
	// TCBlocks.registerBlock(oreBlock, prefix + style + "_ore_" +
	// mat.mat.identifier);

//		oreItemBlock = TCItems.registerItemBlock("item_" + prefix + style + "_ore_" + mat.mat.identifier, this.oreBlock,
//				TinkersCompendium.tab);

//		gravelBlock = new TDOreBlock(oreGravel, hardness, resistance, mininglevel, "shovel");
//		TCBlocks.registerBlock(gravelBlock, "gravel_" + mat.mat.identifier);
//		gravelItemBlock = TCItems.registerItemBlock("item_" + "gravel_" + mat.mat.identifier, this.gravelBlock,
//				TinkersCompendium.tab);
//
//		sandBlock = new TDOreBlock(oreSand, hardness, resistance, mininglevel, "shovel");
//		TCBlocks.registerBlock(sandBlock, "sand_" + mat.mat.identifier);
//
//		sandItemBlock = TCItems.registerItemBlock("item_" + "sand_" + mat.mat.identifier, this.sandBlock,
//				TinkersCompendium.tab);
//		
//		sandstoneBlock = new TDOreBlock(oreSand, hardness, resistance, mininglevel, "pickaxe");
//		TCBlocks.registerBlock(sandBlock, "sandstone_" + mat.mat.identifier);
//
//		sandstoneItemBlock = TCItems.registerItemBlock("item_" + "sandstone_" + mat.mat.identifier, this.sandstoneBlock,
//				TinkersCompendium.tab);

//	}

//	@Override
//	public void setupIntegration(MaterialIntegration mi) {
//		if (mi != null)
//			if (mi.fluid != null) {
//				TinkerRegistry.registerMelting(oreBlock, mi.fluid, Material.VALUE_Ingot * 2);
//				TinkerRegistry.registerMelting(oreClump, mi.fluid, Material.VALUE_Ingot * 2);
//			}
//	}
//
//	@Override
//	public void setupPost(MaterialHelper mat) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void setupClient(MaterialHelper mat) {
//		if (this.oreColor > 0) {
//			TinkersCompendium.proxy.registerItemColorHandler(this.oreColor, oreClump);
////			TinkersCompendium.proxy.registerItemColorHandler(this.oreColor, oreGravel);
////			TinkersCompendium.proxy.registerItemColorHandler(this.oreColor, oreSand);
//
//			TinkersCompendium.proxy.registerBlockColorHandler(this.oreColor, oreBlock);
//			TinkersCompendium.proxy.registerItemColorHandler(this.oreColor, Item.getItemFromBlock(oreBlock));
//
////			TinkersCompendium.proxy.registerBlockColorHandler(this.oreColor, gravelBlock);
////			TinkersCompendium.proxy.registerItemColorHandler(this.oreColor, Item.getItemFromBlock(gravelBlock));
////
////			TinkersCompendium.proxy.registerBlockColorHandler(this.oreColor, sandBlock);
////			TinkersCompendium.proxy.registerItemColorHandler(this.oreColor, Item.getItemFromBlock(sandBlock));
//		} else {
//			TinkersCompendium.proxy.registerItemColorHandler(mat.color, oreClump);
////			TinkersCompendium.proxy.registerItemColorHandler(mat.color, oreGravel);
////			TinkersCompendium.proxy.registerItemColorHandler(mat.color, oreSand);
//
//			TinkersCompendium.proxy.registerBlockColorHandler(mat.color, oreBlock);
//			TinkersCompendium.proxy.registerItemColorHandler(mat.color, Item.getItemFromBlock(oreBlock));
//
////			TinkersCompendium.proxy.registerBlockColorHandler(mat.color, gravelBlock);
////			TinkersCompendium.proxy.registerItemColorHandler(mat.color, Item.getItemFromBlock(gravelBlock));
////
////			TinkersCompendium.proxy.registerBlockColorHandler(mat.color, sandBlock);
////			TinkersCompendium.proxy.registerItemColorHandler(mat.color, Item.getItemFromBlock(sandBlock));
//		}
//	}
//
//	@Override
//	public void setupModels(MaterialHelper mat) {
//		TinkersCompendium.proxy.registerItemRenderer(oreClump, 0, prefix + "clump");
////		TinkersCompendium.proxy.registerItemRenderer(oreGravel, 0, "gravelclump");
////		TinkersCompendium.proxy.registerItemRenderer(oreSand, 0, "sandclump");
//
//		TinkersCompendium.proxy.registerBlockRenderer(oreBlock, prefix + "ore_" + style);
//		TinkersCompendium.proxy.registerItemRenderer(Item.getItemFromBlock(oreBlock), 0, prefix + "ore_" + style);
//
////		TinkersCompendium.proxy.registerBlockRenderer(gravelBlock, "gravel");
////		TinkersCompendium.proxy.registerItemRenderer(Item.getItemFromBlock(gravelBlock), 0, "gravel_ore");
////
////		TinkersCompendium.proxy.registerBlockRenderer(sandBlock, "sand");
////		TinkersCompendium.proxy.registerItemRenderer(Item.getItemFromBlock(sandBlock), 0, "sand_ore");
//	}

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

//	@Override
//	public void setupInit(MaterialHelper mat) {
//		// TODO Auto-generated method stub
//
//	}

	@Override
	public void setupClient(MaterialHelper mat) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupModels(MaterialHelper mat) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setup(FMLCommonSetupEvent event) {
		// TODO Auto-generated method stub

	}

//	@Override
//	public void setupItemTags() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void setupBlockTags() {
//		TCItemTags.ItemTags.add(new ImmutablePair<Tag<Item>, Item>(MATERIAL_ORE, oreItemBlock));
//
//		TCItemTags.NestedTags.add(new ImmutablePair<Tag<Item>, Tag<Item>>(Tags.Items.ORES, MATERIAL_ORE));
//	}
//
//	@Override
//	public void setupRecipes() {
//	}
//
//	@Override
//	public void setupItemModels(ModelProvider<ItemModelBuilder> mp, ExistingFileHelper fh) {
//		mp.getBuilder(matName + "ore")
//		.parent(new ModelFile.UncheckedModelFile(
//				new ResourceLocation(Reference.MOD_ID, "block/" + matName + "ore")));
//	}
//
//	@Override
//	public void setupBlockModels(BlockStateProvider bsp, ExistingFileHelper fh) {
//		oreModel(bsp);
//	}
//
//	private void oreModel(BlockStateProvider bsp) {
//		ModelFile ore = bsp.models()
//				.withExistingParent(matName + "ore", bsp.modLoc("block/ore_node_corner"))
//				.texture("rod", "compendium:block/" + matName + "ore");
//
//		VariantBlockStateBuilder builder = bsp.getVariantBuilder(this.oreBlock);
//
//		builder.partialState().modelForState().modelFile(ore).addModel();
//	}
//
//	@Override
//	public void setupEnglishLocalization(LanguageProvider lang) {
//		// TODO Auto-generated method stub
//
//	}
//
//	public class Loot extends BlockLootTables {
//		@Override
//		protected void addTables() {
//			this.registerLootTable(oreBlock, dropping(oreItemBlock));
//		}
//
//		@Override
//		@Nonnull
//		protected Iterable<Block> getKnownBlocks() {
//			List<Block> l = new ArrayList<Block>();
//			l.add(oreBlock);
//			return l;
//		}
//	}
//
//	@Override
//	public void setupLoot() {
//		TCLootTables.tables.add(Pair.of(Loot::new, LootParameterSets.BLOCK));
//	}

	@Override
	public void setupItems(MaterialHelper mat) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupBlocks(MaterialHelper mat) {
		// TODO Auto-generated method stub

	}
}
