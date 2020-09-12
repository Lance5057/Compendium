package lance5057.compendium.core.library.materialutilities;

import lance5057.compendium.Reference;
import lance5057.compendium.core.library.TCItemTier;
import lance5057.compendium.core.library.materialutilities.addons.CraftableMaterial;
import lance5057.compendium.core.library.materialutilities.addons.MaterialExtraComponents;
import lance5057.compendium.core.library.materialutilities.addons.MaterialExtraTools;
import lance5057.compendium.core.library.materialutilities.addons.MaterialGemOre;
import lance5057.compendium.core.library.materialutilities.addons.MaterialOre;
import lance5057.compendium.core.library.materialutilities.addons.MaterialVanillaComponents;
import lance5057.compendium.core.library.materialutilities.addons.MaterialVanillaTools;
import lance5057.compendium.core.library.materialutilities.addons.MeltableMaterial;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MaterialHelper {
	public String name;
	public String parentMod;
	public TCItemTier tier;

	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Reference.MOD_ID);
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS,
			Reference.MOD_ID);

	private CraftableMaterial craftable;
	private MeltableMaterial meltable;
	private MaterialVanillaComponents vcomponents;
	private MaterialExtraComponents ecomponents;
	private MaterialVanillaTools vtools;
	private MaterialExtraTools etools;

	// TODO add dense and sparse
	private MaterialOre ore;
	private MaterialGemOre gemore;

	boolean preset = false;

	public MaterialHelper(String name,TCItemTier tier) {
		this(name, Reference.MOD_ID, tier);
	}

	public MaterialHelper(String name, String parentMod,TCItemTier tier) {
		this.name = name;
		this.parentMod = parentMod;

		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ITEMS.register(modEventBus);
		BLOCKS.register(modEventBus);
	}

	// Meltable
	public MaterialHelper withIngot() {
		meltable = new MeltableMaterial(this);
		return this;
	}

	public MeltableMaterial getIngot() {
		return meltable;
	}

	// Craftable
	public MaterialHelper withGem() {
		craftable = new CraftableMaterial(this);
		return this;
	}

	public CraftableMaterial getGem() {
		return craftable;
	}

	// Vanilla Components
	public MaterialHelper withVanillaComponents() {
		vcomponents = new MaterialVanillaComponents(this);
		return this;
	}

	public MaterialVanillaComponents getVanillaComponents() {
		return vcomponents;
	}

	// Extra Components
	public MaterialHelper withExtraComponents() {
		ecomponents = new MaterialExtraComponents(this);
		return this;
	}

	public MaterialExtraComponents getExtraComponents() {
		return ecomponents;
	}

//	public MaterialHelper components() {
//		addons.add(new MaterialVanillaComponents(name, parentMod));
//		addons.add(new MaterialExtraComponents(name, parentMod));
//		return this;
//	}
//
//	public MaterialHelper extracomponents() {
//		addons.add(new MaterialExtraComponents(name, parentMod));
//		return this;
//	}
//
//	public MaterialHelper tool(TCItemTier tier) {
//		addons.add(new MaterialTools(name, tier));
//		return this;
//	}
//
//	public MaterialHelper ore(float hardness, int level, String tool, float resistance, int ymax, int ymin,
//			int veinSize, int veinChance, Category biomeCategory) {
//		addons.add(new MaterialOre(name, hardness, level, tool, resistance, ymax, ymin, veinSize, veinChance,
//				biomeCategory));
//		return this;
//	}

//	public MaterialHelper finish() {
//		tags();
//		recipes();
//		loot();
//		return this;
//	}
//
//	public void client() {
//		for (MaterialBase mb : addons) {
//			mb.setupClient(this);
//		}
//	}
//
//	public void models() {
//		for (MaterialBase mb : addons) {
//			mb.setupModels(this);
//		}
//	}
//
//	public void tags() {
//		for (MaterialBase mb : addons) {
//			mb.setupBlockTags();
//			mb.setupItemTags();
//		}
//	}
//
//	public void recipes() {
//		for (MaterialBase mb : addons) {
//			mb.setupRecipes();
//		}
//	}
//
//	public void loot() {
//		for (MaterialBase mb : addons) {
//			mb.setupLoot();
//		}
//	}
//
//	public void setup(final FMLCommonSetupEvent event) {
//		for (MaterialBase mb : addons) {
//			mb.setup(event);
//		}
//	}
}
