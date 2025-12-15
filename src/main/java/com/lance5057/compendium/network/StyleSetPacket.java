package com.lance5057.compendium.network;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.workstations.cosmetictoolbox.CosmeticToolboxMenu;
import com.lance5057.compendium.workstations.cosmetictoolbox.placed.CosmeticToolboxPlacedMenu;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record StyleSetPacket(int containerId, int section, int style) implements CustomPacketPayload {

	public static final Type<StyleSetPacket> id = new CustomPacketPayload.Type<StyleSetPacket>(
			ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "style_set_packet"));

	public StyleSetPacket(FriendlyByteBuf buf) {
		this(buf.readInt(), buf.readInt(), buf.readInt());
	}

	public static void handle(StyleSetPacket message, IPayloadContext ctx) {
		if (ctx.flow().isServerbound()) {
			ctx.enqueueWork(new Runnable() {

				@Override
				public void run() {
					if (ctx.player().containerMenu instanceof CosmeticToolboxMenu ammm) {
						ammm.setStyle(message.section, message.style);
					} else if (ctx.player().containerMenu instanceof CosmeticToolboxPlacedMenu ammm) {
						ammm.setStyle(message.section, message.style);
					}
				}

			});
		}
	}

	public static StreamCodec<ByteBuf, StyleSetPacket> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.INT,
			StyleSetPacket::containerId, ByteBufCodecs.INT, StyleSetPacket::section, ByteBufCodecs.INT,
			StyleSetPacket::style, StyleSetPacket::new);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return id;
	}
}
