package lance5057.compendium.appendixes.construction.materialhelper;

import lance5057.compendium.Reference;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionBarsAndChains;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionDecorations;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionDoorsAndGates;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionLighting;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionMaterialBasic;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionMaterialDungeon;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionMaterialShingles;
import lance5057.compendium.appendixes.construction.materialhelper.addons.ConstructionWindows;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ConstructionMaterialHelper extends MaterialHelperBase {
    public static final ItemGroup GROUP_METAL = new ItemGroup("compendium.materials.wood") {
	@Override
	public ItemStack createIcon() {
	    return new ItemStack(Items.BRICK);
	}
    };

    private ConstructionMaterialBasic basic;
    private ConstructionMaterialDungeon dungeon;
    private ConstructionMaterialShingles shingles;
    private ConstructionLighting lighting;
    private ConstructionBarsAndChains barsandchains;
    private ConstructionDoorsAndGates doorsandgates;
    private ConstructionDecorations decorations;
    private ConstructionWindows windows;

    public ConstructionMaterialHelper(String name) {
	this(name, Reference.MOD_ID);
    }

    public ConstructionMaterialHelper(String name, String parentMod) {
	this.name = name;
	this.parentMod = parentMod;

	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
	ITEMS.register(modEventBus);
	BLOCKS.register(modEventBus);
    }

    @Override
    public void setup() {
	if (this.hasBase())
	    basic.setup(this);
	if (this.hasShingles())
	    shingles.setup(this);
	if (this.hasDungeon())
	    dungeon.setup(this);
	if (this.hasLighting())
	    lighting.setup(this);
	if (this.hasBarsAndChains())
	    barsandchains.setup(this);
	if (this.hasDoorsAndGates())
	    doorsandgates.setup(this);
	if (this.hasDecorations())
	    decorations.setup(this);
	if (this.hasWindows())
	    windows.setup(this);
    }

    @OnlyIn(Dist.CLIENT)
    public void client(FMLClientSetupEvent event) {
	if (this.hasBase())
	    basic.setupClient(this);
	if (this.hasShingles())
	    shingles.setupClient(this);
	if (this.hasDungeon())
	    dungeon.setupClient(this);
	if (this.hasLighting())
	    lighting.setupClient(this);
	if (this.hasBarsAndChains())
	    barsandchains.setupClient(this);
	if (this.hasDoorsAndGates())
	    doorsandgates.setupClient(this);
	if (this.hasDecorations())
	    decorations.setupClient(this);
	if (this.hasWindows())
	    windows.setupClient(this);
    }

    // Base
    public ConstructionMaterialHelper withBase() {
	basic = new ConstructionMaterialBasic(this);
	return this;
    }

    public boolean hasBase() {
	return basic != null;
    }

    public ConstructionMaterialBasic getBase() {
	return basic;
    }

    // Dungeon
    public ConstructionMaterialHelper withDungeon() {
	dungeon = new ConstructionMaterialDungeon(this);
	return this;
    }

    public boolean hasDungeon() {
	return dungeon != null;
    }

    public ConstructionMaterialDungeon getDungeon() {
	return dungeon;
    }

    // Shingles
    public ConstructionMaterialHelper withShingles() {
	shingles = new ConstructionMaterialShingles(this);
	return this;
    }

    public boolean hasShingles() {
	return shingles != null;
    }

    public ConstructionMaterialShingles getShingles() {
	return shingles;
    }

    // Lighting
    public ConstructionMaterialHelper withLighting() {
	lighting = new ConstructionLighting(this);
	return this;
    }

    public boolean hasLighting() {
	return lighting != null;
    }

    public ConstructionLighting getLighting() {
	return lighting;
    }

    // BarsAndChains
    public ConstructionMaterialHelper withBarsAndChains() {
	barsandchains = new ConstructionBarsAndChains(this);
	return this;
    }

    public boolean hasBarsAndChains() {
	return barsandchains != null;
    }

    public ConstructionBarsAndChains getBarsAndChains() {
	return barsandchains;
    }

    // DoorsAndGates
    public ConstructionMaterialHelper withDoorsAndGates() {
	doorsandgates = new ConstructionDoorsAndGates(this);
	return this;
    }

    public boolean hasDoorsAndGates() {
	return doorsandgates != null;
    }

    public ConstructionDoorsAndGates getDoorsAndGates() {
	return doorsandgates;
    }

    // Decorations
    public ConstructionMaterialHelper withDecorations() {
	decorations = new ConstructionDecorations(this);
	return this;
    }

    public boolean hasDecorations() {
	return decorations != null;
    }

    public ConstructionDecorations getDecorations() {
	return decorations;
    }

    // Windows
    public ConstructionMaterialHelper withWindows() {
	windows = new ConstructionWindows(this);
	return this;
    }

    public boolean hasWindows() {
	return windows != null;
    }

    public ConstructionWindows getWindows() {
	return windows;
    }
}
