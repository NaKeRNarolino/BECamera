package nakern.be_camera.camera

import nakern.be_camera.easings.EasingFn

/**
 * Easing options of the Camera.
 */
data class EaseOptions(
    /**
     * The function used to ease. There are a bunch of pre-built easing functions in [nakern.be_camera.easings.Easings]
     */
    val easing: EasingFn,
    /**
     * The duration of the transition, measured in milliseconds.
     */
    val duration: Long
)