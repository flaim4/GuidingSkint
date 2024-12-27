package com.example.examplemod.worldgen.item.client;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.worldgen.item.custom.AnimatedItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AnimatedItemModel extends GeoModel<AnimatedItem> {
    @Override
    public ResourceLocation getModelResource(AnimatedItem animatable) {
        return new ResourceLocation(ExampleMod.MODID, "geo/animated_item.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AnimatedItem animatable) {
        return new ResourceLocation(ExampleMod.MODID, "textures/item/animated_item.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AnimatedItem animatable) {
        return new ResourceLocation(ExampleMod.MODID, "animations/animated_item.animation.json");
    }
}
