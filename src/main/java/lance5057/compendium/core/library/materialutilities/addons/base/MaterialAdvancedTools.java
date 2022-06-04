package lance5057.compendium.core.library.materialutilities.addons.base;

import java.util.function.Consumer;

import javax.annotation.Nullable;

import lance5057.compendium.CompendiumItems;
import lance5057.compendium.core.data.builders.TCBlockModels;
import lance5057.compendium.core.data.builders.TCBlockTags;
import lance5057.compendium.core.data.builders.TCEnglishLoc;
import lance5057.compendium.core.data.builders.TCItemModels;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.data.builders.TCRecipes;
import lance5057.compendium.core.items.tools.HammerItem;
import lance5057.compendium.core.items.tools.PrybarItem;
import lance5057.compendium.core.items.tools.SawItem;
import lance5057.compendium.core.items.weapons.ZweihanderItem;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;
import net.minecraftforge.client.model.generators.ModelBuilder.Perspective;
import net.minecraftforge.registries.RegistryObject;

public class MaterialAdvancedTools extends MaterialBase {
	public RegistryObject<BowItem> BOW;
	public RegistryObject<ZweihanderItem> ZWEIHANDER;
//	public Item crossbow;
//	public Item shield;
	public RegistryObject<HammerItem> HAMMER;
	public RegistryObject<SawItem> SAW;
	public RegistryObject<ShearsItem> SHEARS;
	public RegistryObject<PrybarItem> PRYBAR;
	public RegistryObject<Item> PLIERS;
	public RegistryObject<Item> WRENCH;

//	public Item fishingrod;
//	public Item trident;
//	public Item sickle;
	@Override
	public void setup(MaterialHelper helper) {
		BOW = CompendiumItems.ITEMS.register(helper.name + "bow",
				() -> new BowItem(new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));
		HAMMER = CompendiumItems.ITEMS.register(helper.name + "hammer", () -> new HammerItem(helper.tier, 2, -3.4f,
				new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));
		SAW = CompendiumItems.ITEMS.register(helper.name + "saw",
				() -> new SawItem(helper.tier, 0, -3.0f, new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));
		ZWEIHANDER = CompendiumItems.ITEMS.register(helper.name + "zweihander", () -> new ZweihanderItem(helper.tier, 5,
				-2.6F, new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));

		SHEARS = CompendiumItems.ITEMS.register(helper.name + "shears", () -> new ShearsItem(
				new Item.Properties().durability(helper.tier.getUses()).tab(CompendiumItems.GROUP_MATERIALS)));
		PRYBAR = CompendiumItems.ITEMS.register(helper.name + "prybar", () -> new PrybarItem(
				new Item.Properties().durability(helper.tier.getUses()).tab(CompendiumItems.GROUP_MATERIALS)));
		PLIERS = CompendiumItems.ITEMS.register(helper.name + "pliers", () -> new Item(
				new Item.Properties().durability(helper.tier.getUses()).tab(CompendiumItems.GROUP_MATERIALS)));
		WRENCH = CompendiumItems.ITEMS.register(helper.name + "wrench", () -> new Item(
				new Item.Properties().durability(helper.tier.getUses()).tab(CompendiumItems.GROUP_MATERIALS)));
	}

	@Override
	public void setupClient(MaterialHelper helper) {
		ItemProperties.register(this.BOW.get(), new ResourceLocation("pull"),
				(p_174635_, p_174636_, p_174637_, p_174638_) -> {
					if (p_174637_ == null) {
						return 0.0F;
					} else {
						return p_174637_.getUseItem() != p_174635_ ? 0.0F
								: (float) (p_174635_.getUseDuration() - p_174637_.getUseItemRemainingTicks()) / 20.0F;
					}
				});

		ItemProperties.register(this.BOW.get(), new ResourceLocation("pulling"),
				(p_174630_, p_174631_, p_174632_, p_174633_) -> {
					return p_174632_ != null && p_174632_.isUsingItem() && p_174632_.getUseItem() == p_174630_ ? 1.0F
							: 0.0F;
				});
	}

	@Override
	public void registerBlockModels(TCBlockModels model, String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerItemModels(TCItemModels model, String name) {
		model.withExistingParent(HAMMER.getId().getPath(), model.mcLoc("item/handheld"))
				.texture("layer0", model.modLoc("item/material/" + name + "/" + HAMMER.getId().getPath()))
				.texture("layer1", model.modLoc("item/hammerbase"));
		model.withExistingParent(SAW.getId().getPath(), model.mcLoc("item/handheld"))
				.texture("layer0", model.modLoc("item/material/" + name + "/" + SAW.getId().getPath()))
				.texture("layer1", model.modLoc("item/sawbase"));
		model.withExistingParent(ZWEIHANDER.getId().getPath(), model.mcLoc("item/handheld")).transforms()
				.transform(Perspective.FIRSTPERSON_LEFT).scale(1.5f).rotation(90, 0, 0).translation(1, 0, 0).end()
				.transform(Perspective.FIRSTPERSON_RIGHT).scale(1.5f).rotation(90, 0, 0).translation(1, 0, 0).end()
				.transform(Perspective.THIRDPERSON_LEFT).scale(1.5f).rotation(0, 90, -45).translation(0, 8, 0).end()
				.transform(Perspective.THIRDPERSON_RIGHT).scale(1.5f).rotation(0, 90, 45).translation(0, 8, 0).end()
				.end().texture("layer0", model.modLoc("item/material/" + name + "/" + ZWEIHANDER.getId().getPath()))
				.texture("layer1", model.modLoc("item/zweihander_base"));

		model.withExistingParent(BOW.getId().getPath(), model.mcLoc("item/handheld"))
				.texture("layer1", model.modLoc("item/material/" + name + "/" + BOW.getId().getPath()))
				.texture("layer0", model.mcLoc("item/bow")).override().predicate(model.mcLoc("pulling"), 1)

				.model(model.withExistingParent(BOW.getId().getPath() + "_0", model.mcLoc("item/handheld"))
						.texture("layer1",
								model.modLoc("item/material/" + name + "/" + BOW.getId().getPath() + "_pulling_0"))
						.texture("layer0", model.mcLoc("item/bow_pulling_0")))
				.end()

				.override().predicate(model.mcLoc("pulling"), 1).predicate(model.mcLoc("pull"), 0.65f)
				.model(model.withExistingParent(BOW.getId().getPath() + "_1", model.mcLoc("item/handheld"))
						.texture("layer1",
								model.modLoc("item/material/" + name + "/" + BOW.getId().getPath() + "_pulling_1"))
						.texture("layer0", model.mcLoc("item/bow_pulling_1")))
				.end()

				.override().predicate(model.mcLoc("pulling"), 1).predicate(model.mcLoc("pull"), 0.9f)
				.model(model.withExistingParent(BOW.getId().getPath() + "_2", model.mcLoc("item/handheld"))
						.texture("layer1",
								model.modLoc("item/material/" + name + "/" + BOW.getId().getPath() + "_pulling_2"))
						.texture("layer0", model.mcLoc("item/bow_pulling_2")));

		model.withExistingParent(PLIERS.getId().getPath(), model.mcLoc("item/handheld"))
				.texture("layer0", model.modLoc("item/material/" + name + "/" + PLIERS.getId().getPath()))
				.texture("layer1", model.modLoc("item/pliersbase"));

		model.withExistingParent(PRYBAR.getId().getPath(), model.mcLoc("item/handheld"))
				.texture("layer0", model.modLoc("item/material/" + name + "/" + PRYBAR.getId().getPath()))
				.texture("layer1", model.modLoc("item/prybarbase"));

		model.withExistingParent(WRENCH.getId().getPath(), model.mcLoc("item/handheld"))
				.texture("layer0", model.modLoc("item/material/" + name + "/" + WRENCH.getId().getPath()))
				.texture("layer1", model.modLoc("item/wrenchbase"));

		model.withExistingParent(SHEARS.getId().getPath(), model.mcLoc("item/handheld"))
				.texture("layer0", model.modLoc("item/material/" + name + "/" + SHEARS.getId().getPath()))
				.texture("layer1", model.modLoc("item/shearsbase"));
	}

	@Override
	public void addTranslations(TCEnglishLoc loc, String capName) {
		loc.add(BOW.get(), capName + " Bow");
		loc.add(HAMMER.get(), capName + " Hammer");
		loc.add(SAW.get(), capName + " Saw");
		loc.add(ZWEIHANDER.get(), capName + " Zweihander");
		loc.add(SHEARS.get(), capName + " Shears");
		loc.add(PRYBAR.get(), capName + " Prybar");
		loc.add(PLIERS.get(), capName + " Wrench");
	}

	@Override
	public void registerItemTags(TCItemTags itp, String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerBlockTags(TCBlockTags tags, String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildLootTable(BlockLoot table, String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildRecipes(TCRecipes recipes, Consumer<FinishedRecipe> consumer, String name) {
		// TODO Auto-generated method stub

	}

}
