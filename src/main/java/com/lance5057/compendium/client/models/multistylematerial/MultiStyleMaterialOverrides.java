package com.lance5057.compendium.client.models.multistylematerial;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.components.block.MultiMaterialBlockComponent;
import com.lance5057.compendium.components.block.StyleBlockComponent;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;

public class MultiStyleMaterialOverrides extends ItemOverrides {
	@Override
	@Nullable
	@ParametersAreNonnullByDefault
	public BakedModel resolve(BakedModel pModel, ItemStack pStack, @Nullable ClientLevel pLevel,
			@Nullable LivingEntity pEntity, int pSeed) {
		if (pStack.getItem() instanceof BlockItem bi) {
			BakedModel bm = Minecraft.getInstance().getBlockRenderer().getBlockModel(bi.getBlock().defaultBlockState());

			MultiMaterialBlockComponent mmc = pStack.get(CompendiumComponents.MULTI_MATERIAL);
			StyleBlockComponent sc = pStack.get(CompendiumComponents.STYLE);

			

			return bm;
		}
		return pModel;
	}
}
