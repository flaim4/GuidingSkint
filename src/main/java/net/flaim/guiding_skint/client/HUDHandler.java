package net.flaim.guiding_skint.client;

import com.mojang.blaze3d.platform.Window;
import net.flaim.guiding_skint.GuidingSkintMod;
import net.flaim.guiding_skint.client.widgets.ScrollBarButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;


public class HUDHandler implements ResourceManagerReloadListener, IGuiOverlay {
    public static final HUDHandler INSTANCE = new HUDHandler();

    private static long startTime = -1;
    private static boolean runRender = false;
    private static int screenWidth, screenHeight;
    private static Window window = Minecraft.getInstance().getWindow();
    public static int windowHeight;
    public static int windowWidth;

    private ScrollBarButton scrollBarButton;


    public static void startTimer() {
        startTime = System.currentTimeMillis();
        runRender = true;
    }

    private static final ResourceLocation SKINT_CLEAR_SHADOW = new ResourceLocation(GuidingSkintMod.MOD_ID, "textures/gui/skint_ui.png");

    @Override
    public void render(ForgeGui forgeGui, GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        windowWidth = window.getGuiScaledWidth();
        windowHeight = window.getGuiScaledHeight();
        RenderUI.render(guiGraphics);
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
