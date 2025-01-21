package com.example.examplemod.network;

import com.example.examplemod.GS;
import net.minecraft.resources.ResourceLocation;
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
        INSTANCE.messageBuilder(UpdatePropertyC2SPacket.class, 0, NetworkDirection.PLAY_TO_SERVER)
                .encoder(UpdatePropertyC2SPacket::encode)
                .decoder(UpdatePropertyC2SPacket::new)
                .consumerMainThread(UpdatePropertyC2SPacket::handle)
                .add();
    }

    public static void sendToServer(Object msg) {
        INSTANCE.send(PacketDistributor.SERVER.noArg(), msg);
    }
}
