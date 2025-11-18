package com.lance5057.compendium.network;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.gui.AdjustinatorMultiMaterialScreen;
import com.lance5057.compendium.gui.AdjustinatorWorkstationScreen;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record AdjustinatorSyncPacket(int containerId, BlockPos pos) implements CustomPacketPayload {

	public static final Type<AdjustinatorSyncPacket> id = new CustomPacketPayload.Type<AdjustinatorSyncPacket>(
			ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "adjustinator_packet"));

	public AdjustinatorSyncPacket(FriendlyByteBuf buf) {
		this(buf.readInt(), buf.readBlockPos());
	}

	public static void handle(AdjustinatorSyncPacket message, IPayloadContext ctx) {
		if (ctx.flow().isClientbound()) {
			ctx.enqueueWork(new Runnable() {

				@Override
				public void run() {
					if (Minecraft.getInstance().screen != null)
						if (Minecraft.getInstance().screen instanceof AdjustinatorWorkstationScreen screen) {
							screen.setPos(message.pos());
						} else if (Minecraft.getInstance().screen instanceof AdjustinatorMultiMaterialScreen screen) {
							screen.setPos(message.pos());
						}
				}

			});
		}
	}

	public static StreamCodec<ByteBuf, AdjustinatorSyncPacket> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.INT,
			AdjustinatorSyncPacket::containerId, BlockPos.STREAM_CODEC, AdjustinatorSyncPacket::pos,
			AdjustinatorSyncPacket::new);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return id;
	}
}
