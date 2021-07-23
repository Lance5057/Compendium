package lance5057.compendium.core.items.armor;

import javax.annotation.Nullable;

import lance5057.compendium.Reference;
import lance5057.compendium.core.client.models.armor.heavy.ModelBreastplate;
import lance5057.compendium.core.client.models.armor.heavy.ModelGrieves;
import lance5057.compendium.core.client.models.armor.heavy.ModelHelm;
import lance5057.compendium.core.client.models.armor.heavy.ModelSabatons;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CompendiumArmorItem extends ArmorItem {

    String matName;

    public CompendiumArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Item.Properties builderIn,
	    String materialName) {
	super(materialIn, slot, builderIn);
	matName = materialName;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    @Nullable
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack,
	    EquipmentSlotType armorSlot, A _default) {
	if (armorSlot == EquipmentSlotType.HEAD)
	    return (A) new ModelHelm();
	if (armorSlot == EquipmentSlotType.CHEST)
	    return (A) new ModelBreastplate();
	if (armorSlot == EquipmentSlotType.LEGS)
	    return (A) new ModelGrieves();
	if (armorSlot == EquipmentSlotType.FEET)
	    return (A) new ModelSabatons();
	return _default;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
	
	if (slot == EquipmentSlotType.HEAD)
	    return new ResourceLocation(Reference.MOD_ID,
			"textures/models/armor/material/" + matName + "/" + matName + "_helm.png").toString();
	if (slot == EquipmentSlotType.CHEST)
	    return new ResourceLocation(Reference.MOD_ID,
			"textures/models/armor/material/" + matName + "/" + matName + "_breastplate.png").toString();
	if (slot == EquipmentSlotType.LEGS)
	    return new ResourceLocation(Reference.MOD_ID,
			"textures/models/armor/material/" + matName + "/" + matName + "_grieves.png").toString();
	if (slot == EquipmentSlotType.FEET)
	    return new ResourceLocation(Reference.MOD_ID,
			"textures/models/armor/material/" + matName + "/" + matName + "_sabatons.png").toString();
	
	return null;
    }
}
