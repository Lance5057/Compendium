package lance5057.compendium.core.util.customloottable.conditions;

import java.util.Map.Entry;

import com.google.gson.JsonObject;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

public class EnchantmentCondition extends Condition {

	ResourceLocation enchantment;
	int power;
	
	public EnchantmentCondition(ResourceLocation e, int p)
	{
		enchantment = e;
		power = p;
	}

	@Override
	protected void isFalse() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isTrue() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean test(Level level, Player player, ItemStack stack) {
		for (Entry<Enchantment, Integer> m : EnchantmentHelper.getEnchantments(stack).entrySet()) {
			if (EnchantmentHelper.getEnchantmentId(m.getKey()) == enchantment && m.getValue() == power)
				return true;
		}
		return false;
	}

	public static EnchantmentCondition read(JsonObject j) {
		String s = j.get("Enchantment").getAsString();
		ResourceLocation rc = new ResourceLocation(s);
		int c = j.get("Power").getAsInt();

		return new EnchantmentCondition(rc, c);
	}

	public static EnchantmentCondition read(FriendlyByteBuf buffer) {
		String e = buffer.readUtf();
		int c = buffer.readVarInt();
		ResourceLocation rc = new ResourceLocation(e);
		return new EnchantmentCondition(rc, c);
	}

	public static void write(EnchantmentCondition r, FriendlyByteBuf buffer) {
		buffer.writeUtf(r.enchantment.toString());
		buffer.writeVarInt(r.power);
	}

	public static JsonObject addProperty(EnchantmentCondition r) {
		JsonObject o = new JsonObject();

		o.addProperty("Enchantment", r.enchantment.toString());
		o.addProperty("Power", r.power);

		return o;
	}
}
