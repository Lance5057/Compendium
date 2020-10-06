package lance5057.compendium.core.library.materialutilities.addons;

import lance5057.compendium.TCItems;
import lance5057.compendium.core.blocks.BlockShingles;
import lance5057.compendium.core.blocks.ComponentSheet;
import lance5057.compendium.core.blocks.ComponentStake;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class MaterialExtraComponents implements MaterialBase {

	String matName;
	String parentMod;

	public RegistryObject<Item> DUST;
	//public RegistryObject<Item> SHARDS;
	public RegistryObject<Item> PLATE;
	public RegistryObject<Item> COIN;
	public RegistryObject<Item> GEAR;
	public RegistryObject<Item> ROD;
	public RegistryObject<Item> COIL;
	public RegistryObject<Item> SPRING;
	public RegistryObject<Item> CASING;
	public RegistryObject<Item> WIRE;

	public RegistryObject<ComponentStake> STAKE;
	public RegistryObject<Block> SHINGLES_BLOCK;
	public RegistryObject<BlockShingles> SHINGLES;
	public RegistryObject<BlockShingles> SHINGLES_ALT;
	public RegistryObject<ComponentSheet> SHEET;

	public RegistryObject<BlockItem> ITEM_STAKE;
	public RegistryObject<BlockItem> ITEM_SHINGLES_BLOCK;
	public RegistryObject<BlockItem> ITEM_SHINGLES;
	public RegistryObject<BlockItem> ITEM_SHINGLES_ALT;
	public RegistryObject<BlockItem> ITEM_SHEET;

	public MaterialExtraComponents(MaterialHelper mh) {
		this.matName = mh.name;
		this.parentMod = mh.parentMod;

		STAKE = mh.BLOCKS.register(mh.name + "stake", () -> new ComponentStake());
		SHINGLES_BLOCK = mh.BLOCKS.register(mh.name + "shinglesblock", () -> new Block(
				Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL)));
		SHINGLES = mh.BLOCKS.register(mh.name + "shingles",
				() -> new BlockShingles(() -> SHINGLES_BLOCK.get().getDefaultState(), Block.Properties.create(Material.IRON)
						.hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));
		SHINGLES_ALT = mh.BLOCKS.register(mh.name + "shinglesalt",
				() -> new BlockShingles(() -> SHINGLES_BLOCK.get().getDefaultState(), Block.Properties.create(Material.IRON)
						.hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));
		SHEET = mh.BLOCKS.register(mh.name + "sheet", () -> new ComponentSheet(Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL)));

		//DUST = mh.ITEMS.register(mh.name + "dust", () -> new Item(new Item.Properties().group(TCItems.TCITEMS)));

		ITEM_STAKE = mh.ITEMS.register(mh.name + "itemstake",
				() -> new BlockItem(STAKE.get(), new Item.Properties().group(TCItems.TCITEMS)));
		ITEM_SHINGLES_BLOCK = mh.ITEMS.register(mh.name + "itemshinglesblock",
				() -> new BlockItem(SHINGLES_BLOCK.get(), new Item.Properties().group(TCItems.TCITEMS)));
		ITEM_SHINGLES = mh.ITEMS.register(mh.name + "itemshingles",
				() -> new BlockItem(SHINGLES.get(), new Item.Properties().group(TCItems.TCITEMS)));
		ITEM_SHINGLES_ALT = mh.ITEMS.register(mh.name + "itemshinglesalt",
				() -> new BlockItem(SHINGLES_ALT.get(), new Item.Properties().group(TCItems.TCITEMS)));
		ITEM_SHEET = mh.ITEMS.register(mh.name + "itemsheet",
				() -> new BlockItem(SHEET.get(), new Item.Properties().group(TCItems.TCITEMS)));

		DUST = mh.ITEMS.register(mh.name + "dust", () -> new Item(new Item.Properties().group(TCItems.TCITEMS)));
		//SHARDS = mh.ITEMS.register(mh.name + "shards", () -> new Item(new Item.Properties().group(TCItems.TCITEMS)));;
		PLATE = mh.ITEMS.register(mh.name + "plate", () -> new Item(new Item.Properties().group(TCItems.TCITEMS)));;
		COIN = mh.ITEMS.register(mh.name + "coin", () -> new Item(new Item.Properties().group(TCItems.TCITEMS)));;
		GEAR = mh.ITEMS.register(mh.name + "gear", () -> new Item(new Item.Properties().group(TCItems.TCITEMS)));;
		ROD = mh.ITEMS.register(mh.name + "rod", () -> new Item(new Item.Properties().group(TCItems.TCITEMS)));;
		COIL = mh.ITEMS.register(mh.name + "coil", () -> new Item(new Item.Properties().group(TCItems.TCITEMS)));;
		SPRING = mh.ITEMS.register(mh.name + "spring", () -> new Item(new Item.Properties().group(TCItems.TCITEMS)));;
		CASING = mh.ITEMS.register(mh.name + "casing", () -> new Item(new Item.Properties().group(TCItems.TCITEMS)));;
		WIRE = mh.ITEMS.register(mh.name + "wire", () -> new Item(new Item.Properties().group(TCItems.TCITEMS)));;
		
	}

	@Override
	public void setupClient(MaterialHelper mat) {
		RenderType cutout = RenderType.getCutout();
		RenderTypeLookup.setRenderLayer(this.SHINGLES.get(), cutout);
	}

	@Override
	public void setupModels(MaterialHelper mat) {

	}

	@Override
	public void setup(FMLCommonSetupEvent event) {
		// TODO Auto-generated method stub

	}

//	@Override
//	public void setupItemTags() {
//		// TCItemTags.ItemTags.add(new ImmutablePair<Tag<Item>, Item>(MATERIAL_DUST,
//		// dust));
//		TCItemTags.ItemTags.add(new ImmutablePair<Tag<Item>, Item>(MATERIAL_DUST, dust));
//		TCItemTags.ItemTags.add(new ImmutablePair<Tag<Item>, Item>(MATERIAL_PLATE, plate));
//		TCItemTags.ItemTags.add(new ImmutablePair<Tag<Item>, Item>(MATERIAL_COIN, coin));
//		TCItemTags.ItemTags.add(new ImmutablePair<Tag<Item>, Item>(MATERIAL_GEAR, gear));
//		TCItemTags.ItemTags.add(new ImmutablePair<Tag<Item>, Item>(MATERIAL_ROD, rod));
//		TCItemTags.ItemTags.add(new ImmutablePair<Tag<Item>, Item>(MATERIAL_COIL, coil));
//		TCItemTags.ItemTags.add(new ImmutablePair<Tag<Item>, Item>(MATERIAL_SPRING, spring));
//		TCItemTags.ItemTags.add(new ImmutablePair<Tag<Item>, Item>(MATERIAL_CASING, casing));
//		TCItemTags.ItemTags.add(new ImmutablePair<Tag<Item>, Item>(MATERIAL_WIRE, wire));
//
//		TCItemTags.NestedTags.add(new ImmutablePair<Tag<Item>, Tag<Item>>(CompendiumTags.CASING, MATERIAL_CASING));
//		TCItemTags.NestedTags.add(new ImmutablePair<Tag<Item>, Tag<Item>>(CompendiumTags.COIL, MATERIAL_COIL));
//		TCItemTags.NestedTags.add(new ImmutablePair<Tag<Item>, Tag<Item>>(CompendiumTags.COIN, MATERIAL_COIN));
//		TCItemTags.NestedTags.add(new ImmutablePair<Tag<Item>, Tag<Item>>(CompendiumTags.DUST, MATERIAL_DUST));
//		TCItemTags.NestedTags.add(new ImmutablePair<Tag<Item>, Tag<Item>>(CompendiumTags.GEAR, MATERIAL_CASING));
//		TCItemTags.NestedTags.add(new ImmutablePair<Tag<Item>, Tag<Item>>(CompendiumTags.PLATE, MATERIAL_PLATE));
//		TCItemTags.NestedTags.add(new ImmutablePair<Tag<Item>, Tag<Item>>(CompendiumTags.ROD, MATERIAL_ROD));
//		TCItemTags.NestedTags.add(new ImmutablePair<Tag<Item>, Tag<Item>>(CompendiumTags.SPRING, MATERIAL_SPRING));
//		TCItemTags.NestedTags.add(new ImmutablePair<Tag<Item>, Tag<Item>>(CompendiumTags.WIRE, MATERIAL_WIRE));
//
//	}
//
//	@Override
//	public void setupBlockTags() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void setupRecipes() {
//
//		// Plates
//		TCRecipes.shaped
//				.add(new ImmutablePair<ShapedRecipeBuilder, String>(
//						ShapedRecipeBuilder.shapedRecipe(plate, 2)
//								.key('i',
//										ItemTags.getCollection()
//												.getOrCreate(new ResourceLocation(parentMod, "ingots/" + matName)))
//								.patternLine(" i ")
//								.patternLine(" i "),
//						new ResourceLocation(Reference.MOD_ID, matName + "plate1").toString()));
//
//		TCRecipes.shaped
//				.add(new ImmutablePair<ShapedRecipeBuilder, String>(
//						ShapedRecipeBuilder.shapedRecipe(plate, 2)
//								.key('i',
//										ItemTags.getCollection()
//												.getOrCreate(new ResourceLocation(parentMod, "gems/" + matName)))
//								.patternLine(" i ")
//								.patternLine(" i "),
//						new ResourceLocation(Reference.MOD_ID, matName + "plate2").toString()));
//
//		// Springs
//		TCRecipes.shapeless.add(new ImmutablePair<ShapelessRecipeBuilder, String>(
//				ShapelessRecipeBuilder.shapelessRecipe(spring, 1).addIngredient(wire).addIngredient(Items.STICK),
//				new ResourceLocation(Reference.MOD_ID, matName + "spring1").toString()));// .addCriterion(matName
//
//		TCRecipes.shapeless.add(new ImmutablePair<ShapelessRecipeBuilder, String>(
//				ShapelessRecipeBuilder.shapelessRecipe(spring, 2).addIngredient(wire, 2).addIngredient(Items.STICK),
//				new ResourceLocation(Reference.MOD_ID, matName + "spring2").toString()));// .addCriterion(matName
//
//		TCRecipes.shapeless.add(new ImmutablePair<ShapelessRecipeBuilder, String>(
//				ShapelessRecipeBuilder.shapelessRecipe(spring, 3).addIngredient(wire, 3).addIngredient(Items.STICK),
//				new ResourceLocation(Reference.MOD_ID, matName + "spring3").toString()));// .addCriterion(matName
//
//		TCRecipes.shapeless.add(new ImmutablePair<ShapelessRecipeBuilder, String>(
//				ShapelessRecipeBuilder.shapelessRecipe(spring, 4).addIngredient(wire, 4).addIngredient(Items.STICK),
//				new ResourceLocation(Reference.MOD_ID, matName + "spring4").toString()));// .addCriterion(matName
//
//		TCRecipes.shapeless.add(new ImmutablePair<ShapelessRecipeBuilder, String>(
//				ShapelessRecipeBuilder.shapelessRecipe(spring, 5).addIngredient(wire, 5).addIngredient(Items.STICK),
//				new ResourceLocation(Reference.MOD_ID, matName + "spring5").toString()));// .addCriterion(matName
//
//		TCRecipes.shapeless.add(new ImmutablePair<ShapelessRecipeBuilder, String>(
//				ShapelessRecipeBuilder.shapelessRecipe(spring, 6).addIngredient(wire, 6).addIngredient(Items.STICK),
//				new ResourceLocation(Reference.MOD_ID, matName + "spring6").toString()));// .addCriterion(matName
//
//		TCRecipes.shapeless.add(new ImmutablePair<ShapelessRecipeBuilder, String>(
//				ShapelessRecipeBuilder.shapelessRecipe(spring, 7).addIngredient(wire, 7).addIngredient(Items.STICK),
//				new ResourceLocation(Reference.MOD_ID, matName + "spring7").toString()));// .addCriterion(matName
//
//		TCRecipes.shapeless.add(new ImmutablePair<ShapelessRecipeBuilder, String>(
//				ShapelessRecipeBuilder.shapelessRecipe(spring, 8).addIngredient(wire, 8).addIngredient(Items.STICK),
//				new ResourceLocation(Reference.MOD_ID, matName + "spring8").toString()));// .addCriterion(matName
//
//		// coils
//		TCRecipes.shapeless.add(new ImmutablePair<ShapelessRecipeBuilder, String>(
//				ShapelessRecipeBuilder.shapelessRecipe(coil, 1).addIngredient(wire, 4),
//				new ResourceLocation(Reference.MOD_ID, matName + "coil1").toString()));// .addCriterion(matName
//
//		// wires
//		TCRecipes.shapeless.add(new ImmutablePair<ShapelessRecipeBuilder, String>(
//				ShapelessRecipeBuilder.shapelessRecipe(wire, 2).addIngredient(rod, 1),
//				new ResourceLocation(Reference.MOD_ID, matName + "wire1").toString()));
//
//		// rods
//		TCRecipes.shaped
//				.add(new ImmutablePair<ShapedRecipeBuilder, String>(
//						ShapedRecipeBuilder.shapedRecipe(rod, 6)
//								.key('i',
//										ItemTags.getCollection()
//												.getOrCreate(new ResourceLocation(parentMod, "ingots/" + matName)))
//								.patternLine(" i ")
//								.patternLine(" i ")
//								.patternLine(" i "),
//						new ResourceLocation(Reference.MOD_ID, matName + "rod1").toString()));
//
//		TCRecipes.shaped
//				.add(new ImmutablePair<ShapedRecipeBuilder, String>(
//						ShapedRecipeBuilder.shapedRecipe(rod, 6)
//								.key('i',
//										ItemTags.getCollection()
//												.getOrCreate(new ResourceLocation(parentMod, "gems/" + matName)))
//								.patternLine(" i ")
//								.patternLine(" i ")
//								.patternLine(" i "),
//						new ResourceLocation(Reference.MOD_ID, matName + "rod2").toString()));
//
//		// gear
//		TCRecipes.shaped
//				.add(new ImmutablePair<ShapedRecipeBuilder, String>(
//						ShapedRecipeBuilder.shapedRecipe(gear)
//								.key('i',
//										ItemTags.getCollection()
//												.getOrCreate(new ResourceLocation(parentMod, "ingots/" + matName)))
//								.patternLine(" i ")
//								.patternLine("i i")
//								.patternLine(" i "),
//						new ResourceLocation(Reference.MOD_ID, matName + "gear1").toString()));
//
//		// coin
//		TCRecipes.shapeless.add(new ImmutablePair<ShapelessRecipeBuilder, String>(
//				ShapelessRecipeBuilder.shapelessRecipe(coin, 2)
//						.addIngredient(Ingredient.fromTag(ItemTags.getCollection()
//								.getOrCreate(new ResourceLocation(parentMod, "nuggets/" + matName))), 2),
//				new ResourceLocation(Reference.MOD_ID, matName + "coin1").toString()));
//
//		// casing
//		TCRecipes.shaped.add(new ImmutablePair<ShapedRecipeBuilder, String>(
//				ShapedRecipeBuilder.shapedRecipe(casing).key('i', plate).patternLine(" i ").patternLine("i i"),
//				new ResourceLocation(Reference.MOD_ID, matName + "casing1").toString()));
//
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
////		.add(new ImmutablePair<ShapedRecipeBuilder, String>(
////				ShapedRecipeBuilder.shapedRecipe(itemDoor)
////						.key('i',
////								ItemTags.getCollection()
////										.getOrCreate(new ResourceLocation(parentMod, "plates/" + matName)))
////						.patternLine("ii")
////						.patternLine("ii")
////						.patternLine("ii"),
////				new ResourceLocation(Reference.MOD_ID, matName + "door2").toString()));
//
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
////		.add(new ImmutablePair<ShapedRecipeBuilder, String>(
////				ShapedRecipeBuilder.shapedRecipe(itemBars, 16)
////						.key('i',
////								ItemTags.getCollection()
////										.getOrCreate(new ResourceLocation(parentMod, "ingots/" + matName)))
////						.patternLine("iii")
////						.patternLine("iii"),
////				new ResourceLocation(Reference.MOD_ID, matName + "bars2").toString()));
//
//		// stake
//		TCRecipes.shaped
//				.add(new ImmutablePair<ShapedRecipeBuilder, String>(
//						ShapedRecipeBuilder.shapedRecipe(itemStake)
//								.key('i',
//										ItemTags.getCollection()
//												.getOrCreate(new ResourceLocation(Reference.MOD_ID, "rod/" + matName)))
//								.patternLine("i")
//								.patternLine("i"),
//						new ResourceLocation(Reference.MOD_ID, matName + "stake1").toString()));
//
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
////		.add(new ImmutablePair<ShapedRecipeBuilder, String>(
////				ShapedRecipeBuilder.shapedRecipe(itemTrapdoor)
////						.key('i',
////								ItemTags.getCollection()
////										.getOrCreate(new ResourceLocation(parentMod, "plates/" + matName)))
////						.patternLine("ii")
////						.patternLine("ii"),
////				new ResourceLocation(Reference.MOD_ID, matName + "trapdoor2").toString()));
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
////		bsp.doorBlock(door, new ResourceLocation(Reference.MOD_ID, "block/" + matName + "_door_top"),
////				new ResourceLocation(Reference.MOD_ID, "block/" + matName + "_door_bottom"));
////		bsp.trapdoorBlock(trapdoor, new ResourceLocation(Reference.MOD_ID, "block/" + matName + "trapdoor"), true);
////		bsp.paneBlock(bars, new ResourceLocation(Reference.MOD_ID, "block/" + matName + "bars"),
////				new ResourceLocation(Reference.MOD_ID, "block/" + matName + "bars"));
//		// bsp.simpleBlock(stake, new
//		// ModelFile.ExistingModelFile(bsp.modLoc("block/componentstake"), fh));
//		bsp.simpleBlock(this.shinglesblock, bsp.models().cubeAll(shinglesblock.getRegistryName().getPath(), new ResourceLocation(Reference.MOD_ID, "block/" + matName+"shingles")));
//		stakeModel(bsp);
//		shinglesModel(bsp, "", this.shingles);
//		shinglesModel(bsp, "alt", this.shinglesalt);
//	}
//
//	private void stakeModel(BlockStateProvider bsp) {
//		ModelFile stakeModel = bsp.models()
//				.withExistingParent(matName + "componentstake", bsp.modLoc("block/componentstake"))
//				.texture("rod", "compendium:block/" + matName + "stake");
//		ModelFile stakeBaseModel = bsp.models()
//				.withExistingParent(matName + "componentstake_base", bsp.modLoc("block/componentstake_base"))
//				.texture("rod", "compendium:block/" + matName + "stake");
//
//		VariantBlockStateBuilder builder = bsp.getVariantBuilder(this.stake);
//
//		for (Direction dir : ComponentStake.FACING.getAllowedValues()) {
//
//			builder.partialState()
//					.with(ComponentStake.FACING, dir)
//					.with(ComponentStake.CONNECTED, true)
//					.modelForState()
//					.modelFile(stakeModel)
//					.rotationX(stakeXRotation(dir))
//					.rotationY(stakeYRotation(dir))
//					.addModel()
//
//					.partialState()
//					.with(ComponentStake.FACING, dir)
//					.with(ComponentStake.CONNECTED, false)
//					.modelForState()
////					.modelFile(stake)
////					.rotationX(stakeXRotation(dir))
////					.rotationY(stakeYRotation(dir))
//					// .nextModel()
//					.modelFile(stakeBaseModel)
//					.rotationX(stakeXRotation(dir))
//					.rotationY(stakeYRotation(dir))
//					.addModel();
//		}
//	}
//	
//	private void shinglesModel(BlockStateProvider bsp, String suffix, Block b) {
//		ModelFile shinglesModel = bsp.models()
//				.withExistingParent(matName + "shingles" + suffix, bsp.modLoc("block/shingles" + suffix))
//				.texture("0", "compendium:block/" + matName + "shingles")
//				.texture("1", "compendium:block/shingles_log")
//				.texture("2", "minecraft:block/oak_log");
//		ModelFile shinglesInnerModel = bsp.models()
//				.withExistingParent(matName + "shingles_inner" + suffix, bsp.modLoc("block/shingles_inner_corner" + suffix))
//				.texture("0", "compendium:block/" + matName + "shingles")
//				.texture("1", "compendium:block/shingles_log")
//				.texture("2", "minecraft:block/oak_log");
//		ModelFile shinglesOuterModel = bsp.models()
//				.withExistingParent(matName + "shingles_outer" + suffix, bsp.modLoc("block/shingles_outer_corner" + suffix))
//				.texture("0", "compendium:block/" + matName + "shingles")
//				.texture("1", "compendium:block/shingles_log")
//				.texture("2", "minecraft:block/oak_log");
//
//		VariantBlockStateBuilder builder = bsp.getVariantBuilder(b);
//
//		for (Direction dir : StairsBlock.FACING.getAllowedValues()) {
//
//			//Bottom
//					//Straight
//			builder.partialState()
//					.with(StairsBlock.FACING, dir)
//					.with(StairsBlock.HALF, Half.BOTTOM)
//					.with(StairsBlock.SHAPE, StairsShape.STRAIGHT)
//					.modelForState()
//					.modelFile(shinglesModel)
//					.rotationY(stakeYRotation(dir)-180)
//					.addModel()
//					
//					//Inner
//					
//					.partialState()
//					.with(StairsBlock.FACING, dir)
//					.with(StairsBlock.HALF, Half.BOTTOM)
//					.with(StairsBlock.SHAPE, StairsShape.INNER_LEFT)
//					.modelForState()
//					.modelFile(shinglesInnerModel)
//					.rotationY(stakeYRotation(dir)-180)
//					.addModel()
//					
//					.partialState()
//					.with(StairsBlock.FACING, dir)
//					.with(StairsBlock.HALF, Half.BOTTOM)
//					.with(StairsBlock.SHAPE, StairsShape.INNER_RIGHT)
//					.modelForState()
//					.modelFile(shinglesInnerModel)
//					.rotationY(stakeYRotation(dir)-90)
//					.addModel()
//					
//					//Outer
//					
//					.partialState()
//					.with(StairsBlock.FACING, dir)
//					.with(StairsBlock.HALF, Half.BOTTOM)
//					.with(StairsBlock.SHAPE, StairsShape.OUTER_LEFT)
//					.modelForState()
//					.modelFile(shinglesOuterModel)
//					.rotationY(stakeYRotation(dir)-180)
//					.addModel()
//					
//					.partialState()
//					.with(StairsBlock.FACING, dir)
//					.with(StairsBlock.HALF, Half.BOTTOM)
//					.with(StairsBlock.SHAPE, StairsShape.OUTER_RIGHT)
//					.modelForState()
//					.modelFile(shinglesOuterModel)
//					.rotationY(stakeYRotation(dir)-90)
//					.addModel()
//					
//			//Top
//					.partialState()
//					.with(StairsBlock.FACING, dir)
//					.with(StairsBlock.HALF, Half.TOP)
//					.with(StairsBlock.SHAPE, StairsShape.STRAIGHT)
//					.modelForState()
//					.modelFile(shinglesModel)
//					.rotationX(180)
//					.rotationY(stakeYRotation(dir)-180)
//					.addModel()
//					
//					//Inner
//					
//					.partialState()
//					.with(StairsBlock.FACING, dir)
//					.with(StairsBlock.HALF, Half.TOP)
//					.with(StairsBlock.SHAPE, StairsShape.INNER_LEFT)
//					.modelForState()
//					.modelFile(shinglesInnerModel)
//					.rotationX(180)
//					.rotationY(stakeYRotation(dir)-180)
//					.addModel()
//					
//					.partialState()
//					.with(StairsBlock.FACING, dir)
//					.with(StairsBlock.HALF, Half.TOP)
//					.with(StairsBlock.SHAPE, StairsShape.INNER_RIGHT)
//					.modelForState()
//					.modelFile(shinglesInnerModel)
//					.rotationX(180)
//					.rotationY(stakeYRotation(dir)-180)
//					.addModel()
//					
//					//Outer
//					
//					.partialState()
//					.with(StairsBlock.FACING, dir)
//					.with(StairsBlock.HALF, Half.TOP)
//					.with(StairsBlock.SHAPE, StairsShape.OUTER_LEFT)
//					.modelForState()
//					.modelFile(shinglesOuterModel)
//					.rotationX(180)
//					.rotationY(stakeYRotation(dir)-180)
//					.addModel()
//					
//					.partialState()
//					.with(StairsBlock.FACING, dir)
//					.with(StairsBlock.HALF, Half.TOP)
//					.with(StairsBlock.SHAPE, StairsShape.OUTER_RIGHT)
//					.modelForState()
//					.modelFile(shinglesOuterModel)
//					.rotationX(180)
//					.rotationY(stakeYRotation(dir)-180)
//					.addModel();
//		}
//	}
//
//	private int stakeXRotation(Direction d) {
//		if (d == Direction.UP)
//			return 0;
//		if (d == Direction.DOWN)
//			return 180;
//		return 90;
//	}
//
//	private int stakeYRotation(Direction d) {
//		if (d == Direction.UP || d == Direction.DOWN || d == Direction.NORTH)
//			return 0;
//		if (d == Direction.WEST)
//			return 270;
//		if (d == Direction.SOUTH)
//			return 180;
//		return 90;
//	}
//
//	@Override
//	public void setupEnglishLocalization(LanguageProvider lang) {
//		lang.add(this.casing, StringUtils.capitalise(matName) + " Casing"); 
//		lang.add(this.coil, StringUtils.capitalise(matName) + " Coil"); 
//		lang.add(this.coin, StringUtils.capitalise(matName) + " Coin"); 
//		lang.add(this.dust, StringUtils.capitalise(matName) + " Dust"); 
//		lang.add(this.gear, StringUtils.capitalise(matName) + " Gear"); 
//		lang.add(this.plate, StringUtils.capitalise(matName) + " Plate"); 
//		lang.add(this.rod, StringUtils.capitalise(matName) + " Rod"); 
//		lang.add(this.spring, StringUtils.capitalise(matName) + " Spring"); 
//		lang.add(this.wire, StringUtils.capitalise(matName) + " Wire"); 
//	}
//
//	public class Loot extends BlockLootTables {
//		@Override
//		protected void addTables() {
//			this.registerLootTable(stake, dropping(stake));
//		}
//
//		@Override
//		@Nonnull
//		protected Iterable<Block> getKnownBlocks() {
//			List<Block> l = new ArrayList<Block>();
//			l.add(stake);
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
