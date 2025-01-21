package com.example.examplemod.network;

import com.example.examplemod.GS;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {
    private static final String VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(GS.MODID, "main"),
            () -> VERSION, VERSION::equals, VERSION::equals
    );

    public static void register() {
        INSTANCE.messageBuilder(UpdatePropertyS2CPacket.class, 0, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(UpdatePropertyS2CPacket::encode)
                .decoder(UpdatePropertyS2CPacket::new)
                .consumerMainThread(UpdatePropertyS2CPacket::handle)
                .add();
    }

    public static void sendToClient(Object msg, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), msg);
    }

    public static void sendToAllClients(Object msg) {
        Minecraft.getInstance().getChatListener().handleSystemMessage(Component.literal("ЕБАТЬ"), false);
        INSTANCE.send(PacketDistributor.ALL.noArg(), msg);
    }
}
