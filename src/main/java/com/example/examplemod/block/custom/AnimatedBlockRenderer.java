package com.example.examplemod.block.custom;

import com.example.examplemod.block.entity.GuidingSkintBlockEntity;
import com.example.examplemod.block.entity.client.GuidingSkintBlockModel;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class AnimatedBlockRenderer extends GeoBlockRenderer<GuidingSkintBlockEntity> {
    public AnimatedBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new GuidingSkintBlockModel());
    }
}
