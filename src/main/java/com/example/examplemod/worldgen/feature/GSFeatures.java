package com.example.examplemod.worldgen.feature;

import com.example.examplemod.GS;
import com.example.examplemod.worldgen.feature.custom.ObeliskFeature;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GSFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, GS.MODID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> OBELISK_FEATURE = FEATURES.register("obelisk_feature", () -> new ObeliskFeature(NoneFeatureConfiguration.CODEC));

    public static void init(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}
