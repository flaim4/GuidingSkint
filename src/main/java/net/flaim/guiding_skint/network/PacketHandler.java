package net.flaim.guiding_skint.network;

import net.flaim.guiding_skint.GuidingSkintMod;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraft.resources.ResourceLocation;

public class PacketHandler {
    public static final String VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(GuidingSkintMod.MOD_ID, "main"),
            () -> VERSION, VERSION::equals, VERSION::equals
    );

    public static void register() {
        INSTANCE.messageBuilder(BlockStartAnimationS2C.class, 0, NetworkDirection.PLAY_TO_SERVER)
                .encoder(BlockStartAnimationS2C::encode)
                .decoder(BlockStartAnimationS2C::new)
                .consumerMainThread(BlockStartAnimationS2C::handle)
                .add();
    }

    public static void sendToAll(Object msg) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), msg);
    }

}