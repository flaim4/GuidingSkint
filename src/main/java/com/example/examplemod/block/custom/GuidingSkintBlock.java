package com.example.examplemod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import com.example.examplemod.block.entity.GuidingSkintBlockEntity;

public class GuidingSkintBlock extends BaseEntityBlock {

    public static final BooleanProperty ACTION = BooleanProperty.create("action");
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    VoxelShape guidingSkintBox = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 28.0D, 14.0D);
    VoxelShape infectedSkintBox = Block.box(2.0D, 0D, 2.0D, 14.0D, 8D, 14.0D);

    public GuidingSkintBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(ACTION, false).setValue(WATERLOGGED, false));
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
        } else {
            GuidingSkintBlockEntity.playAnimation();
            blockState.setValue(GuidingSkintBlock.ACTION, true);

            return InteractionResult.SUCCESS;
        }
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter p_152022_, BlockPos p_152023_, CollisionContext p_152024_) {
        return blockState.getValue(ACTION) ? guidingSkintBox : infectedSkintBox;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            return state;
        }

        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public BlockState mirror(BlockState p_152030_, Mirror p_152031_) {
        return p_152030_.rotate(p_152031_.getRotation(p_152030_.getValue(FACING)));
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTION, FACING, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction facing = context.getHorizontalDirection();
        FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());

        return this.defaultBlockState().setValue(FACING, facing);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState state) {
        return new GuidingSkintBlockEntity(blockPos, state);
    }
}
