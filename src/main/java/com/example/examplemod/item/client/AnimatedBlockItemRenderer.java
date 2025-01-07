package com.example.examplemod.item.client;

import com.example.examplemod.item.custom.AnimatedBlockItem;

import software.bernie.geckolib.renderer.GeoItemRenderer;

public class AnimatedBlockItemRenderer extends GeoItemRenderer<AnimatedBlockItem> {
    public AnimatedBlockItemRenderer() {
        super(new AnimatedBlockItemModel());
    }   
}
