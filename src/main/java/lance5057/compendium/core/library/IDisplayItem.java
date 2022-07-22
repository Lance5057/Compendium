package lance5057.compendium.core.library;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public interface IDisplayItem {
	public Vec3 getDisplayPosition(ItemStack item);
	public Vec3 getDisplayRotation(ItemStack item);
	public Vec3 getDisplayScale(ItemStack item);
}
