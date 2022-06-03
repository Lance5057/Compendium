package lance5057.compendium.core.items;

import com.google.common.collect.ImmutableMap;

import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

public abstract class HandedAbilityTool extends DiggerItem {

	public HandedAbilityTool(float attackDamageIn, float attackSpeedIn, Tier tier, TagKey<Block> effectiveBlocksIn,
			Properties builderIn) {
		super(attackDamageIn, attackSpeedIn, tier, effectiveBlocksIn, builderIn);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {

		if (context.getHand() == InteractionHand.MAIN_HAND)
			mainInteractionHandAbility(context);
		else if (context.getHand() == InteractionHand.OFF_HAND)
			offInteractionHandAbility(context);

		return InteractionResult.SUCCESS;
	}

	protected abstract InteractionResult mainInteractionHandAbility(UseOnContext context);

	protected abstract InteractionResult offInteractionHandAbility(UseOnContext context);

	public ImmutableMap<Property<?>, Comparable<?>> getCommonProperties(BlockState oldState, BlockState newState) {
		ImmutableMap.Builder<Property<?>, Comparable<?>> values = ImmutableMap.builder();
		for (Property<?> property : oldState.getProperties()) {
			if (newState.getProperties().contains(property)) {
				values.put(property, oldState.getValue(property));
			}
		}
		return values.build();
	}
}
