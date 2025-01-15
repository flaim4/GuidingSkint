package com.example.examplemod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import com.example.examplemod.block.entity.GuidingSkintBlockEntity;

public class GuidingSkintBlock extends BaseEntityBlock {

    public static final BooleanProperty ACTION = BooleanProperty.create("action");

    VoxelShape guidingSkintBox = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 28.0D, 14.0D);
    VoxelShape infectedSkintBox = Block.box(2.0D, 0D, 2.0D, 14.0D, 8D, 14.0D);

    public GuidingSkintBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(ACTION, false));
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            boolean currentAction = blockState.getValue(ACTION);
            BlockState newState = blockState.setValue(ACTION, !currentAction);

            level.setBlock(blockPos, newState, Block.UPDATE_ALL);
            level.sendBlockUpdated(blockPos, blockState, newState, Block.UPDATE_ALL);

            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof GuidingSkintBlockEntity) {
                ((GuidingSkintBlockEntity) blockEntity).playAnimation();
            }

            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter p_152022_, BlockPos p_152023_, CollisionContext p_152024_) {
        return blockState.getValue(ACTION) ? guidingSkintBox : infectedSkintBox;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTION);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState state) {
        return new GuidingSkintBlockEntity(blockPos, state);
    }

}
