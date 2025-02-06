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
        double guiScale = Minecraft.getInstance().getWindow().getGuiScale();

        int originalWidth = 430;
        int originalHeight = 39;

        double reductionFactor = 0.8;
        int imageWidth = (int) (originalWidth * reductionFactor);
        int imageHeight = (int) (originalHeight * reductionFactor);

        double scaleFactor = 4.0 / guiScale;
        imageWidth *= scaleFactor;
        imageHeight *= scaleFactor;

        if (imageWidth > screenWidth || imageHeight > screenHeight) {
            double widthRatio = (double) screenWidth / imageWidth;
            double heightRatio = (double) screenHeight / imageHeight;

            double scale = Math.min(widthRatio, heightRatio);

            imageWidth = (int) (imageWidth * scale);
            imageHeight = (int) (imageHeight * scale);
        }

        int x = (screenWidth - imageWidth) / 2;
        int y = (screenHeight - imageHeight) / 2;


        guiGraphics.blit(SKINT_CLEAR, x, y, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);
    }






}
