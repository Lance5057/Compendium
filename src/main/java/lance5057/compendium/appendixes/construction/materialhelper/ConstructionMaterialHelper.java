package lance5057.compendium.appendixes.construction.materialhelper;

import lance5057.compendium.Reference;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionMaterialBasic;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionMaterialDungeon;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionMaterialShingles;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ConstructionMaterialHelper extends MaterialHelperBase {
    public static final ItemGroup GROUP_METAL = new ItemGroup("compendium.materials.wood") {
	@Override
	public ItemStack createIcon() {
	    return new ItemStack(Items.BRICK);
	}
    };

    private ConstructionMaterialBasic basic;
    private ConstructionMaterialDungeon dungeon;
    private ConstructionMaterialShingles shingles;

    public ConstructionMaterialHelper(String name) {
	this(name, Reference.MOD_ID);
    }

    public ConstructionMaterialHelper(String name, String parentMod) {
	this.name = name;
	this.parentMod = parentMod;

	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
	ITEMS.register(modEventBus);
	BLOCKS.register(modEventBus);
    }

    @OnlyIn(Dist.CLIENT)
    public void client(FMLClientSetupEvent event) {
	basic.setupClient(this);
	dungeon.setupClient(this);
    }

    // Base
    public ConstructionMaterialHelper withBase() {
	basic = new ConstructionMaterialBasic(this);
	return this;
    }

    public boolean hasBase() {
	return basic != null;
    }

    public ConstructionMaterialBasic getBase() {
	return basic;
    }

    // Dungeon
    public ConstructionMaterialHelper withDungeon() {
	dungeon = new ConstructionMaterialDungeon(this);
	return this;
    }

    public boolean hasDungeon() {
	return dungeon != null;
    }

    public ConstructionMaterialDungeon getDungeon() {
	return dungeon;
    }

    // Shingles
    public ConstructionMaterialHelper withShingles() {
	shingles = new ConstructionMaterialShingles(this);
	return this;
    }

    public boolean hasShingles() {
	return shingles != null;
    }

    public ConstructionMaterialShingles getShingles() {
	return shingles;
    }
}
