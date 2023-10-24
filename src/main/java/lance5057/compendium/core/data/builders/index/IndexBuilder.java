package lance5057.compendium.core.data.builders.index;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import com.google.gson.JsonObject;

import lance5057.compendium.core.data.builders.index.parts.IPartProvider;
import lance5057.compendium.core.data.builders.index.parts.tools.ToolHeadPart;
import lance5057.compendium.core.data.builders.index.parts.weapons.WeaponHeadPart;
import lance5057.compendium.indexes.data.IndexEntry;
import net.minecraft.resources.ResourceLocation;

public class IndexBuilder {
	private final String modid;
	private final String name;
	private final String matType;
	private List<IPartProvider> parts;

	private IndexBuilder(String modid, String name, String type) {
		this.modid = modid;
		this.name = name;
		this.matType = type;
		parts = new ArrayList<IPartProvider>();
	}

	public static IndexBuilder make(String modid, String name, String type) {
		return new IndexBuilder(modid, name, type);
	}

	public IndexBuilder addWeaponHead(int durability, int enchantability, float weaponDamage, float weaponSpeed) {
		parts.add(new WeaponHeadPart(enchantability, enchantability, weaponSpeed, weaponSpeed));
		return this;
	}

	public IndexBuilder addToolHead(int durability, int enchantability, float weaponDamage, float weaponSpeed,
			float digSpeed) {
		parts.add(new ToolHeadPart(enchantability, enchantability, weaponSpeed, weaponSpeed, digSpeed));
		return this;
	}

	public void save(Consumer<Result> consumerIn) {
		ResourceLocation id = new ResourceLocation(modid, name);
		this.validate(id);
		consumerIn.accept(new Result(id, name, matType, parts));
	}

	private void validate(ResourceLocation id) {
		if (IndexEntry.MATERIAL_TYPE.valueOf(matType) == null) {
			throw new IllegalStateException("matType:" + matType + " - invalid type! Valid Types -"
					+ Stream.of(IndexEntry.MATERIAL_TYPE.values()).map(Enum::name).toString());
		}
	}

	public static class Result {
		private final ResourceLocation id;
		private final String name;
		private final String matType;
		private List<IPartProvider> parts;

		public Result(ResourceLocation idIn, String name, String type, List<IPartProvider> parts) {
			this.id = idIn;
			this.parts = parts;
			this.name = name;
			this.matType = type;
		}

		public JsonObject serializeData() {
			JsonObject json = new JsonObject();

			json.addProperty("name", name);
			json.addProperty("material_type", matType);

			for (IPartProvider p : parts) {
				JsonObject e = new JsonObject();
				p.serialize(e);
				json.add(p.getPartName(), e);
			}
			return json;
		}

		public ResourceLocation getId() {
			return this.id;
		}
	}
}
