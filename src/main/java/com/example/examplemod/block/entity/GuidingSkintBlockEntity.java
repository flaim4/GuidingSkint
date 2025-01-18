package com.example.examplemod.block.entity;

import com.example.examplemod.block.GSBlockEntityType;
import com.example.examplemod.block.custom.GuidingSkintBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

public class GuidingSkintBlockEntity extends BlockEntity implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private static boolean hasPlayedAnimation = true;

    public GuidingSkintBlockEntity(BlockPos pos, BlockState state) {
        super(GSBlockEntityType.GUIDING_SKINT_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> animationState) {
        BlockState state = getBlockState();
        BlockState newState;

        if (animationState.getController().getAnimationState() == AnimationController.State.RUNNING) {
            return PlayState.CONTINUE;
        }

        if (state.getValue(GuidingSkintBlock.ACTION)) {
            animationState.getController().setAnimation(RawAnimation.begin().then("animation2", Animation.LoopType.PLAY_ONCE));
            newState = state.setValue(GuidingSkintBlock.ACTION, false);
        } else {
            animationState.getController().setAnimation(RawAnimation.begin().then("animation", Animation.LoopType.PLAY_ONCE));
            newState = state.setValue(GuidingSkintBlock.ACTION, true);
        }
        level.setBlock(getBlockPos(), newState, getBlockState().getBlock().UPDATE_ALL);
        level.sendBlockUpdated(getBlockPos(), getBlockState(), newState, getBlockState().getBlock().UPDATE_ALL);
        return PlayState.CONTINUE;
    }


    public static void playAnimation() {
    }



    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object blockEntity) {
        return RenderUtils.getCurrentTick();
    }
}
