package lance5057.compendium.core.items.armor;

import javax.annotation.Nullable;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CompendiumArmorItem extends ArmorItem {

    public CompendiumArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Item.Properties builderIn) {
	super(materialIn, slot, builderIn);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    @Nullable
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack,
	    EquipmentSlotType armorSlot, A _default) {
	return null;
    }
}
