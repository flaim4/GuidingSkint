package net.flaim.guiding_skint.block;

import net.flaim.guiding_skint.Registries;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.FluidTags;
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
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import software.bernie.geckolib.core.animation.RawAnimation;

public class GuidingSkintBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock, EntityBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty INFECTED = BooleanProperty.create("infected");

    public GuidingSkintBlock(Properties properties) {
        super(properties
                .mapColor(MapColor.COLOR_YELLOW)
                .requiresCorrectToolForDrops()
                .strength(50, 1500)
                .pushReaction(PushReaction.BLOCK)
                .isValidSpawn(Registries::NEVER)
                .isRedstoneConductor(Registries::NEVER)
                .noParticlesOnBreak()
                .sound(SoundType.AMETHYST)
                .lightLevel((state) -> 15)
                .noOcclusion()
        );

        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false).setValue(INFECTED, false));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext placeContext) {
        FluidState fluidstate = placeContext.getLevel().getFluidState(placeContext.getClickedPos());
        return this.defaultBlockState().setValue(FACING, placeContext.getHorizontalDirection()).setValue(WATERLOGGED, fluidstate.is(FluidTags.WATER) && fluidstate.getAmount() == 8);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (state.getValue(GuidingSkintBlock.INFECTED)) {
            ((GuidingSkintBlockEntity) level.getBlockEntity(pos)).DEPLOY_ANIM = GuidingSkintBlockEntity.TRANSFORMATION_ANIM;
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, INFECTED);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Block.box(2, 0, 2, 14, state.getValue(INFECTED) ? 8 : 28, 14);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return getShape(state, world, pos, context);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos pos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }
        return state;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GuidingSkintBlockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Override
    protected void spawnDestroyParticles(Level world, Player player, BlockPos pos, BlockState state) {
        if (!world.isClientSide()) return;
        // player.sendSystemMessage(Component.literal("ХУЙ"));
        world.levelEvent(player, 2001, pos, getId(state));
    }
}
