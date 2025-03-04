package com.lance5057.compendium;

import net.neoforged.neoforge.common.ModConfigSpec;

public class CompendiumConfig {
	public static ModConfigSpec spec;

	public static final String CATEGORY_DEBUG = "debug";
	public static ModConfigSpec.BooleanValue DEBUG;

	public static final String CATEGORY_TOOLS = "tools";
	public static ModConfigSpec.IntValue SAW_DISTANCE;

	static {
		ModConfigSpec.Builder Builder = new ModConfigSpec.Builder();

		Builder.comment("Debug").push(CATEGORY_DEBUG);
		DEBUG = Builder.comment("For Developers").define("debug", false);
		Builder.pop();

		Builder.comment("Tools").push(CATEGORY_TOOLS);
		SAW_DISTANCE = Builder.comment("How far should the saw search for logs?").defineInRange("sawDistance", 128, 4,
				Integer.MAX_VALUE);
		Builder.pop();

		spec = Builder.build();
	}
}
