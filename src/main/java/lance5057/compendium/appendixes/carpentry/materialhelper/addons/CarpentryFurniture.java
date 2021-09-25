package lance5057.compendium.appendixes.carpentry.materialhelper.addons;

import java.util.function.Consumer;

import lance5057.compendium.CompendiumItems;
import lance5057.compendium.Reference;
import lance5057.compendium.appendixes.carpentry.materialhelper.CarpentryMaterialHelper;
import lance5057.compendium.core.blocks.BlockConnected;
import lance5057.compendium.core.blocks.BlockSideConnected;
import lance5057.compendium.core.blocks.BlockSittableConnected;
import lance5057.compendium.core.data.builders.TCBlockModels;
import lance5057.compendium.core.data.builders.TCBlockTags;
import lance5057.compendium.core.data.builders.TCEnglishLoc;
import lance5057.compendium.core.data.builders.TCItemModels;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.data.builders.TCRecipes;
import lance5057.compendium.core.data.builders.loottables.BlockLoot;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.fml.RegistryObject;

public class CarpentryFurniture implements MaterialBase {

    public RegistryObject<BlockSittableConnected> SEAT;
    public RegistryObject<BlockSideConnected> SEAT_BACK;
    public RegistryObject<BlockConnected> TABLE;

    public RegistryObject<BlockNamedItem> SEAT_ITEMBLOCK;
    public RegistryObject<BlockNamedItem> SEAT_BACK_ITEMBLOCK;
    public RegistryObject<BlockNamedItem> TABLE_ITEMBLOCK;

    public CarpentryFurniture(CarpentryMaterialHelper carpentryMaterialHelper) {
	// TODO Auto-generated constructor stub
    }

    @Override
    public void setupClient(MaterialHelperBase mat) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setup(MaterialHelperBase mat) {
	SEAT = mat.BLOCKS.register(mat.name + "_seat", () -> new BlockSittableConnected(AbstractBlock.Properties
		.create(Material.WOOD).hardnessAndResistance(0.3F).notSolid().sound(SoundType.WOOD)));
	SEAT_BACK = mat.BLOCKS.register(mat.name + "_seat_back", () -> new BlockSideConnected(AbstractBlock.Properties
		.create(Material.WOOD).hardnessAndResistance(0.3F).notSolid().sound(SoundType.WOOD)));

	TABLE = mat.BLOCKS.register(mat.name + "_table", () -> new BlockConnected(AbstractBlock.Properties
		.create(Material.WOOD).hardnessAndResistance(0.3F).notSolid().sound(SoundType.WOOD)));

	SEAT_ITEMBLOCK = mat.ITEMS.register(mat.name + "_seat_itemblock",
		() -> new BlockNamedItem(SEAT.get(), new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));
	SEAT_BACK_ITEMBLOCK = mat.ITEMS.register(mat.name + "_seat_back_itemblock",
		() -> new BlockNamedItem(SEAT_BACK.get(),
			new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));
	TABLE_ITEMBLOCK = mat.ITEMS.register(mat.name + "_table_itemblock",
		() -> new BlockNamedItem(TABLE.get(), new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));
    }

    public static void registerBlockModels(CarpentryFurniture m, TCBlockModels model, String name) {
	// Seat

	ModelFile leg = model.models().withExistingParent(name + "leg", model.modLoc("block/bases/seat/stool_leg"))
		.texture("0", model.mcLoc("block/" + name + "_planks"));
	ModelFile seat = model.models().withExistingParent(name + "seat", model.modLoc("block/bases/seat/stool_seat"))
		.texture("0", model.mcLoc("block/" + name + "_planks"));
	ModelFile seat_extension = model.models()
		.withExistingParent(name + "seat_extension", model.modLoc("block/bases/seat/stool_seat_extend"))
		.texture("0", model.mcLoc("block/" + name + "_planks"));

	MultiPartBlockStateBuilder bld = model.getMultipartBuilder(m.SEAT.get());

	bld.part().modelFile(seat).addModel();

	bld.part().modelFile(leg).uvLock(false).rotationY(90).addModel().condition(BlockStateProperties.NORTH, false)
		.condition(BlockStateProperties.EAST, false);
	bld.part().modelFile(leg).uvLock(false).rotationY(0).addModel().condition(BlockStateProperties.NORTH, false)
		.condition(BlockStateProperties.WEST, false);
	bld.part().modelFile(leg).uvLock(false).rotationY(180).addModel().condition(BlockStateProperties.SOUTH, false)
		.condition(BlockStateProperties.EAST, false);
	bld.part().modelFile(leg).uvLock(false).rotationY(-90).addModel().condition(BlockStateProperties.SOUTH, false)
		.condition(BlockStateProperties.WEST, false);

	bld.part().modelFile(seat_extension).uvLock(true).rotationY(0).addModel().condition(BlockStateProperties.NORTH,
		true);
	bld.part().modelFile(seat_extension).uvLock(true).rotationY(-90).addModel().condition(BlockStateProperties.WEST,
		true);
	bld.part().modelFile(seat_extension).uvLock(true).rotationY(180).addModel()
		.condition(BlockStateProperties.SOUTH, true);
	bld.part().modelFile(seat_extension).uvLock(true).rotationY(90).addModel().condition(BlockStateProperties.EAST,
		true);

	// Table

	ModelFile table_leg = model.models()
		.withExistingParent(name + "table_leg", model.modLoc("block/bases/table/leg"))
		.texture("0", model.mcLoc("block/" + name + "_planks"));
	ModelFile table_top = model.models()
		.withExistingParent(name + "table_top", model.modLoc("block/bases/table/table_top"))
		.texture("0", model.mcLoc("block/" + name + "_planks"));

	MultiPartBlockStateBuilder bld_table = model.getMultipartBuilder(m.TABLE.get());

	bld_table.part().modelFile(table_top).addModel();

	bld_table.part().modelFile(table_leg).uvLock(false).rotationY(90).addModel()
		.condition(BlockStateProperties.NORTH, false).condition(BlockStateProperties.EAST, false);
	bld_table.part().modelFile(table_leg).uvLock(false).rotationY(0).addModel()
		.condition(BlockStateProperties.NORTH, false).condition(BlockStateProperties.WEST, false);
	bld_table.part().modelFile(table_leg).uvLock(false).rotationY(180).addModel()
		.condition(BlockStateProperties.SOUTH, false).condition(BlockStateProperties.EAST, false);
	bld_table.part().modelFile(table_leg).uvLock(false).rotationY(-90).addModel()
		.condition(BlockStateProperties.SOUTH, false).condition(BlockStateProperties.WEST, false);

	// Back

	ModelFile backleg = model.models()
		.withExistingParent(name + "backleg", model.modLoc("block/bases/seat/back_leg"))
		.texture("0", model.mcLoc("block/" + name + "_planks"));
	ModelFile backleg2 = model.models()
		.withExistingParent(name + "backleg_other", model.modLoc("block/bases/seat/back_leg_other"))
		.texture("0", model.mcLoc("block/" + name + "_planks"));
	ModelFile back = model.models().withExistingParent(name + "back", model.modLoc("block/bases/seat/back_base"))
		.texture("0", model.mcLoc("block/" + name + "_planks"));
	ModelFile back_extension = model.models()
		.withExistingParent(name + "back_extension", model.modLoc("block/bases/seat/back_extend"))
		.texture("0", model.mcLoc("block/" + name + "_planks"));
	ModelFile back_extension_2 = model.models()
		.withExistingParent(name + "back_extension_other", model.modLoc("block/bases/seat/back_extend_other"))
		.texture("0", model.mcLoc("block/" + name + "_planks"));

	MultiPartBlockStateBuilder bld2 = model.getMultipartBuilder(m.SEAT_BACK.get());

	for (int i = 0; i < 4; i++) {

	    Direction d = Direction.byHorizontalIndex(i);
	    int rot = (int) d.getHorizontalAngle();

	    bld2.part().modelFile(back).rotationY(rot).addModel().condition(BlockStateProperties.HORIZONTAL_FACING, d);

	    bld2.part().modelFile(back_extension_2).uvLock(true).rotationY(rot).addModel()
		    .condition(BlockSideConnected.LEFT, true).condition(BlockStateProperties.HORIZONTAL_FACING, d);
	    bld2.part().modelFile(back_extension).uvLock(true).rotationY(rot).addModel()
		    .condition(BlockSideConnected.RIGHT, true).condition(BlockStateProperties.HORIZONTAL_FACING, d);

	    bld2.part().modelFile(backleg).uvLock(true).rotationY(rot).addModel()
		    .condition(BlockSideConnected.LEFT, false).condition(BlockStateProperties.HORIZONTAL_FACING, d);
	    bld2.part().modelFile(backleg2).uvLock(true).rotationY(rot).addModel()
		    .condition(BlockSideConnected.RIGHT, false).condition(BlockStateProperties.HORIZONTAL_FACING, d);
	}
    }

    public static void registerItemModels(CarpentryFurniture m, TCItemModels model, String name) {
	model.getBuilder(m.TABLE_ITEMBLOCK.getId().getPath())
		.parent(new ModelFile.UncheckedModelFile(model.modLoc("block/bases/table/table_full")))
		.texture("0", model.mcLoc("block/" + name + "_planks"));
	model.getBuilder(m.SEAT_ITEMBLOCK.getId().getPath())
		.parent(new ModelFile.UncheckedModelFile(model.modLoc("block/bases/seat/stool_full")))
		.texture("0", model.mcLoc("block/" + name + "_planks"));
	model.getBuilder(m.SEAT_BACK_ITEMBLOCK.getId().getPath())
		.parent(new ModelFile.UncheckedModelFile(model.modLoc("block/bases/seat/back_full")))
		.texture("0", model.mcLoc("block/" + name + "_planks"));
    }

    public static void addTranslations(CarpentryFurniture m, TCEnglishLoc loc, String capName) {
    }

    public static void registerBlockTags(CarpentryFurniture m, TCBlockTags btp, String name) {
    }

    public static void buildLootTable(CarpentryFurniture b, BlockLoot table, String name) {
	table.registerDropSelfLootTable(b.SEAT.get());
	table.registerDropSelfLootTable(b.SEAT_BACK.get());
	table.registerDropSelfLootTable(b.TABLE.get());
    }

    public static void buildRecipes(CarpentryFurniture m, TCRecipes recipes, Consumer<IFinishedRecipe> consumer,
	    String name) {
	ShapedRecipeBuilder.shapedRecipe(m.SEAT_ITEMBLOCK.get(), 1).key('s', Items.STICK)
		.key('w', Ingredient.fromTag(TCItemTags.ItemTag("planks/" + name))).patternLine("ww").patternLine("ss")
		.addCriterion(name + "seat", TCRecipes.hasItem(Items.STICK))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_make_seat"));

	ShapedRecipeBuilder.shapedRecipe(m.SEAT_BACK_ITEMBLOCK.get(), 1).key('s', Items.STICK)
		.key('w', Ingredient.fromTag(TCItemTags.ItemTag("planks/" + name))).patternLine(" w ")
		.patternLine("s s").addCriterion(name + "seat_back", TCRecipes.hasItem(Items.STICK))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_make_seat_back"));

	ShapedRecipeBuilder.shapedRecipe(m.TABLE_ITEMBLOCK.get(), 1).key('s', Items.STICK)
		.key('w', Ingredient.fromTag(TCItemTags.ItemTag("planks/" + name))).patternLine("www")
		.patternLine("s s").patternLine("s s").addCriterion(name + "table", TCRecipes.hasItem(Items.STICK))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_make_table"));
    }

    public static void registerTags(CarpentryFurniture furniture, TCItemTags itp, String name) {
	// TODO Auto-generated method stub

    }

}
