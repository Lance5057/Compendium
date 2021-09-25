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
import lance5057.compendium.core.recipes.CustomCookingRecipeBuilder;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class MetalMaterialComponents implements MaterialBase {
    public RegistryObject<Item> SETTING;
    public RegistryObject<Item> JUMPRINGS;
    public RegistryObject<Item> FILIGREE;
    public RegistryObject<Item> FOIL;
    public RegistryObject<Item> COIL;
    public RegistryObject<Item> SPRING;
    public RegistryObject<Item> CASING;
    public RegistryObject<Item> WIRE;
    public RegistryObject<Item> CLASP;
    public RegistryObject<Item> RINGSHANK;
    public RegistryObject<Item> RIVETS;
    public RegistryObject<Item> PLATE;
    public RegistryObject<Item> COIN;
    public RegistryObject<Item> GEAR;
    public RegistryObject<Item> ROD;
    public RegistryObject<Item> DUST;
    public RegistryObject<Item> TINYDUST;
    public RegistryObject<Item> KEY;

    public MetalMaterialComponents(MetallurgyMaterialHelper mh) {

    }

    @Override
    public void setupClient(MaterialHelperBase mat) {

    }

    @Override
    public void setup(MaterialHelperBase mh) {
	PLATE = mh.ITEMS.register(mh.name + "plate",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));

	COIN = mh.ITEMS.register(mh.name + "coin",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));

	GEAR = mh.ITEMS.register(mh.name + "gear",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));

	ROD = mh.ITEMS.register(mh.name + "rod",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));

	COIL = mh.ITEMS.register(mh.name + "coil",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));

	SPRING = mh.ITEMS.register(mh.name + "spring",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));

	CASING = mh.ITEMS.register(mh.name + "casing",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));

	WIRE = mh.ITEMS.register(mh.name + "wire",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));

	CLASP = mh.ITEMS.register(mh.name + "clasp",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	RINGSHANK = mh.ITEMS.register(mh.name + "ringshank",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	RIVETS = mh.ITEMS.register(mh.name + "rivets",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	SETTING = mh.ITEMS.register(mh.name + "setting",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	JUMPRINGS = mh.ITEMS.register(mh.name + "jumprings",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	FILIGREE = mh.ITEMS.register(mh.name + "filigree",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	FOIL = mh.ITEMS.register(mh.name + "foil",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	DUST = mh.ITEMS.register(mh.name + "dust",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	TINYDUST = mh.ITEMS.register(mh.name + "tinydust",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	KEY = mh.ITEMS.register(mh.name + "key",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
    }

    public static void registerBlockModels(MetalMaterialComponents m, TCBlockModels model, String name) {

    }

    public static void registerItemModels(MetalMaterialComponents m, TCItemModels model, String name) {
	model.forMaterialItem(m.SETTING, name);
	model.forMaterialItem(m.JUMPRINGS, name);
	model.forMaterialItem(m.FILIGREE, name);
	model.forMaterialItem(m.FOIL, name);
	model.forMaterialItem(m.COIL, name);
	model.forMaterialItem(m.SPRING, name);
	model.forMaterialItem(m.CASING, name);
	model.forMaterialItem(m.WIRE, name);
	model.forMaterialItem(m.CLASP, name);
	model.forMaterialItem(m.RINGSHANK, name);
	model.forMaterialItem(m.RIVETS, name);
	model.forMaterialItem(m.PLATE, name);
	model.forMaterialItem(m.COIN, name);
	model.forMaterialItem(m.GEAR, name);
	model.forMaterialItem(m.ROD, name);
	model.forMaterialItem(m.DUST, name);
	model.forMaterialItem(m.TINYDUST, name);
	model.forMaterialItem(m.KEY, name);
    }

    public static void addTranslations(MetalMaterialComponents m, TCEnglishLoc loc, String capName) {
	loc.add(m.SETTING.get(), capName + " Setting");
	loc.add(m.JUMPRINGS.get(), capName + " Jumprings");
	loc.add(m.FILIGREE.get(), capName + " Filigrees");
	loc.add(m.FOIL.get(), capName + " Foil");
	loc.add(m.COIL.get(), capName + " Coil");
	loc.add(m.SPRING.get(), capName + " Spring");
	loc.add(m.CASING.get(), capName + " Casing");
	loc.add(m.WIRE.get(), capName + " Wire");
	loc.add(m.CLASP.get(), capName + " Clasp");
	loc.add(m.RINGSHANK.get(), capName + " Ring Shank");
	loc.add(m.RIVETS.get(), capName + " Rivets");
	loc.add(m.PLATE.get(), capName + " Plate");
	loc.add(m.COIN.get(), capName + " Coin");
	loc.add(m.GEAR.get(), capName + " Gear");
	loc.add(m.ROD.get(), capName + " Rod");
	loc.add(m.DUST.get(), capName + " Dust");
	loc.add(m.TINYDUST.get(), capName + " Tiny Dust");
	loc.add(m.KEY.get(), capName + " Key");
    }

    public static void buildRecipes(MetalMaterialComponents b, TCRecipes recipes, Consumer<IFinishedRecipe> consumer,
	    String name, String parent) {

	// Coins
	AnvilShapedRecipeBuilder.shapedRecipe(b.COIN.get(), 4)
		.key('p', Ingredient.fromTag(TCItemTags.ItemTag("nuggets/" + name))).patternLine("pp")
		.patternLine("pp")
		.addCriterion(name + "nugget", RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + name)))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 4, true)
		.tool(Ingredient.fromItems(Items.FLINT), 1, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_coin_from_nuggets"));

	// Gears
	AnvilShapedRecipeBuilder.shapedRecipe(b.GEAR.get(), 1)
		.key('p', Ingredient.fromTag(TCItemTags.ItemTag("ingots/" + name))).patternLine(" p ")
		.patternLine("p p").patternLine(" p ")
		.addCriterion(name + "ingot", RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + name)))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 12, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_gear_from_ingots"));

	// Plates
	AnvilShapedRecipeBuilder.shapedRecipe(b.PLATE.get(), 1)
		.key('p', Ingredient.fromTag(TCItemTags.ItemTag("ingots/" + name))).patternLine("p")
		.addCriterion(name + "ingot", RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + name)))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 8, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_plates_from_ingots"));

	// Rods
	AnvilShapedRecipeBuilder.shapedRecipe(b.ROD.get(), 6)
		.key('p', Ingredient.fromTag(TCItemTags.ItemTag("ingots/" + name))).patternLine("p").patternLine("p")
		.patternLine("p")
		.addCriterion(name + "ingot", RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + name)))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 12, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_rods_from_ingots"));

	// Springs
	AnvilShapedRecipeBuilder.shapedRecipe(b.SPRING.get(), 1)
		.key('p', Ingredient.fromTag(TCItemTags.ItemTag("wires/" + name))).key('s', Items.STICK)
		.patternLine("p").patternLine("s")
		.addCriterion(name + "wire", RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + name)))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 4, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_spring_from_wire"));

	// Wire
	AnvilShapedRecipeBuilder.shapedRecipe(b.WIRE.get(), 4)
		.key('p', Ingredient.fromTag(TCItemTags.ItemTag("rods/" + name))).patternLine("p")
		.addCriterion(name + "rod", RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + name)))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 8, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_wires_from_rod"));

	// Casing
	AnvilShapedRecipeBuilder.shapedRecipe(b.CASING.get(), 1)
		.key('p', Ingredient.fromTag(TCItemTags.ItemTag("plates/" + name))).patternLine("ppp")
		.patternLine("p p")
		.addCriterion(name + "casing", RecipeProvider.hasItem(TCItemTags.ItemTag("plates/" + name)))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 10, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_casing_from_plates"));

	// Coil
	AnvilShapedRecipeBuilder.shapedRecipe(b.COIL.get(), 1)
		.key('p', Ingredient.fromTag(TCItemTags.ItemTag("wires/" + name))).key('s', Items.STICK)
		.patternLine(" p ").patternLine("psp").patternLine(" p ")
		.addCriterion(name + "wire", RecipeProvider.hasItem(TCItemTags.ItemTag("wires/" + name)))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 4, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_coil_from_wire"));

	// CLASP
	AnvilShapedRecipeBuilder.shapedRecipe(b.CLASP.get(), 4)
		.key('p', Ingredient.fromTag(TCItemTags.ItemTag("wires/" + name)))
		.key('s', Ingredient.fromTag(TCItemTags.ItemTag("nuggets/" + name))).patternLine("p")
		.patternLine("s")
		.addCriterion(name + "wire", RecipeProvider.hasItem(TCItemTags.ItemTag("wires/" + name)))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 8, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_clasp_from_wire"));

//	RINGSHANK
	AnvilShapedRecipeBuilder.shapedRecipe(b.RINGSHANK.get(), 4)
		.key('s', Ingredient.fromTag(TCItemTags.ItemTag("wires/" + name))).patternLine(" s ")
		.patternLine("s s").patternLine(" s ")
		.addCriterion(name + "wire", RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + name)))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 8, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_ringshank_from_wire"));

//	RIVETS
	AnvilShapedRecipeBuilder.shapedRecipe(b.RIVETS.get(), 4)
		.key('s', Ingredient.fromTag(TCItemTags.ItemTag("nuggets/" + name))).patternLine("s")
		.addCriterion(name + "rod", RecipeProvider.hasItem(TCItemTags.ItemTag("nuggets/" + name)))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 8, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_rivets_from_rod"));

//	SETTING
	AnvilShapedRecipeBuilder.shapedRecipe(b.SETTING.get(), 4)
		.key('s', Ingredient.fromTag(TCItemTags.ItemTag("filigrees/" + name)))
		.key('p', Ingredient.fromTag(TCItemTags.ItemTag("nuggets/" + name))).patternLine(" s ")
		.patternLine("sps").patternLine(" s ")
		.addCriterion(name + "rod", RecipeProvider.hasItem(TCItemTags.ItemTag("filigree/" + name)))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 8, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_setting_from_filigree"));

//	JUMPRINGS
	AnvilShapedRecipeBuilder.shapedRecipe(b.JUMPRINGS.get(), 8)
		.key('s', Ingredient.fromTag(TCItemTags.ItemTag("wires/" + name))).patternLine("ss")
		.patternLine("ss")
		.addCriterion(name + "wire", RecipeProvider.hasItem(TCItemTags.ItemTag("wires/" + name)))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_jumprings_from_wire"));

//	FILIGREE
	AnvilShapedRecipeBuilder.shapedRecipe(b.FILIGREE.get(), 6)
		.key('s', Ingredient.fromTag(TCItemTags.ItemTag("wires/" + name))).patternLine("s").patternLine("s")
		.patternLine("s")
		.addCriterion(name + "wire", RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + name)))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 4, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_filigree_from_wire"));

//	FOIL
	AnvilShapedRecipeBuilder.shapedRecipe(b.FOIL.get(), 4)
		.key('s', Ingredient.fromTag(TCItemTags.ItemTag("plates/" + name))).patternLine("s")
		.addCriterion(name + "plate", RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + name)))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 4, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_foil_from_plate"));

	// Dust
	ShapelessRecipeBuilder.shapelessRecipe(b.DUST.get(), 1)
		.addIngredient(Ingredient.fromTag(TCItemTags.ItemTag("tiny_dusts/" + name)), 9)
		.addCriterion(name + "tinydust", RecipeProvider.hasItem(TCItemTags.ItemTag("tiny_dusts/" + name)))

		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_dust_from_tinydust"));

	ShapelessRecipeBuilder.shapelessRecipe(b.TINYDUST.get(), 9)
		.addIngredient(Ingredient.fromTag(TCItemTags.ItemTag("dusts/" + name)), 1)
		.addCriterion(name + "dust", RecipeProvider.hasItem(TCItemTags.ItemTag("dusts/" + name)))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_tinydust_from_dust"));

	CustomCookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(b.TINYDUST.get()),
		Registry.ITEM.getOrDefault(new ResourceLocation(parent, name + "_nugget")), 1, 0.7F, 200)
		.addCriterion("has_tinydust", RecipeProvider.hasItem(TCItemTags.ItemTag("tiny_dusts/" + name)))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_tinydust_smelt"));

	CustomCookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(b.DUST.get()),
		Registry.ITEM.getOrDefault(new ResourceLocation(parent, name + "_ingot")), 1, 0.7F, 200)
		.addCriterion("has_tinydust", RecipeProvider.hasItem(TCItemTags.ItemTag("dusts/" + name)))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_dust_smelt")); 
    }
}
