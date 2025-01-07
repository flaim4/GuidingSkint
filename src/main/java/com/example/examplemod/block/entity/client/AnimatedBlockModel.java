package com.example.examplemod.block.entity.client;

import com.example.examplemod.SWM;
import com.example.examplemod.block.entity.AnimatedBlockEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AnimatedBlockModel extends GeoModel<AnimatedBlockEntity> {
    @Override
    public ResourceLocation getModelResource(AnimatedBlockEntity animatable) {
        return new ResourceLocation(SWM.MODID, "geo/animated_block.geo.json");
    }
    @Override
    public ResourceLocation getTextureResource(AnimatedBlockEntity animatable) {
        return new ResourceLocation(SWM.MODID, "textures/block/animated_block.png");
    }
    @Override
    public ResourceLocation getAnimationResource(AnimatedBlockEntity animatable) {
        return new ResourceLocation(SWM.MODID, "animations/animated_block.animation.json");
    }
}
