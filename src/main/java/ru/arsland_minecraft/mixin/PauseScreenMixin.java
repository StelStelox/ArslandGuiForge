package ru.arsland_minecraft.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.GenericDirtMessageScreen;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.arsland_minecraft.MainMenu;
@Mixin(PauseScreen.class)
public abstract class PauseScreenMixin {
    @Final
    @Shadow
    private static Component SAVING_LEVEL;

    @Inject(method = "onDisconnect", at = @At("HEAD"), cancellable = true)
    private void onDisconnect(CallbackInfo ci) {
        PauseScreen pauseScreen = (PauseScreen) (Object) this;
        Minecraft minecraft = pauseScreen.getMinecraft();
        boolean flag = pauseScreen.getMinecraft().isLocalServer();
        minecraft.level.disconnect();
        if (flag) {
            minecraft.clearLevel(new GenericDirtMessageScreen(SAVING_LEVEL));
        } else {
            minecraft.clearLevel();
        }

        TitleScreen titlescreen = new TitleScreen();
        if (flag) {
            minecraft.setScreen(new MainMenu(null, false));
        } else {
            minecraft.setScreen(new MainMenu(null, false));
        }

        ci.cancel();
    }
}