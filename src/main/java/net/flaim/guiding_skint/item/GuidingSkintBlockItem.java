package net.flaim.guiding_skint.item;

import net.flaim.guiding_skint.block.GuidingSkintRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class GuidingSkintBlockItem extends BlockItem implements GeoItem {
    protected static final RawAnimation DEPLOY_ANIM = RawAnimation.begin().thenPlay("animation.purified");
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    public GuidingSkintBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private final GeoItemRenderer<GuidingSkintBlockItem> renderer = new GeoItemRenderer<>(new GuidingSkintRenderer.GuidingSkintModel<GuidingSkintBlockItem>());

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, this::deployAnimController));
    }

    protected <E extends GuidingSkintBlockItem> PlayState deployAnimController(final AnimationState<E> state) {
        return state.setAndContinue(DEPLOY_ANIM);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }
}