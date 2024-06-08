package com.lance5057.compendium.client.armor;

import com.lance5057.compendium.Compendium;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

public class CompendiumArmorItem extends ArmorItem {

	final ResourceLocation textureLoc;

	public CompendiumArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties, String textureLoc) {

		super(pMaterial, pType, pProperties);

		this.textureLoc = new ResourceLocation(Compendium.MOD_ID, textureLoc);
	}

	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
		return textureLoc.toString();
	}
}
