package lance5057.compendium.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class ComponentBarDoor extends Block {

	public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
	public static final BooleanProperty ROTATED = BooleanProperty.create("rotated");
	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

	public ComponentBarDoor(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(OPEN, Boolean.valueOf(false))
				.with(ROTATED, Boolean.valueOf(false)).with(POWERED, Boolean.valueOf(false)));
	}

	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		switch (type) {
		case LAND:
			return state.get(OPEN);
		case WATER:
			return false;
		case AIR:
			return state.get(OPEN);
		default:
			return false;
		}
	}

	private int getCloseSound() {
		return this.material == Material.IRON ? 1011 : 1012;
	}

	private int getOpenSound() {
		return this.material == Material.IRON ? 1005 : 1006;
	}
}
