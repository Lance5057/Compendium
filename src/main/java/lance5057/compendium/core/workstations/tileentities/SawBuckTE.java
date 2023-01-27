package lance5057.compendium.core.workstations.tileentities;

import java.util.Optional;

import lance5057.compendium.core.workstations.recipes.CraftingAnvilRecipe;
import lance5057.compendium.core.workstations.tileentities.bases.MultiToolRecipeStation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;

public class SawBuckTE extends MultiToolRecipeStation<CraftingAnvilRecipe>
{

	public SawBuckTE(int slots, int width, int height, BlockEntityType<?> tileEntityTypeIn, BlockPos pos,
			BlockState state) {
		super(slots, width, height, tileEntityTypeIn, pos, state);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected <T> LazyOptional<T> getExtraCapability(Capability<T> cap, Direction side) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Optional<CraftingAnvilRecipe> matchRecipe() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected IItemHandlerModifiable createInteractionHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addParticle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finishRecipe(Player Player, CraftingAnvilRecipe recipe) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void readExtraNBT(CompoundTag nbt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected CompoundTag writeExtraNBT(CompoundTag tag) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
