package net.flaim.guiding_skint.client.widgets;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.event.RenderTooltipEvent;

import java.awt.*;

public class ScrollBarButton extends Button {
    public ScrollBarButton(int buttonId, int x, int y, int width, int height, int visibleHeight, int contentHeight) {
        super(new Builder(Component.empty(), button -> {}).pos(x, y).size(width, height));
    }

    public int getBottom() {
        return getY() + height;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            guiGraphics.setColor(1, 1, 1, 0.5f);
            guiGraphics.fill(getX(), getY(), getX() + width, getBottom(), new Color(0, 0, 0, 0.5F).hashCode());
            guiGraphics.setColor(1, 1, 1, 1);
        }
    }


}
