package net.flaim.guiding_skint.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.flaim.guiding_skint.Registries;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;

public class WispParticleOptions implements ParticleOptions {

    public static final Codec<WispParticleOptions> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(
                Codec.FLOAT.fieldOf("red").forGetter(WispParticleOptions::getRed),
                Codec.FLOAT.fieldOf("green").forGetter(WispParticleOptions::getGreen),
                Codec.FLOAT.fieldOf("blue").forGetter(WispParticleOptions::getBlue),
                Codec.FLOAT.fieldOf("size").forGetter(WispParticleOptions::getSize),
                Codec.FLOAT.fieldOf("setLifetime").forGetter(WispParticleOptions::getSetLifetime)
        ).apply(instance, WispParticleOptions::new);
    });

    public static final ParticleOptions.Deserializer<WispParticleOptions> DESERIALIZER = new ParticleOptions.Deserializer<WispParticleOptions>() {
        @Override
        public WispParticleOptions fromCommand(ParticleType<WispParticleOptions> particleType, StringReader reader) throws CommandSyntaxException {
            float red = reader.readFloat();
            reader.expect(' ');
            float green = reader.readFloat();
            reader.expect(' ');
            float blue = reader.readFloat();
            reader.expect(' ');
            float size = reader.readFloat();
            reader.expect(' ');
            float setLifetime = reader.readFloat();
            return new WispParticleOptions(red, green, blue, size, setLifetime);
        }

        @Override
        public WispParticleOptions fromNetwork(ParticleType<WispParticleOptions> particleType, FriendlyByteBuf buffer) {
            float red = buffer.readFloat();
            float green = buffer.readFloat();
            float blue = buffer.readFloat();
            float size = buffer.readFloat();
            float setLifetime = buffer.readFloat();
            return new WispParticleOptions(red, green, blue, size, setLifetime);
        }

    };

    private final float red;
    private final float green;
    private final float blue;
    private final float size;
    private final float setLifetime;

    public WispParticleOptions(float red, float green, float blue, float size, float setLifetime) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.size = size;
        this.setLifetime = setLifetime;
    }

    @Override
    public ParticleType<?> getType() {
        return Registries.WISP.get();
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf buffer) {
        buffer.writeFloat(red);
        buffer.writeFloat(green);
        buffer.writeFloat(blue);
        buffer.writeFloat(size);
        buffer.writeFloat(setLifetime);
    }

    @Override
    public String writeToString() {
        return String.format("WispParticleOptions{red=%.2f, green=%.2f, blue=%.2f, size=%.2f}", red, green, blue, size);
    }

    public float getRed() {
        return red;
    }

    public float getGreen() {
        return green;
    }

    public float getBlue() {
        return blue;
    }

    public float getSize() {
        return size;
    }

    public float getSetLifetime() {
        return setLifetime;
    }
}
