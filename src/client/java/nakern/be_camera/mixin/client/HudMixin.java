package nakern.be_camera.mixin.client;

import nakern.be_camera.camera.FadeDrawer;
import net.fabricmc.fabric.mixin.client.rendering.InGameHudMixin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class HudMixin {
    @Inject(method = "render", at = @At("TAIL"))
    private void beCamera$onRender(DrawContext context, float tickDelta, CallbackInfo ci) {
//        MinecraftClient client = MinecraftClient.getInstance();
//        if (client == null || client.world == null) return;

        // Create a DrawContext
//        DrawContext context = new DrawContext(client, client.getBufferBuilders().getEntityVertexConsumers());

        FadeDrawer.INSTANCE.draw(context);
        // Call your render method with DrawContextn);

    }
}
