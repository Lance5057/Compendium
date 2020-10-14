package lance5057.compendium.core.data.builders;

import lance5057.compendium.core.library.CompendiumTags;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.CraftableMaterial;
import lance5057.compendium.core.library.materialutilities.addons.MaterialExtraComponents;
import lance5057.compendium.core.library.materialutilities.addons.MeltableMaterial;
import lance5057.compendium.core.materials.CompendiumMaterials;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class TCItemTags extends ItemTagsProvider {

	public TCItemTags(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void registerTags() {
		for (MaterialHelper mh : CompendiumMaterials.materials) {

			// Meltable Materials
			if (mh.getIngot() != null) {
				MeltableMaterial mm = mh.getIngot();
				getBuilder(Tags.Items.INGOTS).add(mm.INGOT.get());
				getBuilder(Tags.Items.NUGGETS).add(mm.NUGGET.get());
				getBuilder(Tags.Items.STORAGE_BLOCKS).add(mm.STORAGE_ITEMBLOCK.get());
				
				Tag<Item> INGOT_MATERIAL = ItemTags.getCollection().getOrCreate(new ResourceLocation("forge", "ingots/" + mh.name));
				Tag<Item> NUGGET_MATERIAL = ItemTags.getCollection().getOrCreate(new ResourceLocation("forge", "nuggets/" + mh.name));
				Tag<Item> BLOCK_MATERIAL = ItemTags.getCollection().getOrCreate(new ResourceLocation("forge", "storage_blocks/" + mh.name));
				
				getBuilder(INGOT_MATERIAL).add(mm.INGOT.get());
				getBuilder(NUGGET_MATERIAL).add(mm.NUGGET.get());
				getBuilder(BLOCK_MATERIAL).add(mm.STORAGE_ITEMBLOCK.get());
			}

			// Craftable Materials
			if (mh.getGem() != null) {
				CraftableMaterial cm = mh.getGem();
				getBuilder(Tags.Items.GEMS).add(cm.GEM.get());
				getBuilder(Tags.Items.NUGGETS).add(cm.NUGGET.get());
				getBuilder(Tags.Items.STORAGE_BLOCKS).add(cm.STORAGE_ITEMBLOCK.get());
				
				Tag<Item> INGOT_MATERIAL = ItemTags.getCollection().getOrCreate(new ResourceLocation("forge", "gems/" + mh.name));
				Tag<Item> NUGGET_MATERIAL = ItemTags.getCollection().getOrCreate(new ResourceLocation("forge", "nuggets/" + mh.name));
				Tag<Item> BLOCK_MATERIAL = ItemTags.getCollection().getOrCreate(new ResourceLocation("forge", "storage_blocks/" + mh.name));
				
				getBuilder(INGOT_MATERIAL).add(cm.GEM.get());
				getBuilder(NUGGET_MATERIAL).add(cm.NUGGET.get());
				getBuilder(BLOCK_MATERIAL).add(cm.STORAGE_ITEMBLOCK.get());
			}
			
			// Extra Components
			if(mh.getExtraComponents() != null)
			{
				MaterialExtraComponents ec = mh.getExtraComponents();
				getBuilder(CompendiumTags.CASING).add(ec.CASING.get());
				getBuilder(CompendiumTags.COIL).add(ec.COIL.get());
				getBuilder(CompendiumTags.COIN).add(ec.COIN.get());
				getBuilder(CompendiumTags.DUST).add(ec.DUST.get());
				getBuilder(CompendiumTags.GEAR).add(ec.GEAR.get());
				getBuilder(CompendiumTags.PLATE).add(ec.PLATE.get());
				getBuilder(CompendiumTags.ROD).add(ec.ROD.get());
				getBuilder(CompendiumTags.SPRING).add(ec.SPRING.get());
				getBuilder(CompendiumTags.WIRE).add(ec.WIRE.get());
				
				Tag<Item> CASING_MATERIAL = ItemTags.getCollection().getOrCreate(new ResourceLocation("forge", "casings/" + mh.name));
				Tag<Item> COIL_MATERIAL = ItemTags.getCollection().getOrCreate(new ResourceLocation("forge", "coils/" + mh.name));
				Tag<Item> COIN_MATERIAL = ItemTags.getCollection().getOrCreate(new ResourceLocation("forge", "coins/" + mh.name));
				Tag<Item> DUST_MATERIAL = ItemTags.getCollection().getOrCreate(new ResourceLocation("forge", "dusts/" + mh.name));
				Tag<Item> GEAR_MATERIAL = ItemTags.getCollection().getOrCreate(new ResourceLocation("forge", "gears/" + mh.name));
				Tag<Item> PLATE_MATERIAL = ItemTags.getCollection().getOrCreate(new ResourceLocation("forge", "plates/" + mh.name));
				Tag<Item> ROD_MATERIAL = ItemTags.getCollection().getOrCreate(new ResourceLocation("forge", "rods/" + mh.name));
				Tag<Item> SPRING_MATERIAL = ItemTags.getCollection().getOrCreate(new ResourceLocation("forge", "springs/" + mh.name));
				Tag<Item> WIRE_MATERIAL = ItemTags.getCollection().getOrCreate(new ResourceLocation("forge", "wires/" + mh.name));
				
				getBuilder(CASING_MATERIAL).add(ec.CASING.get());
				getBuilder(COIL_MATERIAL).add(ec.COIL.get());
				getBuilder(COIN_MATERIAL).add(ec.COIN.get());
				getBuilder(DUST_MATERIAL).add(ec.DUST.get());
				getBuilder(GEAR_MATERIAL).add(ec.GEAR.get());
				getBuilder(PLATE_MATERIAL).add(ec.PLATE.get());
				getBuilder(ROD_MATERIAL).add(ec.ROD.get());
				getBuilder(SPRING_MATERIAL).add(ec.SPRING.get());
				getBuilder(WIRE_MATERIAL).add(ec.WIRE.get());
			}
		}
	}

}
