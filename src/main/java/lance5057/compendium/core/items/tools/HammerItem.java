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
import net.minecraft.block.StairsBlock;
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
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.Half;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HammerItem extends HandedAbilityTool {
    private static final Set<Block> EFFECTIVE_ON = ImmutableSet.of(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE,
	    Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.POWERED_RAIL,
	    Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.NETHER_GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE,
	    Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE,
	    Blocks.BLUE_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.CHISELED_SANDSTONE,
	    Blocks.CUT_SANDSTONE, Blocks.CHISELED_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE, Blocks.RED_SANDSTONE,
	    Blocks.STONE, Blocks.GRANITE, Blocks.POLISHED_GRANITE, Blocks.DIORITE, Blocks.POLISHED_DIORITE,
	    Blocks.ANDESITE, Blocks.POLISHED_ANDESITE, Blocks.STONE_SLAB, Blocks.SMOOTH_STONE_SLAB,
	    Blocks.SANDSTONE_SLAB, Blocks.PETRIFIED_OAK_SLAB, Blocks.COBBLESTONE_SLAB, Blocks.BRICK_SLAB,
	    Blocks.STONE_BRICK_SLAB, Blocks.NETHER_BRICK_SLAB, Blocks.QUARTZ_SLAB, Blocks.RED_SANDSTONE_SLAB,
	    Blocks.PURPUR_SLAB, Blocks.SMOOTH_QUARTZ, Blocks.SMOOTH_RED_SANDSTONE, Blocks.SMOOTH_SANDSTONE,
	    Blocks.SMOOTH_STONE, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE, Blocks.POLISHED_GRANITE_SLAB,
	    Blocks.SMOOTH_RED_SANDSTONE_SLAB, Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.POLISHED_DIORITE_SLAB,
	    Blocks.MOSSY_COBBLESTONE_SLAB, Blocks.END_STONE_BRICK_SLAB, Blocks.SMOOTH_SANDSTONE_SLAB,
	    Blocks.SMOOTH_QUARTZ_SLAB, Blocks.GRANITE_SLAB, Blocks.ANDESITE_SLAB, Blocks.RED_NETHER_BRICK_SLAB,
	    Blocks.POLISHED_ANDESITE_SLAB, Blocks.DIORITE_SLAB, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX,
	    Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX,
	    Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX,
	    Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX,
	    Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX,
	    Blocks.PISTON, Blocks.STICKY_PISTON, Blocks.PISTON_HEAD);

    public HammerItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder) {
	super((float) attackDamageIn, attackSpeedIn, tier, EFFECTIVE_ON,
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

		for (Entry<Property<?>, Comparable<?>> entry : this.getCommonProperties(origState, state)
			.entrySet()) {
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

		context.getPlayer().getHeldItemOffhand()
			.setCount(context.getPlayer().getHeldItemOffhand().getCount() - 1);

		return ActionResultType.PASS;
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

	    return ActionResultType.PASS;
	}
	// }

	return ActionResultType.FAIL;
    }

    private HammerHandedToolRecipe matchRecipe(ItemUseContext context) {
	World world = context.getWorld();
	BlockState bs = context.getWorld().getBlockState(context.getPos());

	if (world != null) {
	    HammerHandedToolRecipe r = world.getRecipeManager().getRecipes().stream()
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
