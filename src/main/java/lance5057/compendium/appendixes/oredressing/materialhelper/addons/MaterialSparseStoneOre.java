package lance5057.compendium.appendixes.oredressing.materialhelper.addons;

import lance5057.compendium.appendixes.oredressing.materialhelper.OreDressingMaterialHelper;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.feature.template.TagMatchRuleTest;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.ToolType;

public class MaterialSparseStoneOre extends MaterialOre {

    public MaterialSparseStoneOre(OreDressingMaterialHelper mh, float hardness, int level, ToolType tool,
	    float resistance, int ymax, int ymin, int veinSize, int veinChance, Category biomeCategory) {
	super(mh, "sparse", hardness, level, tool, resistance, ymax, ymin, veinSize, veinChance, biomeCategory);
	// TODO Auto-generated constructor stub
    }

    @Override
    public RuleTest replace(OreDressingMaterialHelper mh) {
	// TODO Auto-generated method stub
	return new TagMatchRuleTest(Tags.Blocks.STONE);
    }

}
