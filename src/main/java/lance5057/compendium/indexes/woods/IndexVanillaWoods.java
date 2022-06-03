//package lance5057.compendium.indexes.woods;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import lance5057.compendium.appendixes.carpentry.AppendixCarpentry;
//import lance5057.compendium.appendixes.carpentry.materialhelper.CarpentryMaterialHelper;
//import lance5057.compendium.appendixes.construction.AppendixConstruction;
//import lance5057.compendium.appendixes.construction.materialhelper.ConstructionMaterialHelper;
//import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
//
//public class IndexVanillaWoods {
//    public static List<MaterialHelperBase> OAK = new ArrayList<MaterialHelperBase>();
//    public static List<MaterialHelperBase> DARK_OAK = new ArrayList<MaterialHelperBase>();
//    public static List<MaterialHelperBase> BIRCH = new ArrayList<MaterialHelperBase>();
//    public static List<MaterialHelperBase> SPRUCE = new ArrayList<MaterialHelperBase>();
//    public static List<MaterialHelperBase> ACACIA = new ArrayList<MaterialHelperBase>();
//    public static List<MaterialHelperBase> JUNGLE = new ArrayList<MaterialHelperBase>();
//    public static List<MaterialHelperBase> CRIMSON = new ArrayList<MaterialHelperBase>();
//    public static List<MaterialHelperBase> WARPED = new ArrayList<MaterialHelperBase>();
//
//    public IndexVanillaWoods() {
//	CarpentryMaterialHelper oak = new CarpentryMaterialHelper("oak", "minecraft", "log").withShingles("log")
//		.withFurniture();
//	OAK.add(oak);
//	AppendixCarpentry.woods.add(oak);
//
//	ConstructionMaterialHelper constructionOak = new ConstructionMaterialHelper("oak").withBase();
//	AppendixConstruction.constructs.add(constructionOak);
//
//	CarpentryMaterialHelper dark_oak = new CarpentryMaterialHelper("dark_oak", "minecraft", "log")
//		.withShingles("log").withFurniture();
//	DARK_OAK.add(dark_oak);
//	AppendixCarpentry.woods.add(dark_oak);
//
//	ConstructionMaterialHelper constructionDarkOak = new ConstructionMaterialHelper("dark_oak").withBase();
//	AppendixConstruction.constructs.add(constructionDarkOak);
//
//	CarpentryMaterialHelper birch = new CarpentryMaterialHelper("birch", "minecraft", "log").withShingles("log")
//		.withFurniture();
//	BIRCH.add(birch);
//	AppendixCarpentry.woods.add(birch);
//
//	ConstructionMaterialHelper constructionBirch = new ConstructionMaterialHelper("birch").withBase();
//	AppendixConstruction.constructs.add(constructionBirch);
//
//	CarpentryMaterialHelper spruce = new CarpentryMaterialHelper("spruce", "minecraft", "log").withShingles("log")
//		.withFurniture();
//	SPRUCE.add(spruce);
//	AppendixCarpentry.woods.add(spruce);
//
//	ConstructionMaterialHelper constructionSpruce = new ConstructionMaterialHelper("spruce").withBase();
//	AppendixConstruction.constructs.add(constructionSpruce);
//
//	CarpentryMaterialHelper acacia = new CarpentryMaterialHelper("acacia", "minecraft", "log").withShingles("log")
//		.withFurniture();
//	ACACIA.add(acacia);
//	AppendixCarpentry.woods.add(acacia);
//
//	ConstructionMaterialHelper constructionAcacia = new ConstructionMaterialHelper("acacia").withBase();
//	AppendixConstruction.constructs.add(constructionAcacia);
//
//	CarpentryMaterialHelper jungle = new CarpentryMaterialHelper("jungle", "minecraft", "log").withShingles("log")
//		.withFurniture();
//	JUNGLE.add(jungle);
//	AppendixCarpentry.woods.add(jungle);
//
//	ConstructionMaterialHelper constructionJungle = new ConstructionMaterialHelper("jungle").withBase();
//	AppendixConstruction.constructs.add(constructionJungle);
//
//	CarpentryMaterialHelper crimson = new CarpentryMaterialHelper("crimson", "minecraft", "stem")
//		.withShingles("stem").withFurniture();
//	JUNGLE.add(crimson);
//	AppendixCarpentry.woods.add(crimson);
//
//	ConstructionMaterialHelper constructionCrimson = new ConstructionMaterialHelper("crimson").withBase();
//	AppendixConstruction.constructs.add(constructionCrimson);
//
//	CarpentryMaterialHelper warped = new CarpentryMaterialHelper("warped", "minecraft", "stem").withShingles("stem")
//		.withFurniture();
//	JUNGLE.add(warped);
//	AppendixCarpentry.woods.add(warped);
//
//	ConstructionMaterialHelper constructionWarped = new ConstructionMaterialHelper("warped").withBase();
//	AppendixConstruction.constructs.add(constructionWarped);
//    }
//
//}
