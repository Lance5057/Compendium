package com.lance5057.compendium.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.tree.LiteralCommandNode;

import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class CompendiumCommands {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher,
			CommandBuildContext commandBuildContext) {
		LiteralArgumentBuilder<CommandSourceStack> builder = Commands.literal("compendium")
				.then(EachStyleBuildCommand.register(commandBuildContext))
				.then(EntireIndexBuildCommand.register(commandBuildContext))
				.then(IndexAsItemsCommand.register(commandBuildContext));

		LiteralCommandNode<CommandSourceStack> node = dispatcher.register(builder);
		dispatcher.register(Commands.literal("comp").executes(CompendiumCommands::run).redirect(node));
	}

	private static int run(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
		throw new SimpleCommandExceptionType(Component.translatable("commands.compendium.usage", ctx.getInput()))
				.create();
	}
}
