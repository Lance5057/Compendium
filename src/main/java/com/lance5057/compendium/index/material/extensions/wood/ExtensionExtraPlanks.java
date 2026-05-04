package com.lance5057.compendium.index.material.extensions.wood;

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
import com.lance5057.compendium.blocks.PipeStyleBlock;
import com.lance5057.compendium.blocks.SimpleStyleBlock;
import com.lance5057.compendium.blocks.SlabStyleBlock;
import com.lance5057.compendium.blocks.StairStyleBlock;
import com.lance5057.compendium.components.block.IndexEntryComponent;
import com.lance5057.compendium.components.block.StyleBlockComponent;
import com.lance5057.compendium.data.Recipes;
import com.lance5057.compendium.data.loottables.BlockLootTables;
import com.lance5057.compendium.data.loottables.RecipeLootTables;
import com.lance5057.compendium.data.recipebuilders.SawBuckRecipeBuilder;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.base.wood.MaterialWood;
import com.lance5057.compendium.index.material.extensions.MaterialExtensionSerializer;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.util.CompendiumBlockHandler;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.util.TagUtil;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ExtensionExtraPlanks extends _MaterialExtension {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3680413722908106206L;
	public final CompendiumBlockHandler PLANK;
	public final CompendiumBlockHandler PLANK_BLOCK;
	public final CompendiumBlockHandler PLANK_SLAB;
	public final CompendiumBlockHandler PLANK_STAIRS;

	public ExtensionExtraPlanks() {
		super();

		this.BLOCKS.add(PLANK = new CompendiumBlockHandler());
		this.BLOCKS.add(PLANK_BLOCK = new CompendiumBlockHandler());
		this.BLOCKS.add(PLANK_SLAB = new CompendiumBlockHandler());
		this.BLOCKS.add(PLANK_STAIRS = new CompendiumBlockHandler());
	}

	@Override
	public void setup(_MaterialBase base) {
		PLANK.setName(base.name + "_plank");
		PLANK.setup(base,
				() -> new PipeStyleBlock(0.125f, Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS),
						Compendium.modLoc(base.name + "_plank_inventory"), base.getType(), base.name, List.of("plank"),
						StyleData.PLANK),
				() -> new BlockItem(PLANK.BLOCK.get(), new Item.Properties().component(CompendiumComponents.STYLE,
						new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))));
		PLANK.setupItemTag(CompendiumTags.PLANK);
		PLANK.setupItemTag(TagUtil.neoTag("plank/" + base.name));
		PLANK.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);
		PLANK.setupBlockTag(CompendiumTags.CREATE_SAFE_NBT);
		PLANK.setAsValidStyleBlock();
		PLANK.setAsValidStyleItem();

		PLANK_BLOCK.setName(base.name + "_styled_planks");
		PLANK_BLOCK
				.setup(base,
						() -> new SimpleStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS),
								Compendium.modLoc(base.name
										+ "_styled_planks"),
								base.getType(), base.name, List.of("plank_block"), StyleData.PLANKS),
						() -> new BlockItem(PLANK_BLOCK.BLOCK.get(),
								new Item.Properties()
										.component(CompendiumComponents.STYLE,
												new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))
										.component(CompendiumComponents.INDEX,
												new IndexEntryComponent(base.getType(), base.name))));
//		PLANK_BLOCK.setupItemTag(ItemTags.PLANKS);
		PLANK_BLOCK.setupItemTag(TagUtil.neoTag("planks/" + base.name));
		PLANK_BLOCK.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);
		PLANK_BLOCK.setupBlockTag(CompendiumTags.CREATE_SAFE_NBT);
		PLANK_BLOCK.setAsValidStyleBlock();
		PLANK_BLOCK.setAsValidStyleItem();

		PLANK_SLAB.setName(base.name + "_styled_planks_slab");
		PLANK_SLAB
				.setup(base,
						() -> new SlabStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_SLAB).noOcclusion(),
								Compendium.modLoc(base.name
										+ "_styled_planks_slab_inventory"),
								base.getType(), base.name, List.of("plank_block"), StyleData.PLANKS),
						() -> new BlockItem(PLANK_SLAB.BLOCK.get(),
								new Item.Properties()
										.component(CompendiumComponents.STYLE,
												new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))
										.component(CompendiumComponents.INDEX,
												new IndexEntryComponent(base.getType(), base.name))));

		PLANK_SLAB.setupItemTag(TagUtil.neoTag("slabs/planks/" + base.name));
		PLANK_SLAB.setupItemTag(TagUtil.neoTag("wooden_slabs/" + base.name));
		PLANK_SLAB.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);
		PLANK_SLAB.setupBlockTag(CompendiumTags.CREATE_SAFE_NBT);
		PLANK_SLAB.setAsValidStyleBlock();
		PLANK_SLAB.setAsValidStyleItem();

		PLANK_STAIRS.setName(base.name + "_styled_planks_stairs");
		PLANK_STAIRS
				.setup(base,
						() -> new StairStyleBlock(PLANK_BLOCK.BLOCK.get().defaultBlockState(),
								Block.Properties.ofFullCopy(Blocks.DARK_OAK_STAIRS),
								Compendium.modLoc(base.name
										+ "_styled_planks_stairs_inventory"),
								base.getType(), base.name, List.of("plank_block"), StyleData.PLANKS),
						() -> new BlockItem(PLANK_STAIRS.BLOCK.get(),
								new Item.Properties()
										.component(CompendiumComponents.STYLE,
												new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))
										.component(CompendiumComponents.INDEX,
												new IndexEntryComponent(base.getType(), base.name))));
		PLANK_STAIRS.setAsValidStyleBlock();
		PLANK_STAIRS.setAsValidStyleItem();

		PLANK_STAIRS.setupItemTag(ItemTags.WOODEN_STAIRS);
		PLANK_STAIRS.setupItemTag(TagUtil.neoTag("stairs/planks/" + base.name));
		PLANK_STAIRS.setupItemTag(TagUtil.neoTag("wooden_stairs/" + base.name));
		PLANK_STAIRS.setupBlockTag(CompendiumTags.CREATE_SAFE_NBT);
		PLANK_STAIRS.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);
	}

	@Override
	public void tab(_MaterialBase base, Output output) {
		PLANK.tab(base, output);
		PLANK_BLOCK.tab(base, output);
		PLANK_SLAB.tab(base, output);
		PLANK_STAIRS.tab(base, output);
	}

	@Override
	public void blockStateModel(_MaterialBase base, BlockStateProvider bsp) {
	}

//	@Override
//	public void itemModel(_MaterialBase base, ItemModelProvider tmp) {
////		if (this.autoGenItemModel) {
//////			DataUtil.basicMaterial3DItem(tmp, PLANK.BLOCK_ITEM.get(), base, Compendium.modLoc("item/plank"),
//////					base.getType(), tmp.mcLoc("block/" + base.name.toLowerCase() + "_planks"));
////			if (PLANK.shouldGenerate()) {
////				tmp.getBuilder(PLANK.BLOCK_ITEM.get().toString())
////						.parent(new ModelFile.UncheckedModelFile(Compendium.modLoc("item/plank")))
////						.texture("0", tmp.modLoc(base.blockFolder() + "planks/plank"));
////			}
////
////			if (PLANK_BLOCK.shouldGenerate())
////				tmp.withExistingParent(PLANK_BLOCK.BLOCK_ITEM.getRegisteredName(), tmp.modLoc("item/window"));
////
////			if (PLANK_SLAB.shouldGenerate())
////				tmp.withExistingParent(PLANK_SLAB.BLOCK_ITEM.getRegisteredName(), tmp.modLoc("item/window"));
////
////			if (PLANK_STAIRS.shouldGenerate())
////				tmp.withExistingParent(PLANK_STAIRS.BLOCK_ITEM.getRegisteredName(), tmp.modLoc("item/window"));
////		}
//	}

	@Override
	public void engLoc(_MaterialBase base, LanguageProvider lp) {
		StringBuilder material_name = new StringBuilder();
		for (String word : base.name.split("_")) {
			word = word.substring(0, 1).toUpperCase() + word.substring(1);
			material_name.append(word).append(" ");
		}
		if (PLANK.shouldGenerate()) {
			lp.add(this.PLANK.BLOCK_ITEM.get(), material_name + "Plank");
		}
		if (PLANK_BLOCK.shouldGenerate()) {
			lp.add(this.PLANK_BLOCK.BLOCK_ITEM.get(), material_name + "Styled Planks");
		}
		if (PLANK_SLAB.shouldGenerate()) {
			lp.add(this.PLANK_SLAB.BLOCK_ITEM.get(), material_name + "Styled Plank Slab");
		}
		if (PLANK_STAIRS.shouldGenerate()) {
			lp.add(this.PLANK_STAIRS.BLOCK_ITEM.get(), material_name + "Styled Plank Stairs");
		}
	}

	@Override
	public void recipes(_MaterialBase base, RecipeOutput consumer) {
		if (!this.PLANK.isIgnored()) {
			SawBuckRecipeBuilder
					.saw(Ingredient.of(TagKey.create(Registries.ITEM, TagUtil.neoTag("logs/small/" + base.name))),
							new ItemStack(PLANK.BLOCK_ITEM.get(), 2), Vec3.ZERO)
					.tool(Ingredient.of(CompendiumTags.SAW), 1, true, RecipeLootTables.SAW_DUST, List.of(),
							Recipes.standardSawBuckSawModel(TagUtil.modLoc("iron_saw_item"), 0),
							Recipes.standardSawBuckBlockModel(
									TagUtil.modLoc("recipes/" + base.name + "_stripped_split_log_stage3"), 0))
					.save(consumer, base.name + "_plank_from_small");

			SawBuckRecipeBuilder
					.saw(Ingredient
							.of(TagKey.create(Registries.ITEM, TagUtil.neoTag("stripped_logs/small/" + base.name))),
							new ItemStack(PLANK.BLOCK_ITEM.get(), 2), Vec3.ZERO)
					.tool(Ingredient.of(CompendiumTags.SAW), 1, true, RecipeLootTables.SAW_DUST, List.of(),
							Recipes.standardSawBuckSawModel(TagUtil.modLoc("iron_saw_item"), 0),
							Recipes.standardSawBuckBlockModel(
									TagUtil.modLoc("recipes/" + base.name + "_stripped_split_log_stage3"), 0))
					.save(consumer, base.name + "_plank_from_stripped");

			ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ((MaterialWood) base).PLANKS.BLOCK_ITEM, 2)
					.define('p', PLANK.BLOCK_ITEM).pattern("pp").pattern("pp")
					.unlockedBy("plank", CriteriaTriggers.INVENTORY_CHANGED
							.createCriterion(new InventoryChangeTrigger.TriggerInstance(Optional.empty(),
									InventoryChangeTrigger.TriggerInstance.Slots.ANY,
									List.of(ItemPredicate.Builder.item().of(PLANK.BLOCK_ITEM.asItem()).build()))))
					.save(consumer, TagUtil.modLoc(base.name + "_planks"));

			if (!this.PLANK_BLOCK.isIgnored()) {
				ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, PLANK_BLOCK.BLOCK_ITEM, 2)
						.define('p', PLANK.BLOCK_ITEM).pattern("p p").pattern("   ").pattern("p p")
						.unlockedBy("plank", CriteriaTriggers.INVENTORY_CHANGED
								.createCriterion(new InventoryChangeTrigger.TriggerInstance(Optional.empty(),
										InventoryChangeTrigger.TriggerInstance.Slots.ANY,
										List.of(ItemPredicate.Builder.item().of(PLANK.BLOCK_ITEM.asItem()).build()))))
						.save(consumer);

				if (!this.PLANK.isIgnored()) {
					ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, PLANK.BLOCK_ITEM, 4)
							.define('p', ItemTags.create(TagUtil.neoTag("planks/" + base.name))).pattern("p ")
							.pattern(" p")
							.unlockedBy("plank",
									CriteriaTriggers.INVENTORY_CHANGED.createCriterion(
											new InventoryChangeTrigger.TriggerInstance(Optional.empty(),
													InventoryChangeTrigger.TriggerInstance.Slots.ANY,
													List.of(ItemPredicate.Builder.item().of(PLANK.BLOCK_ITEM.asItem())
															.build()))))
							.save(consumer, TagUtil.modLoc(base.name + "_planks_to_planks"));
				}
			}

			if (!this.PLANK_SLAB.isIgnored()) {
				ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, PLANK_SLAB.BLOCK_ITEM, 6)
						.define('p', PLANK.BLOCK_ITEM).pattern("ppp").pattern("ppp")
						.unlockedBy("plank", CriteriaTriggers.INVENTORY_CHANGED
								.createCriterion(new InventoryChangeTrigger.TriggerInstance(Optional.empty(),
										InventoryChangeTrigger.TriggerInstance.Slots.ANY,
										List.of(ItemPredicate.Builder.item().of(PLANK.BLOCK_ITEM.asItem()).build()))))
						.save(consumer);

				if (!this.PLANK.isIgnored()) {

					ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, PLANK.BLOCK_ITEM, 1)
							.requires(ItemTags.create(TagUtil.neoTag("slabs/wooden/" + base.name)))
							.unlockedBy("plank_slab",
									CriteriaTriggers.INVENTORY_CHANGED.createCriterion(
											new InventoryChangeTrigger.TriggerInstance(Optional.empty(),
													InventoryChangeTrigger.TriggerInstance.Slots.ANY,
													List.of(ItemPredicate.Builder.item()
															.of(PLANK_SLAB.BLOCK_ITEM.asItem()).build()))))
							.save(consumer, TagUtil.modLoc(base.name + "_slab_to_planks"));
				}
			}

			if (!this.PLANK_STAIRS.isIgnored()) {
				ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, PLANK_STAIRS.BLOCK_ITEM, 6)
						.define('p', PLANK.BLOCK_ITEM).pattern("p  ").pattern("pp ").pattern("ppp")
						.unlockedBy("plank", CriteriaTriggers.INVENTORY_CHANGED
								.createCriterion(new InventoryChangeTrigger.TriggerInstance(Optional.empty(),
										InventoryChangeTrigger.TriggerInstance.Slots.ANY,
										List.of(ItemPredicate.Builder.item().of(PLANK.BLOCK_ITEM.asItem()).build()))))
						.save(consumer);

				if (!this.PLANK.isIgnored()) {
					ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, PLANK.BLOCK_ITEM, 1)
							.requires(ItemTags.create(TagUtil.neoTag("stairs/wooden/" + base.name)))
							.unlockedBy("plank_stairs",
									CriteriaTriggers.INVENTORY_CHANGED.createCriterion(
											new InventoryChangeTrigger.TriggerInstance(Optional.empty(),
													InventoryChangeTrigger.TriggerInstance.Slots.ANY,
													List.of(ItemPredicate.Builder.item()
															.of(PLANK_STAIRS.BLOCK_ITEM.asItem()).build()))))
							.save(consumer, TagUtil.modLoc(base.name + "_stairs_to_planks"));
				}
			}
		}
	}

	@Override
	public void blockLoot(_MaterialBase base, BlockLootSubProvider blp) {
		if (!this.PLANK.isIgnored()) {
			blp.add(PLANK.BLOCK.get(), BlockLootTables.createStyleItemDrop(PLANK.BLOCK.get()));
		}
		if (!this.PLANK_BLOCK.isIgnored()) {
			blp.add(PLANK_BLOCK.BLOCK.get(), BlockLootTables.createStyleItemDrop(PLANK_BLOCK.BLOCK.get()));
		}
		if (!this.PLANK_SLAB.isIgnored()) {
			blp.add(PLANK_SLAB.BLOCK.get(), this.createSlabItemTable(PLANK_SLAB.BLOCK.get()));
		}
		if (!this.PLANK_STAIRS.isIgnored()) {
			blp.add(PLANK_STAIRS.BLOCK.get(), BlockLootTables.createStyleItemDrop(PLANK_STAIRS.BLOCK.get()));
		}
	}

	protected LootTable.Builder createSlabItemTable(Block block) {
		return LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
						.add(LootItem.lootTableItem(block)
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
										.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
												.setProperties(StatePropertiesPredicate.Builder.properties()
														.hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))
								.apply(CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY)
										.include(CompendiumComponents.STYLE.get()))

						));
	}

//	@Override
//	public void setupItemTags(_MaterialBase base, ItemTagsProvider itp) {
//		if (!PLANK.isIgnored()) {
//			PLANK.itemTag(itp);
//			itp.tag(CompendiumTags.PLANK).add(PLANK.BLOCK_ITEM.asItem());
//		}
//		if (!PLANK_BLOCK.isIgnored()) {
//			PLANK_BLOCK.itemTag(itp);
//		}
//		if (!PLANK_SLAB.isIgnored()) {
//			PLANK_SLAB.itemTag(itp);
//		}
//		if (!PLANK_STAIRS.isIgnored()) {
//			PLANK_STAIRS.itemTag(itp);
//		}
//	}
//
//	@Override
//	public void setupBlockTags(_MaterialBase base, BlockTagsProvider btp) {
//		if (!PLANK.isIgnored()) {
//			PLANK.blockTag(btp);
//		}
//		if (!PLANK_BLOCK.isIgnored()) {
//			PLANK_BLOCK.blockTag(btp);
//		}
//		if (!PLANK_SLAB.isIgnored()) {
//			PLANK_SLAB.blockTag(btp);
//		}
//		if (!PLANK_STAIRS.isIgnored()) {
//			PLANK_STAIRS.blockTag(btp);
//		}
//	}
//
//	@Override
//	public void setupClient(_MaterialBase base, FMLClientSetupEvent event) {
//
//	}

	public static class Serializer extends MaterialExtensionSerializer<ExtensionExtraPlanks> {

		public Serializer() {
			super("EXTRAPLANKS");
		}

		@Override
		public JsonElement serialize(ExtensionExtraPlanks src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("type", type);

			j.add("plank", src.PLANK.serialize());
			j.add("plank_block", src.PLANK_BLOCK.serialize());
			j.add("plank_slab", src.PLANK_SLAB.serialize());
			j.add("plank_stairs", src.PLANK_STAIRS.serialize());

			return j;
		}

		@Override
		public ExtensionExtraPlanks deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			ExtensionExtraPlanks eel = new ExtensionExtraPlanks();
			eel.PLANK.deserialize(j.get("plank").getAsJsonObject());
			eel.PLANK_BLOCK.deserialize(j.get("plank_block").getAsJsonObject());
			eel.PLANK_SLAB.deserialize(j.get("plank_slab").getAsJsonObject());
			eel.PLANK_STAIRS.deserialize(j.get("plank_stairs").getAsJsonObject());

			return eel;
		}

	}

	@Override
	public void otherLoot(_MaterialBase base, LootTableSubProvider lsp) {
		// TODO Auto-generated method stub

	}

//	@Override
//	public boolean isIndexItem(_MaterialBase base, ItemStack stack) {
//		if (PLANK.is(stack))
//			return true;
//		if (PLANK_BLOCK.is(stack))
//			return true;
//		if (PLANK_SLAB.is(stack))
//			return true;
//		if (PLANK_STAIRS.is(stack))
//			return true;
//
//		return false;
//	}
//
//	@Override
//	public Optional<IIndexEntry> getEntryItemBelongsTo(_MaterialBase base, ItemStack stack) {
//		if (PLANK.is(stack))
//			return Optional.of(base);
//		if (PLANK_BLOCK.is(stack))
//			return Optional.of(base);
//		if (PLANK_SLAB.is(stack))
//			return Optional.of(base);
//		if (PLANK_STAIRS.is(stack))
//			return Optional.of(base);
//
//		return Optional.empty();
//	}

//	@Override
//	public void attachComponents(_MaterialBase base, ModifyDefaultComponentsEvent event) {
//		if (PLANK.isNotIgnored())
//			event.modify(PLANK.BLOCK_ITEM.get(), builder -> builder.set(CompendiumComponents.INDEX.get(),
//					new IndexEntryComponent(base.getType(), base.name)));
//
//		if (PLANK_BLOCK.isNotIgnored())
//			event.modify(PLANK_BLOCK.BLOCK_ITEM.get(), builder -> builder.set(CompendiumComponents.INDEX.get(),
//					new IndexEntryComponent(base.getType(), base.name)));
//
//		if (PLANK_SLAB.isNotIgnored())
//			event.modify(PLANK_SLAB.BLOCK_ITEM.get(), builder -> builder.set(CompendiumComponents.INDEX.get(),
//					new IndexEntryComponent(base.getType(), base.name)));
//
//		if (PLANK_STAIRS.isNotIgnored())
//			event.modify(PLANK_STAIRS.BLOCK_ITEM.get(), builder -> builder.set(CompendiumComponents.INDEX.get(),
//					new IndexEntryComponent(base.getType(), base.name)));
//	}
}
