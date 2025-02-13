package net.flaim.guiding_skint.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class HUDHandler extends Screen {


    public HUDHandler() {
        super(Component.empty());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
//        guiGraphics.drawString(minecraft.font, "Skint", (width / 2) - minecraft.font.width(Component.translatable("Skint")) / 2, (height / 2) - (minecraft.font.lineHeight / 2) - 20, 0xFFFFFF);
        RenderUI.render(guiGraphics);
    }

//    private int[] getScaledImageSize(int originalWidth, int originalHeight, float reductionFactor) {
//        int imageWidth = (int) (originalWidth * reductionFactor);
//        int imageHeight = (int) (originalHeight * reductionFactor);
//
//        float scaleFactor = (float) (4.0f / Minecraft.getInstance().getWindow().getGuiScale());
//        imageWidth *= scaleFactor;
//        imageHeight *= scaleFactor;
//
//        if (imageWidth > screenWidth || imageHeight > screenHeight) {
//            double widthRatio = (double) screenWidth / imageWidth;
//            double heightRatio = (double) screenHeight / imageHeight;
//
//            double scale = Math.min(widthRatio, heightRatio);
//
//            imageWidth = (int) ((imageWidth * scale) * reductionFactor);
//            imageHeight = (int) ((imageHeight * scale) * reductionFactor);
//        }
//
//        return new int[]{imageWidth, imageHeight};
//    }

}
