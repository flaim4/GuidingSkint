package net.flaim.guiding_skint.particle;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import org.jetbrains.annotations.NotNull;

public class WispParticleType extends ParticleType<WispParticleOptions> {
    public WispParticleType(boolean alwaysShow) {
        super(alwaysShow, WispParticleOptions.DESERIALIZER);
    }

    @Override
    public ParticleOptions.@NotNull Deserializer<WispParticleOptions> getDeserializer() {
        return WispParticleOptions.DESERIALIZER;
    }

    @Override
    public @NotNull Codec<WispParticleOptions> codec() {
        return WispParticleOptions.CODEC;
    }
}


