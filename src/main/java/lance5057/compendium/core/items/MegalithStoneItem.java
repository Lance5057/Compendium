package lance5057.compendium.core.items;

import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;

public class MegalithStoneItem extends Item {

	public MegalithStoneItem(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}

	public InteractionResult onItemUse(UseOnContext context) {
		AABB aabb = new AABB(
				context.getClickedPos().above(1).north(1).east(1).relative(context.getClickedFace(),2),
				context.getClickedPos().below(1).south(1).west(1).relative(context.getClickedFace(),2));
		List<BlockPos> invalid = BlockPos.betweenClosedStream(aabb)
				.filter(b -> context.getLevel().getBlockState(b) != Blocks.AIR.defaultBlockState())
				.collect(Collectors.toList());

		if (invalid.isEmpty()) {
			BlockPos.betweenClosedStream(aabb)
					.forEach(b -> context.getLevel().setBlockAndUpdate(b, Blocks.STONE.defaultBlockState()));
			return InteractionResult.PASS;
		}
		return InteractionResult.FAIL;
	}

}
