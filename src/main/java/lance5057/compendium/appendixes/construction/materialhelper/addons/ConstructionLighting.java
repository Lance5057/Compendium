package lance5057.compendium.appendixes.construction.materialhelper.addons;

import java.util.function.Consumer;
import java.util.function.ToIntFunction;

import lance5057.compendium.appendixes.construction.materialhelper.ConstructionMaterialHelper;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.core.blocks.BlockSnuffable;
import lance5057.compendium.core.blocks.BlockSnuffableLantern;
import lance5057.compendium.core.data.builders.TCBlockModels;
import lance5057.compendium.core.data.builders.TCBlockTags;
import lance5057.compendium.core.data.builders.TCEnglishLoc;
import lance5057.compendium.core.data.builders.TCItemModels;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.data.builders.TCRecipes;
import lance5057.compendium.core.data.builders.loottables.BlockLoot;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.fml.RegistryObject;

public class ConstructionLighting implements MaterialBase {

    public RegistryObject<BlockSnuffable> BRAZIER;
    public RegistryObject<BlockSnuffable> SOUL_BRAZIER;
    public RegistryObject<BlockSnuffableLantern> LANTERN;

    public RegistryObject<BlockNamedItem> BRAZIER_ITEMBLOCK;
    public RegistryObject<BlockNamedItem> SOUL_BRAZIER_ITEMBLOCK;
    public RegistryObject<BlockNamedItem> LANTERN_ITEMBLOCK;

    public ConstructionLighting(ConstructionMaterialHelper constructionMaterialHelper) {
	// TODO Auto-generated constructor stub
    }

    @Override
    public void setupClient(MaterialHelperBase mat) {
	RenderType cutout = RenderType.getCutout();
	RenderTypeLookup.setRenderLayer(BRAZIER.get(), cutout);
	RenderTypeLookup.setRenderLayer(SOUL_BRAZIER.get(), cutout);
	RenderTypeLookup.setRenderLayer(LANTERN.get(), cutout);
    }

    @Override
    public void setup(MaterialHelperBase mat) {
	BRAZIER = mat.BLOCKS.register(mat.name + "_brazier",
		() -> new BlockSnuffable(Items.FLINT_AND_STEEL, AbstractBlock.Properties.create(Material.REDSTONE_LIGHT)
			.setLightLevel(getLightValueLit(15)).hardnessAndResistance(0.3F).sound(SoundType.METAL)));
	SOUL_BRAZIER = mat.BLOCKS.register(mat.name + "_soul_brazier",
		() -> new BlockSnuffable(Items.FLINT_AND_STEEL, AbstractBlock.Properties.create(Material.REDSTONE_LIGHT)
			.setLightLevel(getLightValueLit(15)).hardnessAndResistance(0.3F).sound(SoundType.METAL)));
	LANTERN = mat.BLOCKS.register(mat.name + "_lantern",
		() -> new BlockSnuffableLantern(Items.FLINT_AND_STEEL,
			AbstractBlock.Properties.create(Material.REDSTONE_LIGHT).setLightLevel(getLightValueLit(15))
				.hardnessAndResistance(0.3F).sound(SoundType.METAL)));

	BRAZIER_ITEMBLOCK = mat.ITEMS.register(mat.name + "_brazier_itemblock", () -> new BlockNamedItem(BRAZIER.get(),
		new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	SOUL_BRAZIER_ITEMBLOCK = mat.ITEMS.register(mat.name + "_soul_brazier_itemblock", () -> new BlockNamedItem(SOUL_BRAZIER.get(),
		new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	LANTERN_ITEMBLOCK = mat.ITEMS.register(mat.name + "_lantern_itemblock", () -> new BlockNamedItem(LANTERN.get(),
		new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
    }

    public static void registerBlockModels(ConstructionLighting m, TCBlockModels model, String name) {
	ModelFile brazier = model.models().withExistingParent(name + "_brazier", model.modLoc("block/bases/brazier"))
		.texture("0", "compendium:block/material/" + name + "/" + name + "topbars");
	ModelFile brazier_off = model.models()
		.withExistingParent(name + "_brazier_out", model.modLoc("block/bases/brazier_out"))
		.texture("0", "compendium:block/material/" + name + "/" + name + "topbars");

	model.getVariantBuilder(m.BRAZIER.get()).partialState().with(BlockStateProperties.LIT, true)
		.addModels(new ConfiguredModel(brazier));
	model.getVariantBuilder(m.BRAZIER.get()).partialState().with(BlockStateProperties.LIT, false)
		.addModels(new ConfiguredModel(brazier_off));

	ModelFile soul_brazier = model.models()
		.withExistingParent(name + "_soul_brazier", model.modLoc("block/bases/brazier"))
		.texture("0", "compendium:block/material/" + name + "/" + name + "topbars")
		.texture("1", model.mcLoc("block/soul_sand")).texture("3", model.mcLoc("block/soul_fire_1"));
	ModelFile soul_brazier_off = model.models()
		.withExistingParent(name + "_soul_brazier_out", model.modLoc("block/bases/brazier_out"))
		.texture("0", "compendium:block/material/" + name + "/" + name + "topbars")
		.texture("1", model.mcLoc("block/soul_sand")).texture("3", model.mcLoc("block/soul_fire_1"));

	model.getVariantBuilder(m.SOUL_BRAZIER.get()).partialState().with(BlockStateProperties.LIT, true)
		.addModels(new ConfiguredModel(soul_brazier));
	model.getVariantBuilder(m.SOUL_BRAZIER.get()).partialState().with(BlockStateProperties.LIT, false)
		.addModels(new ConfiguredModel(soul_brazier_off));
	
	ModelFile lantern = model.models()
		.withExistingParent(name + "_lantern", model.modLoc("block/bases/lantern"))
		.texture("all", "compendium:block/material/" + name + "/" + name + "lantern");
	ModelFile hanging_lantern = model.models()
		.withExistingParent(name + "_lantern_hanging", model.modLoc("block/bases/hanging_lantern"))
		.texture("all", "compendium:block/material/" + name + "/" + name + "lantern");

	model.getVariantBuilder(m.LANTERN.get()).partialState().with(BlockStateProperties.HANGING, false)
		.addModels(new ConfiguredModel(lantern));
	model.getVariantBuilder(m.LANTERN.get()).partialState().with(BlockStateProperties.HANGING, true)
		.addModels(new ConfiguredModel(hanging_lantern));
    }

    public static void registerItemModels(ConstructionLighting m, TCItemModels model, String name) {
	model.forBlockItem(m.BRAZIER_ITEMBLOCK, name);
	model.forBlockItem(m.SOUL_BRAZIER_ITEMBLOCK, name);
	model.forBlockItem(m.LANTERN_ITEMBLOCK, name);
    }

    public static void addTranslations(ConstructionLighting m, TCEnglishLoc loc, String capName) {
	loc.add(m.BRAZIER.get(), capName + " Brazier");
	loc.add(m.SOUL_BRAZIER.get(), capName + " Soul Brazier");
	loc.add(m.LANTERN.get(), capName + " Lantern");
    }

    public static void registerTags(ConstructionLighting m, TCItemTags itp, String name) {
    }

    public static void buildLootTable(ConstructionLighting b, BlockLoot table, String name) {
	table.registerDropSelfLootTable(b.BRAZIER.get());
	table.registerDropSelfLootTable(b.SOUL_BRAZIER.get());
	table.registerDropSelfLootTable(b.LANTERN.get());
    }

    public static void buildRecipes(ConstructionLighting constructionLighting, TCRecipes recipes,
	    Consumer<IFinishedRecipe> consumer, String name) {
    }

    public static void registerBlockTags(ConstructionLighting base, TCBlockTags btp, String name) {
	// TODO Auto-generated method stub

    }

    protected ToIntFunction<BlockState> getLightValueLit(int lightValue) {
	return (state) -> {
	    return state.get(BlockStateProperties.LIT) ? lightValue : 0;
	};
    }
}
