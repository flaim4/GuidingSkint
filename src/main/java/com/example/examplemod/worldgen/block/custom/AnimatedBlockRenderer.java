package com.example.examplemod.worldgen.block.custom;

import com.example.examplemod.worldgen.block.entitie.AnimatedBlockEntity;
import com.example.examplemod.worldgen.block.entitie.client.AnimatedBlockModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class AnimatedBlockRenderer extends GeoBlockRenderer<AnimatedBlockEntity> {
    public AnimatedBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new AnimatedBlockModel());
    }
}
