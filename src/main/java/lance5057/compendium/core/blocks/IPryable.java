package lance5057.compendium.core.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public interface IPryable {
    public void onBreak(Level world, BlockPos pos, LivingEntity entityLiving);
    
    public ResourceLocation getPrybarLootTable();
}
