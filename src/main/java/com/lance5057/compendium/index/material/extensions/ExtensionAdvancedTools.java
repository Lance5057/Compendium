package com.lance5057.compendium.index.material.extensions;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.CompendiumTags;
import com.lance5057.compendium.data.Recipes;
import com.lance5057.compendium.data.loottables.RecipeLootTables;
import com.lance5057.compendium.data.recipebuilders.WorkbenchRecipeBuilder;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.base.metal.MaterialMetal;
import com.lance5057.compendium.index.util.CompendiumItemHandler;
import com.lance5057.compendium.items.tools.HammerItem;
import com.lance5057.compendium.items.tools.PrybarItem;
import com.lance5057.compendium.items.tools.SawItem;
import com.lance5057.compendium.items.tools.ZweihanderItem;
import com.lance5057.compendium.util.TagUtil;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ExtensionAdvancedTools extends _MaterialExtension {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7214770173176898759L;
	public CompendiumItemHandler PRYBAR;
	public CompendiumItemHandler HAMMER;
	public CompendiumItemHandler SAW;
	public CompendiumItemHandler SHEARS;
	public CompendiumItemHandler ZWEIHANDER;
	public CompendiumItemHandler BOW;

	public ExtensionAdvancedTools() {
		this.ITEMS.add(PRYBAR = new CompendiumItemHandler());
		this.ITEMS.add(HAMMER = new CompendiumItemHandler());
		this.ITEMS.add(SAW = new CompendiumItemHandler());
		this.ITEMS.add(SHEARS = new CompendiumItemHandler());
		this.ITEMS.add(ZWEIHANDER = new CompendiumItemHandler());
		this.ITEMS.add(BOW = new CompendiumItemHandler());
	}

	@Override
	public void setup(_MaterialBase base) {
		PRYBAR.setName(base.name + "_prybar");
		PRYBAR.setup(base, () -> new PrybarItem(base.tier, new Item.Properties()));

		HAMMER.setName(base.name + "_hammer");
		HAMMER.setup(base, () -> new HammerItem(base.tier, new Item.Properties()));

		SAW.setName(base.name + "_saw");
		SAW.setup(base, () -> new SawItem(base.tier, new Item.Properties()));

		SHEARS.setName(base.name + "_shears");
		SHEARS.setup(base, () -> new ShearsItem(new Item.Properties()));

		ZWEIHANDER.setName(base.name + "_zweihander");
		ZWEIHANDER.setup(base, () -> new ZweihanderItem(base.tier, new Item.Properties()));

		BOW.setName(base.name + "_bow");
		BOW.setup(base, () -> new BowItem(new Item.Properties()));

	}

	@Override
	public void tab(_MaterialBase base, Output output) {
		PRYBAR.tab(base, output);
		HAMMER.tab(base, output);
		SAW.tab(base, output);
		SHEARS.tab(base, output);
		ZWEIHANDER.tab(base, output);
		BOW.tab(base, output);
	}

	@Override
	public void blockStateModel(_MaterialBase base, BlockStateProvider bsp) {

	}

//	@Override
//	public void itemModel(_MaterialBase base, ItemModelProvider tmp) {
//		if (PRYBAR.shouldGenerate())
//			DataUtil.basicMaterialItemWithExtraLayer(tmp, this.PRYBAR.ITEM.get(), base, "prybar", base.getType(),
//					Compendium.modLoc("item/prybar_base"));
//		if (HAMMER.shouldGenerate())
//			DataUtil.basicMaterialItemWithExtraLayer(tmp, this.HAMMER.ITEM.get(), base, "hammer", base.getType(),
//					Compendium.modLoc("item/hammer_base"));
//		if (SAW.shouldGenerate())
//			DataUtil.basicMaterialItemWithExtraLayer(tmp, this.SAW.ITEM.get(), base, "saw", base.getType(),
//					Compendium.modLoc("item/saw_base")).transforms()
//					.transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND).translation(0, -0.75f, -4.25f)
//					.rotation(0, 90, -40).end().transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
//					.translation(0, -0.75f, -4.25f).rotation(0, -90, 40).end().end();
//		if (SHEARS.shouldGenerate())
//			DataUtil.basicMaterialItemWithExtraLayer(tmp, this.SHEARS.ITEM.get(), base, "shears", base.getType(),
//					Compendium.modLoc("item/shears_base"));
//		if (ZWEIHANDER.shouldGenerate())
//			DataUtil.basicMaterialItemWithExtraLayer(tmp, this.ZWEIHANDER.ITEM.get(), base, "zweihander",
//					base.getType(), Compendium.modLoc("item/zweihander_base")).transforms()
//					.transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND).translation(1.13f, 3.2f, 1.13f)
//					.rotation(0, -90, -55).scale(1.5f, 1.5f, 0.85f).end()
//					.transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND).translation(1.13f, 3.2f, 1.13f)
//					.rotation(0, 90, 55).scale(1.5f, 1.5f, 0.85f).end()
//					.transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND).translation(0, 6, 0.5f).rotation(0, -90, -25)
//					.scale(1.5f, 1.5f, 0.85f).end().transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
//					.translation(0, 6, 0.5f).rotation(0, 90, 25).scale(1.5f, 1.5f, 0.85f).end().end();
//		if (BOW.shouldGenerate()) {
//			DataUtil.basicMaterialBow(tmp, this.BOW.ITEM.get(), base, base.getType());
//
//		}
//	}

	@Override
	public void engLoc(_MaterialBase base, LanguageProvider lp) {
		StringBuilder material_name = new StringBuilder();
		for (String word : base.name.split("_")) {
			word = word.substring(0, 1).toUpperCase() + word.substring(1);
			material_name.append(word).append(" ");
		}
		if (PRYBAR.shouldGenerate()) {
			lp.add(this.PRYBAR.ITEM.asItem(), material_name + "Prybar");
		}
		if (HAMMER.shouldGenerate()) {
			lp.add(this.HAMMER.ITEM.asItem(), material_name + "Hammer");
		}
		if (SAW.shouldGenerate()) {
			lp.add(this.SAW.ITEM.asItem(), material_name + "Saw");
		}
		if (SHEARS.shouldGenerate()) {
			lp.add(this.SHEARS.ITEM.asItem(), material_name + "Shears");
		}
		if (ZWEIHANDER.shouldGenerate()) {
			lp.add(this.ZWEIHANDER.ITEM.asItem(), material_name + "Zweihänder");
		}
		if (BOW.shouldGenerate()) {
			lp.add(this.BOW.ITEM.asItem(), material_name + "Bow");
		}
	}

	@Override
	public void recipes(_MaterialBase base, RecipeOutput consumer) {
		if (base instanceof MaterialMetal metal) {
			if (PRYBAR.shouldGenerate())
				WorkbenchRecipeBuilder.shaped(PRYBAR.ITEM.toStack())
						.define('i', ItemTags.create(TagUtil.neoTag("ingots/" + base.name))).define('s', Items.STICK)
						.pattern("i  ").pattern(" s ").pattern("  i")
						.tool(Ingredient.of(CompendiumTags.HAMMER), 4, true, RecipeLootTables.EMPTY, List.of(),
								Recipes.standardHammeringModel(TagUtil.modLoc("gold_hammer"), 0))
						.save(consumer);

			if (HAMMER.shouldGenerate())
				WorkbenchRecipeBuilder.shaped(HAMMER.ITEM.toStack())
						.define('i', ItemTags.create(TagUtil.neoTag("ingots/" + base.name)))
						.define('b', ItemTags.create(TagUtil.neoTag("storage_blocks/" + base.name)))
						.define('s', Items.STICK).pattern("ibi").pattern(" s ").pattern(" s ")
						.tool(Ingredient.of(CompendiumTags.HAMMER), 4, true, RecipeLootTables.EMPTY, List.of(),
								Recipes.standardHammeringModel(TagUtil.modLoc("gold_hammer"), 0))
						.save(consumer);

			if (SAW.shouldGenerate())
				WorkbenchRecipeBuilder.shaped(SAW.ITEM.toStack())
						.define('i', ItemTags.create(TagUtil.neoTag("ingots/" + base.name)))
						.define('b', ItemTags.create(TagUtil.neoTag("storage_blocks/" + base.name)))
						.define('s', Items.STICK).pattern("ibs")
						.tool(Ingredient.of(CompendiumTags.HAMMER), 4, true, RecipeLootTables.EMPTY, List.of(),
								Recipes.standardHammeringModel(TagUtil.modLoc("gold_hammer"), 0))
						.save(consumer);

			if (SHEARS.shouldGenerate())
				WorkbenchRecipeBuilder.shaped(SHEARS.ITEM.toStack())
						.define('i', ItemTags.create(TagUtil.neoTag("ingots/" + base.name))).pattern("i ").pattern(" i")
						.tool(Ingredient.of(CompendiumTags.HAMMER), 4, true, RecipeLootTables.EMPTY, List.of(),
								Recipes.standardHammeringModel(TagUtil.modLoc("gold_hammer"), 0))
						.save(consumer);

			if (ZWEIHANDER.shouldGenerate())
				WorkbenchRecipeBuilder.shaped(ZWEIHANDER.ITEM.toStack())
						.define('i', ItemTags.create(TagUtil.neoTag("ingots/" + base.name)))
						.define('b', ItemTags.create(TagUtil.neoTag("storage_blocks/" + base.name)))
						.define('s', Items.STICK).pattern("  i").pattern(" b ").pattern("s  ")
						.tool(Ingredient.of(CompendiumTags.HAMMER), 4, true, RecipeLootTables.EMPTY, List.of(),
								Recipes.standardHammeringModel(TagUtil.modLoc("gold_hammer"), 0))
						.save(consumer);

			if (BOW.shouldGenerate())
				WorkbenchRecipeBuilder.shaped(BOW.ITEM.toStack())
						.define('i', ItemTags.create(TagUtil.neoTag("ingots/" + base.name))).define('b', Items.BOW)
						.pattern("i  ").pattern(" b ").pattern("  i")
						.tool(Ingredient.of(CompendiumTags.HAMMER), 4, true, RecipeLootTables.EMPTY, List.of(),
								Recipes.standardHammeringModel(TagUtil.modLoc("gold_hammer"), 0))
						.save(consumer);
		}
	}

	@Override
	public void blockLoot(_MaterialBase base, BlockLootSubProvider blp) {
		// TODO Auto-generated method stub

	}

//	@Override
//	public void setupItemTags(_MaterialBase base, ItemTagsProvider itp) {
////		if (!BOW.isIgnored()) {
////			itp.tag(Tags.Items.TOOLS_BOW).add(this.BOW.ITEM.get());
////			itp.tag(Tags.Items.RANGED_WEAPON_TOOLS).add(this.BOW.ITEM.get());
////		}
////		if (!HAMMER.isIgnored()) {
////			itp.tag(CompendiumTags.HAMMER).add(this.HAMMER.ITEM.get());
////		}
////		if (!PRYBAR.isIgnored()) {
////			itp.tag(CompendiumTags.PRYBAR).add(this.PRYBAR.ITEM.get());
////		}
////		if (!SAW.isIgnored()) {
////			itp.tag(CompendiumTags.SAW).add(this.SAW.ITEM.get());
////		}
////		if (!SHEARS.isIgnored()) {
////			itp.tag(Tags.Items.TOOLS_SHEAR).add(this.SHEARS.ITEM.get());
////		}
////		if (!ZWEIHANDER.isIgnored()) {
////			itp.tag(Tags.Items.MELEE_WEAPON_TOOLS).add(this.ZWEIHANDER.ITEM.get());
////			itp.tag(ItemTags.SWORDS).add(this.ZWEIHANDER.ITEM.get());
////		}
//	}
//
//	@Override
//	public void setupBlockTags(_MaterialBase base, BlockTagsProvider itp) {
//
//	}
//
//	@Override
//	public void setupClient(_MaterialBase base, FMLClientSetupEvent event) {
//		if (!BOW.isIgnored()) {
//			ItemProperties.register(this.BOW.ITEM.get(), ResourceLocation.withDefaultNamespace("pull"),
//					(p_174635_, p_174636_, p_174637_, p_174638_) -> {
//						if (p_174637_ == null) {
//							return 0.0F;
//						} else {
//							return p_174637_.getUseItem() != p_174635_ ? 0.0F
//									: (float) (p_174635_.getUseDuration(p_174637_)
//											- p_174637_.getUseItemRemainingTicks()) / 20.0F;
//						}
//					});
//
//			ItemProperties.register(this.BOW.ITEM.get(), ResourceLocation.withDefaultNamespace("pulling"),
//					(p_174630_, p_174631_, p_174632_, p_174633_) -> {
//						return p_174632_ != null && p_174632_.isUsingItem() && p_174632_.getUseItem() == p_174630_
//								? 1.0F
//								: 0.0F;
//					});
//		}
//	}

	public static class Serializer extends MaterialExtensionSerializer<ExtensionAdvancedTools> {

		public Serializer() {
			super("ADVANCEDTOOLS");
		}

		@Override
		public JsonElement serialize(ExtensionAdvancedTools src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("type", type);
			j.add("prybar", src.PRYBAR.serialize());
			j.add("hammer", src.HAMMER.serialize());
			j.add("saw", src.SAW.serialize());
			j.add("shears", src.SHEARS.serialize());
			j.add("zweihander", src.ZWEIHANDER.serialize());
			j.add("bow", src.BOW.serialize());

			return j;
		}

		@Override
		public ExtensionAdvancedTools deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			ExtensionAdvancedTools eat = new ExtensionAdvancedTools();

			eat.PRYBAR.deserialize(j.get("prybar").getAsJsonObject());
			eat.HAMMER.deserialize(j.get("hammer").getAsJsonObject());
			eat.SAW.deserialize(j.get("saw").getAsJsonObject());
			eat.SHEARS.deserialize(j.get("shears").getAsJsonObject());
			eat.ZWEIHANDER.deserialize(j.get("zweihander").getAsJsonObject());
			eat.BOW.deserialize(j.get("bow").getAsJsonObject());

			return eat;
		}

	}

	@Override
	public void otherLoot(_MaterialBase base, LootTableSubProvider lsp) {
		// TODO Auto-generated method stub

	}

//	@Override
//	public boolean isIndexItem(_MaterialBase base, ItemStack stack) {
//		if (PRYBAR.is(stack))
//			return true;
//		if (HAMMER.is(stack))
//			return true;
//		if (SAW.is(stack))
//			return true;
//		if (SHEARS.is(stack))
//			return true;
//		if (ZWEIHANDER.is(stack))
//			return true;
//		if (BOW.is(stack))
//			return true;
//
//		return false;
//	}
//
//	@Override
//	public Optional<IIndexEntry> getEntryItemBelongsTo(_MaterialBase base, ItemStack stack) {
//		if (PRYBAR.is(stack))
//			return Optional.of(base);
//		if (HAMMER.is(stack))
//			return Optional.of(base);
//		if (SAW.is(stack))
//			return Optional.of(base);
//		if (SHEARS.is(stack))
//			return Optional.of(base);
//		if (ZWEIHANDER.is(stack))
//			return Optional.of(base);
//		if (BOW.is(stack))
//			return Optional.of(base);
//
//		return Optional.empty();
//	}
//
//	@Override
//	public void attachComponents(_MaterialBase base, ModifyDefaultComponentsEvent event) {
//		// TODO Auto-generated method stub
//
//	}

}
