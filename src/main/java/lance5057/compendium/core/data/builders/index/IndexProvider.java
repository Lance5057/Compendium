package lance5057.compendium.core.data.builders.index;

import java.io.IOException;
import java.util.Set;
import java.util.function.Consumer;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lance5057.compendium.Compendium;
import lance5057.compendium.Reference;
import lance5057.compendium.core.data.builders.index.IndexBuilder.Result;
import lance5057.compendium.indexes.data.IndexEntry;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;

public class IndexProvider implements DataProvider {
	private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().setLenient()
			.create();
	protected final DataGenerator.PathProvider pathProvider;
	private final DataGenerator gen;
	private final String modid;

	public IndexProvider(DataGenerator generator) {
		this.gen = generator;
		this.modid = Reference.MOD_ID;

		this.pathProvider = gen.createPathProvider(DataGenerator.Target.DATA_PACK, "indexes");
	}

	void buildIndexes(Consumer<Result> consumer) {
		IndexBuilder.make(modid, "oak", IndexEntry.MATERIAL_TYPE.wood.toString()).addWeaponHead(100, 2, 1, 1).addToolHead(100, 2, 1, 1, 1)
				.save(consumer);
	}

	@Override
	public void run(CachedOutput pOutput) throws IOException {
		Set<ResourceLocation> set = Sets.newHashSet();
		buildIndexes((build) -> {
			if (!set.add(build.getId())) {
				throw new IllegalStateException("Duplicate index " + build.getId());
			} else {
				try {
					DataProvider.saveStable(pOutput, build.serializeData(), this.pathProvider.json(build.getId()));
				} catch (IOException ioexception) {
					Compendium.logger.error("Couldn't save recipe {}", this.pathProvider.json(build.getId()),
							ioexception);
				}
			}
		});

	}

	@Override
	public String getName() {
		return "Index Provider";
	}

}
