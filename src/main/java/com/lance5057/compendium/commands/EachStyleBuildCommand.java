package com.lance5057.compendium.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.commands.arguments.item.ItemInput;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public class EachStyleBuildCommand {
	public static LiteralArgumentBuilder<CommandSourceStack> register(CommandBuildContext context) {
		return Commands.literal("buildEachStyle").requires(cs -> cs.hasPermission(2))
				.then(Commands.argument("item", ItemArgument.item(context)).executes(
						p_137784_ -> EachStyleBuildCommand.run(context, ItemArgument.getItem(p_137784_, "item"))));
	}

	private static int run(CommandBuildContext ctx, ItemInput item) throws CommandSyntaxException {
		Item i = item.getItem();
		if (i instanceof BlockItem bi) {
			
		} else
			throw new SimpleCommandExceptionType(Component.translatable("commands.compendium.not_block_item")).create();
		return Command.SINGLE_SUCCESS;
	}
}
