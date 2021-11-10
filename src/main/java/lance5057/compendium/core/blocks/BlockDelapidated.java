package lance5057.compendium.core.blocks;

import java.util.List;

import lance5057.compendium.Reference;
import lance5057.compendium.core.data.builders.loottables.PrybarLoot;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.LootTable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class BlockDelapidated extends Block implements IPryable {

    String material;

    public BlockDelapidated(String mat, Properties properties) {
	super(properties);
	this.material = mat;
    }

    @Override
    public void onBreak(World world, BlockPos pos, LivingEntity entityLiving) {
	if (world instanceof ServerWorld) {
	    ResourceLocation rc = getPrybarLootTable();

	    LootTable loottable = world.getServer().getLootTableManager().getLootTableFromLocation(rc);
	    LootContext.Builder lootcontext$builder = new LootContext.Builder((ServerWorld) world)
		    .withRandom(world.rand).withParameter(LootParameters.THIS_ENTITY, entityLiving)
		    .withParameter(LootParameters.field_237457_g_, new Vector3d(pos.getX(), pos.getY(), pos.getZ()));

	    LootContext ctx = lootcontext$builder.build(LootParameterSets.GIFT);
	    List<ItemStack> items = loottable.generate(ctx);

	    items.forEach(i -> {
		InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), i);

	    });
	}
    }

    @Override
    public ResourceLocation getPrybarLootTable() {
	// TODO Auto-generated method stub
	return new ResourceLocation(Reference.MOD_ID, "recipes/prybar/" + material + "_machine");
    }

}
