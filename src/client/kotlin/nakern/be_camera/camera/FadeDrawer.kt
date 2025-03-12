package nakern.be_camera.camera

import com.mojang.blaze3d.systems.RenderSystem
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.render.BufferRenderer
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.Tessellator
import net.minecraft.client.render.VertexFormat
import net.minecraft.client.render.VertexFormats
import net.minecraft.util.math.ColorHelper

object FadeDrawer {
//    fun initialize() {
//        HudRenderCallback.EVENT.register {
//            ctx, _ -> draw(ctx)
//        }
//    }

    fun draw(context: DrawContext) {
        val baseColor = CameraManager.getFadeInfo().first;
        val alpha = CameraManager.getFadeInfo().second;

        if (alpha == 0f) {
            return;
        }

        val color = ColorHelper.Argb.getArgb(
            (alpha * ColorHelper.Argb.getAlpha(baseColor)).toInt(),
            ColorHelper.Argb.getRed(baseColor),
            ColorHelper.Argb.getBlue(baseColor),
            ColorHelper.Argb.getGreen(baseColor),
        );

        RenderSystem.enableBlend();

        context.fill(
            RenderLayer.getGuiOverlay(),
            0, 0,
            context.scaledWindowWidth, context.scaledWindowHeight,
            color
        )

        RenderSystem.disableBlend();
    }

    fun draw() {
        val baseColor = CameraManager.getFadeInfo().first;
        val alpha = CameraManager.getFadeInfo().second;

        if (alpha == 0f) {
            return;
        }

        val color = ColorHelper.Argb.getArgb(
            (alpha * 255).toInt(),
            ColorHelper.Argb.getRed(baseColor),
            ColorHelper.Argb.getBlue(baseColor),
            ColorHelper.Argb.getGreen(baseColor),
        );

        RenderSystem.enableBlend();

//        context.fill(
//            0, 0,
//            context.scaledWindowWidth, context.scaledWindowHeight,
//            color
//        )

        val tess = Tessellator.getInstance();
        val buf = tess.buffer;

        buf.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);

        val x1 = 0.0;
//        val x2 = MinecraftClient.getInstance().window.scaledWidth.toDouble();
        val x2 = 100.0;
        val y1 = 0.0;
//        val y2 = MinecraftClient.getInstance().window.scaledHeight.toDouble();
        val y2 = 100.0;

        buf
            .vertex(x1, y1, 150.0)
            .color(color);
        buf
            .vertex(x1, y2, 150.0)
            .color(color);
        buf
            .vertex(x2, y2, 150.0)
            .color(color);
        buf
            .vertex(x2, y1, 150.0)
            .color(color);


        RenderSystem.setShader(GameRenderer::getPositionColorProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
//        buf.end();

//        tess.draw();


        RenderSystem.disableDepthTest();
        BufferRenderer.drawWithGlobalProgram(buf.end())
        RenderSystem.enableDepthTest();
        println("Try draw fade")
//        MinecraftClient.getInstance().gameRenderer
//        DrawContext
    }
}