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

    private static final ResourceLocation SKINT_CLEAR = new ResourceLocation(GuidingSkintMod.MOD_ID, "textures/gui/guiding_skint_cleared.png");
    private static final ResourceLocation SKINT_CLEAR_LINE = new ResourceLocation(GuidingSkintMod.MOD_ID, "textures/gui/skint_line.png");
    private static final ResourceLocation SKINT_CLEAR_SHADOW = new ResourceLocation(GuidingSkintMod.MOD_ID, "textures/gui/guiding_skint_cleared_shadow.png");

    private static final long DISPLAY_DURATION = 5000;
    private static final long FADE_DURATION = 3000;
    private static final long LINE_SCALE_DURATION = 3000;
    private static final long CLEAR_SCALE_DELAY = 0;
    private static final long CLEAR_SCALE_DURATION = 3000;

    private static long startTime = -1;
    private static boolean runRender = false;
    private static int screenWidth, screenHeight;

    public static void startTimer() {
        startTime = System.currentTimeMillis();
        runRender = true;
    }

    @Override
    public void render(ForgeGui forgeGui, GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        if (!runRender || startTime == -1) return;

        long elapsedTime = System.currentTimeMillis() - startTime;
        if (elapsedTime > DISPLAY_DURATION + FADE_DURATION) {
            runRender = false;
            return;
        }

        float alpha = Math.min(1.0f, Math.max(0.0f, elapsedTime < FADE_DURATION ? elapsedTime / (float) FADE_DURATION : 1.0f - (Math.max(0, elapsedTime - DISPLAY_DURATION) / (float) FADE_DURATION)));

        float scaleFactorLine = elapsedTime < LINE_SCALE_DURATION ? 0.5f + (elapsedTime / (float) LINE_SCALE_DURATION) * 0.4f : 0.9f;
        float scaleFactorClear = 0.5f;
        if (elapsedTime > CLEAR_SCALE_DELAY) {
            long adjustedTime = elapsedTime - CLEAR_SCALE_DELAY;
            scaleFactorClear = 0.5f + Math.min(adjustedTime / (float) CLEAR_SCALE_DURATION, 1.0f) * 0.3f;
        }

        screenWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        screenHeight = Minecraft.getInstance().getWindow().getGuiScaledHeight();

        int[] scaledShadow = getScaledImageSize(692, 120, 1.0f);
        int[] scaledLine = getScaledImageSize(418, 25, scaleFactorLine);
        int[] scaledClear = getScaledImageSize(430, 39, scaleFactorClear);

        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, alpha);
        guiGraphics.blit(SKINT_CLEAR_SHADOW, (screenWidth - scaledShadow[0]) / 2, (screenHeight - scaledShadow[1]) / 2, 0, 0, scaledShadow[0], scaledShadow[1], scaledShadow[0], scaledShadow[1]);
        guiGraphics.blit(SKINT_CLEAR_LINE, (screenWidth - scaledLine[0]) / 2, (screenHeight - scaledLine[1]) / 2, 0, 0, scaledLine[0], scaledLine[1], scaledLine[0], scaledLine[1]);
        guiGraphics.blit(SKINT_CLEAR, (screenWidth - scaledClear[0]) / 2, (screenHeight - scaledClear[1]) / 2, 0, 0, scaledClear[0], scaledClear[1], scaledClear[0], scaledClear[1]);
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
