package com.lance5057.compendium.items.tools;

import java.util.List;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.styleblock.StyleBlock;
import com.lance5057.compendium.workstations.cosmetictoolbox.CosmeticToolboxMenu;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;

public class CosmeticToolbox extends BlockItem {

	public CosmeticToolbox(Block block, Properties properties) {
		super(block, properties);
		// TODO Auto-generated constructor stub
	}

	@Override
	public InteractionResult useOn(UseOnContext pContext) {
		if (!pContext.getPlayer().isCrouching()) {
			if (pContext.getLevel().getBlockState(pContext.getClickedPos()).getBlock() instanceof StyleBlock s) {
				if (!pContext.getLevel().isClientSide())
					pContext.getPlayer().openMenu(new SimpleMenuProvider((p_57074_, p_57075_, p_57076_) -> {
						return new CosmeticToolboxMenu(p_57074_, p_57075_,
								ContainerLevelAccess.create(pContext.getLevel(), pContext.getClickedPos()),
								pContext.getClickedPos());
					}, CommonComponents.EMPTY));
				return InteractionResult.PASS;
			}
		} else
			return super.useOn(pContext);
		return InteractionResult.CONSUME;
	}

	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents,
			TooltipFlag tooltipFlag) {
		MutableComponent textEmpty = Component.translatable(Compendium.MOD_ID + ".tooltip.toolbox");
		tooltipComponents.add(textEmpty.withStyle(ChatFormatting.AQUA));
	}
}
