package com.example.examplemod;

import com.example.examplemod.worldgen.block.custom.AnimatedBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.api.distmarker.Dist;

@Mod.EventBusSubscriber(modid = ExampleMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler {
    @Mod.EventBusSubscriber(value = Dist.DEDICATED_SERVER)
    public static class ServerEvents {
        @SubscribeEvent
        public void onServerStarting(ServerStartingEvent event) {
            ExampleMod.LOGGER.info("HELLO from server starting");
        }
    }

    @Mod.EventBusSubscriber(modid = ExampleMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            BlockEntityRenderers.register(Registries.ANIMATED_BLOCK_ENTITY.get(), AnimatedBlockRenderer::new);
        }
    }
}
