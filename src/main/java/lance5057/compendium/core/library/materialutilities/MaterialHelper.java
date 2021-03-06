//package lance5057.compendium.core.library.materialutilities;
//
//import lance5057.compendium.Reference;
//import lance5057.compendium.appendixes.gemology.materialhelper.addons.BasicGemMaterial;
//import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.BasicMetalMaterial;
//import lance5057.compendium.appendixes.oredressing.materialhelper.addons.MaterialOre;
//import lance5057.compendium.core.library.materialutilities.addons.MaterialAdvancedExtraComponents;
//import lance5057.compendium.core.library.materialutilities.addons.MaterialExtraComponents;
//import lance5057.compendium.core.library.materialutilities.addons.MaterialExtraTools;
//import lance5057.compendium.core.library.materialutilities.addons.MaterialVanillaComponents;
//import lance5057.compendium.core.library.materialutilities.addons.MaterialVanillaTools;
//import lance5057.compendium.core.library.materialutilities.addons.PremadeMaterial;
//import net.minecraft.block.Block;
//import net.minecraft.item.IItemTier;
//import net.minecraft.item.Item;
//import net.minecraft.item.crafting.Ingredient;
//import net.minecraft.world.biome.Biome.Category;
//import net.minecraftforge.common.ToolType;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//
//public class MaterialHelper extends MaterialHelperBase {
//    private PremadeMaterial premade;
//    private BasicGemMaterial craftable;
//    private BasicMetalMaterial meltable;
//    private MaterialVanillaComponents vcomponents;
//    private MaterialExtraComponents ecomponents;
//    private MaterialAdvancedExtraComponents acomponents;
//    private MaterialVanillaTools vtools;
//    private MaterialExtraTools etools;
//
//    // TODO add dense and sparse
//    private MaterialOre ore;
//
//    boolean preset = false;
//
//    public MaterialHelper(String name) {
//	this(name, Reference.MOD_ID, null);
//    }
//
//    public MaterialHelper(String name, IItemTier tier) {
//	this(name, Reference.MOD_ID, tier);
//    }
//
//    public MaterialHelper(String name, String parentMod, IItemTier tier) {
//	this.name = name;
//	this.parentMod = parentMod;
//	this.tier = tier;
//
//	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
//	ITEMS.register(modEventBus);
//	BLOCKS.register(modEventBus);
//    }
//
//    // Premade
//    public MaterialHelper withPremade(Ingredient ingot, Ingredient nugget, Ingredient storageblock) {
//	premade = new PremadeMaterial(this, ingot, nugget, storageblock);
//	return this;
//    }
//
//    public PremadeMaterial getPremade() {
//	return premade;
//    }
//
//    // Meltable
//    public MaterialHelper withIngot() {
//	//meltable = new BasicMetalMaterial(this);
//	return this;
//    }
//
//    public BasicMetalMaterial getIngot() {
//	return meltable;
//    }
//
//    // Craftable
//    public MaterialHelper withGem() {
//	craftable = new BasicGemMaterial(this);
//	return this;
//    }
//
//    public BasicGemMaterial getGem() {
//	return craftable;
//    }
//
//    // Vanilla Components
//    public MaterialHelper withVanillaComponents() {
//	vcomponents = new MaterialVanillaComponents(this);
//	return this;
//    }
//
//    public MaterialVanillaComponents getVanillaComponents() {
//	return vcomponents;
//    }
//
//    // Extra Components
//    public MaterialHelper withExtraComponents() {
//	ecomponents = new MaterialExtraComponents(this);
//	return this;
//    }
//
//    public MaterialExtraComponents getExtraComponents() {
//	return ecomponents;
//    }
//
//    // Vanilla Tools
//    public MaterialHelper withVanillaTools() {
//	this.vtools = new MaterialVanillaTools(this);
//	return this;
//    }
//
//    public MaterialVanillaTools getVanillaTools() {
//	return vtools;
//    }
//
//    // Extra Tools
//    public MaterialHelper withExtraTools() {
//	this.etools = new MaterialExtraTools(this);
//	return this;
//    }
//
//    public MaterialExtraTools getExtraTools() {
//	return etools;
//    }
//
//    public MaterialHelper withOre(float hardness, int level, ToolType tool, float resistance, int ymax, int ymin,
//	    int veinSize, int veinChance, Category biomeCategory) {
//	this.ore = new MaterialOre(this, hardness, level, tool, resistance, ymax, ymin, veinSize, veinChance,
//		biomeCategory);
//	return this;
//    }
//
//    public MaterialOre getOre() {
//	return this.ore;
//    }
//    
//    public MaterialHelper withAdvancedComponents()
//    {
//	this.acomponents = new MaterialAdvancedExtraComponents(this);
//	return this;
//    }
//    
//    public MaterialAdvancedExtraComponents getAdvancedComponents()
//    {
//	return this.acomponents;
//    }
//
//    public void client() {
//	if (this.getVanillaComponents() != null) {
//	    this.getVanillaComponents().setupClient(this);
//	}
//	if (this.getExtraComponents() != null) {
//	    this.getExtraComponents().setupClient(this);
//	}
//    }
//}
