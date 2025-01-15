package com.example.examplemod.worldgen.structure.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.Optional;

public class GuidingSkintStructur extends Structure {

    private final int size;
    private final int maxDistanceFromCenter;
    private final HeightProvider startHeight;
    private final Optional<ResourceLocation> startJigsawName;
    private final Holder<StructureTemplatePool> startPool;
    private final Optional<Heightmap.Types> projectStartToHeightmap;

    protected GuidingSkintStructur(Structure.StructureSettings config, Holder<StructureTemplatePool> startPool, Optional<ResourceLocation> startJigsawName, int size, HeightProvider startHeight, Optional<Heightmap.Types> projectStartToHeightmap, int maxDistanceFromCenter) {
        super(config);
        this.size = size;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
        this.startHeight = startHeight;
        this.startJigsawName = startJigsawName;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.startPool = startPool;
    }

    public static final Codec<GuidingSkintStructur> CODEC = RecordCodecBuilder.<GuidingSkintStructur>mapCodec(instance ->
            instance.group(GuidingSkintStructur.settingsCodec(instance),
                    StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                    ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsawName),
                    Codec.intRange(0, 30).fieldOf("size").forGetter(structure -> structure.size),
                    HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
                    Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
                    Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter)
            ).apply(instance, GuidingSkintStructur::new)).codec();

    @Override
    protected Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        WorldgenRandom worldgenRandom = context.random();
        int x = context.chunkPos().getMinBlockX() + worldgenRandom.nextInt(16);
        int z = context.chunkPos().getMinBlockZ() + worldgenRandom.nextInt(16);
        int sea = context.chunkGenerator().getSeaLevel();
        WorldGenerationContext worldGenerationContext = new WorldGenerationContext(context.chunkGenerator(), context.heightAccessor());
        int y = this.startHeight.sample(worldgenRandom, worldGenerationContext);
        NoiseColumn noiseColumn = context.chunkGenerator().getBaseColumn(x, z, context.heightAccessor(), context.randomState());
        while(y >= sea) {
            BlockState blockState = noiseColumn.getBlock(y);
            y--;
            BlockState blockState2 = noiseColumn.getBlock(y);
            if (blockState.isAir()
                    && !blockState2.isAir()) {
                break;
            }
        }

        if (y <= sea) {
            return Optional.empty();
        } else {
            BlockPos blockPos = new BlockPos(x, ++y, z);
            return JigsawPlacement.addPieces(context, this.startPool, this.startJigsawName, this.size, blockPos, false, this.projectStartToHeightmap, 0);
        }
    }

    @Override
    public StructureType<?> type() {
        return null;
    }
}
