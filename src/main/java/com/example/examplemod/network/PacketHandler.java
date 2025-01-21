package com.example.examplemod.network;

import com.example.examplemod.GS;
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
        INSTANCE.messageBuilder(ServerUpdatePropertyPacket.class, 0, NetworkDirection.PLAY_TO_SERVER)
                .encoder(ServerUpdatePropertyPacket::encode)
                .decoder(ServerUpdatePropertyPacket::new)
                .consumerMainThread(ServerUpdatePropertyPacket::handle)
                .add();

        INSTANCE.messageBuilder(ClientUpdatePropertyPacket.class, 1, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(ClientUpdatePropertyPacket::encode)
                .decoder(ClientUpdatePropertyPacket::new)
                .consumerMainThread(ClientUpdatePropertyPacket::handle)
                .add();
    }

    public static void sendToClient(Object msg, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), msg);
    }

    public static void sendToAllClients(Object msg) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), msg);
    }
}
