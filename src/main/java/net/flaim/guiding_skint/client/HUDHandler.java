package net.flaim.guiding_skint.client;

import net.flaim.guiding_skint.client.widget.Scroll;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class HUDHandler extends Screen {

    Minecraft minecraft = Minecraft.getInstance();
    int widthWindow;
    int heightWindow;

    public HUDHandler() {
        super(Component.empty());
    }

    @Override
    public void init() {
        widthWindow = minecraft.getWindow().getGuiScaledWidth();
        heightWindow = minecraft.getWindow().getGuiScaledHeight();
        Scroll scroll = new Scroll(minecraft, 300, 300, (heightWindow / 2) - 150, (widthWindow / 2) - 150);
        this.addRenderableWidget(scroll);
    }


}
