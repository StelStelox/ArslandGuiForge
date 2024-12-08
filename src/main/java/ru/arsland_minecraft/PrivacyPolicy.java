package ru.arsland_minecraft;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.WinScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PrivacyPolicy extends Screen {
    private static final int BUTTON_SPACING = 8;
    private static final int BUTTON_WIDTH = 210;
    private static final Component TITLE = Component.translatable("privacy_policy.button.title");
    private static final Component RULE = Component.translatable("privacy_policy.button.rule");
    private static final Component DOCUMENTATION = Component.translatable("privacy_police.button.documentation");
    private static final Component CONFIDENTIALITY = Component.translatable("privacy_policy.button.confidentiality");
    private final Screen lastScreen;
    private final HeaderAndFooterLayout layout = new HeaderAndFooterLayout(this);

    public PrivacyPolicy(Screen pLastScreen) {
        super(TITLE);
        this.lastScreen = pLastScreen;
    }
    protected void init() {
        this.layout.addToHeader(new StringWidget(this.getTitle(), this.font));
        GridLayout $$0 = ((GridLayout)this.layout.addToContents(new GridLayout())).spacing(8);
        $$0.defaultCellSetting().alignHorizontallyCenter();
        GridLayout.RowHelper $$1 = $$0.createRowHelper(1);
        $$1.addChild(Button.builder(RULE, ConfirmLinkScreen.confirmLink("https://docs.arsland-minecraft.ru/basic/rule_minecraft", this, true)).width(210).build());
        $$1.addChild(Button.builder(DOCUMENTATION, ConfirmLinkScreen.confirmLink("https://docs.arsland-minecraft.ru", this, true)).width(210).build());
        $$1.addChild(Button.builder(CONFIDENTIALITY, ConfirmLinkScreen.confirmLink("https://docs.arsland-minecraft.ru/basic/confidentiality", this, true)).width(210).build());
        this.layout.addToFooter(Button.builder(CommonComponents.GUI_DONE, (p_276311_) -> this.onClose()).build());
        this.layout.arrangeElements();
        this.layout.visitWidgets(this::addRenderableWidget);
    }
    private void openCreditsScreen() {
        Objects.requireNonNull(this.minecraft).setScreen(new WinScreen(false, () -> this.minecraft.setScreen(this)));
    }
    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }
}
