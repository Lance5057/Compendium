package lance5057.compendium.core.library.materialutilities.addons;

import lance5057.compendium.TCItems;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CraftableMaterial implements MaterialBase {

//	public Item gem = new Item(new Item.Properties().group(TCItems.TCITEMS));;
//	public Item nugget = new Item(new Item.Properties().group(TCItems.TCITEMS));;
//
//	public Block block = new Block(Block.Properties.create(Material.GLASS));
//	public Item itemBlock = new BlockItem(block, new Item.Properties().group(TCItems.TCITEMS));

	public RegistryObject<Item> GEM;
	public RegistryObject<Item> NUGGET;
	public RegistryObject<Block> STORAGE_BLOCK;
	public RegistryObject<BlockNamedItem> STORAGE_ITEMBLOCK;

	public Tag<Item> MATERIAL_GEM;
	public Tag<Item> MATERIAL_NUGGET;
	public Tag<Item> MATERIAL_STORAGE_BLOCK;

	public CraftableMaterial(MaterialHelper mh) {

		GEM = mh.ITEMS.register(mh.name + "gem",
				() -> new Item(new Item.Properties().group(TCItems.TCITEMS)));
		NUGGET = mh.ITEMS.register(mh.name + "nugget",
				() -> new Item(new Item.Properties().group(TCItems.TCITEMS)));

		STORAGE_BLOCK = mh.BLOCKS.register(mh.name + "block", () -> new Block(Block.Properties
				.create(Material.IRON).harvestLevel(1).hardnessAndResistance(3, 4).harvestTool(ToolType.PICKAXE)));
		STORAGE_ITEMBLOCK = mh.ITEMS.register(mh.name + "itemblock",
				() -> new BlockNamedItem(STORAGE_BLOCK.get(), new Item.Properties().group(TCItems.TCITEMS)));

//		MATERIAL_GEM = ItemTags.getCollection().getOrCreate(new ResourceLocation(Reference.MOD_ID, "gems/" + mh.name));
//		MATERIAL_NUGGET = ItemTags.getCollection()
//				.getOrCreate(new ResourceLocation(Reference.MOD_ID, "nuggets/" + mh.name));
//		MATERIAL_STORAGE_BLOCK = ItemTags.getCollection()
//				.getOrCreate(new ResourceLocation(Reference.MOD_ID, "storage_blocks/" + mh.name));

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
//	public void setupItemTags() {
////		TCItemTags.ItemTags.add(new ImmutablePair<Tag<Item>, Item>(MATERIAL_GEM, gem));
////		TCItemTags.ItemTags.add(new ImmutablePair<Tag<Item>, Item>(MATERIAL_NUGGET, nugget));
////		// TCItemTags.ItemTags.add(new ImmutablePair<Tag<Item>,
////		// Item>(Tags.Items.STORAGE_BLOCKS, itemBlock));
////
////		TCItemTags.NestedTags.add(new ImmutablePair<Tag<Item>, Tag<Item>>(Tags.Items.INGOTS, MATERIAL_GEM));
////		TCItemTags.NestedTags.add(new ImmutablePair<Tag<Item>, Tag<Item>>(Tags.Items.NUGGETS, MATERIAL_NUGGET));
//	}
//
//	@Override
//	public void setupBlockTags() {
//		// getBuilder(Tags.Blocks.STORAGE_BLOCKS).add(block);
//
//	}
//
//	@Override
//	public void setupRecipes() {
////		TCRecipes.shapeless.add(new ImmutablePair<ShapelessRecipeBuilder, String>(
////				ShapelessRecipeBuilder.shapelessRecipe(nugget, 9)// .addIngredient(ForgeRegistries.ITEMS.getValue(new
////						// ResourceLocation(parentMod, matName+"ingot")),2)
////						.addIngredient(Ingredient.fromTag(ItemTags.getCollection()
////								.getOrCreate(new ResourceLocation(parentMod, "gems/" + matName)))),
////				new ResourceLocation(Reference.MOD_ID, matName + "nugget1").toString()));
////
////		TCRecipes.shapeless.add(new ImmutablePair<ShapelessRecipeBuilder, String>(
////				ShapelessRecipeBuilder.shapelessRecipe(gem, 9)// .addIngredient(ForgeRegistries.ITEMS.getValue(new
////						// ResourceLocation(parentMod, matName+"ingot")),2)
////						.addIngredient(Ingredient.fromTag(ItemTags.getCollection()
////								.getOrCreate(new ResourceLocation(parentMod, "gems/" + matName)))),
////				new ResourceLocation(Reference.MOD_ID, matName + "ingot1").toString()));
////
////		TCRecipes.shapeless.add(new ImmutablePair<ShapelessRecipeBuilder, String>(
////				ShapelessRecipeBuilder.shapelessRecipe(gem, 1)// .addIngredient(ForgeRegistries.ITEMS.getValue(new
////						// ResourceLocation(parentMod, matName+"ingot")),2)
////						.addIngredient(Ingredient.fromTag(ItemTags.getCollection()
////								.getOrCreate(new ResourceLocation(parentMod, "nuggets/" + matName))), 9),
////				new ResourceLocation(Reference.MOD_ID, matName + "ingot2").toString()));
////
////		TCRecipes.shapeless.add(new ImmutablePair<ShapelessRecipeBuilder, String>(
////				ShapelessRecipeBuilder.shapelessRecipe(this.itemBlock, 1)// .addIngredient(ForgeRegistries.ITEMS.getValue(new
////						// ResourceLocation(parentMod, matName+"ingot")),2)
////						.addIngredient(Ingredient.fromTag(ItemTags.getCollection()
////								.getOrCreate(new ResourceLocation(parentMod, "gems/" + matName))), 9),
////				new ResourceLocation(Reference.MOD_ID, matName + "block1").toString()));
//	}
//
//	@Override
//	public void setupItemModels(ModelProvider<ItemModelBuilder> mp, ExistingFileHelper fh) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void setupBlockModels(BlockStateProvider bsp, ExistingFileHelper fh) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void setupEnglishLocalization(LanguageProvider lang) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void setupLoot() {
//		// TODO Auto-generated method stub
//
//	}

	@Override
	public void setup(FMLCommonSetupEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupItems(MaterialHelper mh) {
		
	}

	@Override
	public void setupBlocks(MaterialHelper mh) {
		
	}
}
