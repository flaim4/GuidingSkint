package net.flaim.guiding_skint.block;

import net.flaim.guiding_skint.Registries;
import net.flaim.guiding_skint.client.HUDHandler;
import net.flaim.guiding_skint.network.BlockStartAnimationS2C;
import net.flaim.guiding_skint.network.PacketHandler;
import net.flaim.guiding_skint.particle.WispParticleOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
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
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GuidingSkintBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock, EntityBlock {
    public static final BooleanProperty INFECTED = BooleanProperty.create("infected");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final float[][] COLORS = {
        {0.99f, 0.91f, 0.48f},
        {0.89f, 0.4f, 0.77f}
    };

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
    public @NotNull InteractionResult use(BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if (level.isClientSide() && !state.getValue(INFECTED)) {
            Minecraft.getInstance().execute(() -> Minecraft.getInstance().setScreen(new HUDHandler()));
        }
        if (level.isClientSide() || !state.getValue(INFECTED)) {
            return InteractionResult.PASS;
        }

        level.setBlockAndUpdate(pos, state.setValue(INFECTED, false));
        PacketHandler.sendToAll(new BlockStartAnimationS2C(pos));
        CompoundTag playerNBT = player.getPersistentData();
        ListTag guidingSkintList = playerNBT.getList("ActivatedGuidingSkint", Tag.TAG_STRING);
        ListTag updatedList = new ListTag();

        for (int i = 0; i < guidingSkintList.size(); i++) {
            StringTag guidingSkintTag = (StringTag) guidingSkintList.get(i);
            String[] parts = guidingSkintTag.getAsString().split(";");
            String[] coords = parts[0].split(",");
            BlockPos blockPos = new BlockPos(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2]));
            if (!level.getBlockState(blockPos).isAir()) {
                updatedList.add(guidingSkintTag);
            }
        }

        String blockData = pos.getX() + "," + pos.getY() + "," + pos.getZ() + ";" + state;
        if (updatedList.stream().map(Tag::getAsString).noneMatch(blockData::equals)) {
            updatedList.add(StringTag.valueOf(blockData));
        }

        playerNBT.put("ActivatedGuidingSkint", updatedList);
        updatedList.forEach(tag -> System.out.println("GuidingSkint: " + tag.getAsString()));

        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, INFECTED);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return Block.box(2, 0, 2, 14, state.getValue(INFECTED) ? 8 : 28, 14);
    }

    @Override
    public @NotNull VoxelShape getCollisionShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return getShape(state, world, pos, context);
    }

    @Override
    public @NotNull BlockState updateShape(BlockState state, @NotNull Direction facing, @NotNull BlockState facingState, @NotNull LevelAccessor world, @NotNull BlockPos pos, @NotNull BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }
        return state;
    }

    @Override
    public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new GuidingSkintBlockEntity(pos, state);
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Override
    protected void spawnDestroyParticles(Level world, @NotNull Player player, @NotNull BlockPos pos, @NotNull BlockState state) {
        if (!world.isClientSide()) return;
        world.levelEvent(player, 2001, pos, getId(state));
    }

    @Override
    public void animateTick(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (!level.isClientSide || random.nextInt(state.getValue(INFECTED) ? 10 : 8) != 0) return;

        float[] color;
        double offsetXZ, offsetY, speedMultiplier;
        float size;
        if (state.getValue(INFECTED)) {
            color = COLORS[1];
            offsetXZ = -0.2;
            offsetY = 0;
            speedMultiplier = 1;
            size = 0.2f;
        } else {
            color = COLORS[0];
            offsetXZ = 0.2;
            offsetY = 0.1;
            speedMultiplier = 3;
            size = 0.4f;
        }

        WispParticleOptions particleOptions = new WispParticleOptions(color[0], color[1], color[2], size, 100f);

        for (int i = 0; i < 3; i++) {
            level.addParticle(
                particleOptions,
                pos.getX() + random.nextDouble() + offsetXZ,
                pos.getY() + random.nextDouble() + offsetY,
                pos.getZ() + random.nextDouble() + offsetXZ,
                (random.nextDouble() - 0.5) * 0.1,
                random.nextDouble() * speedMultiplier + 0.4,
                (random.nextDouble() - 0.5) * 0.1
            );
        }
    }
}
