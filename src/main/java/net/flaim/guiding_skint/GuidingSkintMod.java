package net.flaim.guiding_skint;

import net.flaim.guiding_skint.network.PacketHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.simple.SimpleChannel;

@Mod(GuidingSkintMod.MOD_ID)
public class GuidingSkintMod {
    public static final String MOD_ID = "guiding_skint";

    public GuidingSkintMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        Registries.register(modEventBus);
        PacketHandler.register();
        MinecraftForge.EVENT_BUS.register(this);
    }
}