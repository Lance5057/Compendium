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
import com.lance5057.compendium.index.CompendiumIndex.Generate;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.index.material.MaterialTypeRegistry;
import com.lance5057.compendium.index.material.base.MaterialGlass;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.base.metal.MaterialMetal;
import com.lance5057.compendium.index.material.base.textile.MaterialTextile;
import com.lance5057.compendium.index.material.base.textile.locations.SpecialLocationsTextile;
import com.lance5057.compendium.index.material.base.textile.locations.SpecialTextureLocationsTextile;
import com.lance5057.compendium.index.material.base.wood.MaterialWood;
import com.lance5057.compendium.index.material.base.wood.locations.ExistsLocationsWood;
import com.lance5057.compendium.index.material.base.wood.locations.SpecialLocationsWood;
import com.lance5057.compendium.index.material.base.wood.locations.SpecialTextureLocationsWood;
import com.lance5057.compendium.index.material.extensions.ExtensionAdvancedTools;
import com.lance5057.compendium.index.material.extensions.wood.ExtensionExtraLogs;
import com.lance5057.compendium.index.material.extensions.wood.ExtensionExtraPlanks;
import com.lance5057.compendium.util.TagUtil;
import com.mojang.logging.LogUtils;

import net.minecraft.resources.ResourceLocation;
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
		buildDefault(new MaterialMetal("iron", "minecraft", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS)
				.addExtension(new ExtensionAdvancedTools(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.IGNORE, Generate.GENERATE, Generate.IGNORE)));
		buildDefault(new MaterialMetal("gold", "minecraft", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS)
				.addExtension(new ExtensionAdvancedTools(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.IGNORE)));
		buildDefault(new MaterialMetal("copper", "minecraft", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS)
				.addExtension(new ExtensionAdvancedTools(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.IGNORE)));
		buildDefault(new MaterialMetal("netherite", "minecraft", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS)
				.addExtension(new ExtensionAdvancedTools(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.IGNORE)));

		buildDefault(new MaterialGlass("clear", "minecraft", Generate.EXISTS));
		buildDefault(new MaterialGlass("white_stained", "minecraft", Generate.EXISTS));
		buildDefault(new MaterialGlass("light_gray_stained", "minecraft", Generate.EXISTS));
		buildDefault(new MaterialGlass("gray_stained", "minecraft", Generate.EXISTS));
		buildDefault(new MaterialGlass("black_stained", "minecraft", Generate.EXISTS));
		buildDefault(new MaterialGlass("brown_stained", "minecraft", Generate.EXISTS));
		buildDefault(new MaterialGlass("red_stained", "minecraft", Generate.EXISTS));
		buildDefault(new MaterialGlass("orange_stained", "minecraft", Generate.EXISTS));
		buildDefault(new MaterialGlass("yellow_stained", "minecraft", Generate.EXISTS));
		buildDefault(new MaterialGlass("lime_stained", "minecraft", Generate.EXISTS));
		buildDefault(new MaterialGlass("green_stained", "minecraft", Generate.EXISTS));
		buildDefault(new MaterialGlass("cyan_stained", "minecraft", Generate.EXISTS));
		buildDefault(new MaterialGlass("light_blue_stained", "minecraft", Generate.EXISTS));
		buildDefault(new MaterialGlass("blue_stained", "minecraft", Generate.EXISTS));
		buildDefault(new MaterialGlass("purple_stained", "minecraft", Generate.EXISTS));
		buildDefault(new MaterialGlass("magenta_stained", "minecraft", Generate.EXISTS));
		buildDefault(new MaterialGlass("pink_stained", "minecraft", Generate.EXISTS));
//		buildDefault(new MaterialGlass("tinted", "minecraft", Generate.EXISTS));

		buildDefault(new MaterialWood("oak", "minecraft", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));
		buildDefault(new MaterialWood("birch", "minecraft", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));
		buildDefault(new MaterialWood("spruce", "minecraft", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));
		buildDefault(new MaterialWood("jungle", "minecraft", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));
		buildDefault(new MaterialWood("acacia", "minecraft", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));
		buildDefault(new MaterialWood("dark_oak", "minecraft", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));
		buildDefault(new MaterialWood("mangrove", "minecraft", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));
		buildDefault(new MaterialWood("cherry", "minecraft", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));
//		buildDefault(new MaterialWood("bamboo", false).addExtension(new ExtensionExtraLogs(true, true, true, true)));
		SpecialLocationsWood crimson = new SpecialLocationsWood(null, null,
				new SpecialTextureLocationsWood(null, TagUtil.mcLoc("block/crimson_stem"),
						TagUtil.mcLoc("block/stripped_crimson_stem"), TagUtil.mcLoc("block/crimson_stem_top"),
						TagUtil.mcLoc("block/stripped_crimson_stem_top")));
		buildDefault(new MaterialWood("crimson", "minecraft", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS, crimson)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));
		SpecialLocationsWood warped = new SpecialLocationsWood(null, null,
				new SpecialTextureLocationsWood(null, TagUtil.mcLoc("block/warped_stem"),
						TagUtil.mcLoc("block/stripped_warped_stem"), TagUtil.mcLoc("block/warped_stem_top"),
						TagUtil.mcLoc("block/stripped_warped_stem_top")));
		buildDefault(new MaterialWood("warped", "minecraft", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS, warped)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

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

		buildDefault(new MaterialTextile("white_wool", "minecraft", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(
				new MaterialTextile("light_gray_wool", "minecraft", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(new MaterialTextile("gray_wool", "minecraft", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(new MaterialTextile("black_wool", "minecraft", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(new MaterialTextile("brown_wool", "minecraft", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(new MaterialTextile("red_wool", "minecraft", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(
				new MaterialTextile("orange_wool", "minecraft", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(
				new MaterialTextile("yellow_wool", "minecraft", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(new MaterialTextile("lime_wool", "minecraft", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(new MaterialTextile("green_wool", "minecraft", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(new MaterialTextile("cyan_wool", "minecraft", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(
				new MaterialTextile("light_blue_wool", "minecraft", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(new MaterialTextile("blue_wool", "minecraft", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(
				new MaterialTextile("purple_wool", "minecraft", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(
				new MaterialTextile("magenta_wool", "minecraft", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(new MaterialTextile("pink_wool", "minecraft", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));

	}

	private static void moddedDefaults() {
		buildDefault(new MaterialTextile("amber_wool", "dyenamics", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(
				new MaterialTextile("aquamarine_wool", "dyenamics", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(
				new MaterialTextile("bubblegum_wool", "dyenamics", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(
				new MaterialTextile("cherenkov_wool", "dyenamics", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(
				new MaterialTextile("conifer_wool", "dyenamics", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(new MaterialTextile("fluorescent_wool", "dyenamics", Generate.EXISTS, Generate.IGNORE,
				Generate.EXISTS));
		buildDefault(new MaterialTextile("honey_wool", "dyenamics", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(
				new MaterialTextile("icy_blue_wool", "dyenamics", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(
				new MaterialTextile("lavender_wool", "dyenamics", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(
				new MaterialTextile("maroon_wool", "dyenamics", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(new MaterialTextile("mint_wool", "dyenamics", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(new MaterialTextile("navy_wool", "dyenamics", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(new MaterialTextile("peach_wool", "dyenamics", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(
				new MaterialTextile("persimmon_wool", "dyenamics", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(new MaterialTextile("rose_wool", "dyenamics", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));
		buildDefault(new MaterialTextile("spring_green_wool", "dyenamics", Generate.EXISTS, Generate.IGNORE,
				Generate.EXISTS));
		buildDefault(new MaterialTextile("ultramarine_wool", "dyenamics", Generate.EXISTS, Generate.IGNORE,
				Generate.EXISTS));
		buildDefault(new MaterialTextile("wine_wool", "dyenamics", Generate.EXISTS, Generate.IGNORE, Generate.EXISTS));

		buildDefault(new MaterialTextile("gingham_amber", "compendium", Generate.GENERATE, Generate.IGNORE,
				Generate.GENERATE));
		buildDefault(new MaterialTextile("gingham_aquamarine", "compendium", Generate.GENERATE, Generate.IGNORE,
				Generate.GENERATE));
		buildDefault(new MaterialTextile("gingham_bubblegum", "compendium", Generate.GENERATE, Generate.IGNORE,
				Generate.GENERATE));
		buildDefault(new MaterialTextile("gingham_cherenkov", "compendium", Generate.GENERATE, Generate.IGNORE,
				Generate.GENERATE));
		buildDefault(new MaterialTextile("gingham_conifer", "compendium", Generate.GENERATE, Generate.IGNORE,
				Generate.GENERATE));
		buildDefault(new MaterialTextile("gingham_fluorescent", "compendium", Generate.GENERATE, Generate.IGNORE,
				Generate.GENERATE));
		buildDefault(new MaterialTextile("gingham_honey", "compendium", Generate.GENERATE, Generate.IGNORE,
				Generate.GENERATE));
		buildDefault(new MaterialTextile("gingham_icy_blue", "compendium", Generate.GENERATE, Generate.IGNORE,
				Generate.GENERATE));
		buildDefault(new MaterialTextile("gingham_lavender", "compendium", Generate.GENERATE, Generate.IGNORE,
				Generate.GENERATE));
		buildDefault(new MaterialTextile("gingham_maroon", "compendium", Generate.GENERATE, Generate.IGNORE,
				Generate.GENERATE));
		buildDefault(new MaterialTextile("gingham_mint", "compendium", Generate.GENERATE, Generate.IGNORE,
				Generate.GENERATE));
		buildDefault(new MaterialTextile("gingham_navy", "compendium", Generate.GENERATE, Generate.IGNORE,
				Generate.GENERATE));
		buildDefault(new MaterialTextile("gingham_peach", "compendium", Generate.GENERATE, Generate.IGNORE,
				Generate.GENERATE));
		buildDefault(new MaterialTextile("gingham_persimmon", "compendium", Generate.GENERATE, Generate.IGNORE,
				Generate.GENERATE));
		buildDefault(new MaterialTextile("gingham_rose", "compendium", Generate.GENERATE, Generate.IGNORE,
				Generate.GENERATE));
		buildDefault(new MaterialTextile("gingham_spring_green", "compendium", Generate.GENERATE, Generate.IGNORE,
				Generate.GENERATE));
		buildDefault(new MaterialTextile("gingham_ultramarine", "compendium", Generate.GENERATE, Generate.IGNORE,
				Generate.GENERATE));
		buildDefault(new MaterialTextile("gingham_wine", "compendium", Generate.GENERATE, Generate.IGNORE,
				Generate.GENERATE));

		buildDefault(new MaterialTextile("gingham_white", "extradelight", Generate.EXISTS, Generate.IGNORE,
				Generate.EXISTS, new SpecialLocationsTextile(null, null,
						new SpecialTextureLocationsTextile("block/gingham/white", null, null))));
		buildDefault(new MaterialTextile("gingham_light_gray", "extradelight", Generate.EXISTS, Generate.IGNORE,
				Generate.EXISTS, new SpecialLocationsTextile(null, null,
						new SpecialTextureLocationsTextile("block/gingham/light_gray", null, null))));
		buildDefault(new MaterialTextile("gingham_gray", "extradelight", Generate.EXISTS, Generate.IGNORE,
				Generate.EXISTS, new SpecialLocationsTextile(null, null,
						new SpecialTextureLocationsTextile("block/gingham/gray", null, null))));
		buildDefault(new MaterialTextile("gingham_black", "extradelight", Generate.EXISTS, Generate.IGNORE,
				Generate.EXISTS, new SpecialLocationsTextile(null, null,
						new SpecialTextureLocationsTextile("block/gingham/black", null, null))));
		buildDefault(new MaterialTextile("gingham_brown", "extradelight", Generate.EXISTS, Generate.IGNORE,
				Generate.EXISTS, new SpecialLocationsTextile(null, null,
						new SpecialTextureLocationsTextile("block/gingham/brown", null, null))));
		buildDefault(new MaterialTextile("gingham_red", "extradelight", Generate.EXISTS, Generate.IGNORE,
				Generate.EXISTS, new SpecialLocationsTextile(null, null,
						new SpecialTextureLocationsTextile("block/gingham/red", null, null))));
		buildDefault(new MaterialTextile("gingham_orange", "extradelight", Generate.EXISTS, Generate.IGNORE,
				Generate.EXISTS, new SpecialLocationsTextile(null, null,
						new SpecialTextureLocationsTextile("block/gingham/orange", null, null))));
		buildDefault(new MaterialTextile("gingham_yellow", "extradelight", Generate.EXISTS, Generate.IGNORE,
				Generate.EXISTS, new SpecialLocationsTextile(null, null,
						new SpecialTextureLocationsTextile("block/gingham/yellow", null, null))));
		buildDefault(new MaterialTextile("gingham_lime", "extradelight", Generate.EXISTS, Generate.IGNORE,
				Generate.EXISTS, new SpecialLocationsTextile(null, null,
						new SpecialTextureLocationsTextile("block/gingham/lime", null, null))));
		buildDefault(new MaterialTextile("gingham_green", "extradelight", Generate.EXISTS, Generate.IGNORE,
				Generate.EXISTS, new SpecialLocationsTextile(null, null,
						new SpecialTextureLocationsTextile("block/gingham/green", null, null))));
		buildDefault(new MaterialTextile("gingham_cyan", "extradelight", Generate.EXISTS, Generate.IGNORE,

				Generate.EXISTS, new SpecialLocationsTextile(null, null,
						new SpecialTextureLocationsTextile("block/gingham/cyan", null, null))));
		buildDefault(new MaterialTextile("gingham_light_blue", "extradelight", Generate.EXISTS, Generate.IGNORE,
				Generate.EXISTS, new SpecialLocationsTextile(null, null,
						new SpecialTextureLocationsTextile("block/gingham/light_blue", null, null))));
		buildDefault(new MaterialTextile("gingham_blue", "extradelight", Generate.EXISTS, Generate.IGNORE,
				Generate.EXISTS, new SpecialLocationsTextile(null, null,
						new SpecialTextureLocationsTextile("block/gingham/blue", null, null))));
		buildDefault(new MaterialTextile("gingham_purple", "extradelight", Generate.EXISTS, Generate.IGNORE,
				Generate.EXISTS, new SpecialLocationsTextile(null, null,
						new SpecialTextureLocationsTextile("block/gingham/purple", null, null))));
		buildDefault(new MaterialTextile("gingham_magenta", "extradelight", Generate.EXISTS, Generate.IGNORE,
				Generate.EXISTS, new SpecialLocationsTextile(null, null,
						new SpecialTextureLocationsTextile("block/gingham/magenta", null, null))));
		buildDefault(new MaterialTextile("gingham_pink", "extradelight", Generate.EXISTS, Generate.IGNORE,
				Generate.EXISTS, new SpecialLocationsTextile(null, null,
						new SpecialTextureLocationsTextile("block/gingham/pink", null, null))));

		buildDefault(new MaterialWood("alder", "enchanted", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.GENERATE, Generate.GENERATE)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		buildDefault(new MaterialWood("archwood", "ars_nouveau", Generate.EXISTS, Generate.IGNORE, Generate.IGNORE,
				Generate.IGNORE, Generate.IGNORE)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		SpecialLocationsWood aspen = new SpecialLocationsWood(null, null,
				new SpecialTextureLocationsWood(null,
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/aspen_log_side"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/aspen_log_side_stripped"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/aspen_log_top"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/aspen_log_top_stripped")));
		buildDefault(new MaterialWood("aspen", "bloomingnature", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS, aspen)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		SpecialLocationsWood baobab = new SpecialLocationsWood(null, null,
				new SpecialTextureLocationsWood(null,
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/baobab_log_side"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/baobab_log_side_stripped"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/baobab_log_top"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/baobab_log_top_stripped")));
		buildDefault(new MaterialWood("baobab", "bloomingnature", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS, baobab)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		SpecialLocationsWood blackwood = new SpecialLocationsWood(
				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("abyssal_decor", "blackwood_planks"),
						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "blackwood_log"),
						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "stripped_blackwood_log"),
						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "blackwood_wood"), null),
				null,
				new SpecialTextureLocationsWood(
						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/blackwoodplanks"),
						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/moldylogside"),
						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/blackwoodstrippedlogside"),
						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/moldylogtop"),
						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/blackwoodstrippedlogtop")));
		buildDefault(new MaterialWood("blackwood", "abyssal_decor", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.GENERATE, blackwood)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		buildDefault(new MaterialWood("blue_mushroom", "cluttered", Generate.EXISTS, Generate.EXISTS, Generate.GENERATE,
				Generate.EXISTS, Generate.GENERATE)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		SpecialLocationsWood cactus = new SpecialLocationsWood(
				new ExistsLocationsWood(null, ResourceLocation.fromNamespaceAndPath("minecraft", "cactus"), null, null,
						null),
				new ExistsLocationsWood(null, ResourceLocation.fromNamespaceAndPath("minecraft", "cactus"), null, null,
						null),
				new SpecialTextureLocationsWood(null,
						ResourceLocation.fromNamespaceAndPath("compendium", "block/cactus_log"), null,
						ResourceLocation.fromNamespaceAndPath("compendium", "block/cactus_log_top"),
						ResourceLocation.fromNamespaceAndPath("compendium", "block/stripped_cactus_log_top")));
		buildDefault(new MaterialWood("cactus", "bloomingnature", Generate.EXISTS, Generate.EXISTS, Generate.IGNORE,
				Generate.IGNORE, Generate.IGNORE, cactus)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.IGNORE, Generate.IGNORE, Generate.IGNORE, Generate.IGNORE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		SpecialLocationsWood charred_spruce = new SpecialLocationsWood(new ExistsLocationsWood(null, null,
				ResourceLocation.fromNamespaceAndPath("dawnoftimebuilder", "charred_spruce_log_stripped"), null, null),
				new ExistsLocationsWood(null, null,
						ResourceLocation.fromNamespaceAndPath("dawnoftimebuilder", "charred_spruce_log_stripped"), null,
						null),
				new SpecialTextureLocationsWood(null, null,
						ResourceLocation.fromNamespaceAndPath("dawnoftimebuilder", "block/charred_spruce_log_stripped"),
						null, ResourceLocation.fromNamespaceAndPath("dawnoftimebuilder",
								"block/charred_spruce_log_stripped_top")));
		buildDefault(new MaterialWood("charred_spruce", "dawnoftimebuilder", Generate.EXISTS, Generate.GENERATE,
				Generate.EXISTS, Generate.GENERATE, Generate.GENERATE, charred_spruce)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		SpecialLocationsWood chestnut = new SpecialLocationsWood(null, null,
				new SpecialTextureLocationsWood(null,
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/chestnut_log_side"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/chestnut_log_side_stripped"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/chestnut_log_top"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/chestnut_log_top_stripped")));
		buildDefault(new MaterialWood("chestnut", "bloomingnature", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS, chestnut)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		SpecialLocationsWood chorus = new SpecialLocationsWood(
				new ExistsLocationsWood(null, null, null, null,
						ResourceLocation.fromNamespaceAndPath("cataclysm", "chorus_stem")),
				new ExistsLocationsWood(null, null, null, null,
						ResourceLocation.fromNamespaceAndPath("cataclysm", "chorus_stem")),
				new SpecialTextureLocationsWood(null, null,
						ResourceLocation.fromNamespaceAndPath("cataclysm", "block/chorus_stem"),
						ResourceLocation.fromNamespaceAndPath("compendium", "block/material/wood/chorus/logs/log_top"),
						ResourceLocation.fromNamespaceAndPath("compendium",
								"block/material/wood/chorus/logs/log_top")));
		buildDefault(new MaterialWood("chorus", "cataclysm", Generate.EXISTS, Generate.IGNORE, Generate.IGNORE,
				Generate.IGNORE, Generate.EXISTS, chorus)
				.addExtension(new ExtensionExtraLogs(Generate.IGNORE, Generate.IGNORE, Generate.IGNORE, Generate.IGNORE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		SpecialLocationsWood cinnamon = new SpecialLocationsWood(
				new ExistsLocationsWood(null, null, null, null,
						ResourceLocation.fromNamespaceAndPath("extradelight", "strippedcinnamon_wood")),
				new ExistsLocationsWood(null, null, null, null,
						ResourceLocation.fromNamespaceAndPath("extradelight", "stripped_cinnamon_wood")),
				null);
		buildDefault(new MaterialWood("cinnamon", "extradelight", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS, cinnamon)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		SpecialLocationsWood cinnamon_ad = new SpecialLocationsWood(
				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("abyssal_decor", "cinnamon_planks"),
						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "cinnamon_log"),
						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "stripped_cinnamon_log"),
						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "cinnamon_wood"),
						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "stripped_cinnamon_wood")),
				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("abyssal_decor", "cinnamon_planks"),
						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "cinnamon_log"),
						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "stripped_cinnamon_log"),
						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "cinnamon_wood"),
						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "stripped_cinnamon_wood")),
				new SpecialTextureLocationsWood(
						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/cinnamonplanks"),
						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/cinnamonlogside"),
						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/strippedcinnamonlogside"),
						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/cinnamonlogtop"),
						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/strippedcinnamonlogtop")));
		buildDefault(new MaterialWood("cinnamon_ad", "abyssal_decor", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS, cinnamon_ad)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		buildDefault(new MaterialWood("crabapple", "cluttered", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));
		buildDefault(new MaterialWood("flowering_crabapple", "cluttered", Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS, Generate.EXISTS)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(
						new ExtensionExtraPlanks(Generate.IGNORE, Generate.IGNORE, Generate.IGNORE, Generate.IGNORE)));

		SpecialLocationsWood cypress = new SpecialLocationsWood(null, null,
				new SpecialTextureLocationsWood(null,
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/cypress_log_side"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/cypress_log_stripped_side"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/cypress_log_top"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/cypress_log_stripped_top")));
		buildDefault(new MaterialWood("cypress", "bloomingnature", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS, cypress)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		SpecialLocationsWood ebony = new SpecialLocationsWood(null, null,
				new SpecialTextureLocationsWood(null,
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/ebony_log_side"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/ebony_log_side_stripped"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/ebony_log_top"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/ebony_log_top_stripped")));
		buildDefault(new MaterialWood("ebony", "bloomingnature", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS, ebony)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		SpecialLocationsWood fan_palm = new SpecialLocationsWood(null, null,
				new SpecialTextureLocationsWood(null,
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/fan_palm_log_side"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/fan_palm_log_stripped"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/fan_palm_log_top"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/fan_palm_log_top_stripped")));
		buildDefault(new MaterialWood("fan_palm", "bloomingnature", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS, fan_palm)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		SpecialLocationsWood fir = new SpecialLocationsWood(null, null,
				new SpecialTextureLocationsWood(null,
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/fir_log_side"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/fir_log_side_stripped"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/fir_log_top"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/fir_log_top_stripped")));
		buildDefault(new MaterialWood("fir", "bloomingnature", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS, fir)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		buildDefault(new MaterialWood("fluorescent_maple", "cluttered", Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS, Generate.EXISTS)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		buildDefault(new MaterialWood("frostbite_birch", "hazennstuff", Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS, Generate.EXISTS)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		buildDefault(new MaterialWood("fruit", "extradelight", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		buildDefault(new MaterialWood("hawthorn", "enchanted", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.GENERATE, Generate.GENERATE)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		SpecialLocationsWood larch = new SpecialLocationsWood(null, null,
				new SpecialTextureLocationsWood(null,
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/larch_log_side"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/larch_log_stripped"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/larch_log_top"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/larch_log_stripped_top")));
		buildDefault(new MaterialWood("larch", "bloomingnature", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS, larch)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		buildDefault(new MaterialWood("netherwood", "silentgear", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		buildDefault(new MaterialWood("poplar", "cluttered", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		SpecialLocationsWood flowering_poplar = new SpecialLocationsWood(
				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_poplar_planks"),
						ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_poplar_log"),
						ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_flowering_poplar_log"),
						ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_poplar_wood"),
						ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_flowering_poplar_log")),
				null, null);
		buildDefault(new MaterialWood("flowering_poplar", "cluttered", Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS, Generate.EXISTS, flowering_poplar)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(
						new ExtensionExtraPlanks(Generate.IGNORE, Generate.IGNORE, Generate.IGNORE, Generate.IGNORE)));

		SpecialLocationsWood red_mushroom = new SpecialLocationsWood(
				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("cluttered", "red_mushroom_planks"), null,
						ResourceLocation.fromNamespaceAndPath("cluttered", "red_mushroom_log"),
						ResourceLocation.fromNamespaceAndPath("cluttered", "red_mushroom_wood"), null),
				null, null);
		buildDefault(new MaterialWood("red_mushroom", "cluttered", Generate.EXISTS, Generate.EXISTS, Generate.GENERATE,
				Generate.EXISTS, Generate.GENERATE, red_mushroom)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		SpecialLocationsWood rowan = new SpecialLocationsWood(
				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("enchanted", "rowan_planks"), null,
						ResourceLocation.fromNamespaceAndPath("enchanted", "rowan_log"), null,
						ResourceLocation.fromNamespaceAndPath("enchanted", "stripped_rowan_wood")),
				null, null);
		buildDefault(new MaterialWood("rowan", "enchanted", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.GENERATE, Generate.GENERATE, rowan)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		SpecialLocationsWood swamp_cypress = new SpecialLocationsWood(
				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("bloomingnature", "swamp_cypress_planks"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "swamp_cypress_log"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "stripped_swamp_cypress_log"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "swamp_cypress_wood"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "stripped_swamp_cypress_wood")),
				null,
				new SpecialTextureLocationsWood(null,
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/swamp_cypress_log_side"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature",
								"block/swamp_cypress_log_stripped_side"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/swamp_cypress_log_top"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature",
								"block/swamp_cypress_log_stripped_top")));
		buildDefault(new MaterialWood("swamp_cypress", "bloomingnature", Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS, Generate.EXISTS, swamp_cypress)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		SpecialLocationsWood swamp_oak = new SpecialLocationsWood(
				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("bloomingnature", "swamp_oak_planks"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "swamp_oak_log"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "stripped_swamp_oak_log"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "swamp_oak_wood"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "stripped_swamp_oak_wood")),
				null,
				new SpecialTextureLocationsWood(null,
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/swamp_oak_log_side"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/swamp_oak_log_stripped"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/swamp_oak_log_top"),
						ResourceLocation.fromNamespaceAndPath("bloomingnature", "block/swamp_oak_log_stripped_top")));
		buildDefault(new MaterialWood("swamp_oak", "bloomingnature", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS, swamp_oak)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		SpecialLocationsWood sycamore = new SpecialLocationsWood(
				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("cluttered", "sycamore_planks"),
						ResourceLocation.fromNamespaceAndPath("cluttered", "sycamore_log"),
						ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_sycamore_log"),
						ResourceLocation.fromNamespaceAndPath("cluttered", "sycamore_wood"),
						ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_sycamore_wood")),
				null, null);
		buildDefault(new MaterialWood("sycamore", "cluttered", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS, sycamore)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		SpecialLocationsWood waxed_oak = new SpecialLocationsWood(new ExistsLocationsWood(null, null,
				ResourceLocation.fromNamespaceAndPath("dawnoftimebuilder", "waxed_oak_log_stripped"), null, null),
				new ExistsLocationsWood(null, null,
						ResourceLocation.fromNamespaceAndPath("dawnoftimebuilder", "waxed_oak_log_stripped"), null,
						null),
				new SpecialTextureLocationsWood(null, null,
						ResourceLocation.fromNamespaceAndPath("dawnoftimebuilder", "block/waxed_oak_log_stripped"),
						null, ResourceLocation.fromNamespaceAndPath("dawnoftimebuilder",
								"block/waxed_oak_log_stripped_top")));
		buildDefault(new MaterialWood("waxed_oak", "dawnoftimebuilder", Generate.EXISTS, Generate.GENERATE,
				Generate.EXISTS, Generate.GENERATE, Generate.GENERATE, waxed_oak)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		SpecialLocationsWood willow = new SpecialLocationsWood(
				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("cluttered", "willow_planks"),
						ResourceLocation.fromNamespaceAndPath("cluttered", "willow_log"),
						ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_willow_log"),
						ResourceLocation.fromNamespaceAndPath("cluttered", "willow_wood"),
						ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_willow_wood")),
				null, null);
		buildDefault(new MaterialWood("willow", "cluttered", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS, willow)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		SpecialLocationsWood flowering_willow = new SpecialLocationsWood(
				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_willow_planks"),
						ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_willow_log"),
						ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_flowering_willow_log"),
						ResourceLocation.fromNamespaceAndPath("cluttered", "flowering_willow_wood"),
						ResourceLocation.fromNamespaceAndPath("cluttered", "stripped_flowering_willow_wood")),
				null, null);
		buildDefault(new MaterialWood("flowering_willow", "cluttered", Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS, Generate.EXISTS, flowering_willow)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(
						new ExtensionExtraPlanks(Generate.IGNORE, Generate.IGNORE, Generate.IGNORE, Generate.IGNORE)));

		SpecialLocationsWood wisewood = new SpecialLocationsWood(
				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("hazennstuff", "wisewood_planks"),
						ResourceLocation.fromNamespaceAndPath("hazennstuff", "wisewood_log"),
						ResourceLocation.fromNamespaceAndPath("hazennstuff", "stripped_wisewood_log"),
						ResourceLocation.fromNamespaceAndPath("hazennstuff", "wisewood_wood"),
						ResourceLocation.fromNamespaceAndPath("hazennstuff", "stripped_wisewood_wood")),
				null,
				new SpecialTextureLocationsWood(
						ResourceLocation.fromNamespaceAndPath("irons_spellbooks", "block/wisewood_planks"),
						ResourceLocation.fromNamespaceAndPath("hazennstuff", "block/wisewood_log_side"),
						ResourceLocation.fromNamespaceAndPath("hazennstuff", "block/stripped_wisewood_log_side"),
						ResourceLocation.fromNamespaceAndPath("hazennstuff", "block/wisewood_log_side"),
						ResourceLocation.fromNamespaceAndPath("hazennstuff", "block/stripped_wisewood_log_top")));
		buildDefault(new MaterialWood("wisewood", "hazennstuff", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS, wisewood)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		// The problem children
		SpecialLocationsWood whitewood = new SpecialLocationsWood(
				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("abyssal_decor", "white_wood_planks"),
						null, ResourceLocation.fromNamespaceAndPath("abyssal_decor", "white_wood_log"), null,
						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "white_wood_wood")),
				null,
				new SpecialTextureLocationsWood(
						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/whitewoodplanks"), null,
						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/whitewoodstrippedlog"),
						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/whitewoodstrippedlogtop"),
						ResourceLocation.fromNamespaceAndPath("abyssal_decor", "block/whitewoodstrippedlogtop")));
		buildDefault(new MaterialWood("white_wood", "abyssal_decor", Generate.EXISTS, Generate.EXISTS, Generate.IGNORE,
				Generate.EXISTS, Generate.IGNORE, whitewood)
				.addExtension(new ExtensionExtraLogs(Generate.IGNORE, Generate.IGNORE, Generate.IGNORE, Generate.IGNORE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		SpecialLocationsWood kopje = new SpecialLocationsWood(
				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("koopascritters", "kopje_fig_planks"),
						ResourceLocation.fromNamespaceAndPath("koopascritters", "kopje_fig_log"),
						ResourceLocation.fromNamespaceAndPath("koopascritters", "stripped_kopje_log"), null, null),
				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("koopascritters", "kopje_fig_planks"),
						ResourceLocation.fromNamespaceAndPath("koopascritters", "kopje_fig_log"),
						ResourceLocation.fromNamespaceAndPath("koopascritters", "stripped_kopje_log"), null, null),
				new SpecialTextureLocationsWood(
						ResourceLocation.fromNamespaceAndPath("koopascritters", "block/kopjeplanks"),
						ResourceLocation.fromNamespaceAndPath("koopascritters", "block/kopjelogside"),
						ResourceLocation.fromNamespaceAndPath("koopascritters", "block/kopjelogstrippedside"),
						ResourceLocation.fromNamespaceAndPath("koopascritters", "block/kopjelogtop"),
						ResourceLocation.fromNamespaceAndPath("koopascritters", "block/kopjelogstrippedtop")));
		buildDefault(new MaterialWood("kopje", "koopascritters", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.IGNORE, Generate.IGNORE, kopje)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));

		SpecialLocationsWood barn_wood = new SpecialLocationsWood(
				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("butchercraft", "barn_wood_block_item"),
						null, null, null, null),
				new ExistsLocationsWood(ResourceLocation.fromNamespaceAndPath("butchercraft", "barn_wood_block"), null,
						null, null, null),
				new SpecialTextureLocationsWood(
						ResourceLocation.fromNamespaceAndPath("butchercraft", "block/barn_wood_block"),
						ResourceLocation.fromNamespaceAndPath("compendium", "block/barn_wood_log"), null, null, null));
		buildDefault(new MaterialWood("barn_wood", "butchercraft", Generate.EXISTS, Generate.IGNORE, Generate.IGNORE,
				Generate.IGNORE, Generate.IGNORE, barn_wood)
				.addExtension(new ExtensionExtraLogs(Generate.IGNORE, Generate.IGNORE, Generate.IGNORE, Generate.IGNORE,
						Generate.IGNORE, Generate.IGNORE, Generate.IGNORE, Generate.IGNORE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));
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
