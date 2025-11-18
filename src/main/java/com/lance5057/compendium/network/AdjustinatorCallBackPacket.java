package com.lance5057.compendium.network;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.gui.AdjustinatorMultiMaterialMenu;

import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record AdjustinatorCallBackPacket(int index, String combined, BlockPos pos) implements CustomPacketPayload {

	public static StreamCodec<ByteBuf, AdjustinatorCallBackPacket> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.INT, AdjustinatorCallBackPacket::index, ByteBufCodecs.STRING_UTF8,
			AdjustinatorCallBackPacket::combined, BlockPos.STREAM_CODEC, AdjustinatorCallBackPacket::pos,
			AdjustinatorCallBackPacket::new);

	public static final Type<AdjustinatorCallBackPacket> id = new CustomPacketPayload.Type<AdjustinatorCallBackPacket>(
			ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "adjustinator_callback_packet"));

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return id;
	}

	public static void handle(AdjustinatorCallBackPacket message, IPayloadContext ctx) {
		if (ctx.flow().isServerbound()) {
			ctx.enqueueWork(new Runnable() {

				@Override
				public void run() {
					if (ctx.player().containerMenu instanceof AdjustinatorMultiMaterialMenu ammm) {
						ammm.pos = message.pos;
						ammm.syncBlockFromRemote(message.index, message.combined);
					}
				}

			});
		}
	}

}
