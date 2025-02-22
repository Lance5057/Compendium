package com.lance5057.compendium.workstations.workbench;

import com.lance5057.compendium.recipes.interfaces.item.io.multiple.IRecipeShapedItemIn;
import com.lance5057.compendium.recipes.interfaces.item.io.single.IRecipeSingleItemOut;
import com.lance5057.compendium.recipes.interfaces.loottable.io.IRecipeLootTableOut;
import com.lance5057.compendium.workstations.WorkstationRecipes;
import com.lance5057.compendium.workstations._bases.recipes.AnimatedRecipeItemUse;
import com.lance5057.compendium.workstations._bases.recipes.multitoolrecipe.MultiToolRecipeShaped;
import com.lance5057.compendium.workstations._bases.recipes.multitoolrecipe.MultiToolRecipeShapedPattern;
import com.lance5057.compendium.workstations.containers.MultiToolRecipeWrapper;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class WorkbenchRecipe extends MultiToolRecipeShaped
		implements IRecipeShapedItemIn, IRecipeSingleItemOut, IRecipeLootTableOut {

	public WorkbenchRecipe(MultiToolRecipeShapedPattern input, NonNullList<AnimatedRecipeItemUse> recipeToolsIn,
			ItemStack recipeOutputIn) {
		super(input, recipeToolsIn, recipeOutputIn, WorkstationRecipes.WORKBENCH_RECIPE.get());

	}

	@Override
	public ItemStack assemble(MultiToolRecipeWrapper input, Provider registries) {
		// TODO Auto-generated method stub
		return this.getResultItem(registries);
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width <= 5 && height <= 5;
	}

	@Override
	public ResourceLocation getLootTableOut() {
		return null;
	}

	@Override
	public ItemStack getItemOut() {
		return this.recipeOutput;
	}

	@Override
	public void setShapedIn(MultiToolRecipeShapedPattern p) {
		this.pattern = p;
	}

	@Override
	public MultiToolRecipeShapedPattern getShapedIn() {
		return this.pattern;
	}

	@Override
	public RecipeType<?> getType() {
		return WorkstationRecipes.WORKBENCH_RECIPE.get();
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return WorkstationRecipes.WORKBENCH_SERIALIZER.get();
	}

	public static class Serializer implements RecipeSerializer<WorkbenchRecipe> {
		public static final MapCodec<WorkbenchRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst
				.group(MultiToolRecipeShapedPattern.MAP_CODEC.fieldOf("input").forGetter(WorkbenchRecipe::getShapedIn),
						NonNullList.codecOf(AnimatedRecipeItemUse.CODEC).fieldOf("tools")
								.forGetter(WorkbenchRecipe::getTools),
						ItemStack.CODEC.fieldOf("ouput").forGetter(WorkbenchRecipe::getItemOut))
				.apply(inst, WorkbenchRecipe::new));

		public static final StreamCodec<RegistryFriendlyByteBuf, WorkbenchRecipe> STREAM_CODEC = StreamCodec
				.of(Serializer::write, Serializer::read);

		@Override
		public MapCodec<WorkbenchRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, WorkbenchRecipe> streamCodec() {
			return STREAM_CODEC;
		}

		private static WorkbenchRecipe read(RegistryFriendlyByteBuf buffer) {
			MultiToolRecipeShapedPattern p = MultiToolRecipeShapedPattern.STREAM_CODEC.decode(buffer);

			int listSize = buffer.readVarInt();

			NonNullList<AnimatedRecipeItemUse> tools = NonNullList.withSize(listSize, AnimatedRecipeItemUse.EMPTY);
			tools.replaceAll(ignored -> AnimatedRecipeItemUse.STREAM_CODEC.decode(buffer));

			ItemStack out = ItemStack.STREAM_CODEC.decode(buffer);

			return new WorkbenchRecipe(p, tools, out);
		}

		private static void write(RegistryFriendlyByteBuf buffer, WorkbenchRecipe recipe) {

			MultiToolRecipeShapedPattern.STREAM_CODEC.encode(buffer, recipe.pattern);

			buffer.writeVarInt(recipe.getTools().size());
			recipe.getTools().forEach(riu -> AnimatedRecipeItemUse.STREAM_CODEC.encode(buffer, riu));

			ItemStack.STREAM_CODEC.encode(buffer, recipe.getItemOut());
		}
	}

}