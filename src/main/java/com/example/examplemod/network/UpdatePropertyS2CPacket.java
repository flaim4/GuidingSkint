package com.example.examplemod.network;

import com.example.examplemod.GS;
import com.example.examplemod.block.custom.GuidingSkintBlock;
import com.example.examplemod.block.entity.GuidingSkintBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;


import java.util.function.Supplier;

public class UpdatePropertyS2CPacket {
    private final BlockPos pos;

    public UpdatePropertyS2CPacket(BlockPos pos) {
        this.pos = pos;
    }

    public UpdatePropertyS2CPacket(FriendlyByteBuf buffer) {
        this.pos = buffer.readBlockPos();
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(pos);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            Level level = Minecraft.getInstance().level;
            if (level != null) {
                ((GuidingSkintBlockEntity) level.getBlockEntity(pos)).playAnimation();
                level.setBlockAndUpdate(pos, level.getBlockState(pos).setValue(GuidingSkintBlock.ACTION, true));
            }
        });
        context.get().setPacketHandled(true);
    }
}
