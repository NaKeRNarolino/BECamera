package nakern.be_camera.camera

import kotlinx.datetime.Clock
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.GameRenderer
import net.minecraft.util.math.Vec2f
import net.minecraft.util.math.Vec3d
import org.joml.Vector2d
import kotlin.math.*

object CameraManager {
    private var active = false;
    private var cameraData: CameraData? = null;
    private var oldData: CameraData? = null;
    private var lastMs = Clock.System.now().toEpochMilliseconds();
    private val client = MinecraftClient.getInstance();
    private var lastPlayerLocation: Vec3d? = null;
    private var location: Vec3d? = null;
    private var lastPlayerRotation: Vec2f? = null;
    private var lastMsFade = Clock.System.now().toEpochMilliseconds();
    private var fadeData: CameraFadeOptions? = null;

//
//    companion object {
//        private var manager: CameraManager? = null;
//
//        fun instance(): CameraManager {
//            if (CameraManager.manager == null) {
//                CameraManager.manager = CameraManager();
//            }
//            return CameraManager.manager!!;
//        }
//    }

    fun clear() {
        active = false;
        cameraData = null;
        oldData = null;
    }

    fun isActive(): Boolean {
        return active;
    }

    fun maybeData(): CameraData? {
        return cameraData;
    }

    fun getRotation(): Vector2d {
        if (cameraData != null) {
            if (cameraData!!.targetLocation != null) {
                val data = cameraData!!;
                val dx = data.targetLocation!!.x - data.location.x;
                val dy = data.targetLocation.y - data.location.y;
                val dz = data.targetLocation.z - data.location.z;
                val dXZ = sqrt(dx.pow(2) + dz.pow(2));

                val pitch = -Math.toDegrees(atan2(
                    dy,
                    dXZ
                ));
                val yaw = -Math.toDegrees(
                    atan2(
                        dx,
                        dz
                    )
                );

                if (cameraData!!.easeOptions != null) {
                    val delta = Clock.System.now().toEpochMilliseconds() - lastMs;

                    val percentage = min(delta.toFloat() / cameraData!!.easeOptions!!.duration.toFloat(), 1f);

                    val res = cameraData!!.easeOptions!!.easing.invoke(percentage);

                    val oldRot = if (oldData?.targetLocation != null) {
                        calcRotation(
                            oldData!!.location,
                            oldData!!.targetLocation!!
                        )
                    } else {
                        Vector2d(lastPlayerRotation!!.x.toDouble(), lastPlayerRotation!!.y.toDouble())
                    }
//                    val oldPitch = if (oldData?.lookAt != null) {
//                        calcRotation(
//                            oldData!!.position,
//                            oldData!!.lookAt!!
//                        ).x
//                    } else {
//                        lastPlayerRotation!!.x.toDouble()
//                    }

//                    val deltaYaw = yaw - oldYaw
//                    val fYaw = oldYaw + deltaYaw * res
//                    val deltaPitch = pitch - oldPitch;
//                    val fPitch = pitch + deltaPitch * res;
//
//                    println("oldYaw: $oldYaw, oldPitch: $oldPitch")
//                    println("newYaw: $yaw, newPitch: $pitch")
//                    println("res: $res")

                    val newRot = Vector2d(pitch, yaw);
                    var rotDelta = newRot.sub(oldRot);

                    if (rotDelta.y > 180) rotDelta = Vector2d(rotDelta.x, rotDelta.y - 360)
                    if (rotDelta.y < -180) rotDelta = Vector2d(rotDelta.x, rotDelta.y + 360)

                    val fRot = oldRot.add(rotDelta.mul(res.toDouble()))

                    return fRot;
                }
                println("newYaw: $yaw, newPitch: $pitch")

//                MinecraftClient.getInstance().player!!.sendMessage(Text.literal("$pitch $yaw"), false)
                return Vector2d(pitch, yaw);
            } else {
                return Vector2d(0.0, 0.0)
            }
        } else {
            return Vector2d(0.0, 0.0)
        }
    }

    fun getPosition(): Vec3d {
        if (cameraData != null) {
            val delta = Clock.System.now().toEpochMilliseconds() - lastMs;
//            lastMs = Clock.System.now().toEpochMilliseconds();

            if (cameraData!!.easeOptions != null) {
                val percentage = min((delta.toFloat() / cameraData!!.easeOptions!!.duration.toFloat()), 1f);

                val res = cameraData!!.easeOptions!!.easing.invoke(percentage);

                val oldPos = if (oldData != null) {
                    oldData!!.location
                } else {
                    lastPlayerLocation!!
                }

                val deltaPos = cameraData!!.location.subtract(oldPos);

                location = oldPos.add(
                    deltaPos.multiply(res.toDouble())
                );
                return location!!
            }

            location = cameraData!!.location;
            return cameraData!!.location
        } else {
            return Vec3d.ZERO
        }
    }

    fun setCamera(data: CameraData) {
        oldData = cameraData;
        cameraData = data;
        lastMs = Clock.System.now().toEpochMilliseconds();
        lastPlayerLocation = client.player!!.eyePos;
        lastPlayerRotation = client.player!!.rotationClient;
        active = true;
    }

    private fun calcRotation(pos0: Vec3d, pos1: Vec3d): Vector2d {
        val dx = pos1.x - pos0.x;
        val dy = pos1.y - pos0.y;
        val dz = pos1.z - pos0.z;
        val dXZ = sqrt(dx.pow(2) + dz.pow(2));

        val pitch = -Math.toDegrees(atan2(
            dy,
            dXZ
        ));
        val yaw = -Math.toDegrees(
            atan2(
                dx,
                dz
            )
        );

        return Vector2d(pitch, yaw);
    }

    fun shouldRenderPlayer(): Boolean {
        val playerPos = client.player!!.eyePos;

        return playerPos.distanceTo(location) > 0.45
    }

    fun getFadeInfo(): Pair<Int, Float> {
        if (fadeData == null) {
            return Pair(0, 0f)
        }

        val data = fadeData!!;

        val curr = Clock.System.now().toEpochMilliseconds();

//        GameRenderer

        if (curr > lastMsFade + data.fadeInTime + data.holdTime + data.fadeOutTime) {
            return Pair(0, 0f)
        }
        if (curr > lastMsFade && curr < lastMsFade + data.fadeInTime) {
            val delta = curr - lastMsFade;
            val percentage = min(delta.toFloat() / data.fadeInTime.toFloat(), 1f);

            return Pair(data.fadeColor, percentage)
        }
        if (curr > lastMsFade + data.fadeInTime && curr < lastMsFade + data.fadeInTime + data.holdTime) {
            return Pair(data.fadeColor, 1f)
        }
        if (curr > lastMsFade + data.fadeInTime + data.holdTime && curr < lastMsFade + data.fadeInTime + data.holdTime + data.fadeOutTime) {
            val delta = curr - lastMsFade - data.fadeInTime - data.holdTime;
            val percentage = 1f - min((delta.toFloat() / data.fadeOutTime.toFloat()), 1f);

            return Pair(data.fadeColor, percentage)
        }
        return Pair(0, 0f)
    }

    fun setFade(fade: CameraFadeOptions) {
        fadeData = fade;
        lastMsFade = Clock.System.now().toEpochMilliseconds();
    }
}