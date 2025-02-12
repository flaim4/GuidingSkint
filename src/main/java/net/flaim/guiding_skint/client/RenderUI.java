package net.flaim.guiding_skint.client;

import net.minecraft.client.gui.GuiGraphics;

import java.awt.*;

public class RenderUI {
    static Color color = new Color(0.0f, 0.0f, 0.0f, 0.6f);

    public static void render(GuiGraphics guiGraphics) {
        guiGraphics.fill(
                (HUDHandler.windowWidth - 300) / 2,
                (HUDHandler.windowHeight - 300) / 2,
                (HUDHandler.windowWidth - 300) / 2 + 300,
                (HUDHandler.windowHeight - 300) / 2 + 300,
                0,
                color.hashCode()
        );
    }

}