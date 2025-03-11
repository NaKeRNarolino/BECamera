package nakern.be_camera.camera

import net.minecraft.util.math.Vec3d

data class CameraData(
    val location: Vec3d,
    val targetLocation: Vec3d? = null,
    val easeOptions: EaseOptions? = null
)
