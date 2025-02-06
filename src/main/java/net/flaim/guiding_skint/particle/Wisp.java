package net.flaim.guiding_skint.particle;

import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Wisp extends TextureSheetParticle {
    private final SpriteSet spriteSet;
    private final float initialSize;

    Wisp(ClientLevel level, double x, double y, double z, double velocityX, double velocityY, double velocityZ,
         SpriteSet spriteSet, float size, WispParticleOptions options) {
        super(level, x, y, z, velocityX, velocityY, velocityZ);
        this.friction = 1f;
        this.spriteSet = spriteSet;
        this.quadSize *= size;
        this.initialSize = this.quadSize;
        this.hasPhysics = true;
        this.setSpriteFromAge(spriteSet);

        this.setColor(options.red(), options.green(), options.blue());
    }

    @Override
    protected int getLightColor(float f) {
        return 15728880;
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.spriteSet);

        float lifeProgress = (float) this.age / this.lifetime;
        this.quadSize = initialSize * (1.0f - lifeProgress);

        this.alpha = 1.0f - lifeProgress;
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class NormalFactory implements ParticleProvider<WispParticleOptions> {

        private final SpriteSet spriteSet;

        public NormalFactory(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(@NotNull WispParticleOptions particleOptions, @NotNull ClientLevel clientLevel, double d, double e, double f, double g, double h, double i) {
            Wisp wispParticle = new Wisp(clientLevel, d, e, f, g * 0.01, h * 0.01, i * 0.01, this.spriteSet, particleOptions.size(), particleOptions);
            wispParticle.setLifetime((int) particleOptions.setLifetime());
            wispParticle.setParticleSpeed(g * 0.01, h * 0.01, i * 0.01);
            return wispParticle;
        }
    }


}


