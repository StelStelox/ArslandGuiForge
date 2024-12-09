package ru.arsland_minecraft.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.LogoRenderer;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.arsland_minecraft.LogoArslandRender;
import ru.arsland_minecraft.MainMenu;

@Mixin(Minecraft.class)
public class TitleScreenMixin {

    @Inject(method = "setScreen", at = @At("INVOKE"), cancellable = true)
    private void onSetScreen(Screen screen, CallbackInfo ci) {
        if (screen instanceof TitleScreen) {
            Minecraft minecraft = (Minecraft) (Object) this;
            screen = new MainMenu(new LogoArslandRender(false), false);
            minecraft.setScreen(screen);
            ci.cancel();
        }
    }
}
