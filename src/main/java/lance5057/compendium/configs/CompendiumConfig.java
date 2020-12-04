package lance5057.compendium.configs;

import java.nio.file.Path;
import java.util.Objects;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import net.minecraftforge.common.ForgeConfigSpec;

public class CompendiumConfig {
	private static CompendiumConfig instance;

	private final ForgeConfigSpec spec;

	public final Materials materials;
	public final WorldGen worldgen;
	public final General general;

	private CompendiumConfig(ForgeConfigSpec.Builder builder) {
		this.materials = new Materials(builder);
		this.worldgen = new WorldGen(builder);
		this.general = new General(builder);
		
		this.spec = builder.build();
	}

	public static ForgeConfigSpec initialize() {
		CompendiumConfig config = new CompendiumConfig(new ForgeConfigSpec.Builder());
		instance = config;
		return config.getSpec();
	}

	public static CompendiumConfig getInstance() {
		return Objects.requireNonNull(instance, "Called for Config before it's Initialization");
	}

	public static void loadConfig(ForgeConfigSpec spec, Path path) {
		final CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave()
				.writingMode(WritingMode.REPLACE).build();

		configData.load();
		spec.setConfig(configData);
	}

	public ForgeConfigSpec getSpec() {
		return spec;
	}
	
	public static class General {
		public final ForgeConfigSpec.ConfigValue<Boolean> debug;
		
		General(ForgeConfigSpec.Builder builder) {
			debug = builder.comment("Disable/Enable Developer Debugging.")
					.translation("compendium.config.common.general.debug").define("debug", false);
		}
	}

	public static class Materials {
		public final ForgeConfigSpec.ConfigValue<Boolean> aeonsteel;
		public final ForgeConfigSpec.ConfigValue<Boolean> queensgold;
		public final ForgeConfigSpec.ConfigValue<Boolean> dogbearium;
		public final ForgeConfigSpec.ConfigValue<Boolean> sinisterium;
		public final ForgeConfigSpec.ConfigValue<Boolean> nihilite;
		public final ForgeConfigSpec.ConfigValue<Boolean> orichalcum;
		public final ForgeConfigSpec.ConfigValue<Boolean> pandorium;
		public final ForgeConfigSpec.ConfigValue<Boolean> rosegold;
		public final ForgeConfigSpec.ConfigValue<Boolean> platinum;
		public final ForgeConfigSpec.ConfigValue<Boolean> silver;

		public final ForgeConfigSpec.ConfigValue<Boolean> valyriansteel;
		public final ForgeConfigSpec.ConfigValue<Boolean> ice;
		public final ForgeConfigSpec.ConfigValue<Boolean> froststeel;

		// Advanced Materials
		public final ForgeConfigSpec.ConfigValue<Boolean> radiantGold;
		public final ForgeConfigSpec.ConfigValue<Boolean> hallowedSilver;
		public final ForgeConfigSpec.ConfigValue<Boolean> energeticElectrum;

		// Wanderlust Materials
		public final ForgeConfigSpec.ConfigValue<Boolean> mithril;

		// Cornucopia Materials
		public final ForgeConfigSpec.ConfigValue<Boolean> gallite;
		public final ForgeConfigSpec.ConfigValue<Boolean> sundrop;
		public final ForgeConfigSpec.ConfigValue<Boolean> voidite;
		public final ForgeConfigSpec.ConfigValue<Boolean> solarium;
		public final ForgeConfigSpec.ConfigValue<Boolean> dragonsteel;
		public final ForgeConfigSpec.ConfigValue<Boolean> blacksteel;
		public final ForgeConfigSpec.ConfigValue<Boolean> abyssalium;
		public final ForgeConfigSpec.ConfigValue<Boolean> depthsilver;
		public final ForgeConfigSpec.ConfigValue<Boolean> moonsilver;
		public final ForgeConfigSpec.ConfigValue<Boolean> novagold;

		public final ForgeConfigSpec.ConfigValue<Boolean> iron;
		public final ForgeConfigSpec.ConfigValue<Boolean> gold;
		public final ForgeConfigSpec.ConfigValue<Boolean> emerald;
		public final ForgeConfigSpec.ConfigValue<Boolean> diamond;

		public final ForgeConfigSpec.ConfigValue<Boolean> sapphire;
		public final ForgeConfigSpec.ConfigValue<Boolean> ruby;
		public final ForgeConfigSpec.ConfigValue<Boolean> starsapphire;
		public final ForgeConfigSpec.ConfigValue<Boolean> starruby;
		public final ForgeConfigSpec.ConfigValue<Boolean> citrine;
		public final ForgeConfigSpec.ConfigValue<Boolean> quartz;
		public final ForgeConfigSpec.ConfigValue<Boolean> amethyst;
		public final ForgeConfigSpec.ConfigValue<Boolean> lapis;
		public final ForgeConfigSpec.ConfigValue<Boolean> topaz;
		public final ForgeConfigSpec.ConfigValue<Boolean> garnet;
		public final ForgeConfigSpec.ConfigValue<Boolean> opal;
		public final ForgeConfigSpec.ConfigValue<Boolean> tanzinite;
		public final ForgeConfigSpec.ConfigValue<Boolean> amber;

		Materials(ForgeConfigSpec.Builder builder) {
			aeonsteel = builder.comment("Disable/Enable Aeonsteel as a material.")
					.translation("compendium.config.common.material.aeonsteel").define("aeonsteel", true);

			queensgold = builder.comment("Disable/Enable Queen's Gold as a material.")
					.translation("compendium.config.common.material.queensgold").define("queensgold", true);
			dogbearium = builder.comment("Disable/Enable Dogbearium as a material.")
					.translation("compendium.config.common.material.dogbearium").define("dogbearium", true);
			sinisterium = builder.comment("Disable/Enable Sinisterium as a material.")
					.translation("compendium.config.common.material.sinisterium").define("sinisterium", true);
			nihilite = builder.comment("Disable/Enable Nihilite as a material.")
					.translation("compendium.config.common.material.nihilite").define("nihilite", true);
			orichalcum = builder.comment("Disable/Enable Orichalcum as a material.")
					.translation("compendium.config.common.material.orichalcum").define("orichalcum", true);
			pandorium = builder.comment("Disable/Enable Pandorium as a material.")
					.translation("compendium.config.common.material.pandorium").define("pandorium", true);
			rosegold = builder.comment("Disable/Enable Rosegold as a material.")
					.translation("compendium.config.common.material.rosegold").define("rosegold", true);
			platinum = builder.comment("Disable/Enable Platinum as a material.")
					.translation("compendium.config.common.material.platinum").define("platinum", true);
			silver = builder.comment("Disable/Enable Silver as a material.")
					.translation("compendium.config.common.material.silver").define("silver", true);

			valyriansteel = builder.comment("Disable/Enable Valyrian Steel as a material.")
					.translation("compendium.config.common.material.valyriansteel").define("valyriansteel", true);
			ice = builder.comment("Disable/Enable Ice as a material.")
					.translation("compendium.config.common.material.ice").define("ice", true);
			froststeel = builder.comment("Disable/Enable Frost Steel as a material.")
					.translation("compendium.config.common.material.froststeel").define("froststeel", true);

			// Advanced Materials
			radiantGold = builder.comment("Disable/Enable Radiant Gold as a material.")
					.translation("compendium.config.common.material.radiantGold").define("radiantGold", true);
			hallowedSilver = builder.comment("Disable/Enable Hallowed Silver as a material.")
					.translation("compendium.config.common.material.hallowedSilver").define("hallowedSilver", true);
			energeticElectrum = builder.comment("Disable/Enable Energetic Electrum as a material.")
					.translation("compendium.config.common.material.energeticElectrum")
					.define("energeticElectrum", true);

			// Wanderlust Materials
			mithril = builder.comment("Disable/Enable Mithril as a material.")
					.translation("compendium.config.common.material.mithril").define("mithril", true);

			// Cornucopia Materials
			gallite = builder.comment("Disable/Enable Gallite as a material.")
					.translation("compendium.config.common.material.gallite").define("gallite", true);
			sundrop = builder.comment("Disable/Enable Sundrop as a material.")
					.translation("compendium.config.common.material.sundrop").define("sundrop", true);
			voidite = builder.comment("Disable/Enable Voidite as a material.")
					.translation("compendium.config.common.material.voidite").define("voidite", true);
			solarium = builder.comment("Disable/Enable Solarium as a material.")
					.translation("compendium.config.common.material.solarium").define("solarium", true);
			dragonsteel = builder.comment("Disable/Enable Dragon Steel as a material.")
					.translation("compendium.config.common.material.dragonsteel").define("dragonsteel", true);
			blacksteel = builder.comment("Disable/Enable Black Steel as a material.")
					.translation("compendium.config.common.material.blacksteel").define("blacksteel", true);
			abyssalium = builder.comment("Disable/Enable Abyssalium as a material.")
					.translation("compendium.config.common.material.abyssalium").define("abyssalium", true);
			depthsilver = builder.comment("Disable/Enable Depth Silver as a material.")
					.translation("compendium.config.common.material.depthsilver").define("depthsilver", true);
			moonsilver = builder.comment("Disable/Enable Moon Silver as a material.")
					.translation("compendium.config.common.material.moonsilver").define("moonsilver", true);
			novagold = builder.comment("Disable/Enable Nova Gold as a material.")
					.translation("compendium.config.common.material.novagold").define("novagold", true);

			iron = builder.comment("Disable/Enable Iron as a material.")
					.translation("compendium.config.common.material.iron").define("iron", true);
			gold = builder.comment("Disable/Enable Gold as a material.")
					.translation("compendium.config.common.material.gold").define("gold", true);
			emerald = builder.comment("Disable/Enable Emerald as a material.")
					.translation("compendium.config.common.material.emerald").define("emerald", true);
			diamond = builder.comment("Disable/Enable Diamond as a material.")
					.translation("compendium.config.common.material.diamond").define("diamond", true);
			quartz = builder.comment("Disable/Enable Quartz as a material.")
					.translation("compendium.config.common.material.quartz").define("quartz", true);
			lapis = builder.comment("Disable/Enable Lapis as a material.")
					.translation("compendium.config.common.material.lapis").define("lapis", true);

			sapphire = builder.comment("Disable/Enable Sapphire as a material.")
					.translation("compendium.config.common.material.sapphire").define("sapphire", true);
			ruby = builder.comment("Disable/Enable Ruby as a material.")
					.translation("compendium.config.common.material.ruby").define("ruby", true);
			starsapphire = builder.comment("Disable/Enable Star Sapphire as a material.")
					.translation("compendium.config.common.material.starsapphire").define("starsapphire", true);
			starruby = builder.comment("Disable/Enable Star Ruby as a material.")
					.translation("compendium.config.common.material.starruby").define("starruby", true);
			citrine = builder.comment("Disable/Enable Citrine as a material.")
					.translation("compendium.config.common.material.citrine").define("citrine", true);
			amethyst = builder.comment("Disable/Enable Amethyst as a material.")
					.translation("compendium.config.common.material.amethyst").define("amethyst", true);
			topaz = builder.comment("Disable/Enable Topaz as a material.")
					.translation("compendium.config.common.material.topaz").define("topaz", true);
			garnet = builder.comment("Disable/Enable Garnet as a material.")
					.translation("compendium.config.common.material.garnet").define("garnet", true);
			opal = builder.comment("Disable/Enable Opal as a material.")
					.translation("compendium.config.common.material.opal").define("opal", true);
			tanzinite = builder.comment("Disable/Enable Tanzinite as a material.")
					.translation("compendium.config.common.material.tanzinite").define("tanzinite", true);
			amber = builder.comment("Disable/Enable Amber as a material.")
					.translation("compendium.config.common.material.amber").define("amber", true);
		}
	}

	public static class WorldGen {
		public final ForgeConfigSpec.ConfigValue<Boolean> enableRetroGen;
		public final ForgeConfigSpec.ConfigValue<String> retroGenName;

		public final ForgeConfigSpec.ConfigValue<Boolean> genOreGeneral;

		public final ForgeConfigSpec.ConfigValue<Boolean> genOreSilver;
		public final ForgeConfigSpec.ConfigValue<Boolean> genOrePlatinum;
		public final ForgeConfigSpec.ConfigValue<Boolean> genOreMithril;

		public final ForgeConfigSpec.ConfigValue<Boolean> genOreSapphire;
		public final ForgeConfigSpec.ConfigValue<Boolean> genOreRuby;
		public final ForgeConfigSpec.ConfigValue<Boolean> genOreStarsapphire;
		public final ForgeConfigSpec.ConfigValue<Boolean> genOreStarruby;
		public final ForgeConfigSpec.ConfigValue<Boolean> genOreCitrine;
		public final ForgeConfigSpec.ConfigValue<Boolean> genOreAmethyst;
		public final ForgeConfigSpec.ConfigValue<Boolean> genOreTopaz;
		public final ForgeConfigSpec.ConfigValue<Boolean> genOreGarnet;
		public final ForgeConfigSpec.ConfigValue<Boolean> genOreOpal;
		public final ForgeConfigSpec.ConfigValue<Boolean> genOreTanzinite;
		public final ForgeConfigSpec.ConfigValue<Boolean> genOreAmber;

		WorldGen(ForgeConfigSpec.Builder builder) {
			enableRetroGen = builder.comment("Enable RetroGen of ores")
					.translation("compendium.config.common.worldgen.enableretrogen").define("enableRetroGen", false);
			retroGenName = builder.comment("The name used by ore generation to determine if a chunk has been populated. Changing this will mark all chunks for retrogen if enabled.")
					.translation("compendium.config.common.worldgen.retrogenname").define("retroGenName", "Generated");
			
			genOreGeneral = builder.comment("Disable/Enable All Ores")
					.translation("compendium.config.common.worldgen.enableore").define("genOreGeneral", true);

			genOreSilver = builder.comment("Disable/Enable Silver Ore")
					.translation("compendium.config.common.worldgen.enablesilverore").define("genOreSilver", true);
			genOrePlatinum = builder.comment("Disable/Enable Platinum Ore")
					.translation("compendium.config.common.worldgen.enableplatinumore").define("genOrePlatinum", true);
			genOreMithril = builder.comment("Disable/Enable Mithril Ore")
					.translation("compendium.config.common.worldgen.enablemithrilore").define("genOreMithril", true);

			genOreSapphire = builder.comment("Disable/Enable Sapphire Ore")
					.translation("compendium.config.common.worldgen.enablesapphireore").define("genOreSapphire", true);
			genOreRuby = builder.comment("Disable/Enable Ruby Ore")
					.translation("compendium.config.common.worldgen.enablerubyore").define("genOreRuby", true);
			genOreStarsapphire = builder.comment("Disable/Enable Star Sapphire Ore")
					.translation("compendium.config.common.worldgen.enablestarsapphireore")
					.define("genOreStarsapphire", true);
			genOreStarruby = builder.comment("Disable/Enable Star Ruby Ore")
					.translation("compendium.config.common.worldgen.enablestarrubyore").define("genOreStarruby", true);
			genOreCitrine = builder.comment("Disable/Enable Citrine Ore")
					.translation("compendium.config.common.worldgen.enablecitrineore").define("genOreCitrine", true);
			genOreAmethyst = builder.comment("Disable/Enable Amethyst Ore")
					.translation("compendium.config.common.worldgen.enableamethystore").define("genOreAmethyst", true);
			genOreTopaz = builder.comment("Disable/Enable Topaz Ore")
					.translation("compendium.config.common.worldgen.enabletopazore").define("genOreTopaz", true);
			genOreGarnet = builder.comment("Disable/Enable Garnet Ore")
					.translation("compendium.config.common.worldgen.enablegarnetore").define("genOreGarnet", true);
			genOreOpal = builder.comment("Disable/Enable Opal Ore")
					.translation("compendium.config.common.worldgen.enableopalore").define("genOreOpal", true);
			genOreTanzinite = builder.comment("Disable/Enable Tanzinite Ore")
					.translation("compendium.config.common.worldgen.enabletanziniteore")
					.define("genOreTanzinite", true);
			genOreAmber = builder.comment("Disable/Enable Amber Ore")
					.translation("compendium.config.common.worldgen.enableamberore").define("genOreAmber", true);
		}
	}
}
