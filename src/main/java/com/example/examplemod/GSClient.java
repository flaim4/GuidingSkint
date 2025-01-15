package com.example.examplemod;

import com.example.examplemod.particle.GSParticleTypes;
import com.example.examplemod.particle.custom.GlimmerParticle;

import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class GSClient {

    public static void init() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(GSClient::renderParticles);
    }

    public static void renderParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(GSParticleTypes.WISP.get(), GlimmerParticle.LongFactory::new);
    }
}
