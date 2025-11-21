package com.lance5057.compendium.network;

import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.index.CompendiumIndex;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record ChecksumVerificationPacket(String checksum) implements CustomPacketPayload {

	public static final Type<ChecksumVerificationPacket> id = new CustomPacketPayload.Type<ChecksumVerificationPacket>(
			ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "checksum_verification"));

	public ChecksumVerificationPacket(FriendlyByteBuf buf) {

		this(buf.readUtf());
	}

	public static StreamCodec<ByteBuf, ChecksumVerificationPacket> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.STRING_UTF8, ChecksumVerificationPacket::checksum, ChecksumVerificationPacket::new);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return id;
	}

	public static void handle(ChecksumVerificationPacket message, IPayloadContext ctx) {
		if (ctx.flow().isClientbound()) {
			ctx.enqueueWork(new Runnable() {

				@Override
				public void run() {
					BigInteger server = new BigInteger(message.checksum());
					
					try {
						BigInteger client = CompendiumIndex.generateChecksum();
						
						if(!server.equals(client))
							ctx.connection().disconnect(Component.translatable("compendium.network.checksum_failed"));
						else
							Compendium.LOGGER.info("Checksum Verified");
					} catch (NoSuchAlgorithmException e) {
						ctx.connection().disconnect(Component.translatable("compendium.network.checksum_failed_algorithm_exception"));
					} catch (IOException e) {
						ctx.connection().disconnect(Component.translatable("compendium.network.checksum_failed_io_exception"));
					}
				}
			});
		}
	}

}
