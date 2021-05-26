package lance5057.compendium.indexes.woods;

import java.util.ArrayList;
import java.util.List;

import lance5057.compendium.appendixes.carpentry.AppendixCarpentry;
import lance5057.compendium.appendixes.carpentry.materialhelper.CarpentryMaterialHelper;
import lance5057.compendium.appendixes.construction.AppendixConstruction;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;

public class IndexVanillaWoods {
    public static List<MaterialHelperBase> OAK = new ArrayList<MaterialHelperBase>();
    public static List<MaterialHelperBase> DARK_OAK = new ArrayList<MaterialHelperBase>();
    public static List<MaterialHelperBase> BIRCH = new ArrayList<MaterialHelperBase>();
    public static List<MaterialHelperBase> SPRUCE = new ArrayList<MaterialHelperBase>();
    public static List<MaterialHelperBase> ACACIA = new ArrayList<MaterialHelperBase>();
    public static List<MaterialHelperBase> JUNGLE = new ArrayList<MaterialHelperBase>();

    public IndexVanillaWoods() {
	CarpentryMaterialHelper oak = new CarpentryMaterialHelper("oak").withComponents();
	OAK.add(oak);
	AppendixCarpentry.woods.add(oak);
	AppendixConstruction.bases.add(oak);
	
	CarpentryMaterialHelper dark_oak = new CarpentryMaterialHelper("dark_oak").withComponents();
	DARK_OAK.add(dark_oak);
	AppendixCarpentry.woods.add(dark_oak);
	AppendixConstruction.bases.add(dark_oak);
	
	CarpentryMaterialHelper birch = new CarpentryMaterialHelper("birch").withComponents();
	BIRCH.add(birch);
	AppendixCarpentry.woods.add(birch);
	AppendixConstruction.bases.add(birch);
	
	CarpentryMaterialHelper spruce = new CarpentryMaterialHelper("spruce").withComponents();
	SPRUCE.add(spruce);
	AppendixCarpentry.woods.add(spruce);
	AppendixConstruction.bases.add(spruce);
	
	CarpentryMaterialHelper acacia = new CarpentryMaterialHelper("acacia").withComponents();
	ACACIA.add(acacia);
	AppendixCarpentry.woods.add(acacia);
	AppendixConstruction.bases.add(acacia);

	CarpentryMaterialHelper jungle = new CarpentryMaterialHelper("jungle").withComponents();
	JUNGLE.add(jungle);
	AppendixCarpentry.woods.add(jungle);
	AppendixConstruction.bases.add(jungle);
    }

}
