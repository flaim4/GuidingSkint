package net.flaim.guiding_skint.client;


import net.flaim.guiding_skint.GuidingSkintMod;
import net.flaim.guiding_skint.Registries;
import net.flaim.guiding_skint.block.GuidingSkintRenderer;
import net.flaim.guiding_skint.particle.Wisp;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GuidingSkintMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class GuidingSkintModClient {
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(Registries.GUIDING_SKINT_BLOCK_ENTITY.get(), context -> new GuidingSkintRenderer());
    }

    @SubscribeEvent
    public static void renderParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(Registries.WISP.get(), spriteSet -> new Wisp.NormalFactory(spriteSet));
    }

    @SubscribeEvent
    public static void registerOverlayEvent(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("hud", HUDHandler.INSTANCE);
    }

}
