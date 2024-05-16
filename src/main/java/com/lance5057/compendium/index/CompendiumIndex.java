package com.lance5057.compendium.index;

import java.util.HashSet;

import com.lance5057.compendium.Compendium;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CompendiumIndex {
	public static HashSet<IIndexEntry> index = new HashSet<IIndexEntry>();

	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Compendium.MOD_ID);
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Compendium.MOD_ID);
	
	public static void setup(IEventBus bus)
	{
		index.forEach(i -> i.setup());
		
		ITEMS.register(bus);
		BLOCKS.register(bus);
	}
}
