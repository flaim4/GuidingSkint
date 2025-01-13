package com.example.examplemod.block.custom;

import com.example.examplemod.block.entity.GuidingSkintBlockEntity;
import com.example.examplemod.block.entity.client.GuidingSkintBlockModel;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class GuidingSkintBlockRenderer extends GeoBlockRenderer<GuidingSkintBlockEntity> {
    public GuidingSkintBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new GuidingSkintBlockModel());
    }
}
