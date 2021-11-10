package lance5057.compendium.core.blocks;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IPryable {
    public void onBreak(World world, BlockPos pos, LivingEntity entityLiving);
    
    public ResourceLocation getPrybarLootTable();
}
