package com.lance5057.compendium.items;

import java.util.function.Consumer;

import org.jetbrains.annotations.NotNull;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumClient;
import com.lance5057.compendium.client.armor.CompendiumArmorModel;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

public class CompendiumArmorItem extends ArmorItem {

	final ResourceLocation textureLoc;

	public CompendiumArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties, String textureLoc) {

		super(pMaterial, pType, pProperties);

		this.textureLoc = new ResourceLocation(Compendium.MOD_ID, textureLoc);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
		return textureLoc.toString();
	}

	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer) {
		consumer.accept(ArmorRender.INSTANCE);
	}

	private static final class ArmorRender implements IClientItemExtensions {
		private static final ArmorRender INSTANCE = new ArmorRender();

		@Override
		public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot,
				HumanoidModel<?> model) {
			EntityModelSet models = Minecraft.getInstance().getEntityModels();

			ModelPart root = null;
			switch (slot) {
			case FEET:
			default:
				root = models.bakeLayer(CompendiumClient.SABATONS);
				break;
			}

			return new CompendiumArmorModel(root);
		}
	}
}
