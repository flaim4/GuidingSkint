package com.example.examplemod;

import com.example.examplemod.worldgen.blocks.custom.AnimatedBlockRenderer;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Random;

import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

@Mod(ExampleMod.MODID)
public class ExampleMod {
    public static final String MODID = "examplemod";
    public static final Random random = new Random();
    public static final Logger LOGGER = LogUtils.getLogger();


    public ExampleMod(FMLJavaModLoadingContext context) {
        GeckoLib.initialize();
        IEventBus modEventBus = context.getModEventBus();
        Registries.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(new EventHandler());

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            BlockEntityRenderers.register(Registries.ANIMATED_BLOCK_ENTITY.get(), AnimatedBlockRenderer::new);
        }
    }

}
