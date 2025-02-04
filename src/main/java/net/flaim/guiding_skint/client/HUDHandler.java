package net.flaim.guiding_skint.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.flaim.guiding_skint.GuidingSkintMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class HUDHandler implements ResourceManagerReloadListener, IGuiOverlay {

    public static final HUDHandler INSTANCE = new HUDHandler();

    public static final ResourceLocation SKINT_CLEAR = new ResourceLocation(GuidingSkintMod.MOD_ID, "textures/gui/guiding_skint_cleared.png");
    public static final ResourceLocation SKINT_CLEAR_LINE = new ResourceLocation(GuidingSkintMod.MOD_ID, "textures/gui/skint_line.png");
    public static final ResourceLocation SKINT_CLEAR_SHADOW = new ResourceLocation(GuidingSkintMod.MOD_ID, "textures/gui/guiding_skint_cleared_shadow.png");


    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {

    }

    private static long showStartTime = 0;
    private static long displayEndTime = 0;
    private static final long totalDisplayTime = 5000;
    private static  final long fadeDuration = 1000;

    public static void showImageFor5Seconds() {
        showStartTime = System.currentTimeMillis();
        displayEndTime = showStartTime + totalDisplayTime;
    }

    @Override
    public void render(ForgeGui forgeGui, GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        long currentTime = System.currentTimeMillis();
        if (currentTime < displayEndTime) {
            float alpha = 1.0f;
            long elapsed = currentTime - showStartTime;

            if (elapsed < fadeDuration) {
                alpha = elapsed / (float) fadeDuration;
            } else if (currentTime > displayEndTime - fadeDuration) {
                alpha = (displayEndTime - currentTime) / (float) fadeDuration;
            }

            float progress = elapsed / (float) totalDisplayTime;
            float scale = 1.0f + 0.05f * progress;

            PoseStack poseStack = guiGraphics.pose();
            poseStack.pushPose();

            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, alpha);

            int screenWidth = forgeGui.getMinecraft().getWindow().getGuiScaledWidth();
            int screenHeight = forgeGui.getMinecraft().getWindow().getGuiScaledHeight();

            int shadowWidth = 506, shadowHeight = 112;
            int lineWidth = 437, lineHeight = 26;
            int baseWidth = 418, baseHeight = 25;

            int shadowX = (int) ((screenWidth - shadowWidth * scale) / 2);
            int shadowY = (int) ((screenHeight - shadowHeight * scale) / 2);
            int baseX = (int) ((screenWidth - baseWidth * scale) / 2);
            int baseY = (int) ((screenHeight - baseHeight * scale) / 2);

            poseStack.scale(scale, scale, 1.0f);

            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, alpha);
            RenderSystem.setShaderTexture(0, SKINT_CLEAR_SHADOW);
            guiGraphics.blit(SKINT_CLEAR_SHADOW, shadowX, shadowY, 0, 0, shadowWidth, shadowHeight, shadowWidth, shadowHeight);

            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, alpha);
            RenderSystem.setShaderTexture(0, SKINT_CLEAR_LINE);
            guiGraphics.blit(SKINT_CLEAR_LINE, (int) ((screenWidth - lineWidth * scale) / 2), (int) ((screenHeight - lineHeight * scale) / 2), 0, 0, lineWidth, lineHeight, lineWidth, lineHeight);

            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, alpha);
            RenderSystem.setShaderTexture(0, SKINT_CLEAR);
            guiGraphics.blit(SKINT_CLEAR, baseX, baseY, 0, 0, baseWidth, baseHeight, baseWidth, baseHeight);

            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            RenderSystem.disableBlend();

            poseStack.popPose();
        }
    }








}
