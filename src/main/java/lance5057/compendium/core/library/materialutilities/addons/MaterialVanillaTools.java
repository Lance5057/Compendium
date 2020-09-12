package lance5057.compendium.core.library.materialutilities.addons;

import lance5057.compendium.Reference;
import lance5057.compendium.TCItems;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class MaterialVanillaTools implements MaterialBase {
	
	
	
	public Item sword;
	public Item pickaxe;
	public Item shovel;
	public Item axe;
	public Item hoe;

	public MaterialVanillaTools(MaterialHelper mh)
	{		
		sword = new SwordItem(mh.tier, 3, -2.4f, new Item.Properties().group(TCItems.TCITEMS));
		pickaxe = new PickaxeItem(mh.tier,1, -2.8F, (new Item.Properties()).group(TCItems.TCITEMS));
		shovel = new ShovelItem(mh.tier, 1.5F, -3.0F, (new Item.Properties()).group(TCItems.TCITEMS));
		axe = new AxeItem(mh.tier, 5.0F, -3.0F, (new Item.Properties()).group(TCItems.TCITEMS));
		hoe = new HoeItem(mh.tier, -3.0F, (new Item.Properties()).group(TCItems.TCITEMS));
//		bow;
//		crossbow;
//		shield;
//		hammer;
//		shears;
//		fishingrod;
//		trident;
//		sickle;
		
		sword.setRegistryName(new ResourceLocation(Reference.MOD_ID, mh.name+"sword"));
		pickaxe.setRegistryName(new ResourceLocation(Reference.MOD_ID, mh.name+"pickaxe"));
		shovel.setRegistryName(new ResourceLocation(Reference.MOD_ID, mh.name+"shovel"));
		axe.setRegistryName(new ResourceLocation(Reference.MOD_ID, mh.name+"axe"));
		hoe.setRegistryName(new ResourceLocation(Reference.MOD_ID, mh.name+"hoe"));
		
//		TCItems.ITEMS.add(sword);
//		TCItems.ITEMS.add(pickaxe);
//		TCItems.ITEMS.add(shovel);
//		TCItems.ITEMS.add(axe);
//		TCItems.ITEMS.add(hoe);
	}

	@Override
	public void setupClient(MaterialHelper mat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setupModels(MaterialHelper mat) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void setupWiki(MaterialHelper mat, PrintWriter out) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setupItemTags() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setupBlockTags() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setupRecipes() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setupItemModels(ModelProvider<ItemModelBuilder> mp, ExistingFileHelper fh) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setupBlockModels(BlockStateProvider bsp, ExistingFileHelper fh) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setupEnglishLocalization(LanguageProvider lang) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setupLoot() {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void setup(FMLCommonSetupEvent event) {
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

}
