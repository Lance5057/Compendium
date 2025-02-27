package com.lance5057.compendium.network;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.gui.AdjustinatorScreen;
import com.lance5057.compendium.workstations.cosmetictoolbox.CosmeticToolboxScreen;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record StyleSyncPacket(int containerId, BlockPos pos) implements CustomPacketPayload {
	public static final Type<StyleSyncPacket> id = new CustomPacketPayload.Type<StyleSyncPacket>(
			ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "style_packet"));

	public StyleSyncPacket(FriendlyByteBuf buf) {
		this(buf.readInt(), buf.readBlockPos());
	}

//	@Override
//	public void write(FriendlyByteBuf buf) {
//		buf.writeInt(containerId);
//		buf.writeBlockPos(pos);
//	}
//
	public static void handle(StyleSyncPacket message, IPayloadContext ctx) {
		if (ctx.flow().isClientbound()) {
			ctx.enqueueWork(new Runnable() {

				@Override
				public void run() {
					if (Minecraft.getInstance().screen != null)
						if (Minecraft.getInstance().screen instanceof CosmeticToolboxScreen screen) {
							screen.setPos(message.pos());
						}
						else if (Minecraft.getInstance().screen instanceof AdjustinatorScreen screen) {
							screen.setPos(message.pos()); 
							
						}
				}

			});
		}
	}

	public static StreamCodec<ByteBuf, StyleSyncPacket> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.INT,
			StyleSyncPacket::containerId, BlockPos.STREAM_CODEC, StyleSyncPacket::pos,
			StyleSyncPacket::new);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return id;
	}
}
