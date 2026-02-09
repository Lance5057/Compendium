package com.lance5057.compendium.workstations.sawbuck;

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
import net.minecraft.world.phys.Vec3;

public class SawBuckRecipe extends MultiToolRecipe
		implements IRecipeSingleItemIn, IRecipeSingleItemOut, IRecipeLootTableOut {

	private final Ingredient input;
	private final ResourceLocation loot;
	private final ItemStack output;
	private final Vec3 offset;

	public SawBuckRecipe(Ingredient recipeItemsIn, ItemStack output, NonNullList<AnimatedRecipeItemUse> recipeToolsIn,
			ResourceLocation loottable, Vec3 offset) {
		super(recipeToolsIn);
		this.input = recipeItemsIn;
		this.loot = loottable;
		this.output = output;
		this.offset = offset;
	}

	@Override
	public boolean matches(MultiToolRecipeWrapper input, Level level) {
		return this.input.test(input.getItem(0));
	}

	@Override
	public ItemStack assemble(MultiToolRecipeWrapper input, Provider registries) {
		return getItemOut();
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
		return WorkstationRecipes.SAWBUCK_RECIPE.get();
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return WorkstationRecipes.SAWBUCK_SERIALIZER.get();
	}

	@Override
	public ResourceLocation getLootTableOut() {
		return this.loot;
	}

	@Override
	public ItemStack getItemOut() {
		return this.output.copy();
	}

	@Override
	public Ingredient getItemIn() {
		return this.input;
	}

	public Vec3 getOffset() {
		return offset;
	}

	public static class Serializer implements RecipeSerializer<SawBuckRecipe> {
		public static final MapCodec<SawBuckRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst
				.group(Ingredient.CODEC_NONEMPTY.fieldOf("input").forGetter(SawBuckRecipe::getItemIn),
						ItemStack.CODEC.fieldOf("ouput").forGetter(SawBuckRecipe::getItemOut),
						NonNullList.codecOf(AnimatedRecipeItemUse.CODEC).fieldOf("tools")
								.forGetter(SawBuckRecipe::getTools),
						ResourceLocation.CODEC.fieldOf("loot").forGetter(SawBuckRecipe::getLootTableOut),
						Vec3.CODEC.fieldOf("offset").forGetter(SawBuckRecipe::getOffset))
				.apply(inst, SawBuckRecipe::new));

		public static final StreamCodec<RegistryFriendlyByteBuf, SawBuckRecipe> STREAM_CODEC = StreamCodec
				.of(Serializer::write, Serializer::read);

		@Override
		public MapCodec<SawBuckRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, SawBuckRecipe> streamCodec() {
			return STREAM_CODEC;
		}

		private static SawBuckRecipe read(RegistryFriendlyByteBuf buffer) {
			String group = buffer.readUtf();
			Ingredient in = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
			ItemStack out = ItemStack.STREAM_CODEC.decode(buffer);
			int listSize = buffer.readVarInt();

			NonNullList<AnimatedRecipeItemUse> tools = NonNullList.withSize(listSize, AnimatedRecipeItemUse.EMPTY);
			tools.replaceAll(ignored -> AnimatedRecipeItemUse.STREAM_CODEC.decode(buffer));

			ResourceLocation r = ResourceLocation.STREAM_CODEC.decode(buffer);

			Double x = buffer.readDouble();
			Double y = buffer.readDouble();
			Double z = buffer.readDouble();

			return new SawBuckRecipe(in, out, tools, r, new Vec3(x, y, z));
		}

		private static void write(RegistryFriendlyByteBuf buffer, SawBuckRecipe recipe) {
			buffer.writeUtf(recipe.getGroup());

			Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.input);

			ItemStack.STREAM_CODEC.encode(buffer, recipe.output);

			buffer.writeVarInt(recipe.getTools().size());
			recipe.getTools().forEach(riu -> AnimatedRecipeItemUse.STREAM_CODEC.encode(buffer, riu));

			ResourceLocation.STREAM_CODEC.encode(buffer, recipe.loot);

			Vec3 v = recipe.getOffset();
			buffer.writeDouble(v.x);
			buffer.writeDouble(v.y);
			buffer.writeDouble(v.z);
		}
	}
}
