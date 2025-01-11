package com.example.examplemod.item;

import com.example.examplemod.SWM;
import com.example.examplemod.block.SWMBlocks;
import com.example.examplemod.item.custom.GuidingSkintBlockItem;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SWMItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SWM.MODID);

    public static final RegistryObject<Item> ANIMATED_BLOCK_ITEM = ITEMS.register("animated_block",
        () -> new GuidingSkintBlockItem(SWMBlocks.GUIDING_SKINT_BLOCK.get(), new Item.Properties()));

    public static void init(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
