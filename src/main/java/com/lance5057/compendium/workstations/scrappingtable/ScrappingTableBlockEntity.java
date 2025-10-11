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
	private List<AnimatedRecipeItemUse> specialRecipe = new ArrayList<AnimatedRecipeItemUse>();
	private List<ItemStack> specialRecipeDrops = new ArrayList<ItemStack>();

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

				if (other.isPresent()) {
					Optional<IScrappingRule> rule = ScrappingRulesRegistry.getRule(other.get());
					if (rule.isPresent()) {
						useSpecialRecipe = true;
						specialRecipeDrops = rule.get().scrap(other.get(), this.inventory.getStackInSlot(0));
						specialRecipe = new ArrayList<AnimatedRecipeItemUse>();

						for (int i = 0; i < 2; i++) {
							specialRecipe.add(getRandomScrappingTool(i));
						}
					}
				}
			}
		}
		return recipe;
	}

	private AnimatedRecipeItemUse getRandomScrappingTool(int i) {
		switch (i) {
		case 0:
			return new AnimatedRecipeItemUse(3, Ingredient.of(CompendiumTags.HAMMER), 1, true, null, List.of(),
					List.of(standardHammeringModel(TagUtil.modLoc("gold_hammer"), 0)));
		case 1:
		default:
			return new AnimatedRecipeItemUse(3, Ingredient.of(CompendiumTags.PRYBAR), 1, true, null, List.of(),
					List.of(standardHammeringModel(TagUtil.modLoc("gold_prybar"), 0)));
		}
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
			return this.specialRecipe.get(stage);
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
				this.maxProgress = specialRecipe.get(stage).uses();
				this.curTool = specialRecipe.get(stage).tool();
				this.toolCount = specialRecipe.get(stage).count();
			}
			searchForNextItem(pLevel, player, hand, curTool);
			if (this.curTool.test(tool)) {
				if (tool.getCount() >= this.toolCount) {

					if (this.progress >= this.maxProgress - 1) {

						if (stage >= this.specialRecipe.size() - 1) { // final stage
							for (int i = 0; i < 5; i++) {
								addParticle();
							}
							level.playSound(player, worldPosition, SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 1, 0);

							if (tool.isDamageableItem())
								tool.hurtAndBreak(1, player, null);
							else
								tool.setCount(tool.getCount() - this.toolCount);

							for (ItemStack s : specialRecipeDrops)
								ItemUtil.giveOrDrop(s, player);
							useSpecialRecipe = false;
							specialRecipe.clear();
							specialRecipeDrops.clear();
							this.getInventory().shrinkAll();
							this.zeroProgress();

						} else {
//							dropLoot(r.value().getTools().get(stage), player);
							stage++;
							this.progress = 0;
							this.maxProgress = specialRecipe.get(stage).uses();
							this.curTool = specialRecipe.get(stage).tool();
							this.toolCount = specialRecipe.get(stage).count();

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
				specialRecipe.clear();
				specialRecipeDrops.clear();
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

}
