package com.example.examplemod.block.entity.client;

import com.example.examplemod.SWM;
import com.example.examplemod.block.custom.GuidingSkintBlock;
import com.example.examplemod.block.entity.GuidingSkintBlockEntity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.model.GeoModel;

public class GuidingSkintBlockModel extends GeoModel<GuidingSkintBlockEntity> {

    @Override
    public ResourceLocation getModelResource(GuidingSkintBlockEntity animatable) {
        BlockState state = animatable.getBlockState();
        boolean actionState = state.getValue(GuidingSkintBlock.ACTION);

        if (actionState) {
            return new ResourceLocation(SWM.MODID, "geo/guiding_skint.geo.json");
        } else {
            return new ResourceLocation(SWM.MODID, "geo/infected_skint.geo.json");
        }
    }

    @Override
    public ResourceLocation getTextureResource(GuidingSkintBlockEntity animatable) {
        return new ResourceLocation(SWM.MODID, "textures/block/guiding_skint.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GuidingSkintBlockEntity animatable) {
        return new ResourceLocation(SWM.MODID, "animations/guiding_skint.animation.json");
    }

}
