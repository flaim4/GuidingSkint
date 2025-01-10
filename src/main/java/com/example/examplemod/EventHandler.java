package com.example.examplemod;

import com.example.examplemod.block.SWMBlockEntityType;
import com.example.examplemod.block.custom.AnimatedBlockRenderer;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.api.distmarker.Dist;

@Mod.EventBusSubscriber(modid = SWM.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler {
    @Mod.EventBusSubscriber(value = Dist.DEDICATED_SERVER)
    public static class ServerEvents {

        @SubscribeEvent
        public void onServerStarting(ServerStartingEvent event) {
            SWM.LOGGER.info("HELLO from server starting");
        }
    }

    @Mod.EventBusSubscriber(modid = SWM.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            BlockEntityRenderers.register(SWMBlockEntityType.ANIMATED_BLOCK_ENTITY.get(), AnimatedBlockRenderer::new);
        }

        // @SubscribeEvent
        // public static void onBlockClicked(BlockEvent.EntityPlaceEvent event) {
        //     if (event.getWorld().isRemote()) {
        //         return;
        //     }

        //     BlockPos pos = event.getPos();
        //     BlockState state = event.getWorld().getBlockState(pos);

        //     // Проверяем, что это заражённый кристалл
        //     if (state.getBlock() == MyModBlocks.ZARAZHENNY_KRYSTAL) {
        //         playAnimation(pos);
        //     }
        //     System.out.println("Работает");
        // }
    }
}
