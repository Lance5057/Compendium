	package lance5057.compendium.core.data.builders;

import lance5057.compendium.Compendium;
import lance5057.compendium.CompendiumMaterials;
import lance5057.compendium.Reference;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class TCItemModels extends ModelProvider<ItemModelBuilder> {
    private final ExistingFileHelper fh;

    public TCItemModels(DataGenerator generator, ExistingFileHelper fh) {
	super(generator, Reference.MOD_ID, ITEM_FOLDER, ItemModelBuilder::new, fh);
	Compendium.logger.info("\t - Item Models");
	this.fh = fh;
    }

    @Override
    public String getName() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected void registerModels() {
    	
    	for(MaterialHelper m : CompendiumMaterials.materials)
		{
			m.registerItemModels(this);
		}
//	getBuilder(CompendiumItems.MEGALITH_STONE.getId().getPath())
//		.parent(new ModelFile.UncheckedModelFile("block/bases/megalith_stone"));
//	this.withExistingParent(CompendiumItems.MEGALITH_STONE.getId().getPath(),
//		"compendium:block/bases/megalith_stone");
//
//	forBlockItem(CompendiumItems.CRAFTING_ANVIL_ITEMBLOCK,
//		new ResourceLocation(Reference.MOD_ID, "block/workstations/anvil"));
//	forBlockItem(CompendiumItems.HAMMERING_STATION_ITEMBLOCK,
//		new ResourceLocation(Reference.MOD_ID, "block/workstations/hammeringtable"));
//	forBlockItem(CompendiumItems.SAWHORSE_STATION_ITEMBLOCK,
//		new ResourceLocation(Reference.MOD_ID, "block/workstations/sawhorse"));
//	forBlockItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK,
//		new ResourceLocation(Reference.MOD_ID, "block/workstations/dismantling_table"));
//
//	forBlockItem(CompendiumItems.DRYLAKEBED_ITEMBLOCK, "drylakebed");
//
////	forBlockItem(CompendiumItems.ITEM_SHINGLES, "bases/empty_shingles");
////	forBlockItem(CompendiumItems.ITEM_SHINGLES_ALT, "bases/empty_shinglesalt");
//
//	forItem(CompendiumItems.CRUDE_HAMMER, "crudehammer");
//	forItem(CompendiumItems.MINER_GRENADE, "grenade");
//	forItem(CompendiumItems.SAWDUST, "sawdust");
//	forItem(CompendiumItems.BARK, "bark");
//
//	MetalItemModels.registerModels(this);
//	GemItemModels.registerModels(this);
//	OreItemModels.registerModels(this);
//	ConstructionItemModels.registerModels(this);
//	CarpentryItemModels.registerModels(this);

//	for (MaterialHelper mh : CompendiumMaterials.materials) {
//	    // Premade Materials
//	    if (mh.getPremade() != null) {
//		PremadeMaterial pm = mh.getPremade();
//
//		if (pm.INGOT != null)
//		    forItem(pm.INGOT, mh.name);
//		if (pm.NUGGET != null)
//		    forItem(pm.NUGGET, mh.name);
//		if (pm.STORAGE_ITEMBLOCK != null)
//		    forBlockItem(pm.STORAGE_ITEMBLOCK, mh.name);
//	    }
//
//	    // Meltable Materials
//	    if (mh.getIngot() != null) {
//		BasicMetalMaterial mm = mh.getIngot();
//
//		forItem(mm.INGOT, mh.name);
//		forItem(mm.NUGGET, mh.name);
//		forBlockItem(mm.STORAGE_ITEMBLOCK, mh.name);
//	    }
//
//	    // Craftable Materials
//	    if (mh.getGem() != null) {
//		BasicGemMaterial cm = mh.getGem();
//
//		forItem(cm.GEM, mh.name);
//		forItem(cm.SHARD, mh.name);
//		forBlockItem(cm.STORAGE_ITEMBLOCK, mh.name);
//	    }
//
//	    // Vanilla Component Materials
//	    if (mh.getVanillaComponents() != null) {
//		MaterialVanillaComponents vc = mh.getVanillaComponents();
//
//		this.singleTexture(vc.ITEM_DOOR.getId().getPath(), mcLoc("item/InteractionHandheld"), "layer0",
//			modLoc("item/material/" + mh.name + "/" + mh.name + "door"));
//		forBlockItem(vc.ITEM_TRAPDOOR, modLoc("block/" + mh.name + "trapdoor_bottom"));
//
//		// Bars
//		this.singleTexture(vc.ITEM_BARS.getId().getPath(), mcLoc("item/InteractionHandheld"), "layer0",
//			modLoc("block/material/" + mh.name + "/" + mh.name + "bars"));
//
//		forBlockItem(vc.ITEM_LANTERN, mh.name);
//		forBlockItem(vc.ITEM_CHAIN, mh.name);
//	    }
//
//	    // Extra Component Materials
//	    if (mh.getExtraComponents() != null) {
//		MaterialExtraComponents me = mh.getExtraComponents();
//
//		forItem(me.COIN, mh.name);
//		forItem(me.DUST, mh.name);
//		forItem(me.GEAR, mh.name);
//		forItem(me.PLATE, mh.name);
//		forItem(me.ROD, mh.name);
//		// forItem(me.SHARDS);
//
//		forBlockItem(me.ITEM_SHEET, mh.name);
//		forBlockItem(me.ITEM_SHEET_BLOCK, mh.name);
//		forBlockItem(me.ITEM_SHINGLES, mh.name);
//		forBlockItem(me.ITEM_SHINGLES_ALT, mh.name);
//		forBlockItem(me.ITEM_SHINGLES_BLOCK, mh.name);
//
//		forBlockItem(me.ITEM_STAKE, mh.name);
//
//		this.singleTexture(me.ITEM_TRIMMED_WINDOW.getId().getPath(), mcLoc("item/InteractionHandheld"), "layer0",
//			modLoc("block/material/" + mh.name + "/" + mh.name + "trimmedglass"));
//		forBlockItem(me.ITEM_TRIMMED_WINDOW_BLOCK, mh.name);
//
//	    }
//
//	    // Advanced Extra Component Materials
//	    if (mh.getAdvancedComponents() != null) {
//		MaterialAdvancedExtraComponents me = mh.getAdvancedComponents();
//
//		forItem(me.CASING, mh.name);
//		forItem(me.COIL, mh.name);
//		forItem(me.SPRING, mh.name);
//		forItem(me.WIRE, mh.name);
//		forItem(me.CLASP, mh.name);
//		forItem(me.RINGSHANK, mh.name);
//		forItem(me.RIVETS, mh.name);
//		forItem(me.SETTING, mh.name);
//		forItem(me.JUMPRINGS, mh.name);
//		forItem(me.FILIGREE, mh.name);
//		forItem(me.FOIL, mh.name);
//
//		forBlockItem(me.ITEM_BIGCHAIN, mh.name);
//		forBlockItem(me.ITEM_BRAZIER, mh.name);
//		forBlockItem(me.ITEM_BAR_DOOR, modLoc("block/" + mh.name + "slidingdoor_closed"));
//		this.singleTexture(me.ITEM_CHAINLINK_BARS.getId().getPath(), mcLoc("item/InteractionHandheld"), "layer0",
//			modLoc("block/material/" + mh.name + "/" + mh.name + "chainlink"));
//		forBlockItem(me.ITEM_CHAINLINK_BLOCK, mh.name);
//		forBlockItem(me.ITEM_SOUL_BRAZIER, mh.name);
//
//		forBlockItem(me.ITEM_WALL, mcLoc("block/cobblestone_wall_inventory"), "wall",
//			modLoc("block/material/" + mh.name + "/" + mh.name + "wall"));
//		forBlockItem(me.ITEM_SMALL_TILE, mh.name);
//
//		forBlockItem(me.ITEM_ENCASED_GLOWSTONE, mh.name);
//		this.singleTexture(me.ITEM_TOP_BARS.getId().getPath(), mcLoc("item/InteractionHandheld"), "layer0",
//			modLoc("block/material/" + mh.name + "/" + mh.name + "topbars"));
//
//		// Bars
//		this.singleTexture(me.ITEM_DIAMONDBARS.getId().getPath(), mcLoc("item/InteractionHandheld"), "layer0",
//			modLoc("block/material/" + mh.name + "/" + mh.name + "diamondbars"));
//		this.singleTexture(me.ITEM_DIAMONDBARSFLIP.getId().getPath(), mcLoc("item/InteractionHandheld"), "layer0",
//			modLoc("block/material/" + mh.name + "/" + mh.name + "diamondbarsflip"));
//		this.singleTexture(me.ITEM_DIAMONDBARSTOP.getId().getPath(), mcLoc("item/InteractionHandheld"), "layer0",
//			modLoc("block/material/" + mh.name + "/" + mh.name + "diamondbar_top"));
//		
//		 forBlockItem(me.VAULT_ITEMBLOCK, "bases/vault");
//	    }
//
//	    // Vanilla Tools Materials
//	    if (mh.getVanillaTools() != null) {
//		MaterialVanillaTools vt = mh.getVanillaTools();
//
//		withExistingParent(vt.AXE.getId().getPath(), mcLoc("item/InteractionHandheld"))
//			.texture("layer0", modLoc("item/material/" + mh.name + "/" + vt.AXE.getId().getPath()))
//			.texture("layer1", modLoc("item/axebase"));
//
//		withExistingParent(vt.HOE.getId().getPath(), mcLoc("item/InteractionHandheld"))
//			.texture("layer0", modLoc("item/material/" + mh.name + "/" + vt.HOE.getId().getPath()))
//			.texture("layer1", modLoc("item/hoebase"));
//
//		withExistingParent(vt.PICKAXE.getId().getPath(), mcLoc("item/InteractionHandheld"))
//			.texture("layer0", modLoc("item/material/" + mh.name + "/" + vt.PICKAXE.getId().getPath()))
//			.texture("layer1", modLoc("item/pickaxebase"));
//
//		withExistingParent(vt.SHOVEL.getId().getPath(), mcLoc("item/InteractionHandheld"))
//			.texture("layer0", modLoc("item/material/" + mh.name + "/" + vt.SHOVEL.getId().getPath()))
//			.texture("layer1", modLoc("item/shovelbase"));
//
//		withExistingParent(vt.SWORD.getId().getPath(), mcLoc("item/InteractionHandheld"))
//			.texture("layer0", modLoc("item/material/" + mh.name + "/" + vt.SWORD.getId().getPath()))
//			.texture("layer1", modLoc("item/swordbase"));
//	    }
//
//	    // Vanilla Tools Materials
//	    if (mh.getExtraTools() != null) {
//		MaterialExtraTools et = mh.getExtraTools();
//
//		withExistingParent(et.HAMMER.getId().getPath(), mcLoc("item/InteractionHandheld"))
//			.texture("layer0", modLoc("item/material/" + mh.name + "/" + et.HAMMER.getId().getPath()))
//			.texture("layer1", modLoc("item/hammerbase"));
//		withExistingParent(et.SAW.getId().getPath(), mcLoc("item/InteractionHandheld"))
//			.texture("layer0", modLoc("item/material/" + mh.name + "/" + et.SAW.getId().getPath()))
//			.texture("layer1", modLoc("item/sawbase"));
//	    }
//
//	    // Ore
//	    if (mh.getOre() != null) {
//		MaterialOre mo = mh.getOre();
//
//		forBlockItem(mo.ITEM_ORE, mh.name);
//	    }
//	}
    }

    public void forItem(RegistryObject<? extends Item> item, String name) {
		this.singleTexture(item.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/" + name));
    }

    public void forMaterialItem(RegistryObject<? extends Item> item, String name) {
	this.singleTexture(item.getId().getPath(), mcLoc("item/generated"), "layer0",
		modLoc("item/material/" + name + "/" + item.getId().getPath()));
    }

    public void forBlockItem(RegistryObject<? extends BlockItem> item, String name) {
	getBuilder(item.getId().getPath()).parent(new ModelFile.UncheckedModelFile(
		new ResourceLocation(Reference.MOD_ID, "block/" + item.get().getBlock().getRegistryName().getPath())));
    }

    public void forBlockItem(RegistryObject<? extends BlockItem> item, ResourceLocation modelLocation) {
	getBuilder(item.getId().getPath()).parent(new ModelFile.UncheckedModelFile(modelLocation));
    }

    public void forBlockItem(RegistryObject<? extends BlockItem> item, ResourceLocation modelLocation, String key,
	    ResourceLocation texture) {
	getBuilder(item.getId().getPath()).parent(new ModelFile.UncheckedModelFile(modelLocation)).texture(key,
		texture);
    }

}
