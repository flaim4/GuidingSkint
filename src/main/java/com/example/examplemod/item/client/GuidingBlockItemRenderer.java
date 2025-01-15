package com.example.examplemod.item.client;

import com.example.examplemod.item.custom.GuidingSkintBlockItem;

import software.bernie.geckolib.renderer.GeoItemRenderer;

public class GuidingBlockItemRenderer extends GeoItemRenderer<GuidingSkintBlockItem> {
    public GuidingBlockItemRenderer() {
        super(new GuidingSkintBlockItemModel());
    }   
}
