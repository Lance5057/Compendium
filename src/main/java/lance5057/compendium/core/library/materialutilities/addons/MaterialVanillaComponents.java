package lance5057.compendium.core.library.materialutilities.addons;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.tuple.ImmutablePair;

import lance5057.compendium.Reference;
import lance5057.compendium.TCBlocks;
import lance5057.compendium.TCItems;
import lance5057.compendium.core.blocks.ComponentDoor;
import lance5057.compendium.core.blocks.ComponentPane;
import lance5057.compendium.core.blocks.ComponentTrapDoor;
import lance5057.compendium.core.data.builders.TCRecipes;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class MaterialVanillaComponents implements MaterialBase {
	String matName;
	String parentMod;

	public ComponentDoor door = new ComponentDoor(Block.Properties.create(Material.IRON, MaterialColor.IRON)
			.hardnessAndResistance(5.0F)
			.sound(SoundType.METAL)
			.notSolid());
	public ComponentTrapDoor trapdoor = new ComponentTrapDoor(
			Block.Properties.create(Material.IRON).hardnessAndResistance(5.0F).sound(SoundType.METAL).notSolid());
	public ComponentPane bars = new ComponentPane(Block.Properties.create(Material.IRON, MaterialColor.AIR)
			.hardnessAndResistance(5.0F, 6.0F)
			.sound(SoundType.METAL)
			.notSolid());

	public Item itemDoor = new BlockItem(door, new Item.Properties().group(TCItems.TCITEMS));
	public Item itemTrapdoor = new BlockItem(trapdoor, new Item.Properties().group(TCItems.TCITEMS));
	public Item itemBars = new BlockItem(bars, new Item.Properties().group(TCItems.TCITEMS));

	public MaterialVanillaComponents(String matName, String parentMod) {
		this.matName = matName;
		this.parentMod = parentMod;

		door.setRegistryName(new ResourceLocation(Reference.MOD_ID, matName + "door"));
		trapdoor.setRegistryName(new ResourceLocation(Reference.MOD_ID, matName + "trapdoor"));
		bars.setRegistryName(new ResourceLocation(Reference.MOD_ID, matName + "bars"));

		TCBlocks.BLOCKS.add(door);
		TCBlocks.BLOCKS.add(trapdoor);
		TCBlocks.BLOCKS.add(bars);

		itemDoor.setRegistryName(new ResourceLocation(Reference.MOD_ID, matName + "dooritem"));
		itemTrapdoor.setRegistryName(new ResourceLocation(Reference.MOD_ID, matName + "trapdooritem"));
		itemBars.setRegistryName(new ResourceLocation(Reference.MOD_ID, matName + "barsitem"));

		TCItems.ITEMS.add(itemDoor);
		TCItems.ITEMS.add(itemTrapdoor);
		TCItems.ITEMS.add(itemBars);
	}

	@Override
	public void setupClient(MaterialHelper mat) {
		RenderType cutout = RenderType.getCutout();
		RenderTypeLookup.setRenderLayer(this.bars, cutout);
		RenderTypeLookup.setRenderLayer(this.door, cutout);
		RenderTypeLookup.setRenderLayer(this.trapdoor, cutout);
	}

	@Override
	public void setupModels(MaterialHelper mat) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setup(FMLCommonSetupEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupWiki(MaterialHelper mat, PrintWriter out) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupItemTags() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupBlockTags() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupRecipes() {
		// door
		TCRecipes.shaped
				.add(new ImmutablePair<ShapedRecipeBuilder, String>(
						ShapedRecipeBuilder.shapedRecipe(itemDoor)
								.key('i',
										ItemTags.getCollection()
												.getOrCreate(new ResourceLocation(parentMod, "ingots/" + matName)))
								.patternLine("ii")
								.patternLine("ii")
								.patternLine("ii"),
						new ResourceLocation(Reference.MOD_ID, matName + "door1").toString()));

		TCRecipes.shaped
				.add(new ImmutablePair<ShapedRecipeBuilder, String>(
						ShapedRecipeBuilder.shapedRecipe(itemDoor)
								.key('i',
										ItemTags.getCollection()
												.getOrCreate(new ResourceLocation(parentMod, "plates/" + matName)))
								.patternLine("ii")
								.patternLine("ii")
								.patternLine("ii"),
						new ResourceLocation(Reference.MOD_ID, matName + "door2").toString()));

		// bars
		TCRecipes.shaped
				.add(new ImmutablePair<ShapedRecipeBuilder, String>(
						ShapedRecipeBuilder.shapedRecipe(itemBars, 8)
								.key('i',
										ItemTags.getCollection()
												.getOrCreate(new ResourceLocation(Reference.MOD_ID, "rod/" + matName)))
								.patternLine("iii")
								.patternLine("iii"),
						new ResourceLocation(Reference.MOD_ID, matName + "bars1").toString()));

		TCRecipes.shaped
				.add(new ImmutablePair<ShapedRecipeBuilder, String>(
						ShapedRecipeBuilder.shapedRecipe(itemBars, 16)
								.key('i',
										ItemTags.getCollection()
												.getOrCreate(new ResourceLocation(parentMod, "ingots/" + matName)))
								.patternLine("iii")
								.patternLine("iii"),
						new ResourceLocation(Reference.MOD_ID, matName + "bars2").toString()));

		// trapdoor
		TCRecipes.shaped
				.add(new ImmutablePair<ShapedRecipeBuilder, String>(
						ShapedRecipeBuilder.shapedRecipe(itemTrapdoor)
								.key('i',
										ItemTags.getCollection()
												.getOrCreate(new ResourceLocation(parentMod, "ingots/" + matName)))
								.patternLine("ii")
								.patternLine("ii"),
						new ResourceLocation(Reference.MOD_ID, matName + "trapdoor1").toString()));

		TCRecipes.shaped
				.add(new ImmutablePair<ShapedRecipeBuilder, String>(
						ShapedRecipeBuilder.shapedRecipe(itemTrapdoor)
								.key('i',
										ItemTags.getCollection()
												.getOrCreate(new ResourceLocation(parentMod, "plates/" + matName)))
								.patternLine("ii")
								.patternLine("ii"),
						new ResourceLocation(Reference.MOD_ID, matName + "trapdoor2").toString()));
	}

	@Override
	public void setupItemModels(ModelProvider<ItemModelBuilder> mp, ExistingFileHelper fh) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupBlockModels(BlockStateProvider bsp, ExistingFileHelper fh) {
		bsp.doorBlock(door, new ResourceLocation(Reference.MOD_ID, "block/" + matName + "_door_bottom"),
				new ResourceLocation(Reference.MOD_ID, "block/" + matName + "_door_top"));
		bsp.trapdoorBlock(trapdoor, new ResourceLocation(Reference.MOD_ID, "block/" + matName + "trapdoor"), true);
		bsp.paneBlock(bars, new ResourceLocation(Reference.MOD_ID, "block/" + matName + "bars"),
				new ResourceLocation(Reference.MOD_ID, "block/" + matName + "bars"));
	}

	@Override
	public void setupEnglishLocalization(LanguageProvider lang) {
		// TODO Auto-generated method stub

	}

	public class Loot extends BlockLootTables {
		@Override
		protected void addTables() {
			this.registerLootTable(door, dropping(itemDoor));
			this.registerLootTable(trapdoor, dropping(itemTrapdoor));
			this.registerLootTable(bars, dropping(itemBars));
		}

		@Override
		@Nonnull
		protected Iterable<Block> getKnownBlocks() {
			List<Block> l = new ArrayList<Block>();
			l.add(door);
			l.add(bars);
			l.add(trapdoor);
			return l;
		}
	}

	@Override
	public void setupLoot() {
		// TODO Auto-generated method stub

	}

}
