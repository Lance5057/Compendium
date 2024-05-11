package com.lance5057.compendium.index.json;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.lance5057.compendium.index.material.base.MaterialMetal;
import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;

public class IndexInitialResourceLoader {
	private static final Logger LOGGER = LogUtils.getLogger();
	private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
	
	public static void init()
	{
		Path path = Minecraft.getInstance().getResourcePackDirectory().resolve("test.json");
		
		try {
//			FileWriter f = new FileWriter(path.toString() + "\\test.json");
//			GSON.toJson(new MaterialMetal(), f);
//			f.close();
			
			Reader r = Files.newBufferedReader(path);
			MaterialMetal m = GSON.fromJson(r, MaterialMetal.class);
			r.close();
			
			LOGGER.info(m.stringTest);
			
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("JSONIOException!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("IOException!");
		}
	}
}
