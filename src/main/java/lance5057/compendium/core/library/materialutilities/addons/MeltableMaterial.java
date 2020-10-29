package lance5057.compendium.core.library.materialutilities.addons;

import lance5057.compendium.Reference;
import lance5057.compendium.TCItems;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class MeltableMaterial implements MaterialBase {

	String matName;
	String parentMod;

	public RegistryObject<Item> INGOT;
	public RegistryObject<Item> NUGGET;
	public RegistryObject<Block> STORAGE_BLOCK;
	public RegistryObject<BlockNamedItem> STORAGE_ITEMBLOCK;
	private final String name;

	public Tag<Item> MATERIAL_INGOT;
	public Tag<Item> MATERIAL_NUGGET;
	public Tag<Item> MATERIAL_STORAGE_BLOCK;

	public MeltableMaterial(MaterialHelper mh) {
		name = matName;

		INGOT = mh.ITEMS.register(mh.name + "ingot",
				() -> new Item(new Item.Properties().group(TCItems.TCITEMS)));
		NUGGET = mh.ITEMS.register(mh.name + "nugget",
				() -> new Item(new Item.Properties().group(TCItems.TCITEMS)));

		STORAGE_BLOCK = mh.BLOCKS.register(mh.name + "block", () -> new Block(Block.Properties
				.create(Material.IRON).harvestLevel(1).hardnessAndResistance(3, 4).harvestTool(ToolType.PICKAXE)));
		STORAGE_ITEMBLOCK = mh.ITEMS.register(mh.name + "itemblock",
				() -> new BlockNamedItem(STORAGE_BLOCK.get(), new Item.Properties().group(TCItems.TCITEMS)));

//		MATERIAL_INGOT = ItemTags.getCollection()
//				.getOrCreate(new ResourceLocation(Reference.MOD_ID, "ingots/" + matName));
//		MATERIAL_NUGGET = ItemTags.getCollection()
//				.getOrCreate(new ResourceLocation(Reference.MOD_ID, "nuggets/" + matName));
//		MATERIAL_STORAGE_BLOCK = ItemTags.getCollection()
//				.getOrCreate(new ResourceLocation(Reference.MOD_ID, "storage_blocks/" + matName));

	}

	@Override
	public void setupClient(MaterialHelper mat) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupModels(MaterialHelper mat) {
		// TODO Auto-generated method stub

	}

//	@Override
//	public void setupWiki(MaterialHelper mat, PrintWriter out) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void setupItemTags() {
////		TCItemTags.ItemTags.add(new ImmutablePair<Tag<Item>, Item>(Tags.Items.INGOTS, INGOT.get()));
////		TCItemTags.ItemTags.add(new ImmutablePair<Tag<Item>, Item>(Tags.Items.NUGGETS, NUGGET.get()));
////		TCItemTags.ItemTags.add(new ImmutablePair<Tag<Item>, Item>(Tags.Items.STORAGE_BLOCKS, STORAGE_ITEMBLOCK.get()));
////		// TCItemTags.ItemTags.add(new ImmutablePair<Tag<Item>,
////		// Item>(Tags.Items.STORAGE_BLOCKS, itemBlock));
////
////		TCItemTags.NestedTags.add(new ImmutablePair<Tag<Item>, Tag<Item>>(Tags.Items.INGOTS, MATERIAL_INGOT));
////		TCItemTags.NestedTags.add(new ImmutablePair<Tag<Item>, Tag<Item>>(Tags.Items.NUGGETS, MATERIAL_NUGGET));
////		TCItemTags.NestedTags
////				.add(new ImmutablePair<Tag<Item>, Tag<Item>>(Tags.Items.STORAGE_BLOCKS, MATERIAL_STORAGE_BLOCK));
//	}
//
//	@Override
//	public void setupBlockTags() {
//		//TCBlockTags.ItemTags.add(new ImmutablePair<Tag<Item>, Item>(Tags.Items.INGOTS, INGOT.get()));
//	}
//
//	@Override
//	public void setupRecipes() {
////		TCRecipes.furnace
////				.add(new ImmutablePair<CookingRecipeBuilder, String>(
////						CookingRecipeBuilder.smeltingRecipe(
////								Ingredient.fromTag(ItemTags.getCollection()
////										.getOrCreate(new ResourceLocation(parentMod, "ores/" + matName))),
////								this.ingot, 1.0F, 100),
////						new ResourceLocation(Reference.MOD_ID, matName + "ingot_from_smelting").toString()));
////
////		TCRecipes.furnace
////				.add(new ImmutablePair<CookingRecipeBuilder, String>(
////						CookingRecipeBuilder.blastingRecipe(
////								Ingredient.fromTag(ItemTags.getCollection()
////										.getOrCreate(new ResourceLocation(parentMod, "ores/" + matName))),
////								this.ingot, 1.0F, 100),
////						new ResourceLocation(Reference.MOD_ID, matName + "ingot_from_blasting").toString()));
////
////		TCRecipes.shapeless.add(new ImmutablePair<ShapelessRecipeBuilder, String>(
////				ShapelessRecipeBuilder.shapelessRecipe(nugget, 9)// .addIngredient(ForgeRegistries.ITEMS.getValue(new
////						// ResourceLocation(parentMod, matName+"ingot")),2)
////						.addIngredient(Ingredient.fromTag(ItemTags.getCollection()
////								.getOrCreate(new ResourceLocation(parentMod, "ingots/" + matName)))),
////				new ResourceLocation(Reference.MOD_ID, matName + "nugget1").toString()));
////
////		TCRecipes.shapeless.add(new ImmutablePair<ShapelessRecipeBuilder, String>(
////				ShapelessRecipeBuilder.shapelessRecipe(ingot, 9)// .addIngredient(ForgeRegistries.ITEMS.getValue(new
////						// ResourceLocation(parentMod, matName+"ingot")),2)
////						.addIngredient(Ingredient.fromTag(ItemTags.getCollection()
////								.getOrCreate(new ResourceLocation(parentMod, "storage_blocks/" + matName)))),
////				new ResourceLocation(Reference.MOD_ID, matName + "ingot1").toString()));
////
////		TCRecipes.shapeless.add(new ImmutablePair<ShapelessRecipeBuilder, String>(
////				ShapelessRecipeBuilder.shapelessRecipe(ingot, 1)// .addIngredient(ForgeRegistries.ITEMS.getValue(new
////						// ResourceLocation(parentMod, matName+"ingot")),2)
////						.addIngredient(Ingredient.fromTag(ItemTags.getCollection()
////								.getOrCreate(new ResourceLocation(parentMod, "nuggets/" + matName))), 9),
////				new ResourceLocation(Reference.MOD_ID, matName + "ingot2").toString()));
////
////		TCRecipes.shapeless.add(new ImmutablePair<ShapelessRecipeBuilder, String>(
////				ShapelessRecipeBuilder.shapelessRecipe(this.itemBlock, 1)// .addIngredient(ForgeRegistries.ITEMS.getValue(new
////						// ResourceLocation(parentMod, matName+"ingot")),2)
////						.addIngredient(Ingredient.fromTag(ItemTags.getCollection()
////								.getOrCreate(new ResourceLocation(parentMod, "ingots/" + matName))), 9),
////				new ResourceLocation(Reference.MOD_ID, matName + "block1").toString()));
//	}
//
//	@Override
//	public void setupItemModels(ModelProvider<ItemModelBuilder> mp, ExistingFileHelper fh) {
//		mp.singleTexture(matName + "ingot", mp.mcLoc("item/generated"), "layer0",
//				mp.modLoc("items/" + matName + "ingot"));
//		mp.singleTexture(matName + "nugget", mp.mcLoc("item/generated"), "layer0",
//				mp.modLoc("items/" + matName + "nugget"));
//		mp.getBuilder(matName + "storageblock").parent(
//				new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "block/" + matName + "block")));
//	}
//
//	@Override
//	public void setupBlockModels(BlockStateProvider bsp, ExistingFileHelper fh) {
//		bsp.simpleBlock(STORAGE_BLOCK.get());
//	}
//
//	@Override
//	public void setupEnglishLocalization(LanguageProvider lang) {
////		lang.add(this.block, StringUtils.capitalise(matName) + " Block");
////		lang.add(this.ingot, StringUtils.capitalise(matName) + " Ingot");
////		lang.add(this.nugget, StringUtils.capitalise(matName) + " Nugget");
//	}
//
//	public class Loot extends BlockLootTables {
//		@Override
//		protected void addTables() {
//			//this.registerLootTable(block, dropping(itemBlock));
//		}
//
//		@Override
//		@Nonnull
//		protected Iterable<Block> getKnownBlocks() {
//			List<Block> l = new ArrayList<Block>();
//			//l.add(block);
//			return l;
//		}
//	}
//
//	@Override
//	public void setupLoot() {
//		TCLootTables.tables.add(Pair.of(Loot::new, LootParameterSets.BLOCK));
//	}

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
