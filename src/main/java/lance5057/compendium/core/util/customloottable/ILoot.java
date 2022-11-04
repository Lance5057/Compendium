package lance5057.compendium.core.util.customloottable;

import java.util.List;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

public interface ILoot {
	public abstract List<ItemStack> invoke(Level level, Player player, ItemStack usedItem);
}
