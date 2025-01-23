package net.flaim.guiding_skint.block;

import net.flaim.guiding_skint.GuidingSkintMod;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class GuidingSkintRenderer extends GeoBlockRenderer<GuidingSkintBlockEntity> {
    public GuidingSkintRenderer() {
        super(new GuidingSkintModel<>());
    }

    public static class GuidingSkintModel<T extends GeoAnimatable> extends DefaultedBlockGeoModel<T> {
        public GuidingSkintModel() {
            super(new ResourceLocation(GuidingSkintMod.MOD_ID, "guiding_skint"));
        }

        @Override
        public RenderType getRenderType(T animatable, ResourceLocation texture) {
            return RenderType.entityCutout(texture);
        }
    }
}