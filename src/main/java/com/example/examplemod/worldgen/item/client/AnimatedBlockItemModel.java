package com.example.examplemod.worldgen.item.client;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.worldgen.item.custom.AnimatedBlockItem;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AnimatedBlockItemModel extends GeoModel<AnimatedBlockItem> {
     @Override
    public ResourceLocation getModelResource(AnimatedBlockItem animatable) {
        return new ResourceLocation(ExampleMod.MODID, "geo/animated_block.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AnimatedBlockItem animatable) {
        return new ResourceLocation(ExampleMod.MODID, "textures/block/animated_block.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AnimatedBlockItem animatable) {
        return new ResourceLocation(ExampleMod.MODID, "animations/animated_block.animation.json");
    }
}
