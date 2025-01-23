package net.flaim.guiding_skint.network;

import net.flaim.guiding_skint.block.GuidingSkintBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UpdatePropertyC2SPacket {
    private final BlockPos pos;

    public UpdatePropertyC2SPacket(BlockPos pos) {
        this.pos = pos;
    }

    public UpdatePropertyC2SPacket(FriendlyByteBuf buffer) {
        this.pos = buffer.readBlockPos();
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(pos);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ServerPlayer player = context.get().getSender();
            if (player == null) return;

            ServerLevel level = player.serverLevel();
            level.setBlockAndUpdate(pos, level.getBlockState(pos).setValue(GuidingSkintBlock.INFECTED, false));
        });
        context.get().setPacketHandled(true);
    }
}
