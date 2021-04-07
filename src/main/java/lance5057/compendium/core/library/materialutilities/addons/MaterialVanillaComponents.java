//package lance5057.compendium.core.library.materialutilities.addons;
//
//import lance5057.compendium.CompendiumItems;
//import lance5057.compendium.core.blocks.ComponentDoor;
//import lance5057.compendium.core.blocks.ComponentPane;
//import lance5057.compendium.core.blocks.ComponentTrapDoor;
//import lance5057.compendium.core.library.materialutilities.MaterialHelper;
//import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
//import net.minecraft.block.Block;
//import net.minecraft.block.ChainBlock;
//import net.minecraft.block.LanternBlock;
//import net.minecraft.block.SoundType;
//import net.minecraft.block.material.Material;
//import net.minecraft.block.material.MaterialColor;
//import net.minecraft.client.renderer.RenderType;
//import net.minecraft.client.renderer.RenderTypeLookup;
//import net.minecraft.item.BlockItem;
//import net.minecraft.item.Item;
//import net.minecraftforge.fml.RegistryObject;
//import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
//
//public class MaterialVanillaComponents implements MaterialBase {
//    public RegistryObject<ComponentDoor> DOOR;
//    public RegistryObject<ComponentTrapDoor> TRAPDOOR;
//    public RegistryObject<ComponentPane> BARS;
//    public RegistryObject<LanternBlock> LANTERN;
//    public RegistryObject<ChainBlock> CHAIN;
//
//    public RegistryObject<BlockItem> ITEM_DOOR;
//    public RegistryObject<BlockItem> ITEM_TRAPDOOR;
//    public RegistryObject<BlockItem> ITEM_BARS;
//    public RegistryObject<BlockItem> ITEM_LANTERN;
//    public RegistryObject<BlockItem> ITEM_CHAIN;
//
//    public MaterialVanillaComponents(MaterialHelper mh) {
//
//	DOOR = mh.BLOCKS.register(mh.name + "door",
//		() -> new ComponentDoor(Block.Properties.create(Material.IRON, MaterialColor.IRON)
//			.hardnessAndResistance(5.0F).sound(SoundType.METAL).notSolid()));
//	TRAPDOOR = mh.BLOCKS.register(mh.name + "trapdoor", () -> new ComponentTrapDoor(
//		Block.Properties.create(Material.IRON).hardnessAndResistance(5.0F).sound(SoundType.METAL).notSolid()));
//	BARS = mh.BLOCKS.register(mh.name + "bars",
//		() -> new ComponentPane(Block.Properties.create(Material.IRON, MaterialColor.AIR)
//			.hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL).notSolid()));
//	LANTERN = mh.BLOCKS.register(mh.name + "lantern",
//		() -> new LanternBlock(Block.Properties.create(Material.IRON, MaterialColor.AIR)
//			.hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL).notSolid().setLightLevel((state) -> {
//			    return 15;
//			})));
//	CHAIN = mh.BLOCKS.register(mh.name + "chain",
//		() -> new ChainBlock(Block.Properties.create(Material.IRON, MaterialColor.IRON)
//			.hardnessAndResistance(5.0F).sound(SoundType.METAL).notSolid()));
//
//	ITEM_DOOR = mh.ITEMS.register(mh.name + "itemdoor",
//		() -> new BlockItem(DOOR.get(), new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
//	ITEM_TRAPDOOR = mh.ITEMS.register(mh.name + "itemtrapdoor",
//		() -> new BlockItem(TRAPDOOR.get(), new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
//	ITEM_BARS = mh.ITEMS.register(mh.name + "itembars",
//		() -> new BlockItem(BARS.get(), new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
//	ITEM_LANTERN = mh.ITEMS.register(mh.name + "itemlantern",
//		() -> new BlockItem(LANTERN.get(), new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
//	ITEM_CHAIN = mh.ITEMS.register(mh.name + "itemchain",
//		() -> new BlockItem(CHAIN.get(), new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
//
//    }
//
//    @Override
//    public void setupClient(MaterialHelper mat) {
//	RenderType cutout = RenderType.getCutout();
//	RenderTypeLookup.setRenderLayer(this.BARS.get(), cutout);
//	RenderTypeLookup.setRenderLayer(this.DOOR.get(), cutout);
//	RenderTypeLookup.setRenderLayer(this.TRAPDOOR.get(), cutout);
//	RenderTypeLookup.setRenderLayer(this.LANTERN.get(), cutout);
//	RenderTypeLookup.setRenderLayer(this.CHAIN.get(), cutout);
//    }
//
//    @Override
//    public void setup(FMLCommonSetupEvent event) {
//	// TODO Auto-generated method stub
//
//    }
//}
