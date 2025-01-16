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

}
