//package com.lance5057.compendium.workstations.sawbuck;
//
//import com.google.gson.JsonObject;
//import com.lance5057.compendium.util.recipes.WorkstationRecipeWrapper;
//import com.lance5057.compendium.workstations.WorkstationRecipes;
//import com.lance5057.compendium.workstations._bases.interfaces.item.io.single.WorkstationSingleItemOut;
//import com.lance5057.compendium.workstations._bases.interfaces.loottable.io.WorkstationLootTableOut;
//import com.lance5057.compendium.workstations._bases.recipes.AnimatedRecipeItemUse;
//import com.lance5057.compendium.workstations._bases.recipes.multitoolrecipe.MultiToolRecipe;
//import com.lance5057.compendium.workstations._bases.recipes.multitoolrecipe.interfaces.in.items.ISingleItemIn;
//
//import lance5057.compendium.core.workstations._bases.recipes.multitoolrecipe.interfaces.out.items.ILoottableOut;
//import lance5057.compendium.core.workstations._bases.recipes.multitoolrecipe.interfaces.out.items.ISingleItemOut;
//import net.minecraft.core.NonNullList;
//import net.minecraft.network.FriendlyByteBuf;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.util.GsonHelper;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.crafting.Ingredient;
//import net.minecraft.world.item.crafting.RecipeSerializer;
//import net.minecraft.world.item.crafting.ShapedRecipe;
//import net.minecraft.world.level.Level;
//
//public class SawBuckRecipe extends MultiToolRecipe implements ISingleItemIn, WorkstationSingleItemOut, WorkstationLootTableOut {
//
//	private final Ingredient input;
//	private final ResourceLocation loot;
//	private final ItemStack output;
//
//	public SawBuckRecipe(ResourceLocation idIn, String groupIn, Ingredient recipeItemsIn, ItemStack output,
//			NonNullList<AnimatedRecipeItemUse> recipeToolsIn, ResourceLocation loottable) {
//		super(idIn, groupIn, recipeToolsIn, WorkstationRecipes.SAWBUCK_RECIPE.get());
//
//		this.input = recipeItemsIn;
//		this.loot = loottable;
//		this.output = output;
//	}
//
//	public static class Serializer implements RecipeSerializer<SawBuckRecipe> {
//
//		public SawBuckRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
//			String s = GsonHelper.getAsString(json, "group", "");
//			Ingredient itemIn = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "input"));
//
//			NonNullList<AnimatedRecipeItemUse> nonnulllistTools = SawBuckRecipe
//					.deserializeTool(GsonHelper.getAsJsonObject(json, "tools"));
//
//			ItemStack itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
//			ResourceLocation output = new ResourceLocation(json.get("outputTable").getAsString());
//
//			return new SawBuckRecipe(recipeId, s, itemIn, itemstack, nonnulllistTools, output);
//		}
//
//		public SawBuckRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
//			String group = buffer.readUtf(32767);
//
//			Ingredient ing = Ingredient.fromNetwork(buffer);
//
//			int h = buffer.readVarInt();
//
//			NonNullList<AnimatedRecipeItemUse> tools = NonNullList.withSize(h, AnimatedRecipeItemUse.EMPTY);
//
//			for (int k = 0; k < tools.size(); ++k) {
//				tools.set(k, AnimatedRecipeItemUse.read(buffer));
//			}
//
//			String q = buffer.readUtf();
//
//			ItemStack output = buffer.readItem();
//			final ResourceLocation outputTable = new ResourceLocation(q);
//
//			return new SawBuckRecipe(recipeId, group, ing, output, tools, outputTable);
//		}
//
//		public void toNetwork(FriendlyByteBuf buffer, SawBuckRecipe recipe) {
//			buffer.writeUtf(recipe.getGroup());
//
//			recipe.getItemIn().toNetwork(buffer);
//			buffer.writeVarInt(recipe.getToolListLength());
//
//			for (AnimatedRecipeItemUse riu : recipe.getRecipeTools())
//				AnimatedRecipeItemUse.write(riu, buffer);
//
//			buffer.writeItem(recipe.getItemOut());
//			buffer.writeResourceLocation(recipe.getLootTableOut());
//		}
//	}
//
//	@Override
//	public boolean matches(WorkstationRecipeWrapper pContainer, Level pLevel) {
//		return this.input.test(pContainer.getItem(0));
//	}
//
//	@Override
//	public RecipeSerializer<?> getSerializer() {
//		return null;
//	}
//
//	@Override
//	public ResourceLocation getLootTableOut() {
//		// TODO Auto-generated method stub
//		return this.loot;
//	}
//
//	@Override
//	public ItemStack getItemOut() {
//		// TODO Auto-generated method stub
//		return this.output;
//	}
//
//	@Override
//	public Ingredient getItemIn() {
//		// TODO Auto-generated method stub
//		return this.input;
//	}
//
//	@Override
//	public ItemStack getResultItem() {
//		// TODO Auto-generated method stub
//		return this.getItemOut();
//	}
//}
