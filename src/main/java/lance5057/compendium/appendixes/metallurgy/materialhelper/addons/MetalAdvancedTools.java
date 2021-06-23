package lance5057.compendium.appendixes.metallurgy.materialhelper.addons;

import javax.annotation.Nullable;

import lance5057.compendium.CompendiumItems;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.core.data.builders.TCBlockModels;
import lance5057.compendium.core.data.builders.TCEnglishLoc;
import lance5057.compendium.core.data.builders.TCItemModels;
import lance5057.compendium.core.data.builders.loottables.BlockLoot;
import lance5057.compendium.core.items.tools.HammerItem;
import lance5057.compendium.core.items.tools.SawItem;
import lance5057.compendium.core.items.weapons.ZweihanderItem;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.generators.ModelBuilder.Perspective;
import net.minecraftforge.fml.RegistryObject;

public class MetalAdvancedTools implements MaterialBase {

    public RegistryObject<BowItem> BOW;
    public RegistryObject<ZweihanderItem> ZWEIHANDER;
//	public Item crossbow;
//	public Item shield;
    public RegistryObject<HammerItem> HAMMER;
    public RegistryObject<SawItem> SAW;
    public RegistryObject<ShearsItem> SHEARS;
    public RegistryObject<Item> PRYBAR;
    public RegistryObject<Item> PLIERS;
    public RegistryObject<Item> WRENCH;
//	public Item fishingrod;
//	public Item trident;
//	public Item sickle;

    public MetalAdvancedTools(MetallurgyMaterialHelper mh) {

    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void setupClient(MaterialHelperBase mat) {
	// TODO Auto-generated method stub
	ItemModelsProperties.registerProperty(BOW.get(), new ResourceLocation("pull"), new IItemPropertyGetter() {

	    @Override
	    public float call(ItemStack arg0, ClientWorld arg1, LivingEntity arg2) {
		if (arg2 == null) {
		    return 0.0F;
		} else {
		    return !(arg2.getActiveItemStack().getItem() instanceof BowItem) ? 0.0F
			    : (float) (arg0.getUseDuration() - arg2.getItemInUseCount()) / 20.0F;
		}
	    }

	});
	ItemModelsProperties.registerProperty(BOW.get(), new ResourceLocation("pulling"), new IItemPropertyGetter() {

	    @Override
	    public float call(ItemStack stack, @Nullable ClientWorld worldIn, @Nullable LivingEntity entityIn) {
		return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F
			: 0.0F;
	    }
	});
    }

    @Override
    public void setup(MaterialHelperBase mh) {
	BOW = mh.ITEMS.register(mh.name + "bow",
		() -> new BowItem(new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	HAMMER = mh.ITEMS.register(mh.name + "hammer",
		() -> new HammerItem(mh.tier, 2, -3.4f, new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	SAW = mh.ITEMS.register(mh.name + "saw",
		() -> new SawItem(mh.tier, 0, -3.0f, new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
	ZWEIHANDER = mh.ITEMS.register(mh.name + "zweihander", () -> new ZweihanderItem(mh.tier, 5, -2.6F,
		new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));

	SHEARS = mh.ITEMS.register(mh.name + "shears", () -> new ShearsItem(
		new Item.Properties().maxDamage(mh.tier.getMaxUses()).group(CompendiumItems.GROUP_MATERIALS)));
	PRYBAR = mh.ITEMS.register(mh.name + "prybar", () -> new Item(
		new Item.Properties().maxDamage(mh.tier.getMaxUses()).group(CompendiumItems.GROUP_MATERIALS)));
	PLIERS = mh.ITEMS.register(mh.name + "pliers", () -> new Item(
		new Item.Properties().maxDamage(mh.tier.getMaxUses()).group(CompendiumItems.GROUP_MATERIALS)));
	WRENCH = mh.ITEMS.register(mh.name + "wrench", () -> new Item(
		new Item.Properties().maxDamage(mh.tier.getMaxUses()).group(CompendiumItems.GROUP_MATERIALS)));
    }

    public static void registerBlockModels(MetalAdvancedTools m, TCBlockModels model, String name) {

    }

    public static void registerItemModels(MetalAdvancedTools m, TCItemModels model, String name) {
	model.withExistingParent(m.HAMMER.getId().getPath(), model.mcLoc("item/handheld"))
		.texture("layer0", model.modLoc("item/material/" + name + "/" + m.HAMMER.getId().getPath()))
		.texture("layer1", model.modLoc("item/hammerbase"));
	model.withExistingParent(m.SAW.getId().getPath(), model.mcLoc("item/handheld"))
		.texture("layer0", model.modLoc("item/material/" + name + "/" + m.SAW.getId().getPath()))
		.texture("layer1", model.modLoc("item/sawbase"));
	model.withExistingParent(m.ZWEIHANDER.getId().getPath(), model.mcLoc("item/handheld")).transforms()
		.transform(Perspective.FIRSTPERSON_LEFT).scale(1.5f).rotation(90, 0, 0).translation(1, 0, 0).end()
		.transform(Perspective.FIRSTPERSON_RIGHT).scale(1.5f).rotation(90, 0, 0).translation(1, 0, 0).end()
		.transform(Perspective.THIRDPERSON_LEFT).scale(1.5f).rotation(0, 90, -45).translation(0, 8, 0).end()
		.transform(Perspective.THIRDPERSON_RIGHT).scale(1.5f).rotation(0, 90, 45).translation(0, 8, 0).end()
		.end().texture("layer0", model.modLoc("item/material/" + name + "/" + m.ZWEIHANDER.getId().getPath()))
		.texture("layer1", model.modLoc("item/zweihander_base"));

	model.withExistingParent(m.BOW.getId().getPath(), model.mcLoc("item/handheld"))
		.texture("layer1", model.modLoc("item/material/" + name + "/" + m.BOW.getId().getPath()))
		.texture("layer0", model.mcLoc("item/bow")).override().predicate(model.mcLoc("pulling"), 1)

		.model(model.withExistingParent(m.BOW.getId().getPath() + "_0", model.mcLoc("item/handheld"))
			.texture("layer1",
				model.modLoc("item/material/" + name + "/" + m.BOW.getId().getPath() + "_pulling_0"))
			.texture("layer0", model.mcLoc("item/bow_pulling_0")))
		.end()

		.override().predicate(model.mcLoc("pulling"), 1).predicate(model.mcLoc("pull"), 0.65f)
		.model(model.withExistingParent(m.BOW.getId().getPath() + "_1", model.mcLoc("item/handheld"))
			.texture("layer1",
				model.modLoc("item/material/" + name + "/" + m.BOW.getId().getPath() + "_pulling_1"))
			.texture("layer0", model.mcLoc("item/bow_pulling_1")))
		.end()

		.override().predicate(model.mcLoc("pulling"), 1).predicate(model.mcLoc("pull"), 0.9f)
		.model(model.withExistingParent(m.BOW.getId().getPath() + "_2", model.mcLoc("item/handheld"))
			.texture("layer1",
				model.modLoc("item/material/" + name + "/" + m.BOW.getId().getPath() + "_pulling_2"))
			.texture("layer0", model.mcLoc("item/bow_pulling_2")));
	
	model.withExistingParent(m.PLIERS.getId().getPath(), model.mcLoc("item/handheld"))
	.texture("layer0", model.modLoc("item/material/" + name + "/" + m.PLIERS.getId().getPath()))
	.texture("layer1", model.modLoc("item/pliersbase"));
	
	model.withExistingParent(m.PRYBAR.getId().getPath(), model.mcLoc("item/handheld"))
	.texture("layer0", model.modLoc("item/material/" + name + "/" + m.PRYBAR.getId().getPath()))
	.texture("layer1", model.modLoc("item/prybarbase"));
	
	model.withExistingParent(m.WRENCH.getId().getPath(), model.mcLoc("item/handheld"))
	.texture("layer0", model.modLoc("item/material/" + name + "/" + m.WRENCH.getId().getPath()))
	.texture("layer1", model.modLoc("item/wrenchbase"));
    }

    public static void addTranslations(MetalAdvancedTools m, TCEnglishLoc loc, String capName) {
	loc.add(m.BOW.get(), capName + " Bow");
	loc.add(m.HAMMER.get(), capName + " Hammer");
	loc.add(m.SAW.get(), capName + " Saw");
	loc.add(m.ZWEIHANDER.get(), capName + " Zweihander");
    }

    public static void build(MetalAdvancedTools m, BlockLoot table) {

    }

}
