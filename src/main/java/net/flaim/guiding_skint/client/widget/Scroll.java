package net.flaim.guiding_skint.client.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.Tesselator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraftforge.client.gui.widget.ScrollPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Scroll extends ScrollPanel {
    static Minecraft minecraft = Minecraft.getInstance();

    ArrayList<String> formattedCharSequence = new ArrayList<>(Arrays.asList(
            "Текст 1", "Текст 2", "Текст 3", "Текст 4", "Текст 5", "Текст 6", "Текст 7", "Текст 8"
    ));

    static Color color = new Color(0.0f, 0.0f, 0.0f, 0.6f);

    private double scrollDistance = 0;

    public Scroll(Minecraft client, int width, int height, int top, int left) {
        super(client, width, height, top, left);
    }

    @Override
    protected int getContentHeight() {
        return formattedCharSequence.size() * 50;
    }

    @Override
    protected void drawPanel(GuiGraphics guiGraphics, int i, int i1, Tesselator tesselator, int i2, int i3) {
        int yPos = i1 - (int) scrollDistance;

        for (String line : formattedCharSequence) {
            if (line != null) {
                RenderSystem.enableBlend();
                guiGraphics.drawString(minecraft.font, line, this.left + 6, yPos, 0xFFFFFF);
                RenderSystem.disableBlend();
            }
            yPos += 50;
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scroll) {
        if (scroll != 0) {
            scrollDistance += (scroll < 0 ? 20 : -20);
            applyScrollLimits();
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private void applyScrollLimits() {
        int maxScroll = getMaxScroll();
        if (scrollDistance < 0) {
            scrollDistance = 0;
        } else if (scrollDistance > maxScroll) {
            scrollDistance = maxScroll;
        }
    }

    private int getMaxScroll() {
        return getContentHeight() - (this.height - this.border);
    }

    @Override
    public NarrationPriority narrationPriority() {
        return null;
    }

    @Override
    public void updateNarration(NarrationElementOutput narrationElementOutput) {
    }
}
