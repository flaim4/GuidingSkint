package net.flaim.guiding_skint.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.flaim.guiding_skint.GuidingSkintMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class HUDHandler implements ResourceManagerReloadListener, IGuiOverlay {

    public static final HUDHandler INSTANCE = new HUDHandler();

    public static final ResourceLocation GUI_ICONS_LOCATION = new ResourceLocation(GuidingSkintMod.MOD_ID, "textures/gui/guiding_skint_cleared.png");



    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {

    }

    @Override
    public void render(ForgeGui forgeGui, GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();

        int baseWidth = 418;
        int baseHeight = 25;

        RenderSystem.setShaderTexture(0, GUI_ICONS_LOCATION);

        guiGraphics.blit(GUI_ICONS_LOCATION, 10, 10, 0, 0, baseWidth, baseHeight, baseWidth, baseHeight);

        poseStack.popPose();
    }



}
