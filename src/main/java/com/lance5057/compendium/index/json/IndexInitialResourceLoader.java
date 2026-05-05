package com.lance5057.compendium.index.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.slf4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.index.material.MaterialTypeRegistry;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.base.glass.MaterialGlass;
import com.lance5057.compendium.index.material.base.metal.MaterialMetal;
import com.lance5057.compendium.index.material.base.textile.MaterialTextile;
import com.lance5057.compendium.index.material.base.wood.MaterialWood;
import com.lance5057.compendium.index.material.base.wood.locations.SpecialLocationsWood;
import com.lance5057.compendium.index.material.base.wood.locations.SpecialTextureLocationsWood;
import com.lance5057.compendium.index.material.extensions.ExtensionAdvancedTools;
import com.lance5057.compendium.index.material.extensions.wood.ExtensionExtraLogs;
import com.lance5057.compendium.index.material.extensions.wood.ExtensionExtraPlanks;
import com.lance5057.compendium.util.TagUtil;
import com.mojang.logging.LogUtils;

import net.minecraft.world.item.Items;
import net.neoforged.fml.ModList;
import net.neoforged.neoforgespi.language.IModFileInfo;
import net.neoforged.neoforgespi.locating.IModFile;

public class IndexInitialResourceLoader {
	// https://github.com/dyhe83/Gson-Polymorphism-Example/tree/master

	public static final double VERSION = 1.1;
	private static final Logger LOGGER = LogUtils.getLogger();
	private static final Gson GSON = MaterialTypeRegistry.setupGson().setVersion(VERSION).create();

	public static void init() {
//		if (Minecraft.getInstance() != null) { // check if we're in data gen first
		Path resourcePackPath = Path.of("./resourcepacks").resolve("compendium/data/compendium/materials");
		for (MATERIAL_TYPES t : MATERIAL_TYPES.values())
			try {
				Files.createDirectories(resourcePackPath.resolve(t.toString().toLowerCase() + "/"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		buildDefaults();
//		moddedDefaults();
//		readOtherMods();
		readResourcePacks(resourcePackPath);
//		} else {
//			Path resourcePackPath = Path.of(".\\..\\src\\main\\resources\\data\\compendium\\materials");
//			try {
//				Files.walkFileTree(resourcePackPath, new SimpleFileVisitor<Path>() {
//					@Override
//					public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//						Files.delete(file);
//						return FileVisitResult.CONTINUE;
//					}
//				});
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			buildDefaults();
////			moddedDefaults();
//			readResourcePacks(resourcePackPath);
//		}
	}

	static String zipPath = "data/compendium/materials";

	private static void readOtherMods() {
		Stream<Path> paths = ModList.get().getModFiles().stream().map(IModFileInfo::getFile).map(IModFile::getFilePath);
		Collection<URL> urls = paths.map(Path::toUri).map(uri -> {

			URL url = null;
			try {
				url = uri.toURL();
			} catch (MalformedURLException e) {
				Compendium.LOGGER.error("Unable to scan path: " + uri);
				Compendium.LOGGER.error(e);
			}
			return url;
		}).filter(Objects::nonNull).collect(Collectors.toList());

		urls.forEach(url -> {

			try {
				try (ZipFile zipFile = new ZipFile(new File(url.toURI()))) {
					boolean valid = false;
					for (ZipEntry entry : Collections.list(zipFile.entries())) {
						if (entry.getName().contains(zipPath) && entry.getName().endsWith("json")) {

							InputStream stream = zipFile.getInputStream(entry);
							readFile(stream);
							valid = true;
						}
					}

				} catch (URISyntaxException e) {
					Compendium.LOGGER.error("Invalid URL!");
					Compendium.LOGGER.trace(e);

				}
			} catch (IOException e) {
				Compendium.LOGGER.error("Jar not found! Is this a dev enviroment?");
				String s = url.getPath().substring(1);
				read(Path.of(s + "/data/compendium/materials"));
			}
		});

	}

	static void buildDefaults() {
		MaterialMetal iron = new MaterialMetal("iron", "minecraft");
		iron.setupTier("IRON");
		iron.BLOCK.setExists(TagUtil.mcLoc("iron_block"), TagUtil.mcLoc("iron_block"));
		iron.INGOT.setExists(TagUtil.mcLoc("iron_ingot"));
		iron.NUGGET.setExists(TagUtil.mcLoc("iron_nugget"));

		ExtensionAdvancedTools ia = new ExtensionAdvancedTools();
		ia.HAMMER.setGenerate();
		ia.PRYBAR.setGenerate();
		ia.SAW.setGenerate();
		ia.ZWEIHANDER.setGenerate();
		ia.SHEARS.setExists(TagUtil.mcLoc("shears"));
		iron.addExtension(ia);

		buildDefault(iron);

		MaterialMetal gold = new MaterialMetal("gold", "minecraft");
		gold.setupTier("GOLD");
		gold.BLOCK.setExists(TagUtil.mcLoc("gold_block"), TagUtil.mcLoc("gold_block"));
		gold.INGOT.setExists(TagUtil.mcLoc("gold_ingot"));
		gold.NUGGET.setExists(TagUtil.mcLoc("gold_nugget"));

		gold.addExtension(new ExtensionAdvancedTools().generateAll());

		buildDefault(gold);

		MaterialMetal copper = new MaterialMetal("copper", "minecraft");
		copper.setupTier("IRON");
		copper.BLOCK.setExists(TagUtil.mcLoc("copper_block"), TagUtil.mcLoc("copper_block"));
		copper.INGOT.setExists(TagUtil.mcLoc("copper_ingot"));
		copper.NUGGET.setGenerate();

		copper.addExtension(new ExtensionAdvancedTools().generateAll());

		buildDefault(copper);

		MaterialMetal netherite = new MaterialMetal("netherite", "minecraft");
		netherite.setupTier("NETHERITE");
		netherite.BLOCK.setExists(TagUtil.mcLoc("netherite_block"), TagUtil.mcLoc("netherite_block"));
		netherite.INGOT.setExists(TagUtil.mcLoc("netherite_ingot"));
		netherite.NUGGET.setGenerate();

		netherite.addExtension(new ExtensionAdvancedTools().generateAll());

		buildDefault(netherite);

		MaterialGlass glass = new MaterialGlass("glass", "minecraft");
		glass.BLOCK.setExists(TagUtil.mcLoc("glass"), TagUtil.mcLoc("glass"));
		buildDefault(glass);

		MaterialGlass white_stained_glass = new MaterialGlass("white_stained_glass", "minecraft");
		white_stained_glass.BLOCK.setExists(TagUtil.mcLoc("white_stained_glass"), TagUtil.mcLoc("white_stained_glass"));
		buildDefault(white_stained_glass);

		MaterialGlass light_gray_stained_glass = new MaterialGlass("light_gray_stained_glass", "minecraft");
		light_gray_stained_glass.BLOCK.setExists(TagUtil.mcLoc("light_gray_stained_glass"),
				TagUtil.mcLoc("light_gray_stained_glass"));
		buildDefault(light_gray_stained_glass);

		MaterialGlass gray_stained_glass = new MaterialGlass("gray_stained_glass", "minecraft");
		gray_stained_glass.BLOCK.setExists(TagUtil.mcLoc("gray_stained_glass"), TagUtil.mcLoc("gray_stained_glass"));
		buildDefault(gray_stained_glass);

		MaterialGlass black_stained_glass = new MaterialGlass("black_stained_glass", "minecraft");
		black_stained_glass.BLOCK.setExists(TagUtil.mcLoc("black_stained_glass"), TagUtil.mcLoc("black_stained_glass"));
		buildDefault(black_stained_glass);

		MaterialGlass brown_stained_glass = new MaterialGlass("brown_stained_glass", "minecraft");
		brown_stained_glass.BLOCK.setExists(TagUtil.mcLoc("brown_stained_glass"), TagUtil.mcLoc("brown_stained_glass"));
		buildDefault(brown_stained_glass);

		MaterialGlass red_stained_glass = new MaterialGlass("red_stained_glass", "minecraft");
		red_stained_glass.BLOCK.setExists(TagUtil.mcLoc("red_stained_glass"), TagUtil.mcLoc("red_stained_glass"));
		buildDefault(red_stained_glass);

		MaterialGlass orange_stained_glass = new MaterialGlass("orange_stained_glass", "minecraft");
		orange_stained_glass.BLOCK.setExists(TagUtil.mcLoc("orange_stained_glass"),
				TagUtil.mcLoc("orange_stained_glass"));
		buildDefault(orange_stained_glass);

		MaterialGlass yellow_stained_glass = new MaterialGlass("yellow_stained_glass", "minecraft");
		yellow_stained_glass.BLOCK.setExists(TagUtil.mcLoc("yellow_stained_glass"),
				TagUtil.mcLoc("yellow_stained_glass"));
		buildDefault(yellow_stained_glass);

		MaterialGlass lime_stained_glass = new MaterialGlass("lime_stained_glass", "minecraft");
		lime_stained_glass.BLOCK.setExists(TagUtil.mcLoc("lime_stained_glass"), TagUtil.mcLoc("lime_stained_glass"));
		buildDefault(lime_stained_glass);

		MaterialGlass green_stained_glass = new MaterialGlass("green_stained_glass", "minecraft");
		green_stained_glass.BLOCK.setExists(TagUtil.mcLoc("green_stained_glass"), TagUtil.mcLoc("green_stained_glass"));
		buildDefault(green_stained_glass);

		MaterialGlass cyan_stained_glass = new MaterialGlass("cyan_stained_glass", "minecraft");
		cyan_stained_glass.BLOCK.setExists(TagUtil.mcLoc("cyan_stained_glass"), TagUtil.mcLoc("cyan_stained_glass"));
		buildDefault(cyan_stained_glass);

		MaterialGlass light_blue_stained_glass = new MaterialGlass("light_blue_stained_glass", "minecraft");
		light_blue_stained_glass.BLOCK.setExists(TagUtil.mcLoc("light_blue_stained_glass"),
				TagUtil.mcLoc("light_blue_stained_glass"));
		buildDefault(light_blue_stained_glass);

		MaterialGlass blue_stained_glass = new MaterialGlass("blue_stained_glass", "minecraft");
		blue_stained_glass.BLOCK.setExists(TagUtil.mcLoc("blue_stained_glass"), TagUtil.mcLoc("blue_stained_glass"));
		buildDefault(blue_stained_glass);

		MaterialGlass purple_stained_glass = new MaterialGlass("purple_stained_glass", "minecraft");
		purple_stained_glass.BLOCK.setExists(TagUtil.mcLoc("purple_stained_glass"),
				TagUtil.mcLoc("purple_stained_glass"));
		buildDefault(purple_stained_glass);

		MaterialGlass magenta_stained_glass = new MaterialGlass("magenta_stained_glass", "minecraft");
		magenta_stained_glass.BLOCK.setExists(TagUtil.mcLoc("magenta_stained_glass"),
				TagUtil.mcLoc("magenta_stained_glass"));
		buildDefault(magenta_stained_glass);

		MaterialGlass pink_stained_glass = new MaterialGlass("pink_stained_glass", "minecraft");
		pink_stained_glass.BLOCK.setExists(TagUtil.mcLoc("pink_stained_glass"), TagUtil.mcLoc("pink_stained_glass"));
		buildDefault(pink_stained_glass);

//		buildDefault(new MaterialGlass("tinted", "minecraft"));

		MaterialWood oak = new MaterialWood("oak", "minecraft");
		oak.LOG.setExists(TagUtil.mcLoc("oak_log"), TagUtil.mcLoc("oak_log"));
		oak.PLANKS.setExists(TagUtil.mcLoc("oak_planks"), TagUtil.mcLoc("oak_planks"));
		oak.STRIPPED_LOG.setExists(TagUtil.mcLoc("stripped_oak_log"), TagUtil.mcLoc("stripped_oak_log"));
		oak.WOOD.setExists(TagUtil.mcLoc("oak_wood"), TagUtil.mcLoc("oak_wood"));
		oak.STRIPPED_WOOD.setExists(TagUtil.mcLoc("stripped_oak_wood"), TagUtil.mcLoc("stripped_oak_wood"));

		SpecialLocationsWood oak_loc = new SpecialLocationsWood(
				new SpecialTextureLocationsWood(TagUtil.mcLoc("block/oak_planks"), TagUtil.mcLoc("block/oak_log"),
						TagUtil.mcLoc("block/stripped_oak_log"), TagUtil.mcLoc("block/oak_log_top"),
						TagUtil.mcLoc("block/stripped_oak_log_top")));

		oak.specialLocations = oak_loc;

		oak.addExtension(new ExtensionExtraLogs().generateAll());
		oak.addExtension(new ExtensionExtraPlanks().generateAll());
		buildDefault(oak);

		MaterialWood birch = new MaterialWood("birch", "minecraft");
		birch.LOG.setExists(TagUtil.mcLoc("birch_log"), TagUtil.mcLoc("birch_log"));
		birch.PLANKS.setExists(TagUtil.mcLoc("birch_planks"), TagUtil.mcLoc("birch_planks"));
		birch.STRIPPED_LOG.setExists(TagUtil.mcLoc("stripped_birch_log"), TagUtil.mcLoc("stripped_birch_log"));
		birch.WOOD.setExists(TagUtil.mcLoc("birch_wood"), TagUtil.mcLoc("birch_wood"));
		birch.STRIPPED_WOOD.setExists(TagUtil.mcLoc("stripped_birch_wood"), TagUtil.mcLoc("stripped_birch_wood"));

		SpecialLocationsWood birch_loc = new SpecialLocationsWood(
				new SpecialTextureLocationsWood(TagUtil.mcLoc("block/birch_planks"), TagUtil.mcLoc("block/birch_log"),
						TagUtil.mcLoc("block/stripped_birch_log"), TagUtil.mcLoc("block/birch_log_top"),
						TagUtil.mcLoc("block/stripped_birch_log_top")));

		birch.specialLocations = birch_loc;

		birch.addExtension(new ExtensionExtraLogs().generateAll());
		birch.addExtension(new ExtensionExtraPlanks().generateAll());
		buildDefault(birch);

		MaterialWood spruce = new MaterialWood("spruce", "minecraft");
		spruce.LOG.setExists(TagUtil.mcLoc("spruce_log"), TagUtil.mcLoc("spruce_log"));
		spruce.PLANKS.setExists(TagUtil.mcLoc("spruce_planks"), TagUtil.mcLoc("spruce_planks"));
		spruce.STRIPPED_LOG.setExists(TagUtil.mcLoc("stripped_spruce_log"), TagUtil.mcLoc("stripped_spruce_log"));
		spruce.WOOD.setExists(TagUtil.mcLoc("spruce_wood"), TagUtil.mcLoc("spruce_wood"));
		spruce.STRIPPED_WOOD.setExists(TagUtil.mcLoc("stripped_spruce_wood"), TagUtil.mcLoc("stripped_spruce_wood"));

		SpecialLocationsWood spruce_loc = new SpecialLocationsWood(
				new SpecialTextureLocationsWood(TagUtil.mcLoc("block/spruce_planks"), TagUtil.mcLoc("block/spruce_log"),
						TagUtil.mcLoc("block/stripped_spruce_log"), TagUtil.mcLoc("block/spruce_log_top"),
						TagUtil.mcLoc("block/stripped_spruce_log_top")));

		spruce.specialLocations = spruce_loc;

		spruce.addExtension(new ExtensionExtraLogs().generateAll());
		spruce.addExtension(new ExtensionExtraPlanks().generateAll());
		buildDefault(spruce);

		MaterialWood jungle = new MaterialWood("jungle", "minecraft");
		jungle.LOG.setExists(TagUtil.mcLoc("jungle_log"), TagUtil.mcLoc("jungle_log"));
		jungle.PLANKS.setExists(TagUtil.mcLoc("jungle_planks"), TagUtil.mcLoc("jungle_planks"));
		jungle.STRIPPED_LOG.setExists(TagUtil.mcLoc("stripped_jungle_log"), TagUtil.mcLoc("stripped_jungle_log"));
		jungle.WOOD.setExists(TagUtil.mcLoc("jungle_wood"), TagUtil.mcLoc("jungle_wood"));
		jungle.STRIPPED_WOOD.setExists(TagUtil.mcLoc("stripped_jungle_wood"), TagUtil.mcLoc("stripped_jungle_wood"));

		SpecialLocationsWood jungle_loc = new SpecialLocationsWood(
				new SpecialTextureLocationsWood(TagUtil.mcLoc("block/jungle_planks"), TagUtil.mcLoc("block/jungle_log"),
						TagUtil.mcLoc("block/stripped_jungle_log"), TagUtil.mcLoc("block/jungle_log_top"),
						TagUtil.mcLoc("block/stripped_jungle_log_top")));

		jungle.specialLocations = jungle_loc;

		jungle.addExtension(new ExtensionExtraLogs().generateAll());
		jungle.addExtension(new ExtensionExtraPlanks().generateAll());
		buildDefault(jungle);

		MaterialWood acacia = new MaterialWood("acacia", "minecraft");
		acacia.LOG.setExists(TagUtil.mcLoc("acacia_log"), TagUtil.mcLoc("acacia_log"));
		acacia.PLANKS.setExists(TagUtil.mcLoc("acacia_planks"), TagUtil.mcLoc("acacia_planks"));
		acacia.STRIPPED_LOG.setExists(TagUtil.mcLoc("stripped_acacia_log"), TagUtil.mcLoc("stripped_acacia_log"));
		acacia.WOOD.setExists(TagUtil.mcLoc("acacia_wood"), TagUtil.mcLoc("acacia_wood"));
		acacia.STRIPPED_WOOD.setExists(TagUtil.mcLoc("stripped_acacia_wood"), TagUtil.mcLoc("stripped_acacia_wood"));

		SpecialLocationsWood acacia_loc = new SpecialLocationsWood(
				new SpecialTextureLocationsWood(TagUtil.mcLoc("block/acacia_planks"), TagUtil.mcLoc("block/acacia_log"),
						TagUtil.mcLoc("block/stripped_acacia_log"), TagUtil.mcLoc("block/acacia_log_top"),
						TagUtil.mcLoc("block/stripped_acacia_log_top")));

		acacia.specialLocations = acacia_loc;

		acacia.addExtension(new ExtensionExtraLogs().generateAll());
		acacia.addExtension(new ExtensionExtraPlanks().generateAll());
		buildDefault(acacia);

		MaterialWood dark_oak = new MaterialWood("dark_oak", "minecraft");
		dark_oak.LOG.setExists(TagUtil.mcLoc("dark_oak_log"), TagUtil.mcLoc("dark_oak_log"));
		dark_oak.PLANKS.setExists(TagUtil.mcLoc("dark_oak_planks"), TagUtil.mcLoc("dark_oak_planks"));
		dark_oak.STRIPPED_LOG.setExists(TagUtil.mcLoc("stripped_dark_oak_log"), TagUtil.mcLoc("stripped_dark_oak_log"));
		dark_oak.WOOD.setExists(TagUtil.mcLoc("dark_oak_wood"), TagUtil.mcLoc("dark_oak_wood"));
		dark_oak.STRIPPED_WOOD.setExists(TagUtil.mcLoc("stripped_dark_oak_wood"),
				TagUtil.mcLoc("stripped_dark_oak_wood"));

		SpecialLocationsWood dark_oak_loc = new SpecialLocationsWood(
				new SpecialTextureLocationsWood(TagUtil.mcLoc("block/dark_oak_planks"),
						TagUtil.mcLoc("block/dark_oak_log"), TagUtil.mcLoc("block/stripped_dark_oak_log"),
						TagUtil.mcLoc("block/dark_oak_log_top"), TagUtil.mcLoc("block/stripped_dark_oak_log_top")));

		dark_oak.specialLocations = dark_oak_loc;

		dark_oak.addExtension(new ExtensionExtraLogs().generateAll());
		dark_oak.addExtension(new ExtensionExtraPlanks().generateAll());
		buildDefault(dark_oak);

		MaterialWood mangrove = new MaterialWood("mangrove", "minecraft");
		mangrove.LOG.setExists(TagUtil.mcLoc("mangrove_log"), TagUtil.mcLoc("mangrove_log"));
		mangrove.PLANKS.setExists(TagUtil.mcLoc("mangrove_planks"), TagUtil.mcLoc("mangrove_planks"));
		mangrove.STRIPPED_LOG.setExists(TagUtil.mcLoc("stripped_mangrove_log"), TagUtil.mcLoc("stripped_mangrove_log"));
		mangrove.WOOD.setExists(TagUtil.mcLoc("mangrove_wood"), TagUtil.mcLoc("mangrove_wood"));
		mangrove.STRIPPED_WOOD.setExists(TagUtil.mcLoc("stripped_mangrove_wood"),
				TagUtil.mcLoc("stripped_mangrove_wood"));

		SpecialLocationsWood mangrove_loc = new SpecialLocationsWood(
				new SpecialTextureLocationsWood(TagUtil.mcLoc("block/mangrove_planks"),
						TagUtil.mcLoc("block/mangrove_log"), TagUtil.mcLoc("block/stripped_mangrove_log"),
						TagUtil.mcLoc("block/mangrove_log_top"), TagUtil.mcLoc("block/stripped_mangrove_log_top")));

		mangrove.specialLocations = mangrove_loc;

		mangrove.addExtension(new ExtensionExtraLogs().generateAll());
		mangrove.addExtension(new ExtensionExtraPlanks().generateAll());
		buildDefault(mangrove);

		MaterialWood cherry = new MaterialWood("cherry", "minecraft");
		cherry.LOG.setExists(TagUtil.mcLoc("cherry_log"), TagUtil.mcLoc("cherry_log"));
		cherry.PLANKS.setExists(TagUtil.mcLoc("cherry_planks"), TagUtil.mcLoc("cherry_planks"));
		cherry.STRIPPED_LOG.setExists(TagUtil.mcLoc("stripped_cherry_log"), TagUtil.mcLoc("stripped_cherry_log"));
		cherry.WOOD.setExists(TagUtil.mcLoc("cherry_wood"), TagUtil.mcLoc("cherry_wood"));
		cherry.STRIPPED_WOOD.setExists(TagUtil.mcLoc("stripped_cherry_wood"), TagUtil.mcLoc("stripped_cherry_wood"));

		SpecialLocationsWood cherry_loc = new SpecialLocationsWood(
				new SpecialTextureLocationsWood(TagUtil.mcLoc("block/cherry_planks"), TagUtil.mcLoc("block/cherry_log"),
						TagUtil.mcLoc("block/stripped_cherry_log"), TagUtil.mcLoc("block/cherry_log_top"),
						TagUtil.mcLoc("block/stripped_cherry_log_top")));

		cherry.specialLocations = cherry_loc;

		cherry.addExtension(new ExtensionExtraLogs().generateAll());
		cherry.addExtension(new ExtensionExtraPlanks().generateAll());
		buildDefault(cherry);

		MaterialWood crimson = new MaterialWood("crimson", "minecraft");
		crimson.LOG.setExists(TagUtil.mcLoc("crimson_stem"), TagUtil.mcLoc("crimson_stem"));
		crimson.PLANKS.setExists(TagUtil.mcLoc("crimson_planks"), TagUtil.mcLoc("crimson_planks"));
		crimson.STRIPPED_LOG.setExists(TagUtil.mcLoc("stripped_crimson_stem"), TagUtil.mcLoc("stripped_crimson_stem"));
		crimson.WOOD.setExists(TagUtil.mcLoc("crimson_hyphae"), TagUtil.mcLoc("crimson_hyphae"));
		crimson.STRIPPED_WOOD.setExists(TagUtil.mcLoc("stripped_crimson_hyphae"),
				TagUtil.mcLoc("stripped_crimson_hyphae"));

		SpecialLocationsWood crimson_loc = new SpecialLocationsWood(
				new SpecialTextureLocationsWood(TagUtil.mcLoc("block/crimson_planks"),
						TagUtil.mcLoc("block/crimson_stem"), TagUtil.mcLoc("block/stripped_crimson_stem"),
						TagUtil.mcLoc("block/crimson_stem_top"), TagUtil.mcLoc("block/stripped_crimson_stem_top")));

		crimson.specialLocations = crimson_loc;

		crimson.addExtension(new ExtensionExtraLogs().generateAll());
		crimson.addExtension(new ExtensionExtraPlanks().generateAll());
		buildDefault(crimson);

		MaterialWood warped = new MaterialWood("warped", "minecraft");
		warped.LOG.setExists(TagUtil.mcLoc("warped_stem"), TagUtil.mcLoc("warped_stem"));
		warped.PLANKS.setExists(TagUtil.mcLoc("warped_planks"), TagUtil.mcLoc("warped_planks"));
		warped.STRIPPED_LOG.setExists(TagUtil.mcLoc("stripped_warped_stem"), TagUtil.mcLoc("stripped_warped_stem"));
		warped.WOOD.setExists(TagUtil.mcLoc("warped_hyphae"), TagUtil.mcLoc("warped_hyphae"));
		warped.STRIPPED_WOOD.setExists(TagUtil.mcLoc("stripped_warped_hyphae"),
				TagUtil.mcLoc("stripped_warped_hyphae"));

		SpecialLocationsWood warped_loc = new SpecialLocationsWood(
				new SpecialTextureLocationsWood(TagUtil.mcLoc("block/warped_planks"),
						TagUtil.mcLoc("block/warped_stem"), TagUtil.mcLoc("block/stripped_warped_stem"),
						TagUtil.mcLoc("block/warped_stem_top"), TagUtil.mcLoc("block/stripped_warped_stem_top")));

		warped.specialLocations = warped_loc;

		warped.addExtension(new ExtensionExtraLogs().generateAll());
		warped.addExtension(new ExtensionExtraPlanks().generateAll());
		buildDefault(warped);

//		MaterialWood pale_oak = new MaterialWood("pale_oak", "compendium");
//		pale_oak.LOG.setGenerate();
//		pale_oak.PLANKS.setGenerate();
//		pale_oak.STRIPPED_LOG.setGenerate();
//		pale_oak.WOOD.setGenerate();
//		pale_oak.STRIPPED_WOOD.setGenerate();
//
//		pale_oak.addExtension(new ExtensionExtraLogs().generateAll());
//		pale_oak.addExtension(new ExtensionExtraPlanks().generateAll());
//		buildDefault(pale_oak);

//		buildDefault(new MaterialWood("bamboo", false).addExtension(new ExtensionExtraLogs(true, true, true, true)));

//		buildDefault(new MaterialStone("stone", false, false, false).addExtension(new ExtensionStoneStyleBlocks(true)));
//		buildDefault(
//				new MaterialStone("andesite", false, false, false).addExtension(new ExtensionStoneStyleBlocks(true)));
//		buildDefault(
//				new MaterialStone("granite", false, false, false).addExtension(new ExtensionStoneStyleBlocks(true)));
//		buildDefault(
//				new MaterialStone("diorite", false, false, false).addExtension(new ExtensionStoneStyleBlocks(true)));
//		buildDefault(
//				new MaterialStone("basalt", false, false, false).addExtension(new ExtensionStoneStyleBlocks(true)));
//		buildDefault(
//				new MaterialStone("blackstone", false, false, false).addExtension(new ExtensionStoneStyleBlocks(true)));
//		buildDefault(
//				new MaterialStone("calcite", false, false, false).addExtension(new ExtensionStoneStyleBlocks(true)));
//		buildDefault(
//				new MaterialStone("deepslate", false, false, false).addExtension(new ExtensionStoneStyleBlocks(true)));
//		buildDefault(
//				new MaterialStone("dripstone", false, false, false).addExtension(new ExtensionStoneStyleBlocks(true)));
//		buildDefault(
//				new MaterialStone("endstone", false, false, false).addExtension(new ExtensionStoneStyleBlocks(true)));
//		buildDefault(
//				new MaterialStone("purpur", false, false, false).addExtension(new ExtensionStoneStyleBlocks(true)));
//		buildDefault(new MaterialStone("red_sandstone", false, false, false)
//				.addExtension(new ExtensionStoneStyleBlocks(true)));
//		buildDefault(
//				new MaterialStone("sandstone", false, false, false).addExtension(new ExtensionStoneStyleBlocks(true)));
//		buildDefault(new MaterialStone("tuff", false, false, false).addExtension(new ExtensionStoneStyleBlocks(true)));

		MaterialTextile white_wool = new MaterialTextile("white_wool", "minecraft");
		white_wool.BLOCK.setExists(TagUtil.mcLoc("white_wool"), TagUtil.mcLoc("white_wool"));
		white_wool.CARPET.setExists(TagUtil.mcLoc("white_carpet"), TagUtil.mcLoc("white_carpet"));
		white_wool.STRING.setIgnored();
		buildDefault(white_wool);

		MaterialTextile light_gray_wool = new MaterialTextile("light_gray_wool", "minecraft");
		light_gray_wool.BLOCK.setExists(TagUtil.mcLoc("light_gray_wool"), TagUtil.mcLoc("light_gray_wool"));
		light_gray_wool.CARPET.setExists(TagUtil.mcLoc("light_gray_carpet"), TagUtil.mcLoc("light_gray_carpet"));
		light_gray_wool.STRING.setIgnored();
		buildDefault(light_gray_wool);

		MaterialTextile gray_wool = new MaterialTextile("gray_wool", "minecraft");
		gray_wool.BLOCK.setExists(TagUtil.mcLoc("gray_wool"), TagUtil.mcLoc("gray_wool"));
		gray_wool.CARPET.setExists(TagUtil.mcLoc("gray_carpet"), TagUtil.mcLoc("gray_carpet"));
		gray_wool.STRING.setIgnored();
		buildDefault(gray_wool);

		MaterialTextile black_wool = new MaterialTextile("black_wool", "minecraft");
		black_wool.BLOCK.setExists(TagUtil.mcLoc("black_wool"), TagUtil.mcLoc("black_wool"));
		black_wool.CARPET.setExists(TagUtil.mcLoc("black_carpet"), TagUtil.mcLoc("black_carpet"));
		black_wool.STRING.setIgnored();
		buildDefault(black_wool);

		MaterialTextile brown_wool = new MaterialTextile("brown_wool", "minecraft");
		brown_wool.BLOCK.setExists(TagUtil.mcLoc("brown_wool"), TagUtil.mcLoc("brown_wool"));
		brown_wool.CARPET.setExists(TagUtil.mcLoc("brown_carpet"), TagUtil.mcLoc("brown_carpet"));
		brown_wool.STRING.setIgnored();
		buildDefault(brown_wool);

		MaterialTextile red_wool = new MaterialTextile("red_wool", "minecraft");
		red_wool.BLOCK.setExists(TagUtil.mcLoc("red_wool"), TagUtil.mcLoc("red_wool"));
		red_wool.CARPET.setExists(TagUtil.mcLoc("red_carpet"), TagUtil.mcLoc("red_carpet"));
		red_wool.STRING.setIgnored();
		buildDefault(red_wool);

		MaterialTextile orange_wool = new MaterialTextile("orange_wool", "minecraft");
		orange_wool.BLOCK.setExists(TagUtil.mcLoc("orange_wool"), TagUtil.mcLoc("orange_wool"));
		orange_wool.CARPET.setExists(TagUtil.mcLoc("orange_carpet"), TagUtil.mcLoc("orange_carpet"));
		orange_wool.STRING.setIgnored();
		buildDefault(orange_wool);

		MaterialTextile yellow_wool = new MaterialTextile("yellow_wool", "minecraft");
		yellow_wool.BLOCK.setExists(TagUtil.mcLoc("yellow_wool"), TagUtil.mcLoc("yellow_wool"));
		yellow_wool.CARPET.setExists(TagUtil.mcLoc("yellow_carpet"), TagUtil.mcLoc("yellow_carpet"));
		yellow_wool.STRING.setIgnored();
		buildDefault(yellow_wool);

		MaterialTextile lime_wool = new MaterialTextile("lime_wool", "minecraft");
		lime_wool.BLOCK.setExists(TagUtil.mcLoc("lime_wool"), TagUtil.mcLoc("lime_wool"));
		lime_wool.CARPET.setExists(TagUtil.mcLoc("lime_carpet"), TagUtil.mcLoc("lime_carpet"));
		lime_wool.STRING.setIgnored();
		buildDefault(lime_wool);

		MaterialTextile green_wool = new MaterialTextile("green_wool", "minecraft");
		green_wool.BLOCK.setExists(TagUtil.mcLoc("green_wool"), TagUtil.mcLoc("green_wool"));
		green_wool.CARPET.setExists(TagUtil.mcLoc("green_carpet"), TagUtil.mcLoc("green_carpet"));
		green_wool.STRING.setIgnored();
		buildDefault(green_wool);

		MaterialTextile cyan_wool = new MaterialTextile("cyan_wool", "minecraft");
		cyan_wool.BLOCK.setExists(TagUtil.mcLoc("cyan_wool"), TagUtil.mcLoc("cyan_wool"));
		cyan_wool.CARPET.setExists(TagUtil.mcLoc("cyan_carpet"), TagUtil.mcLoc("cyan_carpet"));
		cyan_wool.STRING.setIgnored();
		buildDefault(cyan_wool);

		MaterialTextile light_blue_wool = new MaterialTextile("light_blue_wool", "minecraft");
		light_blue_wool.BLOCK.setExists(TagUtil.mcLoc("light_blue_wool"), TagUtil.mcLoc("light_blue_wool"));
		light_blue_wool.CARPET.setExists(TagUtil.mcLoc("light_blue_carpet"), TagUtil.mcLoc("light_blue_carpet"));
		light_blue_wool.STRING.setIgnored();
		buildDefault(light_blue_wool);

		MaterialTextile blue_wool = new MaterialTextile("blue_wool", "minecraft");
		blue_wool.BLOCK.setExists(TagUtil.mcLoc("blue_wool"), TagUtil.mcLoc("blue_wool"));
		blue_wool.CARPET.setExists(TagUtil.mcLoc("blue_carpet"), TagUtil.mcLoc("blue_carpet"));
		blue_wool.STRING.setIgnored();
		buildDefault(blue_wool);

		MaterialTextile purple_wool = new MaterialTextile("purple_wool", "minecraft");
		purple_wool.BLOCK.setExists(TagUtil.mcLoc("purple_wool"), TagUtil.mcLoc("purple_wool"));
		purple_wool.CARPET.setExists(TagUtil.mcLoc("purple_carpet"), TagUtil.mcLoc("purple_carpet"));
		purple_wool.STRING.setIgnored();
		buildDefault(purple_wool);

		MaterialTextile magenta_wool = new MaterialTextile("magenta_wool", "minecraft");
		magenta_wool.BLOCK.setExists(TagUtil.mcLoc("magenta_wool"), TagUtil.mcLoc("magenta_wool"));
		magenta_wool.CARPET.setExists(TagUtil.mcLoc("magenta_carpet"), TagUtil.mcLoc("magenta_carpet"));
		magenta_wool.STRING.setIgnored();
		buildDefault(magenta_wool);

		MaterialTextile pink_wool = new MaterialTextile("pink_wool", "minecraft");
		pink_wool.BLOCK.setExists(TagUtil.mcLoc("pink_wool"), TagUtil.mcLoc("pink_wool"));
		pink_wool.CARPET.setExists(TagUtil.mcLoc("pink_carpet"), TagUtil.mcLoc("pink_carpet"));
		pink_wool.STRING.setIgnored();
		buildDefault(pink_wool);

	}

	private static void moddedDefaults() {
		MaterialMetal silver = new MaterialMetal("silver", "compendium");
		silver.setupTier("GOLD");
		silver.BLOCK.setGenerate();
		silver.INGOT.setGenerate();
		silver.NUGGET.setGenerate();

		silver.addExtension(new ExtensionAdvancedTools().generateAll());

		buildDefault(silver);

//		buildDefault(new MaterialTextile("amber_wool", "dyenamics"));
//		buildDefault(new MaterialTextile("aquamarine_wool", "dyenamics"));
//		buildDefault(new MaterialTextile("bubblegum_wool", "dyenamics"));
//		buildDefault(new MaterialTextile("cherenkov_wool", "dyenamics"));
//		buildDefault(new MaterialTextile("conifer_wool", "dyenamics"));
//		buildDefault(new MaterialTextile("fluorescent_wool", "dyenamics"));
//		buildDefault(new MaterialTextile("honey_wool", "dyenamics"));
//		buildDefault(new MaterialTextile("icy_blue_wool", "dyenamics"));
//		buildDefault(new MaterialTextile("lavender_wool", "dyenamics"));
//		buildDefault(new MaterialTextile("maroon_wool", "dyenamics"));
//		buildDefault(new MaterialTextile("mint_wool", "dyenamics"));
//		buildDefault(new MaterialTextile("navy_wool", "dyenamics"));
//		buildDefault(new MaterialTextile("peach_wool", "dyenamics"));
//		buildDefault(new MaterialTextile("persimmon_wool", "dyenamics"));
//		buildDefault(new MaterialTextile("rose_wool", "dyenamics"));
//		buildDefault(new MaterialTextile("spring_green_wool", "dyenamics"));
//		buildDefault(new MaterialTextile("ultramarine_wool", "dyenamics"));
//		buildDefault(new MaterialTextile("wine_wool", "dyenamics"));
//
		MaterialTextile gingham_amber = new MaterialTextile("gingham_amber", "compendium");
		gingham_amber.BLOCK.setGenerate();
		gingham_amber.CARPET.setGenerate();
		gingham_amber.STRING.setIgnored();
		buildDefault(gingham_amber);

		MaterialTextile gingham_aquamarine = new MaterialTextile("gingham_aquamarine", "compendium");
		gingham_aquamarine.BLOCK.setGenerate();
		gingham_aquamarine.CARPET.setGenerate();
		gingham_aquamarine.STRING.setIgnored();
		buildDefault(gingham_aquamarine);

		MaterialTextile gingham_bubblegum = new MaterialTextile("gingham_bubblegum", "compendium");
		gingham_bubblegum.BLOCK.setGenerate();
		gingham_bubblegum.CARPET.setGenerate();
		gingham_bubblegum.STRING.setIgnored();
		buildDefault(gingham_bubblegum);

		MaterialTextile gingham_cherenkov = new MaterialTextile("gingham_cherenkov", "compendium");
		gingham_cherenkov.BLOCK.setGenerate();
		gingham_cherenkov.CARPET.setGenerate();
		gingham_cherenkov.STRING.setIgnored();
		buildDefault(gingham_cherenkov);

		MaterialTextile gingham_conifer = new MaterialTextile("gingham_conifer", "compendium");
		gingham_conifer.BLOCK.setGenerate();
		gingham_conifer.CARPET.setGenerate();
		gingham_conifer.STRING.setIgnored();
		buildDefault(gingham_conifer);

		MaterialTextile gingham_fluorescent = new MaterialTextile("gingham_fluorescent", "compendium");
		gingham_fluorescent.BLOCK.setGenerate();
		gingham_fluorescent.CARPET.setGenerate();
		gingham_fluorescent.STRING.setIgnored();
		buildDefault(gingham_fluorescent);

		MaterialTextile gingham_honey = new MaterialTextile("gingham_honey", "compendium");
		gingham_honey.BLOCK.setGenerate();
		gingham_honey.CARPET.setGenerate();
		gingham_honey.STRING.setIgnored();
		buildDefault(gingham_honey);

		MaterialTextile gingham_icy_blue = new MaterialTextile("gingham_icy_blue", "compendium");
		gingham_icy_blue.BLOCK.setGenerate();
		gingham_icy_blue.CARPET.setGenerate();
		gingham_icy_blue.STRING.setIgnored();
		buildDefault(gingham_icy_blue);

		MaterialTextile gingham_lavender = new MaterialTextile("gingham_lavender", "compendium");
		gingham_lavender.BLOCK.setGenerate();
		gingham_lavender.CARPET.setGenerate();
		gingham_lavender.STRING.setIgnored();
		buildDefault(gingham_lavender);

		MaterialTextile gingham_maroon = new MaterialTextile("gingham_maroon", "compendium");
		gingham_maroon.BLOCK.setGenerate();
		gingham_maroon.CARPET.setGenerate();
		gingham_maroon.STRING.setIgnored();
		buildDefault(gingham_maroon);

		MaterialTextile gingham_mint = new MaterialTextile("gingham_mint", "compendium");
		gingham_mint.BLOCK.setGenerate();
		gingham_mint.CARPET.setGenerate();
		gingham_mint.STRING.setIgnored();
		buildDefault(gingham_mint);

		MaterialTextile gingham_navy = new MaterialTextile("gingham_navy", "compendium");
		gingham_navy.BLOCK.setGenerate();
		gingham_navy.CARPET.setGenerate();
		gingham_navy.STRING.setIgnored();
		buildDefault(gingham_navy);

		MaterialTextile gingham_peach = new MaterialTextile("gingham_peach", "compendium");
		gingham_peach.BLOCK.setGenerate();
		gingham_peach.CARPET.setGenerate();
		gingham_peach.STRING.setIgnored();
		buildDefault(gingham_peach);

		MaterialTextile gingham_persimmon = new MaterialTextile("gingham_persimmon", "compendium");
		gingham_persimmon.BLOCK.setGenerate();
		gingham_persimmon.CARPET.setGenerate();
		gingham_persimmon.STRING.setIgnored();
		buildDefault(gingham_persimmon);

		MaterialTextile gingham_rose = new MaterialTextile("gingham_rose", "compendium");
		gingham_rose.BLOCK.setGenerate();
		gingham_rose.CARPET.setGenerate();
		gingham_rose.STRING.setIgnored();
		buildDefault(gingham_rose);

		MaterialTextile gingham_spring_green = new MaterialTextile("gingham_spring_green", "compendium");
		gingham_spring_green.BLOCK.setGenerate();
		gingham_spring_green.CARPET.setGenerate();
		gingham_spring_green.STRING.setIgnored();
		buildDefault(gingham_spring_green);

		MaterialTextile gingham_ultramarine = new MaterialTextile("gingham_ultramarine", "compendium");
		gingham_ultramarine.BLOCK.setGenerate();
		gingham_ultramarine.CARPET.setGenerate();
		gingham_ultramarine.STRING.setIgnored();
		buildDefault(gingham_ultramarine);

		MaterialTextile gingham_wine = new MaterialTextile("gingham_wine", "compendium");
		gingham_wine.BLOCK.setGenerate();
		gingham_wine.CARPET.setGenerate();
		gingham_wine.STRING.setIgnored();
		buildDefault(gingham_wine);

//
//		buildDefault(
//				new MaterialTextile("gingham_white", "extradelight",
//						new SpecialLocationsTextile(
//								new SpecialTextureLocationsTextile(
//										ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/white"),
//										null, null))));
//		buildDefault(new MaterialTextile("gingham_light_gray", "extradelight",
//
//				new SpecialLocationsTextile(
//						new SpecialTextureLocationsTextile(
//								ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/light_gray"), null,
//								null))));
//		buildDefault(
//				new MaterialTextile("gingham_gray", "extradelight",
//						new SpecialLocationsTextile(
//								new SpecialTextureLocationsTextile(
//										ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/gray"),
//										null, null))));
//		buildDefault(
//				new MaterialTextile("gingham_black", "extradelight",
//						new SpecialLocationsTextile(
//								new SpecialTextureLocationsTextile(
//										ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/black"),
//										null, null))));
//		buildDefault(
//				new MaterialTextile("gingham_brown", "extradelight",
//						new SpecialLocationsTextile(
//								new SpecialTextureLocationsTextile(
//										ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/brown"),
//										null, null))));
//		buildDefault(
//				new MaterialTextile("gingham_red", "extradelight",
//						new SpecialLocationsTextile(
//								new SpecialTextureLocationsTextile(
//										ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/red"),
//										null, null))));
//		buildDefault(
//				new MaterialTextile("gingham_orange", "extradelight",
//						new SpecialLocationsTextile(
//								new SpecialTextureLocationsTextile(
//										ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/orange"),
//										null, null))));
//		buildDefault(
//				new MaterialTextile("gingham_yellow", "extradelight",
//						new SpecialLocationsTextile(
//								new SpecialTextureLocationsTextile(
//										ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/yellow"),
//										null, null))));
//		buildDefault(
//				new MaterialTextile("gingham_lime", "extradelight",
//						new SpecialLocationsTextile(
//								new SpecialTextureLocationsTextile(
//										ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/lime"),
//										null, null))));
//		buildDefault(
//				new MaterialTextile("gingham_green", "extradelight",
//						new SpecialLocationsTextile(
//								new SpecialTextureLocationsTextile(
//										ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/green"),
//										null, null))));
//		buildDefault(new MaterialTextile("gingham_cyan", "extradelight",
//
//				new SpecialLocationsTextile( new SpecialTextureLocationsTextile(
//						ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/cyan"), null, null))));
//		buildDefault(new MaterialTextile("gingham_light_blue", "extradelight",
//
//				new SpecialLocationsTextile(
//						new SpecialTextureLocationsTextile(
//								ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/light_blue"), null,
//								null))));
//		buildDefault(
//				new MaterialTextile("gingham_blue", "extradelight",
//						new SpecialLocationsTextile(
//								new SpecialTextureLocationsTextile(
//										ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/blue"),
//										null, null))));
//		buildDefault(
//				new MaterialTextile("gingham_purple", "extradelight",
//						new SpecialLocationsTextile(
//								new SpecialTextureLocationsTextile(
//										ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/purple"),
//										null, null))));
//		buildDefault(
//				new MaterialTextile("gingham_magenta", "extradelight",
//						new SpecialLocationsTextile(
//								new SpecialTextureLocationsTextile(
//										ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/magenta"),
//										null, null))));
//		buildDefault(
//				new MaterialTextile("gingham_pink", "extradelight",
//						new SpecialLocationsTextile(
//								new SpecialTextureLocationsTextile(
//										ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/pink"),
//										null, null))));
//
//		buildDefault(new MaterialWood("alder", "enchanted")
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		buildDefault(new MaterialWood("archwood", "ars_nouveau")
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		SpecialLocationsWood aspen = new SpecialLocationsWood(
//				new SpecialTextureLocationsWood(null,
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/aspen_log_side"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/aspen_log_side_stripped"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/aspen_log_top"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/aspen_log_top_stripped")));
//		buildDefault(new MaterialWood("aspen", "bloomingnature", aspen)
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		SpecialLocationsWood baobab = new SpecialLocationsWood(
//				new SpecialTextureLocationsWood(null,
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/baobab_log_side"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/baobab_log_side_stripped"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/baobab_log_top"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/baobab_log_top_stripped")));
//		buildDefault(new MaterialWood("baobab", "bloomingnature", baobab)
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		SpecialLocationsWood blackwood = new SpecialLocationsWood(
//				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("abyssal_decor", "blackwood_planks"),
//						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "blackwood_log"),
//						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "stripped_blackwood_log"),
//						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "blackwood_wood"), null),
//				null,
//				new SpecialTextureLocationsWood(
//						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/blackwoodplanks"),
//						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/moldylogside"),
//						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/blackwoodstrippedlogside"),
//						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/moldylogtop"),
//						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/blackwoodstrippedlogtop")));
//		buildDefault(new MaterialWood("blackwood", "abyssal_decor", blackwood)
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		buildDefault(new MaterialWood("blue_mushroom", "cluttered", )
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		SpecialLocationsWood cactus = new SpecialLocationsWood(
//				new ExistsLocationsWood(null, ResourceLocation.fromNamespaceAndPath("minecraft", "cactus"), null, null,
//						null),
//				new ExistsLocationsWood(null, ResourceLocation.fromNamespaceAndPath("minecraft", "cactus"), null, null,
//						null),
//				new SpecialTextureLocationsWood(null,
//						ResourceLocation.fromNamespaceAndPath("compendium", "block/cactus_log"), null,
//						ResourceLocation.fromNamespaceAndPath("compendium", "block/cactus_log_top"),
//						ResourceLocation.fromNamespaceAndPath("compendium", "block/stripped_cactus_log_top")));
//		buildDefault(new MaterialWood("cactus", "bloomingnature", cactus)
//				.addExtension(new ExtensionExtraLogs(Generate.IGNORE))
//				.addExtension(new ExtensionExtraPlanks()));
//
//		SpecialLocationsWood charred_spruce = new SpecialLocationsWood(new ExistsLocationsWood(
//				ResourceLocation.fromNamespaceAndPath("dawnoftimebuilder", "charred_spruce_log_stripped"), null, null),
//				new ExistsLocationsWood(
//						ResourceLocation.fromNamespaceAndPath("dawnoftimebuilder", "charred_spruce_log_stripped"), null,
//						null),
//				new SpecialTextureLocationsWood(
//						ResourceLocation.fromNamespaceAndPath("dawnoftimebuilder", "block/charred_spruce_log_stripped"),
//						null, ResourceLocation.fromNamespaceAndPath("dawnoftimebuilder",
//								"block/charred_spruce_log_stripped_top")));
//		buildDefault(new MaterialWood("charred_spruce", "dawnoftimebuilder", charred_spruce)
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		SpecialLocationsWood chestnut = new SpecialLocationsWood(
//				new SpecialTextureLocationsWood(null,
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/chestnut_log_side"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/chestnut_log_side_stripped"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/chestnut_log_top"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/chestnut_log_top_stripped")));
//		buildDefault(new MaterialWood("chestnut", "bloomingnature", chestnut)
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		SpecialLocationsWood chorus = new SpecialLocationsWood(
//				new ExistsLocationsWood( null, null,
//						ResourceLocation.fromNamespaceAndPath("cataclysm", "chorus_stem")),
//				new ExistsLocationsWood( null, null,
//						ResourceLocation.fromNamespaceAndPath("cataclysm", "chorus_stem")),
//				new SpecialTextureLocationsWood(
//						ResourceLocation.fromNamespaceAndPath("cataclysm", "block/chorus_stem"),
//						ResourceLocation.fromNamespaceAndPath("compendium", "block/material/wood/chorus/logs/log_top"),
//						ResourceLocation.fromNamespaceAndPath("compendium",
//								"block/material/wood/chorus/logs/log_top")));
//		buildDefault(
//				new MaterialWood("chorus", "cataclysm", chorus).addExtension(new ExtensionExtraLogs())
//						.addExtension(new ExtensionExtraPlanks()));
//
//		SpecialLocationsWood cinnamon = new SpecialLocationsWood(
//				new ExistsLocationsWood( null, null,
//						ResourceLocation.fromNamespaceAndPath("extradelight", "strippedcinnamon_wood")),
//				new ExistsLocationsWood( null, null,
//						ResourceLocation.fromNamespaceAndPath("extradelight", "stripped_cinnamon_wood")),
//				null);
//		buildDefault(new MaterialWood("cinnamon", "extradelight", cinnamon)
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		SpecialLocationsWood cinnamon_ad = new SpecialLocationsWood(
//				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("abyssal_decor", "cinnamon_planks"),
//						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "cinnamon_log"),
//						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "stripped_cinnamon_log"),
//						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "cinnamon_wood"),
//						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "stripped_cinnamon_wood")),
//				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("abyssal_decor", "cinnamon_planks"),
//						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "cinnamon_log"),
//						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "stripped_cinnamon_log"),
//						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "cinnamon_wood"),
//						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "stripped_cinnamon_wood")),
//				new SpecialTextureLocationsWood(
//						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/cinnamonplanks"),
//						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/cinnamonlogside"),
//						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/strippedcinnamonlogside"),
//						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/cinnamonlogtop"),
//						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/strippedcinnamonlogtop")));
//		buildDefault(new MaterialWood("cinnamon_ad", "abyssal_decor", cinnamon_ad)
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		buildDefault(new MaterialWood("crabapple", "cluttered")
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//		buildDefault(new MaterialWood("flowering_crabapple", "cluttered")
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks(Generate.IGNORE)));
//
//		SpecialLocationsWood cypress = new SpecialLocationsWood(
//				new SpecialTextureLocationsWood(null,
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/cypress_log_side"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/cypress_log_stripped_side"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/cypress_log_top"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/cypress_log_stripped_top")));
//		buildDefault(new MaterialWood("cypress", "bloomingnature", cypress)
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		SpecialLocationsWood ebony = new SpecialLocationsWood(
//				new SpecialTextureLocationsWood(null,
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/ebony_log_side"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/ebony_log_side_stripped"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/ebony_log_top"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/ebony_log_top_stripped")));
//		buildDefault(new MaterialWood("ebony", "bloomingnature", ebony)
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		SpecialLocationsWood fan_palm = new SpecialLocationsWood(
//				new SpecialTextureLocationsWood(null,
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/fan_palm_log_side"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/fan_palm_log_stripped"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/fan_palm_log_top"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/fan_palm_log_top_stripped")));
//		buildDefault(new MaterialWood("fan_palm", "bloomingnature", fan_palm)
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		SpecialLocationsWood fir = new SpecialLocationsWood(
//				new SpecialTextureLocationsWood(null,
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/fir_log_side"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/fir_log_side_stripped"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/fir_log_top"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/fir_log_top_stripped")));
//		buildDefault(
//				new MaterialWood("fir", "bloomingnature", fir).addExtension(new ExtensionExtraLogs())
//						.addExtension(new ExtensionExtraPlanks()));
//
//		buildDefault(new MaterialWood("fluorescent_maple", "cluttered")
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		buildDefault(new MaterialWood("frostbite_birch", "hazennstuff")
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		buildDefault(new MaterialWood("fruit", "extradelight")
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		buildDefault(new MaterialWood("hawthorn", "enchanted", )
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		SpecialLocationsWood larch = new SpecialLocationsWood(
//				new SpecialTextureLocationsWood(null,
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/larch_log_side"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/larch_log_stripped"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/larch_log_top"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/larch_log_stripped_top")));
//		buildDefault(new MaterialWood("larch", "bloomingnature", larch)
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		buildDefault(new MaterialWood("netherwood", "silentgear")
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		buildDefault(new MaterialWood("poplar", "cluttered")
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		SpecialLocationsWood flowering_poplar = new SpecialLocationsWood(
//				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_poplar_planks"),
//						ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_poplar_log"),
//						ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_flowering_poplar_log"),
//						ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_poplar_wood"),
//						ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_flowering_poplar_log")),
//				null, null);
//		buildDefault(new MaterialWood("flowering_poplar", "cluttered", flowering_poplar)
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks(Generate.IGNORE)));
//
//		SpecialLocationsWood red_mushroom = new SpecialLocationsWood(
//				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("cluttered", "red_mushroom_planks"), null,
//						ResourceLocation.fromNamespaceAndPath("cluttered", "red_mushroom_log"),
//						ResourceLocation.fromNamespaceAndPath("cluttered", "red_mushroom_wood"), null),
//				null, null);
//		buildDefault(new MaterialWood("red_mushroom", "cluttered", red_mushroom)
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		SpecialLocationsWood rowan = new SpecialLocationsWood(
//				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("enchanted", "rowan_planks"), null,
//						ResourceLocation.fromNamespaceAndPath("enchanted", "rowan_log"), null,
//						ResourceLocation.fromNamespaceAndPath("enchanted", "stripped_rowan_wood")),
//				null, null);
//		buildDefault(
//				new MaterialWood("rowan", "enchanted", rowan).addExtension(new ExtensionExtraLogs())
//						.addExtension(new ExtensionExtraPlanks()));
//
//		SpecialLocationsWood swamp_cypress = new SpecialLocationsWood(
//				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("bloomingnature", "swamp_cypress_planks"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "swamp_cypress_log"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "stripped_swamp_cypress_log"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "swamp_cypress_wood"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "stripped_swamp_cypress_wood")),
//				null,
//				new SpecialTextureLocationsWood(null,
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/swamp_cypress_log_side"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature",
//								"block/swamp_cypress_log_stripped_side"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/swamp_cypress_log_top"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature",
//								"block/swamp_cypress_log_stripped_top")));
//		buildDefault(new MaterialWood("swamp_cypress", "bloomingnature", swamp_cypress)
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		SpecialLocationsWood swamp_oak = new SpecialLocationsWood(
//				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("bloomingnature", "swamp_oak_planks"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "swamp_oak_log"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "stripped_swamp_oak_log"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "swamp_oak_wood"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "stripped_swamp_oak_wood")),
//				null,
//				new SpecialTextureLocationsWood(null,
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/swamp_oak_log_side"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/swamp_oak_log_stripped"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/swamp_oak_log_top"),
//						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/swamp_oak_log_stripped_top")));
//		buildDefault(new MaterialWood("swamp_oak", "bloomingnature", swamp_oak)
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		SpecialLocationsWood sycamore = new SpecialLocationsWood(
//				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("cluttered", "sycamore_planks"),
//						ResourceLocation.fromNamespaceAndPath("cluttered", "sycamore_log"),
//						ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_sycamore_log"),
//						ResourceLocation.fromNamespaceAndPath("cluttered", "sycamore_wood"),
//						ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_sycamore_wood")),
//				null, null);
//		buildDefault(new MaterialWood("sycamore", "cluttered", sycamore)
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		SpecialLocationsWood waxed_oak = new SpecialLocationsWood(new ExistsLocationsWood(
//				ResourceLocation.fromNamespaceAndPath("dawnoftimebuilder", "waxed_oak_log_stripped"), null, null),
//				new ExistsLocationsWood(
//						ResourceLocation.fromNamespaceAndPath("dawnoftimebuilder", "waxed_oak_log_stripped"), null,
//						null),
//				new SpecialTextureLocationsWood(
//						ResourceLocation.fromNamespaceAndPath("dawnoftimebuilder", "block/waxed_oak_log_stripped"),
//						null, ResourceLocation.fromNamespaceAndPath("dawnoftimebuilder",
//								"block/waxed_oak_log_stripped_top")));
//		buildDefault(new MaterialWood("waxed_oak", "dawnoftimebuilder", waxed_oak)
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		SpecialLocationsWood willow = new SpecialLocationsWood(
//				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("cluttered", "willow_planks"),
//						ResourceLocation.fromNamespaceAndPath("cluttered", "willow_log"),
//						ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_willow_log"),
//						ResourceLocation.fromNamespaceAndPath("cluttered", "willow_wood"),
//						ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_willow_wood")),
//				null, null);
//		buildDefault(
//				new MaterialWood("willow", "cluttered", willow).addExtension(new ExtensionExtraLogs())
//						.addExtension(new ExtensionExtraPlanks()));
//
//		SpecialLocationsWood flowering_willow = new SpecialLocationsWood(
//				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_willow_planks"),
//						ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_willow_log"),
//						ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_flowering_willow_log"),
//						ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_willow_wood"),
//						ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_flowering_willow_wood")),
//				null, null);
//		buildDefault(new MaterialWood("flowering_willow", "cluttered", flowering_willow)
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks(Generate.IGNORE)));
//
//		SpecialLocationsWood wisewood = new SpecialLocationsWood(
//				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("hazennstuff", "wisewood_planks"),
//						ResourceLocation.fromNamespaceAndPath("hazennstuff", "wisewood_log"),
//						ResourceLocation.fromNamespaceAndPath("hazennstuff", "stripped_wisewood_log"),
//						ResourceLocation.fromNamespaceAndPath("hazennstuff", "wisewood_wood"),
//						ResourceLocation.fromNamespaceAndPath("hazennstuff", "stripped_wisewood_wood")),
//				null,
//				new SpecialTextureLocationsWood(
//						ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "block/wisewood_planks"),
//						ResourceLocation.fromNamespaceAndPath("hazennstuff", "block/wisewood_log_side"),
//						ResourceLocation.fromNamespaceAndPath("hazennstuff", "block/stripped_wisewood_log_side"),
//						ResourceLocation.fromNamespaceAndPath("hazennstuff", "block/wisewood_log_side"),
//						ResourceLocation.fromNamespaceAndPath("hazennstuff", "block/stripped_wisewood_log_top")));
//		buildDefault(new MaterialWood("wisewood", "hazennstuff", wisewood)
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		// The problem children
//		SpecialLocationsWood whitewood = new SpecialLocationsWood(
//				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("abyssal_decor", "white_wood_planks"),
//						null, ResourceLocation.fromNamespaceAndPath("abyssal_decor", "white_wood_log"), null,
//						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "white_wood_wood")),
//				null,
//				new SpecialTextureLocationsWood(
//						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/whitewoodplanks"), null,
//						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/whitewoodstrippedlog"),
//						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/whitewoodstrippedlogtop"),
//						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/whitewoodstrippedlogtop")));
//		buildDefault(new MaterialWood("white_wood", "abyssal_decor", whitewood)
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		SpecialLocationsWood kopje = new SpecialLocationsWood(
//				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("koopascritters", "kopje_fig_planks"),
//						ResourceLocation.fromNamespaceAndPath("koopascritters", "kopje_fig_log"),
//						ResourceLocation.fromNamespaceAndPath("koopascritters", "stripped_kopje_log"), null, null),
//				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("koopascritters", "kopje_fig_planks"),
//						ResourceLocation.fromNamespaceAndPath("koopascritters", "kopje_fig_log"),
//						ResourceLocation.fromNamespaceAndPath("koopascritters", "stripped_kopje_log"), null, null),
//				new SpecialTextureLocationsWood(
//						ResourceLocation.fromNamespaceAndPath("koopascritters", "block/kopjeplanks"),
//						ResourceLocation.fromNamespaceAndPath("koopascritters", "block/kopjelogside"),
//						ResourceLocation.fromNamespaceAndPath("koopascritters", "block/kopjelogstrippedside"),
//						ResourceLocation.fromNamespaceAndPath("koopascritters", "block/kopjelogtop"),
//						ResourceLocation.fromNamespaceAndPath("koopascritters", "block/kopjelogstrippedtop")));
//		buildDefault(new MaterialWood("kopje", "koopascritters", kopje)
//				.addExtension(new ExtensionExtraLogs())
//				.addExtension(new ExtensionExtraPlanks()));
//
//		SpecialLocationsWood barn_wood = new SpecialLocationsWood(
//				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("butchercraft", "barn_wood_block_item"),
//						null, null, null, null),
//				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("butchercraft", "barn_wood_block"), null,
//						null, null, null),
//				new SpecialTextureLocationsWood(
//						ResourceLocation.fromNamespaceAndPath("butchercraft", "block/barn_wood_block"),
//						ResourceLocation.fromNamespaceAndPath("compendium", "block/barn_wood_log"), null, null, null));
//		buildDefault(new MaterialWood("barn_wood", "butchercraft", barn_wood)
//				.addExtension(new ExtensionExtraLogs(Generate.IGNORE))
//				.addExtension(new ExtensionExtraPlanks()));
	}

	static void buildDefault(_MaterialBase mat) {

		try {
			Path resourcePackPath = Path.of("./../src/main/resources/data/compendium/materials");
			Files.createDirectories(resourcePackPath.resolve(mat.getType().toString().toLowerCase() + "/"));
			Path p = resourcePackPath.resolve(mat.getType().toString().toLowerCase() + "/").resolve(mat.name + ".json");
			if (Files.exists(p))
				return;
			else {
				Writer w = Files.newBufferedWriter(p);
				String g = GSON.toJson(mat);
				w.write(g);
				w.close();
			}

		} catch (JsonIOException e) {
			LOGGER.error(e.getLocalizedMessage());
		} catch (IOException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
	}

	static void readResourcePacks(Path resourcePackPath) {
//		Path resourcePackPath = Minecraft.getInstance().getResourcePackDirectory()
//				.resolve("compendium\\data\\compendium\\materials");
		Compendium.LOGGER.debug(resourcePackPath.toAbsolutePath().toString());
		read(resourcePackPath);
	}

	static void read(Path path) {
		try {
			Stream<Path> paths = Files.walk(path);
//			List<Path> listPaths = paths.collect(Collectors.toList());
			List<Path> filtered = paths.filter(f -> f.getFileName().toString().endsWith(".json"))
					.collect(Collectors.toList());
			filtered.forEach(p -> readFile(p));
//			paths.forEach(p -> {
//				readFile(p);
//			});
			paths.close();

		} catch (IOException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
	}

	private static void readFile(Path p) {
		try {
			Reader r = Files.newBufferedReader(p);

			_MaterialBase m = GSON.fromJson(r, _MaterialBase.class);
			if (m != null) {
				CompendiumIndex.index.removeIf(i -> {
					if (i instanceof _MaterialBase mb)
						return i.getName().compareTo(m.getName()) == 0 && mb.namespace.compareTo(m.namespace) == 0;
					return false;
				});

				CompendiumIndex.addEntry(m);

			}

			r.close();

		} catch (

		IOException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
	}

	private static void readFile(InputStream stream) {
		try {
			Reader r = new BufferedReader(new InputStreamReader(stream));

			_MaterialBase m = GSON.fromJson(r, _MaterialBase.class);
			if (m != null) {
				CompendiumIndex.index.removeIf(i -> i.getName().compareTo(m.getName()) == 0);

				CompendiumIndex.addEntry(m);

			}

			r.close();

		} catch (IOException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
	}
}
