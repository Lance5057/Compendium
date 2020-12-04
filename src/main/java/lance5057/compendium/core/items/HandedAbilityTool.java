package lance5057.compendium.core.items;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ToolItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;

public abstract class HandedAbilityTool extends ToolItem {

	public HandedAbilityTool(float attackDamageIn, float attackSpeedIn, IItemTier tier, Set<Block> effectiveBlocksIn,
			Properties builderIn) {
		super(attackDamageIn, attackSpeedIn, tier, effectiveBlocksIn, builderIn);
	}
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		
		if(context.getHand() == Hand.MAIN_HAND)
			mainHandAbility(context);
		else if(context.getHand() == Hand.OFF_HAND)
			offHandAbility(context);
			
		return ActionResultType.PASS;
	}

	protected abstract ActionResultType mainHandAbility(ItemUseContext context);
	
	protected abstract ActionResultType offHandAbility(ItemUseContext context);
}
