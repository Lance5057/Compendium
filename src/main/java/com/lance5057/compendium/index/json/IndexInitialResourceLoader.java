package com.lance5057.compendium.index.json;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.slf4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.material.MaterialTypeRegistry;
import com.lance5057.compendium.index.material.base.MaterialMetal;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.extentions.ExtensionAdvancedTools;
import com.lance5057.compendium.index.material.extentions.ExtensionArmor;
import com.lance5057.compendium.index.material.extentions.ExtensionVanillaTools;
import com.lance5057.compendium.index.material.extentions.extrametalblocks.ExtensionExtraMetalBlocks;
import com.mojang.logging.LogUtils;

public class IndexInitialResourceLoader {
	// https://github.com/dyhe83/Gson-Polymorphism-Example/tree/master

	private static final Logger LOGGER = LogUtils.getLogger();
	private static final Gson GSON = MaterialTypeRegistry.setupGson().create();
	private static Path path = Path.of(".\\resourcepacks\\Compendium\\src\\main\\resources\\data\\materials");

	public static void init() {
		buildDefaults();
		read();
	}

	static void buildDefaults() {
		buildDefault(new MaterialMetal("tin", true, true, true)
				.addExtension(new ExtensionVanillaTools(true, true, true, true, true))
				.addExtension(new ExtensionAdvancedTools(true, true, true, true, true, true))
				.addExtension(new ExtensionArmor(true, true, 1, 1, 1, 1, 1, 1, 1))
				.addExtension(new ExtensionExtraMetalBlocks(true)));
		
		buildDefault(new MaterialMetal("iron", false, false, false)
				.addExtension(new ExtensionAdvancedTools(true, true, true, true, true, true))
				.addExtension(new ExtensionExtraMetalBlocks(true)));
	}

	static void buildDefault(_MaterialBase mat) {

		try {
			Files.createDirectories(path);
			Path p = path.resolve(mat.name + ".json");
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

	static void read() {
		try {
			Stream<Path> paths = Files.walk(path);
			paths.forEach(p -> {
				try {
					Reader r = Files.newBufferedReader(p);

					_MaterialBase m = GSON.fromJson(r, _MaterialBase.class);
					if (m != null) {
						CompendiumIndex.index.add(m);

					}

					r.close();

				} catch (IOException e) {
					LOGGER.error(e.getLocalizedMessage());
				}
			});
			paths.close();

		} catch (IOException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
	}
}
