package com.example.examplemod.item.client;

import com.example.examplemod.SWM;
import com.example.examplemod.item.custom.GuidingSkintBlockItem;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AnimatedBlockItemModel extends GeoModel<GuidingSkintBlockItem> {
     @Override
    public ResourceLocation getModelResource(GuidingSkintBlockItem animatable) {
        return new ResourceLocation(SWM.MODID, "geo/animated_block.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GuidingSkintBlockItem animatable) {
        return new ResourceLocation(SWM.MODID, "textures/block/animated_block.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GuidingSkintBlockItem animatable) {
        return new ResourceLocation(SWM.MODID, "animations/animated_block.animation.json");
    }
}
