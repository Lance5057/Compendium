package lance5057.compendium.core.items;

import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class MegalithStoneItem extends Item {

	public MegalithStoneItem(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}

	public ActionResultType onItemUse(ItemUseContext context) {
		AxisAlignedBB aabb = new AxisAlignedBB(
				context.getPos().up(1).north(1).east(1).offset(context.getFace(),2),
				context.getPos().down(1).south(1).west(1).offset(context.getFace(),2));
		List<BlockPos> invalid = BlockPos.getAllInBox(aabb)
				.filter(b -> context.getWorld().getBlockState(b) != Blocks.AIR.getDefaultState())
				.collect(Collectors.toList());

		if (invalid.isEmpty()) {
			BlockPos.getAllInBox(aabb)
					.forEach(b -> context.getWorld().setBlockState(b, Blocks.STONE.getDefaultState()));
			return ActionResultType.PASS;
		}
		return ActionResultType.FAIL;
	}

}
