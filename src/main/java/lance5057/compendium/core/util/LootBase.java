package lance5057.compendium.core.util;

import java.util.List;

import lance5057.compendium.core.util.customloottable.ILoot;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LootBase implements ILoot {
	public final List<ILoot> loot;

	public LootBase(List<ILoot> loot) {
		this.loot = loot;

	}

	public void invoke(Level level, BlockPos workstation, Player player, ItemStack usedItem) {
		for (ILoot l : loot)
			l.invoke(level, workstation, player, usedItem);
	}
}
