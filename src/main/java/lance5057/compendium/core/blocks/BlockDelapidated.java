package lance5057.compendium.core.blocks;

import java.util.List;

import lance5057.compendium.Reference;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

public class BlockDelapidated extends Block implements IPryable {

    String material;

    public BlockDelapidated(String mat, Properties properties) {
	super(properties);
	this.material = mat;
    }

    @Override
    public void onBreak(Level world, BlockPos pos, LivingEntity entityLiving) {
	if (world instanceof ServerLevel) {
	    ResourceLocation rc = getPrybarLootTable();

	    LootTable loottable = world.getServer().getLootTables().get(rc);
	    LootContext.Builder lootcontext$builder = new LootContext.Builder((ServerLevel) world)
		    .withRandom(world.random).withParameter(LootContextParams.THIS_ENTITY, entityLiving)
		    .withParameter(LootContextParams.ORIGIN, new Vec3(pos.getX(), pos.getY(), pos.getZ()));

	    LootContext ctx = lootcontext$builder.create(LootContextParamSets.GIFT);
	    List<ItemStack> items = loottable.getRandomItems(ctx);

	    items.forEach(i -> {
		Containers.dropItemStack(world, pos.getX(), pos.getY(), pos.getZ(), i);

	    });
	}
    }

    @Override
    public ResourceLocation getPrybarLootTable() {
	// TODO Auto-generated method stub
	return new ResourceLocation(Reference.MOD_ID, "recipes/prybar/" + material + "_machine");
    }

}
