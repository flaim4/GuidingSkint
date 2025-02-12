package net.flaim.guiding_skint.block;

import net.flaim.guiding_skint.Registries;
import net.flaim.guiding_skint.client.HUDHandler;
import net.flaim.guiding_skint.particle.WispParticleOptions;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
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
    private static final RawAnimation INFECTED_ANIM = RawAnimation.begin().thenPlay("animation.infected");
    public static final RawAnimation TRANSFORMATION_ANIM = RawAnimation.begin().thenPlay("animation.transformation");
    private static final RawAnimation PURIFIED_ANIM = RawAnimation.begin().thenPlay("animation.purified");

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public GuidingSkintBlockEntity(BlockPos pos, BlockState state) {
        super(Registries.GUIDING_SKINT_BLOCK_ENTITY.get(), pos, state);
        DEPLOY_ANIM = state.getValue(GuidingSkintBlock.INFECTED) ? INFECTED_ANIM : PURIFIED_ANIM;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, this::deployAnimController));
    }

    public static void spawnParticleWave(Level level, BlockPos pos, RandomSource random) {
        if (!level.isClientSide) return;

        int particleCount = 150;
        float red, green, blue;
        double maxRadius = 1.0;
        double speedMultiplier = maxRadius / 0.03f;

        red = 252.0f / 255.0f;
        green = 232.0f / 255.0f;
        blue = 123.0f / 255.0f;

        for (int i = 0; i < particleCount; i++) {

            double theta = 2 * Math.PI * random.nextDouble();
            double phi = Math.acos(2 * random.nextDouble() - 1);

            double velocityX = Math.sin(phi) * Math.cos(theta) * speedMultiplier;
            double velocityY = Math.sin(phi) * Math.sin(theta) * speedMultiplier;
            double velocityZ = Math.cos(phi) * speedMultiplier;

            WispParticleOptions particleOptions = new WispParticleOptions(red, green, blue, 0.8f, 120);

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

    private long triggerTime = -1;

    protected <E extends GuidingSkintBlockEntity> PlayState deployAnimController(final AnimationState<E> state) {
        if (state.getController().getCurrentRawAnimation() == TRANSFORMATION_ANIM) {
            if (state.getController().hasAnimationFinished()) {
                DEPLOY_ANIM = PURIFIED_ANIM;
            } else {
                long currentTime = System.currentTimeMillis();

                if (triggerTime == -1) {
                    triggerTime = currentTime + 3100;
                }

                if (currentTime >= triggerTime) {
                    spawnParticleWave(level, getBlockPos(), RandomSource.create());
                    HUDHandler.startTimer();
                    triggerTime = -1;
                }
            }
        }
        return state.setAndContinue(DEPLOY_ANIM);
    }



    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}