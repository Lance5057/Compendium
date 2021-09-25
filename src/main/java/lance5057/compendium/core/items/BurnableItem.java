package lance5057.compendium.core.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BurnableItem extends Item {

    int burntime;
    
    public BurnableItem(Properties properties, int burntime) {
	super(properties);
	this.burntime = burntime;
    }
    
    @Override
    public int getBurnTime(ItemStack itemStack)
    {
        return burntime;
    }
}
