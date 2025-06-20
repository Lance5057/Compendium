package com.lance5057.compendium;

import com.lance5057.compendium.network.AdjustinatorCallBackPacket;
import com.lance5057.compendium.network.AdjustinatorPacket;
import com.lance5057.compendium.network.StyleSetPacket;
import com.lance5057.compendium.network.StyleSyncPacket;

import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class CompendiumNetworkHandler {
	public static void setupPackets(RegisterPayloadHandlersEvent event) {
		PayloadRegistrar registrar = event.registrar(Compendium.MOD_ID).versioned("1.0.0").optional();

		registrar.playToClient(StyleSyncPacket.id, StyleSyncPacket.STREAM_CODEC, StyleSyncPacket::handle);
		registrar.playToClient(AdjustinatorPacket.id, AdjustinatorPacket.STREAM_CODEC, AdjustinatorPacket::handle);

		registrar.playToServer(AdjustinatorCallBackPacket.id, AdjustinatorCallBackPacket.STREAM_CODEC,
				AdjustinatorCallBackPacket::handle);
		registrar.playToServer(StyleSetPacket.id, StyleSetPacket.STREAM_CODEC, StyleSetPacket::handle);
	}
}
