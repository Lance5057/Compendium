package com.lance5057.compendium.index.material.extensions;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.components.block.IndexEntryComponent;
import com.lance5057.compendium.index.IIndexEntry;
import com.lance5057.compendium.index.material.MaterialTypeRegistry;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.util.CompendiumBlockHandler;
import com.lance5057.compendium.index.util.CompendiumItemHandler;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;

public abstract class _MaterialExtension implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1904148996768649285L;

	public final Set<CompendiumItemHandler> ITEMS;
	public final Set<CompendiumBlockHandler> BLOCKS;

	public _MaterialExtension() {
		ITEMS = new HashSet<CompendiumItemHandler>();
		BLOCKS = new HashSet<CompendiumBlockHandler>();
	}

	public abstract void setup(_MaterialBase base);

	public abstract void tab(_MaterialBase base, Output output);

	protected boolean autoGenBlockModel = true;

	public _MaterialExtension noAutoGenBlockModel() {
		this.autoGenBlockModel = false;
		return this;
	}

	public _MaterialExtension generateAll() {
		BLOCKS.forEach(b -> b.setGenerate());
		ITEMS.forEach(b -> b.setGenerate());

		return this;
	}

//	public abstract void blockStateModel(_MaterialBase base, BlockStateProvider bsp);

//	protected boolean autoGenItemModel = true;
//
//	public _MaterialExtension noAutoGenItemModel() {
//		this.autoGenItemModel = false;
//		return this;
//	}

	public abstract void engLoc(_MaterialBase base, LanguageProvider lp);

	public abstract void recipes(_MaterialBase base, RecipeOutput consumer);

	public abstract void blockLoot(_MaterialBase base, BlockLootSubProvider blp);

	public abstract void otherLoot(_MaterialBase base, LootTableSubProvider lsp);

	public void attachComponents(_MaterialBase base, ModifyDefaultComponentsEvent event) {

		this.ITEMS.forEach(i -> {
			if (i.isNotIgnored())
				event.modify(i.ITEM.get(), builder -> builder.set(CompendiumComponents.INDEX.get(),
						new IndexEntryComponent(base.getType(), base.name)));
		});
		this.BLOCKS.forEach(i -> {
			if (i.isNotIgnored())
				event.modify(i.BLOCK_ITEM.get(), builder -> builder.set(CompendiumComponents.INDEX.get(),
						new IndexEntryComponent(base.getType(), base.name)));
		});
	}

	public static class Serializer extends MaterialExtensionSerializer<_MaterialExtension> {

		public Serializer() {
			super("EXTENSION");
		}

		@Override
		public _MaterialExtension deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			String type = j.get("type").getAsString();

			return MaterialTypeRegistry.getExtensionSerializer(type).deserialize(json, typeOfT, context);
		}

		@Override
		public JsonElement serialize(_MaterialExtension src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject obj = new JsonObject();
			obj.addProperty("type", type);

			context.serialize(src);
			return obj;
		}

	}

}
