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
import com.lance5057.compendium.index.material.base.gem.MaterialGem;
import com.lance5057.compendium.index.material.base.gem.locations.SpecialLocationsGem;
import com.lance5057.compendium.index.material.base.gem.locations.SpecialTextureLocationsGem;
import com.lance5057.compendium.index.material.base.glass.MaterialGlass;
import com.lance5057.compendium.index.material.base.metal.MaterialMetal;
import com.lance5057.compendium.index.material.base.textile.MaterialTextile;
import com.lance5057.compendium.index.material.base.textile.locations.SpecialLocationsTextile;
import com.lance5057.compendium.index.material.base.textile.locations.SpecialTextureLocationsTextile;
import com.lance5057.compendium.index.material.base.wood.MaterialWood;
import com.lance5057.compendium.index.material.base.wood.locations.SpecialLocationsWood;
import com.lance5057.compendium.index.material.base.wood.locations.SpecialTextureLocationsWood;
import com.lance5057.compendium.index.material.extensions.ExtensionAdvancedTools;
import com.lance5057.compendium.index.material.extensions.ExtensionVanillaTools;
import com.lance5057.compendium.index.material.extensions.gem.ExtensionGemStyleBlocks;
import com.lance5057.compendium.index.material.extensions.metal.ExtensionMetalStyleBlocks;
import com.lance5057.compendium.index.material.extensions.wood.ExtensionExtraLogs;
import com.lance5057.compendium.index.material.extensions.wood.ExtensionExtraPlanks;
import com.lance5057.compendium.util.CompendiumTier;
import com.lance5057.compendium.util.TagUtil;
import com.mojang.logging.LogUtils;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
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
		moddedDefaults();
//		addons();
		readOtherMods();
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

	private static void addons() {
		for (DyeColor d : DyeColor.values()) {
			MaterialWood dye = new MaterialWood(d.getName().toLowerCase(), "addendum_colored_planks");
			dye.LOG.setIgnore();
			dye.STRIPPED_LOG.setIgnore();
			dye.WOOD.setIgnore();
			dye.STRIPPED_WOOD.setIgnore();
			dye.PLANKS.setGenerate();

			dye.addExtension(new ExtensionExtraPlanks().generateAll());
			buildDefault(dye, "addendum_colored_planks");
		}
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
		metal();
		glass();
		wood();
		wool();
		gem();
	}

	private static void gem() {
		MaterialGem diamond = new MaterialGem("diamond", "minecraft");
		diamond.tier = new CompendiumTier("DIAMOND");
		diamond.BLOCK.setExists(TagUtil.mcLoc("diamond_block"), TagUtil.mcLoc("diamond_block"));
		diamond.GEM.setExists(TagUtil.mcLoc("diamond"));
		diamond.SHARD.setGenerate();

		diamond.addExtension(new ExtensionAdvancedTools().generateAll());
		diamond.addExtension(new ExtensionGemStyleBlocks().generateAll());

		buildDefault(diamond);

		MaterialGem amethyst = new MaterialGem("amethyst", "minecraft");
		amethyst.tier = new CompendiumTier("IRON");
		amethyst.BLOCK.setExists(TagUtil.mcLoc("amethyst_block"), TagUtil.mcLoc("amethyst_block"));
		amethyst.GEM.setExists(TagUtil.mcLoc("amethyst_shard"));
		amethyst.SHARD.setGenerate();

		amethyst.addExtension(new ExtensionAdvancedTools().generateAll());
		amethyst.addExtension(new ExtensionGemStyleBlocks().generateAll());
		amethyst.addExtension(new ExtensionVanillaTools().generateAll());

		buildDefault(amethyst);

		MaterialGem emerald = new MaterialGem("emerald", "minecraft");
		emerald.tier = new CompendiumTier("DIAMOND");
		emerald.BLOCK.setExists(TagUtil.mcLoc("emerald_block"), TagUtil.mcLoc("emerald_block"));
		emerald.GEM.setExists(TagUtil.mcLoc("emerald"));
		emerald.SHARD.setGenerate();

		emerald.addExtension(new ExtensionAdvancedTools().generateAll());
		emerald.addExtension(new ExtensionGemStyleBlocks().generateAll());
		emerald.addExtension(new ExtensionVanillaTools().generateAll());

		buildDefault(emerald);

		MaterialGem quartz = new MaterialGem("quartz", "minecraft");
		quartz.tier = new CompendiumTier("IRON");
		quartz.BLOCK.setExists(TagUtil.mcLoc("quartz_block"), TagUtil.mcLoc("quartz_block"));
		quartz.GEM.setExists(TagUtil.mcLoc("quartz"));
		quartz.SHARD.setGenerate();

		quartz.addExtension(new ExtensionAdvancedTools().generateAll());
		quartz.addExtension(new ExtensionGemStyleBlocks().generateAll());
		quartz.addExtension(new ExtensionVanillaTools().generateAll());

		buildDefault(quartz);

		MaterialGem ender_pearl = new MaterialGem("ender_pearl", "minecraft");

		SpecialLocationsGem ender_loc = new SpecialLocationsGem(
				new SpecialTextureLocationsGem(TagUtil.modLoc("block/material/gem/ender_pearl/block"), null, null));

		ender_pearl.specialLocations = ender_loc;

		ender_pearl.tier = new CompendiumTier("GOLD");
		ender_pearl.BLOCK.setGenerate();
		ender_pearl.GEM.setExists(TagUtil.mcLoc("ender_pearl"));
		ender_pearl.SHARD.setGenerate();

		ender_pearl.addExtension(new ExtensionAdvancedTools().generateAll());
		ender_pearl.addExtension(new ExtensionGemStyleBlocks().generateAll());
		ender_pearl.addExtension(new ExtensionVanillaTools().generateAll());

		buildDefault(ender_pearl);

		MaterialGem obsidian = new MaterialGem("obsidian", "minecraft");
		obsidian.tier = new CompendiumTier("DIAMOND");
		obsidian.BLOCK.setExists(TagUtil.mcLoc("obsidian"), TagUtil.mcLoc("obsidian"));
		obsidian.GEM.setGenerate();
		obsidian.SHARD.setGenerate();

		obsidian.addExtension(new ExtensionAdvancedTools().generateAll());
		obsidian.addExtension(new ExtensionGemStyleBlocks().generateAll());
		obsidian.addExtension(new ExtensionVanillaTools().generateAll());

		buildDefault(obsidian);

		MaterialGem crying_obsidian = new MaterialGem("crying_obsidian", "minecraft");
		crying_obsidian.tier = new CompendiumTier("DIAMOND");
		crying_obsidian.BLOCK.setExists(TagUtil.mcLoc("crying_obsidian"), TagUtil.mcLoc("crying_obsidian"));
		crying_obsidian.GEM.setGenerate();
		crying_obsidian.SHARD.setGenerate();

		crying_obsidian.addExtension(new ExtensionAdvancedTools().generateAll());
		crying_obsidian.addExtension(new ExtensionGemStyleBlocks().generateAll());
		crying_obsidian.addExtension(new ExtensionVanillaTools().generateAll());

		buildDefault(crying_obsidian);

		MaterialGem lapis = new MaterialGem("lapis", "minecraft");
		lapis.tier = new CompendiumTier("STONE");
		lapis.BLOCK.setExists(TagUtil.mcLoc("lapis_block"), TagUtil.mcLoc("lapis_block"));
		lapis.GEM.setExists(TagUtil.mcLoc("lapis_lazuli"));
		lapis.SHARD.setGenerate();

		lapis.addExtension(new ExtensionAdvancedTools().generateAll());
		lapis.addExtension(new ExtensionGemStyleBlocks().generateAll());
		lapis.addExtension(new ExtensionVanillaTools().generateAll());

		buildDefault(lapis);

		MaterialGem prismarine = new MaterialGem("prismarine", "minecraft");
		prismarine.tier = new CompendiumTier("GOLD");
		prismarine.BLOCK.setExists(TagUtil.mcLoc("prismarine"), TagUtil.mcLoc("prismarine"));
		prismarine.GEM.setExists(TagUtil.mcLoc("prismarine_crystals"));
		prismarine.SHARD.setExists(TagUtil.mcLoc("prismarine_shard"));

		prismarine.addExtension(new ExtensionAdvancedTools().generateAll());
		prismarine.addExtension(new ExtensionGemStyleBlocks().generateAll());
		prismarine.addExtension(new ExtensionVanillaTools().generateAll());

		buildDefault(prismarine);

		MaterialGem dark_prismarine = new MaterialGem("dark_prismarine", "minecraft");
		dark_prismarine.tier = new CompendiumTier("GOLD");
		dark_prismarine.BLOCK.setExists(TagUtil.mcLoc("dark_prismarine"), TagUtil.mcLoc("dark_prismarine"));
		dark_prismarine.GEM.setGenerate();
		dark_prismarine.SHARD.setGenerate();

		dark_prismarine.addExtension(new ExtensionAdvancedTools().generateAll());
		dark_prismarine.addExtension(new ExtensionGemStyleBlocks().generateAll());
		dark_prismarine.addExtension(new ExtensionVanillaTools().generateAll());

		buildDefault(dark_prismarine);
	}

	public static void metal() {
		MaterialMetal iron = new MaterialMetal("iron", "minecraft");
		iron.tier = new CompendiumTier("IRON");
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

		iron.addExtension(new ExtensionMetalStyleBlocks().generateAll());

		buildDefault(iron);

		MaterialMetal gold = new MaterialMetal("gold", "minecraft");
		gold.tier = new CompendiumTier("GOLD");
		gold.BLOCK.setExists(TagUtil.mcLoc("gold_block"), TagUtil.mcLoc("gold_block"));
		gold.INGOT.setExists(TagUtil.mcLoc("gold_ingot"));
		gold.NUGGET.setExists(TagUtil.mcLoc("gold_nugget"));

		gold.addExtension(new ExtensionAdvancedTools().generateAll());
		gold.addExtension(new ExtensionMetalStyleBlocks().generateAll());

		buildDefault(gold);

		MaterialMetal copper = new MaterialMetal("copper", "minecraft");
		copper.tier = new CompendiumTier("IRON");
		copper.BLOCK.setExists(TagUtil.mcLoc("copper_block"), TagUtil.mcLoc("copper_block"));
		copper.INGOT.setExists(TagUtil.mcLoc("copper_ingot"));
		copper.NUGGET.setGenerate();

		copper.addExtension(new ExtensionAdvancedTools().generateAll());
		copper.addExtension(new ExtensionMetalStyleBlocks().generateAll());
		copper.addExtension(new ExtensionVanillaTools().generateAll());

		buildDefault(copper);

		MaterialMetal netherite = new MaterialMetal("netherite", "minecraft");
		netherite.tier = new CompendiumTier("NETHERITE");
		netherite.BLOCK.setExists(TagUtil.mcLoc("netherite_block"), TagUtil.mcLoc("netherite_block"));
		netherite.INGOT.setExists(TagUtil.mcLoc("netherite_ingot"));
		netherite.NUGGET.setGenerate();

		netherite.addExtension(new ExtensionAdvancedTools().generateAll());
		netherite.addExtension(new ExtensionMetalStyleBlocks().generateAll());

		buildDefault(netherite);
	}

	public static void glass() {
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
	}

	public static void wood() {
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
	}

	public static void wool() {
		MaterialTextile white_wool = new MaterialTextile("white_wool", "minecraft");
		white_wool.BLOCK.setExists(TagUtil.mcLoc("white_wool"), TagUtil.mcLoc("white_wool"));
		white_wool.CARPET.setExists(TagUtil.mcLoc("white_carpet"), TagUtil.mcLoc("white_carpet"));
		white_wool.STRING.setIgnore();
		buildDefault(white_wool);

		MaterialTextile light_gray_wool = new MaterialTextile("light_gray_wool", "minecraft");
		light_gray_wool.BLOCK.setExists(TagUtil.mcLoc("light_gray_wool"), TagUtil.mcLoc("light_gray_wool"));
		light_gray_wool.CARPET.setExists(TagUtil.mcLoc("light_gray_carpet"), TagUtil.mcLoc("light_gray_carpet"));
		light_gray_wool.STRING.setIgnore();
		buildDefault(light_gray_wool);

		MaterialTextile gray_wool = new MaterialTextile("gray_wool", "minecraft");
		gray_wool.BLOCK.setExists(TagUtil.mcLoc("gray_wool"), TagUtil.mcLoc("gray_wool"));
		gray_wool.CARPET.setExists(TagUtil.mcLoc("gray_carpet"), TagUtil.mcLoc("gray_carpet"));
		gray_wool.STRING.setIgnore();
		buildDefault(gray_wool);

		MaterialTextile black_wool = new MaterialTextile("black_wool", "minecraft");
		black_wool.BLOCK.setExists(TagUtil.mcLoc("black_wool"), TagUtil.mcLoc("black_wool"));
		black_wool.CARPET.setExists(TagUtil.mcLoc("black_carpet"), TagUtil.mcLoc("black_carpet"));
		black_wool.STRING.setIgnore();
		buildDefault(black_wool);

		MaterialTextile brown_wool = new MaterialTextile("brown_wool", "minecraft");
		brown_wool.BLOCK.setExists(TagUtil.mcLoc("brown_wool"), TagUtil.mcLoc("brown_wool"));
		brown_wool.CARPET.setExists(TagUtil.mcLoc("brown_carpet"), TagUtil.mcLoc("brown_carpet"));
		brown_wool.STRING.setIgnore();
		buildDefault(brown_wool);

		MaterialTextile red_wool = new MaterialTextile("red_wool", "minecraft");
		red_wool.BLOCK.setExists(TagUtil.mcLoc("red_wool"), TagUtil.mcLoc("red_wool"));
		red_wool.CARPET.setExists(TagUtil.mcLoc("red_carpet"), TagUtil.mcLoc("red_carpet"));
		red_wool.STRING.setIgnore();
		buildDefault(red_wool);

		MaterialTextile orange_wool = new MaterialTextile("orange_wool", "minecraft");
		orange_wool.BLOCK.setExists(TagUtil.mcLoc("orange_wool"), TagUtil.mcLoc("orange_wool"));
		orange_wool.CARPET.setExists(TagUtil.mcLoc("orange_carpet"), TagUtil.mcLoc("orange_carpet"));
		orange_wool.STRING.setIgnore();
		buildDefault(orange_wool);

		MaterialTextile yellow_wool = new MaterialTextile("yellow_wool", "minecraft");
		yellow_wool.BLOCK.setExists(TagUtil.mcLoc("yellow_wool"), TagUtil.mcLoc("yellow_wool"));
		yellow_wool.CARPET.setExists(TagUtil.mcLoc("yellow_carpet"), TagUtil.mcLoc("yellow_carpet"));
		yellow_wool.STRING.setIgnore();
		buildDefault(yellow_wool);

		MaterialTextile lime_wool = new MaterialTextile("lime_wool", "minecraft");
		lime_wool.BLOCK.setExists(TagUtil.mcLoc("lime_wool"), TagUtil.mcLoc("lime_wool"));
		lime_wool.CARPET.setExists(TagUtil.mcLoc("lime_carpet"), TagUtil.mcLoc("lime_carpet"));
		lime_wool.STRING.setIgnore();
		buildDefault(lime_wool);

		MaterialTextile green_wool = new MaterialTextile("green_wool", "minecraft");
		green_wool.BLOCK.setExists(TagUtil.mcLoc("green_wool"), TagUtil.mcLoc("green_wool"));
		green_wool.CARPET.setExists(TagUtil.mcLoc("green_carpet"), TagUtil.mcLoc("green_carpet"));
		green_wool.STRING.setIgnore();
		buildDefault(green_wool);

		MaterialTextile cyan_wool = new MaterialTextile("cyan_wool", "minecraft");
		cyan_wool.BLOCK.setExists(TagUtil.mcLoc("cyan_wool"), TagUtil.mcLoc("cyan_wool"));
		cyan_wool.CARPET.setExists(TagUtil.mcLoc("cyan_carpet"), TagUtil.mcLoc("cyan_carpet"));
		cyan_wool.STRING.setIgnore();
		buildDefault(cyan_wool);

		MaterialTextile light_blue_wool = new MaterialTextile("light_blue_wool", "minecraft");
		light_blue_wool.BLOCK.setExists(TagUtil.mcLoc("light_blue_wool"), TagUtil.mcLoc("light_blue_wool"));
		light_blue_wool.CARPET.setExists(TagUtil.mcLoc("light_blue_carpet"), TagUtil.mcLoc("light_blue_carpet"));
		light_blue_wool.STRING.setIgnore();
		buildDefault(light_blue_wool);

		MaterialTextile blue_wool = new MaterialTextile("blue_wool", "minecraft");
		blue_wool.BLOCK.setExists(TagUtil.mcLoc("blue_wool"), TagUtil.mcLoc("blue_wool"));
		blue_wool.CARPET.setExists(TagUtil.mcLoc("blue_carpet"), TagUtil.mcLoc("blue_carpet"));
		blue_wool.STRING.setIgnore();
		buildDefault(blue_wool);

		MaterialTextile purple_wool = new MaterialTextile("purple_wool", "minecraft");
		purple_wool.BLOCK.setExists(TagUtil.mcLoc("purple_wool"), TagUtil.mcLoc("purple_wool"));
		purple_wool.CARPET.setExists(TagUtil.mcLoc("purple_carpet"), TagUtil.mcLoc("purple_carpet"));
		purple_wool.STRING.setIgnore();
		buildDefault(purple_wool);

		MaterialTextile magenta_wool = new MaterialTextile("magenta_wool", "minecraft");
		magenta_wool.BLOCK.setExists(TagUtil.mcLoc("magenta_wool"), TagUtil.mcLoc("magenta_wool"));
		magenta_wool.CARPET.setExists(TagUtil.mcLoc("magenta_carpet"), TagUtil.mcLoc("magenta_carpet"));
		magenta_wool.STRING.setIgnore();
		buildDefault(magenta_wool);

		MaterialTextile pink_wool = new MaterialTextile("pink_wool", "minecraft");
		pink_wool.BLOCK.setExists(TagUtil.mcLoc("pink_wool"), TagUtil.mcLoc("pink_wool"));
		pink_wool.CARPET.setExists(TagUtil.mcLoc("pink_carpet"), TagUtil.mcLoc("pink_carpet"));
		pink_wool.STRING.setIgnore();
		buildDefault(pink_wool);
	}

	private static void moddedDefaults() {
		MaterialMetal silver = new MaterialMetal("silver", "compendium");
		silver.tier = new CompendiumTier("GOLD");
		silver.BLOCK.setGenerate();
		silver.INGOT.setGenerate();
		silver.NUGGET.setGenerate();

		silver.addExtension(new ExtensionAdvancedTools().generateAll());

		buildDefault(silver);

		barnyardBuddies();
		delicateDyes();
		delicateDyesGingham();
		dyenamics();
		dyenamicsGingham();
		extraDelight();
		enchanted();
		arsNouveau();
		// bloomingNature();
		abyssalDecor();
		cluttered();
		cataclysm();
		hazennstuff();
		silentgear();
		koopascritters();
		butchercraft();
		moreSnifferFlowers();
		quark();
		heriosFloralExpansion();
		mysticsBiomes();
		would();
		theGreatOutdoors();
	}

	private static void theGreatOutdoors() {
		MaterialWood whitebark_pine = new MaterialWood("whitebark_pine", "the_great_outdoors");

		whitebark_pine.PLANKS.setExists(
				ResourceLocation.fromNamespaceAndPath("the_great_outdoors", "whitebark_pine_planks"),
				ResourceLocation.fromNamespaceAndPath("the_great_outdoors", "whitebark_pine_planks"));
		whitebark_pine.LOG.setExists(ResourceLocation.fromNamespaceAndPath("the_great_outdoors", "whitebark_pine_log"),
				ResourceLocation.fromNamespaceAndPath("the_great_outdoors", "whitebark_pine_log"));
		whitebark_pine.STRIPPED_LOG.setExists(
				ResourceLocation.fromNamespaceAndPath("the_great_outdoors", "stripped_whitebark_pine_log"),
				ResourceLocation.fromNamespaceAndPath("the_great_outdoors", "stripped_whitebark_pine_log"));
		whitebark_pine.WOOD.setExists(
				ResourceLocation.fromNamespaceAndPath("the_great_outdoors", "whitebark_pine_wood"),
				ResourceLocation.fromNamespaceAndPath("the_great_outdoors", "whitebark_pine_wood"));
		whitebark_pine.STRIPPED_WOOD.setExists(
				ResourceLocation.fromNamespaceAndPath("the_great_outdoors", "stripped_whitebark_pine_wood"),
				ResourceLocation.fromNamespaceAndPath("the_great_outdoors", "stripped_whitebark_pine_wood"));

		whitebark_pine.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("the_great_outdoors", "block/whitebarkpineplanks"),
				ResourceLocation.fromNamespaceAndPath("the_great_outdoors", "block/whitebarkpinelogside"),
				ResourceLocation.fromNamespaceAndPath("the_great_outdoors", "block/whitebarkpinelogstrippedside"),
				ResourceLocation.fromNamespaceAndPath("the_great_outdoors", "block/whitebarkpinelogtop"),
				ResourceLocation.fromNamespaceAndPath("the_great_outdoors", "block/whitebarkpinelogtopstripped")));
		buildDefault(whitebark_pine.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()),
				"the_great_outdoors");
	}

	private static void mysticsBiomes() {
		MaterialWood black_cherry = new MaterialWood("black_cherry", "mysticsbiomes");

		black_cherry.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "black_cherry_planks"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "black_cherry_planks"));
		black_cherry.LOG.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "black_cherry_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "black_cherry_log"));
		black_cherry.STRIPPED_LOG.setExists(
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_black_cherry_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_black_cherry_log"));
		black_cherry.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "black_cherry_wood"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "black_cherry_wood"));
		black_cherry.STRIPPED_WOOD.setExists(
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_black_cherry_wood"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_black_cherry_wood"));

		black_cherry.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/black_cherry_planks"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/black_cherry_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/stripped_black_cherry_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/black_cherry_log_top"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/stripped_black_cherry_log_top")));
		buildDefault(black_cherry.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()),
				"mysticsbiomes");

		MaterialWood lavender = new MaterialWood("lavender", "mysticsbiomes");

		lavender.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "lavender_planks"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "lavender_planks"));
		lavender.LOG.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "lavender_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "lavender_log"));
		lavender.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_lavender_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_lavender_log"));
		lavender.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "lavender_wood"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "lavender_wood"));
		lavender.STRIPPED_WOOD.setExists(
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_lavender_wood"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_lavender_wood"));

		lavender.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/lavender_planks"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/lavender_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/stripped_lavender_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/lavender_log_top"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/stripped_lavender_log_top")));
		buildDefault(lavender.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()),
				"mysticsbiomes");

		MaterialWood maple = new MaterialWood("maple", "mysticsbiomes");

		maple.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "maple_planks"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "maple_planks"));
		maple.LOG.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "maple_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "maple_log"));
		maple.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_maple_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_maple_log"));
		maple.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "maple_wood"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "maple_wood"));
		maple.STRIPPED_WOOD.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_maple_wood"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_maple_wood"));

		maple.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/maple_planks"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/maple_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/stripped_maple_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/maple_log_top"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/stripped_maple_log_top")));
		buildDefault(maple.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()),
				"mysticsbiomes");

		MaterialWood white_maple = new MaterialWood("white_maple", "mysticsbiomes");

		white_maple.PLANKS.setIgnore();
		white_maple.LOG.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "white_maple_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "white_maple_log"));
		white_maple.STRIPPED_LOG.setIgnore();
		white_maple.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "white_maple_wood"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "white_maple_wood"));
		white_maple.STRIPPED_WOOD.setIgnore();

		white_maple.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/maple_planks"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/white_maple_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/stripped_maple_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/white_maple_log_top"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/stripped_maple_log_top")));
		buildDefault(white_maple.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()),
				"mysticsbiomes");

		MaterialWood peach = new MaterialWood("ebony", "mysticsbiomes");

		peach.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "peach_planks"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "peach_planks"));
		peach.LOG.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "peach_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "peach_log"));
		peach.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_peach_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_peach_log"));
		peach.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "peach_wood"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "peach_wood"));
		peach.STRIPPED_WOOD.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_peach_wood"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_peach_wood"));

		peach.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/peach_planks"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/peach_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/stripped_peach_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/peach_log_top"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/stripped_peach_log_top")));
		buildDefault(peach.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()),
				"mysticsbiomes");

		MaterialWood sea_foam = new MaterialWood("ebony", "mysticsbiomes");

		sea_foam.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "sea_foam_planks"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "sea_foam_planks"));
		sea_foam.LOG.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "sea_foam_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "sea_foam_log"));
		sea_foam.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_sea_foam_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_sea_foam_log"));
		sea_foam.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "sea_foam_wood"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "sea_foam_wood"));
		sea_foam.STRIPPED_WOOD.setExists(
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_sea_foam_wood"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_sea_foam_wood"));

		sea_foam.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/sea_foam_planks"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/sea_foam_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/stripped_sea_foam_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/sea_foam_log_top"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/stripped_sea_foam_log_top")));
		buildDefault(sea_foam.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()),
				"mysticsbiomes");

		MaterialWood strawberry = new MaterialWood("ebony", "mysticsbiomes");

		strawberry.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "strawberry_planks"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "strawberry_planks"));
		strawberry.LOG.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "strawberry_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "strawberry_log"));
		strawberry.STRIPPED_LOG.setExists(
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_strawberry_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_strawberry_log"));
		strawberry.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "strawberry_wood"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "strawberry_wood"));
		strawberry.STRIPPED_WOOD.setExists(
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_strawberry_wood"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_strawberry_wood"));

		strawberry.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/strawberry_planks"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/strawberry_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/stripped_strawberry_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/strawberry_log_top"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/stripped_strawberry_log_top")));
		buildDefault(strawberry.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()),
				"mysticsbiomes");

		MaterialWood tropical = new MaterialWood("ebony", "mysticsbiomes");

		tropical.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "tropical_planks"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "tropical_planks"));
		tropical.LOG.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "tropical_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "tropical_log"));
		tropical.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_tropical_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_tropical_log"));
		tropical.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "tropical_wood"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "tropical_wood"));
		tropical.STRIPPED_WOOD.setExists(
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_tropical_wood"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_tropical_wood"));

		tropical.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/tropical_planks"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/tropical_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/stripped_tropical_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/tropical_log_top"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/stripped_tropical_log_top")));
		buildDefault(tropical.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()),
				"mysticsbiomes");

		MaterialWood vanilla = new MaterialWood("ebony", "mysticsbiomes");

		vanilla.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "vanilla_planks"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "vanilla_planks"));
		vanilla.LOG.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "vanilla_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "vanilla_log"));
		vanilla.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_vanilla_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_vanilla_log"));
		vanilla.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "vanilla_wood"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "vanilla_wood"));
		vanilla.STRIPPED_WOOD.setExists(ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_vanilla_wood"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "stripped_vanilla_wood"));

		vanilla.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/vanilla_planks"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/vanilla_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/stripped_vanilla_log"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/vanilla_log_top"),
				ResourceLocation.fromNamespaceAndPath("mysticsbiomes", "block/stripped_vanilla_log_top")));
		buildDefault(vanilla.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()),
				"mysticsbiomes");
	}

	private static void would() {
		MaterialWood aspen = new MaterialWood("aspen", "would");

		aspen.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("would", "aspen_planks"),
				ResourceLocation.fromNamespaceAndPath("would", "aspen_planks"));
		aspen.LOG.setExists(ResourceLocation.fromNamespaceAndPath("would", "aspen_log"),
				ResourceLocation.fromNamespaceAndPath("would", "aspen_log"));
		aspen.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("would", "stripped_aspen_log"),
				ResourceLocation.fromNamespaceAndPath("would", "stripped_aspen_log"));
		aspen.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("would", "aspen_wood"),
				ResourceLocation.fromNamespaceAndPath("would", "aspen_wood"));
		aspen.STRIPPED_WOOD.setExists(ResourceLocation.fromNamespaceAndPath("would", "stripped_azalea_wood"),
				ResourceLocation.fromNamespaceAndPath("would", "stripped_aspen_wood"));

		aspen.specialLocations = new SpecialLocationsWood(
				new SpecialTextureLocationsWood(ResourceLocation.fromNamespaceAndPath("would", "block/aspen_planks"),
						ResourceLocation.fromNamespaceAndPath("would", "block/aspen_log"),
						ResourceLocation.fromNamespaceAndPath("would", "block/stripped_aspen_log"),
						ResourceLocation.fromNamespaceAndPath("would", "block/aspen_log_top"),
						ResourceLocation.fromNamespaceAndPath("would", "block/stripped_aspen_log_top")));
		buildDefault(aspen.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()), "would");

		MaterialWood azalea = new MaterialWood("azalea", "would");

		azalea.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("would", "azalea_planks"),
				ResourceLocation.fromNamespaceAndPath("would", "azalea_planks"));
		azalea.LOG.setExists(ResourceLocation.fromNamespaceAndPath("would", "azalea_log"),
				ResourceLocation.fromNamespaceAndPath("would", "azalea_log"));
		azalea.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("would", "stripped_azalea_log"),
				ResourceLocation.fromNamespaceAndPath("would", "stripped_azalea_log"));
		azalea.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("would", "azalea_wood"),
				ResourceLocation.fromNamespaceAndPath("would", "azalea_wood"));
		azalea.STRIPPED_WOOD.setExists(ResourceLocation.fromNamespaceAndPath("would", "stripped_azalea_wood"),
				ResourceLocation.fromNamespaceAndPath("would", "stripped_azalea_wood"));

		azalea.specialLocations = new SpecialLocationsWood(
				new SpecialTextureLocationsWood(ResourceLocation.fromNamespaceAndPath("would", "block/azalea_planks"),
						ResourceLocation.fromNamespaceAndPath("would", "block/azalea_log"),
						ResourceLocation.fromNamespaceAndPath("would", "block/stripped_azalea_log"),
						ResourceLocation.fromNamespaceAndPath("would", "block/azalea_log_top"),
						ResourceLocation.fromNamespaceAndPath("would", "block/stripped_azalea_log_top")));
		buildDefault(azalea.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()), "would");

		MaterialWood baobab = new MaterialWood("baobab", "would");

		baobab.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("would", "baobab_planks"),
				ResourceLocation.fromNamespaceAndPath("would", "baobab_planks"));
		baobab.LOG.setExists(ResourceLocation.fromNamespaceAndPath("would", "baobab_log"),
				ResourceLocation.fromNamespaceAndPath("would", "baobab_log"));
		baobab.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("would", "stripped_baobab_log"),
				ResourceLocation.fromNamespaceAndPath("would", "stripped_baobab_log"));
		baobab.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("would", "baobab_wood"),
				ResourceLocation.fromNamespaceAndPath("would", "baobab_wood"));
		baobab.STRIPPED_WOOD.setExists(ResourceLocation.fromNamespaceAndPath("would", "stripped_baobab_wood"),
				ResourceLocation.fromNamespaceAndPath("would", "stripped_baobab_wood"));

		baobab.specialLocations = new SpecialLocationsWood(
				new SpecialTextureLocationsWood(ResourceLocation.fromNamespaceAndPath("would", "block/baobab_planks"),
						ResourceLocation.fromNamespaceAndPath("would", "block/baobab_log"),
						ResourceLocation.fromNamespaceAndPath("would", "block/stripped_baobab_log"),
						ResourceLocation.fromNamespaceAndPath("would", "block/baobab_log_top"),
						ResourceLocation.fromNamespaceAndPath("would", "block/stripped_baobab_log_top")));
		buildDefault(baobab.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()), "would");

		MaterialWood blue_spruce = new MaterialWood("blue_spruce", "would");

		blue_spruce.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("would", "blue_spruce_planks"),
				ResourceLocation.fromNamespaceAndPath("would", "blue_spruce_planks"));
		blue_spruce.LOG.setExists(ResourceLocation.fromNamespaceAndPath("would", "blue_spruce_log"),
				ResourceLocation.fromNamespaceAndPath("would", "blue_spruce_log"));
		blue_spruce.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("would", "stripped_blue_spruce_log"),
				ResourceLocation.fromNamespaceAndPath("would", "stripped_blue_spruce_log"));
		blue_spruce.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("would", "blue_spruce_wood"),
				ResourceLocation.fromNamespaceAndPath("would", "blue_spruce_wood"));
		blue_spruce.STRIPPED_WOOD.setExists(ResourceLocation.fromNamespaceAndPath("would", "stripped_blue_spruce_wood"),
				ResourceLocation.fromNamespaceAndPath("would", "stripped_blue_spruce_wood"));

		blue_spruce.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("would", "block/blue_spruce_planks"),
				ResourceLocation.fromNamespaceAndPath("would", "block/blue_spruce_log"),
				ResourceLocation.fromNamespaceAndPath("would", "block/stripped_blue_spruce_log"),
				ResourceLocation.fromNamespaceAndPath("would", "block/blue_spruce_log_top"),
				ResourceLocation.fromNamespaceAndPath("would", "block/stripped_blue_spruce_log_top")));
		buildDefault(blue_spruce.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()),
				"would");

		MaterialWood cedar = new MaterialWood("cedar", "would");

		cedar.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("would", "cedar_planks"),
				ResourceLocation.fromNamespaceAndPath("would", "cedar_planks"));
		cedar.LOG.setExists(ResourceLocation.fromNamespaceAndPath("would", "cedar_log"),
				ResourceLocation.fromNamespaceAndPath("would", "cedar_log"));
		cedar.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("would", "stripped_cedar_log"),
				ResourceLocation.fromNamespaceAndPath("would", "stripped_cedar_log"));
		cedar.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("would", "cedar_wood"),
				ResourceLocation.fromNamespaceAndPath("would", "cedar_wood"));
		cedar.STRIPPED_WOOD.setExists(ResourceLocation.fromNamespaceAndPath("would", "stripped_cedar_wood"),
				ResourceLocation.fromNamespaceAndPath("would", "stripped_cedar_wood"));

		cedar.specialLocations = new SpecialLocationsWood(
				new SpecialTextureLocationsWood(ResourceLocation.fromNamespaceAndPath("would", "block/cedar_planks"),
						ResourceLocation.fromNamespaceAndPath("would", "block/cedar_log"),
						ResourceLocation.fromNamespaceAndPath("would", "block/stripped_cedar_log"),
						ResourceLocation.fromNamespaceAndPath("would", "block/cedar_log_top"),
						ResourceLocation.fromNamespaceAndPath("would", "block/stripped_cedar_log_top")));
		buildDefault(cedar.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()), "would");

		MaterialWood ebony = new MaterialWood("ebony", "would");

		ebony.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("would", "ebony_planks"),
				ResourceLocation.fromNamespaceAndPath("would", "ebony_planks"));
		ebony.LOG.setExists(ResourceLocation.fromNamespaceAndPath("would", "ebony_log"),
				ResourceLocation.fromNamespaceAndPath("would", "ebony_log"));
		ebony.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("would", "stripped_ebony_log"),
				ResourceLocation.fromNamespaceAndPath("would", "stripped_ebony_log"));
		ebony.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("would", "ebony_wood"),
				ResourceLocation.fromNamespaceAndPath("would", "ebony_wood"));
		ebony.STRIPPED_WOOD.setExists(ResourceLocation.fromNamespaceAndPath("would", "stripped_ebony_wood"),
				ResourceLocation.fromNamespaceAndPath("would", "stripped_ebony_wood"));

		ebony.specialLocations = new SpecialLocationsWood(
				new SpecialTextureLocationsWood(ResourceLocation.fromNamespaceAndPath("would", "block/ebony_planks"),
						ResourceLocation.fromNamespaceAndPath("would", "block/ebony_log"),
						ResourceLocation.fromNamespaceAndPath("would", "block/stripped_ebony_log"),
						ResourceLocation.fromNamespaceAndPath("would", "block/ebony_log_top"),
						ResourceLocation.fromNamespaceAndPath("would", "block/stripped_ebony_log_top")));
		buildDefault(ebony.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()), "would");

		MaterialWood fir = new MaterialWood("fir", "would");

		fir.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("would", "fir_planks"),
				ResourceLocation.fromNamespaceAndPath("would", "fir_planks"));
		fir.LOG.setExists(ResourceLocation.fromNamespaceAndPath("would", "fir_log"),
				ResourceLocation.fromNamespaceAndPath("would", "fir_log"));
		fir.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("would", "stripped_fir_log"),
				ResourceLocation.fromNamespaceAndPath("would", "stripped_fir_log"));
		fir.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("would", "fir_wood"),
				ResourceLocation.fromNamespaceAndPath("would", "fir_wood"));
		fir.STRIPPED_WOOD.setExists(ResourceLocation.fromNamespaceAndPath("would", "stripped_fir_wood"),
				ResourceLocation.fromNamespaceAndPath("would", "stripped_fir_wood"));

		fir.specialLocations = new SpecialLocationsWood(
				new SpecialTextureLocationsWood(ResourceLocation.fromNamespaceAndPath("would", "block/fir_planks"),
						ResourceLocation.fromNamespaceAndPath("would", "block/fir_log"),
						ResourceLocation.fromNamespaceAndPath("would", "block/stripped_fir_log"),
						ResourceLocation.fromNamespaceAndPath("would", "block/fir_log_top"),
						ResourceLocation.fromNamespaceAndPath("would", "block/stripped_fir_log_top")));
		buildDefault(fir.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()), "would");

		MaterialWood mahogany = new MaterialWood("mahogany", "would");

		mahogany.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("would", "mahogany_planks"),
				ResourceLocation.fromNamespaceAndPath("would", "mahogany_planks"));
		mahogany.LOG.setExists(ResourceLocation.fromNamespaceAndPath("would", "mahogany_log"),
				ResourceLocation.fromNamespaceAndPath("would", "mahogany_log"));
		mahogany.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("would", "stripped_mahogany_log"),
				ResourceLocation.fromNamespaceAndPath("would", "stripped_mahogany_log"));
		mahogany.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("would", "mahogany_wood"),
				ResourceLocation.fromNamespaceAndPath("would", "mahogany_wood"));
		mahogany.STRIPPED_WOOD.setExists(ResourceLocation.fromNamespaceAndPath("would", "stripped_mahogany_wood"),
				ResourceLocation.fromNamespaceAndPath("would", "stripped_mahogany_wood"));

		mahogany.specialLocations = new SpecialLocationsWood(
				new SpecialTextureLocationsWood(ResourceLocation.fromNamespaceAndPath("would", "block/mahogany_planks"),
						ResourceLocation.fromNamespaceAndPath("would", "block/mahogany_log"),
						ResourceLocation.fromNamespaceAndPath("would", "block/stripped_mahogany_log"),
						ResourceLocation.fromNamespaceAndPath("would", "block/mahogany_log_top"),
						ResourceLocation.fromNamespaceAndPath("would", "block/stripped_mahogany_log_top")));
		buildDefault(mahogany.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()), "would");

		MaterialWood maple_w = new MaterialWood("maple_w", "would");

		maple_w.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("would", "maple_planks"),
				ResourceLocation.fromNamespaceAndPath("would", "maple_planks"));
		maple_w.LOG.setExists(ResourceLocation.fromNamespaceAndPath("would", "maple_log"),
				ResourceLocation.fromNamespaceAndPath("would", "maple_log"));
		maple_w.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("would", "stripped_maple_log"),
				ResourceLocation.fromNamespaceAndPath("would", "stripped_maple_log"));
		maple_w.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("would", "maple_wood"),
				ResourceLocation.fromNamespaceAndPath("would", "maple_wood"));
		maple_w.STRIPPED_WOOD.setExists(ResourceLocation.fromNamespaceAndPath("would", "stripped_maple_wood"),
				ResourceLocation.fromNamespaceAndPath("would", "stripped_maple_wood"));

		maple_w.specialLocations = new SpecialLocationsWood(
				new SpecialTextureLocationsWood(ResourceLocation.fromNamespaceAndPath("would", "block/maple_planks"),
						ResourceLocation.fromNamespaceAndPath("would", "block/maple_log"),
						ResourceLocation.fromNamespaceAndPath("would", "block/stripped_maple_log"),
						ResourceLocation.fromNamespaceAndPath("would", "block/maple_log_top"),
						ResourceLocation.fromNamespaceAndPath("would", "block/stripped_maple_log_top")));
		buildDefault(maple_w.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()), "would");

		MaterialWood palm = new MaterialWood("palm", "would");

		palm.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("would", "palm_planks"),
				ResourceLocation.fromNamespaceAndPath("would", "palm_planks"));
		palm.LOG.setExists(ResourceLocation.fromNamespaceAndPath("would", "palm_log"),
				ResourceLocation.fromNamespaceAndPath("would", "palm_log"));
		palm.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("would", "stripped_palm_log"),
				ResourceLocation.fromNamespaceAndPath("would", "stripped_palm_log"));
		palm.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("would", "palm_wood"),
				ResourceLocation.fromNamespaceAndPath("would", "palm_wood"));
		palm.STRIPPED_WOOD.setExists(ResourceLocation.fromNamespaceAndPath("would", "stripped_palm_wood"),
				ResourceLocation.fromNamespaceAndPath("would", "stripped_palm_wood"));

		palm.specialLocations = new SpecialLocationsWood(
				new SpecialTextureLocationsWood(ResourceLocation.fromNamespaceAndPath("would", "block/palm_planks"),
						ResourceLocation.fromNamespaceAndPath("would", "block/palm_log"),
						ResourceLocation.fromNamespaceAndPath("would", "block/stripped_palm_log"),
						ResourceLocation.fromNamespaceAndPath("would", "block/palm_log_top"),
						ResourceLocation.fromNamespaceAndPath("would", "block/stripped_palm_log_top")));
		buildDefault(palm.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()), "would");

		MaterialWood pine = new MaterialWood("pine", "would");

		pine.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("would", "pine_planks"),
				ResourceLocation.fromNamespaceAndPath("would", "pine_planks"));
		pine.LOG.setExists(ResourceLocation.fromNamespaceAndPath("would", "pine_log"),
				ResourceLocation.fromNamespaceAndPath("would", "pine_log"));
		pine.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("would", "stripped_pine_log"),
				ResourceLocation.fromNamespaceAndPath("would", "stripped_pine_log"));
		pine.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("would", "pine_wood"),
				ResourceLocation.fromNamespaceAndPath("would", "pine_wood"));
		pine.STRIPPED_WOOD.setExists(ResourceLocation.fromNamespaceAndPath("would", "stripped_pine_wood"),
				ResourceLocation.fromNamespaceAndPath("would", "stripped_pine_wood"));

		pine.specialLocations = new SpecialLocationsWood(
				new SpecialTextureLocationsWood(ResourceLocation.fromNamespaceAndPath("would", "block/pine_planks_0"),
						ResourceLocation.fromNamespaceAndPath("would", "block/pine_log"),
						ResourceLocation.fromNamespaceAndPath("would", "block/stripped_pine_log"),
						ResourceLocation.fromNamespaceAndPath("would", "block/pine_log_top"),
						ResourceLocation.fromNamespaceAndPath("would", "block/stripped_pine_log_top")));
		buildDefault(pine.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()), "would");

		MaterialWood walnut = new MaterialWood("walnut", "would");

		walnut.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("would", "walnut_planks"),
				ResourceLocation.fromNamespaceAndPath("would", "walnut_planks"));
		walnut.LOG.setExists(ResourceLocation.fromNamespaceAndPath("would", "walnut_log"),
				ResourceLocation.fromNamespaceAndPath("would", "walnut_log"));
		walnut.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("would", "stripped_walnut_log"),
				ResourceLocation.fromNamespaceAndPath("would", "stripped_walnut_log"));
		walnut.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("would", "walnut_wood"),
				ResourceLocation.fromNamespaceAndPath("would", "walnut_wood"));
		walnut.STRIPPED_WOOD.setExists(ResourceLocation.fromNamespaceAndPath("would", "stripped_walnut_wood"),
				ResourceLocation.fromNamespaceAndPath("would", "stripped_walnut_wood"));

		walnut.specialLocations = new SpecialLocationsWood(
				new SpecialTextureLocationsWood(ResourceLocation.fromNamespaceAndPath("would", "block/walnut_planks"),
						ResourceLocation.fromNamespaceAndPath("would", "block/walnut_log"),
						ResourceLocation.fromNamespaceAndPath("would", "block/stripped_walnut_log"),
						ResourceLocation.fromNamespaceAndPath("would", "block/walnut_log_top"),
						ResourceLocation.fromNamespaceAndPath("would", "block/stripped_walnut_log_top")));
		buildDefault(walnut.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()), "would");

		MaterialWood willow_w = new MaterialWood("willow_w", "would");

		willow_w.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("would", "willow_planks"),
				ResourceLocation.fromNamespaceAndPath("would", "willow_planks"));
		willow_w.LOG.setExists(ResourceLocation.fromNamespaceAndPath("would", "willow_log"),
				ResourceLocation.fromNamespaceAndPath("would", "willow_log"));
		willow_w.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("would", "stripped_willow_log"),
				ResourceLocation.fromNamespaceAndPath("would", "stripped_willow_log"));
		willow_w.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("would", "willow_wood"),
				ResourceLocation.fromNamespaceAndPath("would", "willow_wood"));
		willow_w.STRIPPED_WOOD.setExists(ResourceLocation.fromNamespaceAndPath("would", "stripped_willow_wood"),
				ResourceLocation.fromNamespaceAndPath("would", "stripped_willow_wood"));

		willow_w.specialLocations = new SpecialLocationsWood(
				new SpecialTextureLocationsWood(ResourceLocation.fromNamespaceAndPath("would", "block/willow_planks"),
						ResourceLocation.fromNamespaceAndPath("would", "block/willow_log"),
						ResourceLocation.fromNamespaceAndPath("would", "block/stripped_willow_log"),
						ResourceLocation.fromNamespaceAndPath("would", "block/willow_log_top"),
						ResourceLocation.fromNamespaceAndPath("would", "block/stripped_willow_log_top")));
		buildDefault(willow_w.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()), "would");
	}

	private static void heriosFloralExpansion() {
		MaterialWood giant_stem = new MaterialWood("giant_stem", "herios_floral_expansion");

		giant_stem.PLANKS.setExists(
				ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "giant_stem_planks"),
				ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "giant_stem_planks"));
		giant_stem.LOG.setExists(ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "giant_stem_log"),
				ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "giant_stem_log"));
		giant_stem.STRIPPED_LOG.setExists(
				ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "stripped_giant_stem_log"),
				ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "stripped_giant_stem_log"));
		giant_stem.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "giant_stem_wood"),
				ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "giant_stem_wood"));
		giant_stem.STRIPPED_WOOD.setExists(
				ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "stripped_giant_stem_wood"),
				ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "stripped_giant_stem_wood"));

		giant_stem.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "block/giantstem_planks"),
				ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "block/giantstem_log"),
				ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "block/stripped_giantstem_log"),
				ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "block/giant_stem_log_top"),
				ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "block/stripped_giant_stem_log_top")));
		buildDefault(giant_stem.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()),
				"herios_floral_expansion");

		MaterialWood dried_stem = new MaterialWood("dried_stem", "herios_floral_expansion");

		dried_stem.PLANKS.setExists(
				ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "dried_stem_planks"),
				ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "dried_stem_planks"));
		dried_stem.LOG.setExists(ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "dried_stem_log"),
				ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "dried_stem_log"));
		dried_stem.STRIPPED_LOG.setExists(
				ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "stripped_dried_stem_log"),
				ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "stripped_dried_stem_log"));
		dried_stem.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "dried_stem_wood"),
				ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "dried_stem_wood"));
		dried_stem.STRIPPED_WOOD.setExists(
				ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "stripped_dried_stem_wood"),
				ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "stripped_dried_stem_wood"));

		dried_stem.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "block/driedstem_planks"),
				ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "block/driedstem_log"),
				ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "block/stripped_driedstem_log"),
				ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "block/dried_stem_log_top"),
				ResourceLocation.fromNamespaceAndPath("herios_floral_expansion", "block/stripped_dried_stem_log_top")));
		buildDefault(dried_stem.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()),
				"herios_floral_expansion");
	}

	private static void quark() {
		MaterialWood trumpet = new MaterialWood("trumpet", "quark");

		trumpet.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("quark", "blossom_planks"),
				ResourceLocation.fromNamespaceAndPath("quark", "blossom_planks"));
		trumpet.LOG.setExists(ResourceLocation.fromNamespaceAndPath("quark", "blossom_log"),
				ResourceLocation.fromNamespaceAndPath("quark", "blossom_log"));
		trumpet.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("quark", "stripped_blossom_log"),
				ResourceLocation.fromNamespaceAndPath("quark", "stripped_blossom_log"));
		trumpet.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("quark", "blossom_wood"),
				ResourceLocation.fromNamespaceAndPath("quark", "blossom_wood"));
		trumpet.STRIPPED_WOOD.setExists(ResourceLocation.fromNamespaceAndPath("quark", "stripped_blossom_wood"),
				ResourceLocation.fromNamespaceAndPath("quark", "stripped_blossom_wood"));

		trumpet.specialLocations = new SpecialLocationsWood(
				new SpecialTextureLocationsWood(ResourceLocation.fromNamespaceAndPath("quark", "block/blossom_planks"),
						ResourceLocation.fromNamespaceAndPath("quark", "block/blossom_log"),
						ResourceLocation.fromNamespaceAndPath("quark", "block/stripped_blossom_log"),
						ResourceLocation.fromNamespaceAndPath("quark", "block/blossom_log_top"),
						ResourceLocation.fromNamespaceAndPath("quark", "block/stripped_blossom_log_top")));
		buildDefault(trumpet.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()), "quark");

		MaterialWood ashen = new MaterialWood("ashen", "quark");

		ashen.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("quark", "ancient_planks"),
				ResourceLocation.fromNamespaceAndPath("quark", "ancient_planks"));
		ashen.LOG.setExists(ResourceLocation.fromNamespaceAndPath("quark", "ancient_log"),
				ResourceLocation.fromNamespaceAndPath("quark", "ancient_log"));
		ashen.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("quark", "stripped_ancient_log"),
				ResourceLocation.fromNamespaceAndPath("quark", "stripped_ancient_log"));
		ashen.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("quark", "ancient_wood"),
				ResourceLocation.fromNamespaceAndPath("quark", "ancient_wood"));
		ashen.STRIPPED_WOOD.setExists(ResourceLocation.fromNamespaceAndPath("quark", "stripped_ancient_wood"),
				ResourceLocation.fromNamespaceAndPath("quark", "stripped_ancient_wood"));

		ashen.specialLocations = new SpecialLocationsWood(
				new SpecialTextureLocationsWood(ResourceLocation.fromNamespaceAndPath("quark", "block/ancient_planks"),
						ResourceLocation.fromNamespaceAndPath("quark", "block/ancient_log"),
						ResourceLocation.fromNamespaceAndPath("quark", "block/stripped_ancient_log"),
						ResourceLocation.fromNamespaceAndPath("quark", "block/ancient_log_top"),
						ResourceLocation.fromNamespaceAndPath("quark", "block/stripped_ancient_log_top")));
		buildDefault(ashen.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()), "quark");
	}

	private static void moreSnifferFlowers() {
		MaterialWood vivicus = new MaterialWood("vivicus", "moresnifferflowers");

		vivicus.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "vivicus_planks"),
				ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "vivicus_planks"));
		vivicus.LOG.setExists(ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "vivicus_log"),
				ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "vivicus_log"));
		vivicus.STRIPPED_LOG.setExists(
				ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "stripped_vivicus_log"),
				ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "stripped_vivicus_log"));
		vivicus.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "vivicus_wood"),
				ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "vivicus_wood"));
		vivicus.STRIPPED_WOOD.setExists(
				ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "stripped_vivicus_wood"),
				ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "stripped_vivicus_wood"));

		vivicus.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "block/vivicus_planks"),
				ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "block/vivicus_log"),
				ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "block/stripped_vivicus_log"),
				ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "block/vivicus_log_top"),
				ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "block/stripped_vivicus_log_top")));
		buildDefault(vivicus.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()),
				"moresnifferflowers");

		MaterialWood corrupted = new MaterialWood("corrupted", "moresnifferflowers");

		corrupted.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "corrupted_planks"),
				ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "corrupted_planks"));
		corrupted.LOG.setExists(ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "corrupted_log"),
				ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "corrupted_log"));
		corrupted.STRIPPED_LOG.setExists(
				ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "stripped_corrupted_log"),
				ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "stripped_corrupted_log"));
		corrupted.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "corrupted_wood"),
				ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "corrupted_wood"));
		corrupted.STRIPPED_WOOD.setExists(
				ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "stripped_corrupted_wood"),
				ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "stripped_corrupted_wood"));

		corrupted.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "block/corrupted_planks"),
				ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "block/corrupted_log"),
				ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "block/stripped_corrupted_log"),
				ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "block/corrupted_log_top"),
				ResourceLocation.fromNamespaceAndPath("moresnifferflowers", "block/stripped_corrupted_log_top")));
		buildDefault(corrupted.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()),
				"moresnifferflowers");
	}

	private static void butchercraft() {
		MaterialWood barn_wood = new MaterialWood("barn_wood", "butchercraft");
		barn_wood.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("butchercraft", "block/barn_wood_block"),
				ResourceLocation.fromNamespaceAndPath("compendium", "block/barn_wood_log"), null, null, null));

		barn_wood.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("butchercraft", "barn_wood_block"),
				ResourceLocation.fromNamespaceAndPath("butchercraft", "barn_wood_block_item"));

		buildDefault(barn_wood.addExtension(new ExtensionExtraPlanks().generateAll()), "butchercraft");
	}

	private static void koopascritters() {
		MaterialWood kopje = new MaterialWood("kopje", "koopascritters");

		kopje.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("koopascritters", "kopje_fig_planks"),
				ResourceLocation.fromNamespaceAndPath("koopascritters", "kopje_fig_planks"));
		kopje.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("koopascritters", "kopje_fig_log"),
				ResourceLocation.fromNamespaceAndPath("koopascritters", "kopje_fig_log"));
		kopje.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("koopascritters", "stripped_kopje_log"),
				ResourceLocation.fromNamespaceAndPath("koopascritters", "stripped_kopje_log"));

		kopje.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("koopascritters", "block/kopjeplanks"),
				ResourceLocation.fromNamespaceAndPath("koopascritters", "block/kopjelogside"),
				ResourceLocation.fromNamespaceAndPath("koopascritters", "block/kopjelogstrippedside"),
				ResourceLocation.fromNamespaceAndPath("koopascritters", "block/kopjelogtop"),
				ResourceLocation.fromNamespaceAndPath("koopascritters", "block/kopjelogstrippedtop")));
		buildDefault(kopje.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()),
				"koopascritters");
	}

	private static void silentgear() {
		MaterialWood netherwood = new MaterialWood("netherwood", "silentgear");

		netherwood.PLANKS.setExists(
				ResourceLocation.fromNamespaceAndPath("silentgear", "netherwood_planks"),
				ResourceLocation.fromNamespaceAndPath("silentgear", "netherwood_planks"));
		netherwood.PLANKS.setExists(
				ResourceLocation.fromNamespaceAndPath("silentgear", "netherwood_log"),
				ResourceLocation.fromNamespaceAndPath("silentgear", "netherwood_log"));
		netherwood.PLANKS.setExists(
				ResourceLocation.fromNamespaceAndPath("silentgear", "stripped_netherwood_log"),
				ResourceLocation.fromNamespaceAndPath("silentgear", "stripped_netherwood_log"));
		netherwood.WOOD.setExists(
				ResourceLocation.fromNamespaceAndPath("silentgear", "corrupted_wood"),
				ResourceLocation.fromNamespaceAndPath("silentgear", "corrupted_wood"));
		netherwood.STRIPPED_WOOD.setExists(
				ResourceLocation.fromNamespaceAndPath("silentgear", "stripped_netherwood_wood"),
				ResourceLocation.fromNamespaceAndPath("silentgear", "stripped_netherwood_wood"));

		netherwood.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("silentgear", "block/ntherwood_planks"),
				ResourceLocation.fromNamespaceAndPath("silentgear", "block/netherwood_log"),
				ResourceLocation.fromNamespaceAndPath("silentgear", "block/stripped_netherwood_log"),
				ResourceLocation.fromNamespaceAndPath("silentgear", "block/netherwood_log_top"),
				ResourceLocation.fromNamespaceAndPath("silentgear", "block/stripped_netherwood_log_top")));
		buildDefault(new MaterialWood("netherwood", "silentgear").addExtension(new ExtensionExtraLogs().generateAll())
				.addExtension(new ExtensionExtraPlanks().generateAll()), "silentgear");
	}

	private static void hazennstuff() {
		buildDefault(new MaterialWood("frostbite_birch", "hazennstuff").addExtension(new ExtensionExtraLogs().generateAll())
				.addExtension(new ExtensionExtraPlanks().generateAll()), "hazennstuff");

		MaterialWood wisewood = new MaterialWood("wisewood", "hazennstuff");

		wisewood.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("hazennstuff", "wisewood_planks"),
				ResourceLocation.fromNamespaceAndPath("hazennstuff", "wisewood_planks"));
		wisewood.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("hazennstuff", "wisewood_log"),
				ResourceLocation.fromNamespaceAndPath("hazennstuff", "wisewood_log"));
		wisewood.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("hazennstuff", "stripped_wisewood_log"),
				ResourceLocation.fromNamespaceAndPath("hazennstuff", "stripped_wisewood_log"));
		wisewood.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("hazennstuff", "wisewood_wood"),
				ResourceLocation.fromNamespaceAndPath("hazennstuff", "wisewood_wood"));
		wisewood.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("hazennstuff", "stripped_wisewood_wood"),
				ResourceLocation.fromNamespaceAndPath("hazennstuff", "stripped_wisewood_wood"));

		wisewood.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "block/wisewood_planks"),
				ResourceLocation.fromNamespaceAndPath("hazennstuff", "block/wisewood_log_side"),
				ResourceLocation.fromNamespaceAndPath("hazennstuff", "block/stripped_wisewood_log_side"),
				ResourceLocation.fromNamespaceAndPath("hazennstuff", "block/wisewood_log_side"),
				ResourceLocation.fromNamespaceAndPath("hazennstuff", "block/stripped_wisewood_log_top")));
		buildDefault(wisewood.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()),
				"hazennstuff");
	}

	private static void cataclysm() {
		SpecialLocationsWood chorus = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("cataclysm", "block/chorus_stem"), null, null,
				ResourceLocation.fromNamespaceAndPath("compendium", "block/material/wood/chorus/logs/log_top"),
				ResourceLocation.fromNamespaceAndPath("compendium", "block/material/wood/chorus/logs/log_top")));
		buildDefault(new MaterialWood("chorus", "cataclysm", chorus).addExtension(new ExtensionExtraLogs().generateAll())
				.addExtension(new ExtensionExtraPlanks().generateAll()), "cataclysm");

	}

	public static void cluttered() {
		SpecialLocationsWood blue_mushroom_loc = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/blue_mushroom_planks"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/blue_mushroom_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/stripped_blue_mushroom_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/blue_mushroom_log_top"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/stripped_blue_mushroom_log_top")));

		MaterialWood blue_mushroom = new MaterialWood("blue_mushroom", "cluttered", blue_mushroom_loc);

		blue_mushroom.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "blue_mushroom_planks"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "blue_mushroom_planks"));
		blue_mushroom.LOG.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "blue_mushroom_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "blue_mushroom_log"));
//		blue_mushroom.STRIPPED_LOG.setExists(
//				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_blue_mushroom_log"),
//				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_blue_mushroom_log"));
		blue_mushroom.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "blue_mushroom_wood"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "blue_mushroom_wood"));
//		blue_mushroom.STRIPPED_WOOD.setExists(
//				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_blue_mushroom_log"),
//				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_blue_mushroom_log"));

		buildDefault(blue_mushroom.addExtension(new ExtensionExtraLogs().generateAll())
				.addExtension(new ExtensionExtraPlanks().generateAll()), "cluttered");

		SpecialLocationsWood crabapple_loc = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/crabapple_planks"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/crabapple_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/stripped_crabapple_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/crabapple_log_top"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/stripped_crabapple_log_top")));

		MaterialWood crabapple = new MaterialWood("crabapple", "cluttered", crabapple_loc);

		crabapple.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "crabapple_planks"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "crabapple_planks"));
		crabapple.LOG.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "crabapple_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "crabapple_log"));
		crabapple.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_crabapple_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_crabapple_log"));
		crabapple.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "crabapple_wood"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "crabapple_wood"));
		crabapple.STRIPPED_WOOD.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_crabapple_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_crabapple_log"));

		buildDefault(crabapple.addExtension(new ExtensionExtraLogs().generateAll())
				.addExtension(new ExtensionExtraPlanks().generateAll()), "cluttered");

		SpecialLocationsWood flowering_crabapple_loc = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/flowering_crabapple_planks"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/flowering_crabapple_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/stripped_flowering_crabapple_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/flowering_crabapple_log_top"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/stripped_flowering_crabapple_log_top")));

		MaterialWood flowering_crabapple = new MaterialWood("flowering_crabapple", "cluttered",
				flowering_crabapple_loc);

		flowering_crabapple.PLANKS.setExists(
				ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_crabapple_planks"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_crabapple_planks"));
		flowering_crabapple.LOG.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_crabapple_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_crabapple_log"));
		flowering_crabapple.STRIPPED_LOG.setExists(
				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_flowering_crabapple_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_flowering_crabapple_log"));
		flowering_crabapple.WOOD.setExists(
				ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_crabapple_wood"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_crabapple_wood"));
		flowering_crabapple.STRIPPED_WOOD.setExists(
				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_flowering_crabapple_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_flowering_crabapple_log"));

		buildDefault(flowering_crabapple.addExtension(new ExtensionExtraLogs().generateAll())
				.addExtension(new ExtensionExtraPlanks().generateAll()), "cluttered");

		SpecialLocationsWood fluorescent_maple_loc = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/fluorescent_maple_planks"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/fluorescent_maple_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/stripped_fluorescent_maple_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/fluorescent_maple_log_top"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/stripped_fluorescent_maple_log_top")));
		MaterialWood fluorescent_maple = new MaterialWood("fluorescent_maple", "cluttered", fluorescent_maple_loc);

		fluorescent_maple.PLANKS.setExists(
				ResourceLocation.fromNamespaceAndPath("cluttered", "fluorescent_maple_planks"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "fluorescent_maple_planks"));
		fluorescent_maple.LOG.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "fluorescent_maple_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "fluorescent_maple_log"));
		fluorescent_maple.STRIPPED_LOG.setExists(
				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_fluorescent_maple_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_fluorescent_maple_log"));
		fluorescent_maple.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "fluorescent_maple_wood"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "fluorescent_maple_wood"));
		fluorescent_maple.STRIPPED_WOOD.setExists(
				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_fluorescent_maple_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_fluorescent_maple_log"));

		buildDefault(fluorescent_maple.addExtension(new ExtensionExtraLogs().generateAll())
				.addExtension(new ExtensionExtraPlanks().generateAll()), "cluttered");

		SpecialLocationsWood poplar_loc = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/poplar_planks"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/poplar_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/stripped_poplar_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/poplar_log_top"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/stripped_poplar_log_top")));
		MaterialWood poplar = new MaterialWood("poplar", "cluttered", poplar_loc);

		poplar.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "poplar_planks"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "poplar_planks"));
		poplar.LOG.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "poplar_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "poplar_log"));
		poplar.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_poplar_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_poplar_log"));
		poplar.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "poplar_wood"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "poplar_wood"));
		poplar.STRIPPED_WOOD.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_poplar_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_poplar_log"));

		buildDefault(poplar.addExtension(new ExtensionExtraLogs().generateAll())
				.addExtension(new ExtensionExtraPlanks().generateAll()), "cluttered");

		SpecialLocationsWood flowering_poplar_loc = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/flowering_poplar_planks"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/flowering_poplar_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/stripped_flowering_poplar_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/flowering_poplar_log_top"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/stripped_flowering_poplar_log_top")));

		MaterialWood flowering_poplar = new MaterialWood("flowering_poplar", "cluttered", flowering_poplar_loc);

		flowering_poplar.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_poplar_planks"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_poplar_planks"));
		flowering_poplar.LOG.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_poplar_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_poplar_log"));
		flowering_poplar.STRIPPED_LOG.setExists(
				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_flowering_poplar_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_flowering_poplar_log"));
		flowering_poplar.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_poplar_wood"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_poplar_wood"));
		flowering_poplar.STRIPPED_WOOD.setExists(
				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_flowering_poplar_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_flowering_poplar_log"));

		buildDefault(flowering_poplar.addExtension(new ExtensionExtraLogs().generateAll())
				.addExtension(new ExtensionExtraPlanks().generateAll()), "cluttered");

		SpecialLocationsWood red_mushroom_loc = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/red_mushroom_planks"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/red_mushroom_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/stripped_red_mushroom_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/red_mushroom_log_top"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/stripped_red_mushroom_log_top")));

		MaterialWood red_mushroom = new MaterialWood("red_mushroom", "cluttered", red_mushroom_loc);

		red_mushroom.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "red_mushroom_planks"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "red_mushroom_planks"));
		red_mushroom.LOG.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "red_mushroom_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "red_mushroom_log"));
		red_mushroom.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "red_mushroom_wood"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "red_mushroom_wood"));

		buildDefault(red_mushroom.addExtension(new ExtensionExtraLogs().generateAll())
				.addExtension(new ExtensionExtraPlanks().generateAll()), "cluttered");

		SpecialLocationsWood sycamore_loc = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/sycamore_planks"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/sycamore_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/stripped_sycamore_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/sycamore_log_top"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/stripped_sycamore_log_top")));

		MaterialWood sycamore = new MaterialWood("sycamore", "cluttered", sycamore_loc);

		sycamore.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "sycamore_planks"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "sycamore_planks"));
		sycamore.LOG.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "sycamore_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "sycamore_log"));
		sycamore.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_sycamore_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_sycamore_log"));
		sycamore.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "sycamore_wood"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "sycamore_wood"));
		sycamore.STRIPPED_WOOD.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_sycamore_wood"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_sycamore_wood"));

		buildDefault(
				sycamore.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()),
				"cluttered");

		SpecialLocationsWood willow_loc = new SpecialLocationsWood(
				new SpecialTextureLocationsWood(ResourceLocation.fromNamespaceAndPath("cluttered", "block/willow_planks"),
						ResourceLocation.fromNamespaceAndPath("cluttered", "block/willow_log"),
						ResourceLocation.fromNamespaceAndPath("cluttered", "block/stripped_willow_log"),
						ResourceLocation.fromNamespaceAndPath("cluttered", "block/willow_log_top"),
						ResourceLocation.fromNamespaceAndPath("cluttered", "block/stripped_willow_log_top")));

		MaterialWood willow = new MaterialWood("willow", "cluttered", willow_loc);

		willow.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "willow_planks"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "willow_planks"));
		willow.LOG.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "willow_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "willow_log"));
		willow.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_willow_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_willow_log"));
		willow.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "willow_wood"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "willow_wood"));
		willow.STRIPPED_WOOD.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_willow_wood"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_willow_wood"));

		buildDefault(
				willow.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()),
				"cluttered");

		SpecialLocationsWood flowering_willow_loc = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/flowering_willow_planks"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/flowering_willow_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/stripped_flowering_willow_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/flowering_willow_log_top"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "block/stripped_flowering_willow_log_top")));

		MaterialWood flowering_willow = new MaterialWood("flowering_willow", "cluttered", flowering_willow_loc);

		flowering_willow.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_willow_planks"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_willow_planks"));
		flowering_willow.LOG.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_willow_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_willow_log"));
		flowering_willow.STRIPPED_LOG.setExists(
				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_flowering_willow_log"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_flowering_willow_log"));
		flowering_willow.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_willow_wood"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_willow_wood"));
		flowering_willow.STRIPPED_WOOD.setExists(
				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_flowering_willow_wood"),
				ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_flowering_willow_wood"));

		buildDefault(flowering_willow.addExtension(new ExtensionExtraLogs().generateAll())
				.addExtension(new ExtensionExtraPlanks().generateAll()), "cluttered");
	}

	public static void abyssalDecor() {
		// blackwood
		MaterialWood blackwood = new MaterialWood("blackwood", "abyssal_decor");

		blackwood.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("abyssal_decor", "blackwood_planks"),
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "blackwood_planks"));
		blackwood.LOG.setExists(ResourceLocation.fromNamespaceAndPath("abyssal_decor", "blackwood_log"),
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "blackwood_log"));
		blackwood.STRIPPED_LOG.setExists(
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "stripped_blackwood_log"),
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "stripped_blackwood_log"));
		blackwood.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("abyssal_decor", "blackwood_wood"),
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "blackwood_wood"));

		blackwood.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/blackwoodplanks"),
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/moldylogside"),
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/blackwoodstrippedlogside"),
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/moldylogtop"),
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/blackwoodstrippedlogtop")));

		buildDefault(blackwood.addExtension(new ExtensionExtraLogs().generateAll())
				.addExtension(new ExtensionExtraPlanks().generateAll()), "abyssal_decor");

		// cinnamon
		MaterialWood cinnamon_ad = new MaterialWood("cinnamon_ad", "abyssal_decor");

		cinnamon_ad.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("abyssal_decor", "cinnamon_planks"),
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "cinnamon_planks"));
		cinnamon_ad.LOG.setExists(ResourceLocation.fromNamespaceAndPath("abyssal_decor", "cinnamon_log"),
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "cinnamon_log"));
		cinnamon_ad.STRIPPED_LOG.setExists(
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "stripped_cinnamon_log"),
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "stripped_cinnamon_log"));
		cinnamon_ad.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("abyssal_decor", "cinnamon_wood"),
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "cinnamon_wood"));
		cinnamon_ad.STRIPPED_WOOD.setExists(
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "stripped_cinnamon_wood"),
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "stripped_cinnamon_wood"));

		cinnamon_ad.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/cinnamonplanks"),
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/cinnamonlogside"),
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/strippedcinnamonlogside"),
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/cinnamonlogtop"),
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/strippedcinnamonlogtop")));

		buildDefault(cinnamon_ad.addExtension(new ExtensionExtraLogs().generateAll())
				.addExtension(new ExtensionExtraPlanks().generateAll()), "abyssal_decor");

		MaterialWood whitewood = new MaterialWood("white_wood", "abyssal_decor");

		whitewood.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("abyssal_decor", "white_wood_planks"),
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "white_wood_planks"));
		whitewood.LOG.setExists(ResourceLocation.fromNamespaceAndPath("abyssal_decor", "white_wood_log"),
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "white_wood_log"));
		whitewood.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("abyssal_decor", "white_wood_wood"),
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "white_wood_wood"));

		whitewood.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/whitewoodplanks"), null,
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/whitewoodstrippedlog"),
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/whitewoodstrippedlogtop"),
				ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/whitewoodstrippedlogtop")));

		buildDefault(whitewood.addExtension(new ExtensionExtraLogs().generateAll())
				.addExtension(new ExtensionExtraPlanks().generateAll()), "abyssal_decor");
	}

	public static void bloomingNature() {
		MaterialWood aspen = new MaterialWood("aspen", "bloomingnature");
		aspen.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(null,
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/aspen_log_side"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/aspen_log_side_stripped"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/aspen_log_top"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/aspen_log_top_stripped")));
		buildDefault(aspen.addExtension(new ExtensionExtraLogs().generateAll())
				.addExtension(new ExtensionExtraPlanks().generateAll()), "bloomingnature");

		MaterialWood baobab = new MaterialWood("baobab", "bloomingnature");
		baobab.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(null,
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/baobab_log_side"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/baobab_log_side_stripped"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/baobab_log_top"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/baobab_log_top_stripped")));
		buildDefault(baobab.addExtension(new ExtensionExtraLogs().generateAll())
				.addExtension(new ExtensionExtraPlanks().generateAll()), "bloomingnature");

		MaterialWood cactus = new MaterialWood("cactus", "bloomingnature");
		cactus.LOG.setExists(ResourceLocation.fromNamespaceAndPath("minecraft", "cactus"),
				ResourceLocation.fromNamespaceAndPath("minecraft", "cactus"));
		cactus.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(null,
				ResourceLocation.fromNamespaceAndPath("compendium", "block/cactus_log"), null,
				ResourceLocation.fromNamespaceAndPath("compendium", "block/cactus_log_top"),
				ResourceLocation.fromNamespaceAndPath("compendium", "block/stripped_cactus_log_top")));

		buildDefault(cactus.addExtension(new ExtensionExtraLogs().generateAll())
				.addExtension(new ExtensionExtraPlanks().generateAll()), "bloomingnature");

		SpecialLocationsWood chestnut = new SpecialLocationsWood(new SpecialTextureLocationsWood(null,
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/chestnut_log_side"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/chestnut_log_side_stripped"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/chestnut_log_top"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/chestnut_log_top_stripped")));
		buildDefault(new MaterialWood("chestnut", "bloomingnature", chestnut)
				.addExtension(new ExtensionExtraLogs().generateAll())
				.addExtension(new ExtensionExtraPlanks().generateAll()), "bloomingnature");

		SpecialLocationsWood cypress = new SpecialLocationsWood(new SpecialTextureLocationsWood(null,
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/cypress_log_side"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/cypress_log_stripped_side"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/cypress_log_top"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/cypress_log_stripped_top")));
		buildDefault(new MaterialWood("cypress", "bloomingnature", cypress).addExtension(new ExtensionExtraLogs().generateAll())
				.addExtension(new ExtensionExtraPlanks().generateAll()), "bloomingnature");

		SpecialLocationsWood ebony = new SpecialLocationsWood(new SpecialTextureLocationsWood(null,
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/ebony_log_side"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/ebony_log_side_stripped"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/ebony_log_top"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/ebony_log_top_stripped")));
		buildDefault(new MaterialWood("ebony", "bloomingnature", ebony).addExtension(new ExtensionExtraLogs().generateAll())
				.addExtension(new ExtensionExtraPlanks().generateAll()), "bloomingnature");

		SpecialLocationsWood fan_palm = new SpecialLocationsWood(new SpecialTextureLocationsWood(null,
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/fan_palm_log_side"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/fan_palm_log_stripped"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/fan_palm_log_top"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/fan_palm_log_top_stripped")));
		buildDefault(new MaterialWood("fan_palm", "bloomingnature", fan_palm).addExtension(new ExtensionExtraLogs().generateAll())
				.addExtension(new ExtensionExtraPlanks().generateAll()), "bloomingnature");

		SpecialLocationsWood fir = new SpecialLocationsWood(new SpecialTextureLocationsWood(null,
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/fir_log_side"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/fir_log_side_stripped"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/fir_log_top"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/fir_log_top_stripped")));
		buildDefault(new MaterialWood("fir", "bloomingnature", fir).addExtension(new ExtensionExtraLogs().generateAll())
				.addExtension(new ExtensionExtraPlanks().generateAll()), "bloomingnature");

		SpecialLocationsWood larch = new SpecialLocationsWood(new SpecialTextureLocationsWood(null,
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/larch_log_side"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/larch_log_stripped"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/larch_log_top"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/larch_log_stripped_top")));
		buildDefault(new MaterialWood("larch", "bloomingnature", larch).addExtension(new ExtensionExtraLogs().generateAll())
				.addExtension(new ExtensionExtraPlanks().generateAll()), "bloomingnature");

		MaterialWood swamp_cypress = new MaterialWood("swamp_cypress", "bloomingnature");

		swamp_cypress.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("bloomingnature", "swamp_cypress_planks"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "swamp_cypress_planks"));
		swamp_cypress.LOG.setExists(ResourceLocation.fromNamespaceAndPath("bloomingnature", "swamp_cypress_log"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "swamp_cypress_log"));
		swamp_cypress.STRIPPED_LOG.setExists(
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "stripped_swamp_cypress_log"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "stripped_swamp_cypress_log"));
		swamp_cypress.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("bloomingnature", "swamp_cypress_wood"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "swamp_cypress_wood"));
		swamp_cypress.STRIPPED_WOOD.setExists(
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "stripped_swamp_cypress_wood"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "stripped_swamp_cypress_wood"));

		swamp_cypress.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(null,
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/swamp_cypress_log_side"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/swamp_cypress_log_stripped_side"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/swamp_cypress_log_top"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/swamp_cypress_log_stripped_top")));
		buildDefault(swamp_cypress.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()),
				"bloomingnature");

		MaterialWood swamp_oak = new MaterialWood("swamp_oak", "bloomingnature");

		swamp_oak.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("bloomingnature", "swamp_oak_planks"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "swamp_oak_planks"));
		swamp_oak.LOG.setExists(ResourceLocation.fromNamespaceAndPath("bloomingnature", "swamp_oak_log"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "swamp_oak_log"));
		swamp_oak.STRIPPED_LOG.setExists(
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "stripped_swamp_oak_log"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "stripped_swamp_oak_log"));
		swamp_oak.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("bloomingnature", "swamp_oak_wood"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "swamp_oak_wood"));
		swamp_oak.STRIPPED_WOOD.setExists(
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "stripped_swamp_oak_wood"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "stripped_swamp_oak_wood"));

		swamp_oak.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(null,
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/swamp_oak_log_side"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/swamp_oak_log_stripped"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/swamp_oak_log_top"),
				ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/swamp_oak_log_stripped_top")));
		buildDefault(swamp_oak.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()),
				"bloomingnature");
	}

	public static void arsNouveau() {

		MaterialWood archwood = new MaterialWood("archwood", "ars_nouveau");

		archwood.specialLocations = new SpecialLocationsWood(new SpecialTextureLocationsWood(
				ResourceLocation.fromNamespaceAndPath("ars_nouveau", "block/archwood_planks"),
				ResourceLocation.fromNamespaceAndPath("ars_nouveau", "block/archwood_log_side"),
				ResourceLocation.fromNamespaceAndPath("ars_nouveau", "block/archwood_log_stripped"),
				ResourceLocation.fromNamespaceAndPath("ars_nouveau", "block/archwood_log_top"),
				ResourceLocation.fromNamespaceAndPath("ars_nouveau", "block/archwood_log_stripped_top")));

		archwood.addExtension(new ExtensionExtraLogs().generateAll());
		archwood.addExtension(new ExtensionExtraPlanks().generateAll());
		buildDefault(archwood, "ars_nouveau");
	}

	public static void enchanted() {
		MaterialWood alder = new MaterialWood("alder", "enchanted");

		alder.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("enchanted", "alder_planks"),
				ResourceLocation.fromNamespaceAndPath("enchanted", "alder_planks"));
		alder.LOG.setExists(ResourceLocation.fromNamespaceAndPath("enchanted", "alder_log"),
				ResourceLocation.fromNamespaceAndPath("enchanted", "alder_log"));
		alder.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("enchanted", "stripped_alder_log"),
				ResourceLocation.fromNamespaceAndPath("enchanted", "stripped_alder_log"));

		buildDefault(alder.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()),
				"enchanted");

		MaterialWood hawthorn = new MaterialWood("hawthorn", "enchanted");

		hawthorn.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("enchanted", "hawthorn_planks"),
				ResourceLocation.fromNamespaceAndPath("enchanted", "hawthorn_planks"));
		hawthorn.LOG.setExists(ResourceLocation.fromNamespaceAndPath("enchanted", "hawthorn_log"),
				ResourceLocation.fromNamespaceAndPath("enchanted", "hawthorn_log"));
		hawthorn.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("enchanted", "stripped_hawthorn_log"),
				ResourceLocation.fromNamespaceAndPath("enchanted", "stripped_hawthorn_log"));

		buildDefault(hawthorn.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()),
				"enchanted");

		MaterialWood rowan = new MaterialWood("rowan", "enchanted");

		rowan.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("enchanted", "rowan_planks"),
				ResourceLocation.fromNamespaceAndPath("enchanted", "rowan_planks"));
		rowan.LOG.setExists(ResourceLocation.fromNamespaceAndPath("enchanted", "rowan_log"),
				ResourceLocation.fromNamespaceAndPath("enchanted", "rowan_log"));
		rowan.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("enchanted", "stripped_rowan_log"),
				ResourceLocation.fromNamespaceAndPath("enchanted", "stripped_rowan_log"));

		buildDefault(rowan.addExtension(new ExtensionExtraLogs().generateAll()).addExtension(new ExtensionExtraPlanks().generateAll()),
				"enchanted");
	}

	public static void extraDelight() {
		MaterialWood cinnamon = new MaterialWood("cinnamon", "extradelight");

		cinnamon.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("extradelight", "cinnamon_planks"),
				ResourceLocation.fromNamespaceAndPath("extradelight", "cinnamon_planks"));
		cinnamon.LOG.setExists(ResourceLocation.fromNamespaceAndPath("extradelight", "cinnamon_log"),
				ResourceLocation.fromNamespaceAndPath("extradelight", "cinnamon_log"));
		cinnamon.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("extradelight", "stripped_cinnamon_log"),
				ResourceLocation.fromNamespaceAndPath("extradelight", "stripped_cinnamon_log"));
		cinnamon.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("extradelight", "cinnamon_wood"),
				ResourceLocation.fromNamespaceAndPath("extradelight", "cinnamon_wood"));
		cinnamon.STRIPPED_WOOD.setExists(ResourceLocation.fromNamespaceAndPath("extradelight", "stripped_cinnamon_wood"),
				ResourceLocation.fromNamespaceAndPath("extradelight", "strippedcinnamon_wood"));

		cinnamon.specialLocations = new SpecialLocationsWood(
				new SpecialTextureLocationsWood(ResourceLocation.fromNamespaceAndPath("extradelight", "block/cinnamon_planks"),
						ResourceLocation.fromNamespaceAndPath("extradelight", "block/cinnamon_log"),
						ResourceLocation.fromNamespaceAndPath("extradelight", "block/stripped_cinnamon_log"),
						ResourceLocation.fromNamespaceAndPath("extradelight", "block/cinnamon_log_top"),
						ResourceLocation.fromNamespaceAndPath("extradelight", "block/stripped_cinnamon_log_top")));
		buildDefault(new MaterialWood("cinnamon", "extradelight").addExtension(new ExtensionExtraLogs().generateAll())
				.addExtension(new ExtensionExtraPlanks().generateAll()), "extradelight");

		MaterialTextile gingham_white = new MaterialTextile("gingham_white", "extradelight",
				new SpecialLocationsTextile(new SpecialTextureLocationsTextile(
						ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/white"), null, null)));
		gingham_white.BLOCK.setGenerate();
		gingham_white.CARPET.setGenerate();
		buildDefault(gingham_white, "extradelight");

		MaterialTextile gingham_light_gray = new MaterialTextile("gingham_light_gray", "extradelight",
				new SpecialLocationsTextile(new SpecialTextureLocationsTextile(
						ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/light_gray"), null,
						null)));
		gingham_light_gray.BLOCK.setGenerate();
		gingham_light_gray.CARPET.setGenerate();
		buildDefault(gingham_light_gray, "extradelight");

		MaterialTextile gingham_gray = new MaterialTextile("gingham_gray", "extradelight",
				new SpecialLocationsTextile(new SpecialTextureLocationsTextile(
						ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/gray"), null, null)));
		gingham_gray.BLOCK.setGenerate();
		gingham_gray.CARPET.setGenerate();
		buildDefault(gingham_gray, "extradelight");

		MaterialTextile gingham_black = new MaterialTextile("gingham_black", "extradelight",
				new SpecialLocationsTextile(new SpecialTextureLocationsTextile(
						ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/black"), null, null)));
		gingham_black.BLOCK.setGenerate();
		gingham_black.CARPET.setGenerate();
		buildDefault(gingham_black, "extradelight");

		MaterialTextile gingham_brown = new MaterialTextile("gingham_brown", "extradelight",
				new SpecialLocationsTextile(new SpecialTextureLocationsTextile(
						ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/brown"), null, null)));
		gingham_brown.BLOCK.setGenerate();
		gingham_brown.CARPET.setGenerate();
		buildDefault(gingham_brown, "extradelight");

		MaterialTextile gingham_red = new MaterialTextile("gingham_red", "extradelight",
				new SpecialLocationsTextile(new SpecialTextureLocationsTextile(
						ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/red"), null, null)));
		gingham_red.BLOCK.setGenerate();
		gingham_red.CARPET.setGenerate();
		buildDefault(gingham_red, "extradelight");

		MaterialTextile gingham_orange = new MaterialTextile("gingham_orange", "extradelight",
				new SpecialLocationsTextile(new SpecialTextureLocationsTextile(
						ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/orange"), null, null)));
		gingham_orange.BLOCK.setGenerate();
		gingham_orange.CARPET.setGenerate();
		buildDefault(gingham_orange, "extradelight");

		MaterialTextile gingham_yellow = new MaterialTextile("gingham_yellow", "extradelight",
				new SpecialLocationsTextile(new SpecialTextureLocationsTextile(
						ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/yellow"), null, null)));
		gingham_yellow.BLOCK.setGenerate();
		gingham_yellow.CARPET.setGenerate();
		buildDefault(gingham_yellow, "extradelight");

		MaterialTextile gingham_lime = new MaterialTextile("gingham_lime", "extradelight",
				new SpecialLocationsTextile(new SpecialTextureLocationsTextile(
						ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/lime"), null, null)));
		gingham_lime.BLOCK.setGenerate();
		gingham_lime.CARPET.setGenerate();
		buildDefault(gingham_lime, "extradelight");

		MaterialTextile gingham_green = new MaterialTextile("gingham_green", "extradelight",
				new SpecialLocationsTextile(new SpecialTextureLocationsTextile(
						ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/green"), null, null)));
		gingham_green.BLOCK.setGenerate();
		gingham_green.CARPET.setGenerate();
		buildDefault(gingham_green, "extradelight");

		MaterialTextile gingham_cyan = new MaterialTextile("gingham_cyan", "extradelight",
				new SpecialLocationsTextile(new SpecialTextureLocationsTextile(
						ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/cyan"), null, null)));
		gingham_cyan.BLOCK.setGenerate();
		gingham_cyan.CARPET.setGenerate();
		buildDefault(gingham_cyan, "extradelight");

		MaterialTextile gingham_light_blue = new MaterialTextile("gingham_light_blue", "extradelight",
				new SpecialLocationsTextile(new SpecialTextureLocationsTextile(
						ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/light_blue"), null,
						null)));
		gingham_light_blue.BLOCK.setGenerate();
		gingham_light_blue.CARPET.setGenerate();
		buildDefault(gingham_light_blue, "extradelight");

		MaterialTextile gingham_blue = new MaterialTextile("gingham_blue", "extradelight",
				new SpecialLocationsTextile(new SpecialTextureLocationsTextile(
						ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/blue"), null, null)));
		gingham_blue.BLOCK.setGenerate();
		gingham_blue.CARPET.setGenerate();
		buildDefault(gingham_blue, "extradelight");

		MaterialTextile gingham_purple = new MaterialTextile("gingham_purple", "extradelight",
				new SpecialLocationsTextile(new SpecialTextureLocationsTextile(
						ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/purple"), null, null)));
		gingham_purple.BLOCK.setGenerate();
		gingham_purple.CARPET.setGenerate();
		buildDefault(gingham_purple, "extradelight");

		MaterialTextile gingham_magenta = new MaterialTextile("gingham_magenta", "extradelight",
				new SpecialLocationsTextile(new SpecialTextureLocationsTextile(
						ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/magenta"), null, null)));
		gingham_magenta.BLOCK.setGenerate();
		gingham_magenta.CARPET.setGenerate();
		buildDefault(gingham_magenta, "extradelight");

		MaterialTextile gingham_pink = new MaterialTextile("gingham_pink", "extradelight",
				new SpecialLocationsTextile(new SpecialTextureLocationsTextile(
						ResourceLocation.fromNamespaceAndPath("extradelight", "block/gingham/pink"), null, null)));
		gingham_pink.BLOCK.setGenerate();
		gingham_pink.CARPET.setGenerate();
		buildDefault(gingham_pink, "extradelight");

		MaterialWood fruit = new MaterialWood("fruit", "extradelight");

		fruit.PLANKS.setExists(ResourceLocation.fromNamespaceAndPath("extradelight", "fruit_planks"),
				ResourceLocation.fromNamespaceAndPath("extradelight", "fruit_planks"));
		fruit.LOG.setExists(ResourceLocation.fromNamespaceAndPath("extradelight", "fruit_log"),
				ResourceLocation.fromNamespaceAndPath("extradelight", "fruit_log"));
		fruit.STRIPPED_LOG.setExists(ResourceLocation.fromNamespaceAndPath("extradelight", "stripped_fruit_log"),
				ResourceLocation.fromNamespaceAndPath("extradelight", "stripped_fruit_log"));
		fruit.WOOD.setExists(ResourceLocation.fromNamespaceAndPath("extradelight", "fruit_wood"),
				ResourceLocation.fromNamespaceAndPath("extradelight", "fruit_wood"));
		fruit.STRIPPED_WOOD.setExists(ResourceLocation.fromNamespaceAndPath("extradelight", "stripped_fruit_wood"),
				ResourceLocation.fromNamespaceAndPath("extradelight", "stripped_fruit_wood"));

		fruit.specialLocations = new SpecialLocationsWood(
				new SpecialTextureLocationsWood(ResourceLocation.fromNamespaceAndPath("extradelight", "block/fruit_planks"),
						ResourceLocation.fromNamespaceAndPath("extradelight", "block/fruit_log"),
						ResourceLocation.fromNamespaceAndPath("extradelight", "block/stripped_fruit_log"),
						ResourceLocation.fromNamespaceAndPath("extradelight", "block/fruit_log_top"),
						ResourceLocation.fromNamespaceAndPath("extradelight", "block/stripped_fruit_log_top")));
		buildDefault(new MaterialWood("fruit", "extradelight").addExtension(new ExtensionExtraLogs().generateAll())
				.addExtension(new ExtensionExtraPlanks().generateAll()), "extradelight");
	}

	private static void barnyardBuddies() {
		MaterialTextile spotted_wool = new MaterialTextile("spotted_wool", "barnyardbuddies",
				new SpecialLocationsTextile(new SpecialTextureLocationsTextile(
						ResourceLocation.fromNamespaceAndPath("barnyardbuddies", "block/spottedwool"), null, null)));
		spotted_wool.BLOCK.setIgnore();
		spotted_wool.CARPET.setIgnore();
		spotted_wool.STRING.setIgnore();
		buildDefault(spotted_wool, "barnyardbuddies");
	}

	private static void delicateDyes() {
		MaterialTextile berry_wool = new MaterialTextile("berry_wool", "delicate_dyes",
				new SpecialLocationsTextile(new SpecialTextureLocationsTextile(
						ResourceLocation.fromNamespaceAndPath("delicate_dyes", "block/rose_wool"), null, null)));
		berry_wool.BLOCK.setIgnore();
		berry_wool.CARPET.setIgnore();
		berry_wool.STRING.setIgnore();
		buildDefault(berry_wool, "delicate_dyes");

		MaterialTextile blurple_wool = new MaterialTextile("blurple_wool", "delicate_dyes");
		blurple_wool.BLOCK.setIgnore();
		blurple_wool.CARPET.setIgnore();
		blurple_wool.STRING.setIgnore();
		buildDefault(blurple_wool, "delicate_dyes");

		MaterialTextile canary_wool = new MaterialTextile("canary_wool", "delicate_dyes");
		canary_wool.BLOCK.setIgnore();
		canary_wool.CARPET.setIgnore();
		canary_wool.STRING.setIgnore();
		buildDefault(canary_wool, "delicate_dyes");

		MaterialTextile coral_wool = new MaterialTextile("coral_wool", "delicate_dyes");
		coral_wool.BLOCK.setIgnore();
		coral_wool.CARPET.setIgnore();
		coral_wool.STRING.setIgnore();
		buildDefault(coral_wool, "delicate_dyes");

		MaterialTextile lilac_wool = new MaterialTextile("lilac_wool", "delicate_dyes",
				new SpecialLocationsTextile(new SpecialTextureLocationsTextile(
						ResourceLocation.fromNamespaceAndPath("delicate_dyes", "block/lavender_wool"), null, null)));
		lilac_wool.BLOCK.setIgnore();
		lilac_wool.CARPET.setIgnore();
		lilac_wool.STRING.setIgnore();
		buildDefault(lilac_wool, "delicate_dyes");

		MaterialTextile sacramento_wool = new MaterialTextile("sacramento_wool", "delicate_dyes");
		sacramento_wool.BLOCK.setIgnore();
		sacramento_wool.CARPET.setIgnore();
		sacramento_wool.STRING.setIgnore();
		buildDefault(sacramento_wool, "delicate_dyes");

		MaterialTextile sangria_wool = new MaterialTextile("sangria_wool", "delicate_dyes");
		sangria_wool.BLOCK.setIgnore();
		sangria_wool.CARPET.setIgnore();
		sangria_wool.STRING.setIgnore();
		buildDefault(sangria_wool, "delicate_dyes");

		MaterialTextile sky_wool = new MaterialTextile("sky_wool", "delicate_dyes");
		sky_wool.BLOCK.setIgnore();
		sky_wool.CARPET.setIgnore();
		sky_wool.STRING.setIgnore();
		buildDefault(sky_wool, "delicate_dyes");

		MaterialTextile umber_wool = new MaterialTextile("umber_wool", "delicate_dyes");
		umber_wool.BLOCK.setIgnore();
		umber_wool.CARPET.setIgnore();
		umber_wool.STRING.setIgnore();
		buildDefault(umber_wool, "delicate_dyes");

		MaterialTextile wasabi_wool = new MaterialTextile("wasabi_wool", "delicate_dyes");
		wasabi_wool.BLOCK.setIgnore();
		wasabi_wool.CARPET.setIgnore();
		wasabi_wool.STRING.setIgnore();
		buildDefault(wasabi_wool, "delicate_dyes");
	}

	private static void delicateDyesGingham() {
		MaterialTextile gingham_berry = new MaterialTextile("gingham_berry", "compendium");
		gingham_berry.BLOCK.setGenerate();
		gingham_berry.CARPET.setGenerate();
		gingham_berry.STRING.setIgnore();
		buildDefault(gingham_berry, "compendium");

		MaterialTextile gingham_blurple = new MaterialTextile("gingham_blurple", "compendium");
		gingham_blurple.BLOCK.setGenerate();
		gingham_blurple.CARPET.setGenerate();
		gingham_blurple.STRING.setIgnore();
		buildDefault(gingham_blurple, "compendium");

		MaterialTextile gingham_canary = new MaterialTextile("gingham_canary", "compendium");
		gingham_canary.BLOCK.setGenerate();
		gingham_canary.CARPET.setGenerate();
		gingham_canary.STRING.setIgnore();
		buildDefault(gingham_canary, "compendium");

		MaterialTextile gingham_coral = new MaterialTextile("gingham_coral", "compendium");
		gingham_coral.BLOCK.setGenerate();
		gingham_coral.CARPET.setGenerate();
		gingham_coral.STRING.setIgnore();
		buildDefault(gingham_coral, "compendium");

		MaterialTextile gingham_lilac = new MaterialTextile("gingham_lilac", "compendium");
		gingham_lilac.BLOCK.setGenerate();
		gingham_lilac.CARPET.setGenerate();
		gingham_lilac.STRING.setIgnore();
		buildDefault(gingham_lilac, "compendium");

		MaterialTextile gingham_sacramento = new MaterialTextile("gingham_sacramento", "compendium");
		gingham_sacramento.BLOCK.setGenerate();
		gingham_sacramento.CARPET.setGenerate();
		gingham_sacramento.STRING.setIgnore();
		buildDefault(gingham_sacramento, "compendium");

		MaterialTextile gingham_sangria = new MaterialTextile("gingham_sangria", "compendium");
		gingham_sangria.BLOCK.setGenerate();
		gingham_sangria.CARPET.setGenerate();
		gingham_sangria.STRING.setIgnore();
		buildDefault(gingham_sangria, "compendium");

		MaterialTextile gingham_sky = new MaterialTextile("gingham_sky", "compendium");
		gingham_sky.BLOCK.setGenerate();
		gingham_sky.CARPET.setGenerate();
		gingham_sky.STRING.setIgnore();
		buildDefault(gingham_sky, "compendium");

		MaterialTextile gingham_umber = new MaterialTextile("gingham_umber", "compendium");
		gingham_umber.BLOCK.setGenerate();
		gingham_umber.CARPET.setGenerate();
		gingham_umber.STRING.setIgnore();
		buildDefault(gingham_umber, "compendium");

		MaterialTextile gingham_wasabi = new MaterialTextile("gingham_wasabi", "compendium");
		gingham_wasabi.BLOCK.setGenerate();
		gingham_wasabi.CARPET.setGenerate();
		gingham_wasabi.STRING.setIgnore();
		buildDefault(gingham_wasabi, "compendium");
	}

	private static void dyenamics() {
		MaterialTextile amber_wool = new MaterialTextile("amber_wool", "dyenamics");
		amber_wool.BLOCK.setIgnore();
		amber_wool.CARPET.setIgnore();
		amber_wool.STRING.setIgnore();
		buildDefault(amber_wool, "dyenamics");

		MaterialTextile aquamarine_wool = new MaterialTextile("aquamarine_wool", "dyenamics");
		aquamarine_wool.BLOCK.setIgnore();
		aquamarine_wool.CARPET.setIgnore();
		aquamarine_wool.STRING.setIgnore();
		buildDefault(aquamarine_wool, "dyenamics");

		MaterialTextile bubblegum_wool = new MaterialTextile("bubblegum_wool", "dyenamics");
		bubblegum_wool.BLOCK.setIgnore();
		bubblegum_wool.CARPET.setIgnore();
		bubblegum_wool.STRING.setIgnore();
		buildDefault(bubblegum_wool, "dyenamics");

		MaterialTextile cherenkov_wool = new MaterialTextile("cherenkov_wool", "dyenamics");
		cherenkov_wool.BLOCK.setIgnore();
		cherenkov_wool.CARPET.setIgnore();
		cherenkov_wool.STRING.setIgnore();
		buildDefault(cherenkov_wool, "dyenamics");

		MaterialTextile conifer_wool = new MaterialTextile("conifer_wool", "dyenamics");
		conifer_wool.BLOCK.setIgnore();
		conifer_wool.CARPET.setIgnore();
		conifer_wool.STRING.setIgnore();
		buildDefault(conifer_wool, "dyenamics");

		MaterialTextile fluorescent_wool = new MaterialTextile("fluorescent_wool", "dyenamics");
		fluorescent_wool.BLOCK.setIgnore();
		fluorescent_wool.CARPET.setIgnore();
		fluorescent_wool.STRING.setIgnore();
		buildDefault(fluorescent_wool, "dyenamics");

		MaterialTextile honey_wool = new MaterialTextile("honey_wool", "dyenamics");
		honey_wool.BLOCK.setIgnore();
		honey_wool.CARPET.setIgnore();
		honey_wool.STRING.setIgnore();
		buildDefault(honey_wool, "dyenamics");

		MaterialTextile icy_blue_wool = new MaterialTextile("icy_blue_wool", "dyenamics");
		icy_blue_wool.BLOCK.setIgnore();
		icy_blue_wool.CARPET.setIgnore();
		icy_blue_wool.STRING.setIgnore();
		buildDefault(icy_blue_wool, "dyenamics");

		MaterialTextile lavender_wool = new MaterialTextile("lavender_wool", "dyenamics");
		lavender_wool.BLOCK.setIgnore();
		lavender_wool.CARPET.setIgnore();
		lavender_wool.STRING.setIgnore();
		buildDefault(lavender_wool, "dyenamics");

		MaterialTextile maroon_wool = new MaterialTextile("maroon_wool", "dyenamics");
		maroon_wool.BLOCK.setIgnore();
		maroon_wool.CARPET.setIgnore();
		maroon_wool.STRING.setIgnore();
		buildDefault(maroon_wool, "dyenamics");

		MaterialTextile mint_wool = new MaterialTextile("mint_wool", "dyenamics");
		mint_wool.BLOCK.setIgnore();
		mint_wool.CARPET.setIgnore();
		mint_wool.STRING.setIgnore();
		buildDefault(mint_wool, "dyenamics");

		MaterialTextile navy_wool = new MaterialTextile("navy_wool", "dyenamics");
		navy_wool.BLOCK.setIgnore();
		navy_wool.CARPET.setIgnore();
		navy_wool.STRING.setIgnore();
		buildDefault(navy_wool, "dyenamics");

		MaterialTextile peach_wool = new MaterialTextile("peach_wool", "dyenamics");
		peach_wool.BLOCK.setIgnore();
		peach_wool.CARPET.setIgnore();
		peach_wool.STRING.setIgnore();
		buildDefault(peach_wool, "dyenamics");

		MaterialTextile persimmon_wool = new MaterialTextile("persimmon_wool", "dyenamics");
		persimmon_wool.BLOCK.setIgnore();
		persimmon_wool.CARPET.setIgnore();
		persimmon_wool.STRING.setIgnore();
		buildDefault(persimmon_wool, "dyenamics");

		MaterialTextile rose_wool = new MaterialTextile("rose_wool", "dyenamics");
		rose_wool.BLOCK.setIgnore();
		rose_wool.CARPET.setIgnore();
		rose_wool.STRING.setIgnore();
		buildDefault(rose_wool, "dyenamics");

		MaterialTextile spring_green_wool = new MaterialTextile("spring_green_wool", "dyenamics");
		spring_green_wool.BLOCK.setIgnore();
		spring_green_wool.CARPET.setIgnore();
		spring_green_wool.STRING.setIgnore();
		buildDefault(spring_green_wool, "dyenamics");

		MaterialTextile ultramarine_wool = new MaterialTextile("ultramarine_wool", "dyenamics");
		ultramarine_wool.BLOCK.setIgnore();
		ultramarine_wool.CARPET.setIgnore();
		ultramarine_wool.STRING.setIgnore();
		buildDefault(ultramarine_wool, "dyenamics");

		MaterialTextile wine_wool = new MaterialTextile("wine_wool", "dyenamics");
		wine_wool.BLOCK.setIgnore();
		wine_wool.CARPET.setIgnore();
		wine_wool.STRING.setIgnore();
		buildDefault(wine_wool, "dyenamics");
	}

	private static void dyenamicsGingham() {
		MaterialTextile gingham_amber = new MaterialTextile("gingham_amber", "compendium");
		gingham_amber.BLOCK.setGenerate();
		gingham_amber.CARPET.setGenerate();
		gingham_amber.STRING.setIgnore();
		buildDefault(gingham_amber, "compendium");

		MaterialTextile gingham_aquamarine = new MaterialTextile("gingham_aquamarine", "compendium");
		gingham_aquamarine.BLOCK.setGenerate();
		gingham_aquamarine.CARPET.setGenerate();
		gingham_aquamarine.STRING.setIgnore();
		buildDefault(gingham_aquamarine, "compendium");

		MaterialTextile gingham_bubblegum = new MaterialTextile("gingham_bubblegum", "compendium");
		gingham_bubblegum.BLOCK.setGenerate();
		gingham_bubblegum.CARPET.setGenerate();
		gingham_bubblegum.STRING.setIgnore();
		buildDefault(gingham_bubblegum, "compendium");

		MaterialTextile gingham_cherenkov = new MaterialTextile("gingham_cherenkov", "compendium");
		gingham_cherenkov.BLOCK.setGenerate();
		gingham_cherenkov.CARPET.setGenerate();
		gingham_cherenkov.STRING.setIgnore();
		buildDefault(gingham_cherenkov, "compendium");

		MaterialTextile gingham_conifer = new MaterialTextile("gingham_conifer", "compendium");
		gingham_conifer.BLOCK.setGenerate();
		gingham_conifer.CARPET.setGenerate();
		gingham_conifer.STRING.setIgnore();
		buildDefault(gingham_conifer, "compendium");

		MaterialTextile gingham_fluorescent = new MaterialTextile("gingham_fluorescent", "compendium");
		gingham_fluorescent.BLOCK.setGenerate();
		gingham_fluorescent.CARPET.setGenerate();
		gingham_fluorescent.STRING.setIgnore();
		buildDefault(gingham_fluorescent, "compendium");

		MaterialTextile gingham_honey = new MaterialTextile("gingham_honey", "compendium");
		gingham_honey.BLOCK.setGenerate();
		gingham_honey.CARPET.setGenerate();
		gingham_honey.STRING.setIgnore();
		buildDefault(gingham_honey, "compendium");

		MaterialTextile gingham_icy_blue = new MaterialTextile("gingham_icy_blue", "compendium");
		gingham_icy_blue.BLOCK.setGenerate();
		gingham_icy_blue.CARPET.setGenerate();
		gingham_icy_blue.STRING.setIgnore();
		buildDefault(gingham_icy_blue, "compendium");

		MaterialTextile gingham_lavender = new MaterialTextile("gingham_lavender", "compendium");
		gingham_lavender.BLOCK.setGenerate();
		gingham_lavender.CARPET.setGenerate();
		gingham_lavender.STRING.setIgnore();
		buildDefault(gingham_lavender, "compendium");

		MaterialTextile gingham_maroon = new MaterialTextile("gingham_maroon", "compendium");
		gingham_maroon.BLOCK.setGenerate();
		gingham_maroon.CARPET.setGenerate();
		gingham_maroon.STRING.setIgnore();
		buildDefault(gingham_maroon, "compendium");

		MaterialTextile gingham_mint = new MaterialTextile("gingham_mint", "compendium");
		gingham_mint.BLOCK.setGenerate();
		gingham_mint.CARPET.setGenerate();
		gingham_mint.STRING.setIgnore();
		buildDefault(gingham_mint, "compendium");

		MaterialTextile gingham_navy = new MaterialTextile("gingham_navy", "compendium");
		gingham_navy.BLOCK.setGenerate();
		gingham_navy.CARPET.setGenerate();
		gingham_navy.STRING.setIgnore();
		buildDefault(gingham_navy, "compendium");

		MaterialTextile gingham_peach = new MaterialTextile("gingham_peach", "compendium");
		gingham_peach.BLOCK.setGenerate();
		gingham_peach.CARPET.setGenerate();
		gingham_peach.STRING.setIgnore();
		buildDefault(gingham_peach, "compendium");

		MaterialTextile gingham_persimmon = new MaterialTextile("gingham_persimmon", "compendium");
		gingham_persimmon.BLOCK.setGenerate();
		gingham_persimmon.CARPET.setGenerate();
		gingham_persimmon.STRING.setIgnore();
		buildDefault(gingham_persimmon, "compendium");

		MaterialTextile gingham_rose = new MaterialTextile("gingham_rose", "compendium");
		gingham_rose.BLOCK.setGenerate();
		gingham_rose.CARPET.setGenerate();
		gingham_rose.STRING.setIgnore();
		buildDefault(gingham_rose, "compendium");

		MaterialTextile gingham_spring_green = new MaterialTextile("gingham_spring_green", "compendium");
		gingham_spring_green.BLOCK.setGenerate();
		gingham_spring_green.CARPET.setGenerate();
		gingham_spring_green.STRING.setIgnore();
		buildDefault(gingham_spring_green, "compendium");

		MaterialTextile gingham_ultramarine = new MaterialTextile("gingham_ultramarine", "compendium");
		gingham_ultramarine.BLOCK.setGenerate();
		gingham_ultramarine.CARPET.setGenerate();
		gingham_ultramarine.STRING.setIgnore();
		buildDefault(gingham_ultramarine, "compendium");

		MaterialTextile gingham_wine = new MaterialTextile("gingham_wine", "compendium");
		gingham_wine.BLOCK.setGenerate();
		gingham_wine.CARPET.setGenerate();
		gingham_wine.STRING.setIgnore();
		buildDefault(gingham_wine, "compendium");
	}

	static void buildDefault(_MaterialBase mat) {
		buildDefault(mat, "");
	}

	static void buildDefault(_MaterialBase mat, String folder) {
		if (!folder.isEmpty() && folder != "")
			folder = folder + "/";
		try {
			Path resourcePackPath = Path.of("./../src/main/resources/data/compendium/materials");
			Files.createDirectories(resourcePackPath.resolve(mat.getType().toString().toLowerCase() + "/" + folder));
			Path p = resourcePackPath.resolve(mat.getType().toString().toLowerCase() + "/" + folder)
					.resolve(mat.name + ".json");
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
