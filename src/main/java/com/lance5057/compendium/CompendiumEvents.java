package com.lance5057.compendium;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.lance5057.compendium.commands.CompendiumCommands;
import com.lance5057.compendium.components.block.IndexEntryComponent;
import com.lance5057.compendium.components.block.MultiMaterialBlockComponent;
import com.lance5057.compendium.components.block.StyleBlockComponent;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.network.ChecksumVerificationPacket;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(modid = Compendium.MOD_ID, value = Dist.CLIENT)
public class CompendiumEvents {

	@SubscribeEvent
	public static void modifyComponents(ModifyDefaultComponentsEvent event) {
		CompendiumIndex.index.forEach(i -> {
			if (i instanceof _MaterialBase mb)
				mb.attachComponents(event);
		});
	}

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

		MultiMaterialBlockComponent tooltipProvider = stack.get(CompendiumComponents.MULTI_MATERIAL.get());

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

		IndexEntryComponent tooltipProvider2 = stack.get(CompendiumComponents.INDEX.get());

		if (tooltipProvider2 != null) {
			tooltipProvider2.addToTooltip(stack, ctx, i -> {
				tooltip.add(i);
			}, flag);
		}
	}

	@SubscribeEvent
	public static void registerCommands(RegisterCommandsEvent event) {
		CompendiumCommands.register(event.getDispatcher(), event.getBuildContext());
	}
}
