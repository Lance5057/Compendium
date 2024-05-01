//package lance5057.compendium.core.library.materialutilities.addons.base;
//
//import java.util.function.Consumer;
//
//import lance5057.compendium.CompendiumBlocks;
//import lance5057.compendium.CompendiumItems;
//import lance5057.compendium.CompendiumWorldGen;
//import lance5057.compendium.Reference;
//import lance5057.compendium.core.data.builders.TCBlockModels;
//import lance5057.compendium.core.data.builders.TCBlockTags;
//import lance5057.compendium.core.data.builders.TCEnglishLoc;
//import lance5057.compendium.core.data.builders.TCItemModels;
//import lance5057.compendium.core.data.builders.TCItemTags;
//import lance5057.compendium.core.data.builders.TCRecipes;
//import lance5057.compendium.core.library.materialutilities.MaterialHelper;
//import net.minecraft.core.Holder;
//import net.minecraft.core.Registry;
//import net.minecraft.data.loot.BlockLoot;
//import net.minecraft.data.recipes.FinishedRecipe;
//import net.minecraft.tags.BlockTags;
//import net.minecraft.world.item.BlockItem;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.level.biome.Biome;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.levelgen.GenerationStep;
//import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
//import net.minecraft.world.level.levelgen.feature.Feature;
//import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
//import net.minecraft.world.level.levelgen.placement.PlacedFeature;
//import net.minecraft.world.level.levelgen.placement.PlacementModifier;
//import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
//import net.minecraft.world.level.material.Material;
//import net.minecraftforge.client.model.generators.ConfiguredModel;
//import net.minecraftforge.event.world.BiomeLoadingEvent;
//import net.minecraftforge.registries.RegistryObject;
//
//public class MaterialOre extends MaterialBase {
//
//	public RegistryObject<Block> ORE;
//	public RegistryObject<BlockItem> ITEM_ORE;
//
//	public static Holder<PlacedFeature> OREGEN;
//
//	int size;
//	RuleTest test;
//
//	PlacementModifier[] modifiers;
//
//	public MaterialOre(RuleTest test, int size, PlacementModifier... mods) {
//		this.test = test;
//		this.size = size;
//		this.modifiers = mods;
//	}
//
//	@Override
//	public void setup(MaterialHelper helper) {
//		ORE = CompendiumBlocks.BLOCKS.register(helper.name + "_ore",
//				() -> new Block(Block.Properties.of(Material.METAL).strength(3, 4)));
//		ITEM_ORE = CompendiumItems.ITEMS.register(helper.name + "_itemore",
//				() -> new BlockItem(ORE.get(), new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));
//
//	}
//
//	@Override
//	public void setupClient(MaterialHelper helper) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void registerBlockModels(TCBlockModels model, String name) {
//		model.getVariantBuilder(ORE.get()).partialState()
//				.addModels(
//						ConfiguredModel.allRotations(
//								model.models().withExistingParent(name + "ore", model.modLoc("block/bases/ore"))
//										.texture("1", model.modLoc("block/material/" + name + "/" + name + "ore")),
//								true));
//	}
//
//	@Override
//	public void registerItemModels(TCItemModels model, String name) {
//		model.forBlockItem(ITEM_ORE, name);
//	}
//
//	@Override
//	public void addTranslations(TCEnglishLoc loc, String capName) {
//		loc.add(ITEM_ORE.get(), capName + " Ore");
//	}
//
//	@Override
//	public void registerItemTags(TCItemTags itp, String name) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void registerBlockTags(TCBlockTags tags, String name) {
//		tags.getOrCreateRawBuilder(BlockTags.MINEABLE_WITH_PICKAXE).addElement(Registry.BLOCK.getKey(this.ORE.get()),
//				Reference.MOD_ID);
//	}
//
//	@Override
//	public void buildLootTable(BlockLoot table, String name) {
//		table.dropSelf(ORE.get());
//	}
//
//	@Override
//	public void buildRecipes(TCRecipes recipes, Consumer<FinishedRecipe> consumer, String name) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void biomeEvent(BiomeLoadingEvent event, String name) {
//		OreConfiguration config = new OreConfiguration(test, ORE.get().defaultBlockState(), size);
//
//		OREGEN = CompendiumWorldGen.registerPlacedOreFeature(name + "_ore",
//				new ConfiguredFeature<>(Feature.ORE, config), modifiers);
//		
//		if (event.getCategory() == Biome.BiomeCategory.NETHER) {
//		} else if (event.getCategory() == Biome.BiomeCategory.THEEND) {
//		} else {
//			event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OREGEN);
//		}
//	}
//
//}
