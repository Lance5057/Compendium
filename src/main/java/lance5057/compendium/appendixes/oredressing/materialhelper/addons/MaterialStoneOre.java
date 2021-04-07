package lance5057.compendium.appendixes.oredressing.materialhelper.addons;

import lance5057.compendium.appendixes.oredressing.materialhelper.OreDressingMaterialHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome.Category;
import net.minecraftforge.common.ToolType;

public class MaterialStoneOre extends MaterialOre {

    public MaterialStoneOre(OreDressingMaterialHelper mh, float hardness, int level, ToolType tool, float resistance,
	    int ymax, int ymin, int veinSize, int veinChance, Category biomeCategory) {
	super(mh, "", hardness, level, tool, resistance, ymax, ymin, veinSize, veinChance, biomeCategory);
    }

    @Override
    public Block replace(OreDressingMaterialHelper mh) {
	if(mh.hasSparseOre())
	    return mh.getSparseOre().ORE.get();
	return Blocks.STONE;
    }

}
