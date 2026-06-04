package com.lance5057.compendium.index.material.extensions.gem;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.CompendiumTags;
import com.lance5057.compendium.blocks.SimpleStyleBlock;
import com.lance5057.compendium.client.BlacklistedModel;
import com.lance5057.compendium.components.block.IndexEntryComponent;
import com.lance5057.compendium.components.block.StyleBlockComponent;
import com.lance5057.compendium.data.loottables.BlockLootTables;
import com.lance5057.compendium.data.loottables.RecipeLootTables;
import com.lance5057.compendium.data.recipebuilders.HammeringRecipeBuilder;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.base.gem.MaterialGem;
import com.lance5057.compendium.index.material.extensions.MaterialExtensionSerializer;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.util.CompendiumBlockHandler;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.util.TagUtil;
import com.lance5057.compendium.util.rendering.animation.floats.AnimatedFloat;
import com.lance5057.compendium.util.rendering.animation.floats.AnimatedFloatVector3;
import com.lance5057.compendium.util.rendering.animation.floats.AnimationFloatTransform;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ExtensionGemStyleBlocks extends _MaterialExtension {

	private static final long serialVersionUID = 9038154957807121881L;

	public CompendiumBlockHandler BLOCK;

	public ExtensionGemStyleBlocks() {
		this.BLOCKS.add(BLOCK = new CompendiumBlockHandler());
	}

	@Override
	public void setup(_MaterialBase base) {
		BLOCK.setName(base.name + "_styled_gem");
		BLOCK.setup(base,
				() -> new SimpleStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS),
						Compendium.modLoc(base.name + "_styled_gem"), base.getType(), base.name, List
								.of("gem_block"),
						StyleData.GEM_BLOCK),
				() -> new BlockItem(BLOCK.BLOCK.get(), new Item.Properties()
						.component(CompendiumComponents.STYLE,
								new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))
						.component(CompendiumComponents.INDEX, new IndexEntryComponent(base.getType(), base.name))));
//		BLOCK.setupItemTag(CompendiumTags.);
//		BLOCK.setupItemTag(TagUtil.neoTag("BLOCK/" + base.name));
		BLOCK.setupBlockTag(BlockTags.MINEABLE_WITH_PICKAXE);
		BLOCK.setupBlockTag(CompendiumTags.CREATE_SAFE_NBT);
		BLOCK.setAsValidStyleBlock();
		BLOCK.setAsValidStyleItem();
	}

	@Override
	public void tab(_MaterialBase base, Output out) {
		BLOCK.tab(base, out);
	}

	@Override
	public void engLoc(_MaterialBase base, LanguageProvider lp) {
		StringBuilder material_name = new StringBuilder();
		for (String word : base.name.split("_")) {
			word = word.substring(0, 1).toUpperCase() + word.substring(1);
			material_name.append(word).append(" ");
		}
		if (BLOCK.shouldGenerate()) {
			lp.add(this.BLOCK.BLOCK_ITEM.get(), material_name + "Style Block");
		}
	}

	@Override
	public void recipes(_MaterialBase base, RecipeOutput consumer) {
		BlacklistedModel standardHammeringModel = new BlacklistedModel(
				ResourceLocation.parse("compendium:gold_hammer_item"), false,
				new AnimationFloatTransform()
						.setRotation(new AnimatedFloatVector3()
								.setZ(new AnimatedFloat(-45.000F, 45.000F, 0.000F, 0.500F, true, true)))
						.setLocation(new AnimatedFloatVector3()
								.setX(new AnimatedFloat(-8.000F, 0.000F, 0.000F, 0.000F, false, false))
								.setY(new AnimatedFloat(-10.000F, 10.000F, 0.000F, 0.000F, false, false))
								.setZ(new AnimatedFloat(-8.000F, 8.000F, 0.000F, 0.000F, false, false)))
						.setScale(new AnimatedFloatVector3()
								.setX(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false, false))
								.setY(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false, false))
								.setZ(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false, false)))
						.setPivot(new AnimatedFloatVector3()
								.setX(new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false))
								.setY(new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false))));

		if (BLOCK.shouldGenerate()) {
			ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, BLOCK.BLOCK_ITEM, 2)
					.define('p', ((MaterialGem) base).SHARD.ITEM).pattern("p p").pattern("   ").pattern("p p")
					.unlockedBy("plank",
							CriteriaTriggers.INVENTORY_CHANGED
									.createCriterion(new InventoryChangeTrigger.TriggerInstance(Optional.empty(),
											InventoryChangeTrigger.TriggerInstance.Slots.ANY,
											List.of(ItemPredicate.Builder.item()
													.of(((MaterialGem) base).GEM.ITEM.asItem()).build()))))
					.save(consumer, TagUtil.modLoc(base.name + "_style_block_from_gem"));

			HammeringRecipeBuilder
					.hammer(Ingredient.of(BLOCK.BLOCK_ITEM), new ItemStack(((MaterialGem) base).SHARD.ITEM.asItem(), 2))
					.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
							standardHammeringModel)
					.save(consumer, TagUtil.modLoc(base.name + "_gem_from_style_block"));
		}
	}

	@Override
	public void blockLoot(_MaterialBase base, BlockLootSubProvider blp) {
		if (!this.BLOCK.isIgnored()) {
			blp.add(BLOCK.BLOCK.get(), BlockLootTables.createStyleItemDrop(BLOCK.BLOCK.get()));
		}
	}

	public static class Serializer extends MaterialExtensionSerializer<ExtensionGemStyleBlocks> {

		public Serializer() {
			super("EXTRAGEMBLOCKS");
		}

		@Override
		public JsonElement serialize(ExtensionGemStyleBlocks src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("type", type);

			j.add("block", src.BLOCK.serialize());

			return j;
		}

		@Override
		public ExtensionGemStyleBlocks deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			ExtensionGemStyleBlocks emsb = new ExtensionGemStyleBlocks();
			if (j.has("block"))
				emsb.BLOCK.deserialize(j.get("block").getAsJsonObject());

			return emsb;
		}

	}

	@Override
	public void otherLoot(_MaterialBase base, LootTableSubProvider lsp) {
		// TODO Auto-generated method stub

	}

}
