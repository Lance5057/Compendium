package com.lance5057.compendium;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.network.ChecksumVerificationPacket;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;

@EventBusSubscriber(modid = Compendium.MOD_ID, value = Dist.DEDICATED_SERVER)
public class CompendiumServerEvents {
	@SubscribeEvent
	public static void ServerChecksumEvent(OnDatapackSyncEvent event) {
		if (event.getPlayer() != null)
			try {
				event.getPlayer().connection
						.send(new ChecksumVerificationPacket(CompendiumIndex.generateChecksum().toString()));
			} catch (NoSuchAlgorithmException | IOException e) {
				e.printStackTrace();
			}
	}
}
