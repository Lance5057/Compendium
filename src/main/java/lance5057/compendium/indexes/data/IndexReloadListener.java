package lance5057.compendium.indexes.data;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mojang.logging.LogUtils;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;

public class IndexReloadListener extends SimplePreparableReloadListener<Map<ResourceLocation, List<JsonElement>>> {

	private static final Logger LOGGER = LogUtils.getLogger();
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
	private static final String PATH_SUFFIX = ".json";
	private static final int PATH_SUFFIX_LENGTH = ".json".length();
	private static final String DIRECTORY = "indexes";

	@Override
	protected Map<ResourceLocation, List<JsonElement>> prepare(ResourceManager pResourceManager,
			ProfilerFiller pProfiler) {
		Map<ResourceLocation, List<JsonElement>> entityModelJsons = new HashMap<>();

        for (Map.Entry<ResourceLocation, List<Resource>> entry : pResourceManager.listResourceStacks(DIRECTORY, id -> id.getPath().endsWith(".json")).entrySet()) {
            ResourceLocation fullLocation = entry.getKey();
            String fullPath = fullLocation.getPath();
            ResourceLocation subLocation = new ResourceLocation(fullLocation.getNamespace(), fullPath.substring(DIRECTORY.length() + 1, fullPath.length() - PATH_SUFFIX_LENGTH));

            for (Resource resource : entry.getValue()) {
                try (Reader reader = resource.openAsReader()) {
                    entityModelJsons.computeIfAbsent(subLocation, k -> new ArrayList<>()).add(GsonHelper.fromJson(GSON, reader, JsonElement.class));
                } catch (IllegalArgumentException | IOException | JsonParseException e) {
                    LOGGER.error("Couldn't parse data file {} from {}", fullLocation, subLocation, e);
                }
            }
        }

        return entityModelJsons;
	}

	@Override
	protected void apply(Map<ResourceLocation, List<JsonElement>> pObject, ResourceManager pResourceManager,
			ProfilerFiller pProfiler) {
		for (var entry : pObject.entrySet()) {
		
		}
	}

}
