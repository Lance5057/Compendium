package lance5057.compendium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lance5057.compendium.core.materials.CompendiumMaterials;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Reference.MOD_ID)
public class TinkersCompendium {

	// public static int modGuiIndex = 0;
	// public static final int GUI_CREST_INV = modGuiIndex++;
	// public static final int GUI_ANVIL_INV = modGuiIndex++;
	// public static final int GUI_GUIDEBOOK = modGuiIndex++;
	// public static final int GUI_STRAPS_INV = modGuiIndex++;

	//@Mod.Instance(Reference.MOD_ID)
	//public static TinkersCompendium instance = new TinkersCompendium();

	public static Logger logger = LogManager.getLogger();

	//PacketHandler phandler = new PacketHandler();

	//public static CreativeTab tab = new CreativeTab("tinkerscompendium", new ItemStack(Items.SHIELD));
	//public static TCConfig config;

//	public static final SimpleNetworkWrapper networkInstance = NetworkRegistry.INSTANCE
//			.newSimpleChannel(Reference.MOD_ID);

	//public static Modifiers mods;

	public static TCItems items;
	public static TCBlocks blocks;
//	public static TDParts parts;
//	public static TDTools tools;
	public static CompendiumMaterials mats;
//	public static CompendiumTraits traits;
//	public static CompendiumWorkstations workstations;
//	public static CompendiumModifiers modifiers;
	//public static TCEvents events;
//	public static CompendiumEntities entities;
//
//	SpawnArmorOnMobs mobs = new SpawnArmorOnMobs();
//
//	public static CompendiumTextiles textiles;
//
//	public static List<ModuleBase> addons = new ArrayList<ModuleBase>();
//
//	public static AddonBloodMagic bloodmagic;
//	public static AddonBotania botania;
//	public static AddonToolLeveling leveling;
//	public static AddonEBWizardry wizardry;
//	public static AddonActuallyAdditions actadd;

//	@SidedProxy(clientSide = "lance5057.tDefense.proxy.ClientProxy", serverSide = "lance5057.tDefense.proxy.CommonProxy")
//	public static CommonProxy proxy;

	//public static Item book;

	//@Mod.EventHandler
	public TinkersCompendium() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::modSetup);
		modEventBus.addListener(this::setRenderLayers);
		
//		TCBlocks.register(modEventBus);
//		TCItems.register(modEventBus);
		
		//events = new TCEvents();
		blocks = new TCBlocks();
		items = new TCItems();
//		parts = new TDParts();
		mats = new CompendiumMaterials();
//		tools = new TDTools();
		
//		traits = new CompendiumTraits();
//		modifiers = new CompendiumModifiers();
//		workstations = new CompendiumWorkstations();
//		entities = new CompendiumEntities();
//		textiles = new CompendiumTextiles();
//		config = new TCConfig();
//
//		MinecraftForge.EVENT_BUS.register(mobs);
		
		modEventBus.register(TCEvents.class);
//
//		if (Loader.isModLoaded("bloodmagic") && TCConfig.addons.BloodMagic)
//			addons.add(bloodmagic = new AddonBloodMagic());
//		if (Loader.isModLoaded("botania") && TCConfig.addons.Botania)
//			addons.add(botania = new AddonBotania());
//		if (Loader.isModLoaded("tinkertoolleveling") && TCConfig.addons.ToolLeveling)
//			addons.add(leveling = new AddonToolLeveling());
//		if (Loader.isModLoaded("ebwizardry") && TCConfig.addons.EBWizardry)
//			addons.add(wizardry = new AddonEBWizardry());
//		// if (TCConfig.addons.ActuallyAdditions)
//		addons.add(actadd = new AddonActuallyAdditions());
//
//		for (ModuleBase m : addons) {
//			m.preInit(e);
//		}
//
//		parts.preInit(e);
//		mats.preInit(e);
//		tools.preInit(e);
//		traits.preInit();
//		modifiers.preInit();
//		workstations.preInit(e);
//		textiles.preInit();
//		events.preInit();
//		entities.preInit(e);

//		if(bloodmagic != null)
//			bloodmagic.preInit(e);
//		if(botania != null)
//			botania.preInit(e);
//		if(wizardry != null)
//			wizardry.preInit(e);

//		items.preInit(e);
//		blocks.preInit(e);
//
//		proxy.preInit();
//
//		if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
//			CompendiumBook.init();
//		}
	}
	
	private void modSetup(final FMLCommonSetupEvent event)
	{
		mats.setup(event);
	}
	
	public void setRenderLayers(FMLClientSetupEvent event) {
		TCClient.setRenderLayers();
	}

//	private static Item registerItem(Register<Item> event, Item item, String string) {
//		item.setUnlocalizedName(string).setRegistryName(Reference.MOD_ID, string);
//		event.getRegistry().register(item);
//		return item;
//	}

	
//
//	@Mod.EventHandler
//	public void init(FMLInitializationEvent e) {
//		NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
//
//		parts.init(e);
//		mats.init(e);
//		tools.init(e);
//		traits.init();
//		modifiers.init();
//		workstations.init(e);
//		textiles.init();
//		events.init();
//
//		entities.init(e);
//
////		if(bloodmagic != null)
////			bloodmagic.init(e);
////		if(botania != null)
////			botania.init(e);
////		if(leveling != null)
////			leveling.init(e);
////		if(wizardry != null)
////			wizardry.init(e);
//
//		for (ModuleBase m : addons) {
//			m.init(e);
//		}
//
//		items.init(e);
//		blocks.init(e);
//
//		proxy.init();
//
//		phandler.init();
//
//	}
//
//	@Mod.EventHandler
//	public void postInit(FMLPostInitializationEvent e) {
//		parts.postInit(e);
//		mats.postInit(e);
//		tools.postInit(e);
//		traits.postInit();
//		modifiers.postInit();
//		workstations.postInit(e);
//		textiles.postInit();
//		events.postInit();
//
//		entities.postInit(e);
//
////		if(bloodmagic != null)
////			bloodmagic.postInit(e);		
////		if(botania != null)
////			botania.postInit(e);	
////		if(leveling != null)
////			leveling.postInit(e);
////		if(wizardry != null)
////			wizardry.postInit(e);
//
//		for (ModuleBase m : addons) {
//			m.postInit(e);
//		}
//
//		items.postInit(e);
//		blocks.postInit(e);
//
//		proxy.postInit();
//
//		if (TCConfig.debug) {
//			dumpBiomeInfo();
//		}
//
//		if (TinkersCompendium.config.developerFeatures) {
//			OutputWikiPages.outputWikiSidebar(mats.materials);
//		}
//	}
//
//	// public static List<MaterialHelper.oreGen> biomeCheck = new
//	// ArrayList<MaterialHelper.oreGen>();
//
//	void dumpBiomeInfo() {
//		File f = new File(Loader.instance().getConfigDir(), "BiomeDump.txt");
//		try {
//			BufferedWriter output = new BufferedWriter(new FileWriter(f));
//
//			for (Biome b : ForgeRegistries.BIOMES) {
//				output.write(b.getBiomeName());
//				output.newLine();
//
//				output.write("Elevation:" + Float.toString(b.getBaseHeight()));
//				output.newLine();
//
//				output.write("Temperature:" + Float.toString(b.getDefaultTemperature()));
//				output.newLine();
//
//				output.write("Humidity:" + Float.toString(b.getRainfall()));
//				output.newLine();
//				output.newLine();
//
////				for (MaterialHelper.oreGen ore : biomeCheck) {
////					if ((ore.oreBiomeWhite == null || checkBiome(b, ore.oreBiomeWhite))
////							&& (ore.oreBiomeBlack == null || !checkBiome(b, ore.oreBiomeBlack))) {
////						float temp = b.getDefaultTemperature();
////						float elevation = b.getBaseHeight();
////						float humidity = b.getRainfall();
////
////						// -2 = null
////						if (ore.biomeTempMax == -2 || ore.biomeTempMin == -2
////								|| (temp >= ore.biomeTempMin && temp <= ore.biomeTempMax))
////							if (ore.biomeElevationMax == -2 || ore.biomeElevationMin == -2
////									|| (elevation >= ore.biomeElevationMin && elevation <= ore.biomeElevationMax))
////								if (ore.biomeHumidityMax == -2 || ore.biomeHumidityMin == -2
////										|| (humidity >= ore.biomeHumidityMin && humidity <= ore.biomeHumidityMax)) {
////									output.write(ore.getName());
////									output.newLine();
////								}
////					}
////				}
//
//				output.write("------------------");
//				output.newLine();
//			}
//
//			output.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//	private boolean checkBiome(Biome current, Biome[] biomes) {
//		if (biomes != null) {
//			for (Biome b : biomes) {
//				if (current == b)
//					return true;
//			}
//		} else
//			return true;
//		return false;
//	}

}
