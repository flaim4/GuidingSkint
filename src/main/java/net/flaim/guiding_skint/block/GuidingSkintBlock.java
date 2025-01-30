package net.flaim.guiding_skint.block;

import net.flaim.guiding_skint.Registries;
import net.flaim.guiding_skint.network.BlockStartAnimationS2C;
import net.flaim.guiding_skint.network.PacketHandler;
import net.flaim.guiding_skint.particle.WispParticleOptions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
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
            .lightLevel((state) -> state.getValue(INFECTED) ? 0 : 12)
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
        if (state.getValue(INFECTED) && !level.isClientSide()) {
            level.setBlockAndUpdate(pos, state.setValue(INFECTED, false));
            PacketHandler.sendToAll(new BlockStartAnimationS2C(pos));
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
        world.levelEvent(player, 2001, pos, getId(state));
    }

    public static void spawnParticleWave(Level level, BlockPos pos, RandomSource random, boolean isInfected) {
        if (!level.isClientSide) return;

        int particleCount = 150;
        float red, green, blue;
        double maxRadius = 8.0;
        double speedMultiplier = maxRadius / 1f;


        red = 252.0f / 255.0f;
        green = 232.0f / 255.0f;
        blue = 123.0f / 255.0f;

        for (int i = 0; i < particleCount; i++) {

            double theta = 2 * Math.PI * random.nextDouble();
            double phi = Math.acos(2 * random.nextDouble() - 1);

            double velocityX = Math.sin(phi) * Math.cos(theta) * speedMultiplier;
            double velocityY = Math.sin(phi) * Math.sin(theta) * speedMultiplier;
            double velocityZ = Math.cos(phi) * speedMultiplier;

            WispParticleOptions particleOptions = new WispParticleOptions(red, green, blue, 8.0f, 1000);

            level.addParticle(
                    particleOptions,
                    pos.getX() + 0.5,
                    pos.getY() + 0.5,
                    pos.getZ() + 0.5,
                    velocityX,
                    velocityY,
                    velocityZ
            );
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (!level.isClientSide) return;

        boolean isInfected = state.getValue(INFECTED);
        int randomChance = isInfected ? 10 : 8;
        float red, green, blue;
        double offsetX, offsetY, offsetZ;
        double particleSpeed;
        float size;

        if (random.nextInt(randomChance) == 0) {
            for (int i = 0; i < 3; i++) {
                if (isInfected) {
                    red = 227.0f / 255.0f;
                    green = 102.0f / 255.0f;
                    blue = 196.0f / 255.0f;
                    offsetX = random.nextDouble() - 0.2;
                    offsetZ = random.nextDouble() - 0.2;
                    offsetY = random.nextDouble();
                    particleSpeed = 0.4 + random.nextDouble() * 1;
                    size = 0.2f;
                } else {
                    red = 252.0f / 255.0f;
                    green = 232.0f / 255.0f;
                    blue = 123.0f / 255.0f;
                    offsetX = 0.7 + (random.nextDouble() - 0.5) * 1.0;
                    offsetZ = 0.7 + (random.nextDouble() - 0.5) * 1.0;
                    offsetY = 0.1 + random.nextDouble();
                    particleSpeed = 0.4 + random.nextDouble() * 3;
                    size = 0.6f;
                }

                WispParticleOptions particleOptions = new WispParticleOptions(red, green, blue, size, 100f);

                level.addParticle(
                        particleOptions,
                        pos.getX() + offsetX,
                        pos.getY() + offsetY,
                        pos.getZ() + offsetZ,
                        (random.nextDouble() - 0.5) * 0.1,
                        particleSpeed,
                        (random.nextDouble() - 0.5) * 0.1
                );
            }
        }
    }
















}
