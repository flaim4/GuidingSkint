package net.flaim.guiding_skint.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class HUDHandler implements ResourceManagerReloadListener, IGuiOverlay {

    public static final HUDHandler INSTANCE = new HUDHandler();

    public static final ResourceLocation GUI_ICONS_LOCATION = new ResourceLocation("textures/gui/guiding_skint_cleared");

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {

    }

    @Override
    public void render(ForgeGui forgeGui, GuiGraphics guiGraphics, float v, int i, int i1) {
        guiGraphics.blit(GUI_ICONS_LOCATION, 100, 100, 0, 139, 100, 28);
    }
}
