package net.flaim.guiding_skint.client.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.Tesselator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraftforge.client.gui.widget.ScrollPanel;

public class Scroll extends ScrollPanel {

    Minecraft client;

    String[] arr = new String[]{"text1", "text2", "text3", "text4", "text5", "text2", "text3", "text4", "text5", "text2", "text3", "text4", "text5"};

    public Scroll(Minecraft client, int width, int height, int top, int left) {
        super(client, width, height, top, left);
        this.client = client;
    }

    @Override
    protected int getContentHeight() {
        return arr.length * 50;
    }

    @Override
    protected void drawPanel(GuiGraphics guiGraphics, int i, int i1, Tesselator tesselator, int i2, int i3) {
        int yPos = i1 - (int) scrollDistance;
        for (String line : arr) {
            RenderSystem.enableBlend();
            guiGraphics.drawString(this.client.font, line, this.left + 6, yPos, 0xFFFFFF);
            RenderSystem.disableBlend();
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
        return NarrationPriority.NONE;
    }

    @Override
    public void updateNarration(NarrationElementOutput narrationElementOutput) {

    }
}
