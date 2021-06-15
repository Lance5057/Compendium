package lance5057.compendium.core.library.materialutilities;

import java.util.ArrayList;
import java.util.List;

import lance5057.compendium.Reference;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.block.Block;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class MaterialHelperBase {
    public String name;
    public String parentMod;
    public IItemTier tier;

    public final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);
    public final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);

    public final List<MaterialBase> addons = new ArrayList<MaterialBase>();
    
    public abstract void setup();
}
