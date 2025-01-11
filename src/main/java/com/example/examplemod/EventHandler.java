package com.example.examplemod;

import com.example.examplemod.block.SWMBlockEntityType;
import com.example.examplemod.block.SWMBlocks;
import com.example.examplemod.block.custom.AnimatedBlockRenderer;
import com.example.examplemod.block.entity.AnimatedBlockEntity;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = SWM.MODID)
public class EventHandler {

    @Mod.EventBusSubscriber(modid = SWM.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            BlockEntityRenderers.register(SWMBlockEntityType.ANIMATED_BLOCK_ENTITY.get(), AnimatedBlockRenderer::new);
        }
    }

    @Mod.EventBusSubscriber(modid = SWM.MODID, value = Dist.CLIENT)
    public static class CommonEvents {

        @SubscribeEvent
        public static void onRightClickBlock(RightClickBlock event) {
            if (event.getEntity().level().isClientSide()) {
                BlockPos pos = event.getPos();
                BlockState blockState = event.getLevel().getBlockState(pos);
                Block guidingBlock = SWMBlocks.ANIMATED_BLOCK.get();

                if (blockState.getBlock() == guidingBlock) {
                    if (event.getLevel().getBlockEntity(pos) instanceof AnimatedBlockEntity animatedBlockEntity) {
                        animatedBlockEntity.playAnimation(); // Запускаем анимацию
                    }
                }
            }
        }
    }
}
