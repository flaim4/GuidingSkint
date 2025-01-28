package net.flaim.guiding_skint.particle;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;

public class WispParticleType extends ParticleType<WispParticleOptions> {
    public WispParticleType(boolean alwaysShow) {
        super(alwaysShow, WispParticleOptions.DESERIALIZER);
    }

    @Override
    public ParticleOptions.Deserializer<WispParticleOptions> getDeserializer() {
        return WispParticleOptions.DESERIALIZER;
    }

    @Override
    public Codec<WispParticleOptions> codec() {
        return WispParticleOptions.CODEC;
    }
}


