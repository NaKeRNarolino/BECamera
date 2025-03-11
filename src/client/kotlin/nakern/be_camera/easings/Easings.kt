package nakern.be_camera.easings

import kotlin.math.pow

typealias EasingFn = (Float) -> Float;

object Easings {
    fun none(x: Float): Float {
        return 1.0f;
    }

    fun linear(x: Float): Float {
        return x;
    }

    fun easeInOutBack(x: Float): Float {
        val c1 = 1.70158;
        val c2 = c1 * 1.525;

        return if (x < 0.5) {
            ((2 * x).pow(2) * ((c2 + 1) * 2 * x - c2) / 2).toFloat()
        } else {
            (((2 * x - 2).pow(2) * ((c2 + 1) * (x * 2 - 2) + c2) + 2) / 2).toFloat()
        }
    }
}