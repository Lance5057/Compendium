package com.lance5057.compendium;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.lance5057.compendium.components.block.StyleBlockComponent;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.network.ChecksumVerificationPacket;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(modid = Compendium.MOD_ID, value = Dist.CLIENT)
public class CompendiumEvents {

	@SubscribeEvent
	public static void ServerChecksumEvent(OnDatapackSyncEvent event) {
		if (event.getPlayer() != null)
			try {
				event.getPlayer().connection
						.send(new ChecksumVerificationPacket(CompendiumIndex.generateChecksum().toString()));
			} catch (NoSuchAlgorithmException | IOException e) {
				e.printStackTrace();
			}
	}

	@SubscribeEvent
	public static void registerComponentToolTips(ItemTooltipEvent event) {
		ItemStack stack = event.getItemStack();
		TooltipContext ctx = event.getContext();
		List<Component> tooltip = event.getToolTip();
		TooltipFlag flag = event.getFlags();

		TooltipProvider tooltipProvider = stack.get(CompendiumComponents.MULTI_MATERIAL);

		if (tooltipProvider != null) {
			tooltipProvider.addToTooltip(ctx, i -> {
				tooltip.add(i);
			}, flag);
		}

		StyleBlockComponent tooltipProvider1 = stack.get(CompendiumComponents.STYLE.get());

		if (tooltipProvider1 != null) {
			tooltipProvider1.addToTooltip(stack, ctx, i -> {
				tooltip.add(i);
			}, flag);
		}
	}
}
