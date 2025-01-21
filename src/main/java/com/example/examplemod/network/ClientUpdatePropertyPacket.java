package com.example.examplemod.network;

import com.example.examplemod.block.custom.GuidingSkintBlock;
import com.example.examplemod.block.entity.GuidingSkintBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.network.NetworkEvent;


import java.util.function.Supplier;

public class ClientUpdatePropertyPacket {
    private final BlockPos pos;

    public ClientUpdatePropertyPacket(BlockPos pos) {
        this.pos = pos;
    }

    public ClientUpdatePropertyPacket(FriendlyByteBuf buffer) {
        this.pos = buffer.readBlockPos();
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(pos);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            if (context.get().getDirection().getReceptionSide().isClient()) {
                handleClient();
            }
        });
        context.get().setPacketHandled(true);
    }

    @OnlyIn(Dist.CLIENT)
    private void handleClient() {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            Level level = Minecraft.getInstance().level;
            if (level != null && level.getBlockEntity(pos) instanceof GuidingSkintBlockEntity blockEntity) {
                blockEntity.playAnimation();
                level.setBlockAndUpdate(pos, level.getBlockState(pos).setValue(GuidingSkintBlock.ACTION, true));
            }
        }
    }
}
