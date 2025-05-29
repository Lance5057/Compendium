//package com.lance5057.compendium.network;
//
//import com.lance5057.compendium.Compendium;
//import com.lance5057.compendium.styleblock.StyleType;
//import com.lance5057.compendium.workstations.cosmetictoolbox.CosmeticToolboxMenu;
//
//import io.netty.buffer.ByteBuf;
//import net.minecraft.network.codec.StreamCodec;
//import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
//import net.minecraft.resources.ResourceLocation;
//import net.neoforged.neoforge.network.handling.IPayloadContext;
//
//public record StyleCallBackPacket(StyleType style) implements CustomPacketPayload {
//
//	public static StreamCodec<ByteBuf, StyleCallBackPacket> STREAM_CODEC = StreamCodec.composite(
//			StyleType.STREAM_CODEC, StyleCallBackPacket::style, StyleCallBackPacket::new);
//
//	public static final Type<StyleCallBackPacket> id = new CustomPacketPayload.Type<StyleCallBackPacket>(
//			ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "style_callback_packet"));
//
//	@Override
//	public Type<? extends CustomPacketPayload> type() {
//		return id;
//	}
//
//	public static void handle(StyleCallBackPacket message, IPayloadContext ctx) {
//		if (ctx.flow().isServerbound()) {
//			ctx.enqueueWork(new Runnable() {
//
//				@Override
//				public void run() {
//					if (ctx.player().containerMenu instanceof CosmeticToolboxMenu ammm) {
//						ammm.
//					}
//				}
//
//			});
//		}
//	}
//}
