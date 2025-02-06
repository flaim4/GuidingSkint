package net.flaim.guiding_skint.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.flaim.guiding_skint.GuidingSkintMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import org.jetbrains.annotations.NotNull;

public class HUDHandler implements ResourceManagerReloadListener, IGuiOverlay {
    public static final HUDHandler INSTANCE = new HUDHandler();

    public static final ResourceLocation SKINT_CLEAR = new ResourceLocation(GuidingSkintMod.MOD_ID, "textures/gui/guiding_skint_cleared.png");
    public static final ResourceLocation SKINT_CLEAR_LINE = new ResourceLocation(GuidingSkintMod.MOD_ID, "textures/gui/skint_line.png");
    public static final ResourceLocation SKINT_CLEAR_SHADOW = new ResourceLocation(GuidingSkintMod.MOD_ID, "textures/gui/guiding_skint_cleared_shadow.png");


    @Override
    public void onResourceManagerReload(@NotNull ResourceManager resourceManager) {

    }

    public static void showImageFor5Seconds() {
    }

    @Override
    public void render(ForgeGui forgeGui, GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        int screenWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int screenHeight = Minecraft.getInstance().getWindow().getGuiScaledHeight();

        int[] scaledDimensions = getScaledImageSize(430, 39, 0.8);
        int imageWidth = scaledDimensions[0];
        int imageHeight = scaledDimensions[1];

        int[] shadow = getScaledImageSize(692, 120, 1);
        int imageShadowWidth = shadow[0];
        int imageShadowHeight = shadow[1];

        guiGraphics.blit(SKINT_CLEAR_SHADOW, (int) (screenWidth - imageShadowWidth) / 2, (int) (screenHeight - imageShadowHeight) / 2, 0, 0, imageShadowWidth, imageShadowHeight, imageShadowWidth, imageShadowHeight);
        guiGraphics.blit(SKINT_CLEAR, (int) (screenWidth - imageWidth) / 2, (int) (screenHeight - imageHeight) / 2, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);
    }

    public int[] getScaledImageSize(int originalWidth, int originalHeight, double reductionFactor) {
        int screenWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int screenHeight = Minecraft.getInstance().getWindow().getGuiScaledHeight();

        int imageWidth = (int) (originalWidth * reductionFactor);
        int imageHeight = (int) (originalHeight * reductionFactor);

        double scaleFactor = 4.0 / Minecraft.getInstance().getWindow().getGuiScale();
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

}
