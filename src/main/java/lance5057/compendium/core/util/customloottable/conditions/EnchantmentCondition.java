package lance5057.compendium.core.util.customloottable.conditions;

import java.util.Map.Entry;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

public class EnchantmentCondition extends ConditionBase {

	ResourceLocation enchantment;
	int power;

	public EnchantmentCondition(ResourceLocation e, int p) {
		enchantment = e;
		power = p;
	}

	@Override
	protected boolean test(Level level, BlockPos workstation, Player player, ItemStack stack) {
		for (Entry<Enchantment, Integer> m : EnchantmentHelper.getEnchantments(stack).entrySet()) {
			if (EnchantmentHelper.getEnchantmentId(m.getKey()) == enchantment && m.getValue() == power)
				return true;
		}
		return false;
	}
}
