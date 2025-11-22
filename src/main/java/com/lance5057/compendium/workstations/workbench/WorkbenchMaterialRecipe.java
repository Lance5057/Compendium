package com.lance5057.compendium.workstations.workbench;

import java.util.List;
import java.util.Optional;

import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.components.block.MultiMaterialBlockComponent;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.IIndexEntry;
import com.lance5057.compendium.multimaterial.MultiMaterialType;
import com.lance5057.compendium.workstations.WorkstationRecipes;
import com.lance5057.compendium.workstations._bases.recipes.AnimatedRecipeItemUse;
import com.lance5057.compendium.workstations._bases.recipes.multitoolrecipe.MultiToolRecipeShapedPattern;
import com.lance5057.compendium.workstations.containers.MultiToolRecipeWrapper;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public class WorkbenchMaterialRecipe extends WorkbenchRecipe {

	List<SlotToMaterial> matSlots;

	public WorkbenchMaterialRecipe(MultiToolRecipeShapedPattern input, List<SlotToMaterial> matSlots,
			NonNullList<AnimatedRecipeItemUse> recipeToolsIn, ItemStack recipeOutputIn) {
		super(input, recipeToolsIn, recipeOutputIn, WorkstationRecipes.WORKBENCH_MATERIAL_RECIPE.get());
		this.matSlots = matSlots;
	}

	@Override
	public ItemStack assemble(MultiToolRecipeWrapper input, Provider registries) {
		ItemStack s = this.getResultItem(registries);

		if (s.has(CompendiumComponents.MULTI_MATERIAL)) {
			MultiMaterialBlockComponent mmbc = s.get(CompendiumComponents.MULTI_MATERIAL);

			List<MultiMaterialType> mats = mmbc.types();

			for (SlotToMaterial sm : matSlots) {
				ItemStack i = input.getItem(sm.slot);

				if (CompendiumIndex.isIndexItem(i)) {
					Optional<IIndexEntry> o = CompendiumIndex.getEntryItemBelongsTo(i);

					if (mats.size() > sm.materialLayer) {
						String m = o.get().getName();

						MultiMaterialType mmt = mats.get(sm.materialLayer);
						mmt.setCurrentMaterial(m);
						mats.set(sm.materialLayer, mmt);
					}
				}
			}

			s.set(CompendiumComponents.MULTI_MATERIAL, new MultiMaterialBlockComponent(mats));
		}

		return s;
	}

	public class SlotToMaterial {
		int slot;
		int materialLayer;

		SlotToMaterial(int slot, int material) {
			this.slot = slot;
			this.materialLayer = material;
		}
	}
}
