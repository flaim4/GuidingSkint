package net.flaim.guiding_skint.client;

import com.mojang.blaze3d.vertex.Tesselator;
import net.flaim.guiding_skint.client.widget.Scroll;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraftforge.client.gui.widget.ScrollPanel;

import java.awt.*;

public class RenderUI {
    static Color color = new Color(0.0f, 0.0f, 0.0f, 0.6f);

    static Minecraft minecraft = Minecraft.getInstance();

    static Scroll scroll;

    public static void render(GuiGraphics guiGraphics) {
        int windowWidth = minecraft.getWindow().getGuiScaledWidth();
        int windowHeight = minecraft.getWindow().getGuiScaledHeight();

        int scrollWidth = 300;
        int scrollHeight = 300;
        int scrollX = (windowWidth - scrollWidth) / 2;
        int scrollY = (windowHeight - scrollHeight) / 2;

        scroll = new Scroll(minecraft, scrollWidth, scrollHeight, scrollY, scrollX);

        scroll.render(guiGraphics, 0, 0, 0);
    }

}