package lance5057.compendium.core.data.builders;

import lance5057.compendium.Reference;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.addons.CraftableMaterial;
import lance5057.compendium.core.library.materialutilities.addons.MaterialExtraComponents;
import lance5057.compendium.core.library.materialutilities.addons.MaterialVanillaComponents;
import lance5057.compendium.core.library.materialutilities.addons.MaterialVanillaTools;
import lance5057.compendium.core.library.materialutilities.addons.MeltableMaterial;
import lance5057.compendium.core.materials.CompendiumMaterials;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.fml.RegistryObject;

public class TCItemModels extends ModelProvider<ItemModelBuilder> {
	private final ExistingFileHelper fh;

	public TCItemModels(DataGenerator generator, ExistingFileHelper fh) {
		super(generator, Reference.MOD_ID, ITEM_FOLDER, ItemModelBuilder::new, fh);
		this.fh = fh;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void registerModels() {
		for (MaterialHelper mh : CompendiumMaterials.materials) {

			// Meltable Materials
			if (mh.getIngot() != null) {
				MeltableMaterial mm = mh.getIngot();

				forItem(mm.INGOT);
				forItem(mm.NUGGET);
				forBlockItem(mm.STORAGE_ITEMBLOCK);
			}

			// Craftable Materials
			if (mh.getGem() != null) {
				CraftableMaterial cm = mh.getGem();

				forItem(cm.GEM);
				forItem(cm.NUGGET);
				forBlockItem(cm.STORAGE_ITEMBLOCK);
			}

			// Vanilla Component Materials
			if (mh.getVanillaComponents() != null) {
				MaterialVanillaComponents vc = mh.getVanillaComponents();

				this.singleTexture(vc.ITEM_DOOR.getId().getPath(), mcLoc("item/handheld"), "layer0",
						modLoc("item/" + mh.name + "door"));
				forBlockItem(vc.ITEM_TRAPDOOR, modLoc("block/" + mh.name + "trapdoor_bottom"));

				// Bars
				this.singleTexture(vc.ITEM_BARS.getId().getPath(), mcLoc("item/handheld"), "layer0",
						modLoc("block/" + mh.name + "bars"));
				
				forBlockItem(vc.ITEM_LANTERN);
			}

			// Extra Component Materials
			if (mh.getExtraComponents() != null) {
				MaterialExtraComponents me = mh.getExtraComponents();

				forItem(me.CASING);
				forItem(me.COIL);
				forItem(me.COIN);
				forItem(me.DUST);
				forItem(me.GEAR);
				forItem(me.PLATE);
				forItem(me.ROD);
				// forItem(me.SHARDS);
				forItem(me.SPRING);
				forItem(me.WIRE);

				forBlockItem(me.ITEM_SHINGLES);
				forBlockItem(me.ITEM_SHINGLES_ALT);
				forBlockItem(me.ITEM_SHINGLES_BLOCK);
				forBlockItem(me.ITEM_STAKE);
				forBlockItem(me.ITEM_SHEET);
			}

			// Vanilla Tools Materials
			if (mh.getVanillaTools() != null) {
				MaterialVanillaTools vt = mh.getVanillaTools();

				withExistingParent(vt.AXE.getId().getPath(), mcLoc("item/handheld"))
						.texture("layer0", modLoc("item/" + vt.AXE.getId().getPath()))
						.texture("layer1", modLoc("item/axebase"));

				withExistingParent(vt.HOE.getId().getPath(), mcLoc("item/handheld"))
						.texture("layer0", modLoc("item/" + vt.HOE.getId().getPath()))
						.texture("layer1", modLoc("item/hoebase"));

				withExistingParent(vt.PICKAXE.getId().getPath(), mcLoc("item/handheld"))
						.texture("layer0", modLoc("item/" + vt.PICKAXE.getId().getPath()))
						.texture("layer1", modLoc("item/pickaxebase"));

				withExistingParent(vt.SHOVEL.getId().getPath(), mcLoc("item/handheld"))
						.texture("layer0", modLoc("item/" + vt.SHOVEL.getId().getPath()))
						.texture("layer1", modLoc("item/shovelbase"));

				withExistingParent(vt.SWORD.getId().getPath(), mcLoc("item/handheld"))
						.texture("layer0", modLoc("item/" + vt.SWORD.getId().getPath()))
						.texture("layer1", modLoc("item/swordbase"));
			}
		}
	}

	private void forItem(RegistryObject<? extends Item> item) {
		this.singleTexture(item.getId().getPath(), mcLoc("item/handheld"), "layer0",
				modLoc("item/" + item.getId().getPath()));
	}

	private void forBlockItem(RegistryObject<? extends BlockItem> item) {
		getBuilder(item.getId().getPath()).parent(new ModelFile.UncheckedModelFile(
				new ResourceLocation(Reference.MOD_ID, "block/" + item.get().getBlock().getRegistryName().getPath())));
	}

	private void forBlockItem(RegistryObject<? extends BlockItem> item, ResourceLocation modelLocation) {
		getBuilder(item.getId().getPath()).parent(new ModelFile.UncheckedModelFile(modelLocation));
	}

	private void forBlockItemWithParent(RegistryObject<? extends BlockItem> item, ResourceLocation modelLocation) {
		singleTexture(item.getId().getPath(), mcLoc("item/generated"), "layer0", modelLocation);
	}

	private void forBlockItemWithParent(RegistryObject<? extends BlockItem> item) {
		singleTexture(item.getId().getPath(), mcLoc("item/generated"), "layer0",
				modLoc("block/" + item.getId().getPath()));
	}

}
