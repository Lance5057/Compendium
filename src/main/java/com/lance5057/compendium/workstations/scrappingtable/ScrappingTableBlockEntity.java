package com.lance5057.compendium.workstations.scrappingtable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.CompendiumTags;
import com.lance5057.compendium.client.BlacklistedModel;
import com.lance5057.compendium.util.ItemUtil;
import com.lance5057.compendium.util.TagUtil;
import com.lance5057.compendium.util.rendering.animation.floats.AnimatedFloat;
import com.lance5057.compendium.util.rendering.animation.floats.AnimatedFloatVector3;
import com.lance5057.compendium.util.rendering.animation.floats.AnimationFloatTransform;
import com.lance5057.compendium.workstations.WorkstationRecipes;
import com.lance5057.compendium.workstations._bases.blockentities.MultiToolRecipeStation;
import com.lance5057.compendium.workstations._bases.components.item.BlockEntityItemHandler;
import com.lance5057.compendium.workstations._bases.recipes.AnimatedRecipeItemUse;
import com.lance5057.compendium.workstations.containers.MultiToolRecipeWrapper;
import com.lance5057.compendium.workstations.scrappingtable.scrapping_rules.IScrappingRule;
import com.lance5057.compendium.workstations.scrappingtable.scrapping_rules.ScrappingRulesRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeManager.CachedCheck;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ScrappingTableBlockEntity extends MultiToolRecipeStation<ScrappingTableRecipe> {

	public ScrappingTableBlockEntity(BlockPos pos, BlockState state) {
		super(1, 1, 1, CompendiumBlockEntities.SCRAPPING_TABLE.get(), pos, state);
	}

	boolean useSpecialRecipe = false;
	private CustomRecipe specialRecipe;

	private final CachedCheck<MultiToolRecipeWrapper, ScrappingTableRecipe> quickCheck = RecipeManager
			.createCheck(WorkstationRecipes.SCRAPPINGTABLE_RECIPE.get());

	@Override
	public Optional<RecipeHolder<ScrappingTableRecipe>> matchRecipe() {
		Optional<RecipeHolder<ScrappingTableRecipe>> recipe = Optional.empty();
		if (this.level != null && this.getInventory() != null && !useSpecialRecipe) {
			recipe = this.quickCheck.getRecipeFor(MultiToolRecipeWrapper.of(this.getInventory()), level);
			if (recipe.isEmpty()) {
				// search for item's recipe
				Optional<RecipeHolder<?>> other = this.level.getRecipeManager().getRecipes().stream()
						.filter(r -> ItemStack.isSameItem(r.value().getResultItem(level.registryAccess()),
								this.getInventory().getStackInSlot(0)))
						.findFirst();

				if (other.isPresent() && this.inventory.getStackInSlot(0).getCount() >= other.get().value()
						.getResultItem(null).getCount()) {
					Optional<IScrappingRule> rule = ScrappingRulesRegistry.getRule(other.get());
					if (rule.isPresent()) {
						useSpecialRecipe = true;

						List<ItemStack> drops = rule.get().scrap(other.get(), this.inventory.getStackInSlot(0));
						ArrayList<AnimatedRecipeItemUse> tools = new ArrayList<AnimatedRecipeItemUse>();

						for (int i = 0; i < 2; i++) {
							tools.add(getScrappingTools(i));
						}

						this.specialRecipe = new CustomRecipe(other.get(), tools, drops);
					}
				}
			}
		}
		return recipe;
	}

	private AnimatedRecipeItemUse getScrappingTools(int i) {
		if (i % 2 == 0)
			return new AnimatedRecipeItemUse(3, Ingredient.of(CompendiumTags.HAMMER), 1, true, null, List.of(),
					List.of(standardHammeringModel(TagUtil.modLoc("gold_hammer"), 0)));
		else
			return new AnimatedRecipeItemUse(3, Ingredient.of(CompendiumTags.PRYBAR), 1, true, null, List.of(),
					List.of(standardHammeringModel(TagUtil.modLoc("gold_prybar"), 0)));
	}

	BlacklistedModel standardHammeringModel(ResourceLocation i, float yOffset) {
		return new BlacklistedModel(i, false,
				new AnimationFloatTransform()
						.setRotation(new AnimatedFloatVector3().setZ(new AnimatedFloat(-45, 45, 0, 0.5f, true, true)))
						.setLocation(new AnimatedFloatVector3().setX(new AnimatedFloat(8, 0))
								.setY(new AnimatedFloat(10 + yOffset, 0)).setZ(new AnimatedFloat(8, 0)))
						.setScale(new AnimatedFloatVector3().setAll(new AnimatedFloat(0.5f))));
	}

	@Override
	public AnimatedRecipeItemUse getCurrentTool() {
		if (this.useSpecialRecipe) {
			return this.specialRecipe.steps.get(stage);
		} else {
			Optional<RecipeHolder<ScrappingTableRecipe>> currentRecipe = matchRecipe();
			if (currentRecipe.isPresent())
				return currentRecipe.get().value().getTools().get(stage);
		}
		return null;
	}

	@Override
	public ItemInteractionResult use(Level pLevel, Player player, InteractionHand hand, ItemStack tool) {

		if (this.useSpecialRecipe) {

			if (this.curTool == null) {

				this.progress = 0;
				this.maxProgress = specialRecipe.steps.get(stage).uses();
				this.curTool = specialRecipe.steps.get(stage).tool();
				this.toolCount = specialRecipe.steps.get(stage).count();
			}
			searchForNextItem(pLevel, player, hand, curTool);
			if (this.curTool.test(tool)) {
				if (tool.getCount() >= this.toolCount) {

					if (this.progress >= this.maxProgress - 1) {

						if (stage >= this.specialRecipe.steps.size() - 1) { // final stage
							for (int i = 0; i < 5; i++) {
								addParticle();
							}
							level.playSound(player, worldPosition, SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 1, 0);

							if (tool.isDamageableItem())
								tool.hurtAndBreak(1, player, null);
							else
								tool.setCount(tool.getCount() - this.toolCount);

							ItemStack input = this.getInventory().getStackInSlot(0);
							for (ItemStack s : specialRecipe.drops) {
								int count = s.getCount();
								int damage = input.getDamageValue();
								int max = input.getMaxDamage();

								if (max != 0 && damage != 0) {
									float divide = (float) damage / (float) max;

									int newcount = Math.round(count * divide);
									s.setCount(newcount);
								}

								ItemUtil.giveOrDrop(s, player);
							}
							this.getInventory().shrinkAll(
									specialRecipe.recipe.value().getResultItem(level.registryAccess()).getCount());

							useSpecialRecipe = false;
							specialRecipe = null;

							this.zeroProgress();

						} else {
//							dropLoot(r.value().getTools().get(stage), player);
							stage++;
							this.progress = 0;
							this.maxProgress = specialRecipe.steps.get(stage).uses();
							this.curTool = specialRecipe.steps.get(stage).tool();
							this.toolCount = specialRecipe.steps.get(stage).count();

							searchForNextItem(pLevel, player, hand, curTool);
						}
					} else {
						if (tool.isDamageableItem())
							tool.hurtAndBreak(1, player, null);
						else
							tool.setCount(tool.getCount() - this.toolCount);

						progress++;
					}
				}
			} else {
				searchForNextItem(pLevel, player, hand, curTool);
			}

		} else {
			super.use(pLevel, player, hand, tool);
		}
		this.updateInventory();

		return ItemInteractionResult.SUCCESS;
	}

	@Override
	protected BlockEntityItemHandler createItemHandler() {
		return new BlockEntityItemHandler(this, 1) {
			@Override
			protected void onContentsChanged(int slot) {
				useSpecialRecipe = false;
				specialRecipe = null;
			}
		};
	}

	@Override
	public void addParticle() {
		// TODO Auto-generated method stub

	}

	@Override
	public void finishRecipe(Player Player, ScrappingTableRecipe recipe) {
		ItemUtil.giveOrDrop(recipe.getItemOut(), Player);
		this.getInventory().shrinkAll();
	}

	@Override
	protected void readNBTExtra(CompoundTag arg0, Provider arg1) {
//		this.useSpecialRecipe = arg0.getBoolean("isSpecial");
//
//		CompoundTag sDrops = arg0.getCompound("specialDrops");
//		int count = sDrops.getInt("count");
//
//		for (int i = 0; i < count; i++) {
//			ItemStack s = ItemStack.parseOptional(arg1, (CompoundTag) sDrops.get("specialDrop" + i));
//			specialRecipeDrops.add(s);
//		}

	}

	@Override
	protected void writeNBTExtra(CompoundTag arg0, Provider arg1) {
//		arg0.putBoolean("isSpecial", this.useSpecialRecipe);
//
//		CompoundTag sDrops = new CompoundTag();
//		for (int i = 0; i < this.specialRecipeDrops.size(); i++)
//			sDrops.put("specialDrop" + i, specialRecipeDrops.get(i).save(arg1));
//		sDrops.putInt("count", specialRecipeDrops.size());
//		arg0.put("specialDrops", sDrops);
	}

	@Override
	protected void setupRecipe() {

	}

	private class CustomRecipe {
		public RecipeHolder<?> recipe;
		public List<AnimatedRecipeItemUse> steps = new ArrayList<AnimatedRecipeItemUse>();
		public List<ItemStack> drops = new ArrayList<ItemStack>();

		public CustomRecipe(RecipeHolder<?> r, List<AnimatedRecipeItemUse> s, List<ItemStack> d) {
			this.recipe = r;
			this.steps = s;
			this.drops = d;
		}
	}
}
