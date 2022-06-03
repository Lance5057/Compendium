package lance5057.compendium.core.data.builders;

import lance5057.compendium.Compendium;
import lance5057.compendium.CompendiumMaterials;
import lance5057.compendium.Reference;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TCItemTags extends ItemTagsProvider {

	public TCItemTags(DataGenerator generatorIn, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
		super(generatorIn, blockTagProvider, Reference.MOD_ID, existingFileHelper);
		Compendium.logger.info("\t - Item Tags");
	}

	@Override
	protected void addTags() {
		for(MaterialHelper m : CompendiumMaterials.materials)
		{
			m.addItemTags(this);
		}
//
//		this.tag(CompendiumTags.HAMMER).add(CompendiumItems.CRUDE_HAMMER.get());
//		TagKey<Item> HAMMER_MATERIAL = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "tools/hammers/crude"));
//		this.tag(HAMMER_MATERIAL).add(CompendiumItems.CRUDE_HAMMER.get());
//
//		MetalItemTags.registerTags(this);
//		GemItemTags.registerTags(this);
//		ConstructionItemTags.registerTags(this);
//		CarpentryItemTags.registerTags(this);
//
//		// add vanilla wood
//		tag(CompendiumTags.PLANK).add(Items.OAK_PLANKS);
//		TagKey<Item> OAK_PLANKS_MATERIAL = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "planks/oak"));
//		tag(OAK_PLANKS_MATERIAL).add(Items.OAK_PLANKS);
//
//		tag(CompendiumTags.PLANK).add(Items.ACACIA_PLANKS);
//		TagKey<Item> ACACIA_PLANKS_MATERIAL = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "planks/acacia"));
//		tag(ACACIA_PLANKS_MATERIAL).add(Items.ACACIA_PLANKS);
//
//		tag(CompendiumTags.PLANK).add(Items.BIRCH_PLANKS);
//		TagKey<Item> BIRCH_PLANKS_MATERIAL = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "planks/birch"));
//		tag(BIRCH_PLANKS_MATERIAL).add(Items.BIRCH_PLANKS);
//
//		tag(CompendiumTags.PLANK).add(Items.CRIMSON_PLANKS);
//		TagKey<Item> CRIMSON_PLANKS_MATERIAL = ItemTags
//				.create(new ResourceLocation(Reference.MOD_ID, "planks/crimson"));
//		tag(CRIMSON_PLANKS_MATERIAL).add(Items.CRIMSON_PLANKS);
//
//		tag(CompendiumTags.PLANK).add(Items.DARK_OAK_PLANKS);
//		TagKey<Item> DARK_OAK_PLANKS_MATERIAL = ItemTags
//				.create(new ResourceLocation(Reference.MOD_ID, "planks/dark_oak"));
//		tag(DARK_OAK_PLANKS_MATERIAL).add(Items.DARK_OAK_PLANKS);
//
//		tag(CompendiumTags.PLANK).add(Items.JUNGLE_PLANKS);
//		TagKey<Item> JUNGLE_PLANKS_MATERIAL = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "planks/jungle"));
//		tag(JUNGLE_PLANKS_MATERIAL).add(Items.JUNGLE_PLANKS);
//
//		tag(CompendiumTags.PLANK).add(Items.SPRUCE_PLANKS);
//		TagKey<Item> SPRUCE_PLANKS_MATERIAL = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "planks/spruce"));
//		tag(SPRUCE_PLANKS_MATERIAL).add(Items.SPRUCE_PLANKS);
//
//		tag(CompendiumTags.PLANK).add(Items.WARPED_PLANKS);
//		TagKey<Item> WARPED_PLANKS_MATERIAL = ItemTags.create(new ResourceLocation(Reference.MOD_ID, "planks/warped"));
//		tag(WARPED_PLANKS_MATERIAL).add(Items.WARPED_PLANKS);

//	for (MaterialHelper mh : CompendiumMaterials.materials) {
//
//	    // Premade Materials
//	    if (mh.getPremade() != null) {
//		PremadeMaterial mm = mh.getPremade();
//
//		if (mm.INGOT != null) {
//		    tag(Tags.Items.INGOTS).add(mm.INGOT.get());
//		    TagKey<Item> INGOT_MATERIAL = ItemTag("ingots/" + mh.name);
//		    tag(INGOT_MATERIAL).add(mm.INGOT.get());
//		}
//
//		if (mm.NUGGET != null) {
//		    tag(Tags.Items.NUGGETS).add(mm.NUGGET.get());
//		    TagKey<Item> NUGGET_MATERIAL = ItemTag("nuggets/" + mh.name);
//		    tag(NUGGET_MATERIAL).add(mm.NUGGET.get());
//		}
//
//		if (mm.STORAGE_ITEMBLOCK != null) {
//		    tag(Tags.Items.STORAGE_BLOCKS).add(mm.STORAGE_ITEMBLOCK.get());
//		    TagKey<Item> BLOCK_MATERIAL = ItemTag("storage_blocks/" + mh.name);
//		    tag(BLOCK_MATERIAL).add(mm.STORAGE_ITEMBLOCK.get());
//		}
//	    }
//
//	    // Meltable Materials
//	    if (mh.getIngot() != null) {
//		BasicMetalMaterial mm = mh.getIngot();
//		tag(Tags.Items.INGOTS).add(mm.INGOT.get());
//		tag(Tags.Items.NUGGETS).add(mm.NUGGET.get());
//		tag(Tags.Items.STORAGE_BLOCKS).add(mm.STORAGE_ITEMBLOCK.get());
//
//		TagKey<Item> INGOT_MATERIAL = ItemTag("ingots/" + mh.name);
//		TagKey<Item> NUGGET_MATERIAL = ItemTag("nuggets/" + mh.name);
//		TagKey<Item> BLOCK_MATERIAL = ItemTag("storage_blocks/" + mh.name);
//
//		tag(INGOT_MATERIAL).add(mm.INGOT.get());
//		tag(NUGGET_MATERIAL).add(mm.NUGGET.get());
//		tag(BLOCK_MATERIAL).add(mm.STORAGE_ITEMBLOCK.get());
//	    }
//
//	    // Craftable Materials
//	    if (mh.getGem() != null) {
//		BasicGemMaterial cm = mh.getGem();
//		tag(Tags.Items.GEMS).add(cm.GEM.get());
//		tag(Tags.Items.NUGGETS).add(cm.SHARD.get());
//		tag(Tags.Items.STORAGE_BLOCKS).add(cm.STORAGE_ITEMBLOCK.get());
//
//		TagKey<Item> INGOT_MATERIAL = ItemTag("gems/" + mh.name);
//		TagKey<Item> NUGGET_MATERIAL = ItemTag("nuggets/" + mh.name);
//		TagKey<Item> BLOCK_MATERIAL = ItemTag("storage_blocks/" + mh.name);
//
//		tag(INGOT_MATERIAL).add(cm.GEM.get());
//		tag(NUGGET_MATERIAL).add(cm.SHARD.get());
//		tag(BLOCK_MATERIAL).add(cm.STORAGE_ITEMBLOCK.get());
//	    }
//
//	    // Extra Components
//	    if (mh.getExtraComponents() != null) {
//		MaterialExtraComponents ec = mh.getExtraComponents();
//
//		tag(CompendiumTags.COIN).add(ec.COIN.get());
//		tag(CompendiumTags.DUST).add(ec.DUST.get());
//		tag(CompendiumTags.GEAR).add(ec.GEAR.get());
//		tag(CompendiumTags.PLATE).add(ec.PLATE.get());
//		tag(CompendiumTags.ROD).add(ec.ROD.get());
//
//		TagKey<Item> COIN_MATERIAL = ItemTag("coins/" + mh.name);
//		TagKey<Item> DUST_MATERIAL = ItemTag("dusts/" + mh.name);
//		TagKey<Item> GEAR_MATERIAL = ItemTag("gears/" + mh.name);
//		TagKey<Item> PLATE_MATERIAL = ItemTag("plates/" + mh.name);
//		TagKey<Item> ROD_MATERIAL = ItemTag("rods/" + mh.name);
//
//		tag(COIN_MATERIAL).add(ec.COIN.get());
//		tag(DUST_MATERIAL).add(ec.DUST.get());
//		tag(GEAR_MATERIAL).add(ec.GEAR.get());
//		tag(PLATE_MATERIAL).add(ec.PLATE.get());
//		tag(ROD_MATERIAL).add(ec.ROD.get());
//
//	    }
//
//	    // Advanced Extra Component Materials
//	    if (mh.getAdvancedComponents() != null) {
//		MaterialAdvancedExtraComponents ec = mh.getAdvancedComponents();
//
//		tag(CompendiumTags.CASING).add(ec.CASING.get());
//		tag(CompendiumTags.COIL).add(ec.COIL.get());
//		tag(CompendiumTags.SPRING).add(ec.SPRING.get());
//		tag(CompendiumTags.WIRE).add(ec.WIRE.get());
//
//		TagKey<Item> CASING_MATERIAL = ItemTag("casings/" + mh.name);
//		TagKey<Item> COIL_MATERIAL = ItemTag("coils/" + mh.name);
//		TagKey<Item> SPRING_MATERIAL = ItemTag("springs/" + mh.name);
//		TagKey<Item> WIRE_MATERIAL = ItemTag("wires/" + mh.name);
//
//		tag(CASING_MATERIAL).add(ec.CASING.get());
//		tag(COIL_MATERIAL).add(ec.COIL.get());
//		tag(SPRING_MATERIAL).add(ec.SPRING.get());
//		tag(WIRE_MATERIAL).add(ec.WIRE.get());
//	    }
//	}
	}

//	private static <T> TagKey<T> getOrRegister(List<? extends TagKey<T>> list,
//			Function<ResourceLocation, TagKey<T>> register, ResourceLocation loc) {
//		for (TagKey<T> existing : list) {
//			if (existing.location().equals(loc)) {
//				return existing;
//			}
//		}
//
//		return register.apply(loc);
//	}
//
//	public static TagKey<Block> BlockTag(String name) {
//		return getOrRegister(BlockTags.getWrappers(), loc -> BlockTags.makeWrapperTag(loc.toString()),
//				new ResourceLocation("forge", name));
//	}
//
//	public static TagKey<Item> ItemTag(String name) {
//		return ItemTags.create(new ResourceLocation("forge", name));
//	}

}
