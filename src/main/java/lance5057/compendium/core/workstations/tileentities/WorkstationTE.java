package lance5057.compendium.core.workstations.tileentities;

import java.util.Optional;

import lance5057.compendium.CompendiumTileEntities;
import lance5057.compendium.core.workstations.recipes.CraftingAnvilRecipe;
import lance5057.compendium.core.workstations.tileentities.bases.MultiToolRecipeStation;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.IItemHandlerModifiable;

public class WorkstationTE extends MultiToolRecipeStation<CraftingAnvilRecipe> implements MenuProvider {

	WorkstationTE master;
	
	public WorkstationTE(BlockPos pos, BlockState state) {
		super(27, 5, 5, CompendiumTileEntities.CRAFTING_ANVIL_TE.get(), pos, state);
	}

	@Override
	public AbstractContainerMenu createMenu(int p_39954_, Inventory p_39955_, Player p_39956_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Component getDisplayName() {
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

}
