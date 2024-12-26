package com.example.examplemod.worldgen.blocks.entities.client;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.worldgen.blocks.entities.AnimatedBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AnimatedBlockModel extends GeoModel<AnimatedBlockEntity> {
    @Override
    public ResourceLocation getModelResource(AnimatedBlockEntity animatable) {
        return new ResourceLocation(ExampleMod.MODID, "geo/animated_block.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AnimatedBlockEntity animatable) {
        return new ResourceLocation(ExampleMod.MODID, "textures/block/animated_block.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AnimatedBlockEntity animatable) {
        return new ResourceLocation(ExampleMod.MODID, "animations/animated_block.animation.json");
    }
}
