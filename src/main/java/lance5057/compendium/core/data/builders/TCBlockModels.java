package lance5057.compendium.core.data.builders;

import lance5057.compendium.Compendium;
import lance5057.compendium.CompendiumBlocks;
import lance5057.compendium.core.workstations.workstation.WorkstationBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TCBlockModels extends BlockStateProvider {
	private final net.minecraftforge.common.data.ExistingFileHelper fh;

	public TCBlockModels(DataGenerator gen, String modid, ExistingFileHelper fh) {
		super(gen, modid, fh);
		Compendium.logger.info("\t - Block Models");
		this.fh = fh;
	}

	@Override
	protected void registerStatesAndModels() {

//		for(MaterialHelper m : CompendiumMaterials.materials)
//		{
//			m.registerBlockModels(this);
//		}

//		getVariantBuilder(CompendiumBlocks.HAMMERING_STATION.get()).partialState().addModels(ConfiguredModel
//				.allYRotations(models().getExistingFile(modLoc("block/workstations/hammeringtable")), 0, false));
////	getVariantBuilder(CompendiumBlocks.SAWHORSE_STATION.get()).partialState().addModels(ConfiguredModel
////		.allYRotations(models().getExistingFile(modLoc("block/workstations/sawhorse")), 0, false));
////	getVariantBuilder(CompendiumBlocks.CRAFTING_ANVIL.get()).partialState().addModels(
////		ConfiguredModel.allYRotations(models().getExistingFile(modLoc("block/workstations/anvil")), 0, false));
//
////	this.directionalBlock(CompendiumBlocks.HAMMERING_STATION.get(),
////		models().getExistingFile(modLoc("block/workstations/hammeringtable")));
//		this.horizontalBlock(CompendiumBlocks.SAWHORSE_STATION.get(),
//				models().getExistingFile(modLoc("block/workstations/sawhorse")));

		this.workbenchModel(CompendiumBlocks.WORKSTATION.get());

		this.horizontalBlock(CompendiumBlocks.CRAFTING_ANVIL.get(),
				models().getExistingFile(modLoc("block/workstations/anvil")));
		this.horizontalBlock(CompendiumBlocks.SAW_BUCK.get(),
				models().getExistingFile(modLoc("block/workstations/sawbuck")));
//		this.horizontalBlock(CompendiumBlocks.SCRAPPING_TABLE.get(),
//				models().getExistingFile(modLoc("block/workstations/dismantling_table")));
//
//		// this.simpleBlock(CompendiumBlocks.DRYLAKEBED.get());
//		this.simpleBlock(CompendiumBlocks.DRYLAKEBED.get(),
//				this.models().cubeAll(CompendiumBlocks.DRYLAKEBED.get().getRegistryName().getPath(),
//						new ResourceLocation(Reference.MOD_ID, "block/lakebed")));

		this.simpleBlock(CompendiumBlocks.TEST.get());

		// Shingles
//	this.shinglesModel("empty_", "bases/", "minecraft:block/oak_planks", "", "block/bases/empty_shingles",
//		CompendiumBlocks.SHINGLES.get());
//	this.shinglesModel("empty_", "bases/", "minecraft:block/oak_planks", "alt", "block/bases/empty_shingles",
//		CompendiumBlocks.SHINGLES_ALT.get());

//		MetalBlockModels.registerModels(this);
//		GemBlockModels.registerModels(this);
//		OreBlockModels.registerModels(this);
//		ConstructionBlockModels.registerModels(this);
//		CarpentryBlockModels.registerModels(this);

//	for (MaterialHelper mh : CompendiumMaterials.materials) {
//	    // Premade Materials
//	    if (mh.getPremade() != null) {
//		PremadeMaterial pm = mh.getPremade();
//
//		if (pm.STORAGE_BLOCK != null)
//		    simpleBlock(pm.STORAGE_BLOCK.get(),
//			    models().cubeAll(pm.STORAGE_BLOCK.get().getRegistryName().getPath(), new ResourceLocation(
//				    Reference.MOD_ID, "block/material/" + mh.name + "/" + mh.name + "block")));
//	    }
//
//	    // Meltable Materials
//	    if (mh.getIngot() != null) {
//		BasicMetalMaterial mm = mh.getIngot();
//
//		// this.simpleBlock(mm.STORAGE_BLOCK.get());
//		simpleBlock(mm.STORAGE_BLOCK.get(), models().cubeAll(mm.STORAGE_BLOCK.get().getRegistryName().getPath(),
//			new ResourceLocation(Reference.MOD_ID, "block/material/" + mh.name + "/" + mh.name + "block")));
//
//	    }
//
//	    // Craftable Materials
//	    if (mh.getGem() != null) {
//		BasicGemMaterial cm = mh.getGem();
//
//		simpleBlock(cm.STORAGE_BLOCK.get(), models().cubeAll(cm.STORAGE_BLOCK.get().getRegistryName().getPath(),
//			new ResourceLocation(Reference.MOD_ID, "block/material/" + mh.name + "/" + mh.name + "block")));
//	    }
//
//	    // Vanilla Component Materials
//	    if (mh.getVanillaComponents() != null) {
//		MaterialVanillaComponents vc = mh.getVanillaComponents();
//
//		doorBlock(vc.DOOR.get(),
//			new ResourceLocation(mh.parentMod,
//				"block/material/" + mh.name + "/" + mh.name + "_door_bottom"),
//			new ResourceLocation(mh.parentMod, "block/material/" + mh.name + "/" + mh.name + "_door_top"));
//		trapdoorBlock(vc.TRAPDOOR.get(),
//			new ResourceLocation(mh.parentMod, "block/material/" + mh.name + "/" + mh.name + "trapdoor"),
//			true);
//		paneBlock(vc.BARS.get(),
//			new ResourceLocation(mh.parentMod, "block/material/" + mh.name + "/" + mh.name + "bars"),
//			new ResourceLocation(mh.parentMod, "block/material/" + mh.name + "/" + mh.name + "bars"));
//
//		lanternModel(mh);
//		this.axisBlock(vc.CHAIN.get(),
//			models().withExistingParent(mh.name + "chain", mcLoc("block/chain")).texture("all",
//				modLoc("block/material/" + mh.name + "/" + mh.name + "chain")),
//			models().withExistingParent(mh.name + "bigchain", mcLoc("block/chain")).texture("all",
//				modLoc("block/material/" + mh.name + "/" + mh.name + "chain")));// .cross(me.BIGCHAIN.get().getRegistryName().getPath(),
//		// modLoc("block/material/" + mh.name + "/" + mh.name + "bigchain")));
//
//	    }
//
//	    // Extra Component Materials
//	    if (mh.getExtraComponents() != null) {
//		MaterialExtraComponents me = mh.getExtraComponents();
//
//		// Stake
//		stakeModel(mh);
//
//		// Shingles
//		this.shinglesModel(mh, "", me.SHINGLES.get());
//		this.shinglesModel(mh, "alt", me.SHINGLES_ALT.get());
//		simpleBlock(me.SHINGLES_BLOCK.get(),
//			models().cubeAll(me.SHINGLES_BLOCK.get().getRegistryName().getPath(), new ResourceLocation(
//				Reference.MOD_ID, "block/material/" + mh.name + "/" + mh.name + "shingles")));
//
//		// Sheet
//		this.sheetModel(mh);
//		simpleBlock(me.SHEET_BLOCK.get(), models().cubeAll(me.SHEET_BLOCK.get().getRegistryName().getPath(),
//			new ResourceLocation(Reference.MOD_ID, "block/material/" + mh.name + "/" + mh.name + "sheet")));
//
//		// Glass
//		paneBlock(me.TRIMMED_WINDOW.get(),
//			new ResourceLocation(mh.parentMod,
//				"block/material/" + mh.name + "/" + mh.name + "trimmedglass"),
//			new ResourceLocation(mh.parentMod,
//				"block/material/" + mh.name + "/" + mh.name + "trimmedglass"));
//		simpleBlock(me.TRIMMED_WINDOW_BLOCK.get(),
//			models().cubeAll(me.TRIMMED_WINDOW_BLOCK.get().getRegistryName().getPath(),
//				new ResourceLocation(Reference.MOD_ID,
//					"block/material/" + mh.name + "/" + mh.name + "trimmedglass")));
//
//	    }
//
//	    // Advanced Extra Component Materials
//	    if (mh.getAdvancedComponents() != null) {
//		MaterialAdvancedExtraComponents me = mh.getAdvancedComponents();
//
//		//Vault
//		this.horizontalBlock(me.VAULT.get(),
//			models().withExistingParent(mh.name + "vault", modLoc("block/bases/vault"))
//				.texture("0", modLoc("block/material/" + mh.name + "/" + mh.name + "vault_door"))
//				.texture("1", modLoc("block/material/" + mh.name + "/" + mh.name + "vault_sides")));
//		
//		//Statue
//		this.horizontalBlock(me.STATUE.get(),
//			models().withExistingParent(mh.name + "statue", modLoc("block/bases/statue"))
//				.texture("0", modLoc("block/material/" + mh.name + "/" + mh.name + "gemblock")));
//
//		// Big Chain
//		this.axisBlock(me.BIGCHAIN.get(),
//			models().withExistingParent(mh.name + "bigchain", modLoc("block/bases/bigchain")).texture("all",
//				modLoc("block/material/" + mh.name + "/" + mh.name + "bigchain")),
//			models().withExistingParent(mh.name + "bigchain", modLoc("block/bases/bigchain")).texture("all",
//				modLoc("block/material/" + mh.name + "/" + mh.name + "bigchain")));
//
//		// Brazier
//		simpleBlock(me.BRAZIER.get(),
//			models().withExistingParent(mh.name + "brazier", modLoc("block/bases/brazier"))
//				.texture("0", modLoc("block/material/" + mh.name + "/" + mh.name + "topbars"))
//				.texture("2", modLoc("block/material/" + mh.name + "/" + mh.name + "tile"))
//				.texture("3", mcLoc("block/fire_1"))
//				.texture("particle", modLoc("block/material/" + mh.name + "/" + mh.name + "topbars")));
//		simpleBlock(me.SOUL_BRAZIER.get(),
//			models().withExistingParent(mh.name + "soulbrazier", modLoc("block/bases/brazier"))
//				.texture("0", modLoc("block/material/" + mh.name + "/" + mh.name + "topbars"))
//				.texture("1", mcLoc("block/soul_sand"))
//				.texture("2", modLoc("block/material/" + mh.name + "/" + mh.name + "tile"))
//				.texture("3", mcLoc("block/soul_fire_1"))
//				.texture("particle", modLoc("block/material/" + mh.name + "/" + mh.name + "topbars")));
//
//		// Top Bars
//		paneBlock(me.TOP_BARS.get(),
//			new ResourceLocation(mh.parentMod, "block/material/" + mh.name + "/" + mh.name + "topbars"),
//			new ResourceLocation(mh.parentMod, "block/material/" + mh.name + "/" + mh.name + "topbars"));
//
//		// Chainlink
//		paneBlock(me.CHAINLINK_BARS.get(),
//			new ResourceLocation(mh.parentMod, "block/material/" + mh.name + "/" + mh.name + "chainlink"),
//			new ResourceLocation(mh.parentMod, "block/material/" + mh.name + "/" + mh.name + "chainlink"));
//		simpleBlock(me.CHAINLINK_BLOCK.get(),
//			models().cubeAll(me.CHAINLINK_BLOCK.get().getRegistryName().getPath(), new ResourceLocation(
//				Reference.MOD_ID, "block/material/" + mh.name + "/" + mh.name + "chainlink")));
//
//		// Wall
//		// this.wallBlock(me.WALL.get(), modLoc("block/material/" + mh.name + "/" +
//		// mh.name + "wall"));
//		this.wallBlock(me.WALL.get(),
//			models().singleTexture(mh.name + "wall_post", modLoc("block/bases/wall_post"), "wall",
//				modLoc("block/material/" + mh.name + "/" + mh.name + "wall")),
//			models().singleTexture(mh.name + "wall_side", modLoc("block/bases/wall_side"), "wall",
//				modLoc("block/material/" + mh.name + "/" + mh.name + "wall")),
//			models().singleTexture(mh.name + "wall_side_tall", modLoc("block/bases/wall_side_tall"), "wall",
//				modLoc("block/material/" + mh.name + "/" + mh.name + "wall")));
//
//		// Small Tiles
//		simpleBlock(me.SMALL_TILE.get(),
//			models().cubeAll(me.SMALL_TILE.get().getRegistryName().getPath(), new ResourceLocation(
//				Reference.MOD_ID, "block/material/" + mh.name + "/" + mh.name + "smalltile")));
//
//		// Bar Door
//		bardoorModel(mh);
//
//		// Diamond Bars
//		paneBlock(me.DIAMONDBARS.get(),
//			new ResourceLocation(mh.parentMod, "block/material/" + mh.name + "/" + mh.name + "diamondbars"),
//			new ResourceLocation(mh.parentMod,
//				"block/material/" + mh.name + "/" + mh.name + "diamondbars"));
//		paneBlock(me.DIAMONDBARSTOP.get(),
//			new ResourceLocation(mh.parentMod,
//				"block/material/" + mh.name + "/" + mh.name + "diamondbar_top"),
//			new ResourceLocation(mh.parentMod,
//				"block/material/" + mh.name + "/" + mh.name + "diamondbar_top"));
//		paneBlock(me.DIAMONDBARSFLIP.get(),
//			new ResourceLocation(mh.parentMod,
//				"block/material/" + mh.name + "/" + mh.name + "diamondbarsflip"),
//			new ResourceLocation(mh.parentMod,
//				"block/material/" + mh.name + "/" + mh.name + "diamondbarsflip"));
//
//		// Dungeon Tile
//		// dungeontileModel(mh);
//		getVariantBuilder(me.DUNGEON_TILE.get()).partialState().addModels(ConfiguredModel.allRotations(models()
//			.withExistingParent(mh.name + "dungeontile_full", modLoc("block/bases/dungeontile_full"))
//			.texture("0", "compendium:block/material/" + mh.name + "/" + mh.name + "tile"), true))
//			.partialState()
//			.addModels(ConfiguredModel.allRotations(
//				models().withExistingParent(mh.name + "dungeontile_half",
//					modLoc("block/bases/dungeontile_half")).texture("0",
//						"compendium:block/material/" + mh.name + "/" + mh.name + "halftile"),
//				true))
//			.partialState()
//			.addModels(ConfiguredModel.allRotations(
//				models().withExistingParent(mh.name + "dungeontile_quarter",
//					modLoc("block/bases/dungeontile_quarter")).texture("0",
//						"compendium:block/material/" + mh.name + "/" + mh.name + "smalltile"),
//				true));
//
//		// Ladder
//		simpleBlock(me.LADDER.get(),
//			models().cubeAll(me.LADDER.get().getRegistryName().getPath(), new ResourceLocation(
//				Reference.MOD_ID, "block/material/" + mh.name + "/" + mh.name + "ladder")));
//
//		// Encased Glowstone
//		simpleBlock(me.ENCASED_GLOWSTONE.get(),
//			models().withExistingParent(mh.name + "encasedglowstone",
//				modLoc("block/bases/encased_glowstone"))
//				.texture("1", "compendium:block/material/" + mh.name + "/" + mh.name + "lamp_cover"));
//	    }
//
//	    if (mh.getOre() != null) {
//		MaterialOre mo = mh.getOre();
//
////				List<ConfiguredModel> models = new ArrayList<ConfiguredModel>();
////				models.addAll(Arrays.asList();
////				models.addAll(Arrays.asList();
////				models.addAll(Arrays.asList(ConfiguredModel.allRotations(models().withExistingParent(mh.name + "ore", modLoc("block/bases/ore_corner")).texture("1", modLoc("block/material/" + mh.name + "/"+mh.name + "ore")), true)));
//
//		getVariantBuilder(mo.ORE.get()).partialState()
//			.addModels(
//				ConfiguredModel.allRotations(
//					models().withExistingParent(mh.name + "ore", modLoc("block/bases/ore")).texture(
//						"1", modLoc("block/material/" + mh.name + "/" + mh.name + "ore")),
//					true))
//			.partialState()
//			.addModels(ConfiguredModel.allRotations(
//				models().withExistingParent(mh.name + "ore_sparse", modLoc("block/bases/ore_sparse"))
//					.texture("1", modLoc("block/material/" + mh.name + "/" + mh.name + "ore")),
//				true))
//			.partialState()
//			.addModels(ConfiguredModel.allRotations(
//				models().withExistingParent(mh.name + "ore_corner", modLoc("block/bases/ore_corner"))
//					.texture("1", modLoc("block/material/" + mh.name + "/" + mh.name + "ore")),
//				true));
//	    }
//	}
	}

	public void workbenchModel(Block b) {
		ModelFile left = models().getExistingFile(modLoc("block/workstations/workstation_left"));
		ModelFile right = models().getExistingFile(modLoc("block/workstations/workstation_right"));

		VariantBlockStateBuilder builder = getVariantBuilder(b);

		for (Direction dir : WorkstationBlock.FACING.getPossibleValues()) {

			builder.partialState().with(WorkstationBlock.FACING, dir).with(WorkstationBlock.HALF, Half.TOP)
					.modelForState().modelFile(left).rotationY((int) dir.toYRot()+270).addModel()

					.partialState().with(WorkstationBlock.FACING, dir).with(WorkstationBlock.HALF, Half.BOTTOM)
					.modelForState().modelFile(right).rotationY((int) dir.toYRot()+270).addModel();
		}
	}

//    private void bardoorModel(MaterialHelper mh) {
//	ModelFile closedModel = models()
//		.withExistingParent(mh.name + "slidingdoor_closed", modLoc("block/bases/slidingbars_closed"))
//		.texture("0", "compendium:block/material/" + mh.name + "/" + mh.name + "bars");
//	ModelFile openModel = models()
//		.withExistingParent(mh.name + "slidingdoor_open", modLoc("block/bases/slidingbars_open"))
//		.texture("0", "compendium:block/material/" + mh.name + "/" + mh.name + "bars");
//	ModelFile empty = models().withExistingParent(mh.name + "empty", modLoc("block/bases/bar_notches"));
//
//	VariantBlockStateBuilder builder = getVariantBuilder(mh.getAdvancedComponents().BAR_DOOR.get());
//
////		for (Direction dir : ComponentBarDoor.FACING.getAllowedValues()) {
////
////			builder.partialState().with(ComponentBarDoor.FACING, dir).modelForState().modelFile(stakeModel)
////					.rotationX(stakeXRotation(dir)).rotationY(stakeYRotation(dir)).addModel()
////
////					.partialState().with(ComponentBarDoor.FACING, dir).modelForState()
//////				.modelFile(stake)
//////				.rotationX(stakeXRotation(dir))
//////				.rotationY(stakeYRotation(dir))
////					// .nextModel()
////					.modelFile(stakeBaseModel).rotationX(stakeXRotation(dir)).rotationY(stakeYRotation(dir)).addModel();
////		}
//
//	builder.
//	// Bottom Open
//		partialState().with(ComponentBarDoor.HALF, DoubleBlockHalf.LOWER).with(ComponentBarDoor.OPEN, true)
//		.with(ComponentBarDoor.FACING, Direction.EAST).modelForState().modelFile(empty).addModel().
//
//		partialState().with(ComponentBarDoor.HALF, DoubleBlockHalf.LOWER).with(ComponentBarDoor.OPEN, true)
//		.with(ComponentBarDoor.FACING, Direction.WEST).modelForState().modelFile(empty).addModel().
//
//		partialState().with(ComponentBarDoor.HALF, DoubleBlockHalf.LOWER).with(ComponentBarDoor.OPEN, true)
//		.with(ComponentBarDoor.FACING, Direction.SOUTH).modelForState().modelFile(empty).addModel().
//
//		partialState().with(ComponentBarDoor.HALF, DoubleBlockHalf.LOWER).with(ComponentBarDoor.OPEN, true)
//		.with(ComponentBarDoor.FACING, Direction.NORTH).modelForState().modelFile(empty).addModel().
//
//		// Bottom Closed
//		partialState().with(ComponentBarDoor.HALF, DoubleBlockHalf.LOWER).with(ComponentBarDoor.OPEN, false)
//		.with(ComponentBarDoor.FACING, Direction.EAST).modelForState().modelFile(closedModel).rotationY(90)
//		.addModel().
//
//		partialState().with(ComponentBarDoor.HALF, DoubleBlockHalf.LOWER).with(ComponentBarDoor.OPEN, false)
//		.with(ComponentBarDoor.FACING, Direction.WEST).modelForState().modelFile(closedModel).rotationY(90)
//		.addModel().
//
//		partialState().with(ComponentBarDoor.HALF, DoubleBlockHalf.LOWER).with(ComponentBarDoor.OPEN, false)
//		.with(ComponentBarDoor.FACING, Direction.NORTH).modelForState().modelFile(closedModel).addModel().
//
//		partialState().with(ComponentBarDoor.HALF, DoubleBlockHalf.LOWER).with(ComponentBarDoor.OPEN, false)
//		.with(ComponentBarDoor.FACING, Direction.SOUTH).modelForState().modelFile(closedModel).addModel().
//
//		// Top Open
//		partialState().with(ComponentBarDoor.HALF, DoubleBlockHalf.UPPER).with(ComponentBarDoor.OPEN, true)
//		.with(ComponentBarDoor.FACING, Direction.EAST).modelForState().modelFile(openModel).rotationY(90)
//		.addModel().
//
//		partialState().with(ComponentBarDoor.HALF, DoubleBlockHalf.UPPER).with(ComponentBarDoor.OPEN, true)
//		.with(ComponentBarDoor.FACING, Direction.WEST).modelForState().modelFile(openModel).rotationY(90)
//		.addModel().
//
//		partialState().with(ComponentBarDoor.HALF, DoubleBlockHalf.UPPER).with(ComponentBarDoor.OPEN, true)
//		.with(ComponentBarDoor.FACING, Direction.SOUTH).modelForState().modelFile(openModel).addModel().
//
//		partialState().with(ComponentBarDoor.HALF, DoubleBlockHalf.UPPER).with(ComponentBarDoor.OPEN, true)
//		.with(ComponentBarDoor.FACING, Direction.NORTH).modelForState().modelFile(openModel).addModel().
//
//		// Top Closed
//		partialState().with(ComponentBarDoor.HALF, DoubleBlockHalf.UPPER).with(ComponentBarDoor.OPEN, false)
//		.with(ComponentBarDoor.FACING, Direction.EAST).modelForState().modelFile(closedModel).rotationY(90)
//		.addModel().
//
//		partialState().with(ComponentBarDoor.HALF, DoubleBlockHalf.UPPER).with(ComponentBarDoor.OPEN, false)
//		.with(ComponentBarDoor.FACING, Direction.WEST).modelForState().modelFile(closedModel).rotationY(90)
//		.addModel().
//
//		partialState().with(ComponentBarDoor.HALF, DoubleBlockHalf.UPPER).with(ComponentBarDoor.OPEN, false)
//		.with(ComponentBarDoor.FACING, Direction.SOUTH).modelForState().modelFile(closedModel).addModel().
//
//		partialState().with(ComponentBarDoor.HALF, DoubleBlockHalf.UPPER).with(ComponentBarDoor.OPEN, false)
//		.with(ComponentBarDoor.FACING, Direction.NORTH).modelForState().modelFile(closedModel).addModel();
//
////		if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
////			switch (direction) {
////			case EAST:
////			case WEST:
////			default:
////				return flag ? OPEN_ROTATED_AABB : ROTATED_AABB;
////			case SOUTH:
////			case NORTH:
////				return flag ? OPEN_DEFAULT_AABB : DEFAULT_AABB;
////			}
////		} else {
////			switch (direction) {
////			case EAST:
////			case WEST:
////			default:
////				return flag ? OPEN_BOTTOM : ROTATED_AABB;
////			case SOUTH:
////			case NORTH:
////				return flag ? OPEN_BOTTOM : DEFAULT_AABB;
////			}
////		}
//    }
//
//	public void stakeModel(String name, Block b) {
//		ModelFile stakeModel = models().withExistingParent(name + "stake", modLoc("block/bases/componentstake"))
//				.texture("rod", "compendium:block/material/" + name + "/" + name + "stake");
//		ModelFile stakeBaseModel = models()
//				.withExistingParent(name + "stake_base", modLoc("block/bases/componentstake_base"))
//				.texture("rod", "compendium:block/material/" + name + "/" + name + "stake");
//
//		VariantBlockStateBuilder builder = getVariantBuilder(b);
//
//		for (Direction dir : ComponentStake.FACING.getPossibleValues()) {
//
//			builder.partialState().with(ComponentStake.FACING, dir).with(ComponentStake.CONNECTED, true).modelForState()
//					.modelFile(stakeModel).rotationX(stakeXRotation(dir)).rotationY(stakeYRotation(dir)).addModel()
//
//					.partialState().with(ComponentStake.FACING, dir).with(ComponentStake.CONNECTED, false)
//					.modelForState()
////				.modelFile(stake)
////				.rotationX(stakeXRotation(dir))
////				.rotationY(stakeYRotation(dir))
//					// .nextModel()
//					.modelFile(stakeBaseModel).rotationX(stakeXRotation(dir)).rotationY(stakeYRotation(dir)).addModel();
//		}
//	}
//
//	public static void shinglesCapModel(TCBlockModels model, String name, String shingle_texture, String suffix,
//			String parent, CrossCollisionBlock b) {
//		shinglesCapModel(model, name, shingle_texture, shingle_texture, shingle_texture, suffix, parent, b);
//	}
//
//	public static void shinglesCapModel(TCBlockModels model, String name, String shingle_texture, String log_texture,
//			String suffix, String parent, CrossCollisionBlock b) {
//		shinglesCapModel(model, name, shingle_texture, log_texture, log_texture + "_top", suffix, parent, b);
//	}
//
//	public static void shinglesCapModel(TCBlockModels model, String name, String shingle_texture, String log_texture,
//			String log_top_texture, String suffix, String parent, CrossCollisionBlock b) {
//		ModelFile shinglesPostModel = model.models()
//				.withExistingParent(name + "shingles_cap_post" + suffix, model.modLoc(parent + "_post"))
//				.texture("0", shingle_texture).texture("1", log_top_texture).texture("2", log_texture);
//
//		ModelFile shinglesSideModel = model.models()
//				.withExistingParent(name + "shingles_cap_side" + suffix, model.modLoc(parent + "_side"))
//				.texture("0", shingle_texture).texture("1", log_top_texture).texture("2", log_texture);
//
//		ModelFile shinglesBraceModel = model.models()
//				.withExistingParent(name + "shingles_cap_brace" + suffix, model.modLoc(parent + "_brace" + suffix))
//				.texture("0", shingle_texture).texture("1", log_top_texture).texture("2", log_texture);
//		ModelFile shinglesTopModel = model.models()
//				.withExistingParent(name + "shingles_cap_top" + suffix, model.modLoc(parent + "_top"))
//				.texture("0", shingle_texture).texture("1", log_top_texture).texture("2", log_texture);
//
//		// model.fourWayBlock(b, shinglesPostModel, shinglesSideModel);
//
//		MultiPartBlockStateBuilder builder = model.getMultipartBuilder(b).part().modelFile(shinglesPostModel).addModel()
//				.end();
//
//		PipeBlock.PROPERTY_BY_DIRECTION.entrySet().forEach(e -> {
//			Direction dir = e.getKey();
//			if (dir.getAxis().isHorizontal()) {
//				builder.part().modelFile(shinglesSideModel).rotationY((((int) dir.toYRot()) + 180) % 360).uvLock(true)
//						.addModel().condition(e.getValue(), true);
//				builder.part().modelFile(shinglesBraceModel).rotationY((((int) dir.getOpposite().toYRot())) % 360)
//						.uvLock(true).addModel().condition(e.getValue(), false);
//			}
//
//			if (dir == Direction.UP) {
//				builder.part().modelFile(shinglesTopModel).addModel().condition(e.getValue(), true);
//			}
//		});
//	}
//
//	public static void shinglesModel(TCBlockModels model, String name, String shingle_texture, String log_texture,
//			String suffix, String parent, Block b) {
//		ModelFile shinglesModel = model.models()
//				.withExistingParent(name + "shingles" + suffix, model.modLoc(parent + suffix))
//				.texture("0", shingle_texture).texture("1", log_texture + "_top").texture("2", log_texture);
//		ModelFile shinglesInnerModel = model.models()
//				.withExistingParent(name + "shingles_inner" + suffix, model.modLoc(parent + "_inner_corner" + suffix))
//				.texture("0", shingle_texture).texture("1", log_texture + "_top").texture("2", log_texture);
//		ModelFile shinglesOuterModel = model.models()
//				.withExistingParent(name + "shingles_outer" + suffix, model.modLoc(parent + "_outer_corner" + suffix))
//				.texture("0", shingle_texture).texture("1", log_texture + "_top").texture("2", log_texture);
//
//		VariantBlockStateBuilder builder = model.getVariantBuilder(b);
//
//		for (Direction dir : StairBlock.FACING.getPossibleValues()) {
//
//			// Bottom
//			// Straight
//			builder.partialState().with(StairBlock.FACING, dir).with(StairBlock.HALF, Half.BOTTOM)
//					.with(StairBlock.SHAPE, StairsShape.STRAIGHT).modelForState().modelFile(shinglesModel)
//					.rotationY(TCBlockModels.stakeYRotation(dir) - 180).addModel()
//
//					// Inner
//
//					.partialState().with(StairBlock.FACING, dir).with(StairBlock.HALF, Half.BOTTOM)
//					.with(StairBlock.SHAPE, StairsShape.INNER_LEFT).modelForState().modelFile(shinglesInnerModel)
//					.rotationY(TCBlockModels.stakeYRotation(dir) - 180).addModel()
//
//					.partialState().with(StairBlock.FACING, dir).with(StairBlock.HALF, Half.BOTTOM)
//					.with(StairBlock.SHAPE, StairsShape.INNER_RIGHT).modelForState().modelFile(shinglesInnerModel)
//					.rotationY(TCBlockModels.stakeYRotation(dir) - 90).addModel()
//
//					// Outer
//
//					.partialState().with(StairBlock.FACING, dir).with(StairBlock.HALF, Half.BOTTOM)
//					.with(StairBlock.SHAPE, StairsShape.OUTER_LEFT).modelForState().modelFile(shinglesOuterModel)
//					.rotationY(TCBlockModels.stakeYRotation(dir) - 180).addModel()
//
//					.partialState().with(StairBlock.FACING, dir).with(StairBlock.HALF, Half.BOTTOM)
//					.with(StairBlock.SHAPE, StairsShape.OUTER_RIGHT).modelForState().modelFile(shinglesOuterModel)
//					.rotationY(TCBlockModels.stakeYRotation(dir) - 90).addModel()
//
//					// Top
//					.partialState().with(StairBlock.FACING, dir).with(StairBlock.HALF, Half.TOP)
//					.with(StairBlock.SHAPE, StairsShape.STRAIGHT).modelForState().modelFile(shinglesModel)
//					.rotationX(180).rotationY(TCBlockModels.stakeYRotation(dir) - 180).addModel()
//
//					// Inner
//
//					.partialState().with(StairBlock.FACING, dir).with(StairBlock.HALF, Half.TOP)
//					.with(StairBlock.SHAPE, StairsShape.INNER_LEFT).modelForState().modelFile(shinglesInnerModel)
//					.rotationX(180).rotationY(TCBlockModels.stakeYRotation(dir) - 180).addModel()
//
//					.partialState().with(StairBlock.FACING, dir).with(StairBlock.HALF, Half.TOP)
//					.with(StairBlock.SHAPE, StairsShape.INNER_RIGHT).modelForState().modelFile(shinglesInnerModel)
//					.rotationX(180).rotationY(TCBlockModels.stakeYRotation(dir) - 180).addModel()
//
//					// Outer
//
//					.partialState().with(StairBlock.FACING, dir).with(StairBlock.HALF, Half.TOP)
//					.with(StairBlock.SHAPE, StairsShape.OUTER_LEFT).modelForState().modelFile(shinglesOuterModel)
//					.rotationX(180).rotationY(TCBlockModels.stakeYRotation(dir) - 180).addModel()
//
//					.partialState().with(StairBlock.FACING, dir).with(StairBlock.HALF, Half.TOP)
//					.with(StairBlock.SHAPE, StairsShape.OUTER_RIGHT).modelForState().modelFile(shinglesOuterModel)
//					.rotationX(180).rotationY(TCBlockModels.stakeYRotation(dir) - 180).addModel();
//		}
//	}
//
//	public static int stakeXRotation(Direction d) {
//		if (d == Direction.UP)
//			return 0;
//		if (d == Direction.DOWN)
//			return 180;
//		return 90;
//	}
//
//	public static int stakeYRotation(Direction d) {
//		if (d == Direction.UP || d == Direction.DOWN || d == Direction.NORTH)
//			return 0;
//		if (d == Direction.WEST)
//			return 270;
//		if (d == Direction.SOUTH)
//			return 180;
//		return 90;
//	}

//    private void lanternModel(MaterialHelper mh) {
//	ModelFile lanternModel = models().withExistingParent(mh.name + "lantern", modLoc("block/bases/lantern"))
//		.texture("all", "compendium:block/material/" + mh.name + "/" + mh.name + "lantern")
//		.texture("all2", "compendium:block/lanternflame");
//
//	ModelFile lanternhangingModel = models()
//		.withExistingParent(mh.name + "lanternhanging", modLoc("block/bases/hanging_lantern"))
//		.texture("all", "compendium:block/material/" + mh.name + "/" + mh.name + "lantern")
//		.texture("all2", "compendium:block/lanternflame");
//
//	VariantBlockStateBuilder builder = getVariantBuilder(mh.getVanillaComponents().LANTERN.get());
//
//	builder.partialState().with(LanternBlock.HANGING, false).modelForState().modelFile(lanternModel).addModel()
//		.partialState().with(LanternBlock.HANGING, true).modelForState().modelFile(lanternhangingModel)
//		.addModel();
//
//    }

//    private void sheetModel(MaterialHelper mh) {
//	ModelFile sheetBottom = models().withExistingParent(mh.name + "sheet", modLoc("block/bases/carpet"))
//		.texture("all", "compendium:block/material/" + mh.name + "/" + mh.name + "sheet");
//
//	ModelFile sheetTop = models().withExistingParent(mh.name + "sheettop", modLoc("block/bases/carpet_top"))
//		.texture("all", "compendium:block/material/" + mh.name + "/" + mh.name + "sheet");
//
//	VariantBlockStateBuilder builder = getVariantBuilder(mh.getExtraComponents().SHEET.get());
//
//	builder.partialState().with(SlabBlock.TYPE, SlabType.BOTTOM).modelForState().modelFile(sheetBottom).addModel()
//		.partialState().with(SlabBlock.TYPE, SlabType.TOP).modelForState().modelFile(sheetTop).addModel()
//		.partialState().with(SlabBlock.TYPE, SlabType.DOUBLE).modelForState().modelFile(sheetTop).addModel();
//    }
}