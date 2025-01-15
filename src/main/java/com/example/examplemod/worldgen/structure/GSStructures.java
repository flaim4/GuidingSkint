package com.example.examplemod.worldgen.structure;

import com.example.examplemod.GS;
import com.example.examplemod.worldgen.structure.custom.GuidingSkintStructur;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class GSStructures {
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPE = DeferredRegister.create(Registries.STRUCTURE_TYPE,GS.MODID);

    public static final RegistryObject<StructureType<GuidingSkintStructur>> GUIDING_SKINT = STRUCTURE_TYPE.register("guiding_skint", () -> () -> GuidingSkintStructur.CODEC);

    public static void init(IEventBus eventBus) {
        STRUCTURE_TYPE.register(eventBus);
    }
}
