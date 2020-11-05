package lance5057.compendium.core.library.materialutilities.addons;

import lance5057.compendium.TCItems;
import lance5057.compendium.core.items.HammerItem;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class MaterialExtraTools implements MaterialBase {

//	public Item bow;
//	public Item crossbow;
//	public Item shield;
	public RegistryObject<Item> HAMMER;
//	public Item shears;
//	public Item fishingrod;
//	public Item trident;
//	public Item sickle;
	
	public MaterialExtraTools(MaterialHelper mh) {
		HAMMER = mh.ITEMS.register(mh.name + "hammer",
				() -> new HammerItem(mh.tier, 2, -3.4f, new Item.Properties().group(TCItems.TCITEMS)));
	}
	
	@Override
	public void setupClient(MaterialHelper mat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setupModels(MaterialHelper mat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setupItems(MaterialHelper mat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setupBlocks(MaterialHelper mat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setup(FMLCommonSetupEvent event) {
		// TODO Auto-generated method stub
		
	}

}
