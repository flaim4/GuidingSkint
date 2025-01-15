package com.example.examplemod.block;

import com.example.examplemod.GS;
import com.example.examplemod.block.entity.GuidingSkintBlockEntity;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class GSBlockEntityType {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, GS.MODID);

    public static final RegistryObject<BlockEntityType<GuidingSkintBlockEntity>> GUIDING_SKINT_BLOCK_ENTITY =
        BLOCK_ENTITIES.register("guiding_skint_entity", () ->
            BlockEntityType.Builder.of(GuidingSkintBlockEntity::new,
                        GSBlocks.GUIDING_SKINT_BLOCK.get()).build(null));

    public static void init(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
