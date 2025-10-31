package com.lance5057.compendium;

import java.util.function.Supplier;

import com.lance5057.compendium.components.block.MultiMaterialBlockComponent;
import com.lance5057.compendium.components.block.StyleBlockComponent;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CompendiumComponents {
	public static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister
			.createDataComponents(Registries.DATA_COMPONENT_TYPE, Compendium.MOD_ID);

	public static Supplier<DataComponentType<MultiMaterialBlockComponent>> MULTI_MATERIAL = COMPONENTS
			.register("multi_material_block",
					() -> DataComponentType.<MultiMaterialBlockComponent>builder()
							.persistent(MultiMaterialBlockComponent.CODEC)
							.networkSynchronized(MultiMaterialBlockComponent.STREAM_CODEC).cacheEncoding().build());

	public static Supplier<DataComponentType<StyleBlockComponent>> STYLE = COMPONENTS
			.registerComponentType("style_block", builder -> builder.persistent(StyleBlockComponent.CODEC)
					.networkSynchronized(StyleBlockComponent.STREAM_CODEC));
}
