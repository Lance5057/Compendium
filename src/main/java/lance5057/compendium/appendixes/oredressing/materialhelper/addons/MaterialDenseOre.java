package lance5057.compendium.appendixes.oredressing.materialhelper.addons;

import lance5057.compendium.appendixes.oredressing.materialhelper.OreDressingMaterialHelper;
import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome.Category;
import net.minecraftforge.common.ToolType;

public class MaterialDenseOre extends MaterialOre {

    public MaterialDenseOre(OreDressingMaterialHelper mh, float hardness, int level, ToolType tool, float resistance,
	    int ymax, int ymin, int veinSize, int veinChance, Category biomeCategory) {
	super(mh, "dense", hardness, level, tool, resistance, ymax, ymin, veinSize, veinChance, biomeCategory);
	// TODO Auto-generated constructor stub
    }

    @Override
    public Block replace(OreDressingMaterialHelper mh) {
	return mh.getOre().ORE.get();
    }

}
