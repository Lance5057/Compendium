package lance5057.compendium.core.util.customloottable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface ILoot {
	public abstract void invoke(Level level, BlockPos workstation, Player player, ItemStack usedItem);
	
}
