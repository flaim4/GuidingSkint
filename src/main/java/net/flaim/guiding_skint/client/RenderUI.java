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

    static int windowWidth;
    static int windowHeight;
    static Scroll scroll = new Scroll(minecraft, 10, 300, 0, 100);

    public static void render(GuiGraphics guiGraphics) {
        windowWidth = minecraft.getWindow().getGuiScaledWidth();
        windowHeight = minecraft.getWindow().getGuiScaledHeight();

//        guiGraphics.fill(
//                (windowWidth - 300) / 2,
//                (windowHeight - 300) / 2,
//                (windowWidth - 300) / 2 + 300,
//                (windowHeight - 300) / 2 + 300,
//                0,
//                color.hashCode()
//        );
        scroll.render(guiGraphics, 0, 50, 20);
    }

}