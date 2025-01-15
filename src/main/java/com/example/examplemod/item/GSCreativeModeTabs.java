package com.example.examplemod.item;

import com.example.examplemod.GS;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class GSCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(net.minecraft.core.registries.Registries.CREATIVE_MODE_TAB, GS.MODID);

    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("guidingskint_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.BUILDING_BLOCKS).icon(() -> GSItems.ANIMATED_BLOCK_ITEM.get().getDefaultInstance()).displayItems((parameters, output) -> {
                output.accept(GSItems.ANIMATED_BLOCK_ITEM.get());
            }).build());

    public static void init(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
