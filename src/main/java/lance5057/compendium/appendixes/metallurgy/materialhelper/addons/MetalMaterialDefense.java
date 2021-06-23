package lance5057.compendium.appendixes.metallurgy.materialhelper.addons;

import java.util.function.Consumer;

import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.core.client.ShieldRenderer;
import lance5057.compendium.core.data.builders.TCBlockModels;
import lance5057.compendium.core.data.builders.TCEnglishLoc;
import lance5057.compendium.core.data.builders.TCItemModels;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.data.builders.TCRecipes;
import lance5057.compendium.core.data.builders.loottables.BlockLoot;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ShieldItem;
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
	HELM = mat.ITEMS.register(mat.name + "_helm", () -> new ArmorItem(armorMat, EquipmentSlotType.HEAD,
		new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	BREASTPLATE = mat.ITEMS.register(mat.name + "_chestplate", () -> new ArmorItem(armorMat, EquipmentSlotType.CHEST,
		new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	LEGGINGS = mat.ITEMS.register(mat.name + "_leggings", () -> new ArmorItem(armorMat, EquipmentSlotType.LEGS,
		new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	BOOTS = mat.ITEMS.register(mat.name + "_boots", () -> new ArmorItem(armorMat, EquipmentSlotType.FEET,
		new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));

	SHIELD = mat.ITEMS.register(mat.name + "_shield",
		() -> new ShieldItem(new Item.Properties().setISTER(() -> ShieldRenderer::new).group(MetallurgyMaterialHelper.GROUP_METAL)));
    }

    public static void registerBlockModels(MetalMaterialDefense m, TCBlockModels model, String name) {
    }

    public static void registerItemModels(MetalMaterialDefense m, TCItemModels model, String name) {
	model.withExistingParent(m.SHIELD.getId().getPath(), model.mcLoc("item/shield"));
    }

    public static void addTranslations(MetalMaterialDefense m, TCEnglishLoc loc, String capName) {
    }

    public static void registerTags(MetalMaterialDefense m, TCItemTags itp, String name) {
    }

    public static void buildLootTable(MetalMaterialDefense m, BlockLoot table, String name) {
    }

    public static void buildRecipes(MetalMaterialDefense m, TCRecipes recipes, Consumer<IFinishedRecipe> consumer,
	    String name) {

    }
}
