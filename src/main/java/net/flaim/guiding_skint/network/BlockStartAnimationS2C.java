package net.flaim.guiding_skint.network;

import net.flaim.guiding_skint.block.GuidingSkintBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class BlockStartAnimationS2C {
    private final BlockPos pos;

    public BlockStartAnimationS2C(BlockPos pos) {
        this.pos = pos;
    }

    public BlockStartAnimationS2C(FriendlyByteBuf buffer) {
        this.pos = buffer.readBlockPos();
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(pos);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            Level level = Minecraft.getInstance().level;
            if (level != null && level.getBlockEntity(pos) instanceof GuidingSkintBlockEntity blockEntity) {
                blockEntity.DEPLOY_ANIM = GuidingSkintBlockEntity.TRANSFORMATION_ANIM;
            }
        });
        context.get().setPacketHandled(true);
    }
}
