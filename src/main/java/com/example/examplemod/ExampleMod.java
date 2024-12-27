package com.example.examplemod;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Random;

import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

@Mod(ExampleMod.MODID)
public class ExampleMod {
    public static final String MODID = "examplemod";
    public static final Random RANDOM = new Random();
    public static final Logger LOGGER = LogUtils.getLogger();


    public ExampleMod(FMLJavaModLoadingContext context) {
        GeckoLib.initialize();
        IEventBus modEventBus = context.getModEventBus();
        Registries.register(modEventBus);

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
