package lance5057.compendium.core.util.customloottable.conditions;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class Condition {
	public void invoke()
	{
		if(test())
			isTrue();
		else
			isFalse();
	}

	protected abstract void isFalse();

	protected abstract void isTrue();

	protected abstract boolean test(Level level, Player player, ItemStack usedItem);
}
