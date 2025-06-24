package com.lance5057.compendium.workstations.cosmetictoolbox;

import java.util.List;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.blocks.IStyleable;
import com.lance5057.compendium.blocks.entities.MultiMaterialBlockEntity;
import com.lance5057.compendium.blocks.entities.SimpleStyleBlockEntity;

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
import net.minecraft.world.level.block.entity.BlockEntity;

public class CosmeticToolboxItem extends BlockItem {

	public CosmeticToolboxItem(Block block, Properties properties) {
		super(block, properties);
		// TODO Auto-generated constructor stub
	}

	@Override
	public InteractionResult useOn(UseOnContext pContext) {
		BlockEntity pos = pContext.getLevel().getBlockEntity(pContext.getClickedPos());
		if (pos instanceof MultiMaterialBlockEntity || pos instanceof IStyleable) {

			pContext.getPlayer().openMenu(new SimpleMenuProvider((p_57074_, p_57075_, p_57076_) -> {
				return new CosmeticToolboxMenu(p_57074_, p_57075_,
						ContainerLevelAccess.create(pContext.getLevel(), pContext.getClickedPos()),
						pContext.getClickedPos());
			}, CommonComponents.EMPTY));
			return InteractionResult.SUCCESS;

		} else
			return super.useOn(pContext);
	}

	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents,
			TooltipFlag tooltipFlag) {
		MutableComponent textEmpty = Component.translatable(Compendium.MOD_ID + ".tooltip.toolbox");
		tooltipComponents.add(textEmpty.withStyle(ChatFormatting.AQUA));
	}
}
