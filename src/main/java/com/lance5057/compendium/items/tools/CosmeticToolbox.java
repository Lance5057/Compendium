package com.lance5057.compendium.items.tools;

import java.util.List;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.styleblock.StyleBlock;
import com.lance5057.compendium.styleblock.StyleBlockMenu;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;

public class CosmeticToolbox extends Item {

	public CosmeticToolbox(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}

	@Override
	public InteractionResult useOn(UseOnContext pContext) {
		if (pContext.getLevel().getBlockState(pContext.getClickedPos()).getBlock() instanceof StyleBlock s) {
			if (!pContext.getLevel().isClientSide())
				pContext.getPlayer().openMenu(new SimpleMenuProvider((p_57074_, p_57075_, p_57076_) -> {
					return new StyleBlockMenu(p_57074_, p_57075_,
							ContainerLevelAccess.create(pContext.getLevel(), pContext.getClickedPos()),
							pContext.getClickedPos());
				}, CommonComponents.EMPTY));
		}
		return InteractionResult.PASS;

	}

	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents,
			TooltipFlag tooltipFlag) {
		MutableComponent textEmpty = Component.translatable(Compendium.MOD_ID + ".tooltip.toolbox");
		tooltipComponents.add(textEmpty.withStyle(ChatFormatting.AQUA));
	}
}
