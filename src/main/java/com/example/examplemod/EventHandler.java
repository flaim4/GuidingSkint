package com.example.examplemod;

import com.example.examplemod.block.SWMBlockEntityType;
import com.example.examplemod.block.SWMBlocks;
import com.example.examplemod.block.custom.GuidingSkintBlock;
import com.example.examplemod.block.custom.GuidingSkintBlockRenderer;
import com.example.examplemod.block.entity.GuidingSkintBlockEntity;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.HolderGetter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = SWM.MODID)
public class EventHandler {

    @Mod.EventBusSubscriber(modid = SWM.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            BlockEntityRenderers.register(SWMBlockEntityType.GUIDING_SKINT_BLOCK_ENTITY.get(), GuidingSkintBlockRenderer::new);
        }
    }

    @Mod.EventBusSubscriber(modid = SWM.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class CommonEvents {

        @SubscribeEvent
        public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
            if (event.getLevel().isClientSide()) {
                return;
            }

            BlockPos pos = event.getPos();
            BlockState blockState = event.getLevel().getBlockState(pos);
            Block guidingBlock = SWMBlocks.GUIDING_SKINT_BLOCK.get();

            if (blockState.getBlock() == guidingBlock) {
                boolean currentAction = blockState.getValue(GuidingSkintBlock.ACTION);
                BlockState newState = blockState.setValue(GuidingSkintBlock.ACTION, !currentAction);

                event.getLevel().setBlock(pos, newState, Block.UPDATE_ALL);
                event.getLevel().sendBlockUpdated(pos, blockState, newState, Block.UPDATE_ALL);

            }
        }
    }

}
