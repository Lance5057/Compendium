package com.lance5057.compendium.index.json;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.slf4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.lance5057.compendium.index.material.base.MaterialMetal;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;

public class IndexInitialResourceLoader {
	//https://github.com/dyhe83/Gson-Polymorphism-Example/tree/master
	
	private static final Logger LOGGER = LogUtils.getLogger();
	private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping()
			.registerTypeAdapterFactory(new MaterialTypeAdapterFactory()).create();
	private static final Path path = Minecraft.getInstance().getResourcePackDirectory()
			.resolve("Compendium\\src\\main\\resources\\data\\materials\\");

	public static void init() {
		buildDefaults();
		read();
	}

	static void buildDefaults() {
		buildDefault(new MaterialMetal("iron", false, false, false));
	}

	static void buildDefault(_MaterialBase mat) {

		try {
			Files.createDirectories(path);
			Path p = path.resolve(mat.name + ".json");
			if (Files.exists(path))
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
					
					_MaterialBase b = GSON.fromJson(r, _MaterialBase.class);
					r.close();
					
				} catch (IOException e) {
					LOGGER.error(e.getLocalizedMessage());
				}
			});
			
		} catch (IOException e) {
			LOGGER.error(e.getLocalizedMessage());
		}
	}
}
