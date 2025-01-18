package com.example.examplemod.block.entity.client;

import com.example.examplemod.GS;
import com.example.examplemod.block.custom.GuidingSkintBlock;
import com.example.examplemod.block.entity.GuidingSkintBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import software.bernie.geckolib.model.GeoModel;

public class GuidingSkintBlockModel extends GeoModel<GuidingSkintBlockEntity> {

    @Override
    public ResourceLocation getModelResource(GuidingSkintBlockEntity animatable) {
        BlockState state = animatable.getBlockState();
        boolean actionState = state.getValue(GuidingSkintBlock.ACTION);

        if (actionState) {
            return new ResourceLocation(GS.MODID, "geo/guiding_skint.geo.json");
        } else {
            return new ResourceLocation(GS.MODID, "geo/infected_skint.geo.json");
        }
    }


    @Override
    public ResourceLocation getTextureResource(GuidingSkintBlockEntity animatable) {
        return new ResourceLocation(GS.MODID, "textures/block/guiding_skint.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GuidingSkintBlockEntity animatable) {
        return new ResourceLocation(GS.MODID, "animations/guiding_skint.animation.json");
    }

}
