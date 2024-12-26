package com.example.examplemod.worldgen.feature;

import com.example.examplemod.ExampleMod;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class ObeliskFeature extends Feature<NoneFeatureConfiguration> {

    public ObeliskFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos pos = context.origin();
        WorldGenLevel level = context.level();

        int x = pos.getX();
        int z = pos.getY();
        int y = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE, new BlockPos(x, 0, z)).getY() - 1;
        pos = new BlockPos(x, y, z);

        if (!canGen(level, pos)) return false;
        if (level.getBlockState(pos.below()).getBlock() == Blocks.GRASS_BLOCK) {
            level.setBlock(pos.below(), Blocks.GRASS_BLOCK.defaultBlockState(), 3);
        }

        level.setBlock(pos, Blocks.GOLD_BLOCK.defaultBlockState(), 3);
        BlockState blockStateBelow = level.getBlockState(pos.below());
        blockStateBelow.onNeighborChange(level, pos.below(), pos);

        return true;
    }

    public boolean canGen(WorldGenLevel level, BlockPos pos) {
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                BlockState blockStateAbove = level.getBlockState(pos.offset(x, 1, z));
                BlockState blockState = level.getBlockState(pos.offset(x, 0, z));
                BlockState blockStateBelow = level.getBlockState(pos.offset(x, -1, z));
                if (!blockState.canBeReplaced() || blockState.is(Blocks.WATER) || !blockStateAbove.canBeReplaced() || !blockStateBelow.canOcclude() || blockStateBelow.canBeReplaced() || ExampleMod.RANDOM.nextInt(100) > 60) return false;
            }
        }
        return true;
    }
    
}
