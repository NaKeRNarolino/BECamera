package nakern.be_camera.mixin.client;

import nakern.be_camera.camera.CameraManager;
import net.minecraft.client.render.Camera;
import net.minecraft.util.math.Vec3d;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(Camera.class)
public class CameraMixin {
    @Shadow @Final private Vector3f horizontalPlane;

    @Shadow @Final private Vector3f verticalPlane;

    @Shadow @Final private Vector3f diagonalPlane;

    @Shadow private float pitch;

    @Shadow private float yaw;

    @Shadow @Final private Quaternionf rotation;

    @Inject(at = @At("HEAD"), method = "getPos", cancellable = true)
    void beCamera$getPosition(CallbackInfoReturnable<Vec3d> cir) {
        if (CameraManager.INSTANCE.isCameraChanged()) {
            cir.setReturnValue(
                    Objects.requireNonNull(CameraManager.INSTANCE.getPosition()));
        }
    }

    @Inject(at = @At("HEAD"), method = "setRotation", cancellable = true)
    void beCamera$setRotation(float yw, float pc, CallbackInfo ci) {
        if (CameraManager.INSTANCE.isCameraChanged()) {
            final var rot = CameraManager.INSTANCE.getRotation();
            var yaw = ((float) rot.y);
            var pitch = ((float) rot.x);
            this.pitch = pitch;
            this.yaw = yaw;
            this.rotation.rotationYXZ(-yaw * ((float)Math.PI / 180F), pitch * ((float)Math.PI / 180F), 0.0F);
            this.horizontalPlane.set(0.0F, 0.0F, 1.0F).rotate(this.rotation);
            this.verticalPlane.set(0.0F, 1.0F, 0.0F).rotate(this.rotation);
            this.diagonalPlane.set(1.0F, 0.0F, 0.0F).rotate(this.rotation);
            ci.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "isThirdPerson", cancellable = true)
    void beCamera$isThirdPerson(CallbackInfoReturnable<Boolean> cir) {
        if (CameraManager.INSTANCE.isCameraChanged()) {
            cir.setReturnValue(CameraManager.INSTANCE.shouldRenderPlayer());
        }
    }
}
