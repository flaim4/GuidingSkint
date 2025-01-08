package com.example.examplemod;

import com.example.examplemod.particle.SWMParticleTypes;
import com.example.examplemod.particle.custom.GlimmerParticle;

import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class SWMClient {

    public static void init() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(SWMClient::renderParticles);
    }

    public static void renderParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(SWMParticleTypes.WISP.get(), GlimmerParticle.LongFactory::new);
    }
}
