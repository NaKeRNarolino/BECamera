package nakern.be_camera.camera

/**
 * Describes Camera's fade: its color, fade in, hold, and fade out times measured in milliseconds.
 * */
data class CameraFadeOptions(
    /**
     * The color of the fade in ARGB format.
     */
    val fadeColor: Int,
    /**
     * Fade in time, measured in milliseconds.
     */
    val fadeInTime: Long,
    /**
     * Hold time - the time between fading in and fading out, measured in milliseconds.
     */
    val holdTime: Long,
    /**
     * Fade out time, measured in milliseconds.
     */
    val fadeOutTime: Long
)
