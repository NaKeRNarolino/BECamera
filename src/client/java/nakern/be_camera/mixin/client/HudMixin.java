package nakern.be_camera.mixin.client;

import nakern.be_camera.camera.*;
import nakern.be_camera.easings.Easings;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Colors;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class HudMixin {
    @Inject(method = "render", at = @At("TAIL"))
    private void beCamera$onRender(DrawContext context, float tickDelta, CallbackInfo ci) {
        FadeDrawer.INSTANCE.draw(context);
    }
}
