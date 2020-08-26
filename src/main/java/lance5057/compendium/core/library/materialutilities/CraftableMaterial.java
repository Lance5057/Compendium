package lance5057.tDefense.core.library.materialutilities;

import java.io.PrintWriter;

import org.apache.commons.lang3.tuple.ImmutablePair;

import lance5057.tDefense.Reference;
import lance5057.tDefense.TCBlocks;
import lance5057.tDefense.TCItems;
import lance5057.tDefense.core.data.builders.TCItemTags;
import lance5057.tDefense.core.data.builders.TCRecipes;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CraftableMaterial implements MaterialBase {
//	public HeadMaterialStats head;
//	public HandleMaterialStats handle;
//	public ShieldMaterialStats shield;
//	public ExtraMaterialStats extra;
//	public BowMaterialStats bow;

//	public String type;
//	public String oreDict;
	String matName;
	String parentMod;

	public Item gem = new Item(new Item.Properties().group(TCItems.TCITEMS));;
	public Item nugget = new Item(new Item.Properties().group(TCItems.TCITEMS));;

	public Block block = new Block(Block.Properties.create(Material.GLASS));
	public Item itemBlock = new BlockItem(block, new Item.Properties().group(TCItems.TCITEMS));
	
	public static Tag<Item> MATERIAL_GEM;
	public static Tag<Item> MATERIAL_NUGGET;
	public static Tag<Item> MATERIAL_STORAGE_BLOCK;

	public CraftableMaterial(String matName) {
		this(matName, Reference.MOD_ID);
	}
	
	public CraftableMaterial(String matName, String parentMod) {
		this.matName = matName;
		this.parentMod = parentMod;
		
		gem.setRegistryName(new ResourceLocation(Reference.MOD_ID, matName + "gem"));
		nugget.setRegistryName(new ResourceLocation(Reference.MOD_ID, matName + "nugget"));
		itemBlock.setRegistryName(new ResourceLocation(Reference.MOD_ID, matName + "gemblock"));

		TCItems.ITEMS.add(gem);
		TCItems.ITEMS.add(nugget);
		TCItems.ITEMS.add(itemBlock);

		block.setRegistryName(new ResourceLocation(Reference.MOD_ID, matName + "gemblock"));
		TCBlocks.BLOCKS.add(block);
		
		MATERIAL_GEM = ItemTags.getCollection()
				.getOrCreate(new ResourceLocation(Reference.MOD_ID, "gems/" + matName));
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
		TCItemTags.ItemTags.add(new ImmutablePair<Tag<Item>, Item>(MATERIAL_GEM, gem));
		TCItemTags.ItemTags.add(new ImmutablePair<Tag<Item>, Item>(MATERIAL_NUGGET, nugget));
		// TCItemTags.ItemTags.add(new ImmutablePair<Tag<Item>,
		// Item>(Tags.Items.STORAGE_BLOCKS, itemBlock));

		TCItemTags.NestedTags.add(new ImmutablePair<Tag<Item>, Tag<Item>>(Tags.Items.INGOTS, MATERIAL_GEM));
		TCItemTags.NestedTags.add(new ImmutablePair<Tag<Item>, Tag<Item>>(Tags.Items.NUGGETS, MATERIAL_NUGGET));
	}

	@Override
	public void setupBlockTags() {
		//getBuilder(Tags.Blocks.STORAGE_BLOCKS).add(block);
		
	}

	@Override
	public void setupRecipes() {
		TCRecipes.shapeless.add(new ImmutablePair<ShapelessRecipeBuilder, String>(
				ShapelessRecipeBuilder.shapelessRecipe(nugget, 9)// .addIngredient(ForgeRegistries.ITEMS.getValue(new
						// ResourceLocation(parentMod, matName+"ingot")),2)
						.addIngredient(Ingredient.fromTag(ItemTags.getCollection()
								.getOrCreate(new ResourceLocation(parentMod, "gems/" + matName)))),
				new ResourceLocation(Reference.MOD_ID, matName + "nugget1").toString()));
		
		TCRecipes.shapeless.add(new ImmutablePair<ShapelessRecipeBuilder, String>(
				ShapelessRecipeBuilder.shapelessRecipe(gem, 9)// .addIngredient(ForgeRegistries.ITEMS.getValue(new
						// ResourceLocation(parentMod, matName+"ingot")),2)
						.addIngredient(Ingredient.fromTag(ItemTags.getCollection()
								.getOrCreate(new ResourceLocation(parentMod, "gems/" + matName)))),
				new ResourceLocation(Reference.MOD_ID, matName + "ingot1").toString()));
		
		TCRecipes.shapeless.add(new ImmutablePair<ShapelessRecipeBuilder, String>(
				ShapelessRecipeBuilder.shapelessRecipe(gem, 1)// .addIngredient(ForgeRegistries.ITEMS.getValue(new
						// ResourceLocation(parentMod, matName+"ingot")),2)
						.addIngredient(Ingredient.fromTag(ItemTags.getCollection()
								.getOrCreate(new ResourceLocation(parentMod, "nuggets/" + matName))), 9),
				new ResourceLocation(Reference.MOD_ID, matName + "ingot2").toString()));
		
		TCRecipes.shapeless.add(new ImmutablePair<ShapelessRecipeBuilder, String>(
				ShapelessRecipeBuilder.shapelessRecipe(this.itemBlock, 1)// .addIngredient(ForgeRegistries.ITEMS.getValue(new
						// ResourceLocation(parentMod, matName+"ingot")),2)
						.addIngredient(Ingredient.fromTag(ItemTags.getCollection()
								.getOrCreate(new ResourceLocation(parentMod, "gems/" + matName))), 9),
				new ResourceLocation(Reference.MOD_ID, matName + "block1").toString()));
	}

	@Override
	public void setupItemModels(ModelProvider<ItemModelBuilder> mp, ExistingFileHelper fh) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setupBlockModels(BlockStateProvider bsp, ExistingFileHelper fh) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setupEnglishLocalization(LanguageProvider lang) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setupLoot() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setup(FMLCommonSetupEvent event) {
		// TODO Auto-generated method stub
		
	}

//	public CraftableMaterial(HeadMaterialStats head, HandleMaterialStats handle, ExtraMaterialStats extra,
//			ShieldMaterialStats shield, BowMaterialStats bow) {
//		this("ingot", head, handle, extra, shield, bow);
//	}
//
//	public CraftableMaterial(String type, HeadMaterialStats head, HandleMaterialStats handle, ExtraMaterialStats extra,
//			ShieldMaterialStats shield, BowMaterialStats bow) {
//		this.type = type;
//		this.head = head;
//		this.handle = handle;
//		this.shield = shield;
//		this.extra = extra;
//		this.bow = bow;
//	}
//
//	public CraftableMaterial(String type, String ore, HeadMaterialStats head, HandleMaterialStats handle,
//			ExtraMaterialStats extra, ShieldMaterialStats shield, BowMaterialStats bow) {
//		this.type = type;
//		this.head = head;
//		this.handle = handle;
//		this.shield = shield;
//		this.extra = extra;
//		this.bow = bow;
//
//		this.oreDict = ore;
//	}
//
//	@Override
//	public void setupPre(MaterialHelper mat) {
//		if (doIngot && ingot == null) {
//			ingot = TCItems.registerItem(type + "_" + mat.mat.identifier, TinkersCompendium.tab);
//		}
//
//		if (doNugget && nugget == null) {
//			nugget = TCItems.registerItem("nugget_" + mat.mat.identifier, TinkersCompendium.tab);
//		}
//
//		if (doBlock && block == null) {
//			block = TCBlocks.registerBlock("block_" + mat.mat.identifier, net.minecraft.block.material.Material.ROCK);
//			TCItems.registerItemBlock("block_" + mat.mat.identifier, block, TinkersCompendium.tab);
//		}
//
//		mat.mat.setCraftable(true).setCastable(false);
//
//		if (head != null)
//			TinkerRegistry.addMaterialStats(mat.mat, head);
//		if (handle != null)
//			TinkerRegistry.addMaterialStats(mat.mat, handle);
//		if (extra != null)
//			TinkerRegistry.addMaterialStats(mat.mat, extra);
//		if (shield != null)
//			TinkerRegistry.addMaterialStats(mat.mat, shield);
//		if (bow != null)
//			TinkerRegistry.addMaterialStats(mat.mat, bow);
//	}
//
//	@Override
//	public void setupPost(MaterialHelper mat) {
//		if (doIngot)
//			OreDictionary.registerOre(type + StringUtils.capitalize(mat.mat.identifier), new ItemStack(ingot));
//		if (doNugget)
//			OreDictionary.registerOre("nugget" + StringUtils.capitalize(mat.mat.identifier), new ItemStack(nugget));
//		if (doBlock)
//			OreDictionary.registerOre("block" + StringUtils.capitalize(mat.mat.identifier), new ItemStack(block));
//	}
//
//	@Override
//	public void setupClient(MaterialHelper mat) {
//		if (doIngot)
//			TinkersCompendium.proxy.registerItemColorHandler(mat.color, ingot);
//		if (doNugget)
//			TinkersCompendium.proxy.registerItemColorHandler(mat.color, nugget);
//		if (doBlock) {
//			TinkersCompendium.proxy.registerBlockColorHandler(mat.color, block);
//			TinkersCompendium.proxy.registerItemColorHandler(mat.color, Item.getItemFromBlock(block));
//		}
//	}
//
//	@Override
//	public void setupIntegration(MaterialIntegration mi) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void setupModels(MaterialHelper mat) {
//		if (doIngot)
//			TinkersCompendium.proxy.registerItemRenderer(ingot, 0, type);
//		if (doNugget)
//			TinkersCompendium.proxy.registerItemRenderer(nugget, 0, "nugget");
//
//		if (doBlock) {
//			TinkersCompendium.proxy.registerBlockRenderer(block, "block");
//			TinkersCompendium.proxy.registerItemBlockRenderer(block, 0, "componentblock");
//		}
//	}
//
//	@Override
//	public void setupInit(MaterialHelper mat) {
//		if (ingot != null) {
//			mat.mat.addItem(ingot, 1, Material.VALUE_Ingot);
//			mat.mat.setRepresentativeItem(ingot);
//		}
//		if (nugget != null)
//			mat.mat.addItem(nugget, 1, Material.VALUE_Nugget);
//		if (block != null)
//			mat.mat.addItem(block, Material.VALUE_Block);
//
//		if (ingot != null)
//			mat.mat.addItem(type + StringUtils.capitalize(mat.mat.identifier), 1, mat.mat.VALUE_Ingot);
//		if (nugget != null)
//			mat.mat.addItem("nugget" + StringUtils.capitalize(mat.mat.identifier), 1, mat.mat.VALUE_Nugget);
//		if (block != null)
//			mat.mat.addItem("block" + StringUtils.capitalize(mat.mat.identifier), 1, mat.mat.VALUE_Block);
//
//		if (oreDict != null)
//			mat.mat.addItem(oreDict, 1, mat.mat.VALUE_Ingot);
//	}
//
//	public CraftableMaterial disableIngot() {
//		this.doIngot = false;
//		return this;
//	}
//
//	public CraftableMaterial disableNugget() {
//		this.doNugget = false;
//		return this;
//	}
//
//	public CraftableMaterial disableBlock() {
//		this.doBlock = false;
//		return this;
//	}
//
//	@Override
//	public void setupWiki(MaterialHelper mat, PrintWriter out) {
//		OutputWikiPages.createMaterialOutput(head, handle, shield, extra, bow, out);
//	}

}
