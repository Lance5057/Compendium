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
import com.lance5057.compendium.index.material.MaterialTypeRegistry;
import com.lance5057.compendium.index.material.base.MaterialGlass;
import com.lance5057.compendium.index.material.base.MaterialMetal;
import com.lance5057.compendium.index.material.base.MaterialWood;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.extentions.ExtensionAdvancedTools;
import com.lance5057.compendium.index.material.extentions.ExtensionArmor;
import com.lance5057.compendium.index.material.extentions.ExtensionVanillaTools;
import com.lance5057.compendium.index.material.extentions.metal.ExtensionExtraMetalBlocks;
import com.lance5057.compendium.index.material.extentions.wood.ExtensionExtraLogs;
import com.mojang.logging.LogUtils;

import net.neoforged.fml.ModList;
import net.neoforged.neoforgespi.language.IModFileInfo;
import net.neoforged.neoforgespi.locating.IModFile;

public class IndexInitialResourceLoader {
	// https://github.com/dyhe83/Gson-Polymorphism-Example/tree/master

	private static final Logger LOGGER = LogUtils.getLogger();
	private static final Gson GSON = MaterialTypeRegistry.setupGson().create();
	private static Path resourcePackPath = Path.of(".\\..\\src\\main\\resources\\data\\compendium\\materials");

	public static void init() {
		buildDefaults();
		readOtherMods();
		readResourcePacks();
	}

	static String zipPath = "resources/data/compendium/materials";

	private static void readOtherMods() {
		Stream<Path> paths = ModList.get().getModFiles().stream().map(IModFileInfo::getFile).map(IModFile::getFilePath);
		Collection<URL> urls = paths.map(Path::toUri).map(uri -> {
			URL url = null;
			try {
				url = uri.toURL();
			} catch (MalformedURLException e) {
				Compendium.LOGGER.error("Unable to scan path");
				Compendium.LOGGER.trace(e);
			}
			return url;
		}).filter(Objects::nonNull).collect(Collectors.toList());

		urls.forEach(url -> {

			try {
				try (ZipFile zipFile = new ZipFile(new File(url.toURI()))) {
					for (ZipEntry entry : Collections.list(zipFile.entries())) {
						if (entry.getName().contains(zipPath) && entry.getName().endsWith("json")) {
							InputStream stream = zipFile.getInputStream(entry);
							readFile(stream);
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
		buildDefault(new MaterialMetal("tin", true, true, true)
				.addExtension(new ExtensionVanillaTools(true, true, true, true, true))
				.addExtension(new ExtensionAdvancedTools(true, true, true, true, true, true))
				.addExtension(new ExtensionArmor(true, true, 1, 1, 1, 1, 1, 1, 1))
				.addExtension(new ExtensionExtraMetalBlocks(true)));

		buildDefault(new MaterialMetal("iron", false, false, false)
				.addExtension(new ExtensionAdvancedTools(true, true, true, false, true, true))
				.addExtension(new ExtensionExtraMetalBlocks(true)));

		buildDefault(new MaterialGlass("glass", false, false));

		buildDefault(new MaterialWood("oak", false).addExtension(new ExtensionExtraLogs(true, true, true, true)));
	}

	static void buildDefault(_MaterialBase mat) {

		try {
			Files.createDirectories(resourcePackPath);
			Path p = resourcePackPath.resolve(mat.name + ".json");
			if (Files.exists(p))
				Files.delete(p);
//			else {
			Writer w = Files.newBufferedWriter(p);
			String g = GSON.toJson(mat);
			w.write(g);
			w.close();
//			}

		} catch (JsonIOException e) {
			LOGGER.error(e.getLocalizedMessage());
		} catch (IOException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
	}

	static void readResourcePacks() {
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

				CompendiumIndex.index.add(m);

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

				CompendiumIndex.index.add(m);

			}

			r.close();

		} catch (IOException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
	}
}
