package com.example.examplemod;

import com.example.examplemod.block.GSBlockEntityType;
import com.example.examplemod.block.GSBlocks;
import com.example.examplemod.block.custom.GuidingSkintBlock;
import com.example.examplemod.block.custom.GuidingSkintBlockRenderer;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = GS.MODID)
public class EventHandler {

    @Mod.EventBusSubscriber(modid = GS.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            BlockEntityRenderers.register(GSBlockEntityType.GUIDING_SKINT_BLOCK_ENTITY.get(), GuidingSkintBlockRenderer::new);
        }
    }

    @Mod.EventBusSubscriber(modid = GS.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class CommonEvents {

        @SubscribeEvent
        public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
            if (event.getLevel().isClientSide()) {
                return;
            }

            BlockPos pos = event.getPos();
            BlockState blockState = event.getLevel().getBlockState(pos);
            Block guidingBlock = GSBlocks.GUIDING_SKINT_BLOCK.get();

            if (blockState.getBlock() == guidingBlock) {
                boolean currentAction = blockState.getValue(GuidingSkintBlock.ACTION);
                BlockState newState = blockState.setValue(GuidingSkintBlock.ACTION, !currentAction);

                event.getLevel().setBlock(pos, newState, Block.UPDATE_ALL);
                event.getLevel().sendBlockUpdated(pos, blockState, newState, Block.UPDATE_ALL);

            }
        }
    }

}
