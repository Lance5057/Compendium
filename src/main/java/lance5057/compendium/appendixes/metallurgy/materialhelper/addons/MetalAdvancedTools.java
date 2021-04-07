package lance5057.compendium.appendixes.metallurgy.materialhelper.addons;

import javax.annotation.Nullable;

import lance5057.compendium.CompendiumItems;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.core.items.tools.HammerItem;
import lance5057.compendium.core.items.tools.SawItem;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class MetalAdvancedTools implements MaterialBase {

    public RegistryObject<Item> BOW;
//	public Item crossbow;
//	public Item shield;
    public RegistryObject<Item> HAMMER;
    public RegistryObject<Item> SAW;
//	public Item shears;
//	public Item fishingrod;
//	public Item trident;
//	public Item sickle;

    public MetalAdvancedTools(MetallurgyMaterialHelper mh) {
	BOW = mh.ITEMS.register(mh.name + "bow",
		() -> new BowItem(new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	HAMMER = mh.ITEMS.register(mh.name + "hammer",
		() -> new HammerItem(mh.tier, 5, -3.4f, new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	SAW = mh.ITEMS.register(mh.name + "saw",
		() -> new SawItem(mh.tier, 6, -3.0f, new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
    }

    @Override
    public void setupClient(MaterialHelperBase mat) {
	// TODO Auto-generated method stub
	ItemModelsProperties.registerProperty(BOW.get(), new ResourceLocation("pull"), new IItemPropertyGetter() {

	    @Override
	    public float call(ItemStack arg0, ClientWorld arg1, LivingEntity arg2) {
		if (arg2 == null) {
		    return 0.0F;
		} else {
		    return !(arg2.getActiveItemStack().getItem() instanceof BowItem) ? 0.0F
			    : (float) (arg0.getUseDuration() - arg2.getItemInUseCount()) / 20.0F;
		}
	    }

	});
	ItemModelsProperties.registerProperty(BOW.get(), new ResourceLocation("pulling"), new IItemPropertyGetter() {

	    @Override
	    public float call(ItemStack stack, @Nullable ClientWorld worldIn, @Nullable LivingEntity entityIn) {
		return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F
			: 0.0F;
	    }
	});
    }

    @Override
    public void setup(FMLCommonSetupEvent event) {
	// TODO Auto-generated method stub

    }

}
