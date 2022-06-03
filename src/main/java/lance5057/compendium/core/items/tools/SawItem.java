package lance5057.compendium.core.items.tools;

import java.util.Optional;

import lance5057.compendium.core.items.HandedAbilityTool;
import lance5057.compendium.core.library.CompendiumTags;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class SawItem extends HandedAbilityTool {

	public SawItem(Tier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder) {
		super(attackDamageIn, attackSpeedIn, tier, CompendiumTags.MINEABLE_WITH_SAW, builder);
		// TODO Auto-generated constructor stub 
	}

	@Override
	protected InteractionResult mainInteractionHandAbility(UseOnContext context) {
		Level level = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		Player player = context.getPlayer();
		BlockState blockstate = level.getBlockState(blockpos);
		Optional<BlockState> optional = Optional.ofNullable(
				blockstate.getToolModifiedState(context, net.minecraftforge.common.ToolActions.AXE_STRIP, false));
		Optional<BlockState> optional1 = optional.isPresent() ? Optional.empty()
				: Optional.ofNullable(blockstate.getToolModifiedState(context,
						net.minecraftforge.common.ToolActions.AXE_SCRAPE, false));
		Optional<BlockState> optional2 = optional.isPresent() || optional1.isPresent() ? Optional.empty()
				: Optional.ofNullable(blockstate.getToolModifiedState(context,
						net.minecraftforge.common.ToolActions.AXE_WAX_OFF, false));
		ItemStack itemstack = context.getItemInHand();
		Optional<BlockState> optional3 = Optional.empty();
		if (optional.isPresent()) {
			level.playSound(player, blockpos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
			optional3 = optional;
		} else if (optional1.isPresent()) {
			level.playSound(player, blockpos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
			level.levelEvent(player, 3005, blockpos, 0);
			optional3 = optional1;
		} else if (optional2.isPresent()) {
			level.playSound(player, blockpos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
			level.levelEvent(player, 3004, blockpos, 0);
			optional3 = optional2;
		}

		if (optional3.isPresent()) {
			if (player instanceof ServerPlayer) {
				CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, blockpos, itemstack);
			}

			level.setBlock(blockpos, optional3.get(), 11);
			if (player != null) {
				itemstack.hurtAndBreak(1, player, (p_150686_) -> {
					p_150686_.broadcastBreakEvent(context.getHand());
				});
			}

			return InteractionResult.sidedSuccess(level.isClientSide);
		} else {
			return InteractionResult.PASS;
		}
	}

	@Override
	protected InteractionResult offInteractionHandAbility(UseOnContext context) {
		// TODO Chop down the tree!
		return InteractionResult.PASS;
	}

}
