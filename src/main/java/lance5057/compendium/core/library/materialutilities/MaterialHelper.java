package lance5057.compendium.core.library.materialutilities;

import lance5057.compendium.Reference;
import lance5057.compendium.core.library.materialutilities.addons.CraftableMaterial;
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

	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Reference.MOD_ID);
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS,
			Reference.MOD_ID);

	private CraftableMaterial craftable;
	private MeltableMaterial meltable;
	// private

	// public List<MaterialBase> addons;

	boolean preset = false;

	public MaterialHelper(String name) {
		this(name, Reference.MOD_ID);
	}

	public MaterialHelper(String name, String parentMod) {
		this.name = name;
		this.parentMod = parentMod;
		
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.register(ITEMS);
		modEventBus.register(BLOCKS);
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
	public MaterialHelper witGem() {
		craftable = new CraftableMaterial(this);
		return this;
	}

	public CraftableMaterial getGem() {
		return craftable;
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
