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

	int temp;

	public final RegistryObject<Item> INGOT;
	public final RegistryObject<Item> NUGGET;
	public final RegistryObject<Block> STORAGE_BLOCK;
	public final RegistryObject<BlockNamedItem> STORAGE_ITEMBLOCK;
	private final String name;

	public static Tag<Item> MATERIAL_INGOT;
	public static Tag<Item> MATERIAL_NUGGET;
	public static Tag<Item> MATERIAL_STORAGE_BLOCK;

	public MeltableMaterial(String matName) {
		this(matName, Reference.MOD_ID);
	}

	public MeltableMaterial(String matName, String parentMod) {
		name = matName;

		INGOT = TCItems.ITEMS.register(name + "ingot", () -> new Item(new Item.Properties().group(TCItems.TCITEMS)));
		NUGGET = TCItems.ITEMS.register(name + "nugget", () -> new Item(new Item.Properties().group(TCItems.TCITEMS)));

		STORAGE_BLOCK = TCBlocks.BLOCKS.register(name + "storage_block", () -> new Block(Block.Properties
				.create(Material.IRON).harvestLevel(1).hardnessAndResistance(3, 4).harvestTool(ToolType.PICKAXE)));
		STORAGE_ITEMBLOCK = TCItems.ITEMS.register(name + "storage_itemblock",
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

//	public MeltableMaterial(int temp, HeadMaterialStats head, HandleMaterialStats handle, ExtraMaterialStats extra,
//			ShieldMaterialStats shield, BowMaterialStats bow) {
//		this(temp, head, handle, extra, shield, bow, false);
//	}
//
//	public MeltableMaterial(int temp, HeadMaterialStats head, HandleMaterialStats handle, ExtraMaterialStats extra,
//			ShieldMaterialStats shield, BowMaterialStats bow, boolean hasBlockTexture) {
//		this.temp = temp;
//		this.head = head;
//		this.handle = handle;
//		this.shield = shield;
//		this.extra = extra;
//		this.bow = bow;
//
//		this.hasBlockTexture = hasBlockTexture;
//	}
//
//	@Override
//	public void setupPre(MaterialHelper mat) {
//
//		if (ingot == null) {
//			ingot = TCItems.registerItem("ingot_" + mat.mat.identifier, TinkersCompendium.tab);
//		}
//
//		if (nugget == null) {
//			nugget = TCItems.registerItem("nugget_" + mat.mat.identifier, TinkersCompendium.tab);
//		}
//
//		if (block == null) {
//			block = TCBlocks.registerBlock("block_" + mat.mat.identifier, net.minecraft.block.material.Material.IRON);
//			TCItems.registerItemBlock("block_" + mat.mat.identifier, block, TinkersCompendium.tab);
//		}
//
//		fluid = TCFluids.fluidMetal(mat.mat.getIdentifier(), mat.color, temp);
//
//		mat.mat.setCraftable(false).setCastable(true);
//		mat.mat.setFluid(fluid);
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
//		OreDictionary.registerOre("ingot" + StringUtils.capitalize(mat.mat.identifier), new ItemStack(ingot));
//		OreDictionary.registerOre("nugget" + StringUtils.capitalize(mat.mat.identifier), new ItemStack(nugget));
//		OreDictionary.registerOre("block" + StringUtils.capitalize(mat.mat.identifier), new ItemStack(block));
//
//		mat.mat.addItem(nugget, 1, Material.VALUE_Nugget);
//		mat.mat.addItem(ingot, 1, Material.VALUE_Ingot);
//		mat.mat.addItem(block, Material.VALUE_Block); 
//	}
//
//	@Override
//	public void setupClient(MaterialHelper mat) {
//		TinkersCompendium.proxy.registerItemColorHandler(mat.color, ingot);
//		TinkersCompendium.proxy.registerItemColorHandler(mat.color, nugget);
//		if (!hasBlockTexture) {
//			TinkersCompendium.proxy.registerBlockColorHandler(mat.color, block);
//			TinkersCompendium.proxy.registerItemColorHandler(mat.color, Item.getItemFromBlock(block));
//		}
//	}
//
//	
//
//	public <T extends Block> T registerBlock(T block, String name) {
//		block.setUnlocalizedName(Reference.MOD_ID + "." + name);
//		block.setRegistryName(Reference.MOD_ID + "." + name);
//		Item ib = new ItemBlock(block).setRegistryName(block.getRegistryName());
//		ForgeRegistries.BLOCKS.register(block);
//		ForgeRegistries.ITEMS.register(ib);
//		return block;
//	}
//
//	@Override
//	public void setupIntegration(MaterialIntegration mi) {
//		mi.fluid = fluid;
//
//	}
//
//	@Override
//	public void setupModels(MaterialHelper mat) {
//		TinkersCompendium.proxy.registerItemRenderer(ingot, 0, "ingot");
//		TinkersCompendium.proxy.registerItemRenderer(nugget, 0, "nugget");
//		if (!hasBlockTexture) {
//			TinkersCompendium.proxy.registerBlockRenderer(block, "block");
//			TinkersCompendium.proxy.registerItemBlockRenderer(block, 0, "componentblock");
//		}
//	}
//
//	@Override
//	public void setupInit(MaterialHelper mat) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void setupWiki(MaterialHelper mat, PrintWriter out) {
//		out.write("### Material Info \n\n");
//		out.write("Melting Temp: " + temp + "\n\n");
//
//		OutputWikiPages.createMaterialOutput(head, handle, shield, extra, bow, out);
//	}
}
