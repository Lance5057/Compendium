package lance5057.compendium.core.data.builders;

import java.util.List;
import java.util.function.Function;

import lance5057.compendium.appendixes.gemology.materialhelper.addons.BasicGemMaterial;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.BasicMetalMaterial;
import lance5057.compendium.core.library.CompendiumTags;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.MaterialAdvancedExtraComponents;
import lance5057.compendium.core.library.materialutilities.addons.MaterialExtraComponents;
import lance5057.compendium.core.library.materialutilities.addons.PremadeMaterial;
import lance5057.compendium.core.materials.CompendiumMaterials;
import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class TCItemTags extends ItemTagsProvider {

    public TCItemTags(DataGenerator generatorIn, BlockTagsProvider blockTagProvider) {
	super(generatorIn, blockTagProvider);
    }

    @Override
    protected void registerTags() {
	for (MaterialHelper mh : CompendiumMaterials.materials) {

	    // Premade Materials
	    if (mh.getPremade() != null) {
		PremadeMaterial mm = mh.getPremade();

		if (mm.INGOT != null) {
		    getOrCreateBuilder(Tags.Items.INGOTS).add(mm.INGOT.get());
		    INamedTag<Item> INGOT_MATERIAL = ItemTag("ingots/" + mh.name);
		    getOrCreateBuilder(INGOT_MATERIAL).add(mm.INGOT.get());
		}

		if (mm.NUGGET != null) {
		    getOrCreateBuilder(Tags.Items.NUGGETS).add(mm.NUGGET.get());
		    INamedTag<Item> NUGGET_MATERIAL = ItemTag("nuggets/" + mh.name);
		    getOrCreateBuilder(NUGGET_MATERIAL).add(mm.NUGGET.get());
		}

		if (mm.STORAGE_ITEMBLOCK != null) {
		    getOrCreateBuilder(Tags.Items.STORAGE_BLOCKS).add(mm.STORAGE_ITEMBLOCK.get());
		    INamedTag<Item> BLOCK_MATERIAL = ItemTag("storage_blocks/" + mh.name);
		    getOrCreateBuilder(BLOCK_MATERIAL).add(mm.STORAGE_ITEMBLOCK.get());
		}
	    }

	    // Meltable Materials
	    if (mh.getIngot() != null) {
		BasicMetalMaterial mm = mh.getIngot();
		getOrCreateBuilder(Tags.Items.INGOTS).add(mm.INGOT.get());
		getOrCreateBuilder(Tags.Items.NUGGETS).add(mm.NUGGET.get());
		getOrCreateBuilder(Tags.Items.STORAGE_BLOCKS).add(mm.STORAGE_ITEMBLOCK.get());

		INamedTag<Item> INGOT_MATERIAL = ItemTag("ingots/" + mh.name);
		INamedTag<Item> NUGGET_MATERIAL = ItemTag("nuggets/" + mh.name);
		INamedTag<Item> BLOCK_MATERIAL = ItemTag("storage_blocks/" + mh.name);

		getOrCreateBuilder(INGOT_MATERIAL).add(mm.INGOT.get());
		getOrCreateBuilder(NUGGET_MATERIAL).add(mm.NUGGET.get());
		getOrCreateBuilder(BLOCK_MATERIAL).add(mm.STORAGE_ITEMBLOCK.get());
	    }

	    // Craftable Materials
	    if (mh.getGem() != null) {
		BasicGemMaterial cm = mh.getGem();
		getOrCreateBuilder(Tags.Items.GEMS).add(cm.GEM.get());
		getOrCreateBuilder(Tags.Items.NUGGETS).add(cm.SHARD.get());
		getOrCreateBuilder(Tags.Items.STORAGE_BLOCKS).add(cm.STORAGE_ITEMBLOCK.get());

		INamedTag<Item> INGOT_MATERIAL = ItemTag("gems/" + mh.name);
		INamedTag<Item> NUGGET_MATERIAL = ItemTag("nuggets/" + mh.name);
		INamedTag<Item> BLOCK_MATERIAL = ItemTag("storage_blocks/" + mh.name);

		getOrCreateBuilder(INGOT_MATERIAL).add(cm.GEM.get());
		getOrCreateBuilder(NUGGET_MATERIAL).add(cm.SHARD.get());
		getOrCreateBuilder(BLOCK_MATERIAL).add(cm.STORAGE_ITEMBLOCK.get());
	    }

	    // Extra Components
	    if (mh.getExtraComponents() != null) {
		MaterialExtraComponents ec = mh.getExtraComponents();

		getOrCreateBuilder(CompendiumTags.COIN).add(ec.COIN.get());
		getOrCreateBuilder(CompendiumTags.DUST).add(ec.DUST.get());
		getOrCreateBuilder(CompendiumTags.GEAR).add(ec.GEAR.get());
		getOrCreateBuilder(CompendiumTags.PLATE).add(ec.PLATE.get());
		getOrCreateBuilder(CompendiumTags.ROD).add(ec.ROD.get());

		INamedTag<Item> COIN_MATERIAL = ItemTag("coins/" + mh.name);
		INamedTag<Item> DUST_MATERIAL = ItemTag("dusts/" + mh.name);
		INamedTag<Item> GEAR_MATERIAL = ItemTag("gears/" + mh.name);
		INamedTag<Item> PLATE_MATERIAL = ItemTag("plates/" + mh.name);
		INamedTag<Item> ROD_MATERIAL = ItemTag("rods/" + mh.name);

		getOrCreateBuilder(COIN_MATERIAL).add(ec.COIN.get());
		getOrCreateBuilder(DUST_MATERIAL).add(ec.DUST.get());
		getOrCreateBuilder(GEAR_MATERIAL).add(ec.GEAR.get());
		getOrCreateBuilder(PLATE_MATERIAL).add(ec.PLATE.get());
		getOrCreateBuilder(ROD_MATERIAL).add(ec.ROD.get());

	    }

	    // Advanced Extra Component Materials
	    if (mh.getAdvancedComponents() != null) {
		MaterialAdvancedExtraComponents ec = mh.getAdvancedComponents();

		getOrCreateBuilder(CompendiumTags.CASING).add(ec.CASING.get());
		getOrCreateBuilder(CompendiumTags.COIL).add(ec.COIL.get());
		getOrCreateBuilder(CompendiumTags.SPRING).add(ec.SPRING.get());
		getOrCreateBuilder(CompendiumTags.WIRE).add(ec.WIRE.get());

		INamedTag<Item> CASING_MATERIAL = ItemTag("casings/" + mh.name);
		INamedTag<Item> COIL_MATERIAL = ItemTag("coils/" + mh.name);
		INamedTag<Item> SPRING_MATERIAL = ItemTag("springs/" + mh.name);
		INamedTag<Item> WIRE_MATERIAL = ItemTag("wires/" + mh.name);

		getOrCreateBuilder(CASING_MATERIAL).add(ec.CASING.get());
		getOrCreateBuilder(COIL_MATERIAL).add(ec.COIL.get());
		getOrCreateBuilder(SPRING_MATERIAL).add(ec.SPRING.get());
		getOrCreateBuilder(WIRE_MATERIAL).add(ec.WIRE.get());
	    }
	}
    }

    private static <T> ITag.INamedTag<T> getOrRegister(List<? extends ITag.INamedTag<T>> list,
	    Function<ResourceLocation, ITag.INamedTag<T>> register, ResourceLocation loc) {
	for (ITag.INamedTag<T> existing : list) {
	    if (existing.getName().equals(loc)) {
		return existing;
	    }
	}

	return register.apply(loc);
    }

    public static ITag.INamedTag<Block> BlockTag(String name) {
	return getOrRegister(BlockTags.getAllTags(), loc -> BlockTags.makeWrapperTag(loc.toString()),
		new ResourceLocation("forge", name));
    }

    public static ITag.INamedTag<Item> ItemTag(String name) {
	return getOrRegister(ItemTags.getAllTags(), loc -> ItemTags.makeWrapperTag(loc.toString()),
		new ResourceLocation("forge", name));
    }

}
