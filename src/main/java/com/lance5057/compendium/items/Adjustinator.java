package com.lance5057.compendium.items;

import com.lance5057.compendium.blocks.entities.MultiMaterialBlockEntity;
import com.lance5057.compendium.gui.AdjustinatorMenu;
import com.lance5057.compendium.gui.AdjustinatorMenu.MODES;
import com.lance5057.compendium.workstations._bases.blocks.StationGuiless;

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
			if (pContext.getLevel().getBlockState(pContext.getClickedPos()).getBlock() instanceof StationGuiless) {
				if (!pContext.getLevel().isClientSide())
					pContext.getPlayer().openMenu(new SimpleMenuProvider((p_57074_, p_57075_, p_57076_) -> {
						return new AdjustinatorMenu(p_57074_, p_57075_,
								ContainerLevelAccess.create(pContext.getLevel(), pContext.getClickedPos()),
								pContext.getClickedPos(), MODES.STATION);
					}, CommonComponents.EMPTY));
				return InteractionResult.PASS;
			}
			if (pContext.getLevel().getBlockEntity(pContext.getClickedPos()) instanceof MultiMaterialBlockEntity) {
				if (!pContext.getLevel().isClientSide())
					pContext.getPlayer().openMenu(new SimpleMenuProvider((p_57074_, p_57075_, p_57076_) -> {
						return new AdjustinatorMenu(p_57074_, p_57075_,
								ContainerLevelAccess.create(pContext.getLevel(), pContext.getClickedPos()),
								pContext.getClickedPos(), MODES.MULTIMATERIAL);
					}, CommonComponents.EMPTY));
				return InteractionResult.PASS;
			}
		} else
			return super.useOn(pContext);
		return InteractionResult.CONSUME;
	}
}
