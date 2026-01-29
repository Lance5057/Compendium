package com.lance5057.compendium.commands;

import java.util.ArrayList;
import java.util.List;

import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.blocks.entities.SimpleStyleBlockEntity;
import com.lance5057.compendium.components.block.StyleBlockComponent;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.IIndexEntry;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.styleblock.IStyleBlock;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredHolder;

public class EntireIndexBuildCommand {
	public static LiteralArgumentBuilder<CommandSourceStack> register(CommandBuildContext context) {
		return Commands.literal("buildEntireIndex").requires(cs -> cs.hasPermission(2))
				.then(Commands.argument("entity", EntityArgument.entity()).executes(p_137784_ -> EntireIndexBuildCommand
						.run(context, EntityArgument.getEntity(p_137784_, "entity"))));
	}

	private static int run(CommandBuildContext ctx, Entity entity) throws CommandSyntaxException {
		Level level = entity.level();
		int x = entity.getBlockX();
		for (IIndexEntry ie : CompendiumIndex.index) {
			if (ie instanceof _MaterialBase mb) {

				for (DeferredHolder<Block, ? extends Block> b : mb.BLOCKS.getEntries()) {
					if (b != null && b.get() != null) {
						if (b.get() instanceof IStyleBlock sb) {
							ItemStack stack = b.get().asItem().getDefaultInstance();
							StyleBlockComponent sbc = stack.get(CompendiumComponents.STYLE);

							List<Integer> styles = sbc.styles();
							StyleData[] data = sb.getStyleData();

							for (int d = 0; d < data.length; d++) {
								for (int i = 0; i < data[d].getTypes().size(); i++) {
									BlockPos nPos = new BlockPos(x, entity.getBlockY(), entity.getBlockZ()+(i * 2));

									level.setBlock(nPos, b.get().defaultBlockState(), Block.UPDATE_ALL);
									SimpleStyleBlockEntity bentity = (SimpleStyleBlockEntity) level
											.getBlockEntity(nPos);

									List<Integer> newStyles = new ArrayList<Integer>(styles);
									newStyles.set(d, i);
									bentity.setCurrentStyles(newStyles);

									BlockState state = level.getBlockState(nPos);
									sb.onStyleChanged(level, nPos, state);
									level.sendBlockUpdated(nPos, state, state, Block.UPDATE_ALL);

								}
							}
						} else {
							BlockPos nPos = new BlockPos(x, entity.getBlockY(), entity.getBlockZ());
							level.setBlock(nPos, b.get().defaultBlockState(), Block.UPDATE_ALL);

						}
						x += 2;
					}

				}

			}
		}
		return Command.SINGLE_SUCCESS;

	}
}
