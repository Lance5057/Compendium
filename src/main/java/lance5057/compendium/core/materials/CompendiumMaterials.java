package lance5057.compendium.core.materials;

import java.util.ArrayList;
import java.util.List;

import lance5057.compendium.Reference;
import lance5057.compendium.core.library.TCItemTier;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome.Category;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class CompendiumMaterials {
	// ArmorMaterialStats ams = new ArmorMaterialStats(0, 0, 0, 0, "");

//	public static BowMaterialStats whyWouldYouMakeABowOutOfThis = new BowMaterialStats(0.2f, 0.4f, -1f);
//
//	String SHIELD = ShieldMaterialStats.TYPE;
//	String HELM = HelmMaterialStats.TYPE;
//	String CHEST = ChestMaterialStats.TYPE;
//	String LEGS = LegsMaterialStats.TYPE;
//	String BOOTS = FeetMaterialStats.TYPE;
//	String BAUBLE = BaubleMaterialStats.TYPE;
//
//	public static final Map<String, Integer> colors = new THashMap();

//	public static FluidColored fluidVile;
//	public static FluidColored fluidVibrant;
//	public static FluidColored fluidSlush;
//	public static FluidMolten fluidQuartz;
//	public static FluidColored fluidChorusJuice;
//	public static FluidMolten fluidDragonsBreath;
//
	public static List<MaterialHelper> materials = new ArrayList<>();

	// Base
	public static MaterialHelper AEONSTEEL;
	public static MaterialHelper queensgold;
	public static MaterialHelper dogbearium;
	public static MaterialHelper sinisterium;
	public static MaterialHelper nihilite;
	public static MaterialHelper orichalcum;
	public static MaterialHelper pandorium;
	public static MaterialHelper rosegold;
	public static MaterialHelper platinum;
	public static MaterialHelper silver;

	public static MaterialHelper valyriansteel;
	public static MaterialHelper ice;
	public static MaterialHelper froststeel;

	// Advanced Materials
	public static MaterialHelper radiantGold;
	public static MaterialHelper hallowedSilver;
	public static MaterialHelper energeticElectrum;
//	public static MaterialHelper purifiedCobalt;
//	public static MaterialHelper purifiedArdite;
//	public static MaterialHelper purifiedManyullyn;

	// Wanderlust Materials
	public static MaterialHelper mithril;
	public static MaterialHelper werewolf;

	// Cornucopia Materials
	public static MaterialHelper gallite;
	public static MaterialHelper sundrop;
	public static MaterialHelper voidite;
	public static MaterialHelper solarium;
	public static MaterialHelper dragonsteel;
	public static MaterialHelper blacksteel;
	public static MaterialHelper abyssalium;
	public static MaterialHelper depthsilver;
	public static MaterialHelper moonsilver;
	public static MaterialHelper novagold;

	// Wool Colors

//	public static MaterialHelper black;
//	public static MaterialHelper red;
//	public static MaterialHelper green;
//	public static MaterialHelper brown;
//	public static MaterialHelper blue;
//	public static MaterialHelper purple;
//	public static MaterialHelper cyan;
//	public static MaterialHelper lightgray;
//	public static MaterialHelper gray;
//	public static MaterialHelper pink;
//	public static MaterialHelper lime;
//	public static MaterialHelper yellow;
//	public static MaterialHelper lightblue;
//	public static MaterialHelper magenta;
//	public static MaterialHelper orange;
//	public static MaterialHelper white;
//
//	public static MaterialHelper silky;
//	public static MaterialHelper roughspun;

	// Vanilla Mats
	public static MaterialHelper iron;
	public static MaterialHelper gold;
	public static MaterialHelper emerald;
	public static MaterialHelper diamond;

	// Jokes
//	 List<TDMatHelper> materials_joke = new ArrayList<>(Arrays.asList(
//	 new TDMatHelper("cheese", 0xffe900, fluids.put("cheese", new
//	 FluidMolten("cheese", 0xffe900)), true),
//	 new TDMatHelper("bread", 0x89732a), new TDMatHelper("melon", 0xff77a4)));

	// Holiday
//	redcandy = new MaterialHelper("redcandy", 0xff0000, 100,
//			new HeadMaterialStats(25, 2f, 2f, HarvestLevels.STONE), new HandleMaterialStats(1.0f, 12),
//			new ExtraMaterialStats(12), new ShieldMaterialStats(25, 12), whyWouldYouMakeABowOutOfThis,
//			new HelmMaterialStats(25, 1, 1, 0), new ChestMaterialStats(25, 2, 1, 0),
//			new LegsMaterialStats(25, 2, 1, 0), new FeetMaterialStats(25, 1, 1, 0)).setGenBlock(true).addPremadeItems(HolidayBase.item_redmintcane, null, null, null, null, null, null, null);
//	
//	greencandy = new MaterialHelper("greencandy", 0x00ff00, 100,
//			new HeadMaterialStats(25, 2f, 2f, HarvestLevels.STONE), new HandleMaterialStats(1.0f, 12),
//			new ExtraMaterialStats(12), new ShieldMaterialStats(25, 12), whyWouldYouMakeABowOutOfThis,
//			new HelmMaterialStats(25, 1, 1, 0), new ChestMaterialStats(25, 2, 1, 0),
//			new LegsMaterialStats(25, 2, 1, 0), new FeetMaterialStats(25, 1, 1, 0)).setGenBlock(true).addPremadeItems(HolidayBase.item_greenmintcane, null, null, null, null, null, null, null);
//	
	// Gems
	public static MaterialHelper sapphire;
	public static MaterialHelper ruby;
	public static MaterialHelper starsapphire;
	public static MaterialHelper starruby;
	public static MaterialHelper citrine;
	public static MaterialHelper quartz;
	public static MaterialHelper amethyst;
	public static MaterialHelper lapis;
	public static MaterialHelper topaz;
	public static MaterialHelper garnet;
	public static MaterialHelper opal;
	public static MaterialHelper tanzinite;
	public static MaterialHelper amber;
	//
	// // Cornucopia
	// List<TDMatHelper> materials_cornucopia = new ArrayList<>(Arrays.asList(
	// new TDMatHelper("gallite", 0x198c09, fluids.put("gallite", new
	// FluidMolten("gallite", 0x198c09)), true),
	// new TDMatHelper("sundrop", 0xfff987, fluids.put("sundrop", new
	// FluidMolten("sundrop", 0xfff987)), true),
	// new TDMatHelper("voidite", 0x450059, fluids.put("voidite", new
	// FluidMolten("voidite", 0x450059)), true),
	// new TDMatHelper("solarium", 0xffff31, fluids.put("solarium", new
	// FluidMolten("solarium", 0xffff31)), true),
	// new TDMatHelper("dragonsteel", 0x55914d,
	// fluids.put("dragonsteel", new FluidMolten("dragonsteel", 0x55914d)), true),
	// new TDMatHelper("blacksteel", 0x383838, fluids.put("blacksteel", new
	// FluidMolten("blacksteel", 0x383838)),
	// true),
	// new TDMatHelper("abyssalium", 0x000633, fluids.put("abyssalium", new
	// FluidMolten("abyssalium", 0x000633)),
	// true),
	// new TDMatHelper("depthsilver", 0x646782,
	// fluids.put("depthsilver", new FluidMolten("depthsilver", 0x646782)), true),
	// new TDMatHelper("moonsilver", 0x777777, fluids.put("moonsilver", new
	// FluidMolten("moonsilver", 0x777777)),
	// true),
	// new TDMatHelper("novagold", 0xffc300, fluids.put("novagold", new
	// FluidMolten("novagold", 0xffc300)),
	// true)));
	// // Blood Magic
	// List<String> materials_bm = new ArrayList<>(
	// Arrays.asList("blankslate", "reinforcedslate", "imbued", "demonic",
	// "ethereal"));
	//
	// // Twilight Forest
	// List<String> materials_tf = new ArrayList<>(Arrays.asList("rainbowoak",
	// "ravenfeather", "horn", "ironwood",
	// "steelleaf", "nagascale", "fiery", "caminite", "knightmetal"));
	//
	// // Pam's Harvestcraft
	// List<String> materials_pam = new
	// ArrayList<>(Arrays.asList("hardenedleather"));

	// Vanilla TICO
//	wood = new MaterialHelper("wood", 0x8e661b).setOreIngot("plankWood")
//			.setOreNugget("stickWood");
//	stone = new MaterialHelper("stone", 0x999999).setOreIngot("cobblestone");
//	flint = new MaterialHelper("flint", 0x696969).setGenIngot(false).setGenNugget(false);
//	cactus = new MaterialHelper("cactus", 0x00a10f).setGenIngot(false).setGenNugget(false);
//	bone = new MaterialHelper("bone", 0xede6bf).setGenIngot(false).setGenNugget(false);
//	obsidian = new MaterialHelper("obsidian", 0x601cc4).setGenIngot(false)
//			.setGenNugget(false);;
//	prismarine = new MaterialHelper("prismarine", 0x7edebc).setGenIngot(false)
//			.setGenNugget(false);
//	endstone = new MaterialHelper("endstone", 0xe0d890).setGenIngot(false)
//			.setGenNugget(false);;
//	paper = new MaterialHelper("paper", 0xffffff).setGenIngot(false).setGenNugget(false);
//	sponge = new MaterialHelper("sponge", 0xcacc4e).setGenIngot(false).setGenNugget(false);
//	firewood = new MaterialHelper("firewood", 0xcc5300).setGenIngot(false)
//			.setGenNugget(false);
//
//	// Slime
//	knightslime = new MaterialHelper("knightslime", 0xf18ff0).setGenIngot(false)
//			.setGenNugget(false);
//	slime = new MaterialHelper("slime", 0x82c873).setGenIngot(false).setGenNugget(false);
//	blueslime = new MaterialHelper("blueslime", 0x74c8c7).setGenIngot(false)
//			.setGenNugget(false);
//	magmaslime = new MaterialHelper("magmaslime", 0xff960d).setGenIngot(false)
//			.setGenNugget(false);
//
//	// Metals
//	iron = new MaterialHelper("iron", 0xcacaca).setGenIngot(false).setGenNugget(false);
//	pigiron = new MaterialHelper("pigiron", 0xef9e9b).setGenIngot(false)
//			.setGenNugget(false);
//
//	// Nether new MaterialHelpers
//	netherrack = new MaterialHelper("netherrack", 0xb84f4f).setGenIngot(false)
//			.setGenNugget(false);
//	ardite = new MaterialHelper("ardite", 0xd14210).setGenIngot(false).setGenNugget(false);
//	cobalt = new MaterialHelper("cobalt", 0x2882d4).setGenIngot(false).setGenNugget(false);
//	manyullyn = new MaterialHelper("manyullyn", 0xa15cf8).setGenIngot(false)
//			.setGenNugget(false);
//
//	// mod integration
//	copper = new MaterialHelper("copper", 0xed9f07);
//	bronze = new MaterialHelper("bronze", 0xe3bd68);
//	lead = new MaterialHelper("lead", 0x4d4968);
//	// silver = new MaterialHelper("silver", 0xd1ecf6);
//	electrum = new MaterialHelper("electrum", 0xe8db49);
//	steel = new MaterialHelper("steel", 0xa7a7a7);

//	public static ArrayList<Item> itemList = new ArrayList<Item>();
//	public static ArrayList<Block> blockList = new ArrayList<Block>();

//	@Subscribe
//	public void preInit(FMLPreInitializationEvent event) {
	public CompendiumMaterials() {

		materials.add(AEONSTEEL = new MaterialHelper("aeonsteel", new TCItemTier(4, 500, 15f, 4f, 22, () -> {
			return Ingredient.fromTag(new ItemTags.Wrapper(new ResourceLocation("forge", "ingots/aeonsteel")));
		})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools());

		materials.add(queensgold = new MaterialHelper("queensgold", new TCItemTier(1, 70, 3.00f, 3.00f, 22, () -> {
			return Ingredient.fromTag(new ItemTags.Wrapper(new ResourceLocation("forge", "ingots/queensgold")));
		})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools());

		materials.add(dogbearium = new MaterialHelper("dogbearium", new TCItemTier(2, 150, 5.00f, 9.00f, 22, () -> {
			return Ingredient.fromTag(new ItemTags.Wrapper(new ResourceLocation("forge", "ingots/dogbearium")));
		})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools());

		materials.add(sinisterium = new MaterialHelper("sinisterium", new TCItemTier(3, 224, 5.00f, 5.00f, 22, () -> {
			return Ingredient.fromTag(new ItemTags.Wrapper(new ResourceLocation("forge", "ingots/sinisterium")));
		})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools());

		materials.add(nihilite = new MaterialHelper("nihilite", new TCItemTier(4, 400, 9.7f, 7.1f, 22, () -> {
			return Ingredient.fromTag(new ItemTags.Wrapper(new ResourceLocation("forge", "ingots/nihilite")));
		})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools());

		materials.add(orichalcum = new MaterialHelper("orichalcum", new TCItemTier(3, 180, 5.3f, 6.23f, 22, () -> {
			return Ingredient.fromTag(new ItemTags.Wrapper(new ResourceLocation("forge", "ingots/orichalcum")));
		})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools());

		materials.add(pandorium = new MaterialHelper("pandorium", new TCItemTier(3, 999, 10.2f, 8.72f, 22, () -> {
			return Ingredient.fromTag(new ItemTags.Wrapper(new ResourceLocation("forge", "ingots/pandorium")));
		})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools());

		materials.add(rosegold = new MaterialHelper("rosegold", new TCItemTier(0, 99, 4f, 1f, 22, () -> {
			return Ingredient.fromTag(new ItemTags.Wrapper(new ResourceLocation("forge", "ingots/rosegold")));
		})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools());

		materials.add(platinum = new MaterialHelper("platinum", new TCItemTier(1, 99, 3.7f, 4f, 22, () -> {
			return Ingredient.fromTag(new ItemTags.Wrapper(new ResourceLocation("forge", "ingots/platinum")));
		})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools().withOre(5, 2, ToolType.PICKAXE,
				4, 20, 15, 3, 5, null));

		materials.add(silver = new MaterialHelper("silver", new TCItemTier(3, 150, 5.00f, 9.00f, 22, () -> {
			return Ingredient.fromTag(new ItemTags.Wrapper(new ResourceLocation("forge", "ingots/silver")));
		})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools()
				.withOre(5, 1, ToolType.PICKAXE, 4, 32, 5, 15, 6, null));

		materials.add(valyriansteel = new MaterialHelper("valyriansteel", new TCItemTier(3, 610, 7f, 7f, 22, () -> {
			return Ingredient.fromTag(new ItemTags.Wrapper(new ResourceLocation("forge", "ingots/valyriansteel")));
		})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools());

		materials.add(ice = new MaterialHelper("ice", new TCItemTier(1, 120, 4.00f, 3.00f, 22, () -> {
			return Ingredient.fromTag(new ItemTags.Wrapper(new ResourceLocation("forge", "ingots/ice")));
		})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools());

		materials.add(froststeel = new MaterialHelper("froststeel", new TCItemTier(3, 610, 7f, 7f, 22, () -> {
			return Ingredient.fromTag(new ItemTags.Wrapper(new ResourceLocation("forge", "ingots/froststeel")));
		})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools());

		materials.add(mithril = new MaterialHelper("mithril", new TCItemTier(3, 800, 8f, 6f, 22, () -> {
			return Ingredient.fromTag(new ItemTags.Wrapper(new ResourceLocation("forge", "ingots/mithril")));
		})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools());

		materials.add(iron = new MaterialHelper("iron").withExtraComponents());
		materials.add(gold = new MaterialHelper("gold").withVanillaComponents().withExtraComponents());
		materials.add(emerald = new MaterialHelper("emerald", new TCItemTier(3, 320, 10f, 4f, 22, () -> {
			return Ingredient.fromTag(new ItemTags.Wrapper(new ResourceLocation("forge", "gems/emerald")));
		})).withVanillaComponents().withExtraComponents().withVanillaTools());

		materials.add(diamond = new MaterialHelper("diamond").withVanillaComponents().withExtraComponents());
		materials.add(lapis = new MaterialHelper("lapis", new TCItemTier(0, 120, 2f, 2f, 22, () -> {
			return Ingredient.fromTag(new ItemTags.Wrapper(new ResourceLocation("forge", "gems/lapis")));
		})).withVanillaComponents().withExtraComponents().withVanillaTools());

		materials.add(quartz = new MaterialHelper("quartz", new TCItemTier(1, 430, 5.5f, 7f, 22, () -> {
			return Ingredient.fromTag(new ItemTags.Wrapper(new ResourceLocation("forge", "gems/quartz")));
		})).withVanillaComponents().withExtraComponents().withVanillaTools());

		materials.add(sapphire = new MaterialHelper("sapphire", new TCItemTier(3, 320, 4f, 4f, 22, () -> {
			return Ingredient.fromTag(new ItemTags.Wrapper(new ResourceLocation("forge", "gems/sapphire")));
		})).withGem().withVanillaComponents().withExtraComponents().withVanillaTools()
				.withOre(5, 2, ToolType.PICKAXE, 4, 16, 5, 4, 5, Category.OCEAN));

		materials.add(ruby = new MaterialHelper("ruby", new TCItemTier(3, 320, 4f, 10f, 22, () -> {
			return Ingredient.fromTag(new ItemTags.Wrapper(new ResourceLocation("forge", "gems/ruby")));
		})).withGem().withVanillaComponents().withExtraComponents().withVanillaTools()
				.withOre(5, 2, ToolType.PICKAXE, 4, 16, 5, 4, 5, Category.MESA));

//		materials.add(starsapphire = new MaterialHelper("starsapphire", 0x6e00ff).gem()
//				.components()
//				.tool(new TCItemTier(4, 115, 12f, 1f, 22, () -> {
//					return Ingredient.fromTag(ItemTags.getCollection()
//							.getOrCreate(new ResourceLocation(Reference.MOD_ID, "dogbeariumingot")));
//				}))
//				.finish());
//
//		materials.add(starruby = new MaterialHelper("starruby", 0xff0061).gem()
//				.components()
//				.tool(new TCItemTier(1, 115, 1f, 12f, 22, () -> {
//					return Ingredient.fromTag(ItemTags.getCollection()
//							.getOrCreate(new ResourceLocation(Reference.MOD_ID, "dogbeariumingot")));
//				}))
//				.finish());

		materials.add(citrine = new MaterialHelper("citrine", new TCItemTier(3, 430, 5.5f, 7f, 22, () -> {
			return Ingredient.fromTag(new ItemTags.Wrapper(new ResourceLocation("forge", "gems/citrine")));
		})).withGem().withVanillaComponents().withExtraComponents().withVanillaTools()
				.withOre(5, 2, ToolType.PICKAXE, 4, 16, 5, 4, 5, Category.DESERT));

		materials.add(amethyst = new MaterialHelper("amethyst", new TCItemTier(1, 530, 5.5f, 6.2f, 22, () -> {
			return Ingredient.fromTag(new ItemTags.Wrapper(new ResourceLocation("forge", "gems/amethyst")));
		})).withGem().withVanillaComponents().withExtraComponents().withVanillaTools()
				.withOre(5, 2, ToolType.PICKAXE, 4, 16, 5, 4, 5, Category.TAIGA));

		materials.add(topaz = new MaterialHelper("topaz", new TCItemTier(1, 530, 5.5f, 6.2f, 22, () -> {
			return Ingredient.fromTag(new ItemTags.Wrapper(new ResourceLocation("forge", "gems/topaz")));
		})).withGem().withVanillaComponents().withExtraComponents().withVanillaTools()
				.withOre(5, 0,  ToolType.PICKAXE, 4, 16, 5, 4, 5, Category.PLAINS));

		materials.add(garnet = new MaterialHelper("garnet", new TCItemTier(1, 530, 4.5f, 7.2f, 22, () -> {
			return Ingredient.fromTag(new ItemTags.Wrapper(new ResourceLocation("forge", "gems/garnet")));
		})).withGem().withVanillaComponents().withExtraComponents().withVanillaTools()
				.withOre(5, 2,  ToolType.PICKAXE, 4, 16, 5, 4, 5, Category.SAVANNA));

		materials.add(opal = new MaterialHelper("opal", new TCItemTier(1, 530, 6.5f, 6.2f, 22, () -> {
			return Ingredient.fromTag(new ItemTags.Wrapper(new ResourceLocation("forge", "gems/opal")));
		})).withGem().withVanillaComponents().withExtraComponents().withVanillaTools()
				.withOre(5, 1, ToolType.PICKAXE, 4, 16, 5, 4, 5, Category.SWAMP));

		materials.add(tanzinite = new MaterialHelper("tanzinite", new TCItemTier(1, 630, 5.5f, 6.2f, 22, () -> {
			return Ingredient.fromTag(new ItemTags.Wrapper(new ResourceLocation("forge", "gems/tanzinite")));
		})).withGem().withVanillaComponents().withExtraComponents().withVanillaTools()
				.withOre(5, 1, ToolType.PICKAXE, 4, 16, 5, 4, 5, Category.JUNGLE));

		materials.add(amber = new MaterialHelper("amber", new TCItemTier(0, 120, 2f, 3f, 22, () -> {
			return Ingredient.fromTag(new ItemTags.Wrapper(new ResourceLocation("forge", "gems/amber")));
		})).withGem().withVanillaComponents().withExtraComponents().withVanillaTools()
				.withOre(5, 0, ToolType.PICKAXE, 4, 16, 5, 4, 5, Category.FOREST));
//
//		materials.add(gallite = new MaterialHelper("gallite", 0xdba827).ingot().components().finish());
//		materials.add(sundrop = new MaterialHelper("sundrop", 0xdba827).ingot().components().finish());
//		materials.add(voidite = new MaterialHelper("voidite", 0xdba827).ingot().components().finish());
//		materials.add(solarium = new MaterialHelper("solarium", 0xdba827).ingot().components().finish());
//		materials.add(dragonsteel = new MaterialHelper("dragonsteel", 0xdba827).ingot().components().finish());
//		materials.add(blacksteel = new MaterialHelper("blacksteel", 0xdba827).ingot().components().finish());
//		materials.add(abyssalium = new MaterialHelper("abyssalium", 0xdba827).ingot().components().finish());
//		materials.add(depthsilver = new MaterialHelper("depthsilver", 0xdba827).ingot().components().finish());
//		materials.add(moonsilver = new MaterialHelper("moonsilver", 0xdba827).ingot().components().finish());
//		materials.add(novagold = new MaterialHelper("novagold", 0xdba827).ingot().components().finish());

////		//if (TinkerRegistry.getMaterial("aeonsteel") == Material.UNKNOWN && TCConfig.materials.aeonsteel) {
//		
//		//aeonsteel.addons.add(new MeltableMaterial("aeonsteel"));
////			aeonsteel.addons.add(new MeltableMaterial(500, new HeadMaterialStats(500, 15.00f, 4.0f, HarvestLevels.COBALT), new HandleMaterialStats(1.1f, 0), new ExtraMaterialStats(50), new ShieldMaterialStats(500 / 4, 85), new BowMaterialStats(0.75f, 1.0f, 2.5f)));
////			aeonsteel.addons.add(new ArmorMaterial(new HelmMaterialStats(500, 2, 2, 0f), new ChestMaterialStats(500, 6, 3, 0f), new LegsMaterialStats(500, 5, 3, 0f), new FeetMaterialStats(500, 3, 2, 0f)));
//		//aeonsteel.addons.add(new MaterialComponents("aeonsteel"));
//		//materials.add(aeonsteel);
////		//}
////
//////		//if (TinkerRegistry.getMaterial("queensgold") == Material.UNKNOWN && TCConfig.materials.queensgold) {
//		
////			queensgold.addons.add(new MeltableMaterial("queensgold"));
////			//queensgold.addons.add(new MeltableMaterial(400, new HeadMaterialStats(70, 3.00f, 3.00f, HarvestLevels.IRON), new HandleMaterialStats(1.05f, -45), new ExtraMaterialStats(20), new ShieldMaterialStats(70 / 4, 57), new BowMaterialStats(1.1f, 1.0f, 0.5f)));
////			//queensgold.addons.add(new ArmorMaterial(new HelmMaterialStats(70, 2, 1, 20f), new ChestMaterialStats(70, 6, 2, 30f), new LegsMaterialStats(70, 5, 2, 20f), new FeetMaterialStats(70, 2, 1, 15f)));
////			//queensgold.addons.add(new MaterialComponents(false));
////			queensgold.addons.add(new MaterialComponents("queensgold"));
////			materials.add(queensgold);
//////		//}
//////
//////		if (TinkerRegistry.getMaterial("dogbearium") == Material.UNKNOWN && TCConfig.materials.dogbearium) {
//			
////			dogbearium.addons.add(new MeltableMaterial("dogbearium"));
////////			dogbearium.addons.add(new MeltableMaterial(800, new HeadMaterialStats(150, 5.00f, 9.00f, HarvestLevels.DIAMOND), new HandleMaterialStats(0.85f, 75), new ExtraMaterialStats(75), new ShieldMaterialStats(150 / 4, 75), new BowMaterialStats(0.1f, 0.5f, 5.5f)));
////////			dogbearium.addons.add(new ArmorMaterial(new HelmMaterialStats(150, 3, 3, -60f), new ChestMaterialStats(150, 8, 4, -60f), new LegsMaterialStats(150, 6, 4, -60f), new FeetMaterialStats(150, 3, 3, -60f)));
////			dogbearium.addons.add(new MaterialComponents("dogbearium"));
////			materials.add(dogbearium);
//////		}
//////
//////		if (TinkerRegistry.getMaterial("sinisterium") == Material.UNKNOWN && TCConfig.materials.sinisterium) {
//			
//			sinisterium.addons.add(new MeltableMaterial("sinisterium"));
//			//sinisterium.addons.add(new MeltableMaterial(666, new HeadMaterialStats(224, 5.00f, 5.00f, HarvestLevels.DIAMOND), new HandleMaterialStats(0.75f, 99), new ExtraMaterialStats(99), new ShieldMaterialStats(224 / 4, 55), new BowMaterialStats(2.1f, 1.6f, 7.2f)));
//			//sinisterium.addons.add(new ArmorMaterial(new HelmMaterialStats(224, 2, 0, 9.5f), new ChestMaterialStats(224, 6, 0, 10.5f), new LegsMaterialStats(224, 5, 0, 10.0f), new FeetMaterialStats(224, 2, 0, 9.5f)));
//			sinisterium.addons.add(new MaterialComponents("sinisterium"));
//			materials.add(sinisterium);
//////		}
//////
//////		if (TinkerRegistry.getMaterial("nihilite") == Material.UNKNOWN && TCConfig.materials.nihilite) {
//			
//			nihilite.addons.add(new MeltableMaterial("nihilite"));//800, new HeadMaterialStats(400, 9.7f, 7.1f, HarvestLevels.COBALT), new HandleMaterialStats(0.9f, 70), new ExtraMaterialStats(74), new ShieldMaterialStats(400 / 4, 95), new BowMaterialStats(0.9f, 1.7f, -1f)));
//			//nihilite.addons.add(new ArmorMaterial(new HelmMaterialStats(400, 2, 0, 19), new ChestMaterialStats(400, 5, 0, 15), new LegsMaterialStats(400, 4, 0, 15), new FeetMaterialStats(400, 2, 0, 19)));
//			nihilite.addons.add(new MaterialComponents("nihilite"));
//			materials.add(nihilite);
//////		}
//////
//////		if (TinkerRegistry.getMaterial("orichalcum") == Material.UNKNOWN && TCConfig.materials.orichalcum) {
//			
//			orichalcum.addons.add(new MeltableMaterial("orichalcum"));//800, new HeadMaterialStats(180, 5.3f, 6.23f, HarvestLevels.OBSIDIAN), new HandleMaterialStats(1.1f, 25), new ExtraMaterialStats(35), new ShieldMaterialStats(180 / 4, 70), new BowMaterialStats(1.9f, 1.9f, 2.3f)));
//			//orichalcum.addons.add(new ArmorMaterial(new HelmMaterialStats(180, 2, 2, 20.5f), new ChestMaterialStats(180, 6, 3, 20.5f), new LegsMaterialStats(180, 5, 3, 20.5f), new FeetMaterialStats(180, 2, 3, 20.5f)));
//			orichalcum.addons.add(new MaterialComponents("orichalcum"));
//			materials.add(orichalcum);
//////		}
//////
//////		if (TinkerRegistry.getMaterial("pandorium") == Material.UNKNOWN && TCConfig.materials.pandorium) {
//			
//			pandorium.addons.add(new MeltableMaterial("pandorium"));//800, new HeadMaterialStats(999, 10.2f, 8.72f, HarvestLevels.OBSIDIAN), new HandleMaterialStats(0.4f, 999), new ExtraMaterialStats(99), new ShieldMaterialStats(999 / 4, 100), new BowMaterialStats(3f, 0.9f, 9f)));
//			//pandorium.addons.add(new ArmorMaterial(new HelmMaterialStats(999, 3, 3, -70.0f), new ChestMaterialStats(999, 8, 4, -70.0f), new LegsMaterialStats(999, 6, 4, -70.0f), new FeetMaterialStats(999, 3, 3, -70.0f)));
//			pandorium.addons.add(new MaterialComponents("pandorium"));
//			materials.add(pandorium);
//////		}
//////
//////		if (TinkerRegistry.getMaterial("rosegold") == Material.UNKNOWN && TCConfig.materials.rosegold) {
//			
//			rosegold.addons.add(new MeltableMaterial("rosegold"));//300, new HeadMaterialStats(99, 4f, 1f, HarvestLevels.STONE), new HandleMaterialStats(1.5f, -90), new ExtraMaterialStats(-90), new ShieldMaterialStats(99 / 4, 24), new BowMaterialStats(0.1f, 0.1f, -2f), true));
//			//rosegold.addons.add(new ArmorMaterial(new HelmMaterialStats(99, 1, 0, 23.0f), new ChestMaterialStats(99, 4, 0, 23.0f), new LegsMaterialStats(99, 3, 0, 23.0f), new FeetMaterialStats(99, 1, 0, 23.0f)));
//			rosegold.addons.add(new MaterialComponents("rosegold"));
//			materials.add(rosegold);
//////		}
//////
//////		if (TinkerRegistry.getMaterial("platinum") == Material.UNKNOWN && TCConfig.materials.platinum) {
//			
//			platinum.addons.add(new MeltableMaterial("platinum"));//400, new HeadMaterialStats(99, 3.7f, 4f, HarvestLevels.IRON), new HandleMaterialStats(1.5f, -90), new ExtraMaterialStats(-90), new ShieldMaterialStats(99 / 4, 14), new BowMaterialStats(0.1f, 0.1f, -2f)));
//			//platinum.addons.add(new ArmorMaterial(new HelmMaterialStats(99, 1, 1, 19.0f), new ChestMaterialStats(99, 4, 1, 19.0f), new LegsMaterialStats(99, 3, 1, 19.0f), new FeetMaterialStats(99, 1, 1, 19.0f)));
//			platinum.addons.add(new MaterialComponents("platinum"));
////			platinum.addons.add(new MaterialOre("", "sheet", 0xd1ecf6, 1, HarvestLevels.IRON, "pickaxe", 1));
////			platinum.addons.add(new MaterialOre("gravel", "", 0xd1ecf6, 1, HarvestLevels.IRON, "shovel", 1));
////			platinum.addons.add(new MaterialOre("sand", "", 0xd1ecf6, 1, HarvestLevels.IRON, "shovel", 1));
////			platinum.addons.add(new MaterialOre("sandstone", "", 0xd1ecf6, 1, HarvestLevels.IRON, "pickaxe", 1));
//			materials.add(platinum);
//////		}
//////
//////		if (TCConfig.materials.silver) {
//			
//			silver.addons.add(new MeltableMaterial("silver"));
//			silver.addons.add(new MaterialComponents("silver"));
//			//silver.addons.add(new MaterialOre("", "node", 0xd1ecf6, 1, HarvestLevels.IRON, "pickaxe", 1));
//			materials.add(silver);
//////		}
//////
//////		if (TinkerRegistry.getMaterial("gold") == Material.UNKNOWN && TCConfig.materials.gold) {
//			
//			gold.addons.add(new MeltableMaterial("gold"));
//			//gold.addons.add(new PresetMaterial(300, new HeadMaterialStats(100, 1f, 1f, HarvestLevels.STONE), new HandleMaterialStats(0.25f, 10), new ExtraMaterialStats(25), new ShieldMaterialStats(100 / 4, 14), new BowMaterialStats(0.2f, 0.4f, -1f), TinkerFluids.gold));
//			//gold.addons.add(new ArmorMaterial(new HelmMaterialStats(100, 1, 0, 33f), new ChestMaterialStats(100, 4, 0, 33f), new LegsMaterialStats(100, 3, 0, 33f), new FeetMaterialStats(100, 1, 0, 33f)));
//			gold.addons.add(new MaterialComponents("gold"));
//			materials.add(gold);
//////		}
//////
//////		if (TinkerRegistry.getMaterial("valyriansteel") == Material.UNKNOWN && TCConfig.materials.valyriansteel) {
//			
//			valyriansteel.addons.add(new MeltableMaterial("valyriansteel"));//800, new HeadMaterialStats(610, 7f, 7f, HarvestLevels.OBSIDIAN), new HandleMaterialStats(0.9f, 180), new ExtraMaterialStats(125), new ShieldMaterialStats(610 / 4, 85), new BowMaterialStats(1.6f, 1.5f, 3f)));
//			//valyriansteel.addons.add(new ArmorMaterial(new HelmMaterialStats(610, 3, 3, 0), new ChestMaterialStats(610, 7, 4, 0), new LegsMaterialStats(610, 6, 2, 0), new FeetMaterialStats(610, 3, 3, 0)));
//			valyriansteel.addons.add(new MaterialComponents("valyriansteel"));
//			materials.add(valyriansteel);
//////		}
//////
//////		if (TinkerRegistry.getMaterial("ice") == Material.UNKNOWN && TCConfig.materials.ice) {
//			
//			ice.addons.add(new MeltableMaterial("ice"));
////			ice.addons.add(new CraftableMaterial(new HeadMaterialStats(120, 4.00f, 3.00f, IRON), new HandleMaterialStats(0.50f, -50), new ExtraMaterialStats(20), new ShieldMaterialStats(120 / 4, 30), this.whyWouldYouMakeABowOutOfThis));
////			ice.addons.add(new ArmorMaterial(new HelmMaterialStats(120, 2, 0, 0), new ChestMaterialStats(120, 4, 0, 0), new LegsMaterialStats(120, 3, 0, 0), new FeetMaterialStats(120, 2, 0, 0)));
//			ice.addons.add(new MaterialComponents("ice"));
//			materials.add(ice);
//////		}
//////
//////		if (TinkerRegistry.getMaterial("froststeel") == Material.UNKNOWN && TCConfig.materials.froststeel) {
//			
//			froststeel.addons.add(new MeltableMaterial("froststeel"));//800, new HeadMaterialStats(610, 7f, 7f, HarvestLevels.OBSIDIAN), new HandleMaterialStats(1.1f, 110), new ExtraMaterialStats(125), new ShieldMaterialStats(610 / 4, 85), new BowMaterialStats(1.6f, 1.5f, 3f)));
////			froststeel.addons.add(new ArmorMaterial(new HelmMaterialStats(610, 2, 2, 0), new ChestMaterialStats(610, 6, 3, 0), new LegsMaterialStats(610, 5, 2, 0), new FeetMaterialStats(610, 3, 1, 0)));
//			froststeel.addons.add(new MaterialComponents("froststeel"));
//			materials.add(froststeel);
//////		}
//////
//////		/*
//////		 * Mithril Stats: - Head: - Durability: 800 - Speed: 8 - Damage: 6 - Mining
//////		 * Level: Cobalt (4) - Rod: - Modifier: 1.3 - Durability: 90 - Extra: -
//////		 * Durability: 90 - Shield: - Durability: 750 - Percent Blocked: 75 - Bow: -
//////		 * Draw Speed: 0.7 - Range: 70 - Bonus Damage: 7 -
//////		 */
//////		if (TinkerRegistry.getMaterial("mithril") == Material.UNKNOWN && TCConfig.materials.mithril) {
//			
//			mithril.addons.add(new MeltableMaterial("mithril"));//800, new HeadMaterialStats(800, 8f, 6f, HarvestLevels.COBALT), new HandleMaterialStats(1.3f, 90), new ExtraMaterialStats(90), new ShieldMaterialStats(750, 75), new BowMaterialStats(0.7f, 70, 7)));
////			mithril.addons.add(new ArmorMaterial(new HelmMaterialStats(750, 2, 3, 25.0f), new ChestMaterialStats(750, 7, 3, 25.0f), new LegsMaterialStats(750, 5, 3, 25.0f), new FeetMaterialStats(750, 2, 3, 25.0f)));
//			mithril.addons.add(new MaterialComponents("mithril"));
////			mithril.addons.add(new MaterialOre("", "spike", 0x99CCFF, 1, HarvestLevels.COBALT, "pickaxe", 1));
//
//			materials.add(mithril);
//////		}
//		
//		
//		//iron.addons.add(new MeltableMaterial("iron"));
//		iron.addons.add(new MaterialComponents("iron"));
//		materials.add(iron);
////
////		if (TCConfig.materials.cloth) {
////			if (TinkerRegistry.getMaterial("blackcloth") == Material.UNKNOWN && TCConfig.materials.black) {
////				black = new MaterialHelper("blackcloth", 0x191616);
////				black.addons.add(new CraftableFabricMaterial(new FabricMaterialStats(100, 0, 0, 100), new BowStringMaterialStats(1), new BackpackMaterialStats(100, 9, 3), null, new ItemStack(Blocks.WOOL, 1, 15)));
////				materials.add(black);
////			}
////
////			if (TinkerRegistry.getMaterial("redcloth") == Material.UNKNOWN && TCConfig.materials.red) {
////				red = new MaterialHelper("redcloth", 0x963430);
////				red.addons.add(new CraftableFabricMaterial(new FabricMaterialStats(100, 0, 0, 100), new BowStringMaterialStats(1), new BackpackMaterialStats(100, 9, 3), null, new ItemStack(Blocks.WOOL, 1, 14)));
////				materials.add(red);
////			}
////
////			if (TinkerRegistry.getMaterial("greencloth") == Material.UNKNOWN && TCConfig.materials.green) {
////				green = new MaterialHelper("greencloth", 0x35461B);
////				green.addons.add(new CraftableFabricMaterial(new FabricMaterialStats(100, 0, 0, 100), new BowStringMaterialStats(1), new BackpackMaterialStats(100, 9, 3), null, new ItemStack(Blocks.WOOL, 1, 13)));
////				materials.add(green);
////			}
////
////			if (TinkerRegistry.getMaterial("browncloth") == Material.UNKNOWN && TCConfig.materials.brown) {
////				brown = new MaterialHelper("browncloth", 0x4F321F);
////				brown.addons.add(new CraftableFabricMaterial(new FabricMaterialStats(100, 0, 0, 100), new BowStringMaterialStats(1), new BackpackMaterialStats(100, 9, 3), null, new ItemStack(Blocks.WOOL, 1, 12)));
////				materials.add(brown);
////			}
////
////			if (TinkerRegistry.getMaterial("bluecloth") == Material.UNKNOWN && TCConfig.materials.blue) {
////				blue = new MaterialHelper("bluecloth", 0x2E388D);
////				blue.addons.add(new CraftableFabricMaterial(new FabricMaterialStats(100, 0, 0, 100), new BowStringMaterialStats(1), new BackpackMaterialStats(100, 9, 3), null, new ItemStack(Blocks.WOOL, 1, 11)));
////				materials.add(blue);
////			}
////
////			if (TinkerRegistry.getMaterial("purplecloth") == Material.UNKNOWN && TCConfig.materials.purple) {
////				purple = new MaterialHelper("purplecloth", 0x7E3DB5);
////				purple.addons.add(new CraftableFabricMaterial(new FabricMaterialStats(100, 0, 0, 100), new BowStringMaterialStats(1), new BackpackMaterialStats(100, 9, 3), null, new ItemStack(Blocks.WOOL, 1, 10)));
////				materials.add(purple);
////			}
////
////			if (TinkerRegistry.getMaterial("cyancloth") == Material.UNKNOWN && TCConfig.materials.cyan) {
////				cyan = new MaterialHelper("cyancloth", 0x2E6E89);
////				cyan.addons.add(new CraftableFabricMaterial(new FabricMaterialStats(100, 0, 0, 100), new BowStringMaterialStats(1), new BackpackMaterialStats(100, 9, 3), null, new ItemStack(Blocks.WOOL, 1, 9)));
////				materials.add(cyan);
////			}
////
////			if (TinkerRegistry.getMaterial("lightgraycloth") == Material.UNKNOWN && TCConfig.materials.lightgray) {
////				lightgray = new MaterialHelper("lightgraycloth", 0x9AA1A1);
////				lightgray.addons.add(new CraftableFabricMaterial(new FabricMaterialStats(100, 0, 0, 100), new BowStringMaterialStats(1), new BackpackMaterialStats(100, 9, 3), null, new ItemStack(Blocks.WOOL, 1, 8)));
////				materials.add(lightgray);
////			}
////
////			if (TinkerRegistry.getMaterial("graycloth") == Material.UNKNOWN && TCConfig.materials.gray) {
////				gray = new MaterialHelper("graycloth", 0x404040);
////				gray.addons.add(new CraftableFabricMaterial(new FabricMaterialStats(100, 0, 0, 100), new BowStringMaterialStats(1), new BackpackMaterialStats(100, 9, 3), null, new ItemStack(Blocks.WOOL, 1, 7)));
////				materials.add(gray);
////			}
////
////			if (TinkerRegistry.getMaterial("pinkcloth") == Material.UNKNOWN && TCConfig.materials.pink) {
////				pink = new MaterialHelper("pinkcloth", 0xD08499);
////				pink.addons.add(new CraftableFabricMaterial(new FabricMaterialStats(100, 0, 0, 100), new BowStringMaterialStats(1), new BackpackMaterialStats(100, 9, 3), null, new ItemStack(Blocks.WOOL, 1, 6)));
////				materials.add(pink);
////			}
////
////			if (TinkerRegistry.getMaterial("limecloth") == Material.UNKNOWN && TCConfig.materials.lime) {
////				lime = new MaterialHelper("limecloth", 0x41AE38);
////				lime.addons.add(new CraftableFabricMaterial(new FabricMaterialStats(100, 0, 0, 100), new BowStringMaterialStats(1), new BackpackMaterialStats(100, 9, 3), null, new ItemStack(Blocks.WOOL, 1, 5)));
////				materials.add(lime);
////			}
////
////			if (TinkerRegistry.getMaterial("yellowcloth") == Material.UNKNOWN && TCConfig.materials.yellow) {
////				yellow = new MaterialHelper("yellowcloth", 0xB1A627);
////				yellow.addons.add(new CraftableFabricMaterial(new FabricMaterialStats(100, 0, 0, 100), new BowStringMaterialStats(1), new BackpackMaterialStats(100, 9, 3), null, new ItemStack(Blocks.WOOL, 1, 4)));
////				materials.add(yellow);
////			}
////
////			if (TinkerRegistry.getMaterial("lightbluecloth") == Material.UNKNOWN && TCConfig.materials.lightblue) {
////				lightblue = new MaterialHelper("lightbluecloth", 0x6B8AC9);
////				lightblue.addons.add(new CraftableFabricMaterial(new FabricMaterialStats(100, 0, 0, 100), new BowStringMaterialStats(1), new BackpackMaterialStats(100, 9, 3), null, new ItemStack(Blocks.WOOL, 1, 3)));
////				materials.add(lightblue);
////			}
////
////			if (TinkerRegistry.getMaterial("magentacloth") == Material.UNKNOWN && TCConfig.materials.magenta) {
////				magenta = new MaterialHelper("magentacloth", 0xB350BC);
////				magenta.addons.add(new CraftableFabricMaterial(new FabricMaterialStats(100, 0, 0, 100), new BowStringMaterialStats(1), new BackpackMaterialStats(100, 9, 3), null, new ItemStack(Blocks.WOOL, 1, 2)));
////				materials.add(magenta);
////			}
////
////			if (TinkerRegistry.getMaterial("orangecloth") == Material.UNKNOWN && TCConfig.materials.orange) {
////				orange = new MaterialHelper("orangecloth", 0xDB7D3E);
////				orange.addons.add(new CraftableFabricMaterial(new FabricMaterialStats(100, 0, 0, 100), new BowStringMaterialStats(1), new BackpackMaterialStats(100, 9, 3), null, new ItemStack(Blocks.WOOL, 1, 1)));
////				materials.add(orange);
////			}
////
////			if (TinkerRegistry.getMaterial("whitecloth") == Material.UNKNOWN && TCConfig.materials.white) {
////				white = new MaterialHelper("whitecloth", 0xDDDDDD);
////				white.addons.add(new CraftableFabricMaterial(new FabricMaterialStats(100, 0, 0, 100), new BowStringMaterialStats(1), new BackpackMaterialStats(100, 9, 3), null, new ItemStack(Blocks.WOOL, 1, 0)));
////				materials.add(white);
////			}
////		}
////
////		if (TinkerRegistry.getMaterial("silky") == Material.UNKNOWN && TCConfig.materials.silky) {
////			silky = new MaterialHelper("silky", 0xffdd7a);
////			silky.addons.add(new CraftableFabricMaterial(new FabricMaterialStats(100, 0, 0, 125), new BowStringMaterialStats(1), new BackpackMaterialStats(100, 9, 3), TinkerCommons.matSilkyCloth, null));
////			materials.add(silky);
////		}
////
////		if (TinkerRegistry.getMaterial("roughspun") == Material.UNKNOWN && TCConfig.materials.roughspun) {
////			roughspun = new MaterialHelper("roughspun", 0x407b17);
////			roughspun.addons.add(new CraftableFabricMaterial(new FabricMaterialStats(100, 0, 0, 75), new BowStringMaterialStats(1), new BackpackMaterialStats(100, 9, 3), new ItemStack(CompendiumTextiles.roughspunCord), new ItemStack(CompendiumTextiles.roughspunBlock)));
////			materials.add(roughspun);
////		}
////
////		if (TCConfig.materials.gems) {
////			if (TinkerRegistry.getMaterial("sapphire") == Material.UNKNOWN && TCConfig.materials.sapphire) {
//				
//				sapphire.addons.add(new MeltableMaterial("sapphire"));
//				sapphire.addons.add(new MaterialComponents("sapphire"));
//				//sapphire.addons.add(new CraftableMaterial("gem", new HeadMaterialStats(320, 4f, 4f, HarvestLevels.OBSIDIAN), new HandleMaterialStats(1.3f, 10), new ExtraMaterialStats(5), new ShieldMaterialStats(320 / 4, 45), whyWouldYouMakeABowOutOfThis));
//				//sapphire.addons.add(new ArmorMaterial(new HelmMaterialStats(320, 3, 0, 10), new ChestMaterialStats(320, 6, 3, 15), new LegsMaterialStats(320, 6, 0, 12.5f), new FeetMaterialStats(320, 3, 0, 10)));
//				//sapphire.addons.add(new MaterialOre("", "gem", 1, HarvestLevels.DIAMOND, "pickaxe", 1));
//				materials.add(sapphire);
//////			}
//////
//////			if (TinkerRegistry.getMaterial("ruby") == Material.UNKNOWN && TCConfig.materials.ruby) {
//				
//				ruby.addons.add(new MeltableMaterial("ruby"));
//				ruby.addons.add(new MaterialComponents("ruby"));
////				ruby.addons.add(new CraftableMaterial("gem", new HeadMaterialStats(320, 4f, 10f, HarvestLevels.OBSIDIAN), new HandleMaterialStats(0.1f, 10), new ExtraMaterialStats(5), new ShieldMaterialStats(320 / 4, 45), whyWouldYouMakeABowOutOfThis));
////				ruby.addons.add(new ArmorMaterial(new HelmMaterialStats(320, 3, 0, 10), new ChestMaterialStats(320, 6, 3, 15), new LegsMaterialStats(320, 6, 0, 12.5f), new FeetMaterialStats(320, 3, 0, 10)));
////				ruby.addons.add(new MaterialOre("", "gem", 1, HarvestLevels.DIAMOND, "pickaxe", 1));
//				materials.add(ruby);
//////			}
//////
//////			if (TinkerRegistry.getMaterial("emerald") == Material.UNKNOWN && TCConfig.materials.emerald) {
//				
//				emerald.addons.add(new MeltableMaterial("emerald"));
//				emerald.addons.add(new MaterialComponents("emerald"));
//////				emerald.addons.add(new CraftableMaterial("gem", new HeadMaterialStats(320, 10f, 4f, HarvestLevels.OBSIDIAN), new HandleMaterialStats(0.1f, 10), new ExtraMaterialStats(5), new ShieldMaterialStats(320 / 4, 45), whyWouldYouMakeABowOutOfThis).disableBlock().disableIngot());
//////				emerald.addons.add(new ArmorMaterial(new HelmMaterialStats(320, 3, 0, 10), new ChestMaterialStats(320, 6, 3, 15), new LegsMaterialStats(320, 6, 0, 12.5f), new FeetMaterialStats(320, 3, 0, 10)));
//////				// emerald.addons.add(new MaterialOre("gem", "gem", 0, 1, HarvestLevels.DIAMOND,
//////				// 1));
//				materials.add(emerald);
//////			}
//////
//////			if (TinkerRegistry.getMaterial("diamond") == Material.UNKNOWN && TCConfig.materials.diamond) {
//				
//				//diamond.addons.add(new MeltableMaterial("diamond"));
//				diamond.addons.add(new MaterialComponents("diamond"));
//////				diamond.addons.add(new CraftableMaterial("gem", new HeadMaterialStats(1006, 4f, 4f, HarvestLevels.OBSIDIAN), new HandleMaterialStats(0.1f, 100), new ExtraMaterialStats(50), new ShieldMaterialStats(1006 / 4, 45), whyWouldYouMakeABowOutOfThis).disableBlock().disableIngot());
//////				diamond.addons.add(new ArmorMaterial(new HelmMaterialStats(1006, 3, 0, 10), new ChestMaterialStats(1006, 6, 3, 15), new LegsMaterialStats(1006, 6, 0, 12.5f), new FeetMaterialStats(1006, 3, 0, 10)));
//////				// diamond.addons.add(new MaterialOre("gem", "gem", 0, 1, HarvestLevels.DIAMOND,
//////				// 1));
//				materials.add(diamond);
//////			}
//////
//////			if (TinkerRegistry.getMaterial("starsapphire") == Material.UNKNOWN && TCConfig.materials.starsapphire) {
//				
//				starsapphire.addons.add(new MeltableMaterial("starsapphire"));
//				starsapphire.addons.add(new MaterialComponents("starsapphire"));
//////				starsapphire.addons.add(new CraftableMaterial("gem", new HeadMaterialStats(115, 1f, 1f, HarvestLevels.COBALT), new HandleMaterialStats(1.5f, 5), new ExtraMaterialStats(1), new ShieldMaterialStats(115 / 4, 55), whyWouldYouMakeABowOutOfThis));
//////				starsapphire.addons.add(new ArmorMaterial(new HelmMaterialStats(115, 3, 0, 20), new ChestMaterialStats(115, 6, 3, 25), new LegsMaterialStats(115, 6, 0, 22.5f), new FeetMaterialStats(115, 3, 0, 20)));
//				materials.add(starsapphire);
//////			}
//////
//////			if (TinkerRegistry.getMaterial("starruby") == Material.UNKNOWN && TCConfig.materials.starruby) {
//				
//				starruby.addons.add(new MeltableMaterial("starruby"));
//				starruby.addons.add(new MaterialComponents("starruby"));
//////				starruby.addons.add(new CraftableMaterial("gem", new HeadMaterialStats(115, 1f, 12f, HarvestLevels.COBALT), new HandleMaterialStats(0.05f, 5), new ExtraMaterialStats(1), new ShieldMaterialStats(115 / 4, 55), whyWouldYouMakeABowOutOfThis));
//////				starruby.addons.add(new ArmorMaterial(new HelmMaterialStats(115, 3, 0, 20), new ChestMaterialStats(115, 6, 3, 25), new LegsMaterialStats(115, 6, 0, 22.5f), new FeetMaterialStats(115, 3, 0, 20)));
//				materials.add(starruby);
//////			}
//////
//////			if (TinkerRegistry.getMaterial("citrine") == Material.UNKNOWN && TCConfig.materials.citrine) {
//				
//				citrine.addons.add(new MeltableMaterial("citrine"));
//				citrine.addons.add(new MaterialComponents("citrine"));
//////				citrine.addons.add(new CraftableMaterial("gem", new HeadMaterialStats(430, 5.5f, 6.2f, HarvestLevels.IRON), new HandleMaterialStats(0.6f, -150), new ExtraMaterialStats(100), new ShieldMaterialStats(430 / 4, 35), whyWouldYouMakeABowOutOfThis));
//////				citrine.addons.add(new ArmorMaterial(new HelmMaterialStats(430, 2, 0, 2.5f), new ChestMaterialStats(430, 5, 3, 3.5f), new LegsMaterialStats(430, 5, 0, 3f), new FeetMaterialStats(430, 2, 0, 2.5f)));
//////				citrine.addons.add(new MaterialOre("", "gem", 0, 1, HarvestLevels.DIAMOND, "pickaxe", 1));
//				materials.add(citrine);
//////			}
//////
//////			if (TinkerRegistry.getMaterial("quartz") == Material.UNKNOWN && TCConfig.materials.quartz) {
//				
//				quartz.addons.add(new MeltableMaterial("quartz"));
//				quartz.addons.add(new MaterialComponents("quartz"));
//////				quartz.addons.add(new CraftableMaterial("gem", new HeadMaterialStats(430, 5.5f, 7f, HarvestLevels.IRON), new HandleMaterialStats(0.6f, -150), new ExtraMaterialStats(100), new ShieldMaterialStats(430 / 4, 35), whyWouldYouMakeABowOutOfThis).disableBlock().disableIngot());
//////				quartz.addons.add(new ArmorMaterial(new HelmMaterialStats(430, 2, 0, 2.5f), new ChestMaterialStats(430, 5, 3, 3.5f), new LegsMaterialStats(430, 5, 0, 3f), new FeetMaterialStats(430, 2, 0, 2.5f)));
//				materials.add(quartz);
//////			}
//////
//////			if (TinkerRegistry.getMaterial("amethyst") == Material.UNKNOWN && TCConfig.materials.amethyst) {
//				
//				amethyst.addons.add(new MeltableMaterial("amethyst"));
//				amethyst.addons.add(new MaterialComponents("amethyst"));
//////				amethyst.addons.add(new CraftableMaterial("gem", new HeadMaterialStats(530, 5.5f, 6.2f, HarvestLevels.IRON), new HandleMaterialStats(0.6f, -150), new ExtraMaterialStats(100), new ShieldMaterialStats(430 / 4, 35), whyWouldYouMakeABowOutOfThis));
//////				amethyst.addons.add(new ArmorMaterial(new HelmMaterialStats(530, 2, 0, 2.5f), new ChestMaterialStats(530, 5, 3, 3.5f), new LegsMaterialStats(530, 5, 0, 3f), new FeetMaterialStats(530, 2, 0, 2.5f)));
//				materials.add(amethyst);
//////			}
//////
//////			if (TinkerRegistry.getMaterial("lapis") == Material.UNKNOWN && TCConfig.materials.lapis) {
//				
//				lapis.addons.add(new MeltableMaterial("lapis"));
//				lapis.addons.add(new MaterialComponents("lapis"));
//////				lapis.addons.add(new CraftableMaterial("gem", new HeadMaterialStats(120, 2f, 2f, HarvestLevels.STONE), new HandleMaterialStats(1.1f, -150), new ExtraMaterialStats(50), new ShieldMaterialStats(120 / 4, 35), whyWouldYouMakeABowOutOfThis).disableBlock().disableIngot());
//////				lapis.addons.add(new ArmorMaterial(new HelmMaterialStats(120, 2, 0, 2.5f), new ChestMaterialStats(120, 4, 3, 3.5f), new LegsMaterialStats(120, 4, 0, 3f), new FeetMaterialStats(120, 2, 0, 2.5f)));
//				materials.add(lapis);
//////			}
//////
//////			if (TinkerRegistry.getMaterial("topaz") == Material.UNKNOWN && TCConfig.materials.topaz) {
//				
//				topaz.addons.add(new MeltableMaterial("topaz"));
//				topaz.addons.add(new MaterialComponents("topaz"));
//////				topaz.addons.add(new CraftableMaterial("gem", new HeadMaterialStats(430, 5.5f, 6.2f, HarvestLevels.IRON), new HandleMaterialStats(0.7f, -150), new ExtraMaterialStats(100), new ShieldMaterialStats(430 / 4, 35), whyWouldYouMakeABowOutOfThis));
//////				topaz.addons.add(new ArmorMaterial(new HelmMaterialStats(530, 2, 0, 2.5f), new ChestMaterialStats(530, 5, 3, 3.5f), new LegsMaterialStats(530, 5, 0, 3f), new FeetMaterialStats(530, 2, 0, 2.5f)));
//				materials.add(topaz);
//////			}
//////
//////			if (TinkerRegistry.getMaterial("garnet") == Material.UNKNOWN && TCConfig.materials.garnet) {
//				
//				garnet.addons.add(new MeltableMaterial("garnet"));
//				garnet.addons.add(new MaterialComponents("garnet"));
//////				garnet.addons.add(new CraftableMaterial("gem", new HeadMaterialStats(430, 6.5f, 6.2f, HarvestLevels.IRON), new HandleMaterialStats(0.6f, -150), new ExtraMaterialStats(100), new ShieldMaterialStats(430 / 4, 35), whyWouldYouMakeABowOutOfThis));
//////				garnet.addons.add(new ArmorMaterial(new HelmMaterialStats(530, 2, 0, 2.5f), new ChestMaterialStats(530, 5, 3, 3.5f), new LegsMaterialStats(530, 5, 0, 3f), new FeetMaterialStats(530, 2, 0, 2.5f)));
//				materials.add(garnet);
//////			}
//////
//////			if (TinkerRegistry.getMaterial("opal") == Material.UNKNOWN && TCConfig.materials.opal) {
//				
//				opal.addons.add(new MeltableMaterial("opal"));
//				opal.addons.add(new MaterialComponents("opal"));
//////				opal.addons.add(new CraftableMaterial("gem", new HeadMaterialStats(630, 5.5f, 6.2f, HarvestLevels.IRON), new HandleMaterialStats(0.6f, -150), new ExtraMaterialStats(100), new ShieldMaterialStats(430 / 4, 35), whyWouldYouMakeABowOutOfThis));
//////				opal.addons.add(new ArmorMaterial(new HelmMaterialStats(530, 2, 0, 2.5f), new ChestMaterialStats(530, 5, 3, 3.5f), new LegsMaterialStats(530, 5, 0, 3f), new FeetMaterialStats(530, 2, 0, 2.5f)));
//				materials.add(opal);
//////			}
//////
//////			if (TinkerRegistry.getMaterial("tanzinite") == Material.UNKNOWN && TCConfig.materials.tanzinite) {
//				
//				tanzinite.addons.add(new MeltableMaterial("tanzinite"));
//				tanzinite.addons.add(new MaterialComponents("tanzinite"));
//////				tanzinite.addons.add(new CraftableMaterial("gem", new HeadMaterialStats(630, 5.5f, 6.2f, HarvestLevels.IRON), new HandleMaterialStats(0.6f, -150), new ExtraMaterialStats(100), new ShieldMaterialStats(430 / 4, 35), whyWouldYouMakeABowOutOfThis));
//////				tanzinite.addons.add(new ArmorMaterial(new HelmMaterialStats(630, 2, 0, 2.5f), new ChestMaterialStats(630, 5, 3, 3.5f), new LegsMaterialStats(630, 5, 0, 3f), new FeetMaterialStats(630, 2, 0, 2.5f)));
//				materials.add(tanzinite);
//////			}
//////
//////			if (TinkerRegistry.getMaterial("amber") == Material.UNKNOWN && TCConfig.materials.amber) {
//				
//				amber.addons.add(new MeltableMaterial("amber"));
//				amber.addons.add(new MaterialComponents("amber"));
//////				amber.addons.add(new CraftableMaterial("gem", new HeadMaterialStats(120, 2f, 3f, HarvestLevels.STONE), new HandleMaterialStats(1.1f, -150), new ExtraMaterialStats(50), new ShieldMaterialStats(120 / 4, 35), whyWouldYouMakeABowOutOfThis));
//////				amber.addons.add(new ArmorMaterial(new HelmMaterialStats(120, 2, 0, 2.5f), new ChestMaterialStats(120, 4, 3, 3.5f), new LegsMaterialStats(120, 4, 0, 3f), new FeetMaterialStats(120, 2, 0, 2.5f)));
//				materials.add(amber);
////			}
////		}
//
////		wood.genSandOre();
////		wood.sandOreGen.setupOre("sand", 256, 64, 10, 15);
////		wood.sandOreGen.setupBiomeWhitelist(new Biome[] {Biomes.BEACH, Biomes.COLD_BEACH, Biomes.STONE_BEACH, Biomes.RIVER, Biomes.FROZEN_RIVER});
////		wood.genGravelOre();
////		wood.gravelOreGen.setupOre("gravel", 256, 64, 10, 15);
////		wood.gravelOreGen.setupBiomeWhitelist(new Biome[] {Biomes.BEACH, Biomes.COLD_BEACH, Biomes.STONE_BEACH, Biomes.RIVER, Biomes.FROZEN_RIVER});
////
////		platinum.genStoneOre();
////		platinum.stoneOreGen.setupOre("stone", "sheet", 20, 5, 4, 15, 3, HarvestLevels.DIAMOND, 5);
////
////		silver.genStoneOre();
////		silver.stoneOreGen.setupOre("stone", "node", 64, 32, 6, 7, 3, HarvestLevels.DIAMOND, 5);
////
////		copper.genStoneOre();
////		copper.stoneOreGen.setupOre("stone", "node", 0x96ffba, 64, 48, 5, 10, 3, HarvestLevels.DIAMOND, 5);
////
////		lead.genStoneOre();
////		lead.stoneOreGen.setupOre("stone", "sheet", 48, 24, 6, 7, 3, HarvestLevels.IRON, 5);
////
////		sapphire.genStoneOre();
////		sapphire.stoneOreGen.setupOre("stone", "crystal", 48, 24, 6, 7, 3, HarvestLevels.IRON, 5);
////		sapphire.stoneOreGen.isTransparent = true;
////		sapphire.stoneOreGen.setupBiome(0, 0, 0, 1, 0, 1);
////
////		ruby.genStoneOre();
////		ruby.stoneOreGen.setupOre("stone", "crystal", 48, 24, 6, 7, 3, HarvestLevels.IRON, 5);
////		ruby.stoneOreGen.isTransparent = true;
////		ruby.stoneOreGen.setupBiome(0, 0, 0, 1, 0, 1);
//////		
//////		sapphire.genStoneOre();
//////		sapphire.stoneOreGen.setupOre("stone", 256, 0, 1, 5);
//////		sapphire.stoneOreGen.setupBiome(-1f, 0f, 0f, 0.5f, 0.5f, 1f);
//////		
//////		ruby.genStoneOre();
//////		ruby.stoneOreGen.setupOre("stone", 256, 0, 1, 5);
//////		ruby.stoneOreGen.setupBiome(-1f, 1f, 1f, 2f, 0f, 0.5f);
//////		
////		citrine.genStoneOre();
////		citrine.stoneOreGen.isTransparent = true;
////		citrine.stoneOreGen.setupOre("stone", "crystal", 256, 0, 1, 5, 3, HarvestLevels.IRON, 5);
////
////		amethyst.genStoneOre();
////		amethyst.stoneOreGen.isTransparent = true;
////		amethyst.stoneOreGen.setupOre("stone", "crystal", 256, 0, 1, 5, 3, HarvestLevels.IRON, 5);
////
////		topaz.genStoneOre();
////		topaz.stoneOreGen.isTransparent = true;
////		topaz.stoneOreGen.setupOre("stone", "crystal", 256, 0, 1, 5, 3, HarvestLevels.IRON, 5);
////
////		garnet.genStoneOre();
////		garnet.stoneOreGen.isTransparent = true;
////		garnet.stoneOreGen.setupOre("stone", "crystal", 256, 0, 1, 5, 3, HarvestLevels.IRON, 5);
////
////		tanzinite.genStoneOre();
////		tanzinite.stoneOreGen.isTransparent = true;
////		tanzinite.stoneOreGen.setupOre("stone", "crystal", 256, 0, 1, 5, 3, HarvestLevels.IRON, 5);
////
////		opal.genStoneOre();
////		opal.stoneOreGen.isTransparent = true;
////		opal.stoneOreGen.setupOre("stone", "crystal", 256, 0, 1, 5, 3, HarvestLevels.IRON, 5);
////
////		amber.genStoneOre();
////		amber.stoneOreGen.isTransparent = true;
////		amber.stoneOreGen.setupOre("stone", "crystal", 256, 0, 1, 5, 3, HarvestLevels.IRON, 5);
////		amber.stoneOreGen.setupBiomeWhitelist(new Biome[] { Biomes.ROOFED_FOREST });
//
////		Material.UNKNOWN.addStats(new ShieldMaterialStats(35, 33));
////		Material.UNKNOWN.addStats(new HelmMaterialStats(35, 1, 0, 0));
////		Material.UNKNOWN.addStats(new ChestMaterialStats(35, 1, 0, 0));
////		Material.UNKNOWN.addStats(new LegsMaterialStats(35, 1, 0, 0));
////		Material.UNKNOWN.addStats(new FeetMaterialStats(35, 1, 0, 0));
////		Material.UNKNOWN.addStats(new FabricMaterialStats(35, 0, 0, 0));
////		Material.UNKNOWN.addStats(new BaubleMaterialStats(35));
////		Material.UNKNOWN.addStats(new BackpackMaterialStats(35, 1, 1));
////
////		for (MaterialHelper m : materials)
////			m.pre();
////		for (MaterialHelper m : materials)
////			m.integrate();
////
////		TinkerRegistry.addMaterialStats(TinkerMaterials.wood, new ShieldMaterialStats(35, 25));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.wood, new HelmMaterialStats(35, 1, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.wood, new ChestMaterialStats(35, 3, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.wood, new LegsMaterialStats(35, 2, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.wood, new FeetMaterialStats(35, 1, 0, 0));
////		// TinkerMaterials.wood.addTrait(axelover, SHIELD);
////
////		TinkerRegistry.addMaterialStats(TinkerMaterials.stone, new ShieldMaterialStats(120, 30));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.stone, new HelmMaterialStats(120, 2, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.stone, new ChestMaterialStats(120, 4, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.stone, new LegsMaterialStats(120, 3, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.stone, new FeetMaterialStats(120, 2, 0, 0));
////		// TinkerRegistry.addMaterialTrait(TinkerMaterials.stone, dulling,
////		// SHIELD);
////		// TinkerMaterials.stone.addTrait(dulling, SHIELD);
////
////		TinkerRegistry.addMaterialStats(TinkerMaterials.flint, new ShieldMaterialStats(150, 30));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.flint, new HelmMaterialStats(150, 2, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.flint, new ChestMaterialStats(150, 4, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.flint, new LegsMaterialStats(150, 3, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.flint, new FeetMaterialStats(150, 2, 0, 0));
////		// TinkerRegistry.addMaterialTrait(TinkerMaterials.flint,
////		// firestarter,
////		// SHIELD);
////
////		TinkerRegistry.addMaterialStats(TinkerMaterials.cactus, new ShieldMaterialStats(210, 25));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.cactus, new HelmMaterialStats(210, 1, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.cactus, new ChestMaterialStats(210, 3, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.cactus, new LegsMaterialStats(210, 2, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.cactus, new FeetMaterialStats(210, 1, 0, 0));
////		// TinkerRegistry.addMaterialTrait(TinkerMaterials.cactus, barbed,
////		// SHIELD);
////
////		TinkerRegistry.addMaterialStats(TinkerMaterials.bone, new ShieldMaterialStats(200, 40));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.bone, new HelmMaterialStats(200, 2, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.bone, new ChestMaterialStats(200, 4, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.bone, new LegsMaterialStats(200, 4, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.bone, new FeetMaterialStats(200, 2, 0, 0));
////		// TinkerRegistry.addMaterialTrait(TinkerMaterials.bone, dogtoy,
////		// SHIELD);
////
////		TinkerRegistry.addMaterialStats(TinkerMaterials.obsidian, new ShieldMaterialStats(139, 50));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.obsidian, new HelmMaterialStats(139, 2, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.obsidian, new ChestMaterialStats(139, 6, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.obsidian, new LegsMaterialStats(139, 5, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.obsidian, new FeetMaterialStats(139, 2, 0, 0));
////
////		TinkerRegistry.addMaterialStats(TinkerMaterials.prismarine, new ShieldMaterialStats(430, 45));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.prismarine, new HelmMaterialStats(430, 2, 0, 2.5f));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.prismarine, new ChestMaterialStats(430, 5, 0, 3.5f));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.prismarine, new LegsMaterialStats(430, 5, 0, 3.0f));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.prismarine, new FeetMaterialStats(430, 2, 0, 2.5f));
////
////		TinkerRegistry.addMaterialStats(TinkerMaterials.endstone, new ShieldMaterialStats(420, 50));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.endstone, new HelmMaterialStats(420, 3, 1, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.endstone, new ChestMaterialStats(420, 6, 1, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.endstone, new LegsMaterialStats(420, 5, 1, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.endstone, new FeetMaterialStats(420, 3, 1, 0));
////
////		TinkerRegistry.addMaterialStats(TinkerMaterials.paper, new ShieldMaterialStats(12, 10));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.paper, new HelmMaterialStats(12, 1, 0, 4));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.paper, new ChestMaterialStats(12, 2, 0, 5.5f));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.paper, new LegsMaterialStats(12, 1, 0, 5));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.paper, new FeetMaterialStats(12, 1, 0, 4));
////
////		TinkerRegistry.addMaterialStats(TinkerMaterials.sponge, new ShieldMaterialStats(550, 20));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.sponge, new HelmMaterialStats(550, 1, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.sponge, new ChestMaterialStats(550, 2, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.sponge, new LegsMaterialStats(550, 2, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.sponge, new FeetMaterialStats(550, 1, 0, 0));
////		// TinkerRegistry.addMaterialStats(TinkerMaterials.sponge, new
////		// FabricMaterialStats(550, 0, 0, 0));
////
////		TinkerRegistry.addMaterialStats(TinkerMaterials.firewood, new ShieldMaterialStats(550, 25));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.firewood, new HelmMaterialStats(550, 1, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.firewood, new ChestMaterialStats(550, 3, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.firewood, new LegsMaterialStats(550, 2, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.firewood, new FeetMaterialStats(550, 1, 0, 0));
////
////		// Slime
////		TinkerRegistry.addMaterialStats(TinkerMaterials.slime, new ShieldMaterialStats(1000, 5));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.slime, new HelmMaterialStats(1000, 1, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.slime, new ChestMaterialStats(1000, 2, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.slime, new LegsMaterialStats(1000, 2, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.slime, new FeetMaterialStats(1000, 1, 0, 0));
////
////		TinkerRegistry.addMaterialStats(TinkerMaterials.blueslime, new ShieldMaterialStats(780, 7));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.blueslime, new HelmMaterialStats(780, 1, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.blueslime, new ChestMaterialStats(780, 2, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.blueslime, new LegsMaterialStats(780, 2, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.blueslime, new FeetMaterialStats(780, 1, 0, 0));
////
////		TinkerRegistry.addMaterialStats(TinkerMaterials.knightslime, new ShieldMaterialStats(850, 27));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.knightslime, new HelmMaterialStats(850, 2, 1, 1.5f));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.knightslime, new ChestMaterialStats(850, 6, 1, 2.5f));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.knightslime, new LegsMaterialStats(850, 5, 1, 2.0f));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.knightslime, new FeetMaterialStats(850, 2, 1, 1.5f));
////
////		TinkerRegistry.addMaterialStats(TinkerMaterials.magmaslime, new ShieldMaterialStats(600, 6));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.magmaslime, new HelmMaterialStats(600, 1, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.magmaslime, new ChestMaterialStats(600, 2, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.magmaslime, new LegsMaterialStats(600, 2, 0, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.magmaslime, new FeetMaterialStats(600, 1, 0, 0));
////
////		// Nether
////		TinkerRegistry.addMaterialStats(TinkerMaterials.netherrack, new ShieldMaterialStats(270, 20));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.netherrack, new HelmMaterialStats(270, 1, 0, -12));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.netherrack, new ChestMaterialStats(270, 3, 0, -12));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.netherrack, new LegsMaterialStats(270, 2, 0, -12));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.netherrack, new FeetMaterialStats(270, 1, 0, -12));
////
////		TinkerRegistry.addMaterialStats(TinkerMaterials.cobalt, new ShieldMaterialStats(780, 55));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.cobalt, new HelmMaterialStats(780, 3, 0, 5));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.cobalt, new ChestMaterialStats(780, 7, 0, 5));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.cobalt, new LegsMaterialStats(780, 5, 0, 5));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.cobalt, new FeetMaterialStats(780, 3, 0, 5));
////
////		TinkerRegistry.addMaterialStats(TinkerMaterials.ardite, new ShieldMaterialStats(990, 75));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.ardite, new HelmMaterialStats(990, 3, 3, -100));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.ardite, new ChestMaterialStats(990, 8, 4, -100));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.ardite, new LegsMaterialStats(990, 6, 4, -100));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.ardite, new FeetMaterialStats(990, 3, 3, -100));
////
////		TinkerRegistry.addMaterialStats(TinkerMaterials.manyullyn, new ShieldMaterialStats(820, 60));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.manyullyn, new HelmMaterialStats(820, 3, 2, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.manyullyn, new ChestMaterialStats(820, 8, 2, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.manyullyn, new LegsMaterialStats(820, 6, 2, 0));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.manyullyn, new FeetMaterialStats(820, 3, 2, 0));
////
////		// Metals
////		TinkerRegistry.addMaterialStats(TinkerMaterials.iron, new ShieldMaterialStats(204, 50));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.iron, new HelmMaterialStats(204, 2, 0, -4));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.iron, new ChestMaterialStats(204, 6, 0, -5));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.iron, new LegsMaterialStats(204, 5, 0, -4));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.iron, new FeetMaterialStats(204, 2, 0, -3));
////
////		TinkerRegistry.addMaterialStats(TinkerMaterials.pigiron, new ShieldMaterialStats(380, 52));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.pigiron, new HelmMaterialStats(380, 2, 0, 0.5f));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.pigiron, new ChestMaterialStats(380, 6, 0, 1.5f));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.pigiron, new LegsMaterialStats(380, 5, 0, 1));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.pigiron, new FeetMaterialStats(380, 2, 0, 0.5f));
////
////		// Mod Integration
////		TinkerRegistry.addMaterialStats(TinkerMaterials.copper, new ShieldMaterialStats(210, 34));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.copper, new HelmMaterialStats(210, 1, 0, 3));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.copper, new ChestMaterialStats(210, 4, 0, 3));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.copper, new LegsMaterialStats(210, 3, 0, 3));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.copper, new FeetMaterialStats(210, 1, 0, 3));
////
////		TinkerRegistry.addMaterialStats(TinkerMaterials.bronze, new ShieldMaterialStats(430, 50));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.bronze, new HelmMaterialStats(430, 2, 0, 0.5f));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.bronze, new ChestMaterialStats(430, 6, 0, 0.5f));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.bronze, new LegsMaterialStats(430, 5, 0, 0.5f));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.bronze, new FeetMaterialStats(430, 2, 0, 0.5f));
////
////		TinkerRegistry.addMaterialStats(TinkerMaterials.lead, new ShieldMaterialStats(334, 42));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.lead, new HelmMaterialStats(334, 1, 0, -20));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.lead, new ChestMaterialStats(334, 5, 0, -20));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.lead, new LegsMaterialStats(334, 4, 0, -20));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.lead, new FeetMaterialStats(334, 1, 0, -20));
////
////		TinkerRegistry.addMaterialStats(TinkerMaterials.silver, new ShieldMaterialStats(250, 33));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.silver, new HelmMaterialStats(250, 1, 0, 1.5f));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.silver, new ChestMaterialStats(250, 3, 0, 1.5f));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.silver, new LegsMaterialStats(250, 2, 0, 1.5f));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.silver, new FeetMaterialStats(250, 1, 0, 1.5f));
////
////		TinkerRegistry.addMaterialStats(TinkerMaterials.electrum, new ShieldMaterialStats(50, 22));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.electrum, new HelmMaterialStats(50, 1, 0, 2.5f));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.electrum, new ChestMaterialStats(50, 3, 0, 2.5f));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.electrum, new LegsMaterialStats(50, 2, 0, 2.5f));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.electrum, new FeetMaterialStats(50, 1, 0, 2.5f));
////
////		TinkerRegistry.addMaterialStats(TinkerMaterials.steel, new ShieldMaterialStats(540, 55));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.steel, new HelmMaterialStats(540, 3, 1, -12));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.steel, new ChestMaterialStats(540, 7, 1, -12));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.steel, new LegsMaterialStats(540, 6, 1, -12));
////		TinkerRegistry.addMaterialStats(TinkerMaterials.steel, new FeetMaterialStats(540, 3, 1, -12));
//
	}

	public void setup(final FMLCommonSetupEvent event) {
		for (MaterialHelper mh : materials) {
			if (mh.getOre() != null)
				mh.getOre().OreGen();
		}
	}

////	public void registerItems(final RegistryEvent.Register<Item> event) {
////		final IForgeRegistry registry = event.getRegistry();
////
////		for (Item i : itemList) {
////			registry.register(i);
////		}
////	}
//
//	@Subscribe
//	public void init(FMLInitializationEvent event) {
//
////		if (TConstruct.instance.pulseManager.isPulseLoaded(TinkerSmeltery.PulseId)) {
////			TinkerRegistry.registerMelting(Items.CHORUS_FRUIT, fluidChorusJuice, Material.VALUE_Nugget);
////			TinkerRegistry.registerMelting(Items.DRAGON_BREATH, fluidDragonsBreath, Material.VALUE_Ingot);
////			TinkerRegistry.registerMelting(TinkerCommons.matNecroticBone, fluidVile, Material.VALUE_Nugget);
////			TinkerRegistry.registerMelting(new ItemStack(Items.SKULL, 1, 1), fluidVile, Material.VALUE_Ingot);
////			TinkerRegistry.registerMelting(TinkerCommons.matMendingMoss, fluidVibrant, Material.VALUE_Ingot * 2);
////			TinkerRegistry.registerMelting(Blocks.ICE, fluidSlush, Material.VALUE_Ingot);
////			TinkerRegistry.registerMelting(Blocks.PACKED_ICE, fluidSlush, Material.VALUE_Ingot * 4);
////			TinkerRegistry.registerMelting(Blocks.QUARTZ_BLOCK, fluidQuartz, Material.VALUE_Ingot * 4);
////			TinkerRegistry.registerMelting(Items.QUARTZ, fluidQuartz, Material.VALUE_Ingot);
////
////			TinkerRegistry.registerTableCasting(new ItemStack(Items.QUARTZ, 1, 0), ItemStack.EMPTY, fluidQuartz, Material.VALUE_Ingot);
////			TinkerRegistry.registerBasinCasting(new ItemStack(Blocks.QUARTZ_BLOCK, 1, 0), ItemStack.EMPTY, fluidQuartz, Material.VALUE_Ingot * 4);
////			TinkerRegistry.registerBasinCasting(new ItemStack(Blocks.ICE, 1, 0), ItemStack.EMPTY, fluidSlush, Material.VALUE_Ingot);
////
////			if (aeonsteel != null)
////				registerAlloy(new FluidStack(((MeltableMaterial) aeonsteel.addons.get(0)).fluid, 4), new FluidStack(fluidChorusJuice, 1), new FluidStack(TinkerFluids.cobalt, 3));
////
////			if (queensgold != null)
////				TinkerRegistry.registerAlloy(new FluidStack(((MeltableMaterial) queensgold.addons.get(0)).fluid, 2), new FluidStack(TinkerFluids.gold, 1), new FluidStack(TinkerFluids.knightslime, 1));
////
////			if (dogbearium != null)
////				TinkerRegistry.registerAlloy(new FluidStack(((MeltableMaterial) dogbearium.addons.get(0)).fluid, 4), new FluidStack(TinkerFluids.ardite, 1), new FluidStack(fluidDragonsBreath, 3));
////
////			if (sinisterium != null)
////				TinkerRegistry.registerAlloy(new FluidStack(((MeltableMaterial) sinisterium.addons.get(0)).fluid, 6), new FluidStack(TinkerFluids.blood, 1), new FluidStack(fluidVile, 2), new FluidStack(TinkerFluids.iron, 4));
////
////			if (nihilite != null)
////				TinkerRegistry.registerAlloy(new FluidStack(((MeltableMaterial) nihilite.addons.get(0)).fluid, 3), new FluidStack(TinkerFluids.cobalt, 1), new FluidStack(fluidVile, 2));
////
////			if (orichalcum != null)
////				TinkerRegistry.registerAlloy(new FluidStack(((MeltableMaterial) orichalcum.addons.get(0)).fluid, 6), new FluidStack(TinkerFluids.bronze, 4), new FluidStack(fluidVibrant, 2), new FluidStack(TinkerFluids.gold, 1));
////
////			if (pandorium != null)
////				TinkerRegistry.registerAlloy(new FluidStack(((MeltableMaterial) pandorium.addons.get(0)).fluid, 3), new FluidStack(TinkerFluids.ardite, 1), new FluidStack(fluidVibrant, 2));
////
////			if (rosegold != null)
////				TinkerRegistry.registerAlloy(new FluidStack(((MeltableMaterial) rosegold.addons.get(0)).fluid, 4), new FluidStack(TinkerFluids.gold, 1), new FluidStack(TinkerFluids.copper, 3));
////
////			if (valyriansteel != null)
////				TinkerRegistry.registerAlloy(new FluidStack(((MeltableMaterial) valyriansteel.addons.get(0)).fluid, 4), new FluidStack(TinkerFluids.steel, 2), new FluidStack(TinkerFluids.obsidian, 2), new FluidStack(fluidDragonsBreath, 1));
////
////			if (froststeel != null)
////				TinkerRegistry.registerAlloy(new FluidStack(((MeltableMaterial) froststeel.addons.get(0)).fluid, 4), new FluidStack(TinkerFluids.steel, 2), new FluidStack(TinkerFluids.cobalt, 2), new FluidStack(fluidSlush, 1));
////		}
//
//		// if (!TD_Config.materials.isBlacklisted("pureardite") &&
//		// !TD_Config.materials.isBlacklisted("purifiedgold"))
//		// TinkerRegistry.registerAlloy(new
//		// FluidStack(getMaterialHelper("pureardite").fluid, 1),
//		// new FluidStack(TinkerFluids.ardite, 1), new FluidStack(fluidDragonsBreath,
//		// 2),
//		// new FluidStack(TinkerFluids.blood, 2), new
//		// FluidStack(getMaterialHelper("purifiedgold").fluid, 2));
//		//
//		// if (!TD_Config.materials.isBlacklisted("purecobalt")
//		// &&!TD_Config.materials.isBlacklisted("purifiedsilver"))
//		// TinkerRegistry.registerAlloy(new
//		// FluidStack(getMaterialHelper("purecobalt").fluid, 1),
//		// new FluidStack(TinkerFluids.cobalt, 1), new FluidStack(fluidDragonsBreath,
//		// 2),
//		// new FluidStack(fluidSlush, 2), new
//		// FluidStack(getMaterialHelper("purifiedsilver").fluid, 2));
//		//
//		// if (!TD_Config.materials.isBlacklisted("puremanyullyn") &&
//		// !TD_Config.materials.isBlacklisted("purecobalt")
//		// && !TD_Config.materials.isBlacklisted("pureardite"))
//		// TinkerRegistry.registerAlloy(new
//		// FluidStack(getMaterialHelper("puremanyullyn").fluid, 2),
//		// new FluidStack(getMaterialHelper("pureardite").fluid, 1),
//		// new FluidStack(getMaterialHelper("purecobalt").fluid, 1));
//		//
//		// if (!TD_Config.materials.isBlacklisted("purifiedgold"))
//		// TinkerRegistry.registerAlloy(new
//		// FluidStack(getMaterialHelper("purifiedgold").fluid, 1),
//		// new FluidStack(TinkerFluids.gold, 1), new FluidStack(fluidQuartz, 8),
//		// new FluidStack(TinkerFluids.glass, 2), new
//		// FluidStack(TinkerFluids.purpleSlime, 2));
//		//
//		// if (!TD_Config.materials.isBlacklisted("purifiedsilver"))
//		// TinkerRegistry.registerAlloy(new
//		// FluidStack(getMaterialHelper("purifiedsilver").fluid, 1),
//		// new FluidStack(TinkerFluids.silver, 1), new FluidStack(fluidQuartz, 8),
//		// new FluidStack(TinkerFluids.glass, 2), new
//		// FluidStack(TinkerFluids.purpleSlime, 2));
//
//		// if (!TD_Config.materials.isBlacklisted("nihilite"))
//		// TinkerRegistry.registerAlloy(new FluidStack(fluids.get("solarium"), 1),
//		// new FluidStack(TinkerFluids.steel, 1), new FluidStack(fluids.get("sundrop"),
//		// 1));
//		//
//		// if (!TD_Config.materials.isBlacklisted("nihilite"))
//		// TinkerRegistry.registerAlloy(new FluidStack(fluids.get("dragonsteel"), 1),
//		// new FluidStack(TinkerFluids.steel, 1), new FluidStack(fluids.get("gallite"),
//		// 1));
//		//
//		// if (!TD_Config.materials.isBlacklisted("nihilite"))
//		// TinkerRegistry.registerAlloy(new FluidStack(fluids.get("blacksteel"), 1), new
//		// FluidStack(TinkerFluids.steel, 1),
//		// new FluidStack(fluids.get("voidite"), 1));
//		//
//		// if (!TD_Config.materials.isBlacklisted("nihilite"))
//		// TinkerRegistry.registerAlloy(new FluidStack(fluids.get("abyssalium"), 1),
//		// new FluidStack(fluids.get("voidite"), 1), new
//		// FluidStack(fluids.get("sundrop"), 1));
//		// TinkerRegistry.registerAlloy(new FluidStack(fluids.get("depthsilver"), 1),
//		// new FluidStack(TinkerFluids.silver, 1), new
//		// FluidStack(fluids.get("abyssalium"), 1));
//		// TinkerRegistry.registerAlloy(new FluidStack(fluids.get("moonsilver"), 1),
//		// new FluidStack(TinkerFluids.silver, 1), new FluidStack(fluids.get("voidite"),
//		// 1));
//		// TinkerRegistry.registerAlloy(new FluidStack(fluids.get("novagold"), 1), new
//		// FluidStack(TinkerFluids.gold, 1),
//		// new FluidStack(fluids.get("sundrop"), 1));
//
////		if (TinkersCompendium.bloodmagic)
////			addonbloodmagic.init(event);
//
////		for (MaterialHelper m : materials) {
////			if (!TCConfig.materials.isBlacklisted(m.name))
////				m.setupClient();
////		}
//
//		for (MaterialHelper m : materials)
//			m.init();
//		for (MaterialHelper m : materials)
//			m.client();
//
////		Collection<Material> mats = TinkerRegistry.getAllMaterials();
////		for (Material m : TinkerRegistry.getAllMaterials()) {
////			if (!m.hasStats(SHIELD)) {
////				if (m.hasStats(MaterialTypes.HEAD)) {
////					int dur = ((HeadMaterialStats) m.getStats(MaterialTypes.HEAD)).durability;
////					m.addStats(new ShieldMaterialStats(dur, 33));
////					m.addStats(new HelmMaterialStats(dur, 1, 0, 0));
////					m.addStats(new ChestMaterialStats(dur, 1, 0, 0));
////					m.addStats(new LegsMaterialStats(dur, 1, 0, 0));
////					m.addStats(new FeetMaterialStats(dur, 1, 0, 0));
////					// m.addStats(new ClothMaterialStats(dur, 1, 0, 0));
////				}
////			}
////		}
//
//	}
//
//	@Subscribe
//	public void postInit(FMLPostInitializationEvent event) {
//
//		for (MaterialHelper m : materials)
//			m.post();
//	}
//
//	void oreDictComponent(String name, ComponentPart item) {
//		NonNullList<ItemStack> stacks = null;
//		stacks = NonNullList.create();
//
//		item.getSubItems(item.getCreativeTab(), stacks);
//
//		for (ItemStack s : stacks) {
//			String str = s.getTagCompound().getString("Material");
//			OreDictionary.registerOre(name + StringUtils.capitalize(str), s);
//		}
//	}
//
//	public static void registerAlloy(FluidStack output, FluidStack... components) {
//		AlloyRecipe r = new AlloyRecipe(output, components);
//		TinkerRegistry.registerAlloy(r);
//	}
//
//	@SideOnly(Side.CLIENT)
//	@SubscribeEvent
//	public static void registerModels(ModelRegistryEvent event) {
//		for (MaterialHelper m : materials)
//			m.models();
//	}
//
//	// @SubscribeEvent
//	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
////		for (Block i : blockList) {
////			event.getRegistry().register(i);
////		}
//
//		fluidChorusJuice = regFluid("chorusjuice", 0xd982ff, event);
//		registerBlock(event.getRegistry(), new ChorusJuice(fluidChorusJuice), fluidChorusJuice.getName());
//		TinkersCompendium.proxy.registerFluidModels(fluidChorusJuice);
//
//		fluidVile = regFluid("vile", 0x111111, event);
//		registerBlock(event.getRegistry(), new VileFluid(fluidVile), fluidVile.getName());
//		TinkersCompendium.proxy.registerFluidModels(fluidVile);
//
//		fluidVibrant = regFluid("vibrant", 0x76ff00, event);
//		registerBlock(event.getRegistry(), new VibrantFluid(fluidVibrant), fluidVibrant.getName());
//		TinkersCompendium.proxy.registerFluidModels(fluidVibrant);
//
//		fluidSlush = regFluid("slush", 0xbfefff, event);
//		registerClassicBlock(event.getRegistry(), fluidSlush);
//		TinkersCompendium.proxy.registerFluidModels(fluidSlush);
//
//		fluidQuartz = regMoltenFluid("quartz", 0xdddddd, event);
//		fluidDragonsBreath = regMoltenFluid("dragonsbreath", 0x7f00b7, event);
//	}
//
//	@SubscribeEvent
//	public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
////		for (MaterialHelper m : materials) {
////			if (!TCConfig.materials.isBlacklisted(m.name))
////				m.setupRecipes(event);
////		}
//	}
//
//	static FluidColored regFluid(String name, int color, RegistryEvent.Register<Block> event) {
//		FluidColored f = new FluidColored(name, color);
//		f.setUnlocalizedName(Reference.MOD_ID + "." + name);
//		FluidRegistry.registerFluid(f);
//		FluidRegistry.addBucketForFluid(f);
//		return f;
//	}
//
//	static FluidMolten regMoltenFluid(String name, int color, RegistryEvent.Register<Block> event) {
//		FluidMolten f = new FluidMolten(name, color);
//		f.setUnlocalizedName(Reference.MOD_ID + "." + name);
//		FluidRegistry.registerFluid(f);
//		FluidRegistry.addBucketForFluid(f);
//
//		registerMoltenBlock(event.getRegistry(), f);
//
//		TinkersCompendium.proxy.registerFluidModels(f);
//
//		return f;
//	}
//
//	/** Registers a non-burning water based block for the fluid */
//	public static BlockFluidBase registerClassicBlock(IForgeRegistry<Block> registry, Fluid fluid) {
//		return registerBlock(registry, new BlockTinkerFluid(fluid, net.minecraft.block.material.Material.WATER), fluid.getName());
//	}
//
//	/** Registers a hot lava-based block for the fluid, prefix with molten_ */
//	public static BlockMolten registerMoltenBlock(IForgeRegistry<Block> registry, Fluid fluid) {
//		return registerBlock(registry, new BlockMolten(fluid), "molten_" + fluid.getName()); // molten_foobar prefix
//	}
//
//	protected static <T extends Block> T registerBlock(IForgeRegistry<Block> registry, T block, String name) {
//		if (!name.equals(name.toLowerCase(Locale.US))) {
//			throw new IllegalArgumentException(String.format("Unlocalized names need to be all lowercase! Block: %s", name));
//		}
//
//		String prefixedName = Util.prefix(name);
//		block.setUnlocalizedName(prefixedName);
//
//		register(registry, block, name);
//		return block;
//	}
//
//	protected static <T extends IForgeRegistryEntry<T>> T register(IForgeRegistry<T> registry, T thing, String name) {
//		thing.setRegistryName(Util.getResource(name));
//		registry.register(thing);
//		return thing;
//	}
}
