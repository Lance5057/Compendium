package lance5057.compendium.core.util.customloottable.conditions;

import java.util.List;

import lance5057.compendium.core.util.customloottable.ILoot;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class ConditionBase implements ILoot {
	List<ILoot> isTrueLoot;
	List<ILoot> isFalseLoot;

	public void invoke(Level level, BlockPos workstation, Player player, ItemStack usedItem) {
		if (test(level, workstation, player, usedItem))
			isTrue(level, workstation, player, usedItem);
		else
			isFalse(level, workstation, player, usedItem);
	}

	protected void isFalse(Level level, BlockPos workstation, Player player, ItemStack usedItem) {
		for (ILoot l : isFalseLoot)
			l.invoke(level, workstation, player, usedItem);
	}

	protected void isTrue(Level level, BlockPos workstation, Player player, ItemStack usedItem) {
		for (ILoot l : isTrueLoot)
			l.invoke(level, workstation, player, usedItem);
	}

	protected abstract boolean test(Level level, BlockPos workstation, Player player, ItemStack usedItem);

}
