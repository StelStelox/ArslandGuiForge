package ru.arsland_minecraft;

import com.mojang.authlib.minecraft.BanDetails;
import net.minecraft.Util;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.multiplayer.SafetyScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.LogoRenderer;
import net.minecraft.util.Mth;
import net.minecraftforge.client.gui.ModListScreen;

import net.minecraftforge.client.gui.TitleScreenModUpdateIndicator;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class MainMenu extends Screen {

    public static final Component COPYRIGHT_TEXT = Component.literal("By ArslandTeam");
    private final LogoRenderer logoRenderer;
    private final boolean fading;
    private long fadeInStart;
    private TitleScreenModUpdateIndicator modUpdateNotification;

    public MainMenu(LogoRenderer logoRenderer, boolean fading) {
        super(Component.translatable("narrator.screen.title"));
        this.logoRenderer = logoRenderer != null ? logoRenderer : new LogoRenderer(false);
        this.fading = fading;
    }

    @Override
    protected void init() {
        int i = this.font.width(COPYRIGHT_TEXT);
        int j = this.width - i - 2;
        int k = 24;
        int l = this.height / 4 + 48;

        this.createNormalMenuOptions(l, 24);
        Button modButton = this.addRenderableWidget(Button.builder(Component.translatable("fml.menu.mods"), (p_280830_) -> this.minecraft.setScreen(new ModListScreen(this))).bounds(this.width / 2 - 100, l + 48, 200, 20).build());
        this.modUpdateNotification = new TitleScreenModUpdateIndicator(modButton);
        this.modUpdateNotification.resize(this.minecraft, this.width, this.height);
        this.modUpdateNotification.init();
        this.addRenderableWidget(new ImageButton(this.width / 2 - 124, l + 72 + 12, 20, 20, 0, 106, 20, Button.WIDGETS_LOCATION, 256, 256, (p_280838_) -> this.minecraft.setScreen(new LanguageSelectScreen(this, this.minecraft.options, this.minecraft.getLanguageManager())), Component.translatable("narrator.button.language")));
        this.addRenderableWidget(Button.builder(Component.translatable("menu.options"), (p_280831_) -> this.minecraft.setScreen(new OptionsScreen(this, this.minecraft.options))).bounds(this.width / 2 - 100, l + 72 + 12, 98, 20).build());
        this.addRenderableWidget(Button.builder(Component.translatable("menu.quit"), (p_280835_) -> this.minecraft.stop()).bounds(this.width / 2 + 2, l + 72 + 12, 98, 20).build());
        this.addRenderableWidget(new ImageButton(this.width / 2 + 104, l + 72 + 12, 20, 20, 0, 0, 20, Button.ACCESSIBILITY_TEXTURE, 32, 64, (p_280834_) -> this.minecraft.setScreen(new AccessibilityOptionsScreen(this, this.minecraft.options)), Component.translatable("narrator.button.accessibility")));
        this.addRenderableWidget(new PlainTextButton(j, this.height - 10, i, 10, COPYRIGHT_TEXT, (p_280834_) -> this.minecraft.setScreen(new CreditsAndAttributionScreen(this)), this.font));
        this.minecraft.setConnectedToRealms(false);
    }

    private void createNormalMenuOptions(int pY, int pRowHeight) {
        Component component = this.getMultiplayerDisabledReason();
        boolean flag = component == null;
        this.addRenderableWidget(Button.builder(Component.translatable("menu.multiplayer"), (p_210872_) -> {Screen screen = (Screen)(this.minecraft.options.skipMultiplayerWarning ? new JoinMultiplayerScreen(this) : new SafetyScreen(this));this.minecraft.setScreen(screen);}).bounds(this.width / 2 - 100, pY, 200, 20).build()).active = flag;
        this.addRenderableWidget(Button.builder(Component.translatable("menu.singleplayer"), (button) -> this.minecraft.setScreen(new SelectWorldScreen(this))).bounds(this.width / 2 - 100, pY + pRowHeight, 200, 20).build());
    }

    @Nullable
    private Component getMultiplayerDisabledReason() {
        if (this.minecraft.allowsMultiplayer()) {
            return null;
        } else {
            BanDetails banDetails = this.minecraft.multiplayerBan();
            if (banDetails != null) {
                return banDetails.expires() != null
                        ? Component.translatable("title.multiplayer.disabled.banned.temporary")
                        : Component.translatable("title.multiplayer.disabled.banned.permanent");
            } else {
                return Component.translatable("title.multiplayer.disabled");
            }
        }
    }

    @Override
    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        // Отрисовываем фон
        this.renderBackground(pGuiGraphics);

        // Отрисовка логотипа
        float fadeFactor = this.fading ? (float)(Util.getMillis() - this.fadeInStart) / 1000.0F : 1.0F;
        float opacity = this.fading ? Mth.clamp(fadeFactor - 1.0F, 0.0F, 1.0F) : 1.0F;
        this.logoRenderer.renderLogo(pGuiGraphics, this.width, opacity);

        // Отрисовка остальных элементов
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.modUpdateNotification.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);

    }
}

