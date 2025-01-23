package net.flaim.guiding_skint.block;

import net.flaim.guiding_skint.Registries;
import net.flaim.guiding_skint.network.PacketHandler;
import net.flaim.guiding_skint.network.UpdatePropertyC2SPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class GuidingSkintBlockEntity extends BlockEntity implements GeoBlockEntity {
    public RawAnimation DEPLOY_ANIM;
    protected static final RawAnimation INFECTED_ANIM = RawAnimation.begin().thenPlay("animation.infected");
    protected static final RawAnimation TRANSFORMATION_ANIM = RawAnimation.begin().thenPlay("animation.transformation");
    protected static final RawAnimation PURIFIED_ANIM = RawAnimation.begin().thenPlay("animation.purified");

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public GuidingSkintBlockEntity(BlockPos pos, BlockState state) {
        super(Registries.GUIDING_SKINT_BLOCK_ENTITY.get(), pos, state);
        DEPLOY_ANIM = state.getValue(GuidingSkintBlock.INFECTED) ? INFECTED_ANIM : PURIFIED_ANIM;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, this::deployAnimController));
    }

    protected <E extends GuidingSkintBlockEntity> PlayState deployAnimController(final AnimationState<E> state) {
        if (state.getController().getCurrentRawAnimation() == TRANSFORMATION_ANIM && state.getController().hasAnimationFinished()) {
            PacketHandler.sendToServer(new UpdatePropertyC2SPacket(getBlockPos()));
            DEPLOY_ANIM = PURIFIED_ANIM;
        }

        return state.setAndContinue(DEPLOY_ANIM);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}