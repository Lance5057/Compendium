package lance5057.compendium.core.data.builders;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.tuple.Pair;

import lance5057.compendium.Reference;
import lance5057.compendium.core.library.CompendiumTags;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.CraftableMaterial;
import lance5057.compendium.core.library.materialutilities.addons.MaterialExtraComponents;
import lance5057.compendium.core.library.materialutilities.addons.MaterialOre;
import lance5057.compendium.core.library.materialutilities.addons.MaterialVanillaComponents;
import lance5057.compendium.core.library.materialutilities.addons.MaterialVanillaTools;
import lance5057.compendium.core.library.materialutilities.addons.MeltableMaterial;
import lance5057.compendium.core.materials.CompendiumMaterials;
import net.minecraft.block.Blocks;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class TCRecipes extends RecipeProvider {

	public TCRecipes(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void registerRecipes(@Nonnull Consumer<IFinishedRecipe> consumer) {
		for (MaterialHelper mh : CompendiumMaterials.materials) {

			// Meltable Materials
			if (mh.getIngot() != null) {
				MeltableMaterial mm = mh.getIngot();

				ShapelessRecipeBuilder.shapelessRecipe(mm.NUGGET.get(), 9).addIngredient(mm.INGOT.get(), 1)
						.addCriterion(mh.name + "ingot", hasItem(mm.INGOT.get()))
						.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_nugget_from_ingot"));
				ShapelessRecipeBuilder.shapelessRecipe(mm.INGOT.get(), 9).addIngredient(mm.STORAGE_ITEMBLOCK.get(), 1)
						.addCriterion(mh.name + "storage_block", hasItem(mm.STORAGE_ITEMBLOCK.get()))
						.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_ingot_from_storage_block"));

				ShapelessRecipeBuilder.shapelessRecipe(mm.INGOT.get(), 1).addIngredient(mm.NUGGET.get(), 9)
						.addCriterion(mh.name + "nugget", hasItem(mm.NUGGET.get()))
						.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_ingot_from_nuggets"));
				ShapelessRecipeBuilder.shapelessRecipe(mm.STORAGE_ITEMBLOCK.get(), 1).addIngredient(mm.INGOT.get(), 9)
						.addCriterion(mh.name + "storage_block", hasItem(mm.INGOT.get())).build(consumer,
								new ResourceLocation(Reference.MOD_ID, mh.name + "_storage_block_from_ingots"));
			}

			// Craftable Materials
			if (mh.getGem() != null) {
				CraftableMaterial cm = mh.getGem();

				ShapelessRecipeBuilder.shapelessRecipe(cm.NUGGET.get(), 9).addIngredient(cm.GEM.get(), 1)
						.addCriterion(mh.name + "ingot", hasItem(cm.GEM.get()))
						.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_nuggets_from_gem"));
				ShapelessRecipeBuilder.shapelessRecipe(cm.GEM.get(), 9).addIngredient(cm.STORAGE_ITEMBLOCK.get(), 1)
						.addCriterion(mh.name + "storage_block", hasItem(cm.STORAGE_ITEMBLOCK.get()))
						.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_gems_from_storage_block"));

				ShapelessRecipeBuilder.shapelessRecipe(cm.GEM.get(), 1).addIngredient(cm.NUGGET.get(), 9)
						.addCriterion(mh.name + "nugget", hasItem(cm.NUGGET.get()))
						.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_gem_from_nuggets"));
				ShapelessRecipeBuilder.shapelessRecipe(cm.STORAGE_ITEMBLOCK.get(), 1).addIngredient(cm.GEM.get(), 9)
						.addCriterion(mh.name + "storage_block", hasItem(cm.GEM.get()))
						.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_storage_block_from_gems"));
			}

			// Vanilla Components
			if (mh.getVanillaComponents() != null) {
				MaterialVanillaComponents vc = mh.getVanillaComponents();

				ShapedRecipeBuilder.shapedRecipe(vc.DOOR.get(), 3).key('p', mh.getExtraComponents().PLATE.get())
						.patternLine("pp").patternLine("pp").patternLine("pp")
						.addCriterion(mh.name + "door", hasItem(mh.getExtraComponents().PLATE.get()))
						.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_door_from_plates"));

				ShapedRecipeBuilder.shapedRecipe(vc.BARS.get(), 8).key('p', mh.getExtraComponents().ROD.get())
						.patternLine("ppp").patternLine("ppp")
						.addCriterion(mh.name + "rod", hasItem(mh.getExtraComponents().ROD.get()))
						.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_bars_from_rods"));

				if (mh.getIngot() != null) {
					ShapedRecipeBuilder.shapedRecipe(vc.LANTERN.get(), 1).key('p', mh.getIngot().NUGGET.get())
							.key('t', Items.TORCH).patternLine("ppp").patternLine("ptp").patternLine("ppp")
							.addCriterion(mh.name + "nugget", hasItem(mh.getIngot().NUGGET.get()))
							.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_lantern_from_nuggets"));
				}

				if (mh.getGem() != null) {
					ShapedRecipeBuilder.shapedRecipe(vc.LANTERN.get(), 1).key('p', mh.getGem().NUGGET.get())
							.key('t', Items.TORCH).patternLine("ppp").patternLine("ptp").patternLine("ppp")
							.addCriterion(mh.name + "nugget", hasItem(mh.getGem().NUGGET.get()))
							.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_lantern_from_nuggets"));
				}

				ShapedRecipeBuilder.shapedRecipe(vc.TRAPDOOR.get(), 1).key('p', mh.getExtraComponents().PLATE.get())
						.patternLine("pp").patternLine("pp")
						.addCriterion(mh.name + "plate", hasItem(mh.getExtraComponents().PLATE.get()))
						.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_trapdoor_from_plates"));
			}

			// Extra Components
			if (mh.getExtraComponents() != null) {
				MaterialExtraComponents ec = mh.getExtraComponents();

				ShapedRecipeBuilder.shapedRecipe(ec.CASING.get(), 1).key('p', mh.getExtraComponents().PLATE.get())
						.patternLine("ppp").patternLine("p p")
						.addCriterion(mh.name + "casing", hasItem(mh.getExtraComponents().PLATE.get()))
						.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_casing_from_plates"));

				ShapedRecipeBuilder.shapedRecipe(ec.COIL.get(), 1).key('p', mh.getExtraComponents().WIRE.get())
						.key('s', Items.STICK).patternLine(" p ").patternLine("psp").patternLine(" p ")
						.addCriterion(mh.name + "wire", hasItem(mh.getExtraComponents().WIRE.get()))
						.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_coil_from_wire"));

				// Coins
				if (mh.getIngot() != null) {
					ShapedRecipeBuilder.shapedRecipe(ec.COIN.get(), 4).key('p', mh.getIngot().NUGGET.get())
							.patternLine("pp").patternLine("pp")
							.addCriterion(mh.name + "nugget", hasItem(mh.getIngot().NUGGET.get()))
							.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_coin_from_nuggets"));
				}

				if (mh.getGem() != null) {
					ShapedRecipeBuilder.shapedRecipe(ec.COIN.get(), 4).key('p', mh.getGem().NUGGET.get())
							.patternLine("pp").patternLine("pp")
							.addCriterion(mh.name + "nugget", hasItem(mh.getGem().NUGGET.get()))
							.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_coin_from_nuggets"));
				}

				// Gears
				if (mh.getIngot() != null) {
					ShapedRecipeBuilder.shapedRecipe(ec.GEAR.get(), 1).key('p', mh.getIngot().INGOT.get())
							.patternLine(" p ").patternLine("p p").patternLine(" p ")
							.addCriterion(mh.name + "ingot", hasItem(mh.getIngot().INGOT.get()))
							.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_gear_from_ingots"));
				}

				if (mh.getGem() != null) {
					ShapedRecipeBuilder.shapedRecipe(ec.GEAR.get(), 1).key('p', mh.getGem().GEM.get())
							.patternLine(" p ").patternLine("p p").patternLine(" p ")
							.addCriterion(mh.name + "gem", hasItem(mh.getGem().GEM.get()))
							.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_gear_from_gems"));
				}

				// Plates
				if (mh.getIngot() != null) {
					ShapedRecipeBuilder.shapedRecipe(ec.PLATE.get(), 2).key('p', mh.getIngot().INGOT.get())
							.patternLine("p").patternLine("p")
							.addCriterion(mh.name + "ingot", hasItem(mh.getIngot().INGOT.get()))
							.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_plates_from_ingots"));
				}

				if (mh.getGem() != null) {
					ShapedRecipeBuilder.shapedRecipe(ec.PLATE.get(), 2).key('p', mh.getGem().GEM.get()).patternLine("p")
							.patternLine("p").addCriterion(mh.name + "gem", hasItem(mh.getGem().GEM.get()))
							.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_plates_from_gems"));
				}

				// Rods
				if (mh.getIngot() != null) {
					ShapedRecipeBuilder.shapedRecipe(ec.ROD.get(), 6).key('p', mh.getIngot().INGOT.get())
							.patternLine("p").patternLine("p").patternLine("p")
							.addCriterion(mh.name + "ingot", hasItem(mh.getIngot().INGOT.get()))
							.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_rods_from_ingots"));
				}

				if (mh.getGem() != null) {
					ShapedRecipeBuilder.shapedRecipe(ec.ROD.get(), 6).key('p', mh.getGem().GEM.get()).patternLine("p")
							.patternLine("p").patternLine("p")
							.addCriterion(mh.name + "gem", hasItem(mh.getGem().GEM.get()))
							.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_rods_from_gemss"));
				}

				ShapedRecipeBuilder.shapedRecipe(ec.SPRING.get(), 1).key('p', mh.getExtraComponents().WIRE.get())
						.key('s', Items.STICK).patternLine("p").patternLine("s")
						.addCriterion(mh.name + "wire", hasItem(mh.getExtraComponents().WIRE.get()))
						.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_spring_from_wire"));

				ShapelessRecipeBuilder.shapelessRecipe(ec.WIRE.get(), 4).addIngredient(ec.ROD.get())
						.addCriterion(mh.name + "rod", hasItem(mh.getExtraComponents().ROD.get()))
						.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_wires_from_rod"));

				ShapedRecipeBuilder.shapedRecipe(ec.ITEM_SHEET.get(), 3).key('p', mh.getExtraComponents().PLATE.get())
						.patternLine("ppp")
						.addCriterion(mh.name + "plate", hasItem(mh.getExtraComponents().PLATE.get()))
						.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_sheets_from_plates"));

				ShapedRecipeBuilder.shapedRecipe(ec.ITEM_SHINGLES.get(), 4)
						.key('p', mh.getExtraComponents().PLATE.get()).key('s', Items.STICK)
						.key('l', Ingredient.fromTag(ItemTags.LOGS)).patternLine("  p").patternLine(" ps")
						.patternLine("psl")
						.addCriterion(mh.name + "plate", hasItem(mh.getExtraComponents().PLATE.get()))
						.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_shingles_from_plates"));

				ShapedRecipeBuilder.shapedRecipe(ec.ITEM_SHINGLES_ALT.get(), 4)
						.key('p', mh.getExtraComponents().PLATE.get()).key('s', Items.STICK)
						.key('l', Ingredient.fromTag(ItemTags.LOGS)).patternLine("  p").patternLine(" pl")
						.patternLine("pls")
						.addCriterion(mh.name + "plate", hasItem(mh.getExtraComponents().PLATE.get()))
						.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_shingles_alt_from_plates"));

				ShapedRecipeBuilder.shapedRecipe(ec.ITEM_SHINGLES_BLOCK.get(), 4)
						.key('p', mh.getExtraComponents().PLATE.get()).key('l', Ingredient.fromTag(ItemTags.LOGS))
						.patternLine(" p ").patternLine("plp").patternLine(" p ")
						.addCriterion(mh.name + "plate", hasItem(mh.getExtraComponents().PLATE.get())).build(consumer,
								new ResourceLocation(Reference.MOD_ID, mh.name + "_shingles_block_from_plates"));

				ShapedRecipeBuilder.shapedRecipe(ec.ITEM_STAKE.get(), 3).key('p', mh.getExtraComponents().ROD.get())
						.patternLine("p").patternLine("p").patternLine("p")
						.addCriterion(mh.name + "rod", hasItem(mh.getExtraComponents().ROD.get()))
						.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_stakes_from_rods"));

				if (mh.getIngot() != null) {
					ShapedRecipeBuilder.shapedRecipe(ec.DUST.get(), 1).key('p', mh.getIngot().INGOT.get())
							.key('s', Blocks.COBBLESTONE).patternLine("s").patternLine("p")
							.addCriterion(mh.name + "ingot", hasItem(mh.getIngot().INGOT.get()))
							.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_dust_from_ingot"));

					CookingRecipeBuilder
							.smeltingRecipe(Ingredient.fromItems(ec.DUST.get()), mh.getIngot().INGOT.get(), 1f, 100)
							.addCriterion(mh.name + "dust", hasItem(ec.DUST.get()))
							.build(consumer, mh.name + "ingot_from_dust_furnace");
					CookingRecipeBuilder
							.blastingRecipe(Ingredient.fromItems(ec.DUST.get()), mh.getIngot().INGOT.get(), 1f, 100)
							.addCriterion(mh.name + "dust", hasItem(ec.DUST.get()))
							.build(consumer, mh.name + "ingot_from_dust_blast");
				}

				if (mh.getGem() != null) {
					ShapedRecipeBuilder.shapedRecipe(ec.DUST.get(), 1).key('p', mh.getGem().GEM.get())
							.key('s', Blocks.COBBLESTONE).patternLine("s").patternLine("p")
							.addCriterion(mh.name + "gem", hasItem(mh.getGem().GEM.get()))
							.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_dust_from_gem"));

					CookingRecipeBuilder
							.smeltingRecipe(Ingredient.fromItems(ec.DUST.get()), mh.getGem().GEM.get(), 1f, 100)
							.addCriterion(mh.name + "dust", hasItem(ec.DUST.get()))
							.build(consumer, mh.name + "ingot_from_dust_furnace");
					CookingRecipeBuilder
							.blastingRecipe(Ingredient.fromItems(ec.DUST.get()), mh.getGem().GEM.get(), 1f, 100)
							.addCriterion(mh.name + "dust", hasItem(ec.DUST.get()))
							.build(consumer, mh.name + "ingot_from_dust_blast");
				}
			}

			// Vanilla Tools
			if (mh.getVanillaTools() != null) {
				MaterialVanillaTools vt = mh.getVanillaTools();

				if (mh.getIngot() != null) {
					ShapedRecipeBuilder.shapedRecipe(vt.AXE.get(), 1).key('i', mh.getIngot().INGOT.get())
							.key('s', Items.STICK).patternLine("ii").patternLine("is").patternLine(" s")
							.addCriterion(mh.name + "ingot", hasItem(mh.getIngot().INGOT.get()))
							.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_axe_from_ingots"));

					ShapedRecipeBuilder.shapedRecipe(vt.HOE.get(), 1).key('i', mh.getIngot().INGOT.get())
							.key('s', Items.STICK).patternLine("ii").patternLine(" s").patternLine(" s")
							.addCriterion(mh.name + "ingot", hasItem(mh.getIngot().INGOT.get()))
							.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_hoe_from_ingots"));

					ShapedRecipeBuilder.shapedRecipe(vt.PICKAXE.get(), 1).key('i', mh.getIngot().INGOT.get())
							.key('s', Items.STICK).patternLine("iii").patternLine(" s ").patternLine(" s ")
							.addCriterion(mh.name + "ingot", hasItem(mh.getIngot().INGOT.get()))
							.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_pickaxe_from_ingots"));

					ShapedRecipeBuilder.shapedRecipe(vt.SHOVEL.get(), 1).key('i', mh.getIngot().INGOT.get())
							.key('s', Items.STICK).patternLine("i").patternLine("s").patternLine("s")
							.addCriterion(mh.name + "ingot", hasItem(mh.getIngot().INGOT.get()))
							.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_shovel_from_ingots"));

					ShapedRecipeBuilder.shapedRecipe(vt.SWORD.get(), 1).key('i', mh.getIngot().INGOT.get())
							.key('s', Items.STICK).patternLine("i").patternLine("i").patternLine("s")
							.addCriterion(mh.name + "ingot", hasItem(mh.getIngot().INGOT.get()))
							.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_sword_from_ingots"));
				}

				if (mh.getGem() != null) {
					ShapedRecipeBuilder.shapedRecipe(vt.AXE.get(), 1).key('i', mh.getGem().GEM.get())
							.key('s', Items.STICK).patternLine("ii").patternLine("is").patternLine(" s")
							.addCriterion(mh.name + "gem", hasItem(mh.getGem().GEM.get()))
							.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_axe_from_gems"));

					ShapedRecipeBuilder.shapedRecipe(vt.HOE.get(), 1).key('i', mh.getGem().GEM.get())
							.key('s', Items.STICK).patternLine("ii").patternLine(" s").patternLine(" s")
							.addCriterion(mh.name + "gem", hasItem(mh.getGem().GEM.get()))
							.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_hoe_from_gems"));

					ShapedRecipeBuilder.shapedRecipe(vt.PICKAXE.get(), 1).key('i', mh.getGem().GEM.get())
							.key('s', Items.STICK).patternLine("iii").patternLine(" s ").patternLine(" s ")
							.addCriterion(mh.name + "gem", hasItem(mh.getGem().GEM.get()))
							.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_pickaxe_from_gems"));

					ShapedRecipeBuilder.shapedRecipe(vt.SHOVEL.get(), 1).key('i', mh.getGem().GEM.get())
							.key('s', Items.STICK).patternLine("i").patternLine("s").patternLine("s")
							.addCriterion(mh.name + "gem", hasItem(mh.getGem().GEM.get()))
							.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_shovel_from_gems"));

					ShapedRecipeBuilder.shapedRecipe(vt.SWORD.get(), 1).key('i', mh.getGem().GEM.get())
							.key('s', Items.STICK).patternLine("i").patternLine("i").patternLine("s")
							.addCriterion(mh.name + "gem", hasItem(mh.getGem().GEM.get()))
							.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_sword_from_gems"));
				}
			}

			if (mh.getOre() != null) {
				MaterialOre mo = mh.getOre();
				if (mh.getIngot() != null) {
					CookingRecipeBuilder
							.smeltingRecipe(Ingredient.fromItems(mo.ITEM_ORE.get()), mh.getIngot().INGOT.get(), 1f, 100)
							.addCriterion(mh.name + "ore", hasItem(mo.ITEM_ORE.get()))
							.build(consumer, mh.name + "ingot_from_ore_furnace");
					CookingRecipeBuilder
							.blastingRecipe(Ingredient.fromItems(mo.ITEM_ORE.get()), mh.getIngot().INGOT.get(), 1f, 100)
							.addCriterion(mh.name + "ore", hasItem(mo.ITEM_ORE.get()))
							.build(consumer, mh.name + "ingot_from_ore_blast");
				}
				if (mh.getGem() != null) {
					CookingRecipeBuilder
							.smeltingRecipe(Ingredient.fromItems(mo.ITEM_ORE.get()), mh.getGem().GEM.get(), 1f, 100)
							.addCriterion(mh.name + "ore", hasItem(mo.ITEM_ORE.get()))
							.build(consumer, mh.name + "gem_from_ore_furnace");
					CookingRecipeBuilder
							.blastingRecipe(Ingredient.fromItems(mo.ITEM_ORE.get()), mh.getGem().GEM.get(), 1f, 100)
							.addCriterion(mh.name + "ore", hasItem(mo.ITEM_ORE.get()))
							.build(consumer, mh.name + "gem_from_ore_blast");
				}
			}
		}

	}

}
