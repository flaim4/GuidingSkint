package com.example.examplemod.block;

import com.example.examplemod.SWM;
import com.example.examplemod.block.entity.AnimatedBlockEntity;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class SWMBlockEntityType {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, SWM.MODID);

    public static final RegistryObject<BlockEntityType<AnimatedBlockEntity>> ANIMATED_BLOCK_ENTITY =
        BLOCK_ENTITIES.register("animated_block_entity", () ->
            BlockEntityType.Builder.of(AnimatedBlockEntity::new,
                        SWMBlocks.ANIMATED_BLOCK.get()).build(null));

    public static void init(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
