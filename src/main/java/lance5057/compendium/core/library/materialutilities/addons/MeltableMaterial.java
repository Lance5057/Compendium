package lance5057.compendium.core.library.materialutilities.addons;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.codehaus.plexus.util.StringUtils;

import com.mojang.datafixers.util.Pair;

import lance5057.compendium.Reference;
import lance5057.compendium.TCBlocks;
import lance5057.compendium.TCItems;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.data.builders.TCLootTables;
import lance5057.compendium.core.data.builders.TCRecipes;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class MeltableMaterial implements MaterialBase {

	String matName;
	String parentMod;

	public static RegistryObject<Item> INGOT;
	public static RegistryObject<Item> NUGGET;
	public static RegistryObject<Block> STORAGE_BLOCK;
	public static RegistryObject<BlockNamedItem> STORAGE_ITEMBLOCK;
	private final String name;

	public static Tag<Item> MATERIAL_INGOT;
	public static Tag<Item> MATERIAL_NUGGET;
	public static Tag<Item> MATERIAL_STORAGE_BLOCK;

	public MeltableMaterial(MaterialHelper mh) {
		name = matName;

		INGOT = mh.ITEMS.register(mh.name + "gem",
				() -> new Item(new Item.Properties().group(TCItems.TCITEMS)));
		NUGGET = mh.ITEMS.register(mh.name + "nugget",
				() -> new Item(new Item.Properties().group(TCItems.TCITEMS)));

		STORAGE_BLOCK = mh.BLOCKS.register(mh.name + "storage_block", () -> new Block(Block.Properties
				.create(Material.IRON).harvestLevel(1).hardnessAndResistance(3, 4).harvestTool(ToolType.PICKAXE)));
		STORAGE_ITEMBLOCK = TCItems.ITEMS.register(mh.name + "storage_itemblock",
				() -> new BlockNamedItem(STORAGE_BLOCK.get(), new Item.Properties().group(TCItems.TCITEMS)));

		MATERIAL_INGOT = ItemTags.getCollection()
				.getOrCreate(new ResourceLocation(Reference.MOD_ID, "ingots/" + matName));
		MATERIAL_NUGGET = ItemTags.getCollection()
				.getOrCreate(new ResourceLocation(Reference.MOD_ID, "nuggets/" + matName));
		MATERIAL_STORAGE_BLOCK = ItemTags.getCollection()
				.getOrCreate(new ResourceLocation(Reference.MOD_ID, "storage_blocks/" + matName));

	}

	@Override
	public void setupClient(MaterialHelper mat) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupModels(MaterialHelper mat) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupWiki(MaterialHelper mat, PrintWriter out) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupItemTags() {
//		TCItemTags.ItemTags.add(new ImmutablePair<Tag<Item>, Item>(Tags.Items.INGOTS, ingot));
//		TCItemTags.ItemTags.add(new ImmutablePair<Tag<Item>, Item>(Tags.Items.NUGGETS, nugget));
//		TCItemTags.ItemTags.add(new ImmutablePair<Tag<Item>, Item>(Tags.Items.STORAGE_BLOCKS, this.itemBlock));
//		// TCItemTags.ItemTags.add(new ImmutablePair<Tag<Item>,
//		// Item>(Tags.Items.STORAGE_BLOCKS, itemBlock));
//
//		TCItemTags.NestedTags.add(new ImmutablePair<Tag<Item>, Tag<Item>>(Tags.Items.INGOTS, MATERIAL_INGOT));
//		TCItemTags.NestedTags.add(new ImmutablePair<Tag<Item>, Tag<Item>>(Tags.Items.NUGGETS, MATERIAL_NUGGET));
//		TCItemTags.NestedTags
//				.add(new ImmutablePair<Tag<Item>, Tag<Item>>(Tags.Items.STORAGE_BLOCKS, MATERIAL_STORAGE_BLOCK));
	}

	@Override
	public void setupBlockTags() {
		// getBuilder(Tags.Blocks.STORAGE_BLOCKS).add(block);
	}

	@Override
	public void setupRecipes() {
//		TCRecipes.furnace
//				.add(new ImmutablePair<CookingRecipeBuilder, String>(
//						CookingRecipeBuilder.smeltingRecipe(
//								Ingredient.fromTag(ItemTags.getCollection()
//										.getOrCreate(new ResourceLocation(parentMod, "ores/" + matName))),
//								this.ingot, 1.0F, 100),
//						new ResourceLocation(Reference.MOD_ID, matName + "ingot_from_smelting").toString()));
//
//		TCRecipes.furnace
//				.add(new ImmutablePair<CookingRecipeBuilder, String>(
//						CookingRecipeBuilder.blastingRecipe(
//								Ingredient.fromTag(ItemTags.getCollection()
//										.getOrCreate(new ResourceLocation(parentMod, "ores/" + matName))),
//								this.ingot, 1.0F, 100),
//						new ResourceLocation(Reference.MOD_ID, matName + "ingot_from_blasting").toString()));
//
//		TCRecipes.shapeless.add(new ImmutablePair<ShapelessRecipeBuilder, String>(
//				ShapelessRecipeBuilder.shapelessRecipe(nugget, 9)// .addIngredient(ForgeRegistries.ITEMS.getValue(new
//						// ResourceLocation(parentMod, matName+"ingot")),2)
//						.addIngredient(Ingredient.fromTag(ItemTags.getCollection()
//								.getOrCreate(new ResourceLocation(parentMod, "ingots/" + matName)))),
//				new ResourceLocation(Reference.MOD_ID, matName + "nugget1").toString()));
//
//		TCRecipes.shapeless.add(new ImmutablePair<ShapelessRecipeBuilder, String>(
//				ShapelessRecipeBuilder.shapelessRecipe(ingot, 9)// .addIngredient(ForgeRegistries.ITEMS.getValue(new
//						// ResourceLocation(parentMod, matName+"ingot")),2)
//						.addIngredient(Ingredient.fromTag(ItemTags.getCollection()
//								.getOrCreate(new ResourceLocation(parentMod, "storage_blocks/" + matName)))),
//				new ResourceLocation(Reference.MOD_ID, matName + "ingot1").toString()));
//
//		TCRecipes.shapeless.add(new ImmutablePair<ShapelessRecipeBuilder, String>(
//				ShapelessRecipeBuilder.shapelessRecipe(ingot, 1)// .addIngredient(ForgeRegistries.ITEMS.getValue(new
//						// ResourceLocation(parentMod, matName+"ingot")),2)
//						.addIngredient(Ingredient.fromTag(ItemTags.getCollection()
//								.getOrCreate(new ResourceLocation(parentMod, "nuggets/" + matName))), 9),
//				new ResourceLocation(Reference.MOD_ID, matName + "ingot2").toString()));
//
//		TCRecipes.shapeless.add(new ImmutablePair<ShapelessRecipeBuilder, String>(
//				ShapelessRecipeBuilder.shapelessRecipe(this.itemBlock, 1)// .addIngredient(ForgeRegistries.ITEMS.getValue(new
//						// ResourceLocation(parentMod, matName+"ingot")),2)
//						.addIngredient(Ingredient.fromTag(ItemTags.getCollection()
//								.getOrCreate(new ResourceLocation(parentMod, "ingots/" + matName))), 9),
//				new ResourceLocation(Reference.MOD_ID, matName + "block1").toString()));
	}

	@Override
	public void setupItemModels(ModelProvider<ItemModelBuilder> mp, ExistingFileHelper fh) {
//		mp.singleTexture(matName + "ingot", mp.mcLoc("item/generated"), "layer0",
//				mp.modLoc("items/" + matName + "ingot"));
//		mp.singleTexture(matName + "nugget", mp.mcLoc("item/generated"), "layer0",
//				mp.modLoc("items/" + matName + "nugget"));
//		mp.getBuilder(matName + "storageblock").parent(
//				new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "block/" + matName + "block")));
	}

	@Override
	public void setupBlockModels(BlockStateProvider bsp, ExistingFileHelper fh) {
		//bsp.simpleBlock(block);
	}

	@Override
	public void setupEnglishLocalization(LanguageProvider lang) {
//		lang.add(this.block, StringUtils.capitalise(matName) + " Block");
//		lang.add(this.ingot, StringUtils.capitalise(matName) + " Ingot");
//		lang.add(this.nugget, StringUtils.capitalise(matName) + " Nugget");
	}

	public class Loot extends BlockLootTables {
		@Override
		protected void addTables() {
			//this.registerLootTable(block, dropping(itemBlock));
		}

		@Override
		@Nonnull
		protected Iterable<Block> getKnownBlocks() {
			List<Block> l = new ArrayList<Block>();
			//l.add(block);
			return l;
		}
	}

	@Override
	public void setupLoot() {
		TCLootTables.tables.add(Pair.of(Loot::new, LootParameterSets.BLOCK));
	}

	@Override
	public void setup(FMLCommonSetupEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupItems(MaterialHelper mat) {
		
	}

	@Override
	public void setupBlocks(MaterialHelper mat) {
		
	}
}
