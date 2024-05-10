package com.lance5057.compendium.index.json;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.lance5057.compendium.index.material.basetypes.MaterialMetal;
import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;

public class IndexInitialResourceLoader {
	private static final Logger LOGGER = LogUtils.getLogger();
	private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
	
	public static void init()
	{
		Path path = Minecraft.getInstance().getResourcePackDirectory();
		
		try {
			FileWriter f = new FileWriter(path.toString() + "\\test.json");
			GSON.toJson(new MaterialMetal(), f);
			f.close();
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
