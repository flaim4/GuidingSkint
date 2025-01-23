package net.flaim.guiding_skint.network;

import net.flaim.guiding_skint.GuidingSkintMod;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraft.resources.ResourceLocation;

public class PacketHandler {
    private static final String VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(GuidingSkintMod.MOD_ID, "main"),
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
