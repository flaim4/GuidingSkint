package com.example.examplemod.item.client;

import com.example.examplemod.SWM;
import com.example.examplemod.item.custom.AnimatedBlockItem;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AnimatedBlockItemModel extends GeoModel<AnimatedBlockItem> {
     @Override
    public ResourceLocation getModelResource(AnimatedBlockItem animatable) {
        return new ResourceLocation(SWM.MODID, "geo/animated_block.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AnimatedBlockItem animatable) {
        return new ResourceLocation(SWM.MODID, "textures/block/animated_block.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AnimatedBlockItem animatable) {
        return new ResourceLocation(SWM.MODID, "animations/animated_block.animation.json");
    }
}
