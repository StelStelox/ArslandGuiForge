package ru.arsland_minecraft;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;

public class LogoArslandRender {
    public static final ResourceLocation MINECRAFT_LOGO = new ResourceLocation("arslandgui:textures/gui/title/minecraft_title.png");
    public static final ResourceLocation EASTER_EGG_LOGO = new ResourceLocation("arslandgui:textures/gui/title/minecraft_title.png");
    public static final ResourceLocation MINECRAFT_EDITION = new ResourceLocation("textures/gui/title/edition.png");
    public static final int LOGO_WIDTH = 256;
    public static final int LOGO_HEIGHT = 44;
    private static final int LOGO_TEXTURE_WIDTH = 256;
    private static final int LOGO_TEXTURE_HEIGHT = 64;
    private static final int EDITION_WIDTH = 128;
    private static final int EDITION_HEIGHT = 14;
    private static final int EDITION_TEXTURE_WIDTH = 128;
    private static final int EDITION_TEXTURE_HEIGHT = 16;
    public static final int DEFAULT_HEIGHT_OFFSET = 30;
    private static final int EDITION_LOGO_OVERLAP = 7;
    private final boolean showEasterEgg = (double) RandomSource.create().nextFloat() < 1.0E-4;
    private final boolean keepLogoThroughFade;

    public LogoArslandRender(boolean pKeepLogoThroughFade) {
        this.keepLogoThroughFade = pKeepLogoThroughFade;
    }

    public void renderLogo(GuiGraphics pGuiGraphics, int pScreenWidth, float pTransparency) {
        this.renderLogo(pGuiGraphics, pScreenWidth, pTransparency, 30);
    }

    public void renderLogo(GuiGraphics pGuiGraphics, int pScreenWidth, float pTransparency, int pHeight) {
        pGuiGraphics.setColor(1.0F, 1.0F, 1.0F, this.keepLogoThroughFade ? 1.0F : pTransparency);
        int $$4 = pScreenWidth / 2 - 128;
        pGuiGraphics.blit(this.showEasterEgg ? EASTER_EGG_LOGO : MINECRAFT_LOGO, $$4, pHeight, 0.0F, 0.0F, 256, 44, 256, 64);
        int $$5 = pScreenWidth / 2 - 64;
        int $$6 = pHeight + 44 - 7;
        pGuiGraphics.blit(MINECRAFT_EDITION, $$5, $$6, 0.0F, 0.0F, 128, 14, 128, 16);
        pGuiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

}
