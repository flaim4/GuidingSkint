package com.example.examplemod.worldgen.item.client;

import com.example.examplemod.worldgen.item.custom.AnimatedBlockItem;

import software.bernie.geckolib.renderer.GeoItemRenderer;

public class AnimatedBlockItemRenderer extends GeoItemRenderer<AnimatedBlockItem> {
    public AnimatedBlockItemRenderer() {
        super(new AnimatedBlockItemModel());
    }   
}
