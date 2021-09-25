package lance5057.compendium.core.items.tools;

import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableSet;

import lance5057.compendium.CompendiumItems;
import lance5057.compendium.core.items.HandedAbilityTool;
import lance5057.compendium.core.recipes.HammerHandedToolRecipe;
import lance5057.compendium.core.recipes.HandedToolWrapper;
import lance5057.compendium.core.util.ToolUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.Property;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HammerItem extends HandedAbilityTool {

    public HammerItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder) {
	super((float) attackDamageIn, attackSpeedIn, tier, ImmutableSet.of(),
		builder.addToolType(net.minecraftforge.common.ToolType.PICKAXE, tier.getHarvestLevel()));
    }

    /**
     * Check whether this Item can harvest the given Block
     */
    public boolean canHarvestBlock(BlockState blockIn) {
	int i = this.getTier().getHarvestLevel();
	if (blockIn.getHarvestTool() == net.minecraftforge.common.ToolType.PICKAXE) {
	    return i >= blockIn.getHarvestLevel();
	}
	Material material = blockIn.getMaterial();
	return material == Material.ROCK || material == Material.IRON || material == Material.ANVIL;
    }

    public float getDestroySpeed(ItemStack stack, BlockState state) {
	Material material = state.getMaterial();
	return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK
		? super.getDestroySpeed(stack, state)
		: this.efficiency;
    }

    @Override
    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World world, BlockPos pos, PlayerEntity player) {
	int radius = 1;
	if (player.isCrouching()) {
	    radius = 0;
	}

	// float originHardness = world.getBlockState(pos).getBlockHardness(null, null);

	// only do a 3x3 break if the player's tool is effective on the block they are
	// breaking
	// this makes it so breaking gravel doesn't break nearby stone
	if (player.getHeldItemMainhand().canHarvestBlock(world.getBlockState(pos))) {
	    ToolUtil.breakInRadius(world, player, radius);
	}

	return true;
    }

    BlockState to;

    @Override
    protected ActionResultType mainHandAbility(ItemUseContext context) {
	HammerHandedToolRecipe recipe = this.matchRecipe(context);

	if (recipe != null) {
	    World world = context.getWorld();
	    BlockPos pos = context.getPos();

	    if (recipe.getRecipeOutput().getItem() instanceof BlockItem) {
		BlockItem b = (BlockItem) recipe.getRecipeOutput().getItem();
		to = b.getBlock().getDefaultState();
		BlockState origState = world.getBlockState(pos);
		BlockState state = to;

		for (Entry<Property<?>, Comparable<?>> entry : this.getCommonProperties(origState, state).entrySet()) {
		    Property property = state.getBlock().getStateContainer().getProperty(entry.getKey().getName());
		    final Optional<Comparable> propValue = property.parseValue(entry.getValue().toString());
		    propValue.ifPresent(comparable -> to = to.with(property, comparable));
		}

		world.setBlockState(pos, to);

		for (int i = 0; i < 5; i++) {
		    world.addParticle(
			    new ItemParticleData(ParticleTypes.ITEM, context.getPlayer().getHeldItemOffhand()),
			    pos.getX() + 0.5f, pos.getY() + 1, pos.getZ() + 0.5f, (world.rand.nextFloat() - 0.5f) / 2,
			    (world.rand.nextFloat() - 0.5f) / 2, (world.rand.nextFloat() - 0.5f) / 2);
		}
		world.playSound(context.getPlayer(), pos, SoundEvents.BLOCK_LANTERN_HIT, SoundCategory.BLOCKS, 1, 1);

		context.getPlayer().getHeldItemOffhand().shrink(1);

		return ActionResultType.func_233537_a_(world.isRemote);
	    }
	}
	return ActionResultType.FAIL;
    }

    // Make megalith stone
    @Override
    protected ActionResultType offHandAbility(ItemUseContext context) {

	World world = context.getWorld();

	// if (!world.isRemote) {
	BlockPos pos = context.getPos().toImmutable();
	BlockState bs = world.getBlockState(pos);
	AxisAlignedBB aabb = new AxisAlignedBB(pos.up(1).north(1).east(1).offset(context.getFace(), -1),
		pos.down(1).south(1).west(1).offset(context.getFace(), -1));

	List<BlockPos> invalid = BlockPos.getAllInBox(aabb)
		.filter(b -> world.getBlockState(b.toImmutable()).getBlock() != Blocks.STONE).map(b -> b.toImmutable())
		.collect(Collectors.toList());

	if (invalid.isEmpty()) {

	    BlockPos.getAllInBox(aabb).forEach(b -> world.destroyBlock(b.toImmutable(), false, context.getPlayer()));

	    float e = this.getTier().getEfficiency();
	    float e2 = EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, context.getItem());
	    int e3 = (int) (e * e2);

	    context.getPlayer().getCooldownTracker().setCooldown(this, (int) (100 - e3));
	    context.getPlayer().resetActiveHand();
	    context.getPlayer().world.setEntityState(context.getPlayer(), (byte) 30);

	    context.getItem().damageItem(27, context.getPlayer(), null);

	    InventoryHelper.spawnItemStack(world, context.getPos().getX(), context.getPos().getY(),
		    context.getPos().getZ(), new ItemStack(CompendiumItems.MEGALITH_STONE.get()));

	    return ActionResultType.SUCCESS;
	}
	// }

	return ActionResultType.FAIL;
    }

    private HammerHandedToolRecipe matchRecipe(ItemUseContext context) {
	World world = context.getWorld();
	BlockState bs = context.getWorld().getBlockState(context.getPos());

	if (world != null) {
	    HammerHandedToolRecipe r = world.getRecipeManager().getRecipes()
		    .stream()
		    .filter(recipe -> recipe instanceof HammerHandedToolRecipe)
		    .map(recipe -> (HammerHandedToolRecipe) recipe)
		    .filter(recipe -> recipe.matches(new HandedToolWrapper(context.getItem(),
			    new ItemStack(bs.getBlock()), context.getPlayer().getHeldItemOffhand()), world))
		    .findFirst().orElse(null);

	    return r;
	}
	return null;
    }
}
