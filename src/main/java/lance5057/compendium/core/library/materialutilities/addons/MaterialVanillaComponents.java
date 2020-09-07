package lance5057.compendium.core.library.materialutilities.addons;

import lance5057.compendium.TCItems;
import lance5057.compendium.core.blocks.ComponentDoor;
import lance5057.compendium.core.blocks.ComponentPane;
import lance5057.compendium.core.blocks.ComponentTrapDoor;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.block.Block;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class MaterialVanillaComponents implements MaterialBase {
	String matName;
	String parentMod;

	public static RegistryObject<ComponentDoor> DOOR;
	public static RegistryObject<ComponentTrapDoor> TRAPDOOR;
	public static RegistryObject<ComponentPane> BARS;
	public static RegistryObject<LanternBlock> LANTERN;

	public static RegistryObject<BlockItem> ITEM_DOOR;
	public static RegistryObject<BlockItem> ITEM_TRAPDOOR;
	public static RegistryObject<BlockItem> ITEM_BARS;
	public static RegistryObject<BlockItem> ITEM_LANTERN;

	public MaterialVanillaComponents(MaterialHelper mh) {
		this.matName = mh.name;
		this.parentMod = mh.parentMod;
		
		DOOR = mh.BLOCKS.register(mh.name + "door",
				() -> new ComponentDoor(Block.Properties.create(Material.IRON, MaterialColor.IRON)
						.hardnessAndResistance(5.0F).sound(SoundType.METAL).notSolid()));
		TRAPDOOR = mh.BLOCKS.register(mh.name + "trapdoor", () -> new ComponentTrapDoor(
				Block.Properties.create(Material.IRON).hardnessAndResistance(5.0F).sound(SoundType.METAL).notSolid()));
		BARS = mh.BLOCKS.register(mh.name + "bars",
				() -> new ComponentPane(Block.Properties.create(Material.IRON, MaterialColor.AIR)
						.hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL).notSolid()));
		LANTERN = mh.BLOCKS.register(mh.name + "lantern", () -> new LanternBlock(Block.Properties.create(Material.IRON, MaterialColor.AIR)
						.hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL).notSolid()));
		
		ITEM_DOOR = mh.ITEMS.register(mh.name + "itemdoor",
				() -> new BlockItem(DOOR.get(), new Item.Properties().group(TCItems.TCITEMS)));
		ITEM_TRAPDOOR = mh.ITEMS.register(mh.name + "itemtrapdoor",
				() -> new BlockItem(TRAPDOOR.get(), new Item.Properties().group(TCItems.TCITEMS)));
		ITEM_BARS = mh.ITEMS.register(mh.name + "itembars",
				() -> new BlockItem(BARS.get(), new Item.Properties().group(TCItems.TCITEMS)));
		ITEM_LANTERN  = mh.ITEMS.register(mh.name + "itemlantern",
				() -> new BlockItem(LANTERN.get(), new Item.Properties().group(TCItems.TCITEMS)));

//		TCBlocks.BLOCKS.add(door);
//		TCBlocks.BLOCKS.add(trapdoor);
//		TCBlocks.BLOCKS.add(bars);

//		itemDoor.setRegistryName(new ResourceLocation(Reference.MOD_ID, matName + "dooritem"));
//		itemTrapdoor.setRegistryName(new ResourceLocation(Reference.MOD_ID, matName + "trapdooritem"));
//		itemBars.setRegistryName(new ResourceLocation(Reference.MOD_ID, matName + "barsitem"));

//		TCItems.ITEMS.add(itemDoor);
//		TCItems.ITEMS.add(itemTrapdoor);
//		TCItems.ITEMS.add(itemBars);
	}

	@Override
	public void setupClient(MaterialHelper mat) {
		RenderType cutout = RenderType.getCutout();
		RenderTypeLookup.setRenderLayer(this.BARS.get(), cutout);
		RenderTypeLookup.setRenderLayer(this.DOOR.get(), cutout);
		RenderTypeLookup.setRenderLayer(this.TRAPDOOR.get(), cutout);
		RenderTypeLookup.setRenderLayer(this.LANTERN.get(), cutout);
	}

	@Override
	public void setupModels(MaterialHelper mat) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setup(FMLCommonSetupEvent event) {
		// TODO Auto-generated method stub

	}

////	@Override
////	public void setupWiki(MaterialHelper mat, PrintWriter out) {
////		// TODO Auto-generated method stub
////
////	}
////
////	@Override
////	public void setupItemTags() {
////		// TODO Auto-generated method stub
////
////	}
////
////	@Override
////	public void setupBlockTags() {
////		// TODO Auto-generated method stub
////
////	}
////
////	@Override
////	public void setupRecipes() {
////		// door
////		TCRecipes.shaped
////				.add(new ImmutablePair<ShapedRecipeBuilder, String>(
////						ShapedRecipeBuilder.shapedRecipe(itemDoor)
////								.key('i',
////										ItemTags.getCollection()
////												.getOrCreate(new ResourceLocation(parentMod, "ingots/" + matName)))
////								.patternLine("ii")
////								.patternLine("ii")
////								.patternLine("ii"),
////						new ResourceLocation(Reference.MOD_ID, matName + "door1").toString()));
////
////		TCRecipes.shaped
////				.add(new ImmutablePair<ShapedRecipeBuilder, String>(
////						ShapedRecipeBuilder.shapedRecipe(itemDoor)
////								.key('i',
////										ItemTags.getCollection()
////												.getOrCreate(new ResourceLocation(parentMod, "plates/" + matName)))
////								.patternLine("ii")
////								.patternLine("ii")
////								.patternLine("ii"),
////						new ResourceLocation(Reference.MOD_ID, matName + "door2").toString()));
////
////		// bars
////		TCRecipes.shaped
////				.add(new ImmutablePair<ShapedRecipeBuilder, String>(
////						ShapedRecipeBuilder.shapedRecipe(itemBars, 8)
////								.key('i',
////										ItemTags.getCollection()
////												.getOrCreate(new ResourceLocation(Reference.MOD_ID, "rod/" + matName)))
////								.patternLine("iii")
////								.patternLine("iii"),
////						new ResourceLocation(Reference.MOD_ID, matName + "bars1").toString()));
////
////		TCRecipes.shaped
////				.add(new ImmutablePair<ShapedRecipeBuilder, String>(
////						ShapedRecipeBuilder.shapedRecipe(itemBars, 16)
////								.key('i',
////										ItemTags.getCollection()
////												.getOrCreate(new ResourceLocation(parentMod, "ingots/" + matName)))
////								.patternLine("iii")
////								.patternLine("iii"),
////						new ResourceLocation(Reference.MOD_ID, matName + "bars2").toString()));
////
////		// trapdoor
////		TCRecipes.shaped
////				.add(new ImmutablePair<ShapedRecipeBuilder, String>(
////						ShapedRecipeBuilder.shapedRecipe(itemTrapdoor)
////								.key('i',
////										ItemTags.getCollection()
////												.getOrCreate(new ResourceLocation(parentMod, "ingots/" + matName)))
////								.patternLine("ii")
////								.patternLine("ii"),
////						new ResourceLocation(Reference.MOD_ID, matName + "trapdoor1").toString()));
////
////		TCRecipes.shaped
////				.add(new ImmutablePair<ShapedRecipeBuilder, String>(
////						ShapedRecipeBuilder.shapedRecipe(itemTrapdoor)
////								.key('i',
////										ItemTags.getCollection()
////												.getOrCreate(new ResourceLocation(parentMod, "plates/" + matName)))
////								.patternLine("ii")
////								.patternLine("ii"),
////						new ResourceLocation(Reference.MOD_ID, matName + "trapdoor2").toString()));
////	}
////
////	@Override
////	public void setupItemModels(ModelProvider<ItemModelBuilder> mp, ExistingFileHelper fh) {
////		// TODO Auto-generated method stub
////
////	}
////
////	@Override
////	public void setupBlockModels(BlockStateProvider bsp, ExistingFileHelper fh) {
////		bsp.doorBlock(door, new ResourceLocation(Reference.MOD_ID, "block/" + matName + "_door_bottom"),
////				new ResourceLocation(Reference.MOD_ID, "block/" + matName + "_door_top"));
////		bsp.trapdoorBlock(trapdoor, new ResourceLocation(Reference.MOD_ID, "block/" + matName + "trapdoor"), true);
////		bsp.paneBlock(bars, new ResourceLocation(Reference.MOD_ID, "block/" + matName + "bars"),
////				new ResourceLocation(Reference.MOD_ID, "block/" + matName + "bars"));
////	}
////
////	@Override
////	public void setupEnglishLocalization(LanguageProvider lang) {
////		// TODO Auto-generated method stub
////
////	}
//
//	public class Loot extends BlockLootTables {
//		@Override
//		protected void addTables() {
//			this.registerLootTable(door, dropping(itemDoor));
//			this.registerLootTable(trapdoor, dropping(itemTrapdoor));
//			this.registerLootTable(bars, dropping(itemBars));
//		}
//
//		@Override
//		@Nonnull
//		protected Iterable<Block> getKnownBlocks() {
//			List<Block> l = new ArrayList<Block>();
//			l.add(door);
//			l.add(bars);
//			l.add(trapdoor);
//			return l;
//		}
//	}
//
//	@Override
//	public void setupLoot() {
//		// TODO Auto-generated method stub
//
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
