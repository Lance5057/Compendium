package com.lance5057.compendium.workstations.scrappingtable;

import com.lance5057.compendium.recipes.interfaces.item.io.single.IRecipeSingleItemIn;
import com.lance5057.compendium.recipes.interfaces.item.io.single.IRecipeSingleItemOut;
import com.lance5057.compendium.recipes.interfaces.loottable.io.IRecipeLootTableOut;
import com.lance5057.compendium.workstations.WorkstationRecipes;
import com.lance5057.compendium.workstations._bases.recipes.AnimatedRecipeItemUse;
import com.lance5057.compendium.workstations._bases.recipes.multitoolrecipe.MultiToolRecipe;
import com.lance5057.compendium.workstations.containers.MultiToolRecipeWrapper;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class ScrappingTableRecipe extends MultiToolRecipe
		implements IRecipeSingleItemIn, IRecipeSingleItemOut, IRecipeLootTableOut {

	private final Ingredient input;
	private final ResourceLocation loot;
	private final ItemStack output;

	public ScrappingTableRecipe(Ingredient recipeItemsIn, ItemStack output,
			NonNullList<AnimatedRecipeItemUse> recipeToolsIn, ResourceLocation loottable) {
		super(recipeToolsIn);
		this.input = recipeItemsIn;
		this.loot = loottable;
		this.output = output;
	}

	@Override
	public boolean matches(MultiToolRecipeWrapper input, Level level) {
		return this.input.test(input.getItem(0));
	}

	@Override
	public ItemStack assemble(MultiToolRecipeWrapper input, Provider registries) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getResultItem(Provider registries) {
		return this.output;
	}

	@Override
	public RecipeType<?> getType() {
		return WorkstationRecipes.SCRAPPINGTABLE_RECIPE.get();
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return WorkstationRecipes.SCRAPPINGTABLE_SERIALIZER.get();
	}

	@Override
	public ResourceLocation getLootTableOut() {
		return this.loot;
	}

	@Override
	public ItemStack getItemOut() {
		return this.output;
	}

	@Override
	public Ingredient getItemIn() {
		return this.input;
	}

	public static class Serializer implements RecipeSerializer<ScrappingTableRecipe> {
		public static final MapCodec<ScrappingTableRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst
				.group(Ingredient.CODEC_NONEMPTY.fieldOf("input").forGetter(ScrappingTableRecipe::getItemIn),
						ItemStack.CODEC.fieldOf("ouput").forGetter(ScrappingTableRecipe::getItemOut),
						NonNullList.codecOf(AnimatedRecipeItemUse.CODEC).fieldOf("tools")
								.forGetter(ScrappingTableRecipe::getTools),
						ResourceLocation.CODEC.fieldOf("loot").forGetter(ScrappingTableRecipe::getLootTableOut))
				.apply(inst, ScrappingTableRecipe::new));

		public static final StreamCodec<RegistryFriendlyByteBuf, ScrappingTableRecipe> STREAM_CODEC = StreamCodec
				.of(Serializer::write, Serializer::read);

		@Override
		public MapCodec<ScrappingTableRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, ScrappingTableRecipe> streamCodec() {
			return STREAM_CODEC;
		}

		private static ScrappingTableRecipe read(RegistryFriendlyByteBuf buffer) {
			String group = buffer.readUtf();
			Ingredient in = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
			ItemStack out = ItemStack.STREAM_CODEC.decode(buffer);
			int listSize = buffer.readVarInt();

			NonNullList<AnimatedRecipeItemUse> tools = NonNullList.withSize(listSize, AnimatedRecipeItemUse.EMPTY);
			tools.replaceAll(ignored -> AnimatedRecipeItemUse.STREAM_CODEC.decode(buffer));

			ResourceLocation r = ResourceLocation.STREAM_CODEC.decode(buffer);

			return new ScrappingTableRecipe(in, out, tools, r);
		}

		private static void write(RegistryFriendlyByteBuf buffer, ScrappingTableRecipe recipe) {
			buffer.writeUtf(recipe.getGroup());

			Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.input);

			ItemStack.STREAM_CODEC.encode(buffer, recipe.output);

			buffer.writeVarInt(recipe.getTools().size());
			recipe.getTools().forEach(riu -> AnimatedRecipeItemUse.STREAM_CODEC.encode(buffer, riu));

			ResourceLocation.STREAM_CODEC.encode(buffer, recipe.loot);
		}
	}
}
