package lance5057.compendium.appendixes.oredressing.materialhelper;

import lance5057.compendium.Reference;
import lance5057.compendium.appendixes.oredressing.materialhelper.addons.MaterialDenseOre;
import lance5057.compendium.appendixes.oredressing.materialhelper.addons.MaterialSparseStoneOre;
import lance5057.compendium.appendixes.oredressing.materialhelper.addons.MaterialStoneOre;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import net.minecraft.world.biome.Biome.Category;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class OreDressingMaterialHelper extends MaterialHelperBase {
    public OreDressingMaterialHelper(String name) {
	this(name, Reference.MOD_ID);
    }

    MaterialStoneOre ore;
    private MaterialDenseOre denseore;
    private MaterialSparseStoneOre sparseore;

    public OreDressingMaterialHelper(String name, String parentMod) {
	this.name = name;
	this.parentMod = parentMod;

	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
	ITEMS.register(modEventBus);
	BLOCKS.register(modEventBus);
    }

    public void client(FMLClientSetupEvent event) {

    }

    // Base
    public OreDressingMaterialHelper withOre(float hardness, int level, ToolType tool, float resistance, int ymax,
	    int ymin, int veinSize, int veinChance, Category biomeCategory) {
	ore = new MaterialStoneOre(this, hardness, level, tool, resistance, ymax, ymin, veinSize, veinChance,
		biomeCategory);
	return this;
    }

    public boolean hasOre() {
	return ore != null;
    }

    public MaterialStoneOre getOre() {
	return ore;
    }

    public OreDressingMaterialHelper withDenseOre(float hardness, int level, ToolType tool, float resistance, int ymax,
	    int ymin, int veinSize, int veinChance, Category biomeCategory) {
	denseore = new MaterialDenseOre(this, hardness, level, tool, resistance, ymax, ymin, veinSize, veinChance,
		biomeCategory);
	return this;
    }
    
    public boolean hasDenseOre() {
	return denseore != null;
    }

    public MaterialDenseOre getDenseOre() {
	return denseore;
    }
    
    public OreDressingMaterialHelper withSparseOre(float hardness, int level, ToolType tool, float resistance, int ymax,
	    int ymin, int veinSize, int veinChance, Category biomeCategory) {
	sparseore = new MaterialSparseStoneOre(this, hardness, level, tool, resistance, ymax, ymin, veinSize, veinChance,
		biomeCategory);
	return this;
    }

    public boolean hasSparseOre() {
	return sparseore != null;
    }

    public MaterialSparseStoneOre getSparseOre() {
	return sparseore;
    }
}
