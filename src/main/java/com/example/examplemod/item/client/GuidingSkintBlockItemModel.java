package com.example.examplemod.item.client;

import com.example.examplemod.SWM;
import com.example.examplemod.item.custom.GuidingSkintBlockItem;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GuidingSkintBlockItemModel extends GeoModel<GuidingSkintBlockItem> {
     @Override
    public ResourceLocation getModelResource(GuidingSkintBlockItem animatable) {
        return new ResourceLocation(SWM.MODID, "geo/guiding_skint.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GuidingSkintBlockItem animatable) {
        return new ResourceLocation(SWM.MODID, "textures/block/guiding_skint.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GuidingSkintBlockItem animatable) {
        return new ResourceLocation(SWM.MODID, "animations/guiding_skint.animation.json");
    }
}
