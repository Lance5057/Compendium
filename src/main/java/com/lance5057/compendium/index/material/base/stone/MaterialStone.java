package com.lance5057.compendium.index.material.base.stone;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.components.block.IndexEntryComponent;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.index.material.base.MaterialTypeSerializer;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.base.stone.locations.SpecialLocationsStone;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.util.CompendiumBlockHandler;
import com.lance5057.compendium.util.TagUtil;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;

public class MaterialStone extends _MaterialBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5574491153486861741L;
	public final CompendiumBlockHandler COBBLESTONE;
	public final CompendiumBlockHandler STONE;
	public final CompendiumBlockHandler SMOOTH_STONE;

	public SpecialLocationsStone specialLocations;

	public MaterialStone(String name, String namespace) {
		this(name, namespace, null);
	}

	public MaterialStone(String name, String namespace, SpecialLocationsStone loc) {
		super(name, namespace);

		this.BLOCKS.add(COBBLESTONE = new CompendiumBlockHandler());
		this.BLOCKS.add(STONE = new CompendiumBlockHandler());
		this.BLOCKS.add(SMOOTH_STONE = new CompendiumBlockHandler());

		specialLocations = loc;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setup() {
		COBBLESTONE.setName(name + "_cobblestone");
		COBBLESTONE.setup(this, () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)));

		COBBLESTONE.setupItemTag(Tags.Items.COBBLESTONES);
		COBBLESTONE.setupItemTag(TagUtil.neoTag("cobblestones/" + name));
		COBBLESTONE.setupBlockTag(BlockTags.MINEABLE_WITH_PICKAXE);

		STONE.setName(name + "_stone");
		STONE.setup(this, () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

		STONE.setupItemTag(Tags.Items.STONES);
		STONE.setupItemTag(TagUtil.neoTag("stones/" + name));
		STONE.setupBlockTag(BlockTags.MINEABLE_WITH_PICKAXE);

		SMOOTH_STONE.setName(name + "_stone");
		SMOOTH_STONE.setup(this, () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_STONE)));

		SMOOTH_STONE.setupItemTag(Tags.Items.STONES);
		SMOOTH_STONE.setupItemTag(TagUtil.neoTag("stones/" + name));
		SMOOTH_STONE.setupBlockTag(BlockTags.MINEABLE_WITH_PICKAXE);

		this.extensions.forEach(i -> i.setup(this));
	}

//	@Override
//	public void tab(Output output) {
//		if (this.loadCobblestone)
//			output.accept(COBBLESTONE_ITEM);
//		if (this.loadSmooth)
//			output.accept(SMOOTH_ITEM);
//		if (this.loadStone)
//			output.accept(STONE_ITEM);
//
//		this.extensions.forEach(i -> i.tab(this, output));
//	}
//
//	@Override
//	public void blockStateModel(BlockStateProvider bsp) {
//		if (this.loadCobblestone)
//			bsp.simpleBlock(COBBLESTONE.get());
//		if (this.loadSmooth)
//			bsp.simpleBlock(SMOOTH.get());
//		if (this.loadStone)
//			bsp.simpleBlock(STONE.get());
//
//		this.extensions.forEach(i -> i.blockStateModel(this, bsp));
//	}
//
//	@Override
//	public void itemModel(ItemModelProvider tmp) {
//		if (this.loadCobblestone)
//			ItemModels.forBlockItem(tmp, COBBLESTONE_ITEM, name);
//		if (this.loadSmooth)
//			ItemModels.forBlockItem(tmp, SMOOTH_ITEM, name);
//		if (this.loadStone)
//			ItemModels.forBlockItem(tmp, STONE_ITEM, name);
//
//		this.extensions.forEach(i -> i.itemModel(this, tmp));
//	}

	@Override
	public void engLoc(LanguageProvider lp) {
		StringBuilder locName = new StringBuilder();
		for (String word : this.name.split("_")) {
			word = word.substring(0, 1).toUpperCase() + word.substring(1);
			locName.append(word).append(" ");
		}
		lp.add("compendium.tooltip.material." + this.name, locName.toString());

		this.extensions.forEach(i -> i.engLoc(this, lp));
	}

	@Override
	public void recipes(RecipeOutput consumer) {
		this.extensions.forEach(i -> i.recipes(this, consumer));
	}

	@Override
	public void blockLoot(BlockLootSubProvider blp) {
		this.extensions.forEach(i -> i.blockLoot(this, blp));
	}

//	@Override
//	public void setupItemTags(ItemTagsProvider itp) {
//		this.extensions.forEach(i -> i.setupItemTags(this, itp));
//	}
//
//	@Override
//	public void setupBlockTags(BlockTagsProvider itp) {
//		this.extensions.forEach(i -> i.setupBlockTags(this, itp));
//	}

	public static class Serializer extends MaterialTypeSerializer<MaterialStone> {

		public Serializer() {
			super("STONE");
		}

		@Override
		public MaterialStone deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			String name = j.get("name").getAsString();
			String tagNamespace = j.get("tagNamespace").getAsString();

			SpecialLocationsStone sp = null;

			if (j.get("specialLocations") != null)
				sp = context.deserialize(j.get("specialLocations"), SpecialLocationsStone.class);

			MaterialStone w = new MaterialStone(name, tagNamespace, sp);

			if (j.has("cobblestone"))
				w.COBBLESTONE.deserialize(j.get("cobblestone").getAsJsonObject());
			if (j.has("stone"))
				w.STONE.deserialize(j.get("stone").getAsJsonObject());
			if (j.has("smooth_stone"))
				w.SMOOTH_STONE.deserialize(j.get("smooth_stone").getAsJsonObject());

			JsonArray extensionsArray = j.getAsJsonArray("extensions");

			if (extensionsArray != null)
				for (JsonElement extensionElement : extensionsArray) {
					w.addExtension(context.deserialize(extensionElement, _MaterialExtension.class));
				}

			return w;
		}

		@Override
		public JsonElement serialize(MaterialStone src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("name", src.name);
			j.addProperty("tagNamespace", src.namespace);
			j.addProperty("type", type);

			j.add("cobblestone", src.COBBLESTONE.serialize());
			j.add("stone", src.STONE.serialize());
			j.add("smooth_stone", src.SMOOTH_STONE.serialize());

			if (src.specialLocations != null) {
				j.add("specialLocations", context.serialize(src.specialLocations, SpecialLocationsStone.class));
			}

			JsonArray ext = new JsonArray();

			for (_MaterialExtension e : src.extensions)
				ext.add(context.serialize(e));

			j.add("extensions", ext);

			return j;
		}

	}

//	@Override
//	public void setupClient(FMLClientSetupEvent event) {
//		this.extensions.forEach(i -> i.setupClient(this, event));
//	}

	@Override
	public Ingredient getBaseItem() {
		return Ingredient.of(this.STONE.BLOCK_ITEM);
	}

	@Override
	public MATERIAL_TYPES getType() {
		return MATERIAL_TYPES.STONE;
	}

//	@Override
//	public void blockModel(IndexBlockModelProvider ibmp) {
//		this.extensions.forEach(i -> i.blockModel(this, ibmp));
//	}

	@Override
	public void otherLoot(LootTableSubProvider lsp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attachComponents(ModifyDefaultComponentsEvent event) {
		if (STONE.isNotIgnored())
			event.modify(STONE.BLOCK_ITEM.get(),
					builder -> builder.set(CompendiumComponents.INDEX.get(), new IndexEntryComponent(getType(), name)));

		if (COBBLESTONE.isNotIgnored())
			event.modify(COBBLESTONE.BLOCK_ITEM.get(),
					builder -> builder.set(CompendiumComponents.INDEX.get(), new IndexEntryComponent(getType(), name)));

		if (SMOOTH_STONE.isNotIgnored())
			event.modify(SMOOTH_STONE.BLOCK_ITEM.get(),
					builder -> builder.set(CompendiumComponents.INDEX.get(), new IndexEntryComponent(getType(), name)));

		this.extensions.forEach(i -> i.attachComponents(this, event));
	}

}
