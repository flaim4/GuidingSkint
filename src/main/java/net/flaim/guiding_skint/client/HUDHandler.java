package net.flaim.guiding_skint.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.flaim.guiding_skint.GuidingSkintMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class HUDHandler implements ResourceManagerReloadListener, IGuiOverlay {
    public static final HUDHandler INSTANCE = new HUDHandler();

    public static final ResourceLocation SKINT_CLEAR = new ResourceLocation(GuidingSkintMod.MOD_ID, "textures/gui/guiding_skint_cleared.png");
    public static final ResourceLocation SKINT_CLEAR_SHADOW = new ResourceLocation(GuidingSkintMod.MOD_ID, "textures/gui/guiding_skint_cleared_shadow.png");

    private static final long DISPLAY_DURATION = 5000;
    private static final long FADE_DURATION = 2000;

    private static long startTime = -1;
    private static boolean runRender = false;
    private static float alpha = 1.0f;
    private static float scaleFactor = 0.5f;
    private static int screenWidth, screenHeight;

    public static void startTimer() {
        startTime = System.currentTimeMillis();
        runRender = true;
    }

    @Override
    public void render(ForgeGui forgeGui, GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        if (!runRender) return;

        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;

        if (elapsedTime > DISPLAY_DURATION + FADE_DURATION) {
            runRender = false;
            return;
        }

        if (elapsedTime < FADE_DURATION) {
            alpha = elapsedTime / (float) FADE_DURATION;
        } else if (elapsedTime > DISPLAY_DURATION) {
            long fadeOutElapsed = elapsedTime - DISPLAY_DURATION;
            alpha = 1.0f - (fadeOutElapsed / (float) FADE_DURATION);
        } else {
            alpha = 1.0f;
        }

        if (elapsedTime < FADE_DURATION) {
            scaleFactor = 0.5f + (elapsedTime / (float) FADE_DURATION) * 0.3f;
        } else {
            scaleFactor = 0.8f;
        }

        screenWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        screenHeight = Minecraft.getInstance().getWindow().getGuiScaledHeight();

        int[] scaledDimensions = getScaledImageSize(430, 39, scaleFactor);
        int[] shadow = getScaledImageSize(692, 120, 1);

        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, alpha);
        guiGraphics.blit(SKINT_CLEAR_SHADOW, (screenWidth - shadow[0]) / 2, (screenHeight - shadow[1]) / 2, 0, 0, shadow[0], shadow[1], shadow[0], shadow[1]);

        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, alpha);
        guiGraphics.blit(SKINT_CLEAR, (screenWidth - scaledDimensions[0]) / 2, (screenHeight - scaledDimensions[1]) / 2, 0, 0, scaledDimensions[0], scaledDimensions[1], scaledDimensions[0], scaledDimensions[1]);
    }

    private int[] getScaledImageSize(int originalWidth, int originalHeight, float reductionFactor) {
        int imageWidth = (int) (originalWidth * reductionFactor);
        int imageHeight = (int) (originalHeight * reductionFactor);

        float scaleFactor = (float) (4.0f / Minecraft.getInstance().getWindow().getGuiScale());
        imageWidth *= scaleFactor;
        imageHeight *= scaleFactor;

        if (imageWidth > screenWidth || imageHeight > screenHeight) {
            double widthRatio = (double) screenWidth / imageWidth;
            double heightRatio = (double) screenHeight / imageHeight;

            double scale = Math.min(widthRatio, heightRatio);

            imageWidth = (int) ((imageWidth * scale) * reductionFactor);
            imageHeight = (int) ((imageHeight * scale) * reductionFactor);
        }

        return new int[]{imageWidth, imageHeight};
    }

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {

    }
}
