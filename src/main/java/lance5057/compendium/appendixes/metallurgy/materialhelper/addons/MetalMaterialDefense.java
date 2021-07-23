package lance5057.compendium.appendixes.metallurgy.materialhelper.addons;

import java.util.function.Consumer;

import lance5057.compendium.Reference;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.core.client.ISTERenderers;
import lance5057.compendium.core.client.ShieldRenderer;
import lance5057.compendium.core.data.builders.TCBlockModels;
import lance5057.compendium.core.data.builders.TCEnglishLoc;
import lance5057.compendium.core.data.builders.TCItemModels;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.data.builders.TCRecipes;
import lance5057.compendium.core.data.builders.loottables.BlockLoot;
import lance5057.compendium.core.data.builders.workstationrecipes.AnvilShapedRecipeBuilder;
import lance5057.compendium.core.items.armor.CompendiumArmorItem;
import lance5057.compendium.core.library.CompendiumTags;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.RegistryObject;

public class MetalMaterialDefense implements MaterialBase {
    public RegistryObject<ArmorItem> HELM;
    public RegistryObject<ArmorItem> BREASTPLATE;
    public RegistryObject<ArmorItem> LEGGINGS;
    public RegistryObject<ArmorItem> BOOTS;
    public RegistryObject<ShieldItem> SHIELD;

    public IArmorMaterial armorMat;

    public MetalMaterialDefense(MetallurgyMaterialHelper metallurgyMaterialHelper, IArmorMaterial iam) {
	armorMat = iam;
    }

    @Override
    public void setupClient(MaterialHelperBase mat) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setup(MaterialHelperBase mat) {
	HELM = mat.ITEMS.register(mat.name + "helm", () -> new CompendiumArmorItem(armorMat, EquipmentSlotType.HEAD,
		new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL), mat.name));
	BREASTPLATE = mat.ITEMS.register(mat.name + "breastplate", () -> new CompendiumArmorItem(armorMat, EquipmentSlotType.CHEST,
		new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL), mat.name));
	LEGGINGS = mat.ITEMS.register(mat.name + "grieves", () -> new CompendiumArmorItem(armorMat, EquipmentSlotType.LEGS,
		new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL), mat.name));
	BOOTS = mat.ITEMS.register(mat.name + "sabatons", () -> new CompendiumArmorItem(armorMat, EquipmentSlotType.FEET,
		new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL), mat.name));

	SHIELD = mat.ITEMS.register(mat.name + "_shield",
		() -> new ShieldItem(new Item.Properties().setISTER(() -> {return ISTERenderers.makeShield(mat.name);}).group(MetallurgyMaterialHelper.GROUP_METAL)));
    }

    public static void registerBlockModels(MetalMaterialDefense m, TCBlockModels model, String name) {
    }

    public static void registerItemModels(MetalMaterialDefense m, TCItemModels model, String name) {
	model.withExistingParent(m.SHIELD.getId().getPath(), model.mcLoc("item/shield"));
	
	model.forMaterialItem(m.BOOTS, name);
	model.forMaterialItem(m.BREASTPLATE, name);
	model.forMaterialItem(m.HELM, name);
	model.forMaterialItem(m.LEGGINGS, name);
    }

    public static void addTranslations(MetalMaterialDefense m, TCEnglishLoc loc, String capName) {
	loc.add(m.SHIELD.get(), capName + " Shield");
	loc.add(m.BOOTS.get(), capName + " Boots");
	loc.add(m.BREASTPLATE.get(), capName + " Breastplate");
	loc.add(m.HELM.get(), capName + " Helm");
	loc.add(m.LEGGINGS.get(), capName + " Greaves");
    }

    public static void registerTags(MetalMaterialDefense m, TCItemTags itp, String name) {
    }

    public static void buildLootTable(MetalMaterialDefense m, BlockLoot table, String name) {
    }

    public static void buildRecipes(MetalMaterialDefense m, TCRecipes recipes, Consumer<IFinishedRecipe> consumer,
	    String name) {
	AnvilShapedRecipeBuilder.shapedRecipe(m.SHIELD.get(), 1)
	.key('p', Ingredient.fromTag(TCItemTags.ItemTag("plates/" + name)))
	.key('r', Ingredient.fromTag(TCItemTags.ItemTag("rods/" + name)))
	.key('o', Ingredient.fromTag(TCItemTags.ItemTag("rivets/" + name)))
	.key('n', Ingredient.fromTag(ItemTags.PLANKS))
	.patternLine("po ")
	.patternLine("pnr")
	.patternLine("po ")
	.addCriterion(name + "ingot",
		RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + name)))
	.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 16, true)
	.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_shield"));
	
	AnvilShapedRecipeBuilder.shapedRecipe(m.BOOTS.get(), 1)
	.key('p', Ingredient.fromTag(TCItemTags.ItemTag("plates/" + name)))
	.key('f', Ingredient.fromTag(TCItemTags.ItemTag("filigrees/" + name)))
	.key('o', Ingredient.fromTag(TCItemTags.ItemTag("rivets/" + name)))
	.patternLine(" o o ")
	.patternLine(" p p ")
	.patternLine("fp pf")
	.addCriterion(name + "ingot",
		RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + name)))
	.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 16, true)
	.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_boots"));
	
	AnvilShapedRecipeBuilder.shapedRecipe(m.HELM.get(), 1)
	.key('p', Ingredient.fromTag(TCItemTags.ItemTag("plates/" + name)))
	.key('f', Ingredient.fromTag(TCItemTags.ItemTag("filigrees/" + name)))
	.key('o', Ingredient.fromTag(TCItemTags.ItemTag("rivets/" + name)))
	.key('r', Ingredient.fromTag(TCItemTags.ItemTag("rods/" + name)))
	.patternLine(" r ")
	.patternLine("ppp")
	.patternLine("pop")
	.patternLine("f f")
	.addCriterion(name + "ingot",
		RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + name)))
	.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 16, true)
	.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_helm"));
	
	AnvilShapedRecipeBuilder.shapedRecipe(m.HELM.get(), 1)
	.key('p', Ingredient.fromTag(TCItemTags.ItemTag("plates/" + name)))
	.key('f', Ingredient.fromTag(TCItemTags.ItemTag("filigrees/" + name)))
	.key('o', Ingredient.fromTag(TCItemTags.ItemTag("rivets/" + name)))
	.patternLine(" o o ")
	.patternLine(" p p ")
	.patternLine("fpppf")
	.patternLine(" ppp ")
	.addCriterion(name + "ingot",
		RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + name)))
	.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 16, true)
	.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_breastplate"));
	
	AnvilShapedRecipeBuilder.shapedRecipe(m.HELM.get(), 1)
	.key('p', Ingredient.fromTag(TCItemTags.ItemTag("plates/" + name)))
	.key('f', Ingredient.fromTag(TCItemTags.ItemTag("filigrees/" + name)))
	.key('o', Ingredient.fromTag(TCItemTags.ItemTag("rivets/" + name)))
	.key('c', Ingredient.fromTag(TCItemTags.ItemTag("clasps/" + name)))
	.patternLine(" oco ")
	.patternLine(" ppp ")
	.patternLine("fp pf")
	.patternLine(" p p ")
	.addCriterion(name + "ingot",
		RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + name)))
	.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 16, true)
	.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_greaves"));
    }
}
