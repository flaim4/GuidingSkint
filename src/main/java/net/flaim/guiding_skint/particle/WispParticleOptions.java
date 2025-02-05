package net.flaim.guiding_skint.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.flaim.guiding_skint.Registries;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import org.jetbrains.annotations.NotNull;

public record WispParticleOptions(float red, float green, float blue, float size, float setLifetime) implements ParticleOptions {
    public static final Codec<WispParticleOptions> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Codec.FLOAT.fieldOf("red").forGetter(WispParticleOptions::red),
            Codec.FLOAT.fieldOf("green").forGetter(WispParticleOptions::green),
            Codec.FLOAT.fieldOf("blue").forGetter(WispParticleOptions::blue),
            Codec.FLOAT.fieldOf("size").forGetter(WispParticleOptions::size),
            Codec.FLOAT.fieldOf("setLifetime").forGetter(WispParticleOptions::setLifetime)
    ).apply(instance, WispParticleOptions::new));

    public static final Deserializer<WispParticleOptions> DESERIALIZER = new Deserializer<>() {
        @Override
        public @NotNull WispParticleOptions fromCommand(@NotNull ParticleType<WispParticleOptions> particleType, StringReader reader) throws CommandSyntaxException {
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
        public @NotNull WispParticleOptions fromNetwork(@NotNull ParticleType<WispParticleOptions> particleType, FriendlyByteBuf buffer) {
            float red = buffer.readFloat();
            float green = buffer.readFloat();
            float blue = buffer.readFloat();
            float size = buffer.readFloat();
            float setLifetime = buffer.readFloat();
            return new WispParticleOptions(red, green, blue, size, setLifetime);
        }
    };

    @Override
    public @NotNull ParticleType<?> getType() {
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
    public @NotNull String writeToString() {
        return String.format("WispParticleOptions{red=%.2f, green=%.2f, blue=%.2f, size=%.2f}", red, green, blue, size);
    }
}
