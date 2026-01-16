package com.lance5057.compendium.commands;

import java.util.ArrayList;
import java.util.List;

import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.blocks.entities.StyledMultiMaterialBlockEntity;
import com.lance5057.compendium.components.block.MultiMaterialBlockComponent;
import com.lance5057.compendium.components.block.StyleBlockComponent;
import com.lance5057.compendium.index.CompendiumIndex;
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
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class EachStyleBuildCommand {
	public static LiteralArgumentBuilder<CommandSourceStack> register(CommandBuildContext context) {
		return Commands.literal("buildEachStyle").requires(cs -> cs.hasPermission(2))
				.then(Commands.argument("entity", EntityArgument.entity())
						.then(Commands.argument("item", ItemArgument.item(context))
								.executes(p_137784_ -> EachStyleBuildCommand.run(context,
										ItemArgument.getItem(p_137784_, "item"),
										EntityArgument.getEntity(p_137784_, "entity")))));
	}

	private static int run(CommandBuildContext ctx, ItemInput item, Entity entity) throws CommandSyntaxException {
		Item it = item.getItem();
		if (it instanceof BlockItem bi) {
			if (bi.getBlock() instanceof IStyleBlock sb) {
				ItemStack stack = bi.getDefaultInstance();

				StyleBlockComponent sbc = stack.get(CompendiumComponents.STYLE);
				MultiMaterialBlockComponent mmc = stack.get(CompendiumComponents.MULTI_MATERIAL);

				List<Integer> styles = sbc.styles();
				StyleData[] data = sb.getStyleData();

				BlockPos pos = entity.blockPosition();
				Level level = entity.level();

				for (int d = 0; d < data.length; d++) {
					for (int i = 0; i < data[d].getTypes().size(); i++) {
						List<String> mats = CompendiumIndex.getAllMaterialsForType(mmc.types().get(d).getType());

						for (int m = 0; m < mats.size(); m++) {
							BlockPos nPos = new BlockPos(pos.getX() + (d * 2), pos.getY() + (m * 2),
									pos.getZ() + (i * 2));

							level.setBlock(nPos, bi.getBlock().defaultBlockState(), Block.UPDATE_ALL);
							StyledMultiMaterialBlockEntity bentity = (StyledMultiMaterialBlockEntity) level
									.getBlockEntity(nPos);

							List<Integer> newStyles = new ArrayList<Integer>(styles);
							newStyles.set(d, i);
							bentity.setMaterials(mmc.types());
							bentity.setMaterial(d, mats.get(m));
							bentity.setCurrentStyles(newStyles);

							BlockState state = level.getBlockState(nPos);
							sb.onStyleChanged(level, pos, state);
							level.sendBlockUpdated(pos, state, state, Block.UPDATE_ALL);
						}

					}

				}
			}

		} else
			throw new SimpleCommandExceptionType(Component.translatable("commands.compendium.not_block_item")).create();
		return Command.SINGLE_SUCCESS;
	}
}
