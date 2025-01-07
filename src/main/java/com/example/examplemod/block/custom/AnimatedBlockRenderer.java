package com.example.examplemod.block.custom;

import com.example.examplemod.block.entity.AnimatedBlockEntity;
import com.example.examplemod.block.entity.client.AnimatedBlockModel;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class AnimatedBlockRenderer extends GeoBlockRenderer<AnimatedBlockEntity> {
    public AnimatedBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new AnimatedBlockModel());
    }
}
