package lance5057.compendium.core.library.materialutilities.addons;

import java.io.PrintWriter;

import lance5057.compendium.Reference;
import lance5057.compendium.TCItems;
import lance5057.compendium.core.library.TCItemTier;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class MaterialTools implements MaterialBase {
	
	public TCItemTier tier;
	
	public Item sword;
	public Item pickaxe;
	public Item shovel;
	public Item axe;
	public Item hoe;
	public Item bow;
	public Item crossbow;
	public Item shield;
	public Item hammer;
	public Item shears;
	public Item fishingrod;
	public Item trident;
	public Item sickle;
	
	
	public MaterialTools(String matName, TCItemTier tier)
	{
		this.tier = tier;
		
		sword = new SwordItem(tier, 3, -2.4f, new Item.Properties().group(TCItems.TCITEMS));
		pickaxe = new PickaxeItem(tier,1, -2.8F, (new Item.Properties()).group(TCItems.TCITEMS));
		shovel = new ShovelItem(tier, 1.5F, -3.0F, (new Item.Properties()).group(TCItems.TCITEMS));
		axe = new AxeItem(tier, 5.0F, -3.0F, (new Item.Properties()).group(TCItems.TCITEMS));
		hoe = new HoeItem(tier, -3.0F, (new Item.Properties()).group(TCItems.TCITEMS));
//		bow;
//		crossbow;
//		shield;
//		hammer;
//		shears;
//		fishingrod;
//		trident;
//		sickle;
		
		sword.setRegistryName(new ResourceLocation(Reference.MOD_ID, matName+"sword"));
		pickaxe.setRegistryName(new ResourceLocation(Reference.MOD_ID, matName+"pickaxe"));
		shovel.setRegistryName(new ResourceLocation(Reference.MOD_ID, matName+"shovel"));
		axe.setRegistryName(new ResourceLocation(Reference.MOD_ID, matName+"axe"));
		hoe.setRegistryName(new ResourceLocation(Reference.MOD_ID, matName+"hoe"));
		
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

	@Override
	public void setupWiki(MaterialHelper mat, PrintWriter out) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setupItemTags() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setupBlockTags() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setupRecipes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setupItemModels(ModelProvider<ItemModelBuilder> mp, ExistingFileHelper fh) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setupBlockModels(BlockStateProvider bsp, ExistingFileHelper fh) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setupEnglishLocalization(LanguageProvider lang) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setupLoot() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setup(FMLCommonSetupEvent event) {
		// TODO Auto-generated method stub
		
	}

}
