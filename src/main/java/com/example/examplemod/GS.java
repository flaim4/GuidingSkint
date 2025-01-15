package com.example.examplemod;

import com.example.examplemod.block.GSBlocks;
import com.example.examplemod.block.GSBlockEntityType;
import com.example.examplemod.item.GSCreativeModeTabs;
import com.example.examplemod.item.GSItems;
import com.example.examplemod.particle.GSParticleTypes;
import com.example.examplemod.worldgen.structure.GSStructures;
import com.mojang.logging.LogUtils;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib.GeckoLib;

import java.util.Random;

import org.slf4j.Logger;

@Mod(GS.MODID)
public class GS {
    public static final String MODID = "examplemod";
    public static final Random RANDOM = new Random();
    public static final Logger LOGGER = LogUtils.getLogger();

    public GS() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        GeckoLib.initialize();
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> GSClient::init);
        GSBlocks.init(modEventBus);
        GSBlockEntityType.init(modEventBus);
        GSItems.init(modEventBus);
        GSStructures.init(modEventBus);
        GSCreativeModeTabs.init(modEventBus);
        GSParticleTypes.init(modEventBus);
    }

}
