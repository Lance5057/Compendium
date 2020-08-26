package lance5057.compendium.core.library.materialutilities;

import java.util.ArrayList;
import java.util.List;

import lance5057.compendium.Reference;
import lance5057.compendium.core.library.TCItemTier;
import lance5057.compendium.core.library.materialutilities.addons.CraftableMaterial;
import lance5057.compendium.core.library.materialutilities.addons.MaterialExtraComponents;
import lance5057.compendium.core.library.materialutilities.addons.MaterialOre;
import lance5057.compendium.core.library.materialutilities.addons.MaterialTools;
import lance5057.compendium.core.library.materialutilities.addons.MaterialVanillaComponents;
import lance5057.compendium.core.library.materialutilities.addons.MeltableMaterial;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.world.biome.Biome.Category;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class MaterialHelper {
	String name;
	int color;
	String parentMod;
	
	// public Material mat;
	// MaterialIntegration matint;
	public List<MaterialBase> addons;

	boolean preset = false;
	
	public MaterialHelper(String name, int color)
	{
		this(name, color, Reference.MOD_ID);
	}

	public MaterialHelper(String name, int color, String parentMod) {
		this.name = name;
		this.color = color;
		this.parentMod = parentMod;

		// mat = new Material(name, color);
		addons = new ArrayList<MaterialBase>();
	}
	
	public MaterialHelper ingot() {
		addons.add(new MeltableMaterial(name, parentMod));
		return this;
	}

	public MaterialHelper gem() {
		addons.add(new CraftableMaterial(name));
		return this;
	}


	public MaterialHelper components() {
		addons.add(new MaterialVanillaComponents(name, parentMod));
		addons.add(new MaterialExtraComponents(name, parentMod));
		return this;
	}
	
	public MaterialHelper extracomponents() {
		addons.add(new MaterialExtraComponents(name, parentMod));
		return this;
	}

	public MaterialHelper tool(TCItemTier tier) {
		addons.add(new MaterialTools(name, tier));
		return this;
	}

	public MaterialHelper ore(float hardness, int level, String tool, float resistance, int ymax, int ymin,
			int veinSize, int veinChance, Category biomeCategory) {
		addons.add(new MaterialOre(name, hardness, level, tool, resistance, ymax, ymin, veinSize, veinChance,
				biomeCategory));
		return this;
	}
	
	public MaterialHelper finish()
	{
		tags();
		recipes();
		loot();
		return this; 
	}

//	public MaterialHelper(Material mat) {
//		this.name = mat.identifier;
//		this.color = mat.materialTextColor;
//
//		this.mat = mat;
//		addons = new ArrayList<MaterialBase>();
//
//		preset = true;
//	}

//	public void pre() {
//		for (MaterialBase mb : addons) {
//			mb.setupPre(this);
//		}
//	}
//
//	public void init() {
//		for (MaterialBase mb : addons) {
//			mb.setupInit(this);
//		}
//	}

//	public void integrate() {
//		if (!preset) {
//			matint = new MaterialIntegration(mat, null, StringUtils.capitalize(mat.identifier));
//
//			for (MaterialBase mb : addons) {
//				mb.setupIntegration(matint);
//			}
//			matint.toolforge().preInit();
//
//			TinkersCompendium.proxy.registerMatColor(mat, color);
//
//			TinkerRegistry.integrate(matint);
//		}
//	}

//	public void post() {
//		for (MaterialBase mb : addons) {
//			mb.setupPost(this);
//		}

//		if (!preset)
//			matint.integrate();

//		if(TinkersCompendium.config.developerFeatures)
//		{
//			OutputWikiPages.outputWikiMaterial(this);
//		}
//	}
	
	

	public void client() {
		for (MaterialBase mb : addons) {
			mb.setupClient(this);
		}
	}

	public void models() {
		for (MaterialBase mb : addons) {
			mb.setupModels(this);
		}
	}
	
	public void tags() {
		for (MaterialBase mb : addons) {
			mb.setupBlockTags();
			mb.setupItemTags();
		}
	}
	
	public void recipes() {
		for (MaterialBase mb : addons) {
			mb.setupRecipes();
		}
	}
	
	public void loot() {
		for (MaterialBase mb : addons) {
			mb.setupLoot();
		}
	}

	public void setup(final FMLCommonSetupEvent event) {
		for (MaterialBase mb : addons) {
			mb.setup(event);
		}
	}

}
