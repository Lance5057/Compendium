package com.lance5057.compendium.index.material.extentions;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumTags;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.util.DataUtil;
import com.lance5057.compendium.items.tools.HammerItem;
import com.lance5057.compendium.items.tools.PrybarItem;
import com.lance5057.compendium.items.tools.SawItem;
import com.lance5057.compendium.items.tools.ZweihanderItem;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ShearsItem;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredItem;

public class ExtensionAdvancedTools extends _MaterialExtension {
	boolean loadPrybar;
	boolean loadHammer;
	boolean loadSaw;
	boolean loadShears;
	boolean loadZweihander;
	boolean loadBow;

	public DeferredItem<Item> PRYBAR;
	public DeferredItem<Item> HAMMER;
	public DeferredItem<Item> SAW;
	public DeferredItem<Item> SHEARS;
	public DeferredItem<Item> ZWEIHANDER;
	public DeferredItem<Item> BOW;

	public ExtensionAdvancedTools(boolean loadPrybar, boolean loadHammer, boolean loadSaw, boolean loadShears,
			boolean loadZweihander, boolean loadBow) {
		this.loadPrybar = loadPrybar;
		this.loadHammer = loadHammer;
		this.loadSaw = loadSaw;
		this.loadShears = loadShears;
		this.loadZweihander = loadZweihander;
		this.loadBow = loadBow;
	}

	@Override
	public void setup(_MaterialBase base) {
		if (this.loadPrybar)
			PRYBAR = CompendiumIndex.ITEMS.register(base.name + "_prybar",
					() -> new PrybarItem(base.tier, new Item.Properties()));
		if (this.loadHammer)
			HAMMER = CompendiumIndex.ITEMS.register(base.name + "_hammer",
					() -> new HammerItem(base.tier, new Item.Properties()));
		if (this.loadSaw)
			SAW = CompendiumIndex.ITEMS.register(base.name + "_saw",
					() -> new SawItem(base.tier, new Item.Properties()));
		if (this.loadShears)
			SHEARS = CompendiumIndex.ITEMS.register(base.name + "_shears", () -> new ShearsItem(new Item.Properties()));
		if (this.loadZweihander)
			ZWEIHANDER = CompendiumIndex.ITEMS.register(base.name + "_zweihander",
					() -> new ZweihanderItem(base.tier, new Item.Properties()));
		if (this.loadBow)
			BOW = CompendiumIndex.ITEMS.register(base.name + "_bow", () -> new BowItem(new Item.Properties()));
	}

	@Override
	public void tab(_MaterialBase base, Output output) {
		if (this.loadPrybar)
			output.accept(PRYBAR);
		if (this.loadHammer)
			output.accept(HAMMER);
		if (this.loadSaw)
			output.accept(SAW);
		if (this.loadShears)
			output.accept(SHEARS);
		if (this.loadZweihander)
			output.accept(ZWEIHANDER);
		if (this.loadBow)
			output.accept(BOW);
	}

	@Override
	public void blockModel(_MaterialBase base, BlockStateProvider bsp) {

	}

	@Override
	public void itemModel(_MaterialBase base, ItemModelProvider tmp) {
		if (this.loadPrybar)
			DataUtil.basicMaterialItemWithExtraLayer(tmp, this.PRYBAR.get(), base, "prybar", base.getType(),
					Compendium.modLoc("item/prybar_base"));
		if (this.loadHammer)
			DataUtil.basicMaterialItemWithExtraLayer(tmp, this.HAMMER.get(), base, "hammer", base.getType(),
					Compendium.modLoc("item/hammer_base"));
		if (this.loadSaw)
			DataUtil.basicMaterialItemWithExtraLayer(tmp, this.SAW.get(), base, "saw", base.getType(),
					Compendium.modLoc("item/saw_base"));
		if (this.loadShears)
			DataUtil.basicMaterialItemWithExtraLayer(tmp, this.SHEARS.get(), base, "shears", base.getType(),
					Compendium.modLoc("item/shears_base"));
		if (this.loadZweihander)
			DataUtil.basicMaterialItemWithExtraLayer(tmp, this.ZWEIHANDER.get(), base, "zweihander", base.getType(),
					Compendium.modLoc("item/zweihander_base")).transforms()
					.transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND).translation(1.13f, 3.2f, 1.13f)
					.rotation(0, -90, -55).scale(1.5f, 1.5f, 0.85f).end()
					.transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND).translation(1.13f, 3.2f, 1.13f)
					.rotation(0, 90, 55).scale(1.5f, 1.5f, 0.85f).end()
					.transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND).translation(0, 6, 0.5f).rotation(0, -90, -25)
					.scale(1.5f, 1.5f, 0.85f).end().transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
					.translation(0, 6, 0.5f).rotation(0, 90, 25).scale(1.5f, 1.5f, 0.85f).end().end();
		if (this.loadBow) {
			DataUtil.basicMaterialBow(tmp, this.BOW.get(), base, base.getType());

		}
	}

	@Override
	public void engLoc(_MaterialBase base, LanguageProvider lp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void recipes(_MaterialBase base, RecipeOutput consumer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void blockLoot(_MaterialBase base, BlockLootSubProvider blp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupItemTags(_MaterialBase base, ItemTagsProvider itp) {
		if (loadHammer)
			itp.tag(CompendiumTags.HAMMER).add(this.HAMMER.get());
	}

	@Override
	public void setupBlockTags(_MaterialBase base, BlockTagsProvider itp) {

	}

	@Override
	public void setupClient(_MaterialBase base, FMLClientSetupEvent event) {
		ItemProperties.register(this.BOW.get(), ResourceLocation.withDefaultNamespace("pull"),
				(p_174635_, p_174636_, p_174637_, p_174638_) -> {
					if (p_174637_ == null) {
						return 0.0F;
					} else {
						return p_174637_.getUseItem() != p_174635_ ? 0.0F
								: (float) (p_174635_.getUseDuration(p_174637_) - p_174637_.getUseItemRemainingTicks())
										/ 20.0F;
					}
				});

		ItemProperties.register(this.BOW.get(), ResourceLocation.withDefaultNamespace("pulling"),
				(p_174630_, p_174631_, p_174632_, p_174633_) -> {
					return p_174632_ != null && p_174632_.isUsingItem() && p_174632_.getUseItem() == p_174630_ ? 1.0F
							: 0.0F;
				});
	}

	public static class Serializer extends MaterialExtensionSerializer<ExtensionAdvancedTools> {

		public Serializer() {
			super("ADVANCEDTOOLS");
		}

		@Override
		public JsonElement serialize(ExtensionAdvancedTools src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("type", type);
			j.addProperty("loadPrybar", src.loadPrybar);
			j.addProperty("loadHammer", src.loadHammer);
			j.addProperty("loadSaw", src.loadSaw);
			j.addProperty("loadShears", src.loadShears);
			j.addProperty("loadZweihander", src.loadZweihander);
			j.addProperty("loadBow", src.loadBow);

			return j;
		}

		@Override
		public ExtensionAdvancedTools deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			boolean prybar = j.get("loadPrybar").getAsBoolean();
			boolean hammer = j.get("loadHammer").getAsBoolean();
			boolean saw = j.get("loadSaw").getAsBoolean();
			boolean shears = j.get("loadShears").getAsBoolean();
			boolean zweihander = j.get("loadZweihander").getAsBoolean();
			boolean bow = j.get("loadBow").getAsBoolean();

			return new ExtensionAdvancedTools(prybar, hammer, saw, shears, zweihander, bow);
		}

	}

}
