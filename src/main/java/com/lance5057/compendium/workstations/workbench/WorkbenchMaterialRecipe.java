package com.lance5057.compendium.workstations.workbench;

import java.util.List;
import java.util.Optional;

import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.components.block.MultiMaterialBlockComponent;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.IIndexEntry;
import com.lance5057.compendium.multimaterial.MultiMaterialType;
import com.lance5057.compendium.util.SlotToMaterial;
import com.lance5057.compendium.workstations.WorkstationRecipes;
import com.lance5057.compendium.workstations._bases.recipes.AnimatedRecipeItemUse;
import com.lance5057.compendium.workstations._bases.recipes.multitoolrecipe.MultiToolRecipeShapedPattern;
import com.lance5057.compendium.workstations.containers.MultiToolRecipeWrapper;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class WorkbenchMaterialRecipe extends WorkbenchRecipe {

	NonNullList<SlotToMaterial> matSlots;

	public NonNullList<SlotToMaterial> getMatSlots() {
		return matSlots;
	}

	public WorkbenchMaterialRecipe(MultiToolRecipeShapedPattern input, NonNullList<SlotToMaterial> matSlots,
			NonNullList<AnimatedRecipeItemUse> recipeToolsIn, ItemStack recipeOutputIn) {
		super(input, recipeToolsIn, recipeOutputIn, WorkstationRecipes.WORKBENCH_MATERIAL_RECIPE.get());
		this.matSlots = matSlots;
	}

	@Override
	public ItemStack assemble(MultiToolRecipeWrapper input, Provider registries) {
		ItemStack s = this.getResultItem(registries);

		if (s.has(CompendiumComponents.MULTI_MATERIAL)) {
			MultiMaterialBlockComponent mmbc = s.get(CompendiumComponents.MULTI_MATERIAL);

			List<MultiMaterialType> mats = mmbc.types();

			for (SlotToMaterial sm : matSlots) {
				ItemStack i = input.getItem(sm.getSlot());

				if (CompendiumIndex.isIndexItem(i)) {
					Optional<IIndexEntry> o = CompendiumIndex.getEntryItemBelongsTo(i);

					if (mats.size() > sm.getMaterialLayer()) {
						String m = o.get().getName();

						MultiMaterialType mmt = mats.get(sm.getMaterialLayer());
						mmt.setCurrentMaterial(m);
						mats.set(sm.getMaterialLayer(), mmt);
					}
				}
			}

			s.set(CompendiumComponents.MULTI_MATERIAL, new MultiMaterialBlockComponent(mats));
		}

		return s;
	}
	
	public static class Serializer implements RecipeSerializer<WorkbenchMaterialRecipe> {
		public static final MapCodec<WorkbenchMaterialRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst
				.group(MultiToolRecipeShapedPattern.MAP_CODEC.fieldOf("input").forGetter(WorkbenchMaterialRecipe::getShapedIn),
						NonNullList.codecOf(SlotToMaterial.CODEC).fieldOf("mats").forGetter(WorkbenchMaterialRecipe::getMatSlots),
						NonNullList.codecOf(AnimatedRecipeItemUse.CODEC).fieldOf("tools")
								.forGetter(WorkbenchRecipe::getTools),
						ItemStack.CODEC.fieldOf("ouput").forGetter(WorkbenchRecipe::getItemOut))
				.apply(inst, WorkbenchMaterialRecipe::new));

		public static final StreamCodec<RegistryFriendlyByteBuf, WorkbenchMaterialRecipe> STREAM_CODEC = StreamCodec
				.of(Serializer::write, Serializer::read);

		@Override
		public MapCodec<WorkbenchMaterialRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, WorkbenchMaterialRecipe> streamCodec() {
			return STREAM_CODEC;
		}

		private static WorkbenchMaterialRecipe read(RegistryFriendlyByteBuf buffer) {
			MultiToolRecipeShapedPattern p = MultiToolRecipeShapedPattern.STREAM_CODEC.decode(buffer);

			int listSize = buffer.readVarInt();

			NonNullList<AnimatedRecipeItemUse> tools = NonNullList.withSize(listSize, AnimatedRecipeItemUse.EMPTY);
			tools.replaceAll(ignored -> AnimatedRecipeItemUse.STREAM_CODEC.decode(buffer));
			
			int listSize2 = buffer.readVarInt();

			NonNullList<SlotToMaterial> mats = NonNullList.withSize(listSize2, new SlotToMaterial(0,0));
			mats.replaceAll(ignored -> SlotToMaterial.STREAM_CODEC.decode(buffer));

			ItemStack out = ItemStack.STREAM_CODEC.decode(buffer);

			return new WorkbenchMaterialRecipe(p, mats, tools, out);
		}

		private static void write(RegistryFriendlyByteBuf buffer, WorkbenchMaterialRecipe recipe) {

			MultiToolRecipeShapedPattern.STREAM_CODEC.encode(buffer, recipe.pattern);

			buffer.writeVarInt(recipe.getTools().size());
			recipe.getTools().forEach(riu -> AnimatedRecipeItemUse.STREAM_CODEC.encode(buffer, riu));
			
			buffer.writeVarInt(recipe.getMatSlots().size());
			recipe.getMatSlots().forEach(riu -> SlotToMaterial.STREAM_CODEC.encode(buffer, riu));

			ItemStack.STREAM_CODEC.encode(buffer, recipe.getItemOut());
		}
	}

}
