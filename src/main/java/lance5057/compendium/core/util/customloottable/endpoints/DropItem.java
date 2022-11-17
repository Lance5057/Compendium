package lance5057.compendium.core.util.customloottable.endpoints;

import lance5057.compendium.core.util.customloottable.ILoot;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

public class DropItem implements ILoot {

	public final Ingredient item;
	public final int count;

	public DropItem(Ingredient item, int count) {
		this.item = item;
		this.count = count;

	}

	@Override
	public void invoke(Level level, BlockPos workstation, Player player, ItemStack usedItem) {

	}
}
