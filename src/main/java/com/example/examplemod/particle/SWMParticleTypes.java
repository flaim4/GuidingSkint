package com.example.examplemod.particle;

import com.example.examplemod.SWM;

import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SWMParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, SWM.MODID);

    public static void init(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
