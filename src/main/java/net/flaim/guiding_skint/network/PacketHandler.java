package net.flaim.guiding_skint.network;

import net.flaim.guiding_skint.GuidingSkintMod;
import net.flaim.guiding_skint.client.widget.Scroll;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class PacketHandler {
    public static final String VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(GuidingSkintMod.MOD_ID, "main"),
            () -> VERSION, VERSION::equals, VERSION::equals
    );

    public static void register() {
        INSTANCE.messageBuilder(BlockStartAnimationS2C.class, 0, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(BlockStartAnimationS2C::encode)
                .decoder(BlockStartAnimationS2C::new)
                .consumerMainThread(BlockStartAnimationS2C::handle)
                .add();

        INSTANCE.messageBuilder(CompoundTag.class, 1, NetworkDirection.PLAY_TO_CLIENT)
                .encoder((msg, buffer) -> buffer.writeNbt(msg))
                .decoder(FriendlyByteBuf::readNbt)
                .consumerMainThread((msg, ctx) -> handleGuidingSkint(msg, ctx))
                .add();
    }

    private static void handleGuidingSkint(CompoundTag tag, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ListTag guidingSkintList = tag.getList("ActivatedGuidingSkint", 8);
            Scroll.handleGuidingSkint(guidingSkintList);
        });
        context.get().setPacketHandled(true);
    }

    public static void sendToAll(Object msg) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), msg);
    }

    public static void sendToPlayer(ServerPlayer player, ListTag listTag) {
        CompoundTag tag = new CompoundTag();
        tag.put("ActivatedGuidingSkint", listTag);
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), tag);
    }
}