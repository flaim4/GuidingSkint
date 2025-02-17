package net.flaim.guiding_skint.client.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.Tesselator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.client.gui.widget.ScrollPanel;

public class Scroll extends ScrollPanel {

    Minecraft client;

    static ListTag listTag;

    public static void handleGuidingSkint(ListTag receivedListTag) {
        listTag = receivedListTag;
    }

    public Scroll(Minecraft client, int width, int height, int top, int left) {
        super(client, width, height, top, left);
        this.client = client;
    }

    @Override
    protected int getContentHeight() {
        if (listTag == null) {
            return 0;
        }
        return listTag.size() * 50;
    }

    @Override
    protected void drawPanel(GuiGraphics guiGraphics, int i, int i1, Tesselator tesselator, int i2, int i3) {
        int yPos = i1 - (int) scrollDistance;
        if (listTag == null) {
            return;
        }

        guiGraphics.fill(this.left, this.top, this.left + this.width, this.top + this.height, 0x55000000);

        for (Tag line : listTag) {
            if (yPos + 50 >= this.top && yPos <= this.top + this.height) {
                RenderSystem.enableBlend();
                String lineText = line.getAsString();
                guiGraphics.drawString(this.client.font, lineText, this.left + 6, yPos, 0xFFFFFF);
                RenderSystem.disableBlend();
            }
            yPos += 50;
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(guiGraphics, mouseX, mouseY, partialTicks);

        int contentHeight = getContentHeight();
        if (contentHeight > this.height) {
            int scrollbarHeight = Math.max(20, (int) ((float) this.height / contentHeight * this.height));
            int scrollbarPos = (int) ((float) scrollDistance / getMaxScroll() * (this.height - scrollbarHeight));

            guiGraphics.fill(this.left + this.width - 6, this.top + scrollbarPos, this.left + this.width - 2, this.top + scrollbarPos + scrollbarHeight, 0xFFAAAAAA);
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scroll) {
        scrollDistance += (scroll < 0 ? 30 : -30);
        applyScrollLimits();
        return true;
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
        int contentHeight = getContentHeight();
        return Math.max(0, contentHeight - this.height);
    }

    @Override
    public NarrationPriority narrationPriority() {
        return NarrationPriority.NONE;
    }

    @Override
    public void updateNarration(NarrationElementOutput narrationElementOutput) {
    }
}