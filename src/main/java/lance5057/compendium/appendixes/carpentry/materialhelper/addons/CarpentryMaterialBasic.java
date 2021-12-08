package lance5057.compendium.appendixes.carpentry.materialhelper.addons;

import java.util.function.Consumer;

import lance5057.compendium.Reference;
import lance5057.compendium.appendixes.carpentry.materialhelper.CarpentryMaterialHelper;
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
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.StandingSignBlock;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.material.Material;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.BoatItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SignItem;
import net.minecraft.item.TallBlockItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

public class CarpentryMaterialBasic implements MaterialBase {
    public RegistryObject<Block> PLANK;
    public RegistryObject<SlabBlock> SLAB;
    public RegistryObject<StairsBlock> STAIRS;
    public RegistryObject<FenceBlock> FENCE;
    public RegistryObject<StandingSignBlock> SIGN;
    public RegistryObject<WallSignBlock> WALL_SIGN;
    public RegistryObject<PressurePlateBlock> PRESSURE_PLATE;
    public RegistryObject<TrapDoorBlock> TRAPDOOR;
    public RegistryObject<FenceGateBlock> GATE;
    public RegistryObject<WoodButtonBlock> BUTTON;
    public RegistryObject<DoorBlock> DOOR;

    public RegistryObject<BlockNamedItem> PLANK_ITEMBLOCK;
    public RegistryObject<BlockNamedItem> SLAB_ITEMBLOCK;
    public RegistryObject<BlockNamedItem> STAIRS_ITEMBLOCK;
    public RegistryObject<BlockNamedItem> FENCE_ITEMBLOCK;
    public RegistryObject<SignItem> SIGN_ITEMBLOCK;
    public RegistryObject<BlockNamedItem> PRESSURE_PLATE_ITEMBLOCK;
    public RegistryObject<BlockNamedItem> TRAPDOOR_ITEMBLOCK;
    public RegistryObject<BlockNamedItem> GATE_ITEMBLOCK;
    public RegistryObject<BlockNamedItem> BUTTON_ITEMBLOCK;
    public RegistryObject<TallBlockItem> DOOR_ITEMBLOCK;

    public RegistryObject<BoatItem> BOAT;

    public CarpentryMaterialBasic(CarpentryMaterialHelper cmh) {

    }

    @Override
    public void setupClient(MaterialHelperBase mat) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setup(MaterialHelperBase mat) {
	PLANK = mat.BLOCKS.register(mat.name + "_plank", () -> new Block(
		Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).harvestTool(ToolType.AXE)));
	SLAB = mat.BLOCKS.register(mat.name + "_slab", () -> new SlabBlock(
		Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).harvestTool(ToolType.AXE)));
	STAIRS = mat.BLOCKS.register(mat.name + "_stairs",
		() -> new StairsBlock(PLANK.get().getDefaultState(), AbstractBlock.Properties.from(PLANK.get())));
	FENCE = mat.BLOCKS.register(mat.name + "_fence",
		() -> new FenceBlock(AbstractBlock.Properties.create(Material.WOOD, PLANK.get().getMaterialColor())
			.hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	SIGN = mat.BLOCKS
		.register(mat.name + "_sign",
			() -> new StandingSignBlock(AbstractBlock.Properties.create(Material.WOOD)
				.doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD),
				WoodType.OAK));
	WALL_SIGN = mat.BLOCKS
		.register(mat.name + "_wallsign",
			() -> new WallSignBlock(
				AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement()
					.hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(SIGN.get()),
				WoodType.OAK));

	PRESSURE_PLATE = mat.BLOCKS.register(mat.name + "_pressure_plate",
		() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
			AbstractBlock.Properties.create(Material.WOOD, PLANK.get().getMaterialColor())
				.doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));

	TRAPDOOR = mat.BLOCKS.register(mat.name + "_trapdoor",
		() -> new TrapDoorBlock(AbstractBlock.Properties.create(Material.WOOD, PLANK.get().getMaterialColor())
			.hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid()));

	GATE = mat.BLOCKS.register(mat.name + "_gate",
		() -> new FenceGateBlock(AbstractBlock.Properties.create(Material.WOOD, PLANK.get().getMaterialColor())
			.hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));

	BUTTON = mat.BLOCKS.register(mat.name + "_button",
		() -> new WoodButtonBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement()
			.hardnessAndResistance(0.5F).sound(SoundType.WOOD)));

	DOOR = mat.BLOCKS.register(mat.name + "_door",
		() -> new DoorBlock(AbstractBlock.Properties.create(Material.WOOD, PLANK.get().getMaterialColor())
			.hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid()));

	PLANK_ITEMBLOCK = mat.ITEMS.register(mat.name + "_plank_itemblock",
		() -> new BlockNamedItem(PLANK.get(), new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));
	SLAB_ITEMBLOCK = mat.ITEMS.register(mat.name + "_slab_itemblock",
		() -> new BlockNamedItem(SLAB.get(), new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));
	STAIRS_ITEMBLOCK = mat.ITEMS.register(mat.name + "_stairs_itemblock", () -> new BlockNamedItem(STAIRS.get(),
		new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));
	FENCE_ITEMBLOCK = mat.ITEMS.register(mat.name + "_fence_itemblock",
		() -> new BlockNamedItem(FENCE.get(), new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));
	SIGN_ITEMBLOCK = mat.ITEMS.register(mat.name + "_sign_itemblock",
		() -> new SignItem(new Item.Properties().maxStackSize(16).group(CarpentryMaterialHelper.GROUP_WOOD),
			SIGN.get(), WALL_SIGN.get()));
	PRESSURE_PLATE_ITEMBLOCK = mat.ITEMS.register(mat.name + "_pressure_plate_itemblock",
		() -> new BlockNamedItem(PRESSURE_PLATE.get(),
			new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));
	TRAPDOOR_ITEMBLOCK = mat.ITEMS.register(mat.name + "_trapdoor_itemblock",
		() -> new BlockNamedItem(TRAPDOOR.get(),
			new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));
	GATE_ITEMBLOCK = mat.ITEMS.register(mat.name + "_gate_itemblock",
		() -> new BlockNamedItem(GATE.get(), new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));
	BUTTON_ITEMBLOCK = mat.ITEMS.register(mat.name + "_button_itemblock", () -> new BlockNamedItem(BUTTON.get(),
		new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));
	DOOR_ITEMBLOCK = mat.ITEMS.register(mat.name + "_door_itemblock",
		() -> new TallBlockItem(DOOR.get(), new Item.Properties().group(CarpentryMaterialHelper.GROUP_WOOD)));
    }

    public static void registerBlockModels(CarpentryMaterialBasic m, TCBlockModels model, String name,
	    String parentMod) {
	model.simpleBlock(m.PLANK.get(), model.models().cubeAll(m.PLANK.get().getRegistryName().getPath(),
		new ResourceLocation(parentMod, "block/" + name + "_planks")));
	model.slabBlock(m.SLAB.get(), new ResourceLocation(parentMod, "block/" + name + "_plank"),
		new ResourceLocation(parentMod, "block/" + name + "_planks"));
	model.stairsBlock(m.STAIRS.get(), new ResourceLocation(parentMod, "block/" + name + "_planks"));
	model.fenceBlock(m.FENCE.get(), new ResourceLocation(parentMod, "block/" + name + "_planks"));
	model.trapdoorBlock(m.TRAPDOOR.get(), new ResourceLocation(parentMod, "block/" + name + "_trapdoor"), true); // TODO
	model.fenceGateBlock(m.GATE.get(), new ResourceLocation(parentMod, "block/" + name + "_planks"));
	model.doorBlock(m.DOOR.get(), new ResourceLocation(parentMod, "block/" + name + "_door_bottom"),
		new ResourceLocation(parentMod, "block/" + name + "_door_top"));// TODO
    }

    public static void registerItemModels(CarpentryMaterialBasic m, TCItemModels model, String name, String parentMod) {
	model.forBlockItem(m.PLANK_ITEMBLOCK, name);
	model.forBlockItem(m.SLAB_ITEMBLOCK, name);
	model.forBlockItem(m.STAIRS_ITEMBLOCK, name);
    }

    public static void addTranslations(CarpentryMaterialBasic m, TCEnglishLoc loc, String capName) {
    }

    public static void registerTags(CarpentryMaterialBasic m, TCItemTags itp, String name) {
    }

    public static void buildLootTable(CarpentryMaterialBasic b, BlockLoot table, String name) {
	table.registerDropSelfLootTable(b.PLANK.get());
	table.registerDropSelfLootTable(b.SLAB.get());
	table.registerDropSelfLootTable(b.BUTTON.get());
	table.registerLootTable(b.DOOR.get(), BlockLootTables.registerDoor(b.DOOR.get()));
	table.registerDropSelfLootTable(b.FENCE.get());
	table.registerDropSelfLootTable(b.GATE.get());
	table.registerDropSelfLootTable(b.PRESSURE_PLATE.get());
	table.registerDropSelfLootTable(b.SIGN.get());
	table.registerDropSelfLootTable(b.STAIRS.get());
	table.registerDropSelfLootTable(b.TRAPDOOR.get());
	table.registerDropSelfLootTable(b.WALL_SIGN.get());
    }

    public static void buildRecipes(CarpentryMaterialBasic m, TCRecipes recipes, Consumer<IFinishedRecipe> consumer,
	    String name, String parentMod) {
    }

    public static void registerBlockTags(CarpentryMaterialBasic m, TCBlockTags btp, String name) {
	btp.getOrCreateBuilder(BlockTags.FENCES).add(m.FENCE.get());
	btp.getOrCreateBuilder(BlockTags.WOODEN_FENCES).add(m.FENCE.get());

	btp.getOrCreateBuilder(BlockTags.PLANKS).add(m.PLANK.get());

	btp.getOrCreateBuilder(BlockTags.BUTTONS).add(m.BUTTON.get());
	btp.getOrCreateBuilder(BlockTags.WOODEN_BUTTONS).add(m.BUTTON.get());

	btp.getOrCreateBuilder(BlockTags.PRESSURE_PLATES).add(m.PRESSURE_PLATE.get());
	btp.getOrCreateBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(m.PRESSURE_PLATE.get());

	btp.getOrCreateBuilder(BlockTags.SLABS).add(m.SLAB.get());
	btp.getOrCreateBuilder(BlockTags.WOODEN_SLABS).add(m.SLAB.get());

	btp.getOrCreateBuilder(BlockTags.STAIRS).add(m.STAIRS.get());
	btp.getOrCreateBuilder(BlockTags.WOODEN_STAIRS).add(m.STAIRS.get());

	btp.getOrCreateBuilder(BlockTags.DOORS).add(m.DOOR.get());
	btp.getOrCreateBuilder(BlockTags.WOODEN_DOORS).add(m.DOOR.get());

	btp.getOrCreateBuilder(BlockTags.TRAPDOORS).add(m.TRAPDOOR.get());
	btp.getOrCreateBuilder(BlockTags.WOODEN_TRAPDOORS).add(m.TRAPDOOR.get());
    }

}
