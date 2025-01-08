package com.example.examplemod.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GlimmerParticle extends TextureSheetParticle  {

    private final SpriteSet spriteSet;

    GlimmerParticle(ClientLevel level, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteSet spriteSet) {
        super(level, x, y, z, velocityX, velocityY, velocityZ);
        this.friction = 0.96f;
        this.spriteSet = spriteSet;
        this.quadSize *= 0.75f;
        this.hasPhysics = true;
        this.setSpriteFromAge(spriteSet);
    }

    @Override
    protected int getLightColor(float f) {
        return 15728880;
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.spriteSet);
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class NormalFactory
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public NormalFactory(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType particleOptions, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i) {
            GlimmerParticle glimmerParticle = new GlimmerParticle(clientLevel, d, e, f, 0.0, 0.0, 0.0, this.spriteSet);
            glimmerParticle.setColor(1.0f, 1.0f, 1.0f);
            glimmerParticle.setParticleSpeed(g * 0.01, h * 0.01, i * 0.01);
            glimmerParticle.setLifetime(clientLevel.random.nextInt(30) + 10);
            return glimmerParticle;
        }
    }

    public static class GhastFireballFactory
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public GhastFireballFactory(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType particleOptions, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i) {
            GlimmerParticle glimmerParticle = new GlimmerParticle(clientLevel, d, e, f, 0.0, 0.0, 0.0, this.spriteSet);
            glimmerParticle.setColor(1.0f, 1.0f, 1.0f);
            glimmerParticle.quadSize = 0.9f;
            glimmerParticle.setParticleSpeed(g * 0.01, h * 0.01, i * 0.01);
            glimmerParticle.setLifetime(clientLevel.random.nextInt(20));
            return glimmerParticle;
        }
    }

    public static class FireballFactory
            implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public FireballFactory(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType particleOptions, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i) {
            GlimmerParticle glimmerParticle = new GlimmerParticle(clientLevel, d, e, f, 0.0, 0.0, 0.0, this.spriteSet);
            glimmerParticle.setColor(1.0f, 1.0f, 1.0f);
            glimmerParticle.quadSize = 0.5f;
            glimmerParticle.setParticleSpeed(g * 0.01, h * 0.01, i * 0.01);
            glimmerParticle.setLifetime(clientLevel.random.nextInt(10));
            return glimmerParticle;
        }
    }

    public static class LongFactory
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public LongFactory(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType particleOptions, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i) {
            GlimmerParticle glimmerParticle = new GlimmerParticle(clientLevel, d, e, f, 0.0, 0.0, 0.0, this.spriteSet);
            glimmerParticle.setColor(1.0f, 1.0f, 1.0f);
            glimmerParticle.setParticleSpeed(g * 0.01, h * 0.01, i * 0.01);
            glimmerParticle.setLifetime(clientLevel.random.nextInt(30) + 30);
            return glimmerParticle;
        }
    }

    public static class ColoredFactory
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public ColoredFactory(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType particleOptions, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i) {
            GlimmerParticle glimmerParticle = new GlimmerParticle(clientLevel, d, e, f, 0.0, 0.0, 0.0, this.spriteSet);
            glimmerParticle.setColor((float)g, (float)h, (float)i);
            glimmerParticle.setLifetime(clientLevel.random.nextInt(30) + 30);
            return glimmerParticle;
        }
    }
    
}
