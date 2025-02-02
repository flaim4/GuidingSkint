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

    public static final ResourceLocation GUI_ICONS_LOCATION = new ResourceLocation(GuidingSkintMod.MOD_ID, "textures/gui/guiding_skint_cleared.png");

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
            }
            else if (currentTime > displayEndTime - fadeDuration) {
                alpha = (displayEndTime - currentTime) / (float) fadeDuration;
            }

            PoseStack poseStack = guiGraphics.pose();
            poseStack.pushPose();

            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, alpha);

            int baseWidth = 418;
            int baseHeight = 25;

            RenderSystem.setShaderTexture(0, GUI_ICONS_LOCATION);

            int screenWidth = forgeGui.getMinecraft().getWindow().getGuiScaledWidth();
            int screenHeight = forgeGui.getMinecraft().getWindow().getGuiScaledHeight();

            int centerX = (screenWidth - baseWidth)/ 2;
            int centerY = (screenHeight - baseHeight) / 2;

            guiGraphics.blit(GUI_ICONS_LOCATION, centerX, centerY, 0, 0, baseWidth, baseHeight, baseWidth, baseHeight);

            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            RenderSystem.disableBlend();

            poseStack.popPose();
        }
    }



}
