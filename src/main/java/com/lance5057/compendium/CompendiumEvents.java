package com.lance5057.compendium;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.lance5057.compendium.commands.CompendiumCommands;
import com.lance5057.compendium.components.block.IndexEntryComponent;
import com.lance5057.compendium.components.block.MultiMaterialBlockComponent;
import com.lance5057.compendium.components.block.StyleBlockComponent;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.network.ChecksumVerificationPacket;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(modid = Compendium.MOD_ID)
public class CompendiumEvents {

	@SubscribeEvent
	public static void modifyComponents(ModifyDefaultComponentsEvent event) {
		CompendiumIndex.index.forEach(i -> {
			if (i instanceof _MaterialBase mb)
				mb.attachComponents(event);
		});

		event.modify(Items.ACACIA_SLAB, builder -> builder.set(CompendiumComponents.INDEX.get(),
				new IndexEntryComponent(MATERIAL_TYPES.WOOD, "acacia")));
		event.modify(Items.BIRCH_SLAB, builder -> builder.set(CompendiumComponents.INDEX.get(),
				new IndexEntryComponent(MATERIAL_TYPES.WOOD, "birch")));
		event.modify(Items.CHERRY_SLAB, builder -> builder.set(CompendiumComponents.INDEX.get(),
				new IndexEntryComponent(MATERIAL_TYPES.WOOD, "cherry")));
		event.modify(Items.CRIMSON_SLAB, builder -> builder.set(CompendiumComponents.INDEX.get(),
				new IndexEntryComponent(MATERIAL_TYPES.WOOD, "crimson")));
		event.modify(Items.DARK_OAK_SLAB, builder -> builder.set(CompendiumComponents.INDEX.get(),
				new IndexEntryComponent(MATERIAL_TYPES.WOOD, "dark_oak")));
		event.modify(Items.JUNGLE_SLAB, builder -> builder.set(CompendiumComponents.INDEX.get(),
				new IndexEntryComponent(MATERIAL_TYPES.WOOD, "jungle")));
		event.modify(Items.MANGROVE_SLAB, builder -> builder.set(CompendiumComponents.INDEX.get(),
				new IndexEntryComponent(MATERIAL_TYPES.WOOD, "mangrove")));
		event.modify(Items.OAK_SLAB, builder -> builder.set(CompendiumComponents.INDEX.get(),
				new IndexEntryComponent(MATERIAL_TYPES.WOOD, "oak")));
		event.modify(Items.SPRUCE_SLAB, builder -> builder.set(CompendiumComponents.INDEX.get(),
				new IndexEntryComponent(MATERIAL_TYPES.WOOD, "spruce")));
		event.modify(Items.WARPED_SLAB, builder -> builder.set(CompendiumComponents.INDEX.get(),
				new IndexEntryComponent(MATERIAL_TYPES.WOOD, "warped")));

		event.modify(Items.ACACIA_STAIRS, builder -> builder.set(CompendiumComponents.INDEX.get(),
				new IndexEntryComponent(MATERIAL_TYPES.WOOD, "acacia")));
		event.modify(Items.BIRCH_STAIRS, builder -> builder.set(CompendiumComponents.INDEX.get(),
				new IndexEntryComponent(MATERIAL_TYPES.WOOD, "birch")));
		event.modify(Items.CHERRY_STAIRS, builder -> builder.set(CompendiumComponents.INDEX.get(),
				new IndexEntryComponent(MATERIAL_TYPES.WOOD, "cherry")));
		event.modify(Items.CRIMSON_STAIRS, builder -> builder.set(CompendiumComponents.INDEX.get(),
				new IndexEntryComponent(MATERIAL_TYPES.WOOD, "crimson")));
		event.modify(Items.DARK_OAK_STAIRS, builder -> builder.set(CompendiumComponents.INDEX.get(),
				new IndexEntryComponent(MATERIAL_TYPES.WOOD, "dark_oak")));
		event.modify(Items.JUNGLE_STAIRS, builder -> builder.set(CompendiumComponents.INDEX.get(),
				new IndexEntryComponent(MATERIAL_TYPES.WOOD, "jungle")));
		event.modify(Items.MANGROVE_STAIRS, builder -> builder.set(CompendiumComponents.INDEX.get(),
				new IndexEntryComponent(MATERIAL_TYPES.WOOD, "mangrove")));
		event.modify(Items.OAK_STAIRS, builder -> builder.set(CompendiumComponents.INDEX.get(),
				new IndexEntryComponent(MATERIAL_TYPES.WOOD, "oak")));
		event.modify(Items.SPRUCE_STAIRS, builder -> builder.set(CompendiumComponents.INDEX.get(),
				new IndexEntryComponent(MATERIAL_TYPES.WOOD, "spruce")));
		event.modify(Items.WARPED_STAIRS, builder -> builder.set(CompendiumComponents.INDEX.get(),
				new IndexEntryComponent(MATERIAL_TYPES.WOOD, "warped")));

	}

	@SubscribeEvent
	public static void registerCommands(RegisterCommandsEvent event) {
		CompendiumCommands.register(event.getDispatcher(), event.getBuildContext());
	}
}
