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
import org.jetbrains.annotations.NotNull;

public class HUDHandler implements ResourceManagerReloadListener, IGuiOverlay {
    public static final HUDHandler INSTANCE = new HUDHandler();

    public static final ResourceLocation SKINT_CLEAR = new ResourceLocation(GuidingSkintMod.MOD_ID, "textures/gui/guiding_skint_cleared.png");
    public static final ResourceLocation SKINT_CLEAR_LINE = new ResourceLocation(GuidingSkintMod.MOD_ID, "textures/gui/skint_line.png");
    public static final ResourceLocation SKINT_CLEAR_SHADOW = new ResourceLocation(GuidingSkintMod.MOD_ID, "textures/gui/guiding_skint_cleared_shadow.png");


    @Override
    public void onResourceManagerReload(@NotNull ResourceManager resourceManager) {

    }

    public static void showImageFor5Seconds() {
    }

    @Override
    public void render(ForgeGui forgeGui, GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {

    }

}
