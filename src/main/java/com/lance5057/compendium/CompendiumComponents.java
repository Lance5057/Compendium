package com.lance5057.compendium;

import java.util.function.Supplier;

import com.lance5057.compendium.components.block.MultiMaterialBlockComponent;

import net.minecraft.core.component.DataComponentType;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CompendiumComponents {
	public static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister
			.createDataComponents(Compendium.MOD_ID);

	public static Supplier<DataComponentType<MultiMaterialBlockComponent>> MULTI_MATERIAL = COMPONENTS.registerComponentType(
			"multi_material_block", builder -> builder.persistent(MultiMaterialBlockComponent.CODEC)
					.networkSynchronized(MultiMaterialBlockComponent.STREAM_CODEC));
}
