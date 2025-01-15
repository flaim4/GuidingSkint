package com.example.examplemod.item;

import com.example.examplemod.GS;
import com.example.examplemod.block.GSBlocks;
import com.example.examplemod.item.custom.GuidingSkintBlockItem;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GSItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, GS.MODID);

    public static final RegistryObject<Item> ANIMATED_BLOCK_ITEM = ITEMS.register("guiding_skint",
        () -> new GuidingSkintBlockItem(GSBlocks.GUIDING_SKINT_BLOCK.get(), new Item.Properties()));

    public static void init(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
