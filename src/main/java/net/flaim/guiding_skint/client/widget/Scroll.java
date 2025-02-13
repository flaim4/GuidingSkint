package net.flaim.guiding_skint.client.widget;

import com.mojang.blaze3d.vertex.Tesselator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraftforge.client.gui.widget.ScrollPanel;

import java.awt.*;

public class Scroll extends ScrollPanel {

    static Minecraft minecraft = Minecraft.getInstance();

    static int windowWidth;
    static int windowHeight;

    static Color color = new Color(0.0f, 0.0f, 0.0f, 0.6f);

    public Scroll(Minecraft client, int width, int height, int top, int left) {
        super(client, width, height, top, left);
    }

    @Override
    protected int getContentHeight() {
        return 0;
    }

    @Override
    protected void drawPanel(GuiGraphics guiGraphics, int i, int i1, Tesselator tesselator, int i2, int i3) {
        guiGraphics.fill(
                (windowWidth - 300) / 2,
                (windowHeight - 300) / 2,
                (windowWidth - 300) / 2 + 300,
                (windowHeight - 300) / 2 + 300,
                0,
                color.hashCode()
        );
    }

    @Override
    public NarrationPriority narrationPriority() {
        return null;
    }

    @Override
    public void updateNarration(NarrationElementOutput narrationElementOutput) {

    }
}
