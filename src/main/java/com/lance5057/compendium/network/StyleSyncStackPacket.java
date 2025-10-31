//package com.lance5057.compendium.network;
//
//import com.lance5057.compendium.Compendium;
//import com.lance5057.compendium.workstations.cosmetictoolbox.CosmeticToolboxScreen;
//
//import net.minecraft.client.Minecraft;
//import io.netty.buffer.ByteBuf;
//import net.minecraft.network.codec.ByteBufCodecs;
//import net.minecraft.network.codec.StreamCodec;
//import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.ItemStack;
//import net.neoforged.neoforge.network.handling.IPayloadContext;
//
//public record StyleSyncStackPacket(int containerId, ItemStack stack) implements CustomPacketPayload {
//
//	public static final Type<StyleSyncStackPacket> id = new CustomPacketPayload.Type<StyleSyncStackPacket>(
//			ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "style_stack_packet"));
//
//	public static void handle(StyleSyncStackPacket message, IPayloadContext ctx) {
//		if (ctx.flow().isClientbound()) {
//			ctx.enqueueWork(new Runnable() {
//
//				@Override
//				public void run() {
//					if (Minecraft.getInstance().screen != null)
//						if (Minecraft.getInstance().screen instanceof CosmeticToolboxScreen screen) {
//							screen.getMenu().slots.get(0).set(message.stack());
//
//						}
//				}
//
//			});
//		}
//	}
//
//	public static StreamCodec<ByteBuf, StyleSyncStackPacket> STREAM_CODEC = StreamCodec.composite(
//			ByteBufCodecs.INT, StyleSyncStackPacket::containerId, ItemStack.code, StyleSyncStackPacket::stack,
//			StyleSyncStackPacket::new);
//
//	@Override
//	public Type<? extends CustomPacketPayload> type() {
//		return id;
//	}
//}
