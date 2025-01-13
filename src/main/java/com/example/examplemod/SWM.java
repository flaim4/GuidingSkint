package com.example.examplemod;

import com.example.examplemod.block.SWMBlockEntityType;
import com.example.examplemod.block.SWMBlocks;
import com.example.examplemod.item.SWMCreativeModeTabs;
import com.example.examplemod.item.SWMItems;
import com.example.examplemod.particle.SWMParticleTypes;
import com.example.examplemod.worldgen.feature.SWMFeatures;
import com.mojang.logging.LogUtils;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib.GeckoLib;

import java.util.Random;

import org.slf4j.Logger;

@Mod(SWM.MODID)
public class SWM {
    public static final String MODID = "examplemod";
    public static final Random RANDOM = new Random();
    public static final Logger LOGGER = LogUtils.getLogger();

    public SWM() {
        FMLJavaModLoadingContext context =  FMLJavaModLoadingContext.get();
        GeckoLib.initialize();
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> SWMClient::init);
        IEventBus modEventBus = context.getModEventBus();
        SWMBlocks.init(modEventBus);
        SWMBlockEntityType.init(modEventBus);
        SWMItems.init(modEventBus);
        SWMFeatures.init(modEventBus);
        SWMCreativeModeTabs.init(modEventBus);
        SWMParticleTypes.init(modEventBus);
    }

}