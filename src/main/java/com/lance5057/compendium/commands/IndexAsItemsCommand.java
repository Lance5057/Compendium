package com.lance5057.compendium.commands;

import java.util.ArrayList;
import java.util.List;

import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.components.block.MultiMaterialBlockComponent;
import com.lance5057.compendium.components.block.StyleBlockComponent;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.multimaterial.MultiMaterialType;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.styleblock.IStyleBlock;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.commands.arguments.item.ItemInput;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class IndexAsItemsCommand {
	public static LiteralArgumentBuilder<CommandSourceStack> register(CommandBuildContext context) {
		return Commands.literal("indexAsItems").requires(cs -> cs.hasPermission(2))
				.then(Commands.argument("entity", EntityArgument.entity())
						.then(Commands.argument("item", ItemArgument.item(context))
								.executes(p_137784_ -> IndexAsItemsCommand.run(context,
										ItemArgument.getItem(p_137784_, "item"),
										EntityArgument.getEntity(p_137784_, "entity")))));
	}

	private static int run(CommandBuildContext ctx, ItemInput item, Entity entity) throws CommandSyntaxException {
		BlockPos pos = entity.blockPosition();
		Level level = entity.level();
		Item it = item.getItem();

		if (it instanceof BlockItem bi) {
			buildFrame(pos, level, bi);

		} else
			throw new SimpleCommandExceptionType(Component.translatable("commands.compendium.not_block_item")).create();

		return Command.SINGLE_SUCCESS;
	}

	private static void buildFrame(BlockPos pos, Level level, BlockItem it) {
		if (it.getBlock() instanceof IStyleBlock sb) {
			ItemStack stack = it.getDefaultInstance();

			StyleBlockComponent sbc = stack.get(CompendiumComponents.STYLE);
			MultiMaterialBlockComponent mmc = stack.get(CompendiumComponents.MULTI_MATERIAL);

			List<MultiMaterialType> mm = null;
			if (mmc != null) {
				mm = new ArrayList<MultiMaterialType>(mmc.getTypes());
				for (MultiMaterialType m : mm) {
					m.setCurrentMaterial(CompendiumIndex.getDefaultMaterialFromType(m.getType().getFirst()));
				}
			}

			List<Integer> styles = sbc.styles();
			StyleData[] data = sb.getStyleData();

			BlockPos nextPos = pos;

			for (int d = 0; d < data.length; d++) {
				for (int i = 0; i < data[d].getTypes().size(); i++) {
					BlockPos nPos = new BlockPos(pos.getX() + (d * 2), pos.getY(), pos.getZ() + (i * 2));

					level.setBlock(nPos, Blocks.WHITE_TERRACOTTA.defaultBlockState(), Block.UPDATE_ALL);
					ItemFrame frame = new ItemFrame(level, nPos.above(), Direction.UP);
					
					List<Integer> newStyles = new ArrayList<Integer>(styles);
					newStyles.set(d, i);

					stack.set(CompendiumComponents.STYLE, new StyleBlockComponent(newStyles));
					stack.set(CompendiumComponents.MULTI_MATERIAL, new MultiMaterialBlockComponent(mm));

					frame.setItem(stack);
					level.addFreshEntity(frame);
				}
			}
		}
	}
}
