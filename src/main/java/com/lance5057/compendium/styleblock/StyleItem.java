package com.lance5057.compendium.styleblock;

import java.util.List;

import com.lance5057.compendium.Compendium;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.level.block.Block;

public class StyleItem extends BlockItem {

	public StyleItem(Block b, Properties properties) {
		super(b, properties);
		// TODO Auto-generated constructor stub
	}

	public String getStyleFromBlock(BlockItemStateProperties bisp) {
		return "";
	}

	public String getStyleFromBlock(int i) {
		return "";
	}

	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents,
			TooltipFlag tooltipFlag) {
//		if (stack.has(DataComponents.BLOCK_STATE)) {
		BlockItemStateProperties bisp = stack.get(DataComponents.BLOCK_STATE);
		MutableComponent textEmpty = Component.translatable(Compendium.MOD_ID + ".tooltip." + getStyleFromBlock(bisp));
		tooltipComponents.add(textEmpty.withStyle(ChatFormatting.AQUA));
//		}
	}

}
