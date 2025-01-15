package com.example.examplemod.item;

import com.example.examplemod.SWM;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class SWMCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(net.minecraft.core.registries.Registries.CREATIVE_MODE_TAB, SWM.MODID);

    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("guidingskint_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.BUILDING_BLOCKS).icon(() -> SWMItems.ANIMATED_BLOCK_ITEM.get().getDefaultInstance()).displayItems((parameters, output) -> {
                output.accept(SWMItems.ANIMATED_BLOCK_ITEM.get());
            }).build());

    public static void init(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
