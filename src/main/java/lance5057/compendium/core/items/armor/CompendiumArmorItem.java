package lance5057.compendium.core.items.armor;

import lance5057.compendium.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CompendiumArmorItem extends ArmorItem {

    String matName;

    public CompendiumArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Item.Properties builderIn,
	    String materialName) {
	super(materialIn, slot, builderIn);
	matName = materialName;
    }

//    @Override
//    @OnlyIn(Dist.CLIENT)
//    @Nullable
//    public <A extends HumanoidModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack,
//	    EquipmentSlot armorSlot, A _default) {
//	if (armorSlot == EquipmentSlot.HEAD)
//	    return (A) new ModelHelm();
//	if (armorSlot == EquipmentSlot.CHEST)
//	    return (A) new ModelBreastplate();
//	if (armorSlot == EquipmentSlot.LEGS)
//	    return (A) new ModelGrieves();
//	if (armorSlot == EquipmentSlot.FEET)
//	    return (A) new ModelSabatons();
//	return _default;
//    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
	
	if (slot == EquipmentSlot.HEAD)
	    return new ResourceLocation(Reference.MOD_ID,
			"textures/models/armor/material/" + matName + "/" + matName + "_helm.png").toString();
	if (slot == EquipmentSlot.CHEST)
	    return new ResourceLocation(Reference.MOD_ID,
			"textures/models/armor/material/" + matName + "/" + matName + "_breastplate.png").toString();
	if (slot == EquipmentSlot.LEGS)
	    return new ResourceLocation(Reference.MOD_ID,
			"textures/models/armor/material/" + matName + "/" + matName + "_grieves.png").toString();
	if (slot == EquipmentSlot.FEET)
	    return new ResourceLocation(Reference.MOD_ID,
			"textures/models/armor/material/" + matName + "/" + matName + "_sabatons.png").toString();
	
	return null;
    }
}
