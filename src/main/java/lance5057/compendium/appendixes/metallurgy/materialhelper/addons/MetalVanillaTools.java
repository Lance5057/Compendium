package lance5057.compendium.appendixes.metallurgy.materialhelper.addons;

import java.util.function.Consumer;

import lance5057.compendium.Reference;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.core.data.builders.TCBlockModels;
import lance5057.compendium.core.data.builders.TCEnglishLoc;
import lance5057.compendium.core.data.builders.TCItemModels;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.data.builders.TCRecipes;
import lance5057.compendium.core.data.builders.workstationrecipes.builders.AnvilShapedRecipeBuilder;
import lance5057.compendium.core.library.CompendiumTags;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class MetalVanillaTools implements MaterialBase {
    public RegistryObject<Item> SWORD;
    public RegistryObject<Item> PICKAXE;
    public RegistryObject<Item> SHOVEL;
    public RegistryObject<Item> AXE;
    public RegistryObject<Item> HOE;

    public MetalVanillaTools(MetallurgyMaterialHelper mm) {

    }

    @Override
    public void setupClient(MaterialHelperBase mat) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setup(MaterialHelperBase mm) {
	SWORD = mm.ITEMS.register(mm.name + "sword", () -> new SwordItem(mm.tier, 3, -2.4f,
		new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	PICKAXE = mm.ITEMS.register(mm.name + "pickaxe", () -> new PickaxeItem(mm.tier, 1, -2.8F,
		(new Item.Properties()).group(MetallurgyMaterialHelper.GROUP_METAL)));
	SHOVEL = mm.ITEMS.register(mm.name + "shovel", () -> new ShovelItem(mm.tier, 1.5F, -3.0F,
		(new Item.Properties()).group(MetallurgyMaterialHelper.GROUP_METAL)));
	AXE = mm.ITEMS.register(mm.name + "axe", () -> new AxeItem(mm.tier, 5.0F, -3.0F,
		(new Item.Properties()).group(MetallurgyMaterialHelper.GROUP_METAL)));
	HOE = mm.ITEMS.register(mm.name + "hoe", () -> new HoeItem(mm.tier, -3, 0.0F,
		(new Item.Properties()).group(MetallurgyMaterialHelper.GROUP_METAL)));
    }

    public static void registerBlockModels(TCBlockModels model, String name) {

    }

    public static void addTranslations(MetalVanillaTools m, TCEnglishLoc loc, String capName) {
	loc.add(m.AXE.get(), capName + " Axe");
	loc.add(m.HOE.get(), capName + " Hoe");
	loc.add(m.PICKAXE.get(), capName + " Pickaxe");
	loc.add(m.SHOVEL.get(), capName + " Shovel");
	loc.add(m.SWORD.get(), capName + " Sword");
    }

    public static void registerItemModels(MetalVanillaTools m, TCItemModels model, String name) {
	model.withExistingParent(m.AXE.getId().getPath(), model.mcLoc("item/handheld"))
		.texture("layer0", model.modLoc("item/material/" + name + "/" + m.AXE.getId().getPath()))
		.texture("layer1", model.modLoc("item/axebase"));

	model.withExistingParent(m.HOE.getId().getPath(), model.mcLoc("item/handheld"))
		.texture("layer0", model.modLoc("item/material/" + name + "/" + m.HOE.getId().getPath()))
		.texture("layer1", model.modLoc("item/hoebase"));

	model.withExistingParent(m.PICKAXE.getId().getPath(), model.mcLoc("item/handheld"))
		.texture("layer0", model.modLoc("item/material/" + name + "/" + m.PICKAXE.getId().getPath()))
		.texture("layer1", model.modLoc("item/pickaxebase"));

	model.withExistingParent(m.SHOVEL.getId().getPath(), model.mcLoc("item/handheld"))
		.texture("layer0", model.modLoc("item/material/" + name + "/" + m.SHOVEL.getId().getPath()))
		.texture("layer1", model.modLoc("item/shovelbase"));

	model.withExistingParent(m.SWORD.getId().getPath(), model.mcLoc("item/handheld"))
		.texture("layer0", model.modLoc("item/material/" + name + "/" + m.SWORD.getId().getPath()))
		.texture("layer1", model.modLoc("item/swordbase"));
    }

    public static void buildRecipes(MetalVanillaTools b, TCRecipes recipes, Consumer<IFinishedRecipe> consumer,
	    String name) {

	// Sword
	AnvilShapedRecipeBuilder.shapedRecipe(b.SWORD.get(), 1)
		.key('i', Ingredient.fromTag(TCItemTags.ItemTag("ingots/" + name)))
		.key('r', Ingredient.fromTag(TCItemTags.ItemTag("rods/" + name))).key('s', Items.STICK)
		.key('o', Ingredient.fromTag(TCItemTags.ItemTag("rivets/" + name)))
		.key('n', Ingredient.fromTag(TCItemTags.ItemTag("nuggets/" + name))).patternLine(" i ")
		.patternLine(" i ").patternLine("rso").patternLine(" n ")
		.addCriterion(name + "ingot", RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + name)))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 16, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_sword"));

	// Pickaxe
	AnvilShapedRecipeBuilder.shapedRecipe(b.PICKAXE.get(), 1)
		.key('i', Ingredient.fromTag(TCItemTags.ItemTag("ingots/" + name))).key('s', Items.STICK)
		.key('r', Ingredient.fromTag(TCItemTags.ItemTag("rods/" + name)))
		.key('o', Ingredient.fromTag(TCItemTags.ItemTag("rivets/" + name)))
		.key('n', Ingredient.fromTag(TCItemTags.ItemTag("nuggets/" + name))).patternLine(" o ")
		.patternLine("rir").patternLine(" s ").patternLine(" s ").patternLine(" n ")
		.addCriterion(name + "ingot", RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + name)))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 16, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_pickaxe"));

	// Axe
	AnvilShapedRecipeBuilder.shapedRecipe(b.AXE.get(), 1)
		.key('i', Ingredient.fromTag(TCItemTags.ItemTag("ingots/" + name))).key('s', Items.STICK)
		.key('o', Ingredient.fromTag(TCItemTags.ItemTag("rivets/" + name)))
		.key('n', Ingredient.fromTag(TCItemTags.ItemTag("nuggets/" + name))).patternLine(" o ")
		.patternLine("ii ").patternLine("is ").patternLine(" s ").patternLine(" n ")
		.addCriterion(name + "ingot", RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + name)))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 16, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_axe"));

	// Shovel
	AnvilShapedRecipeBuilder.shapedRecipe(b.SHOVEL.get(), 1)
		.key('i', Ingredient.fromTag(TCItemTags.ItemTag("plates/" + name))).key('s', Items.STICK)
		.key('o', Ingredient.fromTag(TCItemTags.ItemTag("rivets/" + name)))
		.key('n', Ingredient.fromTag(TCItemTags.ItemTag("nuggets/" + name))).patternLine(" i ")
		.patternLine(" so").patternLine(" s ").patternLine(" n ")
		.addCriterion(name + "ingot", RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + name)))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 16, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_shovel"));

	// Hoe
	AnvilShapedRecipeBuilder.shapedRecipe(b.HOE.get(), 1)
		.key('i', Ingredient.fromTag(TCItemTags.ItemTag("plates/" + name))).key('s', Items.STICK)
		.key('r', Ingredient.fromTag(TCItemTags.ItemTag("rods/" + name)))
		.key('o', Ingredient.fromTag(TCItemTags.ItemTag("rivets/" + name)))
		.key('n', Ingredient.fromTag(TCItemTags.ItemTag("nuggets/" + name))).patternLine(" o ")
		.patternLine("ir ").patternLine(" s ").patternLine(" s ").patternLine(" n ")
		.addCriterion(name + "ingot", RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + name)))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 16, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_hoe"));

    }
}
