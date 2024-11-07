//package com.lance5057.compendium.workstations._bases.recipes.multitoolrecipe;
//
//import java.util.Map.Entry;
//
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.lance5057.compendium.util.recipes.WorkstationRecipeWrapper;
//import com.lance5057.compendium.workstations._bases.recipes.AnimatedRecipeItemUse;
//
//import net.minecraft.core.NonNullList;
//import net.minecraft.core.RegistryAccess;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.crafting.Recipe;
//import net.minecraft.world.item.crafting.RecipeType;
//
//public abstract class MultiToolRecipe implements Recipe<WorkstationRecipeWrapper> {
//
//	private final RecipeType<?> type;
//	protected final NonNullList<AnimatedRecipeItemUse> recipeTools;
//	private final String group;
//
//	public MultiToolRecipe(String groupIn, NonNullList<AnimatedRecipeItemUse> recipeToolsIn,
//			RecipeType<?> type) {
//		this.recipeTools = recipeToolsIn;
//		this.type = type;
//		this.group = groupIn;
//	}
//
//	public NonNullList<AnimatedRecipeItemUse> getToolList() {
//		return this.getRecipeTools();
//	}
//
//	public int getToolListLength() {
//		return recipeTools.size();
//	}
//
//	public NonNullList<AnimatedRecipeItemUse> getRecipeTools() {
//		return recipeTools;
//	}
//
//	@Override
//	public RecipeType<?> getType() {
//		return type;
//	}
//
//	public String getGroup() {
//		return this.group;
//	}
//
//	@Override
//	public ItemStack assemble(WorkstationRecipeWrapper p_44001_, RegistryAccess p_267165_) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	
//	@Override
//	public int getRecipeWidth() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int getRecipeHeight() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	public static NonNullList<AnimatedRecipeItemUse> deserializeTool(JsonObject json) {
//		NonNullList<AnimatedRecipeItemUse> map = NonNullList.create();
//
//		for (Entry<String, JsonElement> entry : json.entrySet()) {
//
//			AnimatedRecipeItemUse r = AnimatedRecipeItemUse.read(entry.getValue().getAsJsonObject());
//
//			map.add(r);
//		}
//
//		// map.put(" ", AnimatedRecipeItemUse.EMPTY);
//		return map;
//	}
//
//}
