package com.example.examplemod.item.client;

import com.example.examplemod.item.custom.GuidingSkintBlockItem;

import software.bernie.geckolib.renderer.GeoItemRenderer;

public class AnimatedBlockItemRenderer extends GeoItemRenderer<GuidingSkintBlockItem> {
    public AnimatedBlockItemRenderer() {
        super(new AnimatedBlockItemModel());
    }   
}
