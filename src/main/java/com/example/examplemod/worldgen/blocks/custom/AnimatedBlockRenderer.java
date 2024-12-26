package com.example.examplemod.worldgen.blocks.custom;

import com.example.examplemod.worldgen.blocks.entities.AnimatedBlockEntity;
import com.example.examplemod.worldgen.blocks.entities.client.AnimatedBlockModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class AnimatedBlockRenderer extends GeoBlockRenderer<AnimatedBlockEntity> {
    public AnimatedBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new AnimatedBlockModel());
    }
}
