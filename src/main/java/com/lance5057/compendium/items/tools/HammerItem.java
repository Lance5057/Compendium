package com.lance5057.compendium.items.tools;

import java.util.List;
import java.util.stream.Collectors;

import com.lance5057.compendium.CompendiumItems;
import com.lance5057.compendium.items.HandedAbilityTool;
import com.lance5057.compendium.items.tools.api.IAOETool;
import com.lance5057.compendium.util.ToolUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class HammerItem extends HandedAbilityTool implements IAOETool {

	public HammerItem(Tier tier, Item.Properties builder) {
		super(tier, BlockTags.MINEABLE_WITH_PICKAXE, builder);
	}

//	@Override
//	public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
//		int radius = 1;
//		if (player.isCrouching()) {
//			radius = 0;
//		}
//
//		// float originHardness = level.getBlockState(pos).getBlockHardness(null, null);
//
//		// only do a 3x3 break if the player's tool is effective on the block they are
//		// breaking
//		// this makes it so breaking gravel doesn't break nearby stone
//		if (player.getMainHandItem().isCorrectToolForDrops(level.getBlockState(pos))) {
//			ToolUtil.breakInRadius(level, player, radius, pos);
//		}
//
//		return true;
//	}

	@Override
	protected InteractionResult mainInteractionHandAbility(UseOnContext context) {
//		HammerHandedToolRecipe recipe = this.matchRecipe(context);
//
//		if (recipe != null) {
//			Level level = context.getLevel();
//			BlockPos pos = context.getClickedPos();
//
//			if (recipe.getResultItem().getItem() instanceof BlockItem) {
//				BlockItem b = (BlockItem) recipe.getResultItem().getItem();
//				to = b.getBlock().defaultBlockState();
//				BlockState origState = level.getBlockState(pos);
//				BlockState state = to;
//
//				for (Entry<Property<?>, Comparable<?>> entry : this.getCommonProperties(origState, state).entrySet()) {
//					Property property = state.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
//					final Optional<Comparable> propValue = property.getValue(entry.getValue().toString());
//					propValue.ifPresent(comparable -> to = to.setValue(property, comparable));
//				}
//
//				level.setBlockAndUpdate(pos, to);
//
//				for (int i = 0; i < 5; i++) {
//					level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, context.getPlayer().getMainHandItem()),
//							pos.getX() + 0.5f, pos.getY() + 1, pos.getZ() + 0.5f, (level.random.nextFloat() - 0.5f) / 2,
//							(level.random.nextFloat() - 0.5f) / 2, (level.random.nextFloat() - 0.5f) / 2);
//				}
//				level.playSound(context.getPlayer(), pos, SoundEvents.LANTERN_HIT, SoundSource.BLOCKS, 1, 1);
//
//				context.getPlayer().getOffhandItem().shrink(1);
//
//				return InteractionResult.sidedSuccess(level.isClientSide());
//			}
//		}
		return InteractionResult.FAIL;
	}

	// Make megalith stone
	@Override
	protected InteractionResult offInteractionHandAbility(UseOnContext context) {

		Level level = context.getLevel();

		BlockPos pos = context.getClickedPos().immutable();
		BlockState bs = level.getBlockState(pos);
		AABB aabb = new AABB(pos.above(1).north(1).east(1).relative(context.getClickedFace(), -1).getCenter(),
				pos.below(1).south(1).west(1).relative(context.getClickedFace(), -1).getCenter());

		List<BlockPos> invalid = BlockPos.betweenClosedStream(aabb)
				.filter(b -> level.getBlockState(b.immutable()).getBlock() != Blocks.STONE).map(b -> b.immutable())
				.collect(Collectors.toList());

		if (invalid.isEmpty()) {

			BlockPos.betweenClosedStream(aabb)
					.forEach(b -> level.destroyBlock(b.immutable(), false, context.getPlayer()));

			float e = this.getTier().getSpeed();
			float e2 = context.getItemInHand()
					.getEnchantmentLevel(ToolUtil.getEnchantment(level, Enchantments.EFFICIENCY));
			int e3 = (int) (e * e2);

			context.getPlayer().getCooldowns().addCooldown(this, (int) (100 - e3));
			context.getPlayer().stopUsingItem();
			context.getPlayer().level().broadcastEntityEvent(context.getPlayer(), (byte) 30);

			context.getItemInHand().hurtAndBreak(27, context.getPlayer(), null);

			Containers.dropItemStack(level, context.getClickedPos().getX(), context.getClickedPos().getY(),
					context.getClickedPos().getZ(), new ItemStack(CompendiumItems.MEGALITH_STONE.get()));

			return InteractionResult.SUCCESS;
		}

		return InteractionResult.FAIL;
	}

	@Override
	public int getWidth() {
		return 1;
	}

	@Override
	public int getHeight() {
		return 1;
	}

	@Override
	public int getDepth() { 
		return 0;
	}

//	private HammerHandedToolRecipe matchRecipe(UseOnContext context) {
//		Level level = context.getLevel();
//		BlockState bs = context.getLevel().getBlockState(context.getClickedPos());
//
//		if (level != null) {
//			HammerHandedToolRecipe r = level.getRecipeManager().getRecipes().stream()
//					.filter(recipe -> recipe instanceof HammerHandedToolRecipe)
//					.map(recipe -> (HammerHandedToolRecipe) recipe)
//					.filter(recipe -> recipe.matches(new HandedToolWrapper(context.getItemInHand(),
//							new ItemStack(bs.getBlock()), context.getPlayer().getOffhandItem()), level))
//					.findFirst().orElse(null);
//
//			return r;
//		}
//		return null;
//	}
}