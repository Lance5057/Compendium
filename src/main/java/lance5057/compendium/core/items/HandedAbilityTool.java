package lance5057.compendium.core.items;

import java.util.Set;

import com.google.common.collect.ImmutableMap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ToolItem;
import net.minecraft.state.Property;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;

public abstract class HandedAbilityTool extends ToolItem {

    public HandedAbilityTool(float attackDamageIn, float attackSpeedIn, IItemTier tier, Set<Block> effectiveBlocksIn,
	    Properties builderIn) {
	super(attackDamageIn, attackSpeedIn, tier, effectiveBlocksIn, builderIn);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {

	if (context.getHand() == Hand.MAIN_HAND)
	    mainHandAbility(context);
	else if (context.getHand() == Hand.OFF_HAND)
	    offHandAbility(context);

	return ActionResultType.SUCCESS;
    }

    protected abstract ActionResultType mainHandAbility(ItemUseContext context);

    protected abstract ActionResultType offHandAbility(ItemUseContext context);

    public ImmutableMap<Property<?>, Comparable<?>> getCommonProperties(BlockState oldState,
	    BlockState newState) {
	ImmutableMap.Builder<Property<?>, Comparable<?>> values = ImmutableMap.builder();
	for (Property<?> property : oldState.getProperties()) {
	    if (newState.getProperties().contains(property)) {
		values.put(property, oldState.get(property));
	    }
	}
	return values.build();
    }
}
