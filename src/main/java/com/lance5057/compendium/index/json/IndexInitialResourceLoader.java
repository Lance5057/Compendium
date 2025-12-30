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
import com.lance5057.compendium.index.material.base.MaterialMetal;
import com.lance5057.compendium.index.material.base.MaterialTextile;
import com.lance5057.compendium.index.material.base.MaterialWood;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.extensions.ExtensionAdvancedTools;
import com.lance5057.compendium.index.material.extensions.wood.ExtensionExtraLogs;
import com.lance5057.compendium.index.material.extensions.wood.ExtensionExtraPlanks;
import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.neoforged.fml.ModList;
import net.neoforged.neoforgespi.language.IModFileInfo;
import net.neoforged.neoforgespi.locating.IModFile;

public class IndexInitialResourceLoader {
	// https://github.com/dyhe83/Gson-Polymorphism-Example/tree/master

	private static final Logger LOGGER = LogUtils.getLogger();
	private static final Gson GSON = MaterialTypeRegistry.setupGson().setVersion(1.0).create();
//	private static 

	public static void init() {
		if (Minecraft.getInstance() != null) { // check if we're in data gen first
			Path resourcePackPath = Minecraft.getInstance().getResourcePackDirectory()
					.resolve("compendium\\data\\compendium\\materials");
			for (MATERIAL_TYPES t : MATERIAL_TYPES.values())
				try {
					Files.createDirectories(resourcePackPath.resolve(t.toString().toLowerCase() + "/"));
				} catch (IOException e) {
					e.printStackTrace();
				}
//		
			readOtherMods();
			readResourcePacks(resourcePackPath);
		} else {
			Path resourcePackPath = Path.of(".\\..\\resources\\compendium\\data\\compendium\\materials");
			buildDefaults();
			readResourcePacks(resourcePackPath);
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
//		buildDefault(new MaterialMetal("tin", true, true, true)
//				.addExtension(new ExtensionVanillaTools(true, true, true, true, true))
//				.addExtension(new ExtensionAdvancedTools(true, true, true, true, true, true))
//				.addExtension(new ExtensionArmor(true, true, 1, 1, 1, 1, 1, 1, 1))
//				.addExtension(new ExtensionExtraMetalBlocks(true)));

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
		buildDefault(new MaterialWood("crimson", "minecraft", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));
		buildDefault(new MaterialWood("warped", "minecraft", Generate.EXISTS, Generate.EXISTS, Generate.EXISTS,
				Generate.EXISTS, Generate.EXISTS)
				.addExtension(new ExtensionExtraLogs(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE))
				.addExtension(new ExtensionExtraPlanks(Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
						Generate.GENERATE)));
		// buildDefault(new MaterialWood("pale_oak", false).addExtension(new
		// ExtensionExtraLogs(true, true, true, true)));

		buildDefault(new MaterialWood("fruit", "extradelight", Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
				Generate.GENERATE, Generate.GENERATE)
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

		buildDefault(new MaterialTextile("white_wool", "minecraft", Generate.EXISTS, Generate.IGNORE));
		buildDefault(new MaterialTextile("light_gray_wool", "minecraft", Generate.EXISTS, Generate.IGNORE));
		buildDefault(new MaterialTextile("gray_wool", "minecraft", Generate.EXISTS, Generate.IGNORE));
		buildDefault(new MaterialTextile("black_wool", "minecraft", Generate.EXISTS, Generate.IGNORE));
		buildDefault(new MaterialTextile("brown_wool", "minecraft", Generate.EXISTS, Generate.IGNORE));
		buildDefault(new MaterialTextile("red_wool", "minecraft", Generate.EXISTS, Generate.IGNORE));
		buildDefault(new MaterialTextile("orange_wool", "minecraft", Generate.EXISTS, Generate.IGNORE));
		buildDefault(new MaterialTextile("yellow_wool", "minecraft", Generate.EXISTS, Generate.IGNORE));
		buildDefault(new MaterialTextile("lime_wool", "minecraft", Generate.EXISTS, Generate.IGNORE));
		buildDefault(new MaterialTextile("green_wool", "minecraft", Generate.EXISTS, Generate.IGNORE));
		buildDefault(new MaterialTextile("cyan_wool", "minecraft", Generate.EXISTS, Generate.IGNORE));
		buildDefault(new MaterialTextile("light_blue_wool", "minecraft", Generate.EXISTS, Generate.IGNORE));
		buildDefault(new MaterialTextile("blue_wool", "minecraft", Generate.EXISTS, Generate.IGNORE));
		buildDefault(new MaterialTextile("purple_wool", "minecraft", Generate.EXISTS, Generate.IGNORE));
		buildDefault(new MaterialTextile("magenta_wool", "minecraft", Generate.EXISTS, Generate.IGNORE));
		buildDefault(new MaterialTextile("pink_wool", "minecraft", Generate.EXISTS, Generate.IGNORE));
	}

	static void buildDefault(_MaterialBase mat) {

		try {
			Path resourcePackPath = Path.of(".\\..\\resources\\compendium\\data\\compendium\\materials");
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
				CompendiumIndex.index.removeIf(i -> i.getName().compareTo(m.getName()) == 0);

				CompendiumIndex.addEntry(m);

			}

			r.close();

		} catch (IOException e) {
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
