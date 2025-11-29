package com.lance5057.compendium.items;

import com.lance5057.compendium.blocks.entities.MultiMaterialBlockEntity;
import com.lance5057.compendium.gui.AdjustinatorMultiMaterialMenu;
import com.lance5057.compendium.gui.AdjustinatorWorkstationMenu;
import com.lance5057.compendium.workstations._bases.blocks.StationBlock;

import net.minecraft.network.chat.CommonComponents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

public class Adjustinator extends Item {

	public Adjustinator(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}

	@Override
	public InteractionResult useOn(UseOnContext pContext) {
		if (pContext.getPlayer().isCrouching()) {
			if (pContext.getLevel().getBlockState(pContext.getClickedPos()).getBlock() instanceof StationBlock sg) {
				if (!pContext.getLevel().isClientSide())
					pContext.getPlayer().openMenu(new SimpleMenuProvider((p_57074_, p_57075_, p_57076_) -> {
						return new AdjustinatorWorkstationMenu(p_57074_, p_57075_,
								ContainerLevelAccess.create(pContext.getLevel(), pContext.getClickedPos()),
								pContext.getClickedPos(), sg);
					}, CommonComponents.EMPTY));
				return InteractionResult.PASS;
			}
			else if (pContext.getLevel().getBlockEntity(pContext.getClickedPos()) instanceof MultiMaterialBlockEntity mmbe) {
				if (!pContext.getLevel().isClientSide())
					pContext.getPlayer().openMenu(new SimpleMenuProvider((p_57074_, p_57075_, p_57076_) -> {
						return new AdjustinatorMultiMaterialMenu(p_57074_, p_57075_,
								ContainerLevelAccess.create(pContext.getLevel(), pContext.getClickedPos()),
								pContext.getClickedPos(), mmbe);
					}, CommonComponents.EMPTY));
				return InteractionResult.PASS;
			}
		} else
			return super.useOn(pContext);
		return InteractionResult.CONSUME;
	}
}
