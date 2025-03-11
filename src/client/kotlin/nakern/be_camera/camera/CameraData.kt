package nakern.be_camera.camera

import net.minecraft.util.math.Vec3d

/**
 * Describes player's camera state. Location is mandatory.
 * */
data class CameraData(
    /**
     * The location of the camera.
     */
    val location: Vec3d,
    /**
     * The target location of the camera. The camera will point at that position.
     */
    val targetLocation: Vec3d? = null,
    /**
     * Easing options for the camera. Determines the transition from the old state to the new one.
     */
    val easeOptions: EaseOptions? = null
)
